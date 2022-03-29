package com.example.calculadorainventario.Adapadores;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculadorainventario.AlertDialogos.deletedialogo;
import com.example.calculadorainventario.AlertDialogos.sionoalert;
import com.example.calculadorainventario.ClickInterface1;
import com.example.calculadorainventario.R;
import com.example.calculadorainventario.ViewModel.SharedViewModel;
import com.example.calculadorainventario.constructornom2;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHoldercliente> implements Filterable {
    private final ClickInterface1 clickInterface1;
    String tag1,tag2,tag3,tag4,tag5,tag6,tx1,tx2,tx3,tx4,tx5,tx6;
    deletedialogo deletedialogo;
    sionoalert sionoalert;
    FirebaseAuth mAuth;
    SharedViewModel sharedViewModel;
    int row_index=-1;


    ArrayList<constructornom2> ListaClientes;
    ArrayList<constructornom2>ListaClientesFull;
    public ClienteAdapter(ArrayList<constructornom2> listaClientes,ClickInterface1 clickInterface1) {
        ListaClientes = listaClientes;
        ListaClientesFull=ListaClientes;
        this.clickInterface1=clickInterface1;

    }
    @NonNull
    @Override
    public ViewHoldercliente onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.visorfb,parent,false);
        return new ViewHoldercliente(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHoldercliente holder, final int position) {
        holder.txnombre45.setText(ListaClientes.get(position).getCliente_Nombre());
        holder.btaddcliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Cliente=holder.txnombre45.getText().toString();
                String Email=ListaClientes.get(position).getEmail();
                String Phone=ListaClientes.get(position).getTel();
                String Address=ListaClientes.get(position).getAddress();
                String City=ListaClientes.get(position).getCity();
                clickInterface1.onButtonclienteClick(position);
                clickInterface1.passingcliente2Click(position,Cliente,Phone,Email,Address,City);
                row_index=position;
                notifyDataSetChanged();
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                deletedialogo=new deletedialogo();
                tag1=holder.itemView.getResources().getString(R.string.Customer_Name);
                tag2=holder.itemView.getResources().getString(R.string.Email);
                tag3=holder.itemView.getResources().getString(R.string.Mobile_phone);
                tag4=holder.itemView.getResources().getString(R.string.Address);
                tag5=holder.itemView.getResources().getString(R.string.City);
                tx1=ListaClientes.get(position).getCliente_Nombre();
                tx2=ListaClientes.get(position).getEmail();
                tx3=ListaClientes.get(position).getTel();
                tx4=ListaClientes.get(position).getAddress();
                tx5=ListaClientes.get(position).getCity();


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

                                getposicion(position);
                                confirmdialog.dismiss();

                            }
                        });





                    }
                });

                return true;
            }
        });

        if(row_index==position){
            holder.btaddcliente.setBackgroundColor(holder.itemView.getResources().getColor(R.color.colorAccent));

            // holder.buttonaddproducto.setBackgroundColor(holder.itemView.getResources().getColor(R.color.colorAccent));
            holder.btaddcliente.setTextColor(holder.itemView.getResources().getColor(R.color.colorBlancox));
            //holder.textBusquedas.setTextColor(holder.itemView.getResources().getColor(R.color.colorBla));

        }
        else
        {
            holder.btaddcliente.setBackgroundColor(holder.itemView.getResources().getColor(R.color.colorGris));
            holder.btaddcliente.setTextColor(holder.itemView.getResources().getColor(R.color.colorPrimary));
            // holder.textBusquedas.setTextColor(holder.itemView.getResources().getColor(R.color.colorGris));
        }
      /*  holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ClienteEdit.class);
                intent.putExtra("cliente", ListaClientes.get(position).getNombre());
                intent.putExtra("tel",ListaClientes.get(position).getTel());
                intent.putExtra("key",ListaClientes.get(position).getKey());
                v.getContext().startActivity(intent);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return ListaClientes.size();
    }

    @Override
    public Filter getFilter() {
        return ClienteFilter;
    }
    private Filter ClienteFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<constructornom2> filteredClienteList=new ArrayList<>();
            if(constraint==null||constraint.length()==0){
                filteredClienteList.addAll(ListaClientesFull);

            }else{
                String filterClientePattern= constraint.toString().toLowerCase().trim();
                for(constructornom2 item:ListaClientesFull){
                    if(item.getCliente_Nombre().toLowerCase().startsWith(constraint.toString().toLowerCase().trim())){
                        filteredClienteList.add(item);
                    }
                }
            }
            FilterResults clienteresults=new FilterResults();
            clienteresults.values=filteredClienteList;
            return clienteresults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults clienteresults) {
            ListaClientes=((ArrayList)clienteresults.values);

            notifyDataSetChanged();

        }
    };

    class ViewHoldercliente extends RecyclerView.ViewHolder{
        TextView txnombre45;
        MaterialButton btaddcliente;

        public ViewHoldercliente(@NonNull View itemView) {
            super(itemView);
            Context Acontext=itemView.getContext();
            txnombre45 = itemView.findViewById(R.id.txnombre45);
            btaddcliente=itemView.findViewById(R.id.btaddcliente);
        }
    }

//
    public void getposicion(int position){
        mAuth = FirebaseAuth.getInstance();
        DatabaseReference ref;
        ListaClientes.get(position);

    String key=ListaClientes.get(position).getKey();
    final String id = mAuth.getCurrentUser().getUid();
    ref = FirebaseDatabase.getInstance().getReference().child("CLIENTE").child(id).child(key);
        ref.removeValue();}



}
