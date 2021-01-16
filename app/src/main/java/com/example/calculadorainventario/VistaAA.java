package com.example.calculadorainventario;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class VistaAA extends AppCompatActivity {
    TextView gdate, gprecio, gvalor, gmedida, gcantidad, gnombre, gproducto, ghora,gopcion;

    GridView gridcueros2;
    CardView card_view;


    private File pdfFile;
    private Paragraph paragraph;
    private List lista;
    Document mDoc = new Document(PageSize.LETTER);
    String pattern = "EEEEE MMMMM yyyy HH:mm:ss.SSSZ";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("en", "US"));
    String mFilename = simpleDateFormat.format(System.currentTimeMillis());


    String mFilepath = Environment.getExternalStorageDirectory() + "/" + mFilename + ".pdf";

    //private Font ftittle = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
   // private Font ftext = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
    //private Font fother = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD, BaseColor.BLUE);

    BottomNavigationView navigationView;
    FloatingActionButton btredondo4;
    RecyclerView recyclerArray;
    RecyclerView.Adapter mAdapter;
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 111;
    private static final int REQUEST_CODE_ASK_PERMISSIONS_2 = 112;
    ImageButton btnshare;
    Calendar mcalendar;
    Intent shareintent;
    DatabaseReference myrootDbaseref5;
    FirebaseStorage mystorage;
    FirebaseAuth mAuth;
    Uri pdfUri;
    //FirebaseDatabase mydatabase;
    //String pattern = "EEEEE MMMMM yyyy HH:mm:ss.SSSZ";
    //SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("fr", "FR"));
    //String mFilename = simpleDateFormat.format(System.currentTimeMillis());

   

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista);


        Date d = new Date();

        gdate = (TextView) findViewById(R.id.gdate);
        gprecio = (TextView) findViewById(R.id.gprecio);
        gmedida = (TextView) findViewById(R.id.gmedida);
        gopcion = (TextView) findViewById(R.id.gopcion);
        gcantidad = (TextView) findViewById(R.id.gcantidad);
        gvalor = (TextView) findViewById(R.id.gvalor);
        gnombre = (TextView) findViewById(R.id.gnombre);
        gproducto = (TextView) findViewById(R.id.gproducto);
        btredondo4 = (FloatingActionButton) findViewById(R.id.btredondo4);
        ghora = (TextView) findViewById(R.id.ghora);
        card_view = (CardView) findViewById(R.id.card_view);
        recyclerArray = (RecyclerView) findViewById(R.id.recyclerArray);

        lista = new List();
        recyclerArray.setHasFixedSize(true);
        recyclerArray.setLayoutManager(new GridLayoutManager(this,6));

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat fecc = new SimpleDateFormat("dd/MMM/yyyy");
        String fechacComplString = fecc.format(d);
        gdate.setText(fechacComplString);
        SimpleDateFormat hora1 = new SimpleDateFormat("HH:mm:ss");
        String hora2 = hora1.format(calendar.getTime());
        ghora.setText(hora2);
        myrootDbaseref5 = FirebaseDatabase.getInstance().getReference();
        mAuth=FirebaseAuth.getInstance();
        mystorage= FirebaseStorage.getInstance();



        btredondo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarbdventas();
                showpdf();

                //openPdfFile(pdfFile);
               // if (Build.VERSION.SDK_INT >= 24) {
                   
                 //   try {

                        // For API's > 24, runtime exception occurs when a URI is exposed BEYOND this particular app that you are writing (AKA when user attempts to open in device/emulator

                   //     Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");

                     //   m.invoke(null);

                    //} catch (Exception e) {

                      //  e.printStackTrace();

                    //}

                //}

               // Intent target = new Intent(Intent.ACTION_VIEW);

               // target.setDataAndType(Uri.fromFile(pdfFile), "application/pdf");

               // target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);


               // Intent intent = Intent.createChooser(target, "Open File");

               // try {

                 //   startActivity(intent);

                //} catch (ActivityNotFoundException e) {

                    // Instruct the user to install a PDF reader here, or something

                //}
            }
        });

/////pasar a livedata
        SharedPreferences result = getSharedPreferences("datosguardados", Context.MODE_PRIVATE);
        String nombredos = result.getString("value", "No se encontraron Datos");
        String producto2 = result.getString("producto2", "No se encontró producto");
        String Opcion2=result.getString("opcion","No se encontró producto");
        gnombre.setText(nombredos);
        gproducto.setText(producto2);
        gopcion.setText(Opcion2);
        gopcion.setTextColor(getResources().getColor(R.color.mdtp_red));
        ArrayList<Double> listacuero2 = (ArrayList<Double>) getIntent().getSerializableExtra("WLTP_list");
        /////livedata
        //listacuero2.add(1.0);
        //ArrayAdapter<Double> adapter = new ArrayAdapter<Double>(VistaAA.this, android.R.layout.simple_selectable_list_item, listacuero2);
        //gridcueros2.setAdapter(adapter);
        //mAdapter = new arrayadapterclass(listacuero2, this);
        recyclerArray.setAdapter(mAdapter);



       

        //Trae datos desde mainactivity
        String precio2 = getIntent().getStringExtra("precio2");
        String unidades2 = getIntent().getStringExtra("uds2");
        String val2 = getIntent().getStringExtra("valortotal");
        String suma2 = getIntent().getStringExtra("sumatotal2");
        String nom2 = getIntent().getStringExtra("nombredos");
        String pro2 = getIntent().getStringExtra("prod2");
        gprecio.setText(precio2);
        gmedida.setText(suma2);
        gcantidad.setText(unidades2);
        gvalor.setText(val2);

        ///////////////////////////
        /////aqui pdf



        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, REQUEST_CODE_ASK_PERMISSIONS);

            } else {
                savepdf();

            }
        } else {
            savepdf();

        }


    }

    private void savepdf() {
        BaseFont baseFont = null;
        try {
            baseFont = BaseFont.createFont("res/font/montserratregular.ttf", "UTF-8", BaseFont.EMBEDDED);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        BaseColor orangedark = new BaseColor(255, 79, 0);
        Font regularHead = new Font(baseFont, 15, Font.BOLD, BaseColor.WHITE);
        Font regularReport = new Font(baseFont, 25, Font.BOLD, new BaseColor(254, 114, 0));
        Font regularName = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.BLACK);
        Font regularAddress = new Font(Font.FontFamily.HELVETICA, 15, Font.NORMAL, BaseColor.BLACK);
        Font regularSub = new Font(Font.FontFamily.COURIER, 6,Font.ITALIC,BaseColor.RED);
        Font regularTotal = new Font(baseFont, 14, Font.ITALIC, BaseColor.BLACK);
        Font regularTotalBold = new Font(baseFont, 16, Font.BOLD, BaseColor.BLACK);
        //Font footerN = new Font(baseFont, 15,Font.BOLD,printAccent);
        Font footerE = new Font(baseFont, 12, Font.NORMAL, BaseColor.BLACK);
        ArrayList<Double> listacuero3 = (ArrayList<Double>) getIntent().getSerializableExtra("WLTP_list");
        //String pattern = "EEEEE MMMMM yyyy HH:mm:ss.SSSZ";
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("fr", "FR"));
        //String mFilename = simpleDateFormat.format(System.currentTimeMillis());


        //String mFilepath = Environment.getExternalStorageDirectory() + "/" + mFilename + ".pdf";
        pdfFile = new File(mFilepath);
        try {
            PdfWriter.getInstance(mDoc, new FileOutputStream(mFilepath));
            mDoc.open();
            String nombreventas2 = gnombre.getText().toString();
            String fechaventas2 = gdate.getText().toString();
            String horaventas2 = ghora.getText().toString();
            String productoventas2 = gproducto.getText().toString();
            String colorventas2 = ghora.getText().toString();
            String unidadesventas2 = gcantidad.getText().toString();
            String precioventas2 = gprecio.getText().toString();
            String medidaventas2 = gmedida.getText().toString();
            String valorventas2 = gvalor.getText().toString();


            Chunk nombre1 = new Chunk("Nombre:", regularName);
            Chunk vnombre = new Chunk(nombreventas2, regularAddress);
            Paragraph pnombre=new Paragraph();
            pnombre.add(nombre1);
            pnombre.add(" "+vnombre);

            Paragraph pcompany = new Paragraph("PyMESoft®", regularReport);
            pcompany.setAlignment(Element.ALIGN_RIGHT);


            Chunk fecha1 = new Chunk(fechaventas2, regularTotal);
            Chunk vfecha1 = new Chunk(horaventas2, regularTotal);
            Paragraph pfecha=new Paragraph();
            pfecha.add(fecha1);
            pfecha.add(" "+vfecha1);
            pfecha.setSpacingAfter(20);

            Chunk producto1 = new Chunk("Producto:", regularName);
            Chunk vproducto = new Chunk(productoventas2, regularAddress);
            Paragraph pproducto=new Paragraph();
            pproducto.add(producto1);
            pproducto.add(" "+vproducto);

            Chunk precio1 = new Chunk("Precio:", regularName);
            Chunk vprecio = new Chunk(precioventas2, regularAddress);
            Paragraph pprecio=new Paragraph();
           pprecio.add(precio1);
            pprecio.add(" "+vprecio);

            Chunk medida1 = new Chunk("Medida:", regularName);
            Chunk vpmedida = new Chunk(medidaventas2, regularAddress);
            Paragraph pmedida=new Paragraph();
            pmedida.add(medida1);
            pmedida.add(" "+vpmedida);

            Chunk valor1 = new Chunk("Valor:", regularName);
            Chunk vvalor = new Chunk(valorventas2, regularAddress);
            Paragraph pvalor=new Paragraph();
            pvalor.add(valor1);
            pvalor  .add(" "+vvalor);
            pvalor.setSpacingAfter(40);

            Chunk unidades1 = new Chunk("Unidades:", regularName);
            Chunk vunidades = new Chunk(unidadesventas2, regularAddress);
            Paragraph punidades=new Paragraph();
            punidades.add(unidades1);
            punidades.add(" "+vunidades);


            PdfPTable table1=new PdfPTable(4);
            table1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.setWidthPercentage(70);
            PdfPCell cellnombre=new PdfPCell();
            PdfPCell cellnombre2=new PdfPCell();
            PdfPCell cellfecha=new PdfPCell();
            PdfPCell cellhora=new PdfPCell();
            PdfPCell cellproducto=new PdfPCell();
            PdfPCell cellproducto2=new PdfPCell();
            PdfPCell cellunidades=new PdfPCell();
            PdfPCell cellunidades2=new PdfPCell();
            PdfPCell cellmedida=new PdfPCell();
            PdfPCell cellmedida2=new PdfPCell();
            PdfPCell cellprecio=new PdfPCell();
            PdfPCell cellprecio2=new PdfPCell();
            PdfPCell cellvalor=new PdfPCell();
            PdfPCell cellvalor2=new PdfPCell();
           cellnombre.addElement(nombre1);
            cellnombre2.addElement(vnombre);
           cellfecha.addElement(fecha1);
           cellhora.addElement(vfecha1);
           cellproducto.addElement(producto1);
            cellproducto2.addElement(vproducto);
           cellunidades.addElement(unidades1);
            cellunidades2.addElement(vunidades);
           cellmedida.addElement(medida1);
            cellmedida2.addElement(vpmedida);
            cellvalor.addElement(valor1);

           cellvalor2.addElement(vvalor);
           cellprecio.addElement(precio1);
            cellprecio2.addElement(vprecio);

            cellnombre.setBorderColor(BaseColor.WHITE);
            cellnombre2.setBorderColor(BaseColor.WHITE);

            cellfecha.setBorderColor(BaseColor.WHITE);
            cellhora.setBorderColor(BaseColor.WHITE);
            cellproducto.setBorderColor(BaseColor.WHITE);
            cellproducto2.setBorderColor(BaseColor.WHITE);
            cellunidades.setBorderColor(BaseColor.WHITE);
            cellunidades2.setBorderColor(BaseColor.WHITE);
            cellmedida.setBorderColor(BaseColor.WHITE);
            cellmedida2.setBorderColor(BaseColor.WHITE);
            cellvalor.setBorderColor(BaseColor.WHITE);
            cellvalor2.setBorderColor(BaseColor.WHITE);
            cellprecio.setBorderColor(BaseColor.WHITE);
            cellprecio2.setBorderColor(BaseColor.WHITE);


            cellfecha.setFixedHeight(30);
            cellhora.setFixedHeight(30);
            cellfecha.setColspan(2);
            cellhora.setColspan(2);

            table1.addCell(cellfecha);
            table1.addCell(cellhora);
            table1.addCell(cellnombre);
            table1.addCell(cellnombre2);
            table1.addCell(cellproducto);
            table1.addCell(cellproducto2);
            table1.addCell(cellmedida);
            table1.addCell(cellmedida2);
            table1.addCell(cellunidades);
            table1.addCell(cellunidades2);
            table1.addCell(cellprecio);
            table1.addCell(cellprecio2);
            table1.addCell(cellvalor);
            table1.addCell(cellvalor2);

            mDoc.add(pcompany);
            //mDoc.add(pfecha);
            //mDoc.add(pnombre);
            //mDoc.add(pproducto);
            //mDoc.add(punidades);
            //mDoc.add(pmedida);
            //mDoc.add(pprecio);
            //mDoc.add(pvalor);

            table1.setSpacingAfter(20);
            mDoc.add(table1);


            PdfPTable pdfPtable = new PdfPTable(1);
            pdfPtable.setWidthPercentage(100);
            pdfPtable.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setFixedHeight(2);
            cell.setBorderColor(BaseColor.LIGHT_GRAY);
            pdfPtable.addCell(cell);
            pdfPtable.setSpacingAfter(20);

            mDoc.add(pdfPtable);

           // Toast.makeText(VistaAA.this, mFilename + "guardado" + mFilepath, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(VistaAA.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        try {
            PdfPTable Atable=new PdfPTable(10);
            Atable.setHorizontalAlignment(Element.ALIGN_LEFT);
            Atable.setWidthPercentage(100);

            List list = new List();

        for(int aw = 0; aw < listacuero3.size();aw++) {
            Paragraph p=new Paragraph();

            Paragraph q1=new Paragraph(String.valueOf(aw+1),regularSub);
            Paragraph q2=new Paragraph(String.valueOf(listacuero3.get(aw)));
            Chunk gumble= new Chunk(new VerticalPositionMark());

            p.add(q1);
            p.add(gumble);
            p.add(q2);

            PdfPCell cell=new PdfPCell(p);
            cell.setPaddingBottom(8);
            cell.setPaddingTop(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorderColor(BaseColor.LIGHT_GRAY);
            cell.setFixedHeight(30);

            Atable.addCell(cell);

            }

            Atable.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
            Atable.completeRow();




        mDoc.add(Atable);
        mDoc.close();
       // Toast.makeText(VistaAA.this, mFilename + "guardado" + mFilepath, Toast.LENGTH_SHORT).show();


        }catch (Exception e) {
         Toast.makeText(VistaAA.this, "error", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    savepdf();


                } else {
                    Toast.makeText(VistaAA.this, "Permiso negado", Toast.LENGTH_SHORT).show();
                }

            }
            case REQUEST_CODE_ASK_PERMISSIONS_2:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                   uploadpdf(pdfUri);


                } else {
                    Toast.makeText(VistaAA.this, "Permiso negado", Toast.LENGTH_SHORT).show();
                }

            }
        }

    }

   



    //private void openPdfFile(File pdfFile) {


    public void showpdf() {
        Intent intent = new Intent(VistaAA.this, pdfviewer.class);
        intent.putExtra("path", pdfFile.getAbsolutePath());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    //////EMPIEZA A CONSTRUIR PDF
  

public void uploadpdf(Uri pdfUri) {


    pdfUri = Uri.fromFile(new File(pdfFile.getAbsolutePath()));
    StorageReference storageReference = mystorage.getReference();


    storageReference.child("pdfcloud").child(mFilename).putFile(pdfUri)
            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // String url = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
             taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                 @Override
                 public void onSuccess(Uri uri) {
                     Uri downloadurl=uri;

             String url2=String.valueOf(downloadurl);


                            String nombreventas = gnombre.getText().toString();
                            String fechaventas = gdate.getText().toString();
                            String horaventas = ghora.getText().toString();
                            String productoventas = gproducto.getText().toString();
                            String colorventas = ghora.getText().toString();
                            String unidadesventas = gcantidad.getText().toString();
                            String precioventas = gprecio.getText().toString();
                            String medidaventas = gmedida.getText().toString();
                            String valorventas = gvalor.getText().toString();
                            String estado=gopcion.getText().toString();
                             String id= mAuth.getCurrentUser().getUid();


                            Map<String, Object> datosventa = new HashMap<>();
                            datosventa.put("Fecha", fechaventas);
                            datosventa.put("Hora", horaventas);
                            datosventa.put("Cliente", nombreventas);
                            datosventa.put("Producto", productoventas);
                            datosventa.put("Medida", medidaventas);
                            datosventa.put("Precio", precioventas);
                            datosventa.put("Unidades", unidadesventas);
                            datosventa.put("Valor", valorventas);
                            datosventa.put("Estado",estado);
                            datosventa.put("pdfurl", url2);

                            //datosventa.put("Id_usuario",id);
                            myrootDbaseref5.child("VENTAS").child(id).push().setValue(datosventa);
                            Toast.makeText(VistaAA.this, "Se ha registrado la Venta", Toast.LENGTH_SHORT).show();
                 }
             });
                            //myrootDbaseref5.child("VENTAS").push().setValue(datosventa);
                        }
                    });


                    // Instruct the user to install a PDF reader here, or something


                }


                public void guardarbdventas() {
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                                PackageManager.PERMISSION_DENIED) {
                            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                            requestPermissions(permissions, REQUEST_CODE_ASK_PERMISSIONS_2);

                        } else {
                            uploadpdf(pdfUri);

                        }


                    } else {
                        uploadpdf(pdfUri);
                    }


                    Toast.makeText(VistaAA.this, "Se ha registrado la Venta", Toast.LENGTH_SHORT).show();
                    //Intent i = new Intent(VistaAA.this, home1.class);
                    //startActivity(i);

                    ///////////////////////
                }



}


