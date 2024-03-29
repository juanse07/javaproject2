package com.example.calculadorainventario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.calculadorainventario.Adapadores.BuscarAdaptador;
import com.example.calculadorainventario.Adapadores.pdfadaptador;
import com.example.calculadorainventario.Constructores.PdfChooseContructor;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class PdfChoose extends AppCompatActivity {
    RecyclerView Recpdf;
    RecyclerView.Adapter pdfAdapter;
    pdfadaptador pdfadaptador;
    PdfChooseContructor pdfChooseContructor;
    ArrayList<PdfChooseContructor> myImageList;
    pdfadaptador pdfadap;
    ArrayList<String>titulos;
    ArrayList<Integer>myImageList2;
    RecyclerView.LayoutManager Lmanager;
    pdfadaptador.Viewholder viewholder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_choose);

        Recpdf = findViewById(R.id.Recpdf);

//        pdfChooseContructor=new PdfChooseContructor();
//
//        pdfChooseContructor.setImage(R.drawable.pdfstructured);
//        pdfChooseContructor.setImage(R.drawable.simplepdfformat);
//        pdfChooseContructor.setTittle("PDFSTRUCTURED");
//        pdfChooseContructor.setTittle("");
        String pdfopcion = Constants.getSP(PdfChoose.this).getPDFPREFERENCE();

        myImageList = new ArrayList<>();

        myImageList.add(new PdfChooseContructor(R.drawable.pdfstructured, "Classic Robotic"));
        myImageList.add(new PdfChooseContructor(R.drawable.simplepdfformat, "Classic Ligero"));
        myImageList.add(new PdfChooseContructor(R.drawable.blueinvoice,"Refreshing Blue"));

//        pdfChooseContructor.setImage(R.drawable.pdfstructured);


        Recpdf.setHasFixedSize(true);

        Lmanager = new GridLayoutManager(this, 2);

        pdfAdapter = new pdfadaptador(myImageList);
        Recpdf.setLayoutManager(Lmanager);
        Recpdf.setAdapter(pdfAdapter);

    }

// later...
//        myImageView.setImageResource(myImageList.get(i));
    }
