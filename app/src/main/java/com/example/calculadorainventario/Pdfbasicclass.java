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
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
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

public class Pdfbasicclass extends Activity {

    BaseFont baseFont=null;


    PdfPTable tableFooter;
    File file2;


    String Invoice,Receipt,Quote,Days,Terms,Customer,Product,Date,Due_Date,Total,Subtotal,Info_Fac,List_Products,
            Quantity,Price,Tax,Email,Address,City,Phone;


    StorageReference storageReference;


    PdfWriter writer1, writer2;

    FirebaseDatabase mydatabase = FirebaseDatabase.getInstance();

    //ShareViewModel2 shareViewModel2;
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    //String fechaventas2;
    Document mDoc = new Document(PageSize.LETTER,36,36,53,56);



    String pattern = "EEEEE MMMMM yyyy HH:mm:ss.SSSZ";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("en", "US"));
    String mFilename = simpleDateFormat.format(System.currentTimeMillis());
    private static Context mContext;




    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat hora1 = new SimpleDateFormat("HH:mm:ss");
    String hora2 = hora1.format(calendar.getTime());
    String mFilepath = Environment.getExternalStorageDirectory() +   File.separator+ "PyMESoft"+
            File.separator+"invoices"+File.separator +mFilename.toString().replaceAll(":",".");
    File filepath2;



    // byte[] outputStream = new ByteArrayOutputStream();
    byte[] outputStream2;
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 111;
    private static final int REQUEST_CODE_ASK_PERMISSIONS_2 = 112;
    // InputStream in = new ByteArrayInputStream(outputStream2.toByteArray());

    // Bundle bundle;





    public void createpdf(Context context, String productoventas2, String precioventas2, String valorventas2, Double valorbr,
                          Double valorneto, String fechaventas2, String estadoventas2, String diaspago,
                          String Fecha2, String nombreventas2, ArrayList<String> Lista7, ArrayList<String> List1,
                          ArrayList<Double> List2, ArrayList<Double> List3, ArrayList<Double> List4, ByteArrayOutputStream outputStream,

                          String mFilepath, Double ValorDesc,Double ValorImp,Double ValorImp2,Double DiscountValue, Double TaxValue,
                          Double TaxValue2, String pdfphone, String pdfemail, String pdfaddress, String pdfcity)throws DocumentException{
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
//        pdfviewer2=new pdfviewer2();
//
//        context=pdfviewer2.getcontext(context);
        Invoice=context.getResources().getString(R.string.Invoice);
        Receipt=context.getResources().getString(R.string.Receipts);
        Quote=context.getResources().getString(R.string.Quote);
        Days=context.getResources().getString(R.string.Days);
        Terms=context.getResources().getString(R.string.Payment_Term);
        Customer=context.getResources().getString(R.string.Costumers);
        Product=context.getResources().getString(R.string.Product_Name);
        Date=context.getResources().getString(R.string.Date);
        Due_Date=context.getResources().getString(R.string.Due_Date);
        Total=context.getResources().getString(R.string.Total);
        Subtotal=context.getResources().getString(R.string.Subtotal);
        Info_Fac=context.getResources().getString(R.string.Invoice_info);
        List_Products=context.getResources().getString(R.string.Doc_list);
        Quantity = context.getResources().getString(R.string.Quantity);
        Price=context.getResources().getString(R.string.Price);
        Tax=context.getResources().getString(R.string.Tax);
        Email=context.getResources().getString(R.string.Email);
        Phone=context.getResources().getString(R.string.Mobile_phone);
        Address=context.getResources().getString(R.string.Address);
        City=context.getResources().getString(R.string.City);



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
        Font ColorResalte=new Font(baseFont,12,Font.BOLD,new BaseColor(3,191,165));
        //Font footerN = new Font(baseFont, 15,Font.BOLD,printAccent);

        Font footerE = new Font(baseFont, 8, Font.NORMAL, BaseColor.BLACK);
        Image image;


        File file = new File(mFilepath);
        if (!file.exists()) {
            file.mkdirs();

        }
        filepath2= new File(file.getAbsolutePath());
        filepath2.mkdir();
        File file2 = new File(filepath2, "inv-" + nombreventas2 + ".pdf");
        setfile2(file2);


        try {

            // PdfWriter writer1, writer2;

            writer2= PdfWriter.getInstance(mDoc, new FileOutputStream(file2));
            //PdfWriter.getInstance(mDoc, new FileOutputStream(mFilepath));
            writer1= PdfWriter.getInstance(mDoc, outputStream);

            mDoc.open();



            tableFooter = new PdfPTable(1);


            tableFooter.setTotalWidth(523);
            tableFooter.setWidthPercentage(100);
            tableFooter.setHorizontalAlignment(Element.ALIGN_CENTER);
            String empresa=Constants.getSP(context).getCompanyname();
            String direccion=Constants.getSP(context).getAdressname();
            String tel=Constants.getSP(context).getCompanyphone();
            String email=Constants.getSP(context).getCOMPANYEMAIL();
            Paragraph p1=new Paragraph();
            Phrase q1= new Phrase(empresa+" | ", footerE) ;
            Phrase q2 = new Phrase(direccion+ " | ",footerE);
            Phrase q3= new Phrase(tel+" | ",footerE);


            Phrase q4=new Phrase("Powered by",footerE);
            Phrase q5= new Phrase(email+" | ",footerE);
            Phrase q6=new Phrase(" PyMESoft",ColorResalte);




            Chunk gumble = new Chunk(new VerticalPositionMark());
            Chunk gumble2 = new Chunk(new VerticalPositionMark());
            Chunk gumble3 = new Chunk(new VerticalPositionMark());
            p1.add(q1);

            p1.add(q2);
            p1.add(q3);
            p1.add(q5);

            p1.add(q4);
            p1.add(q6);




            PdfPCell footerName = new PdfPCell();
            footerName.addElement(p1);
            footerName.setHorizontalAlignment(Element.ALIGN_CENTER);

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



//            tableFooter.setWidthPercentage(100);
//            tableFooter.addCell(preBorderBlue);
            // tableFooter.addCell(footerEmail);
            tableFooter.addCell(footerName);






            HeaderFooter event = new HeaderFooter(tableFooter);
            writer1.setPageEvent(event);
            writer2.setPageEvent(event);






            // mDoc.setMargins(36, 36, 55, 150);



//
            SharedPreferences logopreference2 =context.getSharedPreferences
                    ("logopref2", context.MODE_PRIVATE);

            String radiotipo= logopreference2.getString("logocheck2","no hay datos");
            PdfPCell factucell = new PdfPCell();
            PdfPCell imagecell = new PdfPCell();

            if (radiotipo.equals("Rectangular")&&estadoventas2.equals(context.getResources().getString(R.string.Sales))){
//
                TopSide(regularReportA,regularTotalBold,diaspago,Invoice,Days,factucell);

                imagecell.addElement(LogoImage(160f,70f));

//
            }else if(radiotipo.equals("Cuadrado")&&estadoventas2.equals(context.getResources().getString(R.string.Sales))) {
                  TopSide(regularReportA,regularTotalBold,diaspago,Invoice,Days,factucell);

                imagecell.addElement(LogoImage(100f,100f));
            }
            if (radiotipo.equals("Rectangular")&&estadoventas2.equals(context.getResources().getString(R.string.Receipts))){
                TopSide(regularReportA,regularTotalBold,diaspago,Receipt,Days,factucell);

                imagecell.addElement(LogoImage(160f,70f));

            }else if(radiotipo.equals("Cuadrado")&&estadoventas2.equals(context.getResources().getString(R.string.Receipts))) {
                TopSide(regularReportA,regularTotalBold,diaspago,Receipt,Days,factucell);

                imagecell.addElement(LogoImage(100f,100f));
            }
            if (radiotipo.equals("Rectangular")&&estadoventas2.equals(context.getResources().getString(R.string.Draft))){
                TopSide(regularReportA,regularTotalBold,diaspago,Quote,Days,factucell);

                imagecell.addElement(LogoImage(160f,70f));
            }else if(radiotipo.equals("Cuadrado")&&estadoventas2.equals(context.getResources().getString(R.string.Draft))) {
                TopSide(regularReportA,regularTotalBold,diaspago,Quote,Days,factucell);

                imagecell.addElement(LogoImage(100f,100f));

            }
            imagecell.setBorderColor(new BaseColor(255,255,255));

            PdfPTable pdfPtableimage = new PdfPTable(2);
            pdfPtableimage.setWidthPercentage(100);
            pdfPtableimage.setHorizontalAlignment(Element.ALIGN_CENTER);





            pdfPtableimage.addCell(imagecell);
            pdfPtableimage.addCell(factucell);

            pdfPtableimage.setSpacingAfter(25);
            mDoc.add(pdfPtableimage);
            System.setProperty("http.agent", "Chrome");











        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Toast.makeText(context, "Fallo c1", Toast.LENGTH_SHORT).show();
            Log.d("err5",String.valueOf(ex));
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            Toast.makeText(context, "Fallo c2", Toast.LENGTH_SHORT).show();
            Log.d("err4",String.valueOf(ex));
        } catch (IOException ex) {
            ex.printStackTrace();
            Toast.makeText(context, "Fallo c3", Toast.LENGTH_SHORT).show();
            Log.d("err3",String.valueOf(ex));
        } catch (BadElementException ex) {

            ex.printStackTrace();
            Toast.makeText(context, "Fallo c4", Toast.LENGTH_SHORT).show();
            Log.d("err2",String.valueOf(ex));
        } catch (DocumentException ex) {
            ex.printStackTrace();
            Toast.makeText(context, "Fallo c5", Toast.LENGTH_SHORT).show();
            Log.d("err1",String.valueOf(ex));
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

            Paragraph pdfemail1 = new Paragraph(Email+":", regularSub2);
            Paragraph vpdfemail = new Paragraph(pdfemail,regularAddress);
            pdfemail1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vpdfemail.setAlignment(Element.ALIGN_CENTER|Element.ALIGN_MIDDLE);

            Paragraph pdfaddress1 = new Paragraph(Address+":", regularSub2);
            Paragraph vpdfaddress = new Paragraph(pdfaddress,regularAddress);
            pdfaddress1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vpdfaddress.setAlignment(Element.ALIGN_LEFT);
            Paragraph pdfcity1 = new Paragraph(City+":", regularSub2);
            Paragraph vpdfcity = new Paragraph(pdfcity,regularAddress);
            pdfcity1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vpdfcity.setAlignment(Element.ALIGN_LEFT|Element.ALIGN_MIDDLE);
            Paragraph pdfphone1 = new Paragraph(Phone+":", regularSub2);
            Paragraph vpdfphone = new Paragraph(pdfphone,regularAddress);
            pdfphone1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vpdfphone.setAlignment(Element.ALIGN_LEFT|Element.ALIGN_MIDDLE);




            Paragraph unidades1 = new Paragraph("Unidades - Promedio:", regularSub2);
//            Paragraph vunidades = new Paragraph(unidadesventas2+"    "+"|"+"    "+horaventas2);
            unidades1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
//            vunidades.setAlignment(Element.ALIGN_RIGHT);


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
            PdfPCell duedate = new PdfPCell();
            PdfPCell duedate2 = new PdfPCell();
            PdfPCell cellphone = new PdfPCell();
            PdfPCell cellphone2 = new PdfPCell();
            PdfPCell cellemail = new PdfPCell();
            PdfPCell cellemail2 = new PdfPCell();
            PdfPCell celladdress = new PdfPCell();
            PdfPCell celladdress2 = new PdfPCell();
            PdfPCell cellcity = new PdfPCell();
            PdfPCell cellcity2 = new PdfPCell();

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




            // Toast.makeText(VistaAA.context, mFilename + "guardado" + mFilepath, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context, "Fallo b", Toast.LENGTH_SHORT).show();
        }
        try {
            PdfPTable Atable = new PdfPTable(5);
//
            Atable.setTotalWidth(new float[] { 27, 10,10,53,10 });
            Atable.setHorizontalAlignment(Element.ALIGN_LEFT);
            Atable.setWidthPercentage(100);
            Atable.setSpacingAfter(10);
            PdfPTable Atable2 = new PdfPTable(2);
            Atable2.setTotalWidth(new float[]{50,50});
            Atable2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            Atable2.setWidthPercentage(40);
            Atable2.setSpacingAfter(20);
//            PdfPTable Atable3 = new PdfPTable(1);
//            Atable3.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            Atable3.setWidthPercentage(21);

            Paragraph Product_name=new Paragraph(Product,regularTotalBold);
            Product_name.setAlignment(Element.ALIGN_LEFT);
            Paragraph Quantity=new Paragraph(context.getResources().getString(R.string.Quantity),regularTotalBold);
            Quantity.setAlignment(Element.ALIGN_RIGHT);
            Paragraph Price=new Paragraph(context.getResources().getString(R.string.Price),regularTotalBold);
            Price.setAlignment(Element.ALIGN_RIGHT);

            Paragraph Result=new Paragraph(Subtotal,regularTotalBold);
            Result.setAlignment(Element.ALIGN_RIGHT);

//            Paragraph Result=new Paragraph(Subtotal,regularTotalBold);
//            Result.setAlignment(Element.ALIGN_RIGHT);

            Paragraph Tax=new Paragraph(context.getResources().getString(R.string.Description),regularTotalBold);
            Tax.setAlignment(Element.ALIGN_CENTER);
            Paragraph Netvalue=new Paragraph(Total,regularTotalBold);
            Netvalue.setAlignment(Element.ALIGN_RIGHT);

            /////se crean los párrafos de la parte baja del total////
            Paragraph Total=new Paragraph(context.getResources().getString(R.string.Total),regularTotalBold);
            Paragraph Psubtotal=new Paragraph(context.getResources().getString(R.string.Subtotal),regularTotalBold);
            Paragraph Pdiscount=new Paragraph(context.getResources().getString(R.string.Discount)+" "+"("+DiscountValue+")"+"%",regularTotalBold);
            Paragraph Ptx1=new Paragraph(context.getResources().getString(R.string.Tax)+" "+"("+TaxValue+"%"+")",regularTotalBold);
            Paragraph Ptx2=new Paragraph(context.getResources().getString(R.string.Tax2)+" "+"("+TaxValue2+"%"+")",regularTotalBold);
            String Valorfooter=valorventas2;


            DecimalFormat formatter3 = new DecimalFormat("###,###,##0");
            Paragraph Totalvalue=new Paragraph(String.valueOf(formatter3.format(valorneto))+" USD",regularTotalBold);
            Totalvalue.setAlignment(Element.ALIGN_RIGHT);
            Paragraph SubtotalValue=new Paragraph(String.valueOf(formatter3.format(valorbr))+" USD",regularTotalBold);
            SubtotalValue.setAlignment(Element.ALIGN_RIGHT);
            Paragraph DiscountValue2=new Paragraph(String.valueOf(formatter3.format(ValorDesc))+" USD",regularTotalBold);
            DiscountValue2.setAlignment(Element.ALIGN_RIGHT);
            Paragraph Tx1Value=new Paragraph(String.valueOf(formatter3.format(ValorImp))+" USD",regularTotalBold);
            Tx1Value.setAlignment(Element.ALIGN_RIGHT);
            Paragraph Tx2Value=new Paragraph(String.valueOf(formatter3.format(ValorImp2))+" USD",regularTotalBold);
            Tx2Value.setAlignment(Element.ALIGN_RIGHT);
            Paragraph RELLENO=new Paragraph(String.valueOf(""));
            RELLENO.setAlignment(Element.ALIGN_RIGHT);
////////////////////////

            PdfPCell cellP = new PdfPCell();
            cellP.setPaddingBottom(8);
            cellP.setPaddingTop(5);
            cellP.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellP.setBorderColor(BaseColor.WHITE);
            cellP.setFixedHeight(25);

            cellP.addElement(Product_name);
            PdfPCell cellQ = new PdfPCell();
            cellQ.setPaddingBottom(8);
            cellQ.setPaddingTop(5);
            cellQ.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellQ.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellQ.setBorderColor(BaseColor.WHITE);
            cellQ.setFixedHeight(25);
            cellQ.addElement(Price);
            PdfPCell cellP2 = new PdfPCell();
            cellP2.setPaddingBottom(8);
            cellP2.setPaddingTop(5);
            cellP2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellP2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellP2.setBorderColor(BaseColor.WHITE);
            cellP2.setFixedHeight(25);
            cellP2.addElement(Quantity);
            PdfPCell cellR = new PdfPCell();
            cellR.setPaddingBottom(8);
            cellR.setPaddingTop(5);
            cellR.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellR.setHorizontalAlignment(Element.ALIGN_RIGHT);

            cellR.setBorderColor(BaseColor.WHITE);
            cellR.setFixedHeight(25);
            cellR.addElement(Result);




            PdfPCell celltax = new PdfPCell();
            celltax.setPaddingBottom(8);
            celltax.setPaddingTop(5);
            celltax.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celltax.setHorizontalAlignment(Element.ALIGN_LEFT);
            celltax.setBorderColor(BaseColor.WHITE);
            celltax.setFixedHeight(25);
            celltax.addElement(Tax);
            PdfPCell celltax2 = new PdfPCell();
            celltax2.setPaddingBottom(8);
            celltax2.setPaddingTop(5);
            celltax2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celltax2.setHorizontalAlignment(Element.ALIGN_LEFT);
            celltax2.setBorderColor(BaseColor.WHITE);
            celltax2.setFixedHeight(25);
            celltax2.addElement(Netvalue);

            PdfPCell cellTotal = new PdfPCell();
            cellresume(cellTotal,Total);

            PdfPCell cellTotalV = new PdfPCell();
            cellresume(cellTotalV,Totalvalue);

            PdfPCell cellSubTotal = new PdfPCell();

            cellresume(cellSubTotal,Psubtotal);



            PdfPCell cellSubTotalV = new PdfPCell();
            cellresume(cellSubTotalV,SubtotalValue);

            PdfPCell cellDiscount = new PdfPCell();
            PdfPCell celdaDiscountT=cellresume(cellDiscount,Pdiscount);
//            celdaDiscountT.setBackgroundColor(new BaseColor(235,236,240));

            PdfPCell cellDiscountV = new PdfPCell();
            PdfPCell celdaDiscountV=cellresume(cellDiscountV,DiscountValue2);

            PdfPCell cellTx1 = new PdfPCell();
            PdfPCell celdaTx1=cellresume(cellTx1,Ptx1);
//            celdaTx1.setBackgroundColor(new BaseColor(235,236,240));

            PdfPCell cellTx1V = new PdfPCell();
            PdfPCell celdaTx1V=cellresume(cellTx1V,Tx1Value);

            PdfPCell cellTx2 = new PdfPCell();
            PdfPCell celdaTx2=cellresume(cellTx2,Ptx2);
//            celdaTx2.setBackgroundColor(new BaseColor(235,236,240));


            PdfPCell cellTx2V = new PdfPCell();

            PdfPCell celdaTx2V=cellresume(cellTx2V,Tx2Value);






            Atable.addCell(cellP);
            Atable.addCell(cellQ);
            Atable.addCell(cellP2);
            Atable.addCell(celltax);
            Atable.addCell(cellR);
//            Atable.addCell(celltax2);
            Atable.setHeaderRows(1);



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




                PdfPCell cell = new PdfPCell(p);
                cell.setPaddingBottom(8);
                cell.setPaddingTop(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderColor(BaseColor.WHITE);
                cell.setFixedHeight(25);
                PdfPCell cell1 = new PdfPCell(p1);
                cell1.setPaddingBottom(8);
                cell1.setPaddingTop(5);
                cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell1.setBorderColor(BaseColor.WHITE);
                cell1.setFixedHeight(25);
                PdfPCell cell2 = new PdfPCell(p2);
                cell2.setPaddingBottom(8);
                cell2.setPaddingTop(5);
                cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

                cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell2.setBorderColor(BaseColor.WHITE);
                cell2.setFixedHeight(25);
                PdfPCell cell3 = new PdfPCell(p3);
                cell3.setPaddingBottom(8);
                cell3.setPaddingTop(5);
                cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell3.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                cell3.setBorderColor(BaseColor.WHITE);
                cell3.setFixedHeight(25);
                PdfPCell cell4 = new PdfPCell(p4);
                cell4.setPaddingBottom(8);
                cell4.setPaddingTop(5);
                cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell4.setBorderColor(BaseColor.WHITE);
                cell4.setFixedHeight(25);
                PdfPCell cell5 = new PdfPCell(p5);
                cell5.setPaddingBottom(8);
                cell5.setPaddingTop(5);
                cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell5.setBorderColor(BaseColor.WHITE);
                cell5.setFixedHeight(25);



                Atable.addCell(cell);

                Atable.addCell(cell1);
                Atable.addCell(cell4);
                Atable.addCell(cell3);
                Atable.addCell(cell2);

//               Atable.addCell(cell5);


                //stamper.close();

            }


            Atable2.addCell(cellSubTotal);
            Atable2.addCell(cellSubTotalV);
            Atable2.addCell(celdaTx1);
            Atable2.addCell(celdaTx1V);
            Atable2.addCell(celdaTx2);
            Atable2.addCell(celdaTx2V);
            Atable2.addCell(celdaDiscountT);
            Atable2.addCell(celdaDiscountV);
            Atable2.addCell(cellTotal);
            Atable2.addCell(cellTotalV);





//
//            Atable.getDefaultCell().setBorderColor(BaseColor.WHITE);
//


            Atable.completeRow();

            mDoc.add(Atable);
            mDoc.add(Atable2);


//            mDoc.close();

        } catch (Exception e) {
//            Toast.makeText(context, "Fallo abc", Toast.LENGTH_SHORT).show();

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
            if(List1.size()>8&&List1.size()<=14){
                mDoc.add(pdfPtablesign);

            }else if(List1.size()<=8){

                pdfPtablesign.writeSelectedRows(0,-1,mDoc.left(mDoc.leftMargin()),pdfPtablesign.getTotalHeight()+ mDoc.bottom(mDoc.bottomMargin()),writer1.getDirectContent());
                pdfPtablesign.writeSelectedRows(0,-1,mDoc.left(mDoc.leftMargin()),pdfPtablesign.getTotalHeight()+ mDoc.bottom(mDoc.bottomMargin()),writer2.getDirectContent());


            }else if(List1.size()>33&&List1.size()<=42){
                mDoc.add(pdfPtablesign);

            }else if(List1.size()>14&&List1.size()<=33){
                pdfPtablesign.writeSelectedRows(0,-1,mDoc.left(mDoc.leftMargin()),pdfPtablesign.getTotalHeight()+ mDoc.bottom(mDoc.bottomMargin()),writer1.getDirectContent());
                pdfPtablesign.writeSelectedRows(0,-1,mDoc.left(mDoc.leftMargin()),pdfPtablesign.getTotalHeight()+ mDoc.bottom(mDoc.bottomMargin()),writer2.getDirectContent());


            }else if(List1.size()>42){
                mDoc.add(pdfPtablesign);

            }else {
                mDoc.add(pdfPtablesign);

            }

//    pdfPtablesign.writeSelectedRows(0,-1,mDoc.left(mDoc.leftMargin()),pdfPtablesign.getTotalHeight()+ mDoc.bottom(mDoc.bottomMargin()),writer1.getDirectContent());
//    pdfPtablesign.writeSelectedRows(0,-1,mDoc.left(mDoc.leftMargin()),pdfPtablesign.getTotalHeight()+ mDoc.bottom(mDoc.bottomMargin()),writer2.getDirectContent());

            mDoc.close();
            // Toast.makeText(VistaAA.context, mFilename + "guardado" + mFilepath, Toast.LENGTH_SHORT).show();


        }catch (Exception e){
            Toast.makeText(context, "Fallo firma y otros", Toast.LENGTH_SHORT).show();



        }




}

    public File getfile2(){
        return file2;}
    public void setfile2(File file2) {
        this.file2 = file2;
    }

    public Image LogoImage(float float1,float float2) throws IOException, BadElementException {
        Image image;
        String imFile = "/storage/emulated/0/PyMESoft/Logotipo/logopng";
        image = Image.getInstance(imFile);
        image.scaleAbsolute(float1,float2);
        image.setAlignment(Image.LEFT);

        return image;

    }

    public void TopSide(Font regularReportA,Font regularTotalBold,String diaspago,String Tipo,String Days,PdfPCell factucell) throws IOException, BadElementException {
        Paragraph factu=new Paragraph(Tipo,regularReportA);
        factu.setAlignment(Element.ALIGN_RIGHT);
        Paragraph Plazo1=new Paragraph(Terms,regularTotalBold);
        Plazo1.setAlignment(Element.ALIGN_RIGHT);
        Paragraph Plazo=new Paragraph(diaspago+" "+Days,regularTotalBold);
        Plazo.setAlignment(Element.ALIGN_RIGHT);
        factucell.addElement(factu);
        factucell.setBorderColor(new BaseColor(255,255,255));
        factucell.addElement(Plazo1);
        factucell.addElement(Plazo);


    }
    public PdfPCell cellresume(PdfPCell cell1,Paragraph p){
//        cell1.setPaddingBottom(8);
//        cell1.setPaddingTop(5);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell1.setBorderColor(BaseColor.WHITE);
        cell1.setFixedHeight(18);
        cell1.addElement(p);
        return cell1;
    }

}

