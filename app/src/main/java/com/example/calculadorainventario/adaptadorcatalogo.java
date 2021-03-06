package com.example.calculadorainventario;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;



public class adaptadorcatalogo extends RecyclerView.Adapter<adaptadorcatalogo.ViewHolder>implements Filterable  {
    public static final String GROUPSNAME_SHARED_PREF = "groupname";


    private ArrayList<cuerospinner>productos;
    private ArrayList<cuerospinner>productosfull;


    private  ClickInterface1 clickInterface1;
    int row_index=-1;


    SharedViewModel sharedViewModel;
    DatabaseReference ref;
    FirebaseAuth mAuth;





    public adaptadorcatalogo(ArrayList<cuerospinner> productos,ClickInterface1 clickInterface1) {



        this.productos = productos;
        productosfull=productos;
        this.clickInterface1=clickInterface1;
    }

    @NonNull
    @Override
    public adaptadorcatalogo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.catalogocard,parent,false);

        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final adaptadorcatalogo.ViewHolder holder, final int position) {
        holder.preciotext.setText("1000");

        //DataProcessor dataProcessor = new DataProcessor();
       // holder.itemView.setTag(productos.get(position));
        //holder.preciotext.setText(dataProcessor.getStr("precio"));
       holder.preciotext.setText(productos.get(position).getPrecio());
        holder.nombreproducto.setText(productos.get(position).getTIPO_CUERO());

        final String Ritmo1=productos.get(position).getRitmo();
        final String Impuesto1=productos.get(position).getImpuesto();
        final String estadoimp=productos.get(position).getEstado_Imp();
        if(estadoimp.equals("SI")){
            holder.checkIVA.setChecked(true);
        }else {holder.checkIVA.setChecked(false);}
        holder.configcrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),Crearproducto.class);

                intent.putExtra("producto",holder.nombreproducto.getText().toString());
                intent.putExtra("key",productos.get(position).getKey());
                intent.putExtra("precio",holder.preciotext.getText().toString());
                intent.putExtra("estado",estadoimp);
                intent.putExtra("ritmo",Ritmo1);
                intent.putExtra("impuesto",Impuesto1);
                v.getContext().startActivity(intent);
            }
        });


        holder.preciomas.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int valorprecio = Integer.parseInt(holder.preciotext.getText().toString());
               int valorritmo = Integer.parseInt(Ritmo1);
               int valornuevosuma = valorprecio + valorritmo;
               holder.preciotext.setText(valornuevosuma + "");







           }
       });
       holder.preciomenos.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int valorprecio = Integer.parseInt(holder.preciotext.getText().toString());
               int valorritmo = Integer.parseInt(Ritmo1);
               int valornuevosuma = valorprecio - valorritmo;
               holder.preciotext.setText(valornuevosuma + "");

           }
       });

        holder.buttonaddproducto.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {





                //cuerospinner current = productos.get(position);
             CharSequence Producto= holder.nombreproducto.getText();
             CharSequence Precio=holder.preciotext.getText();
                clickInterface1.onButtonAddClick(position);
                clickInterface1.passingproductoClick(position,Producto);
                clickInterface1.passingprecio1Click(position,Precio);
                row_index=position;
                notifyDataSetChanged();

                final String id = mAuth.getCurrentUser().getUid();
                ref = FirebaseDatabase.getInstance().getReference().child("PRODUCTOS").child(id).child(productos.get(position).getKey());
                ref.child("Precio").setValue(holder.preciotext.getText().toString());
                if(holder.checkIVA.isChecked()){
                ref.child("Estado_Imp").setValue("SI");}else { ref.child("Estado_Imp").setValue("NO");}

               // String Varposition2= Constants.getSP(holder.itemView.getContext()).getpositioncatalogo();
                //holder.buttonaddproducto.setBackgroundTintList(holder.itemView.getResources().getColorStateList(R.color.colorGrisclaro));
               




            }
        });
        if(row_index==position){
            holder.buttonaddproducto.setBackgroundColor(holder.itemView.getResources().getColor(R.color.colorAccent));

           // holder.buttonaddproducto.setBackgroundColor(holder.itemView.getResources().getColor(R.color.colorAccent));
            holder.buttonaddproducto.setTextColor(holder.itemView.getResources().getColor(R.color.colorBlancox));
            //holder.textBusquedas.setTextColor(holder.itemView.getResources().getColor(R.color.colorBla));

        }
        else
        {
            holder.buttonaddproducto.setBackgroundColor(holder.itemView.getResources().getColor(R.color.colorGris));
            holder.buttonaddproducto.setTextColor(holder.itemView.getResources().getColor(R.color.colorAccent));
           // holder.textBusquedas.setTextColor(holder.itemView.getResources().getColor(R.color.colorGris));
        }

    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    @Override
    public Filter getFilter() {
        return FiltroProducto;
    }
private Filter FiltroProducto=new Filter() {
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        ArrayList<cuerospinner> filteredProdList=new ArrayList<>();
        if(constraint==null||constraint.length()==0){
            filteredProdList.addAll(productosfull);

        }else{
            String filterProdPattern= constraint.toString().toLowerCase().trim();
            for(cuerospinner item:productosfull){
                if(item.getTIPO_CUERO().toLowerCase().contains(constraint.toString().toLowerCase().trim())){
                    filteredProdList.add(item);
                }
            }
        }
        FilterResults prodresults=new FilterResults();
        prodresults.values=filteredProdList;
        return prodresults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults prodresults) {
        productos=((ArrayList)prodresults.values);

        notifyDataSetChanged();

    }
};

    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView nombreproducto;
        Button buttonaddproducto;
        ImageView preciomas, preciomenos,configcrear;
        CheckBox checkIVA;


        EditText preciotext;









        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreproducto = itemView.findViewById(R.id.nombreproducto);
            buttonaddproducto = itemView.findViewById(R.id.buttonaddproducto);
            preciomas=itemView.findViewById(R.id.preciomas);
            preciomenos=itemView.findViewById(R.id.preciomenos);
            preciotext=itemView.findViewById(R.id.preciotext);
            configcrear=itemView.findViewById(R.id.configcrear);
            checkIVA=itemView.findViewById(R.id.checkIVA);


            mAuth = FirebaseAuth.getInstance();








        }
    }


}
