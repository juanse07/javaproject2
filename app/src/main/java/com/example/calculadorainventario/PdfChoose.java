package com.example.calculadorainventario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.calculadorainventario.Adapadores.BuscarAdaptador;
import com.example.calculadorainventario.Adapadores.pdfadaptador;
import com.example.calculadorainventario.Constructores.PdfChooseContructor;

import java.util.ArrayList;

public class PdfChoose extends AppCompatActivity {
    RecyclerView Recpdf;
    RecyclerView.Adapter pdfAdapter;
    PdfChooseContructor pdfChooseContructor;
    ArrayList<PdfChooseContructor> myImageList;
    ArrayList<Integer>myImageList2;
    RecyclerView.LayoutManager Lmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_choose);

        Recpdf = findViewById(R.id.Recpdf);


            myImageList2=new ArrayList<>();
//        pdfChooseContructor.setImage(R.drawable.pdfstructured);
        myImageList2.add(R.drawable.pdfstructured);
        myImageList2.add(R.drawable.simplepdfformat);



        Recpdf.setHasFixedSize(true);

        Lmanager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);

        pdfAdapter=new pdfadaptador(myImageList2);
        Recpdf.setLayoutManager(Lmanager);
        Recpdf.setAdapter(pdfAdapter);
// later...
//        myImageView.setImageResource(myImageList.get(i));
    }
}