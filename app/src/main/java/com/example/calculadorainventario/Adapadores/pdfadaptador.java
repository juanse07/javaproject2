package com.example.calculadorainventario.Adapadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.calculadorainventario.Constructores.PdfChooseContructor;
import com.example.calculadorainventario.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class pdfadaptador extends RecyclerView.Adapter<pdfadaptador.Viewholder> {
    ArrayList<Integer> myImageList;

   public pdfadaptador(ArrayList<Integer>myImageList){
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
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Integer currentimage=myImageList.get(position);
        holder.pdfimage.setImageResource(currentimage);


    }


    @Override
    public int getItemCount() {
        return myImageList.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {

      MaterialCardView pdfscard;
      ShapeableImageView pdfimage;

        // int row_index=-1;


        public Viewholder(@NonNull final View itemView) {
            super(itemView);
           pdfscard = itemView.findViewById(R.id.pdfsCard);
           pdfimage=itemView.findViewById(R.id.pdfimage);



        }

    }
}
