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
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEvent;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
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

//import com.github.barteksc.pdfviewer.util.FitPolicy;


public class pdfviewer3 extends AppCompatActivity  implements Interface2 {
    private PDFView pdfView;
    ImageView back1;
    private File file;
    int comprobarpdf;


    Pdfstructuredclass pdfstructuredclass=new Pdfstructuredclass();
    Pdfbasicclass pdfbasicclass=new Pdfbasicclass();


    TextView title6;
    BaseFont baseFont=null;
    Uri pdfUri;


    ArrayList<Double> listacuero3;

    NoteProdViewModel noteProdViewModel;
    DecimalFormat format = new DecimalFormat("###,###,##0");


    StorageReference storageReference;





    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


    Document mDoc = new Document(PageSize.LETTER,36,36,53,56);

//
    String productoventas2;

    String precioventas2;

  String valorventas2;
  double valorbr;
    double ValorImp;
    double ValorDesc;
    double TaxValue;
    double TaxValue2;
    double DiscountValue;
    double ValorImp2;
    double valorneto;
  String fechaventas2,pdfemail,pdfphone,pdfcity,pdfaddress;
   String estadoventas2;
//    String horareal2;
  String diaspago,Fecha2;
    DatabaseReference myrootDbaseref5;
    FirebaseStorage mystorage;
    FirebaseAuth mAuth;

    ArrayList<String>List1,Lista7;
    ArrayList<Double>List2,List3,List4;

   String nombreventas2;

    String pattern = "EEEEE MMMMM yyyy HH:mm:ss.SSSZ";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("en", "US"));
    String mFilename = simpleDateFormat.format(System.currentTimeMillis());




    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat hora1 = new SimpleDateFormat("HH:mm:ss");
    String hora2 = hora1.format(calendar.getTime());
 String mFilepath = Environment.getExternalStorageDirectory() +   File.separator+ "PyMESoft"+
  File.separator+"invoices"+File.separator +mFilename.toString().replaceAll(":",".");


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


        myrootDbaseref5 = FirebaseDatabase.getInstance().getReference();

        mystorage= FirebaseStorage.getInstance();
        storageReference=mystorage.getReference();




        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, REQUEST_CODE_ASK_PERMISSIONS);


            } else {
                try {

                    RecibirBundleFragment3();
                        if(comprobarpdf==1){
                            pdfbasicclass.createpdf(pdfviewer3.this,productoventas2,precioventas2,valorventas2,
                                    valorbr,valorneto,fechaventas2,estadoventas2,diaspago,Fecha2,nombreventas2,
                                    Lista7,List1,List2,List3,List4,outputStream, mFilepath,ValorDesc,ValorImp,ValorImp2,
                                    DiscountValue,TaxValue,TaxValue2,pdfphone,pdfemail,pdfaddress,pdfcity);
                        }
                        else if(comprobarpdf==0){
                    pdfstructuredclass.createstructuredpdf(pdfviewer3.this,productoventas2,precioventas2,valorventas2,
                            valorbr,valorneto,fechaventas2,estadoventas2,diaspago,Fecha2,nombreventas2,
                            Lista7,List1,List2,List3,List4,outputStream, mFilepath,ValorDesc,ValorImp,ValorImp2,
                            DiscountValue,TaxValue,TaxValue2,pdfphone,pdfemail,pdfaddress,pdfcity);
                        }


                } catch (DocumentException e) {
                    Toast.makeText(this, "no inicia", Toast.LENGTH_SHORT).show();
                }


            }
        } else {





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






















////////////////////////////////////

    }


    public void compartir(View view) throws IOException {
        RecibirBundleFragment3();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, REQUEST_CODE_ASK_PERMISSIONS);


            } else {




            }
        } else {









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

        if(comprobarpdf==1){
            file=new File(pdfbasicclass.getfile2().getAbsolutePath());

        }else if(comprobarpdf==0){
             file=new File(pdfstructuredclass.getfile2().getAbsolutePath());

        }

        Uri pdfUri = Uri.fromFile(file);
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);

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

        Intent i = new Intent(pdfviewer3.this, homeinvoice2.class);
        startActivity(i);
        noteProdViewModel=new ViewModelProvider(this).get(NoteProdViewModel.class);
        noteProdViewModel.DeleteAll();

    }


    @Override
    public void OnaddClick(double getinput) {

    }

    @Override
    public void outputClick(ArrayList<Double> Lista5) {


    }

    @Override
    public void streamclick(ByteArrayOutputStream outputStream) {

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    //new PdfSave().execute();
                  try {
                      RecibirBundleFragment3();
                      if(comprobarpdf==0){
                          pdfstructuredclass.createstructuredpdf(pdfviewer3.this,productoventas2,precioventas2,valorventas2,
                                  valorbr,valorneto,fechaventas2,estadoventas2,diaspago,Fecha2,nombreventas2,
                                  Lista7,List1,List2,List3,List4,outputStream, mFilepath,ValorDesc,ValorImp,ValorImp2,DiscountValue,TaxValue,TaxValue2,
                                  pdfphone,pdfemail,pdfaddress,pdfcity);


                      }else if(comprobarpdf==1){
                          pdfbasicclass.createpdf(pdfviewer3.this,productoventas2,precioventas2,valorventas2,
                                  valorbr,valorneto,fechaventas2,estadoventas2,diaspago,Fecha2,nombreventas2,
                                  Lista7,List1,List2,List3,List4,outputStream, mFilepath,ValorDesc,ValorImp,ValorImp2,DiscountValue,TaxValue,TaxValue2,
                                  pdfphone,pdfemail,pdfaddress,pdfcity);

                      }


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
//                    uploadpdf(pdfUri);


                } else {
                    Toast.makeText(pdfviewer3.this, "Permiso negado para adultos", Toast.LENGTH_SHORT).show();
                }

            }
        }






    }

    public void uploadpdf(File file) {


        if(comprobarpdf==1){
            file=new File(pdfbasicclass.getfile2().getAbsolutePath());

        }else if(comprobarpdf==0){
             file=new File(pdfstructuredclass.getfile2().getAbsolutePath());

        }

        mAuth=FirebaseAuth.getInstance();
        String id=mAuth.getCurrentUser().getUid();



       // pdfUri = Uri.fromFile(new File(pdfFile.getAbsolutePath()))
        pdfUri = Uri.fromFile(file);




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
                               String horaventas = "n/a";//horaventas2;
                                String productoventas = productoventas2;
                                //String colorventas = ghora.getText().toString();
//                                String unidadesventas = unidadesventas2;
                                String precioventas = precioventas2;
                                String medidaventas = "n/a"; //medidaventas2;
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
//                                datosventa.put("Unidades", unidadesventas);
                                datosventa.put("Valor", valorventas);
                                datosventa.put("Estado",estado);
                                datosventa.put("pdfurl", url2);
                                datosventa.put("Key_fire",newref.getKey());
                                datosventa.put("Fechaparapago",Fecha2);
                                datosventa.put("Dias_plazo",diaspago);


                                datosventa.put("Id_usuario",id);
                                newref.setValue(datosventa);
                                Toast.makeText(pdfviewer3.this   , "Se ha registrado la Venta", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });


//         Instruct the user to install a PDF reader here, or something


    }
    public void guardarbdventas() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissions, REQUEST_CODE_ASK_PERMISSIONS_2);

            } else {
                uploadpdf(file);

            }


        } else {
            uploadpdf(file);
        }


        Toast.makeText(pdfviewer3.this, "Se ha registrado la Venta", Toast.LENGTH_SHORT).show();
        //Intent i = new Intent(VistaAA.this, home1.class);
        //startActivity(i);

        ///////////////////////
    }

    public void RecibirBundleFragment3(){

        comprobarpdf=Constants.getSP(this).getPDFPOSITION();


        listacuero3= (ArrayList<Double>) getIntent().getSerializableExtra("WLTP_list");

        // gnombre2.setText(getIntent().getExtras().getString("Nombre1"));
        nombreventas2=getIntent().getExtras().getString("Nombre1");
//        RecibirNoteprod=(Map<String,String>).getSerializableExtra("mapa");


        fechaventas2= getIntent().getExtras().getString("Fecha1").trim();

//         horaventas2=getIntent().getExtras().getString("Hora1");

        productoventas2=getIntent().getExtras().getString("Producto1");

        List1= (ArrayList<String>) getIntent().getSerializableExtra("listaProd1");
        List2= (ArrayList<Double>) getIntent().getSerializableExtra("listaPre1");
        List3= (ArrayList<Double>) getIntent().getSerializableExtra("listaCant1");
        List4= (ArrayList<Double>) getIntent().getSerializableExtra("listaResultado");
//        List5= (ArrayList<Double>) getIntent().getSerializableExtra("ListaImp");
//        List6=(ArrayList<Double>) getIntent().getSerializableExtra("ListaRimp");
        Lista7=(ArrayList<String>) getIntent().getSerializableExtra("ListaDesc");

        valorbr=getIntent().getExtras().getDouble("valorbruto");
        ValorImp=getIntent().getExtras().getDouble("valorimp");
        ValorImp2=getIntent().getExtras().getDouble("valorimp2");
        ValorDesc=getIntent().getExtras().getDouble("valordesc");
        TaxValue=getIntent().getExtras().getDouble("impuestopercent");
        TaxValue2=getIntent().getExtras().getDouble("impuesto2percent");
        DiscountValue=getIntent().getExtras().getDouble("descpercent");
        valorneto=getIntent().getExtras().getDouble("valorneto");
        pdfphone=getIntent().getExtras().getString("pdfphone");
        pdfemail= getIntent().getExtras().getString("pdfemail");
        pdfaddress=getIntent().getExtras().getString("pdfaddress");
        pdfcity=getIntent().getExtras().getString("pdfcity");
//        SumaResultado=getIntent().getExtras().getString("Total1");






//        unidadesventas2=getIntent().getExtras().getString("Unidades1");

        precioventas2=getIntent().getExtras().getString("Precio1");

//       medidaventas2=getIntent().getExtras().getString("Medida1");
//
        valorventas2=getIntent().getExtras().getString("Total1");

        estadoventas2=getIntent().getExtras().getString("Estado1");
        Fecha2 =getIntent().getExtras().getString("Fecha2").trim();
        //outputStream2=getIntent().getExtras().getByteArray("wpa");



//        horareal2=hora2;
        diaspago=Constants.getSP(this).getDIAS();



    }










}