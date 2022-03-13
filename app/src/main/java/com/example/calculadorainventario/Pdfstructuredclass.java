package com.example.calculadorainventario;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculadorainventario.Adapadores.AdaptadorProductoGuardado;
import com.example.calculadorainventario.Constructores.arrayconstructor;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
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
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Pdfstructuredclass extends Activity {

    private PDFView pdfView;
    ImageView back1;
    private File file;
    pdfviewer3.RoundedBorder roundedBorder;
    PdfPCell cell;

    TextView title6;
    BaseFont baseFont=null;
    Uri pdfUri;
    PdfPTable tableFooter;
    List<Note> Listadobles2;
    ArrayList<arrayconstructor>Listadobles3;
    Map<String,String> RecibirNoteprod;
    ArrayList<Double> ListaCuero;
    ArrayList<Double> listacuero3;
    AdaptadorProductoGuardado adpt1=new AdaptadorProductoGuardado();
    NoteProdViewModel noteProdViewModel;
    DecimalFormat format = new DecimalFormat("###,###,##0");
    String SumaResultado;
    String Invoice,Receipt,Quote,Days,Terms,Customer,Product,Date,Due_Date,Total,Subtotal,Info_Fac,List_Products,
            Quantity,Price,Tax;

    StorageReference storageReference;


    PdfWriter writer1, writer2;

    FirebaseDatabase mydatabase = FirebaseDatabase.getInstance();

    //ShareViewModel2 shareViewModel2;
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    //String fechaventas2;
    Document mDoc = new Document(PageSize.LETTER,36,36,53,56);

    //   String horaventas2;
    String productoventas2;
    //    String unidadesventas2;
    String precioventas2;
    //    String medidaventas2;
    String valorventas2;
    double valorbr;
    double ValorImp;
    double ValorDesc;
    double TaxValue;
    double DiscountValue;
    double valorneto;
    String fechaventas2;
    String estadoventas2;
    //    String horareal2;
    String diaspago,Fecha2;
    DatabaseReference myrootDbaseref5;
    FirebaseStorage mystorage;
    FirebaseAuth mAuth;
    NoteViewModel noteViewModel;
    ArrayList<Note>noteArralist=new ArrayList<>();
    ArrayList<String>List1,Lista7;
    ArrayList<Double>List2,List3,List4;
    byte[] outputstream2;
    //Button btactualizarpdf;
    String nombreventas2;
    String CantProd;
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

    public void createstructuredpdf(Context context, String productoventas2, String precioventas2, String valorventas2, Double valorbr,
                                    Double valorneto, String fechaventas2, String estadoventas2, String diaspago,
                                    String Fecha2, String nombreventas2, ArrayList<String> Lista7, ArrayList<String> List1,
                                    ArrayList<Double> List2, ArrayList<Double> List3, ArrayList<Double> List4, ByteArrayOutputStream outputStream,

                                    String mFilepath)throws DocumentException {

        //        BaseFont baseFont=null;
//        try {
//
//            baseFont = BaseFont.createFont("res/font/montserratregular.ttf", "UTF-8", BaseFont.EMBEDDED);
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(context, "Fallo d1", Toast.LENGTH_SHORT).show();
//        } catch (DocumentException e) {
//            e.printStackTrace();
//            Toast.makeText(context, "Fallo d2", Toast.LENGTH_SHORT).show();
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
            String empresa=Constants.getSP(context).getCompanyname();
            String direccion=Constants.getSP(context).getAdressname();
            String tel=Constants.getSP(context).getCompanyphone();
            String email=Constants.getSP(context).getCOMPANYEMAIL();
            Paragraph p1=new Paragraph();
            Phrase q1= new Phrase(empresa, regularReport) ;
            Phrase q2 = new Phrase(direccion,footerE);
            Phrase q3= new Phrase(tel,footerE);

            Phrase q4=new Phrase("Powered by",footerE);
            Phrase q5= new Phrase(email,footerE);




            Chunk gumble = new Chunk(new VerticalPositionMark());
            Chunk gumble2 = new Chunk(new VerticalPositionMark());
            Chunk gumble3 = new Chunk(new VerticalPositionMark());

            p1.add(q2);
            p1.add(gumble);
            p1.add(q3);
            p1.add(gumble2);
            p1.add(q4);
            p1.add(q5);
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
//            tableFooter.addCell(preBorderBlue);
            // tableFooter.addCell(footerEmail);
            tableFooter.addCell(footerName);






            HeaderFooter event = new HeaderFooter(tableFooter);
            writer1.setPageEvent(event);
            writer2.setPageEvent(event);






            // mDoc.setMargins(36, 36, 55, 150);




            SharedPreferences logopreference2 = context.getSharedPreferences("logopref2", context.MODE_PRIVATE);


            String radiotipo= logopreference2.getString("logocheck2","no hay datos");

            if (radiotipo.equals("Rectangular")&&estadoventas2.equals("1")){
                String imFile = "/storage/emulated/0/PyMESoft/Logotipo/logopng";
                image = Image.getInstance(imFile);
                image.scaleAbsolute(160f,70f);
                image.setAlignment(Image.LEFT);
                Paragraph factu=new Paragraph(Invoice,regularReportA);
                factu.setAlignment(Element.ALIGN_RIGHT);
                Paragraph Plazo1=new Paragraph(Terms,regularTotalBold);
                Paragraph Plazo=new Paragraph(diaspago+" "+Days,regularTotalBold);
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
            }else if(radiotipo.equals("Cuadrado")&&estadoventas2.equals("1")) {
                String imFile = "/storage/emulated/0/PyMESoft/Logotipo/logopng";
                image = Image.getInstance(imFile);
                image.scaleAbsolute(100f,100f);
                image.setAlignment(Image.LEFT);
                Paragraph factu=new Paragraph(Invoice,regularReportA);
                factu.setAlignment(Element.ALIGN_RIGHT);
                Paragraph Plazo1=new Paragraph(Terms,regularTotalBold);
                Plazo1.setAlignment(Element.ALIGN_RIGHT);
                Paragraph Plazo=new Paragraph(diaspago+" "+Days,regularTotalBold);
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
            if (radiotipo.equals("Rectangular")&&estadoventas2.equals("2")){
                String imFile = "/storage/emulated/0/PyMESoft/Logotipo/logopng";
                image = Image.getInstance(imFile);
                image.scaleAbsolute(160f,70f);
                image.setAlignment(Image.LEFT);
                Paragraph factu=new Paragraph(Receipt,regularReportA);
                Paragraph Plazo1=new Paragraph(Terms,regularTotalBold);
                Plazo1.setAlignment(Element.ALIGN_RIGHT);
                Paragraph Plazo=new Paragraph(diaspago+" "+Days,regularTotalBold);
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
                factucell.addElement(Plazo1);
                factucell.addElement(Plazo);





                pdfPtableimage.addCell(imagecell);
                pdfPtableimage.addCell(factucell);

                pdfPtableimage.setSpacingAfter(25);
                mDoc.add(pdfPtableimage);
                System.setProperty("http.agent", "Chrome");
            }else if(radiotipo.equals("Cuadrado")&&estadoventas2.equals("2")) {
                String imFile = "/storage/emulated/0/PyMESoft/Logotipo/logopng";
                image = Image.getInstance(imFile);
                image.scaleAbsolute(100f,100f);
                image.setAlignment(Image.LEFT);
                Paragraph factu=new Paragraph(Receipt,regularReportA);
                factu.setAlignment(Element.ALIGN_RIGHT);
                Paragraph Plazo1=new Paragraph(Terms,regularTotalBold);
                Plazo1.setAlignment(Element.ALIGN_RIGHT);
                Paragraph Plazo=new Paragraph(diaspago+" "+Days,regularTotalBold);
                Plazo.setAlignment(Element.ALIGN_RIGHT);
                PdfPTable pdfPtableimage = new PdfPTable(2);
                pdfPtableimage.setWidthPercentage(100);
                pdfPtableimage.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell imagecell = new PdfPCell();
                PdfPCell factucell = new PdfPCell();
                imagecell.addElement(image);
                imagecell.setBorderColor(new BaseColor(255,255,255));
                factucell.addElement(factu);
                factucell.addElement(Plazo1);

                factucell.setBorderColor(new BaseColor(255,255,255));
                factucell.addElement(Plazo);




                pdfPtableimage.addCell(imagecell);
                pdfPtableimage.addCell(factucell);

                pdfPtableimage.setSpacingAfter(25);
                mDoc.add(pdfPtableimage);
                System.setProperty("http.agent", "Chrome");
            }
            if (radiotipo.equals("Rectangular")&&estadoventas2.equals("3")){
                String imFile = "/storage/emulated/0/PyMESoft/Logotipo/logopng";
                image = Image.getInstance(imFile);
                image.scaleAbsolute(160f,70f);
                image.setAlignment(Image.LEFT);
                Paragraph factu=new Paragraph(Quote,regularReportA);
                factu.setAlignment(Element.ALIGN_RIGHT);
                Paragraph Plazo1=new Paragraph(Terms,regularTotalBold);
                Plazo1.setAlignment(Element.ALIGN_RIGHT);
                Paragraph Plazo=new Paragraph(diaspago+" "+Days,regularTotalBold);
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
            }else if(radiotipo.equals("Cuadrado")&&estadoventas2.equals("3")) {
                String imFile = "/storage/emulated/0/PyMESoft/Logotipo/logopng";
                image = Image.getInstance(imFile);
                image.scaleAbsolute(100f,100f);
                image.setAlignment(Image.LEFT);
                Paragraph factu=new Paragraph(Quote,regularReportA);
                factu.setAlignment(Element.ALIGN_RIGHT);
                Paragraph Plazo1=new Paragraph(Terms,regularTotalBold);
                Plazo1.setAlignment(Element.ALIGN_RIGHT);
                Paragraph Plazo=new Paragraph(diaspago+" "+Days,regularTotalBold);
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

                pdfPtableimage.setSpacingAfter(20);
                mDoc.add(pdfPtableimage);
                System.setProperty("http.agent", "Chrome");
            }











        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Toast.makeText(context, "Fallo c1", Toast.LENGTH_SHORT).show();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            Toast.makeText(context, "Fallo c2", Toast.LENGTH_SHORT).show();
        } catch (IOException ex) {
            ex.printStackTrace();
            Toast.makeText(context, "Fallo c3", Toast.LENGTH_SHORT).show();
        } catch (BadElementException ex) {

            ex.printStackTrace();
            Toast.makeText(context, "Fallo c4", Toast.LENGTH_SHORT).show();
        } catch (DocumentException ex) {
            ex.printStackTrace();
            Toast.makeText(context, "Fallo c5", Toast.LENGTH_SHORT).show();
        }
        try{
            Paragraph pcompany = new Paragraph("PyMESoft®", regularReport);
            pcompany.setAlignment(Element.ALIGN_RIGHT);
            pcompany.setSpacingAfter(40);

            Paragraph pdatosgenerales = new Paragraph(Info_Fac, regularReport2);
            pdatosgenerales.setAlignment(Element.ALIGN_LEFT);
            pdatosgenerales.setSpacingAfter(10);

            Paragraph plista = new Paragraph(List_Products, regularReport2);
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







            Paragraph nombre1 = new Paragraph(Customer+":", regularSub2);
            Paragraph vnombre = new Paragraph(nombreventas2,regularAddress);
            nombre1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vnombre.setAlignment(Element.ALIGN_RIGHT);



            Paragraph fecha1 = new Paragraph(Date+":", regularSub2);
            Paragraph vfecha1 = new Paragraph(fechaventas2,regularAddress);


            Paragraph duefecha = new Paragraph(Due_Date+":", regularSub2);
            Paragraph duevfecha1 = new Paragraph(Fecha2,regularAddress);
            duefecha.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            duevfecha1.setAlignment(Element.ALIGN_RIGHT);


            fecha1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vfecha1.setAlignment(Element.ALIGN_RIGHT);


            Paragraph hora1 = new Paragraph("Hora", regularSub2);
//            Paragraph vhora1 = new Paragraph(horareal2,regularAddress);
            hora1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
//            vhora1.setAlignment(Element.ALIGN_RIGHT);



            Paragraph producto1 = new Paragraph(Product+":", regularSub2);
            Paragraph vproducto = new Paragraph(productoventas2,regularAddress);
            producto1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vproducto.setAlignment(Element.ALIGN_RIGHT);

            Paragraph precio1 = new Paragraph(Price+":", regularSub2);
            Paragraph vprecio = new Paragraph(precioventas2,regularAddress);
            precio1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vprecio.setAlignment(Element.ALIGN_RIGHT);

            Paragraph medida1 = new Paragraph("Medida:", regularSub2);
//            Paragraph vpmedida = new Paragraph(medidaventas2,regularAddress);
            medida1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
//            vpmedida.setAlignment(Element.ALIGN_RIGHT);




            Paragraph valor1 = new Paragraph(Total+":", regularSub2);
            DecimalFormat formatter = new DecimalFormat("###,###,##0");

            Paragraph vvalor = new Paragraph(formatter.format(Double.parseDouble(String.valueOf(valorneto)))+" USD",regularAddress);
            valor1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vvalor.setAlignment(Element.ALIGN_RIGHT);



            Paragraph unidades1 = new Paragraph("Unidades - Promedio:", regularSub2);
//            Paragraph vunidades = new Paragraph(unidadesventas2+"    "+"|"+"    "+horaventas2);
            unidades1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
//            vunidades.setAlignment(Element.ALIGN_RIGHT);




            PdfPTable table1 = new PdfPTable(4);

            table1.setTableEvent(new PdfPTableEvent() {
                @Override
                public void tableLayout(PdfPTable table, float[][] widths, float[] heights, int headerRows, int rowStart, PdfContentByte[] canvases) {
                    PdfContentByte cb = canvases[PdfPTable.LINECANVAS];
                    cb.roundRectangle(
                            widths[0][0],heights[heights.length-1],widths[0][4]-widths[0][0],
                            heights[0]-heights[heights.length-1],4
                    );
                    cb.stroke();
                }
            });

            table1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.setWidthPercentage(50);
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
            PdfPCell duedate = new PdfPCell();
            PdfPCell duedate2 = new PdfPCell();

            cellnombre.addElement(nombre1);

            cellnombre2.addElement(vnombre);
            cellfecha.addElement(fecha1);
            cellfecha2.addElement(vfecha1);
            cellhora.addElement(hora1);
//            cellhora2.addElement(vhora1);
            cellproducto.addElement(producto1);
            cellproducto2.addElement(vproducto);
            cellunidades.addElement(unidades1);
//            cellunidades2.addElement(vunidades);
            cellmedida.addElement(medida1);
//            cellmedida2.addElement(vpmedida);
            cellvalor.addElement(valor1);

            cellvalor2.addElement(vvalor);
            cellprecio.addElement(precio1);
            cellprecio2.addElement(vprecio);
            duedate.addElement(duefecha);
            duedate2.addElement(duevfecha1);

            cellnombre.setUseAscender(true);
            cellnombre.setVerticalAlignment(Element.ALIGN_TOP);
            cellnombre.setBorderColor(BaseColor.WHITE);


            cellnombre2.setBorderColor(BaseColor.WHITE);
            cellnombre2.setUseAscender(true);
            cellnombre2.setVerticalAlignment(Element.ALIGN_CENTER);


            cellfecha.setBorderColor(BaseColor.WHITE);
            cellfecha.setUseAscender(true);
            cellfecha.setVerticalAlignment(Element.ALIGN_TOP);
            duedate.setBorderColor(BaseColor.WHITE);
            duedate.setUseAscender(true);
            duedate.setVerticalAlignment(Element.ALIGN_TOP);


            cellfecha2.setBorderColor(BaseColor.WHITE);
            cellfecha2.setUseAscender(true);
            cellfecha2.setVerticalAlignment(Element.ALIGN_CENTER);
            duedate2.setBorderColor(BaseColor.WHITE);
            duedate2.setUseAscender(true);
            duedate2.setVerticalAlignment(Element.ALIGN_CENTER);
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
            cellvalor2.setBackgroundColor(BaseColor.WHITE);
            cellvalor2.setVerticalAlignment(Element.ALIGN_CENTER);
            cellprecio.setBorderColor(BaseColor.WHITE);
            cellprecio.setUseAscender(true);
            cellprecio.setVerticalAlignment(Element.ALIGN_TOP);
            cellprecio2.setBorderColor(BaseColor.WHITE);
            cellprecio2.setUseAscender(true);
            cellprecio2.setVerticalAlignment(Element.ALIGN_CENTER);





            table1.addCell(cellnombre);
            table1.addCell(cellfecha);
            table1.addCell(duedate);
            table1.addCell(cellvalor);

            table1.addCell(cellnombre2);
            table1.addCell(cellfecha2);
            table1.addCell(duedate2);
            table1.addCell(cellvalor2);
//            table1.addCell(cellhora2);
//            table1.addCell(cellmedida);
//            table1.addCell(cellunidades);
//            table1.addCell(cellprecio);
//            table1.addCell(cellhora);
//
//            table1.addCell(cellmedida2);
//            table1.addCell(cellunidades2);
//            table1.addCell(cellprecio2);




//            mDoc.add(pdatosgenerales);
//            mDoc.add(pdfPtablea);



            table1.setSpacingAfter(20);
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

//            mDoc.add(pdfPtable);
//            mDoc.add(plista) ;
            PdfPTable pdfPtable2 = new PdfPTable(1);
            pdfPtable2.setWidthPercentage(100);
            pdfPtable2.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell cell2 = new PdfPCell();
            cell2.setBackgroundColor(new BaseColor(114,133,165));
            cell2.setFixedHeight(2);
            cell2.setBorderColor(new BaseColor(114,133,165));
            pdfPtable2.addCell(cell2);
            pdfPtable2.setSpacingAfter(5);
//            mDoc.add(pdfPtable2);
//            mDoc.close();




            // Toast.makeText(VistaAA.context, mFilename + "guardado" + mFilepath, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context, "Fallo b", Toast.LENGTH_SHORT).show();
        }
        try {
            final PdfPTable primarytable=new PdfPTable(1);
            primarytable.setWidthPercentage(100);
//            primarytable.setLockedWidth(true);

            PdfPCell nestercell=new  PdfPCell();

            nestercell.setBorder(Rectangle.NO_BORDER);
            nestercell.setFixedHeight(380f);

            nestercell.setCellEvent(new PdfPCellEvent() {
                @Override
                public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
                    PdfContentByte cb = canvases[PdfPTable.BACKGROUNDCANVAS];
                    cb.roundRectangle(
                            position.getLeft() + 1.5f,
                            position.getBottom() + 1.5f,
                            position.getWidth() - 3,
                            position.getHeight() - 3, 4
                    );
                    cb.stroke();

                }
            });
            PdfPCell nestercell2=new PdfPCell();
            nestercell2.setBorder(Rectangle.NO_BORDER);
            nestercell2.setCellEvent(new PdfPCellEvent() {
                @Override
                public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
                    PdfContentByte cb = canvases[PdfPTable.BACKGROUNDCANVAS];

                    cb.roundRectangle(
                            position.getLeft() + 1.5f,
                            position.getBottom() + 1.5f,
                            position.getWidth() - 3,
                            position.getHeight() - 3, 4
                    );
                    cb.stroke();

                }
            });


            final PdfPTable Atable = new PdfPTable(5);
            // Atable.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            Atable.setTotalWidth(new float[] { 27, 10,10,53,10 });

            Atable.setHorizontalAlignment(Element.ALIGN_LEFT);

//            Atable.setTableEvent(new PdfPTableEvent() {
//                @Override
//                public void tableLayout(PdfPTable table, float[][] widths, float[] heights, int headerRows, int rowStart, PdfContentByte[] canvases) {
//                    PdfContentByte cb = canvases[PdfPTable.BACKGROUNDCANVAS];
//
//                    cb.roundRectangle(
//                            widths[0][0],heights[heights.length.],widths[0][5]-widths[0][0],
//                            heights[0]-heights[100-1],4
//                    );
//                   cb.stroke();
////                    cb.roundRectangle(
////                            widths[0][0],heights[heights.length-1],widths[0][5]-widths[0][0],
////                            heights[0]-heights[heights.length-1],10
////                    );
////                    cb.stroke();
//
//
////                    cb.roundRectangle(
////                            position.getLeft() + 1.5f,
////                            position.getBottom() + 1.5f,
////                            position.getWidth() - 3,
////                            position.getHeight() - 3, 4
////                    );
////                    cb.stroke();
//
//                }
//            });
            Atable.setWidthPercentage(100);
            Atable.setSpacingAfter(0);
            PdfPTable Atable2 = new PdfPTable(3);

            Atable2.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            Atable2.setTotalWidth(new float[] { 80, 10,10 });
            Atable2.setWidthPercentage(100);
            Atable2.setSpacingAfter(20);
//            PdfPTable Atable3 = new PdfPTable(1);
//            Atable3.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            Atable3.setWidthPercentage(21);

            Paragraph Product_name=new Paragraph(Product,regularTotalBold);
            Product_name.setAlignment(Element.ALIGN_CENTER);
            Paragraph Quantity=new Paragraph(context.getResources().getString(R.string.Quantity),regularTotalBold);
            Quantity.setAlignment(Element.ALIGN_CENTER);
            Paragraph Price=new Paragraph(context.getResources().getString(R.string.Price),regularTotalBold);
            Price.setAlignment(Element.ALIGN_CENTER);

            Paragraph Result=new Paragraph(Subtotal,regularTotalBold);
            Result.setAlignment(Element.ALIGN_CENTER);

//            Paragraph Result=new Paragraph(Subtotal,regularTotalBold);
//            Result.setAlignment(Element.ALIGN_RIGHT);

            Paragraph Tax=new Paragraph(context.getResources().getString(R.string.Description),regularTotalBold);
            Tax.setAlignment(Element.ALIGN_CENTER);
            Paragraph Netvalue=new Paragraph(Total,regularTotalBold);
            Netvalue.setAlignment(Element.ALIGN_MIDDLE);

            Paragraph Total=new Paragraph(context.getResources().getString(R.string.Total),regularTotalBold);
            Paragraph Psubtotal=new Paragraph(context.getResources().getString(R.string.Subtotal),regularTotalBold);
            String Valorfooter=valorventas2;


            DecimalFormat formatter3 = new DecimalFormat("###,###,##0");
            Paragraph Totalvalue=new Paragraph(String.valueOf(formatter3.format(valorneto))+" USD",regularTotalBold);
            Totalvalue.setAlignment(Element.ALIGN_RIGHT);
            Paragraph SubtotalValue=new Paragraph(String.valueOf(formatter3.format(valorbr))+" USD",regularTotalBold);
            SubtotalValue.setAlignment(Element.ALIGN_RIGHT);
            Paragraph RELLENO=new Paragraph(String.valueOf(""));
            RELLENO.setAlignment(Element.ALIGN_RIGHT);

            PdfPCell cellP = new PdfPCell();
            cellP.setPaddingBottom(8);
//            cellP.setCellEvent(new PdfPCellEvent() {
//                @Override
//                public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
//                    PdfContentByte cb = canvases[PdfPTable.LINECANVAS];
//                    cb.roundRectangle(
//                            position.getLeft() + 1.5f,
//                            position.getBottom() + 1.5f,
//                            position.getWidth() - 3,
//                            position.getHeight() - 3, 4
//                    );
//                    cb.stroke();
//
//                }
//            });
            cellP.setPaddingTop(5);
            cellP.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellP.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellP.setBorder(Rectangle.BOTTOM);
            cellP.setBorderColor(BaseColor.BLACK);
            cellP.setFixedHeight(25);
            cellP.setBackgroundColor(new BaseColor(235,236,240));

            cellP.addElement(Product_name);
            PdfPCell cellQ = new PdfPCell();
            cellQ.setPaddingBottom(8);
            cellQ.setPaddingTop(5);
            cellQ.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellQ.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cellQ.setBorder(Rectangle.BOTTOM);
            cellQ.setBackgroundColor(new BaseColor(235,236,240));
            cellQ.setBorderColor(BaseColor.BLACK);
            cellQ.setFixedHeight(25);
            cellQ.addElement(Price);
            PdfPCell cellP2 = new PdfPCell();
            cellP2.setPaddingBottom(8);
            cellP2.setPaddingTop(5);
            cellP2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellP2.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cellP2.setBorder(Rectangle.BOTTOM);
            cellP2.setBackgroundColor(new BaseColor(235,236,240));
            cellP2.setBorderColor(BaseColor.BLACK);
            cellP2.setFixedHeight(25);
            cellP2.addElement(Quantity);
            PdfPCell cellR = new PdfPCell();
            cellR.setPaddingBottom(8);
            cellR.setPaddingTop(5);
            cellR.setBorder(Rectangle.BOTTOM);
            cellR.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellR.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cellR.setBackgroundColor(new BaseColor(235,236,240));


            cellR.setBorderColor(BaseColor.BLACK);
            cellR.setFixedHeight(25);
            cellR.addElement(Result);
            PdfPCell cellTotal = new PdfPCell();
            cellTotal.setPaddingBottom(8);
            cellTotal.setPaddingTop(5);
            cellTotal.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTotal.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cellTotal.setBorder( Rectangle.BOTTOM|Rectangle.LEFT);
            cellTotal.setBorderColor(BaseColor.DARK_GRAY);
            cellTotal.setFixedHeight(25);
            cellTotal.addElement(Total);
            PdfPCell cellTotalV = new PdfPCell();
            cellTotalV.setPaddingBottom(8);
            cellTotalV.setPaddingTop(5);
            cellTotalV.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTotalV.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cellTotalV.setBorder(Rectangle.BOTTOM);
            cellTotalV.setBorderColor(BaseColor.DARK_GRAY);
            cellTotalV.setFixedHeight(25);
            cellTotalV.addElement(Totalvalue);

            PdfPCell celltax = new PdfPCell();
            celltax.setPaddingBottom(8);
            celltax.setPaddingTop(5);
            celltax.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celltax.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            celltax.setBorder( Rectangle.BOTTOM);
            celltax.setBackgroundColor(new BaseColor(235,236,240));
            celltax.setBorderColor(BaseColor.BLACK);
            celltax.setFixedHeight(25);
            celltax.addElement(Tax);
            PdfPCell celltax2 = new PdfPCell();
            celltax2.setPaddingBottom(8);
            celltax2.setPaddingTop(5);
            celltax2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celltax2.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cellTotal.setBorder( Rectangle.BOTTOM);
            celltax2.setBorderColor(BaseColor.DARK_GRAY);
            celltax2.setFixedHeight(25);
            celltax2.addElement(Netvalue);
            PdfPCell cellSubTotal = new PdfPCell();
            cellSubTotal.setPaddingBottom(8);
            cellSubTotal.setPaddingTop(5);
            cellSubTotal.setBorder(Rectangle.LEFT);
            cellSubTotal.setBorderColor(BaseColor.DARK_GRAY);
            cellSubTotal.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellSubTotal.setHorizontalAlignment(Element.ALIGN_MIDDLE);

            cellSubTotal.setBorderColor(BaseColor.DARK_GRAY);
            cellSubTotal.setFixedHeight(25);
            cellSubTotal.addElement(Psubtotal);
            PdfPCell cellSubTotalV = new PdfPCell();
            cellSubTotalV.setPaddingBottom(8);
            cellSubTotalV.setPaddingTop(5);
            cellSubTotalV.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellSubTotalV.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cellSubTotalV.setBorder( Rectangle.BOTTOM);
            cellSubTotalV.setBorderColor(BaseColor.DARK_GRAY);

            cellSubTotalV.setFixedHeight(25);
            cellSubTotalV.addElement(SubtotalValue);
            PdfPCell cellRELLENO= new PdfPCell();
            cellRELLENO.setPaddingBottom(8);
            cellRELLENO.setPaddingTop(5);
            cellRELLENO.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellRELLENO.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cellSubTotalV.setBorder( Rectangle.NO_BORDER);

            cellRELLENO.setBorderColor(BaseColor.DARK_GRAY);
            cellRELLENO.setFixedHeight(25);
            cellRELLENO.addElement(RELLENO);




            Atable.addCell(cellP);
            Atable.addCell(cellQ);
            Atable.addCell(cellP2);
            Atable.addCell(celltax);
            Atable.addCell(cellR);
//            Atable.addCell(celltax2);
            Atable.setHeaderRows(1);


//            cell.setBackgroundColor(BaseColor.WHITE);
//
            for (int aw = 0; aw <List1.size(); aw++) {
                // for (adpt.setNotes(allnotes3);;) {
                //adpt1.notifyDataSetChanged();

                //ListaMed.add(adpt.getmedida(aw));

                Paragraph p = new Paragraph();
                Paragraph p1=new Paragraph();
                Paragraph p2=new Paragraph();
                Paragraph p3=new Paragraph();
                Paragraph p4=new Paragraph();
                Paragraph p5=new Paragraph();

                Paragraph q1 = new Paragraph(String.valueOf(aw + 1),regularSub );
                Paragraph q2 = new Paragraph(String.valueOf(List1.get(aw)),regularTotal2);
                q2.setAlignment(Element.ALIGN_LEFT);
                Paragraph q3 = new Paragraph(String.valueOf(List2.get(aw)),regularTotal2);
                q3.setAlignment(Element.ALIGN_RIGHT);
                Paragraph q4 = new Paragraph(String.valueOf(List3.get(aw)),regularTotal2);
                q4.setAlignment(Element.ALIGN_RIGHT);
                DecimalFormat formatter2 = new DecimalFormat("###,###,##0");
                formatter2.format(Double.parseDouble(String.valueOf(aw)));
                String par5=  formatter2.format(Double.parseDouble(String.valueOf(List4.get(aw))));
                Paragraph q5 = new Paragraph(par5,regularTotal2);
                q5.setAlignment(Element.ALIGN_RIGHT);
//               String par6=  format.format(Double.parseDouble(String.valueOf(List5.get(aw))));
                String par6=String.valueOf(Lista7.get(aw));
                Paragraph q6 = new Paragraph(par6,regularTotal2);
                q6.setAlignment(Element.ALIGN_LEFT);
//               String par7=String.valueOf(Lista7.get(aw));
//               String par7=  format.format(Double.parseDouble(String.valueOf(List6.get(aw))));
//               Paragraph q7 = new Paragraph(par7,regularTotal2);
//               q5.setAlignment(Element.ALIGN_RIGHT);





                Chunk gumble = new Chunk(new VerticalPositionMark());

                p.add(q2);
//                p.add(gumble);
                p1.add(q3);
                p2.add(q5);
                p3.add(q6);
                p4.add(q4);
//                p5.add(q7);
                p.setAlignment(Element.ALIGN_LEFT);
                p1.setAlignment(Element.ALIGN_RIGHT);
                p2.setAlignment(Element.ALIGN_RIGHT);
                p3.setAlignment(Element.ALIGN_LEFT);
                p4.setAlignment(Element.ALIGN_RIGHT);
                p5.setAlignment(Element.ALIGN_RIGHT);




                cell = new PdfPCell(p);
                cell.setPaddingBottom(8);
                cell.setPaddingTop(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);

//                if(cell.getBackgroundColor().equals(BaseColor.LIGHT_GRAY)){
//                    cell.setBackgroundColor(BaseColor.WHITE);
//
//
//               }else if(cell.getBackgroundColor().equals(BaseColor.WHITE)){
//                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
//                };
                cell.setBorder( Rectangle.RIGHT);

                cell.setBorderColor(BaseColor.WHITE);
                cell.setFixedHeight(25);
                PdfPCell cell1 = new PdfPCell(p1);
                cell1.setPaddingBottom(8);
                cell1.setPaddingTop(5);
                cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell1.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
//
                cell1.setBorderColor(BaseColor.WHITE);

                cell1.setFixedHeight(25);
                PdfPCell cell2 = new PdfPCell(p2);
                cell2.setPaddingBottom(8);
                cell2.setPaddingTop(5);
                cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

                cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell2.setBorder(Rectangle.LEFT |Rectangle.ALIGN_BOTTOM);
//
                cell2.setBorderColor(BaseColor.WHITE);
                cell2.setFixedHeight(25);
                PdfPCell cell3 = new PdfPCell(p3);
                cell3.setPaddingBottom(8);
                cell3.setPaddingTop(5);
                cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell3.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                cell3.setBorder(Rectangle.LEFT | Rectangle.RIGHT);

                cell3.setBorderColor(BaseColor.WHITE);
                cell3.setFixedHeight(25);
                PdfPCell cell4 = new PdfPCell(p4);
                cell4.setPaddingBottom(8);
                cell4.setPaddingTop(5);
                cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell4.setBorder(Rectangle.LEFT | Rectangle.RIGHT);

                cell4.setBorderColor(BaseColor.WHITE);
                cell4.setFixedHeight(25);
                PdfPCell cell5 = new PdfPCell(p5);
                cell5.setPaddingBottom(8);
                cell5.setPaddingTop(5);
                cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell5.setBorder(Rectangle.LEFT | Rectangle.RIGHT);

                cell5.setBorderColor(BaseColor.WHITE);
                cell5.setFixedHeight(25);




                Atable.addCell(cell);

                Atable.addCell(cell1);
                Atable.addCell(cell4);
                Atable.addCell(cell3);
                Atable.addCell(cell2);

                //      Atable.addCell(cell5);

                //stamper.close();

            }



            Atable2.addCell(RELLENO);
            Atable2.addCell(cellSubTotal);
            Atable2.addCell(cellSubTotalV);
            Atable2.addCell(RELLENO);
            Atable2.addCell(cellTotal);



            Atable2.addCell(cellTotalV);


            nestercell.addElement(Atable);
            nestercell2.addElement(Atable2);
//            nestercell.addElement(Atable2);
            primarytable.addCell(nestercell);
            primarytable.addCell(nestercell2);





            //Atable.getDefaultCell().setBorder(Rectangle.NO_BORDER);


            //  Atable.getDefaultCell().setBorderColor(BaseColor.WHITE);


            Atable.completeRow();

//            mDoc.add(Atable);
//           mDoc.add(Atable2);
            mDoc.add(primarytable);

//            mDoc.close();

        } catch (Exception e) {
            Toast.makeText(context, "Fallo abc", Toast.LENGTH_SHORT).show();
            Log.d("fallo", String.valueOf(e));

        }

        try {
            Paragraph Terminos = new Paragraph(Terms, regularReport2);
            Terminos.setAlignment(Element.ALIGN_LEFT);
            Terminos.setSpacingAfter(10);
            Terminos.setSpacingBefore(10);

            Paragraph Txterminos = new Paragraph();
            Txterminos.add("El Cliente se compromete a pagar en un plazo no superior a");
//            if(diaspago.equals("0")){
//                Txterminos.add(" "+"Contado ");}else{
//
//                Txterminos.add(" "+diaspago+ " "+Days);}
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
            pdfPtablesign.setSpacingBefore(40);


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
//            pdfPtablesign.addCell(Nomsigncell);
//            pdfPtablesign.addCell(CCcell);
            //pdfPtablesign.setExtendLastRow(true);
            //pdfPtablesign.setTotalWidth((mDoc.right()-mDoc.left())*pdfPtablesign.getWidthPercentage()/100f);
            //pdfPtablesign.writeSelectedRows(0, -1, mDoc.left(), mDoc.bottom()+pdfPtablesign.getTotalHeight(),writer1.getDirectContent());
            // pdfPtablesign.setSpacingBefore(120);
            //pdfPtablesign.setTotalWidth(mDoc.right(40)
            // - mDoc.left(60));
            //mDoc.add(pdfPtablesign);
            //mDoc.left(mDoc.leftMargin())
            pdfPtablesign.setTotalWidth(90);
//            int intetable1=pdfPtablesign.getRows().size();
//            int numbpage=mDoc.getPageNumber();
//
//
//
//
//
//
//            pdfPtablesign.writeSelectedRows(0, -1,
//                            mDoc.left(420)
//                    ,
//                    pdfPtablesign.getTotalHeight() + mDoc.bottom(mDoc.bottomMargin()),
//                    writer1.getDirectContent());
//            pdfPtablesign.writeSelectedRows(0, -1,
//                    mDoc.left(420)
//                    ,
//                    pdfPtablesign.getTotalHeight() + mDoc.bottom(mDoc.bottomMargin()),
//                    writer2.getDirectContent());
            //////////////////////
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
// if(List1.size()>8&&List1.size()<=14){
//     mDoc.add(pdfPtablesign);
//
// }else if(List1.size()<=8){
//
//     pdfPtablesign.writeSelectedRows(0,-1,mDoc.left(mDoc.leftMargin()),pdfPtablesign.getTotalHeight()+ mDoc.bottom(mDoc.bottomMargin()),writer1.getDirectContent());
//     pdfPtablesign.writeSelectedRows(0,-1,mDoc.left(mDoc.leftMargin()),pdfPtablesign.getTotalHeight()+ mDoc.bottom(mDoc.bottomMargin()),writer2.getDirectContent());
//
//
// }else if(List1.size()>33&&List1.size()<=42){
//        mDoc.add(pdfPtablesign);
//
//    }else if(List1.size()>14&&List1.size()<=33){
//     pdfPtablesign.writeSelectedRows(0,-1,mDoc.left(mDoc.leftMargin()),pdfPtablesign.getTotalHeight()+ mDoc.bottom(mDoc.bottomMargin()),writer1.getDirectContent());
//     pdfPtablesign.writeSelectedRows(0,-1,mDoc.left(mDoc.leftMargin()),pdfPtablesign.getTotalHeight()+ mDoc.bottom(mDoc.bottomMargin()),writer2.getDirectContent());
//
//
// }else if(List1.size()>42){
//     mDoc.add(pdfPtablesign);
//
// }else {
//     mDoc.add(pdfPtablesign);
//
// }

//    pdfPtablesign.writeSelectedRows(0,-1,mDoc.left(mDoc.leftMargin()),pdfPtablesign.getTotalHeight()+ mDoc.bottom(mDoc.bottomMargin()),writer1.getDirectContent());
//    pdfPtablesign.writeSelectedRows(0,-1,mDoc.left(mDoc.leftMargin()),pdfPtablesign.getTotalHeight()+ mDoc.bottom(mDoc.bottomMargin()),writer2.getDirectContent());

            mDoc.close();
            // Toast.makeText(VistaAA.context, mFilename + "guardado" + mFilepath, Toast.LENGTH_SHORT).show();


        }catch (Exception e){
            Toast.makeText(context, "Fallo firma y otros", Toast.LENGTH_SHORT).show();
        }













    }
}
