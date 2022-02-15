package com.example.calculadorainventario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorNoteProd extends RecyclerView.Adapter<AdaptadorNoteProd.myClass> {
    List<NoteProducto>notesProd= new ArrayList<>();
    @NonNull
    @Override
    public AdaptadorNoteProd.myClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nuevavistafac, parent, false);
        return new myClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorNoteProd.myClass holder, int position) {
        NoteProducto currentnote= notesProd.get(position);
        holder.nombreproducto00.setText(currentnote.getNombre_prod());
        holder.text1.setText(currentnote.getCant_Prod());
        holder.textm2.setText(currentnote.getPrecio_prod());


    }

    @Override
    public int getItemCount() {
        return notesProd.size();
    }


    class myClass extends RecyclerView.ViewHolder{
        TextView nombreproducto00,text1,textm2;
        ImageView min1,min2,mas1,mas2;
        public myClass(@NonNull View itemView) {

            super(itemView);
            nombreproducto00 = itemView.findViewById(R.id.nobreproducto00);
            text1 = itemView.findViewById(R.id.text1);
            textm2=itemView.findViewById(R.id.textm2);
            min1=itemView.findViewById(R.id.min1);
            min2=itemView.findViewById(R.id.min2);
            mas1=itemView.findViewById(R.id.mas1);
            mas2=itemView.findViewById(R.id.mas2);
        }

}
    public void setNotes(List<NoteProducto>notesProd){
        this.notesProd=notesProd;
        notifyDataSetChanged();


    }

}
