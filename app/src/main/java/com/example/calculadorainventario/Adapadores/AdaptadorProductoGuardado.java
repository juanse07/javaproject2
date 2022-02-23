package com.example.calculadorainventario.Adapadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.calculadorainventario.Note;
import com.example.calculadorainventario.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorProductoGuardado extends RecyclerView.Adapter<AdaptadorProductoGuardado.myClass> {
    List<Note>notes=new ArrayList<>();
    List<Double>Medida=new ArrayList<>();
    Double medida1=null;

    @NonNull
    @Override
    public myClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.arrayview, parent, false);
        return new myClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myClass holder, int position) {
        Note currentnote=notes.get(position);
        holder.txmedidaproducto.setText(String.valueOf(currentnote.getValor_Medida()));
       holder.txserial.setText(String.valueOf(position+1+"."));
       medida1=currentnote.getValor_Medida();
       Medida.add(medida1);

    }



    @Override
    public int getItemCount() {
        return notes.size();
    }
    public int getItemCount2() {
        return Medida.size();
    }
    class myClass extends RecyclerView.ViewHolder{
        TextView txserial, txmedidaproducto;
        public myClass(@NonNull View itemView) {

            super(itemView);
            txserial = itemView.findViewById(R.id.txserial);
            txmedidaproducto = itemView.findViewById(R.id.txmedidaproducto);
        }
    }
    public void setNotes(List<Note>notes){
        this.notes=notes;
        notifyDataSetChanged();


    }
    public Double getmedida(int position){
        notifyDataSetChanged();

        return

        notes.get(position).getValor_Medida();




    }
}
