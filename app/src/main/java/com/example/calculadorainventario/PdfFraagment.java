package com.example.calculadorainventario;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.itextpdf.text.pdf.qrcode.ByteArray;


import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.Buffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PdfFraagment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PdfFraagment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 111;
    //private static final int REQUEST_CODE_ASK_PERMISSIONS_2 = 112;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Document mDoc = new Document(PageSize.LETTER);
    private PDFView pdfView;
    private File file;
    Uri pdfUri;
    ShareViewModel2 shareViewModel2;
    NoteViewModel noteViewModel;
    FirebaseDatabase mydatabase = FirebaseDatabase.getInstance();
    DatabaseReference myref = mydatabase.getReference("pdfurl");
    ArrayList<Double> ListaCuero;
    java.util.List<Note> allnotes3;
    View PdfFragmentView;
    private File pdfFile;
    private Paragraph paragraph;
    private List lista;
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
   // ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());


    String pattern = "EEEEE MMMMM yyyy HH:mm:ss.SSSZ";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("en", "US"));
    String mFilename = simpleDateFormat.format(System.currentTimeMillis());
    String mFilepath = Environment.getExternalStorageDirectory() + "/" + mFilename + ".pdf";
    String fechaventas2;
    String horaventas2;
    String productoventas2;
    String unidadesventas2;
    String precioventas2;
    String medidaventas2;
    String valorventas2;
    Button btactualizarpdf;
    String nombreventas2;
    //InputStream in = new ByteArrayInputStream(outputStream.toByteArray());
    // ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
    byte[] outputStream2;



    Bundle bundle;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PdfFraagment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PdfFraagment.
     */
    // TODO: Rename and change types and number of parameters
    public static PdfFraagment newInstance(String param1, String param2) {
        PdfFraagment fragment = new PdfFraagment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_pdf_fraagment, container, false);
        PdfFragmentView = inflater.inflate(R.layout.fragment_pdf_fraagment, container, false);
        //Bundle bundle = getIntent().getExtras();
        // calc_fragment fragmento1 = new calc_fragment();
        //SharedPreferences result = getContext().getSharedPreferences("datosguardados", Context.MODE_PRIVATE);
        pdfView = PdfFragmentView.findViewById(R.id.pdfView);
        btactualizarpdf = PdfFragmentView.findViewById(R.id.btactualizarpdf);

        noteViewModel=new ViewModelProvider(this).get(NoteViewModel.class);

        noteViewModel.getAllNotes().observe(getViewLifecycleOwner(), new Observer<java.util.List<Note>>() {
            @Override
            public void onChanged(java.util.List<Note> notes) {
                //adpt.setNotes(notes);
                //adpt.notifyDataSetChanged();

                //if (notes!=null){
                allnotes3=notes;








            }
        });



        shareViewModel2 = new ViewModelProvider((ViewModelStoreOwner) getActivity()).get(ShareViewModel2.class);


        shareViewModel2.getNombrepdf().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String nombrepdf) {
                // nombreventas2 = nombrepdf;

            }
        });
        shareViewModel2.getProductopdf().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String productopdf) {
                //  productoventas2 = productopdf;
            }
        });
        shareViewModel2.getMedidaepdf().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {

            }
        });
        shareViewModel2.getUnidadespdf().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String unidadespdf) {
                unidadesventas2 = unidadespdf;
            }
        });
        shareViewModel2.getValorpdf().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String valorpdf) {
                valorventas2 = valorpdf;
            }
        });

        shareViewModel2.getHorapdf().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String horapdf) {
                // horaventas2 = horapdf;
            }
        });
        shareViewModel2.getPreciopdf().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String preciopdf) {
                precioventas2 = preciopdf;
            }
        });
        shareViewModel2.getFechapdf().observe(getViewLifecycleOwner(), new Observer<ByteArrayOutputStream>() {
            @Override
            public void onChanged(ByteArrayOutputStream byteArrayOutputStream) {

            outputStream2=byteArrayOutputStream.toByteArray();

            }


        });
       /* pdfView.fromBytes(outputStream2)
                //.enableSwipe(true)
                //.swipeHorizontal(false)
                //.enableDoubletap(true)
                //.enableAntialiasing(true)
                //.fitEachPage(true)
                //.spacing(0)
                //.autoSpacing(true)
                //.pageFitPolicy(FitPolicy.BOTH)
                //.pageFling(true)
                //.pageSnap(true)
                .fitEachPage(true)
                .swipeHorizontal(true)
                .pageSnap(true)
                .autoSpacing(true)
                .pageFling(true)


                .load();*/


        // shareViewModel2 = new ViewModelProvider((ViewModelStoreOwner) getActivity()).get(ShareViewModel2.class);
        // shareViewModel2.getPath().observe(getViewLifecycleOwner(), new Observer<File>() {


        //   @Override
        // public void onChanged(File file) {


        // }



        //});


        return PdfFragmentView;
    }

    public class PdfSave extends AsyncTask<String, Void, String> {

        BaseFont baseFont = null;

        @Override
        protected String doInBackground(String... strings) {


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
            Font regularSub = new Font(Font.FontFamily.COURIER, 6, Font.ITALIC, BaseColor.RED);
            Font regularTotal = new Font(baseFont, 14, Font.ITALIC, BaseColor.BLACK);
            Font regularTotalBold = new Font(baseFont, 16, Font.BOLD, BaseColor.BLACK);
            //Font footerN = new Font(baseFont, 15,Font.BOLD,printAccent);
            Font footerE = new Font(baseFont, 12, Font.NORMAL, BaseColor.BLACK);
            //ArrayList<Double> listacuero3 = (ArrayList<Double>) getIntent().getSerializableExtra("WLTP_list");
            //String pattern = "EEEEE MMMMM yyyy HH:mm:ss.SSSZ";
            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("fr", "FR"));
            //String mFilename = simpleDateFormat.format(System.currentTimeMillis());


            //String mFilepath = Environment.getExternalStorageDirectory() + "/" + mFilename + ".pdf";
            pdfFile = new File(mFilepath);
            try {

                //outputStream.reset();
                //PdfWriter.getInstance(mDoc, new FileOutputStream(mFilepath));
                PdfWriter.getInstance(mDoc, outputStream);


                mDoc.open();
                String nombreventas2 = "JoseMiel";
                String fechaventas2 = "Hoy";
                String horaventas2 = "Inmediato";
                String productoventas2 = "Tula";
                String colorventas2 = "Rojo";
                // String unidadesventas2 =
                //String precioventas2 =
                //String medidaventas2 =
                //String valorventas2 = txvalor.getText().toString();


                Chunk nombre1 = new Chunk("Nombre:", regularName);
                Chunk vnombre = new Chunk(nombreventas2, regularAddress);
                Paragraph pnombre = new Paragraph();
                pnombre.add(nombre1);
                pnombre.add(" " + vnombre);

                Paragraph pcompany = new Paragraph("PyMESoft®", regularReport);
                pcompany.setAlignment(Element.ALIGN_RIGHT);


                Chunk fecha1 = new Chunk(fechaventas2, regularTotal);
                Chunk vfecha1 = new Chunk(horaventas2, regularTotal);
                Paragraph pfecha = new Paragraph();
                pfecha.add(fecha1);
                pfecha.add(" " + vfecha1);
                pfecha.setSpacingAfter(20);

                Chunk producto1 = new Chunk("Producto:", regularName);
                Chunk vproducto = new Chunk(productoventas2, regularAddress);
                Paragraph pproducto = new Paragraph();
                pproducto.add(producto1);
                pproducto.add(" " + vproducto);

                Chunk precio1 = new Chunk("Precio:", regularName);
                Chunk vprecio = new Chunk(precioventas2, regularAddress);
                Paragraph pprecio = new Paragraph();
                pprecio.add(precio1);
                pprecio.add(" " + vprecio);

                Chunk medida1 = new Chunk("Medida:", regularName);
                Chunk vpmedida = new Chunk(medidaventas2, regularAddress);
                Paragraph pmedida = new Paragraph();
                pmedida.add(medida1);
                pmedida.add(" " + vpmedida);

                Chunk valor1 = new Chunk("Valor:", regularName);
                Chunk vvalor = new Chunk(valorventas2, regularAddress);
                Paragraph pvalor = new Paragraph();
                pvalor.add(valor1);
                pvalor.add(" " + vvalor);
                pvalor.setSpacingAfter(40);

                Chunk unidades1 = new Chunk("Unidades:", regularName);
                Chunk vunidades = new Chunk(unidadesventas2, regularAddress);
                Paragraph punidades = new Paragraph();
                punidades.add(unidades1);
                punidades.add(" " + vunidades);


                PdfPTable table1 = new PdfPTable(4);
                table1.setHorizontalAlignment(Element.ALIGN_LEFT);
                table1.setWidthPercentage(70);
                PdfPCell cellnombre = new PdfPCell();
                PdfPCell cellnombre2 = new PdfPCell();
                PdfPCell cellfecha = new PdfPCell();
                PdfPCell cellhora = new PdfPCell();
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
                // Toast.makeText(PdfFragmentView.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            try {
                PdfPTable Atable = new PdfPTable(10);
                Atable.setHorizontalAlignment(Element.ALIGN_LEFT);
                Atable.setWidthPercentage(100);

                com.itextpdf.text.List list = new com.itextpdf.text.List();

                // stamper.getWriter();
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

                }

                Atable.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
                Atable.completeRow();

                mDoc.add(Atable);
                mDoc.close();
                // Toast.makeText(VistaAA.this, mFilename + "guardado" + mFilepath, Toast.LENGTH_SHORT).show();


            } catch (Exception e) {
                //Toast.makeText(PdfFragmentView.getContext(), "error", Toast.LENGTH_SHORT).show();
            }


            return "finish";
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                   //  new PdfSave().execute();


                    Toast.makeText(PdfFragmentView.getContext(), "Permiso concedido", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(PdfFragmentView.getContext(), "Permiso negado", Toast.LENGTH_SHORT).show();
                }


                // case REQUEST_CODE_ASK_PERMISSIONS_2:{
                //   if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //     uploadpdf(pdfUri);


                //} else {
                //  Toast.makeText(PdfFragmentView.getContext(), "Permiso negado", Toast.LENGTH_SHORT).show();
            }

        }
    }


    @Override
    public void onResume() {

        super.onResume();
        //new PdfSave().execute();



       // pdfView.fromBytes(outputStream.toByteArray())
         //       .enableSwipe(true)
           //     .swipeHorizontal(false)
             //   .enableDoubletap(true)
               // .enableAntialiasing(true)
                //.load()
        //;




    }

    @Override
    public void onStart() {
        super.onStart();



    }
    public void createpdf(){
      byte[] getoutputstream = getArguments().getByteArray("wpa");


        //outputStream2=getActivity().getIntent().getExtras().getByteArray("wpa");
        pdfView.fromBytes(getoutputstream)
                //.enableSwipe(true)
                //.swipeHorizontal(false)
                //.enableDoubletap(true)
                //.enableAntialiasing(true)
                //.fitEachPage(true)
                //.spacing(0)
                //.autoSpacing(true)
                //.pageFitPolicy(FitPolicy.BOTH)
                //.pageFling(true)
                //.pageSnap(true)


                .load();

    }
    public void savepdf() throws DocumentException {
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


        // Intent intent = getIntent();
        // ListaCuero=(ArrayList<Double>) getIntent().getSerializableExtra("List_lt");
      /*  noteViewModel=new ViewModelProvider(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                ListaPdf2=notes;
                //ListaPdf=new ArrayList<>();
                //ListaPdf.add(ListaPdf2);

            }
        });*/

        // AdaptadorProductoGuardado adpt=new AdaptadorProductoGuardado();
        //adpt.setNotes(allnotes3);



        //allnotes4=allnotes3;

        //ArrayList<Double>Listadoble2=(ArrayList<Double>)Listadobles;
        // ListaPdf=new ArrayList<>();
        // ListaPdf=(ArrayList<Note>)getIntent().getExtras().getSerializable("Lista_5");




        // ArrayList<Double>ListaCuero = (ArrayList<Double>) getIntent().getExtras().getSerializable("Lista_5");





        //List1= (ArrayList<Note>) getIntent().getSerializableExtra("Lista_5");
        //String pattern = "EEEEE MMMMM yyyy HH:mm:ss.SSSZ";
        //SimpleDateFormat simpleDateFormat = new LiSimpleDateFormat(pattern, new Locale("fr", "FR"));
        //String mFilename = simpleDateFormat.format(System.currentTimeMillis());


        //String mFilepath = Environment.getExternalStorageDirectory() + "/" + mFilename + ".pdf";
        file = new File(mFilepath);

       /* try {

            //outputStream.reset();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter.getInstance(mDoc, new FileOutputStream(mFilepath));
            PdfWriter.getInstance(mDoc, outputStream);
            outputStreamnext=outputStream;
            shareViewModel2.setFechapdf(outputStreamnext);



            mDoc.open();
            bundle.putSerializable("Diasdepago2",diaspag);
            bundle.putSerializable("Fecha2",gopcion.getText().toString());
            bundle.putSerializable("Unidades1",gcantidad.getText().toString());
            bundle.putSerializable("Medida1",gmedida.getText().toString());
            bundle.putSerializable("Total1",txtotalbottom.getText().toString());
            bundle.putSerializable("Fecha1",gdate.getText().toString());
            bundle.putSerializable("Hora1",ghora.getText().toString());
            bundle.putSerializable("Precio1",gprecio.getText().toString());
            bundle.putSerializable("Nombre1",gnombre.getText().toString());
            bundle.putSerializable("Producto1",gproducto.getText().toString();
            bundle.putSerializable("Estado1",textoprepa.getText().toString());
            bundle.putSerializable("Lista5", Lista78);

            //Intent intent2 = getIntent();
            //horaventas2=ghora2.getText().toString();
            productoventas2=gproducto.getText().toString();
            unidadesventas2=gcantidad.getText().toString();
            precioventas2=gprecio.getText().toString();
            medidaventas2=gcantidad.getText().toString();
            valorventas2=txtotalbottom.getText().toString();
            fechaventas2=gdate.getText().toString();
            //Button btactualizarpdf;
            nombreventas2=gnombre.getText().toString();
            estadoventas2=textoprepa.getText().toString();
            //horareal2=ghora22.getText().toString();

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


            // mDoc.add(pcompany);
            mDoc.add(pdatosgenerales);
            mDoc.add(pdfPtablea);

            //mDoc.add(pfecha);
            //mDoc.add(pnombre);
            //mDoc.add(pproducto);
            //mDoc.add(punidades);
            //mDoc.add(pmedida);
            //mDoc.add(pprecio);
            //mDoc.add(pvalor);

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



            // Toast.makeText(VistaAA.this, mFilename + "guardado" + mFilepath, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            // Toast.makeText(PdfFragmentView.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }*/
        try {

            PdfWriter.getInstance(mDoc, new FileOutputStream(mFilepath));
            PdfWriter.getInstance(mDoc, outputStream);
            mDoc.open();
            PdfPTable Atable = new PdfPTable(10);
            Atable.setHorizontalAlignment(Element.ALIGN_LEFT);
            Atable.setWidthPercentage(100);




            for (int aw = 0; aw < allnotes3.size(); aw++) {
                //adpt.notifyDataSetChanged();

                Paragraph p = new Paragraph();

                Paragraph q1 = new Paragraph(String.valueOf(aw + 1),regularSub );
                // Paragraph q2 = new Paragraph(String.valueOf(Listadobles.get(aw)),regularTotal);
                Paragraph q2 = new Paragraph(String.valueOf(allnotes3.get(aw).getValor_Medida()));
                // Paragraph q2 = new Paragraph(String.valueOf(



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
            mDoc.add(Txterminos);

            mDoc.close();
            // Toast.makeText(VistaAA.this, mFilename + "guardado" + mFilepath, Toast.LENGTH_SHORT).show();*/

            mDoc.close();
        } catch (Exception e) {
            Toast.makeText(PdfFragmentView.getContext(),"Fallo conocido", Toast.LENGTH_SHORT).show();
        }


    }





}
