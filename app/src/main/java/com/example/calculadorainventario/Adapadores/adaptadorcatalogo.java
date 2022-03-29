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

import com.example.calculadorainventario.AlertDialogos.deletedialogo;
import com.example.calculadorainventario.AlertDialogos.sionoalert;
import com.example.calculadorainventario.ClickInterface1;
import com.example.calculadorainventario.Crearproducto;
import com.example.calculadorainventario.NoteProdViewModel;
import com.example.calculadorainventario.Constructores.NoteProducto;
import com.example.calculadorainventario.R;
import com.example.calculadorainventario.ViewModel.SharedViewModel;
import com.example.calculadorainventario.Constructores.cuerospinner;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
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
    String tag1,tag2,tag3,tag4,tag5,tag6,tx1,tx2,tx3,tx4,tx5,tx6;
    NoteProducto noteProducto;
    NoteProdViewModel noteProdViewModel;
    com.example.calculadorainventario.AlertDialogos.deletedialogo deletedialogo;
    com.example.calculadorainventario.AlertDialogos.sionoalert sionoalert;


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

//
        holder.cantup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double valorritmo = 1.0;

             holder.buttonup(valorritmo,holder.canttext2);

            }
        });
        holder.cantdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Double valorritmo = 1.0;

                holder.buttondown(valorritmo,holder.canttext2);

            }
        });
        holder.priceup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double valorritmo = Double.parseDouble(productos.get(position).getRitmo());

               holder.buttonup(valorritmo,holder.txcatprice);

            }
        });
        holder.pricedown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Double valorritmo =  Double.parseDouble(productos.get(position).getRitmo());;

                holder.buttondown(valorritmo,holder.txcatprice);
            }
        });
        holder.txcatprice.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String price=holder.itemView.getResources().getString(R.string.Price);
               holder.showdialog(holder.txcatprice,price,holder.itemView);


            }
        });
        holder.canttext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Cant=holder.itemView.getResources().getString(R.string.Quantity);
                holder.showdialog(holder.canttext2,Cant,holder.itemView);
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
            holder.buttonaddproducto.setTextColor(holder.itemView.getResources().getColor(R.color.colorPrimary));
            // holder.textBusquedas.setTextColor(holder.itemView.getResources().getColor(R.color.colorGris));
        }


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                deletedialogo=new deletedialogo();
                tag1=holder.itemView.getResources().getString(R.string.Product_Name);
                tag2=holder.itemView.getResources().getString(R.string.Price);
                tag3=holder.itemView.getResources().getString(R.string.Ritmo);
                tag4=holder.itemView.getResources().getString(R.string.Description);
                tag5=holder.itemView.getResources().getString(R.string.Tax);
                tx1=productos.get(position).getTIPO_CUERO();
                tx2=productos.get(position).getPrecio();
                tx3=productos.get(position).getRitmo();
                tx4=productos.get(position).getImpuesto();
                tx5=productos.get(position).getDescripcion();


                final AlertDialog dialogodelete=deletedialogo.DeleteDialogo(holder.itemView.getContext(),v,tag1,tag2,tag3,tag4,tag5,tag6,
                        tx1,tx2,tx3,tx4,tx5,tx6);
                deletedialogo.btndismiss(dialogodelete);
                deletedialogo.btnokay().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogodelete.dismiss();
                        sionoalert=new sionoalert();
                        final AlertDialog confirmdialog= sionoalert.sionoalert(holder.itemView.getContext(),v);

                        sionoalert.btncancel(confirmdialog,dialogodelete);
                        sionoalert.btnokay().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                holder.getpos(position);
                                confirmdialog.dismiss();

                            }
                        });





                    }
                });

//                AlertDialog.Builder builder1=deletedialogo.getBuilder();
//                View view1=deletedialogo.getiew();

//                btndismiss=view1.findViewById(R.id.mtbdismiss);
//                btndismiss.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialogodelete.dismiss();
//                    }
//                });




                return true;
            }
        });

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
                        String Descripcion=productos.get(position).getDescripcion();




                        noteProducto=new NoteProducto(Nombre_prod,Cant_Prod,Precio_prod,Resultado_valor,Impuesto,Resultado_impuesto,Descripcion);
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
        MaterialCardView priceup,pricedown,cantup,cantdown;


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
            cantdown=itemView.findViewById(R.id.cantdown);
            cantup=itemView.findViewById(R.id.cantup);
            priceup=itemView.findViewById(R.id.priceup);
            pricedown=itemView.findViewById(R.id.picedown);


            mAuth = FirebaseAuth.getInstance();








        }

    public void buttonup(Double valorritmo,TextView textView){

        Double valorcant = Double.parseDouble(textView.getText().toString());

        Double valornuevosuma = valorcant + valorritmo;
        textView.setText(valornuevosuma + "");

    }
    public void buttondown(Double valorritmo,TextView textView){

        Double valorcant = Double.parseDouble(textView.getText().toString());

        Double valornuevosuma = valorcant - valorritmo;
        textView.setText(valornuevosuma + "");

    }


        private  void showdialog(final TextView textview, String input1,View itemview){
            AlertDialog.Builder builder=new AlertDialog.Builder(itemview.getContext(),R.style.Theme_MaterialComponents_Dialog_Alert);
            ;
            final View view=LayoutInflater.from(itemview.getContext()).inflate(R.layout.edittextdialog,(ConstraintLayout)itemview.findViewById(R.id.parentconstrait));
            builder.setView(view);
            ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).setText(textview.getText().toString());
            ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).requestFocus();
            ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).setHint(input1);
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

                    textview.setText( ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).getText().toString());

                    alertDialog.dismiss();

                }
            });
        }

        public void getpos(int position){
            mAuth = FirebaseAuth.getInstance();
            DatabaseReference ref;
            productos.get(position);


            String key=productos.get(position).getKey();
            final String id = mAuth.getCurrentUser().getUid();
            ref = FirebaseDatabase.getInstance().getReference().child("PRODUCTOS").child(id).child(key);
            ref.removeValue();

        }



    }


}
