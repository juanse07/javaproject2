package com.example.calculadorainventario;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculadorainventario.Adapadores.AdaptadorProductoGuardado;
import com.example.calculadorainventario.Constructores.arrayconstructor;
import com.github.barteksc.pdfviewer.PDFView;
//import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.itextpdf.text.Image;


public class pdfviewer extends AppCompatActivity  implements Interface2 {
    private PDFView pdfView;
    ImageView back1;
    private File file;

    TextView title6;
    BaseFont baseFont=null;
    Uri pdfUri;
    PdfPTable tableFooter;
    List<Note>Listadobles2;
    ArrayList<arrayconstructor>Listadobles3;
    ArrayList<Double> ListaCuero;
    ArrayList<Double> listacuero3;
    AdaptadorProductoGuardado adpt1=new AdaptadorProductoGuardado();

    StorageReference storageReference;


    PdfWriter writer1, writer2;

    FirebaseDatabase mydatabase = FirebaseDatabase.getInstance();

    //ShareViewModel2 shareViewModel2;
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    //String fechaventas2;
    Document mDoc = new Document(PageSize.LETTER,36,36,53,56);

   String horaventas2;
    String productoventas2;
    String unidadesventas2;
    String precioventas2;
    String medidaventas2;
    String valorventas2;
    String fechaventas2;
    String estadoventas2;
    String horareal2;
    String diaspago,Fecha2;
    DatabaseReference myrootDbaseref5;
    FirebaseStorage mystorage;
    FirebaseAuth mAuth;
    NoteViewModel noteViewModel;
    ArrayList<Note>noteArralist=new ArrayList<>();
    ArrayList<String>List1;
    byte[] outputstream2;
    //Button btactualizarpdf;
   String nombreventas2;
    String pattern = "EEEEE MMMMM yyyy HH:mm:ss.SSSZ";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("en", "US"));
    String mFilename = simpleDateFormat.format(System.currentTimeMillis());



    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat hora1 = new SimpleDateFormat("HH:mm:ss");
    String hora2 = hora1.format(calendar.getTime());
 String mFilepath = Environment.getExternalStorageDirectory() +   File.separator+ "PyMESoft"+
  File.separator+"invoices"+File.separator +mFilename.toString().replaceAll(":",".");
 File filepath2;
    File file2;

   // byte[] outputStream = new ByteArrayOutputStream();
    byte[] outputStream2;
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 111;
    private static final int REQUEST_CODE_ASK_PERMISSIONS_2 = 112;
    // InputStream in = new ByteArrayInputStream(outputStream2.toByteArray());

    // Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewer2);
        pdfView = (PDFView) findViewById(R.id.pdfView);
        title6=findViewById(R.id.title6);

        back1=findViewById(R.id.back1);

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           
                onBackPressed();
            }
        });
//        noteViewModel=new ViewModelProvider(this).get(NoteViewModel.class);
//        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
//            @Override
//            public void onChanged(List<Note> notes) {
//                Listadobles2=notes;
//            }
//        });

        listacuero3= (ArrayList<Double>) getIntent().getSerializableExtra("WLTP_list");

       // gnombre2.setText(getIntent().getExtras().getString("Nombre1"));
        nombreventas2=getIntent().getExtras().getString("Nombre1");


         fechaventas2= getIntent().getExtras().getString("Fecha1").trim();

         horaventas2=getIntent().getExtras().getString("Hora1");

        productoventas2=getIntent().getExtras().getString("Producto1");

        List1= (ArrayList<String>) getIntent().getSerializableExtra("listapdf1");





        unidadesventas2=getIntent().getExtras().getString("Unidades1");

       precioventas2=getIntent().getExtras().getString("Precio1");

       medidaventas2=getIntent().getExtras().getString("Medida1");

      valorventas2=getIntent().getExtras().getString("Total1");

        estadoventas2=getIntent().getExtras().getString("Estado1");
        Fecha2=getIntent().getExtras().getString("Fecha2").trim();
        //outputStream2=getIntent().getExtras().getByteArray("wpa");


        horareal2=hora2;
        diaspago=Constants.getSP(this).getDIAS();

        myrootDbaseref5 = FirebaseDatabase.getInstance().getReference();

        mystorage= FirebaseStorage.getInstance();
        storageReference=mystorage.getReference();

        String first = "PyMESoft";
        String next = "<font color='#1D2E4A'>FActuras</font>";
        title6.setText(Html.fromHtml(first + next));



        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED) {
                String[] permissions = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, REQUEST_CODE_ASK_PERMISSIONS);


            } else {
                try {

                    savepdf();
                } catch (DocumentException e) {
                    Toast.makeText(this, "no inicia", Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(this, "Fallo permiso", Toast.LENGTH_SHORT).show();


//                try {
//                    savepdf();
//                } catch (DocumentException e) {
//                    e.printStackTrace();
//                    Toast.makeText(this, "Fallo a1", Toast.LENGTH_SHORT).show();
//                }

            }
        } else {

//            outputStream = new ByteArrayOutputStream();
//
//            try {
//                savepdf();
//            } catch (DocumentException e) {
//                e.printStackTrace();
//                Toast.makeText(this, "Fallo a2", Toast.LENGTH_SHORT).show();
//            }



        }

        pdfView.fromBytes(outputStream.toByteArray())
                //.enableSwipe(true)
                //.swipeHorizontal(false)
                //.enableDoubletap(true)
                //.enableAntialiasing(true)
                //.fitEachPage(true)
                //.spacing(0)
                //.autoSpacing(true)

                //.pageFling(true)
                //.pageSnap(true)
                //.fitEachPage(true)
                .swipeHorizontal(true)
                //.pageSnap(true)
                //.autoSpacing(true)
               // .pageFling(true)



                .load();















        //Intent intent=getIntent();






////////////////////////////////////

    }


    public void compartir(View view) throws IOException {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED) {
                String[] permissions = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, REQUEST_CODE_ASK_PERMISSIONS);


            } else {

                //new PdfSave().execute();


            }
        } else {

           // outputStream = new ByteArrayOutputStream();




            //new PdfSave().execute();


        }

       // OutputStream outputStream3 = new FileOutputStream (mFilepath);
       // OutputStream outputStream4=outputStream2.
       // outputStream2.writeTo(outputStream3);



       if (Build.VERSION.SDK_INT >= 24) {

            try {

                //For API's > 24, runtime exception occurs when a URI is exposed BEYOND this particular app that you are writing (AKA when user attempts to open in device/emulator

              Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");

                m.invoke(null);

            } catch (Exception e) {

                e.printStackTrace();

            }

        }


        File file=new File(file2.getAbsolutePath());
        Uri pdfUri = Uri.fromFile(file);
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        //shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        shareIntent.putExtra(Intent.EXTRA_STREAM, pdfUri);
        shareIntent.setType("application/pdf");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);


        try {

            startActivity(Intent.createChooser(shareIntent, "share"));
        } catch (ActivityNotFoundException e) {

            // Instruct the user to install a PDF reader here, or something

            //}


        }
    }

    public void home1(View view) {
        guardarbdventas();
        finish();

        Intent i = new Intent(pdfviewer.this, home1.class);
        startActivity(i);
        noteViewModel=new ViewModelProvider(this).get(NoteViewModel.class);
        noteViewModel.DeleteAll();

    }

    public void wzp(View view) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED) {
                String[] permissions = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, REQUEST_CODE_ASK_PERMISSIONS);


            } else {

                //new PdfSave().execute();



            }
        } else {

            // outputStream = new ByteArrayOutputStream();




            //new PdfSave().execute();


        }


        if (Build.VERSION.SDK_INT >= 24) {

            try {

                //For API's > 24, runtime exception occurs when a URI is exposed BEYOND this particular app that you are writing (AKA when user attempts to open in device/emulator

                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");

                m.invoke(null);

            } catch (Exception e) {

                e.printStackTrace();

            }

        }
        File file=new File(file2.getAbsolutePath());

        Uri pdfUri = Uri.fromFile(file);
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        //shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        shareIntent.putExtra(Intent.EXTRA_STREAM, pdfUri);
        shareIntent.setType("application/pdf");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        shareIntent.setPackage("com.whatsapp");


        try {

            startActivity(Intent.createChooser(shareIntent, "share"));
        } catch (ActivityNotFoundException e) {

            // Instruct the user to install a PDF reader here, or something

            //}


        }

    }

    @Override
    public void OnaddClick(double getinput) {

    }

    @Override
    public void outputClick(ArrayList<Double> Lista5) {
        //ListaCuero=Lista5;

    }

    @Override
    public void streamclick(ByteArrayOutputStream outputStream) {
        ///outputStream=outputStream2;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    //new PdfSave().execute();
                  try {
                       savepdf();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }


                    Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(this, "Permiso no apto para mayores", Toast.LENGTH_SHORT).show();
                }


            }
            case REQUEST_CODE_ASK_PERMISSIONS_2:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    uploadpdf(pdfUri);


                } else {
                    Toast.makeText(pdfviewer.this, "Permiso negado para adultos", Toast.LENGTH_SHORT).show();
                }

            }
        }






    }

    public void uploadpdf(Uri pdfUri) {

        mAuth=FirebaseAuth.getInstance();
        String id=mAuth.getCurrentUser().getUid();



       // pdfUri = Uri.fromFile(new File(pdfFile.getAbsolutePath()))
        pdfUri = Uri.fromFile(file2);




        storageReference.child("pdfcloud2").child(mFilename).child(id).putFile(pdfUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                        // String url = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                        taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Uri downloadurl=uri;

                                String url2=String.valueOf(downloadurl);
                                mAuth=FirebaseAuth.getInstance();
                                String id=mAuth.getCurrentUser().getUid();


                                String nombreventas = nombreventas2;
                                String fechaventas = fechaventas2;
                                String horaventas = horaventas2;
                                String productoventas = productoventas2;
                                //String colorventas = ghora.getText().toString();
                                String unidadesventas = unidadesventas2;
                                String precioventas = precioventas2;
                                String medidaventas = medidaventas2;
                                String valorventas = valorventas2;
                                String estado=estadoventas2;


                                DatabaseReference newref=  myrootDbaseref5.child("VENTAS").child(id).push();




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
                                datosventa.put("Key",newref.getKey());
                                datosventa.put("Fechaparapago",Fecha2);
                                datosventa.put("Dias_plazo",diaspago);


                                datosventa.put("Id_usuario",id);
                                newref.setValue(datosventa);
                                Toast.makeText(pdfviewer.this   , "Se ha registrado la Venta", Toast.LENGTH_SHORT).show();
                            }
                        });

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


        Toast.makeText(pdfviewer.this, "Se ha registrado la Venta", Toast.LENGTH_SHORT).show();
        //Intent i = new Intent(VistaAA.this, home1.class);
        //startActivity(i);

        ///////////////////////
    }
    private void savepdf() throws DocumentException {
//        BaseFont baseFont=null;
//        try {
//
//            baseFont = BaseFont.createFont("res/font/montserratregular.ttf", "UTF-8", BaseFont.EMBEDDED);
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "Fallo d1", Toast.LENGTH_SHORT).show();
//        } catch (DocumentException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "Fallo d2", Toast.LENGTH_SHORT).show();
//        }
        BaseColor orangedark = new BaseColor(255, 79, 0);
        Font regularHead = new Font(baseFont, 15, Font.BOLD, BaseColor.WHITE);
        Font regularReport = new Font(baseFont, 12, Font.BOLD, new BaseColor(254, 114, 0));
        Font regularReportA = new Font(baseFont, 25, Font.BOLD, new BaseColor(114,133,165));
        Font regularReport2 = new Font(baseFont, 16, Font.BOLD, BaseColor.BLACK);
        Font regularName = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.BLACK);
        Font regularAddress = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        Font regularSub = new Font(Font.FontFamily.COURIER, 6, Font.ITALIC, BaseColor.RED);
        Font regularTotal = new Font(Font.FontFamily.HELVETICA, 14, Font.ITALIC, BaseColor.BLACK);
        Font regularTotal2 = new Font(Font.FontFamily.HELVETICA, 11, Font.ITALIC, BaseColor.BLACK);
        Font regularTotalBold = new Font(baseFont, 8, Font.BOLD, new BaseColor(128,128,128));
        Font regularSub2 = new Font(Font.FontFamily.HELVETICA, 7, Font.NORMAL, BaseColor.GRAY);
        //Font footerN = new Font(baseFont, 15,Font.BOLD,printAccent);

        Font footerE = new Font(baseFont, 8, Font.NORMAL, BaseColor.BLACK);
        Image image;





        file = new File(mFilepath);
        if (!file.exists()) {
            file.mkdirs();

        }
        filepath2= new File(file.getAbsolutePath());
        filepath2.mkdir();
         file2=new File(filepath2,"inv-"+nombreventas2+".pdf");


        try {

           // PdfWriter writer1, writer2;

           writer2= PdfWriter.getInstance(mDoc, new FileOutputStream(file2));
            //PdfWriter.getInstance(mDoc, new FileOutputStream(mFilepath));
           writer1= PdfWriter.getInstance(mDoc, outputStream);

            mDoc.open();



            tableFooter = new PdfPTable(1);
            tableFooter.setTotalWidth(523);
            Paragraph p1=new Paragraph();
            Phrase q1= new Phrase("PyMESoft®", regularReport) ;
            Phrase q2 = new Phrase("www.pymesoft.com",footerE);
            Phrase q3= new Phrase("POLYSIN   industriaquimica@polysin.com || 3103214617 - 3045250997 || cra 12 #2-61",footerE);
            Phrase q4=new Phrase("Powered by",footerE);




            Chunk gumble = new Chunk(new VerticalPositionMark());
            Chunk gumble2 = new Chunk(new VerticalPositionMark());
            Chunk gumble3 = new Chunk(new VerticalPositionMark());

            p1.add(q2);
            p1.add(gumble);
            p1.add(q3);
            p1.add(gumble2);
            p1.add(q4);
            p1.add(gumble3);
            p1.add(q1);

            PdfPCell footerName = new PdfPCell();
            footerName.addElement(p1);
            footerName.setHorizontalAlignment(Element.ALIGN_RIGHT);

           // PdfPCell footerEmail = new PdfPCell();

          //  PdfPCell footerEmpty = new PdfPCell(new Phrase(""));
            footerName.setBorder(Rectangle.NO_BORDER);
           // footerEmpty.setBorder(Rectangle.NO_BORDER);
           // footerEmail.setBorder(Rectangle.NO_BORDER);





            PdfPCell preBorderBlue = new PdfPCell();
            preBorderBlue.addElement(p1);
            preBorderBlue.setMinimumHeight(5f);
            preBorderBlue.setHorizontalAlignment(Element.ALIGN_CENTER);
            preBorderBlue.setUseVariableBorders(true);
            preBorderBlue.setBorder(Rectangle.TOP);
            preBorderBlue.setBorderColorTop((new BaseColor(114,133,165)));
            preBorderBlue.setBorderWidthTop(3);



            tableFooter.setWidthPercentage(100);
            tableFooter.addCell(preBorderBlue);
            //tableFooter.addCell(footerEmail);
           // tableFooter.addCell(footerName);






            HeaderFooter event = new HeaderFooter(tableFooter);
            writer1.setPageEvent(event);
         writer2.setPageEvent(event);






           // mDoc.setMargins(36, 36, 55, 150);




            SharedPreferences logopreference2 = getSharedPreferences
                    ("logopref2", Context.MODE_PRIVATE);

            String radiotipo= logopreference2.getString("logocheck2","no hay datos");

            if (radiotipo.equals("Rectangular")&&estadoventas2.equals("Venta")){
                String imFile = "/storage/emulated/0/PyMESoft/Logotipo/logopng";
                image = Image.getInstance(imFile);
                image.scaleAbsolute(160f,70f);
                image.setAlignment(Image.LEFT);
                Paragraph factu=new Paragraph("Factura de Venta",regularReportA);
                factu.setAlignment(Element.ALIGN_RIGHT);
                Paragraph Plazo1=new Paragraph("Término de pago",regularTotalBold);
                Paragraph Plazo=new Paragraph(diaspago+" "+"días",regularTotalBold);
                Plazo.setAlignment(Element.ALIGN_RIGHT);
                PdfPTable pdfPtableimage = new PdfPTable(2);
                pdfPtableimage.setWidthPercentage(100);
                pdfPtableimage.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell imagecell = new PdfPCell();
                PdfPCell factucell = new PdfPCell();
                imagecell.addElement(image);
                imagecell.setBorderColor(new BaseColor(255,255,255));
                factucell.addElement(factu);
                factucell.setBorderColor(new BaseColor(255,255,255));
                factucell.addElement(Plazo1);
                factucell.addElement(Plazo);




                pdfPtableimage.addCell(imagecell);
                pdfPtableimage.addCell(factucell);

                pdfPtableimage.setSpacingAfter(25);
                mDoc.add(pdfPtableimage);
                System.setProperty("http.agent", "Chrome");
            }else if(radiotipo.equals("Cuadrado")&&estadoventas2.equals("Venta")) {
                String imFile = "/storage/emulated/0/PyMESoft/Logotipo/logopng";
                image = Image.getInstance(imFile);
                image.scaleAbsolute(100f,100f);
                image.setAlignment(Image.LEFT);
                Paragraph factu=new Paragraph("Factura de Venta",regularReportA);
                factu.setAlignment(Element.ALIGN_RIGHT);
                Paragraph Plazo1=new Paragraph("Término de pago",regularTotalBold);
                Plazo1.setAlignment(Element.ALIGN_RIGHT);
                Paragraph Plazo=new Paragraph(diaspago+" "+"días",regularTotalBold);
                Plazo.setAlignment(Element.ALIGN_RIGHT);
                PdfPTable pdfPtableimage = new PdfPTable(2);
                pdfPtableimage.setWidthPercentage(100);
                pdfPtableimage.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell imagecell = new PdfPCell();
                PdfPCell factucell = new PdfPCell();
                imagecell.addElement(image);
                imagecell.setBorderColor(new BaseColor(255,255,255));
                factucell.addElement(factu);
                factucell.setBorderColor(new BaseColor(255,255,255));
                factucell.addElement(Plazo1);
                factucell.addElement(Plazo);




                pdfPtableimage.addCell(imagecell);
                pdfPtableimage.addCell(factucell);

                pdfPtableimage.setSpacingAfter(25);
                mDoc.add(pdfPtableimage);
                System.setProperty("http.agent", "Chrome");
            }
            if (radiotipo.equals("Rectangular")&&estadoventas2.equals("Compra")){
                String imFile = "/storage/emulated/0/PyMESoft/Logotipo/logopng";
                image = Image.getInstance(imFile);
                image.scaleAbsolute(160f,70f);
                image.setAlignment(Image.LEFT);
                Paragraph factu=new Paragraph("Factura de Compra",regularReportA);
                Paragraph Plazo1=new Paragraph("Término de pago",regularTotalBold);
                Plazo1.setAlignment(Element.ALIGN_RIGHT);
                Paragraph Plazo=new Paragraph(diaspago+" "+"días",regularTotalBold);
                Plazo.setAlignment(Element.ALIGN_RIGHT);
                factu.setAlignment(Element.ALIGN_RIGHT);

                Plazo.setAlignment(Element.ALIGN_RIGHT);
                PdfPTable pdfPtableimage = new PdfPTable(2);
                pdfPtableimage.setWidthPercentage(100);
                pdfPtableimage.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell imagecell = new PdfPCell();
                PdfPCell factucell = new PdfPCell();
                imagecell.addElement(image);
                imagecell.setBorderColor(new BaseColor(255,255,255));
                factucell.addElement(factu);

                factucell.setBorderColor(new BaseColor(255,255,255));
                factucell.addElement(Plazo);




                pdfPtableimage.addCell(imagecell);
                pdfPtableimage.addCell(factucell);

                pdfPtableimage.setSpacingAfter(25);
                mDoc.add(pdfPtableimage);
                System.setProperty("http.agent", "Chrome");
            }else if(radiotipo.equals("Cuadrado")&&estadoventas2.equals("Compra")) {
                String imFile = "/storage/emulated/0/PyMESoft/Logotipo/logopng";
                image = Image.getInstance(imFile);
                image.scaleAbsolute(100f,100f);
                image.setAlignment(Image.LEFT);
                Paragraph factu=new Paragraph("Factura de Compra",regularReportA);
                factu.setAlignment(Element.ALIGN_RIGHT);
                Paragraph Plazo=new Paragraph(diaspago+" "+"días",regularTotalBold);
                Plazo.setAlignment(Element.ALIGN_RIGHT);
                PdfPTable pdfPtableimage = new PdfPTable(2);
                pdfPtableimage.setWidthPercentage(100);
                pdfPtableimage.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell imagecell = new PdfPCell();
                PdfPCell factucell = new PdfPCell();
                imagecell.addElement(image);
                imagecell.setBorderColor(new BaseColor(255,255,255));
                factucell.addElement(factu);

                factucell.setBorderColor(new BaseColor(255,255,255));
                factucell.addElement(Plazo);




                pdfPtableimage.addCell(imagecell);
                pdfPtableimage.addCell(factucell);

                pdfPtableimage.setSpacingAfter(25);
                mDoc.add(pdfPtableimage);
                System.setProperty("http.agent", "Chrome");
            }
            if (radiotipo.equals("Rectangular")&&estadoventas2.equals("Borrador")){
                String imFile = "/storage/emulated/0/PyMESoft/Logotipo/logopng";
                image = Image.getInstance(imFile);
                image.scaleAbsolute(160f,70f);
                image.setAlignment(Image.LEFT);
                Paragraph factu=new Paragraph("Cotización",regularReportA);
                factu.setAlignment(Element.ALIGN_RIGHT);
                Paragraph Plazo=new Paragraph(diaspago+" "+"días",regularTotalBold);
                Plazo.setAlignment(Element.ALIGN_RIGHT);
                PdfPTable pdfPtableimage = new PdfPTable(2);
                pdfPtableimage.setWidthPercentage(100);
                pdfPtableimage.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell imagecell = new PdfPCell();
                PdfPCell factucell = new PdfPCell();
                imagecell.addElement(image);
                imagecell.setBorderColor(new BaseColor(255,255,255));
                factucell.addElement(factu);

                factucell.setBorderColor(new BaseColor(255,255,255));
                factucell.addElement(Plazo);




                pdfPtableimage.addCell(imagecell);
                pdfPtableimage.addCell(factucell);

                pdfPtableimage.setSpacingAfter(25);
                mDoc.add(pdfPtableimage);
                System.setProperty("http.agent", "Chrome");
            }else if(radiotipo.equals("Cuadrado")&&estadoventas2.equals("Borrador")) {
                String imFile = "/storage/emulated/0/PyMESoft/Logotipo/logopng";
                image = Image.getInstance(imFile);
                image.scaleAbsolute(100f,100f);
                image.setAlignment(Image.LEFT);
                Paragraph factu=new Paragraph("Cotización",regularReportA);
                factu.setAlignment(Element.ALIGN_RIGHT);
                Paragraph Plazo=new Paragraph(diaspago+" "+"días",regularTotalBold);
                Plazo.setAlignment(Element.ALIGN_RIGHT);
                PdfPTable pdfPtableimage = new PdfPTable(2);
                pdfPtableimage.setWidthPercentage(100);
                pdfPtableimage.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell imagecell = new PdfPCell();
                PdfPCell factucell = new PdfPCell();
                imagecell.addElement(image);
                imagecell.setBorderColor(new BaseColor(255,255,255));
                factucell.addElement(factu);

                factucell.setBorderColor(new BaseColor(255,255,255));
                factucell.addElement(Plazo);




                pdfPtableimage.addCell(imagecell);
                pdfPtableimage.addCell(factucell);

                pdfPtableimage.setSpacingAfter(40);
                mDoc.add(pdfPtableimage);
                System.setProperty("http.agent", "Chrome");
            }











        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Toast.makeText(this, "Fallo c1", Toast.LENGTH_SHORT).show();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            Toast.makeText(this, "Fallo c2", Toast.LENGTH_SHORT).show();
        } catch (IOException ex) {
            ex.printStackTrace();
            Toast.makeText(this, "Fallo c3", Toast.LENGTH_SHORT).show();
        } catch (BadElementException ex) {

            ex.printStackTrace();
            Toast.makeText(this, "Fallo c4", Toast.LENGTH_SHORT).show();
        } catch (DocumentException ex) {
            ex.printStackTrace();
            Toast.makeText(this, "Fallo c5", Toast.LENGTH_SHORT).show();
        }
        try{
            Paragraph pcompany = new Paragraph("PyMESoft®", regularReport);
            pcompany.setAlignment(Element.ALIGN_RIGHT);
            pcompany.setSpacingAfter(40);

            Paragraph pdatosgenerales = new Paragraph("INFORMACIÓN FACTURA", regularReport2);
            pdatosgenerales.setAlignment(Element.ALIGN_LEFT);
            pdatosgenerales.setSpacingAfter(10);

            Paragraph plista = new Paragraph("LISTADO PRODUCTOS", regularReport2);
            plista.setAlignment(Element.ALIGN_LEFT);
            plista.setSpacingAfter(10);

            PdfPTable pdfPtablea = new PdfPTable(1);
            pdfPtablea.setWidthPercentage(100);
            pdfPtablea.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(new BaseColor(114,133,165));
            cell.setFixedHeight(2);
            cell.setBorderColor(new BaseColor(114,133,165));
            pdfPtablea.addCell(cell);
            pdfPtablea.setSpacingAfter(5);







            Paragraph nombre1 = new Paragraph("Nombre:", regularSub2);
            Paragraph vnombre = new Paragraph(nombreventas2,regularAddress);
            nombre1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vnombre.setAlignment(Element.ALIGN_RIGHT);



            Paragraph fecha1 = new Paragraph("Fecha", regularSub2);
            Paragraph vfecha1 = new Paragraph(fechaventas2,regularAddress);

            fecha1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vfecha1.setAlignment(Element.ALIGN_RIGHT);


            Paragraph hora1 = new Paragraph("Hora", regularSub2);
            Paragraph vhora1 = new Paragraph(horareal2,regularAddress);
            hora1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vhora1.setAlignment(Element.ALIGN_RIGHT);



            Paragraph producto1 = new Paragraph("Producto:", regularSub2);
            Paragraph vproducto = new Paragraph(productoventas2,regularAddress);
            producto1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vproducto.setAlignment(Element.ALIGN_RIGHT);

            Paragraph precio1 = new Paragraph("Precio:", regularSub2);
            Paragraph vprecio = new Paragraph(precioventas2,regularAddress);
            precio1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vprecio.setAlignment(Element.ALIGN_RIGHT);

            Paragraph medida1 = new Paragraph("Medida:", regularSub2);
            Paragraph vpmedida = new Paragraph(medidaventas2,regularAddress);
            medida1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vpmedida.setAlignment(Element.ALIGN_RIGHT);




            Paragraph valor1 = new Paragraph("Valor:", regularSub2);
            Paragraph vvalor = new Paragraph(valorventas2,regularAddress);
            valor1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vvalor.setAlignment(Element.ALIGN_RIGHT);


            Paragraph unidades1 = new Paragraph("Unidades - Promedio:", regularSub2);
            Paragraph vunidades = new Paragraph(unidadesventas2+"    "+"|"+"    "+horaventas2);
            unidades1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vunidades.setAlignment(Element.ALIGN_RIGHT);


            PdfPTable table1 = new PdfPTable(4);
            table1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.setWidthPercentage(100);
            PdfPCell cellnombre = new PdfPCell();
            PdfPCell cellnombre2 = new PdfPCell();
            PdfPCell cellfecha = new PdfPCell();
            PdfPCell cellfecha2 = new PdfPCell();
            PdfPCell cellhora = new PdfPCell();
            PdfPCell cellhora2 = new PdfPCell();
            PdfPCell cellproducto = new PdfPCell();
            PdfPCell cellproducto2 = new PdfPCell();
            PdfPCell cellunidades = new PdfPCell();
            PdfPCell cellunidades2 = new PdfPCell();
            PdfPCell cellmedida = new PdfPCell();
            PdfPCell cellmedida2 = new PdfPCell();
            PdfPCell cellprecio = new PdfPCell();
            PdfPCell cellprecio2 = new PdfPCell();
            PdfPCell cellvalor = new PdfPCell();
            PdfPCell cellvalor2 = new PdfPCell();

            cellnombre.addElement(nombre1);

            cellnombre2.addElement(vnombre);
            cellfecha.addElement(fecha1);
            cellfecha2.addElement(vfecha1);
            cellhora.addElement(hora1);
            cellhora2.addElement(vhora1);
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

            cellnombre.setUseAscender(true);
            cellnombre.setVerticalAlignment(Element.ALIGN_TOP);
            cellnombre.setBorderColor(BaseColor.WHITE);


            cellnombre2.setBorderColor(BaseColor.WHITE);
            cellnombre2.setUseAscender(true);
            cellnombre2.setVerticalAlignment(Element.ALIGN_CENTER);


            cellfecha.setBorderColor(BaseColor.WHITE);
            cellfecha.setUseAscender(true);
            cellfecha.setVerticalAlignment(Element.ALIGN_TOP);


            cellfecha2.setBorderColor(BaseColor.WHITE);
            cellfecha2.setUseAscender(true);
            cellfecha2.setVerticalAlignment(Element.ALIGN_CENTER);
            cellhora.setBorderColor(BaseColor.WHITE);
            cellhora.setUseAscender(true);
            cellhora.setVerticalAlignment(Element.ALIGN_TOP);
            cellhora2.setBorderColor(BaseColor.WHITE);
            cellhora2.setUseAscender(true);
            cellhora2.setVerticalAlignment(Element.ALIGN_CENTER);
            cellproducto.setBorderColor(BaseColor.WHITE);
            cellproducto.setUseAscender(true);
            cellproducto.setVerticalAlignment(Element.ALIGN_TOP);

            cellproducto2.setBorderColor(BaseColor.WHITE);
            cellproducto2.setUseAscender(true);
            cellproducto2.setVerticalAlignment(Element.ALIGN_CENTER);
            cellunidades.setBorderColor(BaseColor.WHITE);
            cellunidades.setUseAscender(true);
            cellunidades.setVerticalAlignment(Element.ALIGN_TOP);

            cellunidades2.setBorderColor(BaseColor.WHITE);
            cellunidades2.setUseAscender(true);
            cellunidades2.setVerticalAlignment(Element.ALIGN_CENTER);
            cellmedida.setBorderColor(BaseColor.WHITE);
            cellmedida.setUseAscender(true);
            cellmedida.setVerticalAlignment(Element.ALIGN_TOP);

            cellmedida2.setBorderColor(BaseColor.WHITE);
            cellmedida2.setUseAscender(true);
            cellmedida2.setVerticalAlignment(Element.ALIGN_CENTER);
            cellvalor.setBorderColor(BaseColor.WHITE);
            cellvalor.setUseAscender(true);
            cellvalor.setVerticalAlignment(Element.ALIGN_TOP);
            cellvalor2.setBorderColor(BaseColor.WHITE);
            cellvalor2.setUseAscender(true);
            cellvalor2.setVerticalAlignment(Element.ALIGN_CENTER);
            cellprecio.setBorderColor(BaseColor.WHITE);
            cellprecio.setUseAscender(true);
            cellprecio.setVerticalAlignment(Element.ALIGN_TOP);
            cellprecio2.setBorderColor(BaseColor.WHITE);
            cellprecio2.setUseAscender(true);
            cellprecio2.setVerticalAlignment(Element.ALIGN_CENTER);





            table1.addCell(cellnombre);
            table1.addCell(cellproducto);
            table1.addCell(cellfecha);
            table1.addCell(cellhora);
            table1.addCell(cellnombre2);
            table1.addCell(cellproducto2);
            table1.addCell(cellfecha2);
            table1.addCell(cellhora2);
            table1.addCell(cellmedida);
            table1.addCell(cellunidades);
            table1.addCell(cellprecio);
            table1.addCell(cellvalor);
            table1.addCell(cellmedida2);
            table1.addCell(cellunidades2);
            table1.addCell(cellprecio2);
            table1.addCell(cellvalor2);



            mDoc.add(pdatosgenerales);
            mDoc.add(pdfPtablea);



            table1.setSpacingAfter(5);
            mDoc.add(table1);


            PdfPTable pdfPtable = new PdfPTable(1);
            pdfPtable.setWidthPercentage(100);
            pdfPtable.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell cell1 = new PdfPCell();
            cell1.setBackgroundColor(new BaseColor(114,133,165));
            cell1.setFixedHeight(2);
            cell1.setBorderColor(new BaseColor(114,133,165));
            pdfPtable.addCell(cell);
            pdfPtable.setSpacingAfter(20);

            mDoc.add(pdfPtable);
            mDoc.add(plista) ;
            PdfPTable pdfPtable2 = new PdfPTable(1);
            pdfPtable2.setWidthPercentage(100);
            pdfPtable2.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell cell2 = new PdfPCell();
            cell2.setBackgroundColor(new BaseColor(114,133,165));
            cell2.setFixedHeight(2);
            cell2.setBorderColor(new BaseColor(114,133,165));
            pdfPtable2.addCell(cell2);
            pdfPtable2.setSpacingAfter(5);
            mDoc.add(pdfPtable2);
//            mDoc.close();




            // Toast.makeText(VistaAA.this, mFilename + "guardado" + mFilepath, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, "Fallo b", Toast.LENGTH_SHORT).show();
        }
        try {
            PdfPTable Atable = new PdfPTable(15);
            Atable.setHorizontalAlignment(Element.ALIGN_CENTER);
            Atable.setWidthPercentage(95);
           // ArrayList<Double> ListaMed=new ArrayList<>();


//
           for (int aw = 0; aw <List1.size(); aw++) {
                // for (adpt.setNotes(allnotes3);;) {
                //adpt1.notifyDataSetChanged();

                //ListaMed.add(adpt.getmedida(aw));

                Paragraph p = new Paragraph();

                Paragraph q1 = new Paragraph(String.valueOf(aw + 1),regularSub );
                Paragraph q2 = new Paragraph(String.valueOf(List1.get(aw)),regularTotal2);




                Chunk gumble = new Chunk(new VerticalPositionMark());

                p.add(q1);
                p.add(gumble);
               p.add(q2);

                PdfPCell cell = new PdfPCell(p);
                cell.setPaddingBottom(8);
                cell.setPaddingTop(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorderColor(BaseColor.LIGHT_GRAY);
                cell.setFixedHeight(25);

                Atable.addCell(cell);
                //stamper.close();

            }


            Atable.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
            Atable.completeRow();

           mDoc.add(Atable);
//            mDoc.close();

        } catch (Exception e) {

        }

try {
            Paragraph Terminos = new Paragraph("TÉRMINOS DE PAGO", regularReport2);
            Terminos.setAlignment(Element.ALIGN_LEFT);
            Terminos.setSpacingAfter(10);
            Terminos.setSpacingBefore(10);

            Paragraph Txterminos = new Paragraph();
            Txterminos.add("El Cliente se compromete a pagar en un plazo no superior a");
            if(diaspago.equals("0")){
                Txterminos.add(" "+"Contado ");}else{

                Txterminos.add(" "+diaspago+ " "+"días");}
            Txterminos.setAlignment(Element.ALIGN_LEFT);
            Txterminos.setSpacingAfter(10);
            Txterminos.setSpacingBefore(3);

            PdfPTable pdflinea = new PdfPTable(1);
            pdflinea.setWidthPercentage(100);
            pdflinea.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell cellg = new PdfPCell();
            cellg.setBackgroundColor(new BaseColor(114,133,165));
            cellg.setFixedHeight(2);
            cellg.setBorderColor(new BaseColor(114,133,165));
            pdflinea.addCell(cellg);
            pdflinea.setSpacingAfter(5);
//            mDoc.add(Terminos);
//            mDoc.add(pdflinea);
//            mDoc.add(Txterminos);
           // mDoc.add(tableFooter);
            String imgsign = "/storage/emulated/0/PyMESoft/Signature/signaturepng";
            image = Image.getInstance(imgsign);
            image.scaleAbsolute(180f,150f);
            image.setAlignment(Image.ALIGN_CENTER|Image.ALIGN_BOTTOM);
            PdfPTable pdfPtablesign = new PdfPTable(1);
            pdfPtablesign.setWidthPercentage(20);

            pdfPtablesign.setHorizontalAlignment(Element.ALIGN_RIGHT);


            PdfPCell signcell = new PdfPCell();
            PdfPCell CCcell = new PdfPCell();
            PdfPCell Nomsigncell=new PdfPCell();
            signcell.addElement(image);
            signcell.setBorder(Rectangle.BOTTOM);
            signcell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            signcell.setBorderColorBottom((new BaseColor(114,133,165)));
            signcell.setBorderWidthBottom(1);
            signcell.setBorderColor(new BaseColor(255,255,255));
            CCcell.addElement(new Phrase("cc."+" "+"1018429410",footerE));
            CCcell.setBorderColor(new BaseColor(255,255,255));
            CCcell.setVerticalAlignment(Element.ALIGN_TOP);
            Nomsigncell.addElement(new Phrase("Juan Sebastián Gómez",footerE));
           Nomsigncell.setBorderColor(new BaseColor(255,255,255));
           Nomsigncell.setVerticalAlignment(Element.ALIGN_TOP);
            pdfPtablesign.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);

            pdfPtablesign.addCell(signcell);
            pdfPtablesign.addCell(Nomsigncell);
            pdfPtablesign.addCell(CCcell);
            //pdfPtablesign.setExtendLastRow(true);
            //pdfPtablesign.setTotalWidth((mDoc.right()-mDoc.left())*pdfPtablesign.getWidthPercentage()/100f);
            //pdfPtablesign.writeSelectedRows(0, -1, mDoc.left(), mDoc.bottom()+pdfPtablesign.getTotalHeight(),writer1.getDirectContent());
           // pdfPtablesign.setSpacingBefore(120);
            //pdfPtablesign.setTotalWidth(mDoc.right(40)
                   // - mDoc.left(60));
           //mDoc.add(pdfPtablesign);
            //mDoc.left(mDoc.leftMargin())
            pdfPtablesign.setTotalWidth(90);
            int intetable1=pdfPtablesign.getRows().size();
            int numbpage=mDoc.getPageNumber();






            pdfPtablesign.writeSelectedRows(0, -1,
                            mDoc.left(420)
                    ,
                    pdfPtablesign.getTotalHeight() + mDoc.bottom(mDoc.bottomMargin()),
                    writer1.getDirectContent());
            pdfPtablesign.writeSelectedRows(0, -1,
                    mDoc.left(420)
                    ,
                    pdfPtablesign.getTotalHeight() + mDoc.bottom(mDoc.bottomMargin()),
                    writer2.getDirectContent());
//            PdfContentByte canvas = writer1.getDirectContent();
//          PdfContentByte canvas2 = writer2.getDirectContent();
//            canvas.setColorStroke(BaseColor.GRAY);
//            canvas.setColorFill(BaseColor.WHITE);
//            canvas.setLineWidth(0f);
//            canvas.roundRectangle(487, 665,90, 30, 10);
//            canvas.stroke();
//    canvas2.setColorStroke(BaseColor.GRAY);
//    canvas2.setColorFill(BaseColor.WHITE);
//    canvas2.setLineWidth(0f);
//    canvas2.roundRectangle(487, 665,90, 30, 10);
//    canvas2.stroke();

                mDoc.close();
   // Toast.makeText(VistaAA.this, mFilename + "guardado" + mFilepath, Toast.LENGTH_SHORT).show();


}catch (Exception e){
    Toast.makeText(this, "Fallo firma y otros", Toast.LENGTH_SHORT).show();
}














    }






}