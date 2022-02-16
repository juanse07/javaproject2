package com.example.calculadorainventario;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorNoteProd extends RecyclerView.Adapter<AdaptadorNoteProd.myClass> {
    NoteProdViewModel noteProdViewModel;
    List<NoteProducto>notesProd= new ArrayList<>();
    private ClickInterface1 clickInterface1;
    public AdaptadorNoteProd(ClickInterface1 clickInterface1) {



//       this.notesProd = notesProd;
//        notesProdfull=notesProd;
        this.clickInterface1=clickInterface1;
    }

    @NonNull
    @Override
    public AdaptadorNoteProd.myClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nuevavistafac, parent, false);
        return new myClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdaptadorNoteProd.myClass holder, final int position) {

        NoteProducto currentnote= notesProd.get(position);
        final String Producto=currentnote.getNombre_prod();
        final String Cantidad=currentnote.getCant_Prod();
        final String Precio=currentnote.getPrecio_prod();
        holder.nombreproducto00.setText(currentnote.getNombre_prod());
        holder.text1.setText(currentnote.getCant_Prod());
        holder.textm2.setText(currentnote.getPrecio_prod());
        holder.deletesym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                noteProdViewModel=new ViewModelProvider((ViewModelStoreOwner) v.getContext()).get(NoteProdViewModel.class);
                NoteProducto currentnote= notesProd.get(position);

                int currentkey2=currentnote.Key;
                 String Producto=holder.nombreproducto00.getText().toString();
                String Cantidad=holder.text1.getText().toString();
                 String Precio=holder.textm2.getText().toString();
                clickInterface1.PassnoteprodPosition(position,Producto,Cantidad,Precio,currentnote);
                Log.d("values:",String.valueOf(position));
                Log.d("values:",String.valueOf(currentnote.Key));
                Log.d("values:",String.valueOf(Producto));
                Log.d("values:",String.valueOf(Cantidad));
                Log.d("values:",String.valueOf(Precio));



//                clickInterface1.PassnoteprodPosition(position,Producto,Cantidad,Precio);


            }
        });

//        holder.mas2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int valorprecio = Integer.parseInt(holder.textm2.getText().toString());
//                //               int valorritmo = Integer.parseInt(Ritmo1);
//                int valorritmo = 1;
//                int valornuevosuma = valorprecio + valorritmo;
//                holder.textm2.setText(valornuevosuma + "");
//
//
//
//
//
//
//
//            }
//        });
//        holder.min2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int valorprecio = Integer.parseInt(holder.textm2.getText().toString());
//                //               int valorritmo = Integer.parseInt(Ritmo1);
//                int valorritmo = 1;
//                int valornuevosuma = valorprecio - valorritmo;
//                holder.textm2.setText(valornuevosuma + "");
//
//            }
//        });
//
//        holder.mas3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int valorcant = Integer.parseInt(holder.text1.getText().toString());
//                int valorritmo = 50;
//                int valornuevosuma = valorcant + valorritmo;
//                holder.text1.setText(valornuevosuma + "");
//
//
//
//
//
//
//
//            }
//        });
//        holder.min3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int valorcant = Integer.parseInt(holder.text1.getText().toString());
//                int valorritmo = 50;
//                int valornuevosuma = valorcant - valorritmo;
//                holder.text1.setText(valornuevosuma + "");
//
//            }
//        });
//


    }

    @Override
    public int getItemCount() {
        return notesProd.size();
    }


    class myClass extends RecyclerView.ViewHolder{
        TextView nombreproducto00,text1,textm2;
        ImageView min3,min2,mas3,mas2,deletesym;
        public myClass(@NonNull View itemView) {

            super(itemView);
            nombreproducto00 = itemView.findViewById(R.id.nobreproducto00);
            text1 = itemView.findViewById(R.id.text1);
            textm2=itemView.findViewById(R.id.textm2);
            deletesym=itemView.findViewById(R.id.deletesym);

//            mas3=itemView.findViewById(R.id.mas3);
//            min2=itemView.findViewById(R.id.min2);
//            min3=itemView.findViewById(R.id.min3);
//            mas2=itemView.findViewById(R.id.mas2);
        }

}
    public void setNotes(List<NoteProducto>notesProd){
        this.notesProd=notesProd;
        notifyDataSetChanged();


    }

}
