package com.example.calculadorainventario.Adapadores;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.calculadorainventario.ClickInterface1;
import com.example.calculadorainventario.Crearproducto;
import com.example.calculadorainventario.NoteProdViewModel;
import com.example.calculadorainventario.NoteProducto;
import com.example.calculadorainventario.R;
import com.example.calculadorainventario.SharedViewModel;
import com.example.calculadorainventario.Constructores.cuerospinner;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;



public class adaptadorcatalogo extends RecyclerView.Adapter<adaptadorcatalogo.ViewHolder>implements Filterable  {
    public static final String GROUPSNAME_SHARED_PREF = "groupname";


    private ArrayList<cuerospinner>productos;
    private ArrayList<cuerospinner>productosfull;
    NoteProducto noteProducto;
    NoteProdViewModel noteProdViewModel;


    private ClickInterface1 clickInterface1;
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
        holder.txcatprice.setText("1000");
        holder.canttext2.setText("1");

        //DataProcessor dataProcessor = new DataProcessor();
       // holder.itemView.setTag(productos.get(position));
        //holder.txcatprice.setText(dataProcessor.getStr("precio"));
       holder.txcatprice.setText(productos.get(position).getPrecio());
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
                Intent intent=new Intent(v.getContext(), Crearproducto.class);

                intent.putExtra("producto",holder.nombreproducto.getText().toString());
                intent.putExtra("key",productos.get(position).getKey());
                intent.putExtra("precio",holder.txcatprice.getText().toString());
                intent.putExtra("estado",estadoimp);
                intent.putExtra("ritmo",Ritmo1);
                intent.putExtra("impuesto",Impuesto1);
                v.getContext().startActivity(intent);
            }
        });


        holder.preciomas.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int valorprecio = Integer.parseInt(holder.txcatprice.getText().toString());

               int valorritmo = 50;
               int valornuevosuma = valorprecio + valorritmo;
               holder.txcatprice.setText(valornuevosuma + "");







           }
       });
       holder.preciomenos.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int valorprecio = Integer.parseInt(holder.txcatprice.getText().toString());
//               int valorritmo = Integer.parseInt(Ritmo1);
               int valorritmo = 50;

               int valornuevosuma = valorprecio - valorritmo;
               holder.txcatprice.setText(valornuevosuma + "");

           }
       });

        holder.cantmas2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valorcant = Integer.parseInt(holder.canttext2.getText().toString());
                int valorritmo = 1;
                int valornuevosuma = valorcant + valorritmo;
                holder.canttext2.setText(valornuevosuma + "");







            }
        });
        holder.txcatprice.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext(),R.style.Theme_MaterialComponents_Dialog_Alert);
                ;
                final View view=LayoutInflater.from(v.getContext()).inflate(R.layout.edittextdialog,(ConstraintLayout)v.findViewById(R.id.parentconstrait));
                builder.setView(view);
                ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).setText(holder.txcatprice.getText().toString());
                ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).requestFocus();
                ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).setHint(view.getResources().getString(R.string.Price));
                ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).setHintTextColor(view.getResources().getColor(R.color.colorGrisoscuro));
                final AlertDialog alertDialog=builder.create();
                if(alertDialog.getWindow() !=null){
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                alertDialog.show();
                ((MaterialButton) view.findViewById(R.id.editdialogbutton)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                ((MaterialButton) view.findViewById(R.id.aceptedtx)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        holder.txcatprice.setText( ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).getText().toString());

                        alertDialog.dismiss();

                    }
                });

            }
        });
        holder.cantmenos2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valorcant = Integer.parseInt(holder.canttext2.getText().toString());
                int valorritmo = 1;
                int valornuevosuma = valorcant - valorritmo;
                holder.canttext2.setText(valornuevosuma + "");

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
            holder.buttonaddproducto.setTextColor(holder.itemView.getResources().getColor(R.color.colorGrisoscuro));
            // holder.textBusquedas.setTextColor(holder.itemView.getResources().getColor(R.color.colorGris));
        }

        holder.buttonaddproducto.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext(),R.style.Theme_MaterialComponents_Dialog_Alert);
                ;
                View view=LayoutInflater.from(v.getContext()).inflate(R.layout.confirmationdialog,(ConstraintLayout)v.findViewById(R.id.parental1));
                builder.setView(view);
                ((TextView) view.findViewById(R.id.txproducalert)).setText(holder.nombreproducto.getText().toString());
                ((TextView) view.findViewById(R.id.txpricealert)).setText(holder.txcatprice.getText().toString());
                ((TextView) view.findViewById(R.id.txquantityalert)).setText(holder.canttext2.getText().toString());
                if(holder.checkIVA.isChecked()){
                ((TextView) view.findViewById(R.id.taxtview)).setText(holder.itemView.getResources().getString(R.string.Tax_included));}else{
                    ((TextView) view.findViewById(R.id.taxtview)).setText("");

                }
                final AlertDialog alertDialog=builder.create();
                if(alertDialog.getWindow() !=null){
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                alertDialog.show();
                ((MaterialButton) view.findViewById(R.id.buttonNO)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                ((MaterialButton) view.findViewById(R.id.buttonYES)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        noteProdViewModel=new ViewModelProvider((ViewModelStoreOwner) v.getContext()).get(NoteProdViewModel.class);



                        //cuerospinner current = productos.get(position);
                        String Nombre_prod= holder.nombreproducto.getText().toString();
                        CharSequence Precio=holder.txcatprice.getText();
                        CharSequence Producto=holder.nombreproducto.getText();
                        Double Impuesto=Double.parseDouble(productos.get(position).getImpuesto().toString());



                        CharSequence Cantidad=holder.canttext2.getText();
                        clickInterface1.onButtonAddClick(position);
                        clickInterface1.passingproductoClick(position,Producto,Precio,Cantidad);
                        clickInterface1.passingprecio1Click(position,Precio);

                        Double Precio_prod=Double.parseDouble(holder.txcatprice.getText().toString());
                        Double Cant_Prod= Double.parseDouble(holder.canttext2.getText().toString());

//                int valorritmo = 1;
                        Double valornuevosuma = Precio_prod * Cant_Prod;
                        Double Resultado_valor=valornuevosuma;
                        Double Resultado_impuesto;
                        Double valImp=Impuesto/100;
                        Double valImps2=valornuevosuma*valImp;

                        Resultado_impuesto=valornuevosuma+valImps2;




                        noteProducto=new NoteProducto(Nombre_prod,Cant_Prod,Precio_prod,Resultado_valor,Impuesto,Resultado_impuesto);
                        noteProdViewModel.Insert(noteProducto);
                        int key=noteProducto.Key;
                        Log.d("key",String.valueOf(key));


                        row_index=position;
                        notifyDataSetChanged();
//                noteProdViewModel.getSumTotal();
//                Log.d("values:",String.valueOf(noteProdViewModel.getSumTotal()));

                        final String id = mAuth.getCurrentUser().getUid();
                        ref = FirebaseDatabase.getInstance().getReference().child("PRODUCTOS").child(id).child(productos.get(position).getKey());
                        ref.child("Precio").setValue(holder.txcatprice.getText().toString());
                        if(holder.checkIVA.isChecked()){
                            ref.child("Estado_Imp").setValue("SI");}else { ref.child("Estado_Imp").setValue("NO");}

                        // String Varposition2= Constants.getSP(holder.itemView.getContext()).getpositioncatalogo();
                        //holder.buttonaddproducto.setBackgroundTintList(holder.itemView.getResources().getColorStateList(R.color.colorGrisclaro));


//                        holder.buttonaddproducto.setBackgroundColor(holder.itemView.getResources().getColor(R.color.mdtp_white));
//                        holder.buttonaddproducto.setEnabled(false);
//                        holder.buttonaddproducto.setTextColor(holder.itemView.getResources().getColor(R.color.colorGrisoscuro));
//                        holder.buttonaddproducto.setText("Added");
                        alertDialog.dismiss();
                    }
                });









            }
        });


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
        TextView nombreproducto,canttext2,txcatprice;
        Button buttonaddproducto;
        ImageView preciomas, preciomenos,configcrear,cantmenos2,cantmas2;
        CheckBox checkIVA;


        EditText preciotext;









        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreproducto = itemView.findViewById(R.id.nombreproducto);
            buttonaddproducto = itemView.findViewById(R.id.buttonaddproducto);
            cantmenos2=itemView.findViewById(R.id.cantmenos2);
            canttext2=itemView.findViewById(R.id.canttext2);
            cantmas2=itemView.findViewById(R.id.cantmas2);
            preciomas=itemView.findViewById(R.id.preciomas);
            preciomenos=itemView.findViewById(R.id.preciomenos);
            preciotext=itemView.findViewById(R.id.preciotext);
            configcrear=itemView.findViewById(R.id.deletesym);
            checkIVA=itemView.findViewById(R.id.checkIVA);
            txcatprice=itemView.findViewById(R.id.txcatprice);


            mAuth = FirebaseAuth.getInstance();








        }
        private  void showdialog(){
            AlertDialog.Builder builder=new AlertDialog.Builder(itemView.getContext(),R.style.Theme_MaterialComponents_Dialog_Alert);
;
            View view=LayoutInflater.from(itemView.getContext()).inflate(R.layout.confirmationdialog,(ConstraintLayout)itemView.findViewById(R.id.parental1));
            builder.setView(view);
        }
    }



}
