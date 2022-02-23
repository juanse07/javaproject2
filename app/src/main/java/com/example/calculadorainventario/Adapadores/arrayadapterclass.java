package com.example.calculadorainventario.Adapadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.calculadorainventario.ClickInterface1;
import com.example.calculadorainventario.R;
import com.example.calculadorainventario.Constructores.arrayconstructor;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class arrayadapterclass extends RecyclerView.Adapter<arrayadapterclass.ViewHolder> {

    //private final vista_fragment context;
    ArrayList<arrayconstructor> listacuero2;
    ClickInterface1 clickInterface1;


   // public arrayadapterclass(ArrayList<arrayconstructor> listacuero2) {
     //   this.listacuero2 = listacuero2;
    //}

    public arrayadapterclass(ArrayList<arrayconstructor> listacueros2,ClickInterface1 clickInterface1) {
       // this.context=context;

        this.clickInterface1=clickInterface1;

    }
    public arrayadapterclass(ArrayList<arrayconstructor>listacueros2){
        this.listacuero2=listacueros2;
    }




    @NonNull
    @Override
    public arrayadapterclass.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.arrayview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull arrayadapterclass.ViewHolder holder, int position) {

        holder.txmedidaproducto.setText(String.valueOf(listacuero2.get(position)));


        holder.txserial.setText(String.valueOf(position + 1 + "."));
       // clickInterface1.passingpositionk(holder.getAdapterPosition());


    }

    @Override
    public int getItemCount() {
        return listacuero2 != null ? listacuero2.size() : 0;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txserial, txmedidaproducto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txserial = itemView.findViewById(R.id.txserial);
            txmedidaproducto = itemView.findViewById(R.id.txmedidaproducto);
        }
    }

}




