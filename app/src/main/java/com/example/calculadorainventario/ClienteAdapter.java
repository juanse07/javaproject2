package com.example.calculadorainventario;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHoldercliente> implements Filterable {
    private final ClickInterface1 clickInterface1;
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
        holder.txnombre45.setText(ListaClientes.get(position).getNombre());
        holder.btaddcliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Cliente=holder.txnombre45.getText().toString();
                clickInterface1.onButtonclienteClick(position);
                clickInterface1.passingcliente2Click(position,Cliente);
                row_index=position;
                notifyDataSetChanged();
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
            holder.btaddcliente.setTextColor(holder.itemView.getResources().getColor(R.color.colorAccent));
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
                    if(item.getNombre().toLowerCase().startsWith(constraint.toString().toLowerCase().trim())){
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


}
