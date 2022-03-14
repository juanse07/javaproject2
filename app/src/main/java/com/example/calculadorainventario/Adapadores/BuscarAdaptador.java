package com.example.calculadorainventario.Adapadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.calculadorainventario.ClickInterface1;
import com.example.calculadorainventario.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class BuscarAdaptador extends RecyclerView.Adapter<BuscarAdaptador.Viewholder> {
    ArrayList<String> Busquedas;
    private ClickInterface1 clickInterface1;

  CharSequence Busq;
  int Position1;
  int Position2;
  int row_index=-1;
  Viewholder viewholder;
  CardView card1;



    public BuscarAdaptador(ArrayList<String>Busquedas, ClickInterface1 clickInterface1){
        this.Busquedas=Busquedas;
        this.clickInterface1=clickInterface1;

    }




    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buscartarjetas, parent, false);


        return new Viewholder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder holder, final int position) {

        holder.textBusquedas.setText(Busquedas.get(position));

        //holder.getAdapterPosition();


        Position2=holder.getAdapterPosition();
        card1=holder.CardBusquedas;




        card1.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                CharSequence tipo=holder.textBusquedas.getText();
                if(tipo.equals(v.getResources().getString(R.string.Sales))){

                CharSequence tipodoc= v.getResources().getString(R.string.Sales).trim();
                clickInterface1.PassTipoDoc(position,tipodoc);
                row_index=position;
                notifyDataSetChanged();
                }else if(tipo.equals(v.getResources().getString(R.string.Receipts))){
                    CharSequence tipodoc= v.getResources().getString(R.string.Receipts).trim();
                    clickInterface1.PassTipoDoc(position,tipodoc);
                    row_index=position;
                    notifyDataSetChanged();

                }else if(tipo.equals(v.getResources().getString(R.string.Draft))){
                    CharSequence tipodoc= v.getResources().getString(R.string.Draft).trim();
                    clickInterface1.PassTipoDoc(position,tipodoc);
                    row_index=position;
                    notifyDataSetChanged();

                }



            }
        });
        if(row_index==position){
            holder.CardBusquedas.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.Turquesa));
            holder.textBusquedas.setTextColor(holder.itemView.getResources().getColor(R.color.colorGrisoscuro));

        }
        else
        {
            holder.CardBusquedas.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.colorGrisoscuro));
            holder.textBusquedas.setTextColor(holder.itemView.getResources().getColor(R.color.colorBlancox));
        }







    }




    @Override
    public int getItemCount() {
        return Busquedas.size();
    }



    public static class Viewholder extends RecyclerView.ViewHolder {
        TextView textBusquedas;
        CardView CardBusquedas;
       // int row_index=-1;




        public Viewholder(@NonNull final View itemView) {
            super(itemView);
            textBusquedas=itemView.findViewById(R.id.textBusquedas);
            CardBusquedas=itemView.findViewById(R.id.CardBusquedas);




        }

      /*  @Override
       public void onClick(View v) {
           /* if (selectedItems.get(getAdapterPosition(), false)) {
                selectedItems.delete(getAdapterPosition());
                CardBusquedas.setSelected(false);
            }
            else {
                selectedItems.put(getAdapterPosition(), true);
                CardBusquedas.setSelected(true);

        }*/
    }
}
