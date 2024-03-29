package com.example.calculadorainventario.Adapadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.calculadorainventario.Constants;
import com.example.calculadorainventario.Constructores.PdfChooseContructor;
import com.example.calculadorainventario.PdfChoose;
import com.example.calculadorainventario.R;
import com.example.calculadorainventario.fragments3;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class pdfadaptador extends RecyclerView.Adapter<pdfadaptador.Viewholder> {
    ArrayList<PdfChooseContructor> myImageList=new ArrayList<>();

    String pdfstate;
    int row_index=-1;
    String titulo;


   public pdfadaptador(ArrayList<PdfChooseContructor>myImageList){
       this.myImageList=myImageList;
//
  }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {




        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pdfsrecyclercard, parent, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder holder, final int position) {
     int previousPos=  Constants.getSP(holder.itemView.getContext()).getPDFPOSITION();
    row_index=previousPos;
     if (row_index==previousPos){
         selectRowindex(position,holder);
     }


        Integer currentimage=myImageList.get(position).getImage();
        titulo=myImageList.get(position).gettittle();
        holder.pdfimage.setImageResource(currentimage);
        holder.pdftitulo.setText(titulo);




        holder.pdfscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Integer currentimage=myImageList.get(position);
                row_index=position;
                notifyDataSetChanged();
                Constants.getSP(v.getContext()).setPDFPOSITION(position);
                Constants.getSP(v.getContext()).setPDFPREFERENCE(titulo);




            }





        });
        selectRowindex(position,holder);





    }




    @Override
    public int getItemCount() {
        return myImageList.size();
    }

    public void selectRowindex(Integer position, Viewholder holder){
        if (row_index==position){

            holder.pdfscard.setStrokeWidth(10);
            holder.pdfscard.setStrokeColor(holder.itemView.getResources().getColor(R.color.colorNegrobrillante));

        }else {
            holder.pdfscard.setStrokeWidth(1);
            holder.pdfscard.setStrokeColor(holder.itemView.getResources().getColor(R.color.colorGrisoscuro));

        }


    }
    public void PositionW(Integer position){
       row_index=-1;
       row_index=position;


    }

    public static class Viewholder extends RecyclerView.ViewHolder {

      MaterialCardView pdfscard;
      ShapeableImageView pdfimage;
      TextView pdftitulo;

        // int row_index=-1;


        public Viewholder(@NonNull final View itemView) {
            super(itemView);
           pdfscard = itemView.findViewById(R.id.pdfsCard);
           pdfimage=itemView.findViewById(R.id.pdfimage);
           pdftitulo=itemView.findViewById(R.id.pdftitulo);
           String pdfstate=  Constants.getSP(itemView.getContext()).getPDFPREFERENCE();
           if(pdfstate.equals(pdftitulo.getText().toString())){

               pdfscard.setStrokeWidth(10);
               pdfscard.setStrokeColor(itemView.getResources().getColor(R.color.colorNegrobrillante));
               }else {
                   pdfscard.setStrokeWidth(1);
                   pdfscard.setStrokeColor(itemView.getResources().getColor(R.color.colorGrisoscuro));

               }
           





        }

    }

}
