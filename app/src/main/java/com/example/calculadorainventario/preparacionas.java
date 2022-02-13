package com.example.calculadorainventario;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.icu.text.NumberFormat;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.os.Parcelable;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculadorainventario.ui.main.SectionsPagerAdapter;
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
import java.io.Serializable;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class preparacionas extends AppCompatActivity {
    TextView gdate, gprecio, gvalor, gmedida, gcantidad, gnombre, gproducto, ghora, gopcion, txtotalbottom,textoprepa,title5;
    Button btpdf;
    ShareViewModel2 shareViewModel2;
    SharedViewModel shareViewModel;
    ImageView back3,arrowchange2;
    ArrayList<Double> ListaCuero,Listathis;
    ArrayList<String> Listapdf,Listapasar;;
    ArrayList<List<Note>> all3=new ArrayList<>();
    //ArrayList<Note>ListaPdf;
    NoteViewModel noteViewModel;
    RelativeLayout relativprepa;
    List<Note>allnotes3,allnotes4;
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ArrayList<arrayconstructor>Listadobles;
    List<Note>Listadobles2;
    ByteArrayOutputStream outputStreamnext = new ByteArrayOutputStream();

    private File file;
    CardView card_prepa;
    String pattern = "EEEEE MMMMM yyyy HH:mm:ss.SSSZ";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("en", "US"));
    String mFilename = simpleDateFormat.format(System.currentTimeMillis());
    String mFilepath = Environment.getExternalStorageDirectory() + "/" + mFilename + ".pdf";

   // ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    int diasp;
    Document mDoc = new Document(PageSize.LETTER);
    String diaspag;
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
    String nombreventas2;
    AdaptadorProductoGuardado adpt=new AdaptadorProductoGuardado();

    private static final int REQUEST_CODE_ASK_PERMISSIONS = 111;
    private static final int REQUEST_CODE_ASK_PERMISSIONS_2 = 112;


    CardView card_view, card_operacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preparacionas);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        gdate = (TextView) findViewById(R.id.gdate);
        gprecio = (TextView) findViewById(R.id.gprecio);
        gmedida = (TextView) findViewById(R.id.gmedida);
        gopcion = (TextView) findViewById(R.id.gopcion);
        gcantidad = (TextView) findViewById(R.id.gcantidad);
        gvalor = (TextView) findViewById(R.id.gvalor);
        relativprepa=findViewById(R.id.relativprepa);
        title5=findViewById(R.id.title5);

        card_prepa=findViewById(R.id.card_prepa);
        textoprepa=findViewById(R.id.textoprepa);

        gnombre = (TextView) findViewById(R.id.gnombre);
        gproducto = (TextView) findViewById(R.id.gproducto);
        txtotalbottom = findViewById(R.id.txtotalbottom);
        btpdf = findViewById(R.id.btpdf);
        arrowchange2=findViewById(R.id.arrowchange2);
        //card_operacion = findViewById(R.id.card_operacion);
        back3= findViewById(R.id.back3);


        ghora = (TextView) findViewById(R.id.ghora);
        card_view = (CardView) findViewById(R.id.card_view);
        //Date d = new Date();

        recibirbun();

        ViewPager view_pager = (ViewPager) findViewById(R.id.view_pager);
        view_pager.setOffscreenPageLimit(1);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat fecc = new SimpleDateFormat("dd/MMM/yyyy");

        String fechacComplString = fecc.format(calendar.getTime());
        gdate.setText(fechacComplString);
       calendar.add(Calendar.DATE,diasp);
        final String fechafinal= fecc.format(calendar.getTime());
        gopcion.setText(fechafinal);

      //  Calendar c = Calendar.getInstance();
       // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss");
       // String reg_date = df.format(c.getTime());
       // showtoast("Currrent Date Time : "+reg_date);

       // c.add(Calendar.DATE, 3);  // number of days to add
       // String end_date = df.format(c.getTime());
       // showtoast("end Time : "+end_date);
        shareViewModel2 = new ViewModelProvider(this).get(ShareViewModel2.class);
        shareViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        //if(savedInstanceState!=null) {

        //}else{




    tabs.setVisibility(View.GONE);
    view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
           //if(position==2){
             //   new pdfasynktask().execute();

                   // savepdf();
               //     PdfFraagment pdfk=new PdfFraagment();
                    //Intent intent=new Intent(preparacionas.this,PdfFraagment.class);
                    //Bundle bundle=new Bundle();
                    //bundle.putByteArray("wpa",outputStreamnext.toByteArray());
              //  try {
                //    savepdf();
                //} catch (DocumentException e) {
                  //  e.printStackTrace();
                //}
                //pdfk.createpdf(outputStreamnext.toByteArray());

          //  }



        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    });




        //gvalor.setText("0");


        String first = "PyMESoft";
        String next = "<font color='#1D2E4A'>FActuras</font>";
        title5.setText(Html.fromHtml(first + next));
    arrowchange2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(textoprepa.getText().toString().equals("Venta")){
                textoprepa.setText("Compra");
                card_prepa.setCardBackgroundColor(getResources().getColor(R.color.colorDarkRed));
            }else  if(textoprepa.getText().toString().equals("Compra")){
                textoprepa.setText("Borrador");
                card_prepa.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            }else  if(textoprepa.getText().toString().equals("Borrador")){
                textoprepa.setText("Venta");
                card_prepa.setCardBackgroundColor(getResources().getColor(R.color.colorDarkBlue));
            }
        }
    });
        noteViewModel=new ViewModelProvider(this).get(NoteViewModel.class);
        noteViewModel.getAllDoubleNotes().observe(this, new Observer<List<Double>>() {
            @Override
            public void onChanged(List<Double> doubles) {

//                Listadobles=doubles;



            }
        });
        shareViewModel.getUnidadesLista2().observe(this, new Observer<ArrayList<arrayconstructor>>() {
            @Override
            public void onChanged(ArrayList<arrayconstructor> arrayconstructors) {
                Listadobles=arrayconstructors;
            }
        });
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                Listadobles2=notes;

            }
        });










         btpdf.setOnClickListener(new View.OnClickListener() {
           @Override
         public void onClick(View v) {
                       Intent intent = new Intent(preparacionas.this, pdfviewer.class);

                       //  ArrayList<Note> Lista78;
                       //Lista78=(ArrayList<Note>)allnotes3;
                       Bundle bundle = new Bundle();
         //intent.putExtra("Lista6", (Parcelable) allnotes3);

         bundle.putSerializable("Diasdepago2",diaspag);
         bundle.putSerializable("Fecha2",gopcion.getText().toString());
         bundle.putSerializable("Unidades1",gcantidad.getText().toString());
         bundle.putSerializable("Medida1",gmedida.getText().toString());
         bundle.putSerializable("Total1",txtotalbottom.getText().toString());
         bundle.putSerializable("Fecha1",gdate.getText().toString());
         bundle.putSerializable("Hora1",ghora.getText().toString());
         bundle.putSerializable("Precio1",gprecio.getText().toString());
         bundle.putSerializable("Nombre1",gnombre.getText().toString());
         bundle.putSerializable("Producto1",gproducto.getText().toString());
         bundle.putSerializable("Estado1",textoprepa.getText().toString());

         Listapasar=new ArrayList<>();


               for (int i = 0 ; i <Listadobles2.size() ; i++){

//                   Log.d("value is" , Listadobles2.get(i).valor_Medida.toString());}
//                   Listapdf.add(Listadobles2.get(i).getValor_Medida().toString());
                   String pdfvalor1=Listadobles2.get(i).valor_Medida.toString();
               Listapasar.add(pdfvalor1);}
//
//
//

         bundle.putSerializable("listapdf1",Listapasar);

//
//         bundle.putSerializable("Listapdf",Listapasar);
        // bundle.putByteArray("wpa",outputStream.toByteArray());


                       intent.putExtras(bundle);
                      // pdfviewer pdfviewer2=new pdfviewer();





                       startActivity(intent);














        }
        });
    back3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            onBackPressed();
        }
    });


        shareViewModel2.setPrecio2(gprecio.getText().toString());
      shareViewModel2.getPath().observe(this, new Observer<ArrayList<Note>>() {
          @Override
          public void onChanged(ArrayList<Note> notes) {
              //ListaPdf=notes;
          }
      });
        shareViewModel2.getNombrepdf().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String nombrepdf) {
            }
        });
        shareViewModel2.getProductopdf().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String productopdf) {
               // ghora.setText(productopdf);
            }
        });
       shareViewModel2.getMedidaepdf().observe(this, new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {
             // gmedida.setText(charSequence);
            //  gcantidad.setText(charSequence);

            }
        });
        shareViewModel2.getUnidadespdf().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String unidadespdf) {

               // gcantidad.setText(unidadespdf);
            }
        });
        shareViewModel2.getValorpdf().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String valorpdf) {
                txtotalbottom.setText(valorpdf);

            }
        });
        shareViewModel2.getFechapdf().observe(this, new Observer<ByteArrayOutputStream>() {
            @Override
            public void onChanged(ByteArrayOutputStream byteArrayOutputStream) {



            }


        });
        shareViewModel2.getHorapdf().observe(this, new Observer<String>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(String horapdf) {


                gvalor.setText(horapdf);
                shareViewModel2.setINGRESAR_DATOS(gvalor.getText());


            }
        });
        shareViewModel2.getPreciopdf().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String preciopdf) {

            }
        });
        shareViewModel2.getPrecio2().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String precio2) {

            }
        });
        shareViewModel2.getINGRESAR_DATOS().observe(this, new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {


            }
        });
        shareViewModel2.getMedidasuma().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                //gmedida.setText(String.valueOf(aDouble));


            }
        });
        shareViewModel2.getTxValor().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txtotalbottom.setText(s);
            }
        });


        LiveData<Double> s =noteViewModel.getSumTotal();
        s.observe(this, new Observer<Double>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(Double aDouble) {
                if(aDouble!=null){
                   // DecimalFormat df = new DecimalFormat("##.#");
                   // gmedida.setText(Double.toString(Double.parseDouble(df.format(aDouble))).trim());}
                    gmedida.setText(Double.toString(Double.parseDouble(String.valueOf(aDouble))).trim());}
                NumberFormat dc = NumberFormat.getCurrencyInstance(Locale.US);
                dc.setMaximumFractionDigits(0);
                if(gmedida.getText().length()!=0){
                double pre1 = Double.parseDouble(gprecio.getText().toString());
                //double val1 = Double.parseDouble(txsuma.getText().toString());
                double val1 = Double.parseDouble(gmedida.getText().toString());
                double valfinal = pre1 * val1;

                Double Valorcompartir=valfinal;
                //txvalor.setText(String.valueOf(valfinal));

                shareViewModel2.setTxValor(String.valueOf(Valorcompartir));}
                }






        });
       noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adpt.setNotes(notes);
                adpt.notifyDataSetChanged();

                //if (notes!=null){
                allnotes3=notes;








            }
        });
        noteViewModel.getCalcPromed().observe(this, new Observer<Double>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(Double aDouble) {


                if (aDouble != null) {
                   /* double doubleValue = 0;
                    try {
                        doubleValue = Double.parseDouble(aDouble.toString().replace(',', '.'));
                    } catch (NumberFormatException e) {*/
                    //Error
                    DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
                    symbols.setDecimalSeparator('.');

                    DecimalFormat format = new DecimalFormat();
                    format.setDecimalFormatSymbols(symbols);
                    format.setMaximumFractionDigits(1);


                 //  NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);


                    // DecimalFormat df = new DecimalFormat("##.#");
                    ghora.setText(Double.toString(Double.parseDouble(format.format(aDouble))).trim());}
                   // ghora.setText(Double.toString(Double.parseDouble(String.valueOf(number))).trim());


            }
        });
        noteViewModel.getCalcCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                gcantidad.setText(String.valueOf(integer));
            }
        });




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
                    Toast.makeText(this, "Permiso negado", Toast.LENGTH_SHORT).show();
                }


            }


        }

    }






    public void recibirbun(){
        Intent intent = getIntent();

        diaspag= getIntent().getExtras().getString("Diasdepago");
        diasp=getIntent().getExtras().getInt("Diasdepagoint");

       // gopcion.setText(diaspag);
        gprecio.setText(intent.getExtras().getString("Precio"));
        gnombre.setText(intent.getExtras().getString("Cliente"));
        gproducto.setText(intent.getExtras().getString("Producto"));
        textoprepa.setText(intent.getExtras().getString("radiobuton"));
        if (textoprepa.getText().toString().equals("Venta")) {
            card_prepa.setCardBackgroundColor(getResources().getColor(R.color.colorDarkBlue));
        }
        else if(textoprepa.getText().toString().equals("Compra")){
            //card_operacion.setBackgroundColor(getResources().getColor(R.color.colorDarkRed));
            card_prepa.setCardBackgroundColor(getResources().getColor(R.color.colorDarkRed));

        }else if(textoprepa.getText().toString().equals("Borrador")){
            card_prepa.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    private void savepdf() throws DocumentException {
        BaseFont baseFont=null;
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
        Font regularReportA = new Font(baseFont, 25, Font.BOLD, new BaseColor(114,133,165));
        Font regularReport2 = new Font(baseFont, 16, Font.BOLD, BaseColor.BLACK);
        Font regularName = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.BLACK);
        Font regularAddress = new Font(Font.FontFamily.HELVETICA, 15, Font.NORMAL, BaseColor.BLACK);
        Font regularSub = new Font(Font.FontFamily.COURIER, 6, Font.ITALIC, BaseColor.RED);
        Font regularTotal = new Font(baseFont, 14, Font.ITALIC, BaseColor.BLACK);
        Font regularTotalBold = new Font(baseFont, 16, Font.BOLD, BaseColor.BLACK);
        Font regularSub2 = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.GRAY);
        //Font footerN = new Font(baseFont, 15,Font.BOLD,printAccent);
        Font footerE = new Font(baseFont, 12, Font.NORMAL, BaseColor.BLACK);
        Image image;



//        ListaCuero=Listadobles;


        file = new File(mFilepath);

        try {



            PdfWriter.getInstance(mDoc, new FileOutputStream(mFilepath));
            PdfWriter.getInstance(mDoc, outputStream);





            mDoc.open();

             productoventas2=gproducto.getText().toString();
            unidadesventas2=gcantidad.getText().toString();
            precioventas2=gprecio.getText().toString();
            medidaventas2=gcantidad.getText().toString();
            valorventas2=txtotalbottom.getText().toString();
             fechaventas2=gdate.getText().toString();

            nombreventas2=gnombre.getText().toString();
            estadoventas2=textoprepa.getText().toString();


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
                PdfPTable pdfPtableimage = new PdfPTable(2);
                pdfPtableimage.setWidthPercentage(100);
                pdfPtableimage.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell imagecell = new PdfPCell();
                PdfPCell factucell = new PdfPCell();
                imagecell.addElement(image);
                imagecell.setBorderColor(new BaseColor(255,255,255));
                factucell.addElement(factu);
                factucell.setBorderColor(new BaseColor(255,255,255));
                pdfPtableimage.addCell(imagecell);
                pdfPtableimage.addCell(factucell);
                pdfPtableimage.setSpacingAfter(40);
                mDoc.add(pdfPtableimage);
                System.setProperty("http.agent", "Chrome");
            }else if(radiotipo.equals("Cuadrado")&&estadoventas2.equals("Venta")) {
                String imFile = "/storage/emulated/0/PyMESoft/Logotipo/logopng";
                image = Image.getInstance(imFile);
                image.scaleAbsolute(100f,100f);
                image.setAlignment(Image.LEFT);
                Paragraph factu=new Paragraph("Factura de Venta",regularReportA);
                factu.setAlignment(Element.ALIGN_RIGHT);
                PdfPTable pdfPtableimage = new PdfPTable(2);
                pdfPtableimage.setWidthPercentage(100);
                pdfPtableimage.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell imagecell = new PdfPCell();
                PdfPCell factucell = new PdfPCell();
                imagecell.addElement(image);
                imagecell.setBorderColor(new BaseColor(255,255,255));
                factucell.addElement(factu);
                factucell.setBorderColor(new BaseColor(255,255,255));
                pdfPtableimage.addCell(imagecell);
                pdfPtableimage.addCell(factucell);
                pdfPtableimage.setSpacingAfter(40);
                mDoc.add(pdfPtableimage);
                System.setProperty("http.agent", "Chrome");
            }
            if (radiotipo.equals("Rectangular")&&estadoventas2.equals("Compra")){
                String imFile = "/storage/emulated/0/PyMESoft/Logotipo/logopng";
                image = Image.getInstance(imFile);
                image.scaleAbsolute(160f,70f);
                image.setAlignment(Image.LEFT);
                Paragraph factu=new Paragraph("Factura de Compra",regularReportA);
                factu.setAlignment(Element.ALIGN_RIGHT);
                PdfPTable pdfPtableimage = new PdfPTable(2);
                pdfPtableimage.setWidthPercentage(100);
                pdfPtableimage.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell imagecell = new PdfPCell();
                PdfPCell factucell = new PdfPCell();
                imagecell.addElement(image);
                imagecell.setBorderColor(new BaseColor(255,255,255));
                factucell.addElement(factu);
                factucell.setBorderColor(new BaseColor(255,255,255));
                pdfPtableimage.addCell(imagecell);
                pdfPtableimage.addCell(factucell);
                pdfPtableimage.setSpacingAfter(40);
                mDoc.add(pdfPtableimage);
                System.setProperty("http.agent", "Chrome");
            }else if(radiotipo.equals("Cuadrado")&&estadoventas2.equals("Compra")) {
                String imFile = "/storage/emulated/0/PyMESoft/Logotipo/logopng";
                image = Image.getInstance(imFile);
                image.scaleAbsolute(100f,100f);
                image.setAlignment(Image.LEFT);
                Paragraph factu=new Paragraph("Factura de Compra",regularReportA);
                factu.setAlignment(Element.ALIGN_RIGHT);
                PdfPTable pdfPtableimage = new PdfPTable(2);
                pdfPtableimage.setWidthPercentage(100);
                pdfPtableimage.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell imagecell = new PdfPCell();
                PdfPCell factucell = new PdfPCell();
                imagecell.addElement(image);
                imagecell.setBorderColor(new BaseColor(255,255,255));
                factucell.addElement(factu);
                factucell.setBorderColor(new BaseColor(255,255,255));
                pdfPtableimage.addCell(imagecell);
                pdfPtableimage.addCell(factucell);
                pdfPtableimage.setSpacingAfter(40);
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
                PdfPTable pdfPtableimage = new PdfPTable(2);
                pdfPtableimage.setWidthPercentage(100);
                pdfPtableimage.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell imagecell = new PdfPCell();
                PdfPCell factucell = new PdfPCell();
                imagecell.addElement(image);
                imagecell.setBorderColor(new BaseColor(255,255,255));
                factucell.addElement(factu);
                factucell.setBorderColor(new BaseColor(255,255,255));
                pdfPtableimage.addCell(imagecell);
                pdfPtableimage.addCell(factucell);
                pdfPtableimage.setSpacingAfter(40);
                mDoc.add(pdfPtableimage);
                System.setProperty("http.agent", "Chrome");
            }else if(radiotipo.equals("Cuadrado")&&estadoventas2.equals("Borrador")) {
                String imFile = "/storage/emulated/0/PyMESoft/Logotipo/logopng";
                image = Image.getInstance(imFile);
                image.scaleAbsolute(100f,100f);
                image.setAlignment(Image.LEFT);
                Paragraph factu=new Paragraph("Cotización",regularReportA);
                factu.setAlignment(Element.ALIGN_RIGHT);
                PdfPTable pdfPtableimage = new PdfPTable(2);
                pdfPtableimage.setWidthPercentage(100);
                pdfPtableimage.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell imagecell = new PdfPCell();
                PdfPCell factucell = new PdfPCell();
                imagecell.addElement(image);
                imagecell.setBorderColor(new BaseColor(255,255,255));
                factucell.addElement(factu);
                factucell.setBorderColor(new BaseColor(255,255,255));
                pdfPtableimage.addCell(imagecell);
                pdfPtableimage.addCell(factucell);
                pdfPtableimage.setSpacingAfter(40);
                mDoc.add(pdfPtableimage);
                System.setProperty("http.agent", "Chrome");
            }











        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (BadElementException ex) {
            ex.printStackTrace();
        } catch (DocumentException ex) {
            ex.printStackTrace();
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
            Paragraph vnombre = new Paragraph(nombreventas2);
            nombre1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vnombre.setAlignment(Element.ALIGN_RIGHT);



            Paragraph fecha1 = new Paragraph("Fecha", regularSub2);
            Paragraph vfecha1 = new Paragraph(fechaventas2);

            fecha1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vfecha1.setAlignment(Element.ALIGN_RIGHT);


            Paragraph hora1 = new Paragraph("Hora", regularSub2);
            Paragraph vhora1 = new Paragraph(horareal2);
            hora1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vhora1.setAlignment(Element.ALIGN_RIGHT);



            Paragraph producto1 = new Paragraph("Producto:", regularSub2);
            Paragraph vproducto = new Paragraph(productoventas2);
            producto1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vproducto.setAlignment(Element.ALIGN_RIGHT);

            Paragraph precio1 = new Paragraph("Precio:", regularSub2);
            Paragraph vprecio = new Paragraph(precioventas2);
            precio1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vprecio.setAlignment(Element.ALIGN_RIGHT);

            Paragraph medida1 = new Paragraph("Medida:", regularSub2);
            Paragraph vpmedida = new Paragraph(medidaventas2);
            medida1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vpmedida.setAlignment(Element.ALIGN_RIGHT);




            Paragraph valor1 = new Paragraph("Valor:", regularSub2);
            Paragraph vvalor = new Paragraph(valorventas2);
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


            //cellfecha.setFixedHeight(30);
            //cellhora.setFixedHeight(30);
            //cellfecha.setColspan(2);
            //cellhora.setColspan(2);


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




        } catch (Exception e) {

        }
         try {
            PdfPTable Atable = new PdfPTable(12);
            Atable.setHorizontalAlignment(Element.ALIGN_CENTER);
            Atable.setWidthPercentage(95);
            // ArrayList<Double> ListaMed=new ArrayList<>();



             for (int aw = 0; aw < ListaCuero.size(); aw++) {
                 //PdfReader reader = new PdfReader(outputStream.toByteArray());
                 //PdfStamper stamper = new PdfStamper(reader, outputStream);
                 Paragraph p = new Paragraph();

                 Paragraph q1 = new Paragraph(String.valueOf(aw + 1), regularSub);
                 Paragraph q2 = new Paragraph(String.valueOf(ListaCuero.get(aw)));
                 Chunk gumble = new Chunk(new VerticalPositionMark());

                 p.add(q1);
                 p.add(gumble);
                 p.add(q2);

                 PdfPCell cell = new PdfPCell(p);
                 cell.setPaddingBottom(8);
                 cell.setPaddingTop(5);
                 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                 cell.setBorderColor(BaseColor.LIGHT_GRAY);
                 cell.setFixedHeight(30);

                 Atable.addCell(cell);
                 //stamper.close();
                 //stamper.close();

            }


            Atable.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
            Atable.completeRow();

            mDoc.add(Atable);

           /* Paragraph Terminos = new Paragraph("TÉRMINOS DE PAGO", regularReport2);
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
            mDoc.add(Terminos);
            mDoc.add(pdflinea);
            mDoc.add(Txterminos);*/

            mDoc.close();
            // Toast.makeText(VistaAA.this, mFilename + "guardado" + mFilepath, Toast.LENGTH_SHORT).show();


        } catch (Exception e) {
            Toast.makeText(this, "Fallo conocido", Toast.LENGTH_SHORT).show();
        }


    }
    public class  pdfasynktask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {

            try {
                savepdf();
            } catch (DocumentException e) {
                e.printStackTrace();
            }

// set Fragmentclass Arguments


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Bundle bundle=new Bundle();
            bundle.putByteArray("wpa",outputStream.toByteArray());
            PdfFraagment pdfFraagment=new PdfFraagment();
            pdfFraagment.setArguments(bundle);
            if (pdfFraagment.isVisible()){


            pdfFraagment.createpdf();}
        }

    }






}