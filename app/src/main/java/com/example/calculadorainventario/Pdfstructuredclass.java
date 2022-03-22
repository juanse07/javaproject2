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
import com.itextpdf.text.pdf.PdfTemplate;
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



    private File file;

    PdfPCell cell;
    SharedPreferences logopreference2;
    String radiotipo;


    BaseFont baseFont=null;

    PdfPTable tableFooter;

    String Invoice,Receipt,Quote,Days,Terms,Customer,Product,Date,Due_Date,Total,Subtotal,Info_Fac,List_Products,
            Quantity,Price,Tax,Email,Address,City,Phone;




    PdfWriter writer1, writer2;


    Document mDoc = new Document(PageSize.LETTER,36,36,53,56);


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

                                    String mFilepath, Double ValorDesc,Double ValorImp,Double ValorImp2,Double DiscountValue, Double TaxValue,
    Double TaxValue2, String pdfphone, String pdfemail, String pdfaddress, String pdfcity)throws DocumentException {

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
        Font regularReportA = new Font(baseFont, 30, Font.BOLD, new BaseColor(31,31,31));
        Font regularReportB = new Font(baseFont, 25, Font.BOLD, new BaseColor(114,133,165));
        Font regularReport2 = new Font(baseFont, 16, Font.BOLD, BaseColor.BLACK);
        Font regularName = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.BLACK);
        Font regularAddress = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
        Font regularAddress2 = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        Font regularSub = new Font(Font.FontFamily.COURIER, 6, Font.ITALIC, BaseColor.RED);
        Font regularTotal = new Font(Font.FontFamily.HELVETICA, 14, Font.ITALIC, BaseColor.BLACK);
        Font regularTotal2 = new Font(Font.FontFamily.HELVETICA, 11, Font.ITALIC, BaseColor.BLACK);
        Font regularTotalBold = new Font(baseFont, 8, Font.BOLD, new BaseColor(128,128,128));
        Font regularTotalBold2 = new Font(baseFont, 8, Font.BOLD, new BaseColor(128,128,128));
        Font regularSub2 = new Font(Font.FontFamily.HELVETICA, 7, Font.NORMAL, BaseColor.GRAY);
        Font ColorResalte=new Font(baseFont,12,Font.BOLD,new BaseColor(3,191,165));
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



            p1.add(q1);
            p1.add(q2);
            p1.add(q3);
            p1.add(q5);
            p1.add(q4);
            p1.add(q6);




            PdfPCell footerName = new PdfPCell();
            footerName.addElement(p1);
            footerName.setHorizontalAlignment(Element.ALIGN_CENTER);

            footerName.setBorder(Rectangle.NO_BORDER);


            tableFooter.addCell(footerName);


            HeaderFooter event = new HeaderFooter(tableFooter);
            writer1.setPageEvent(event);
            writer2.setPageEvent(event);


            CargarPreferencias(context);

            PdfPCell factucell = new PdfPCell();
            factucell.setBorder(Rectangle.NO_BORDER);
            factucell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            factucell.setVerticalAlignment(Element.ALIGN_TOP);
            PdfPCell imagecell = new PdfPCell();
            imagecell.setBorder(Rectangle.NO_BORDER);
            PdfPCell spacecell=new PdfPCell();
            PdfPTable TableTitulo=new PdfPTable(2);
            PdfPCell Logocell=new PdfPCell();
            PdfPCell izcell=new PdfPCell();


            if (radiotipo.equals("Rectangular")&&estadoventas2.equals(context.getResources().getString(R.string.Sales))){
//
                TopSide(regularReportA,regularTotalBold,diaspago,Invoice,Days,factucell);
                factudatatop(TableTitulo,40,60);

                imagecell.addElement(LogoImage(160f,70f));

//
            }else if(radiotipo.equals("Cuadrado")&&estadoventas2.equals(context.getResources().getString(R.string.Sales))) {
                TopSide(regularReportA,regularTotalBold,diaspago,Invoice,Days,factucell);
                factudatatop(TableTitulo,30,70);

                imagecell.addElement(LogoImage(100f,100f));
            }
            if (radiotipo.equals("Rectangular")&&estadoventas2.equals(context.getResources().getString(R.string.Receipts))){
                TopSide(regularReportA,regularTotalBold,diaspago,Receipt,Days,factucell);
                factudatatop(TableTitulo,40,60);
                imagecell.addElement(LogoImage(160f,70f));

            }else if(radiotipo.equals("Cuadrado")&&estadoventas2.equals(context.getResources().getString(R.string.Receipts))) {
                TopSide(regularReportA,regularTotalBold,diaspago,Receipt,Days,factucell);
                factudatatop(TableTitulo,30,70);
                imagecell.addElement(LogoImage(100f,100f));
            }
            if (radiotipo.equals("Rectangular")&&estadoventas2.equals(context.getResources().getString(R.string.Draft))){
                TopSide(regularReportA,regularTotalBold,diaspago,Quote,Days,factucell);

                imagecell.addElement(LogoImage(160f,70f));
            }else if(radiotipo.equals("Cuadrado")&&estadoventas2.equals(context.getResources().getString(R.string.Draft))) {
                TopSide(regularReportA,regularTotalBold,diaspago,Quote,Days,factucell);
                factudatatop(TableTitulo,30,70);
                imagecell.addElement(LogoImage(100f,100f));

            }
            imagecell.setBorderColor(new BaseColor(255,255,255));




            PdfPTable pdfPtableimage = new PdfPTable(1);
            pdfPtableimage.setWidthPercentage(90);
            pdfPtableimage.setHorizontalAlignment(Element.ALIGN_CENTER|Element.ALIGN_MIDDLE);





//Parrafos Cápsula Alta//

            Paragraph nombre1 = new Paragraph(Customer+":", regularSub2);
            Paragraph vnombre = new Paragraph(nombreventas2,regularAddress);
            nombre1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vnombre.setAlignment(Element.ALIGN_MIDDLE|Element.ALIGN_LEFT);

            Paragraph fecha1 = new Paragraph(Date+":", regularSub2);
            Paragraph vfecha1 = new Paragraph(fechaventas2,regularAddress);

            Paragraph duefecha = new Paragraph(Due_Date+":", regularSub2);
            Paragraph duevfecha1 = new Paragraph(Fecha2,regularAddress);
            duefecha.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            duevfecha1.setAlignment(Element.ALIGN_LEFT|Element.ALIGN_MIDDLE);

            fecha1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vfecha1.setAlignment(Element.ALIGN_LEFT|Element.ALIGN_MIDDLE);

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



            Paragraph valor1 = new Paragraph(Total+":", regularSub2);
            DecimalFormat formatter = new DecimalFormat("###,###,##0");

            Paragraph vvalor = new Paragraph(formatter.format(Double.parseDouble(String.valueOf(valorneto)))+" USD",regularAddress);
            valor1.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);
            vvalor.setAlignment(Element.ALIGN_CENTER|Element.ALIGN_MIDDLE);
////terminan Párrafos cápsula alta/////


            ///Se crean las Tablas y la celda con cornerradius//////

            PdfPTable tableData=new PdfPTable(1);
            PdfPTable tableiz=new PdfPTable(1);

            tableData.setWidthPercentage(60);

            tableData.setHorizontalAlignment(Element.ALIGN_RIGHT);
            PdfPCell nestedData=new PdfPCell();
            nestedData.setBorder(Rectangle.NO_BORDER);


            nestedData.setCellEvent(new PdfPCellEvent() {
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


            PdfPTable table1 = new PdfPTable(4);
            table1.setTotalWidth(new float[] {15,35,15,35});
            table1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.setWidthPercentage(100);
            PdfPTable table2=new PdfPTable(4);
            table2.setHorizontalAlignment(Element.ALIGN_LEFT);
            table2.setTotalWidth(new float[] {15,35,15,35});
            table2.setWidthPercentage(100);
            PdfPTable table3=new PdfPTable(4);
            table3.setHorizontalAlignment(Element.ALIGN_LEFT);
            table3.setTotalWidth(new float[] {15,35,15,35});
            table3.setWidthPercentage(100);
            PdfPTable table4=new PdfPTable(4);
            table4.setTotalWidth(new float[] {15,35,15,35});
            table4.setHorizontalAlignment(Element.ALIGN_LEFT);
            table4.setWidthPercentage(100);

            ////terminan las tablas y la celda con curva/////


            //////se crean Las celdas Cápsula Alta////
            PdfPCell cellnombre = new PdfPCell();
            PdfPCell cellnombre2 = new PdfPCell();
            PdfPCell cellfecha = new PdfPCell();
            PdfPCell cellfecha2 = new PdfPCell();
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


            PdfPCell Celdatituloemail;
            Celdatituloemail=  CustomerDataCell(cellemail,pdfemail1,1,1,BaseColor.DARK_GRAY);
            Celdatituloemail.setVerticalAlignment(Element.ALIGN_TOP);
            Celdatituloemail.setBorder(Rectangle.BOTTOM);
            Celdatituloemail.setFixedHeight(20);

            PdfPCell CeldaValorEmail=  CustomerDataCell(cellemail2,vpdfemail,Element.ALIGN_LEFT,5,BaseColor.DARK_GRAY);
            CeldaValorEmail.setBorder(Rectangle.BOTTOM|Rectangle.RIGHT);
            CeldaValorEmail.setFixedHeight(20);


            PdfPCell PdfceldaTituloPhone=CustomerDataCell(cellphone,pdfphone1,Element.ALIGN_CENTER,Element.ALIGN_TOP,BaseColor.DARK_GRAY);
            PdfceldaTituloPhone.setBorder(Rectangle.BOTTOM);
            PdfceldaTituloPhone.setFixedHeight(20);

            PdfPCell CeldaValorPhone=  CustomerDataCell(cellphone2,vpdfphone,Element.ALIGN_LEFT,5,BaseColor.DARK_GRAY);
            CeldaValorPhone.setBorder(Rectangle.BOTTOM|Rectangle.RIGHT);
            CeldaValorPhone.setFixedHeight(20);

            PdfPCell PdfceldaTituloAddress=CustomerDataCell(celladdress,pdfaddress1,Element.ALIGN_CENTER,Element.ALIGN_TOP,BaseColor.DARK_GRAY);
            PdfceldaTituloAddress.setBorder(Rectangle.BOTTOM);
            PdfceldaTituloAddress.setFixedHeight(20);

            PdfPCell CeldaValorAddress=  CustomerDataCell(celladdress2,vpdfaddress,Element.ALIGN_LEFT,5,BaseColor.DARK_GRAY);
            CeldaValorAddress.setBorder(Rectangle.BOTTOM|Rectangle.RIGHT);
            CeldaValorAddress.setFixedHeight(20);

            PdfPCell PdfceldaTituloCity=CustomerDataCell(cellcity,pdfcity1,Element.ALIGN_CENTER,Element.ALIGN_TOP,BaseColor.DARK_GRAY);
            PdfceldaTituloCity.setBorder(Rectangle.BOTTOM);
            PdfceldaTituloAddress.setFixedHeight(20);

            PdfPCell CeldaValorCity=  CustomerDataCell(cellcity2,vpdfcity,Element.ALIGN_LEFT,5,BaseColor.DARK_GRAY);
            CeldaValorCity.setBorder(Rectangle.BOTTOM|Rectangle.RIGHT);
            CeldaValorAddress.setFixedHeight(20);

            PdfPCell PdfceldaTituloName=CustomerDataCell(cellnombre,nombre1,Element.ALIGN_CENTER,Element.ALIGN_TOP,BaseColor.DARK_GRAY);
            PdfceldaTituloName.setBorder(Rectangle.BOTTOM);
            PdfceldaTituloName.setFixedHeight(20);

            PdfPCell CeldaValorName=  CustomerDataCell(cellnombre2,vnombre,Element.ALIGN_LEFT,5,BaseColor.DARK_GRAY);
            CeldaValorName.setBorder(Rectangle.BOTTOM|Rectangle.RIGHT);
            CeldaValorName.setFixedHeight(20);

            PdfPCell PdfceldaTitulofecha1=CustomerDataCell(cellfecha,fecha1,Element.ALIGN_CENTER,Element.ALIGN_TOP,BaseColor.DARK_GRAY);
            PdfceldaTitulofecha1.setBorder(Rectangle.BOTTOM);
            PdfceldaTitulofecha1.setFixedHeight(20);

            PdfPCell CeldaValorfecha1=  CustomerDataCell(cellfecha2,vfecha1,Element.ALIGN_LEFT,5,BaseColor.DARK_GRAY);
            CeldaValorfecha1.setBorder(Rectangle.BOTTOM|Rectangle.RIGHT);
            CeldaValorfecha1.setFixedHeight(20);
            PdfPCell PdfceldaTituloduefecha=CustomerDataCell(duedate,duefecha,Element.ALIGN_CENTER,Element.ALIGN_TOP,BaseColor.DARK_GRAY);
            PdfceldaTituloduefecha.setBorder(Rectangle.BOTTOM);
            PdfceldaTituloAddress.setFixedHeight(20);

            PdfPCell CeldaValorduefecha=  CustomerDataCell(duedate2,duevfecha1,Element.ALIGN_LEFT,5,BaseColor.DARK_GRAY);
            CeldaValorduefecha.setBorder(Rectangle.BOTTOM|Rectangle.RIGHT);
            CeldaValorduefecha.setFixedHeight(20);
            PdfPCell PdfceldaTituloValue=CustomerDataCell(cellvalor,valor1,Element.ALIGN_CENTER,Element.ALIGN_TOP,BaseColor.DARK_GRAY);
            PdfceldaTituloValue.setBorder(Rectangle.BOTTOM);
            PdfceldaTituloValue.setFixedHeight(20);

            PdfPCell CeldaValorValue=  CustomerDataCell(cellvalor2,vvalor,Element.ALIGN_LEFT,5,BaseColor.DARK_GRAY);
            CeldaValorValue.setBorder(Rectangle.BOTTOM|Rectangle.RIGHT);
            CeldaValorValue.setFixedHeight(20);

//Terminan Las celdas Cápsula Alta////////





/////se anaden las celdas a las tablas////



            table1.addCell(cellnombre);
            table1.addCell(cellnombre2);
            table1.addCell(cellvalor);
            table1.addCell(cellvalor2);
            table2.addCell(cellphone);
            table2.addCell(cellphone2);
            table2.addCell(cellemail);
            table2.addCell(cellemail2);

            table3.addCell(celladdress);
            table3.addCell(celladdress2);
            table3.addCell(cellcity);
            table3.addCell(cellcity2);
            table4.addCell(cellfecha);
            table4.addCell(cellfecha2);
            table4.addCell(duedate);
            table4.addCell(duedate2);

///// termina se anaden las celdas a las tablas ////

/// everything se pone en la celda curva ///

            nestedData.addElement(table1);
            nestedData.addElement(table2);
            nestedData.addElement(table3);
            nestedData.addElement(table4);

//////////////////////////////////////

            ///everything se adjunta como corresponde y se anade al docuento////


            tableData.addCell(nestedData);

            pdfPtableimage.addCell(imagecell);




            Logocell.addElement(pdfPtableimage);
            Logocell.setBorder(Rectangle.NO_BORDER);
            Logocell.setVerticalAlignment(Element.ALIGN_CENTER);
            Logocell.setHorizontalAlignment(Element.ALIGN_CENTER);
            spacecell.setBorder(Rectangle.NO_BORDER);




            izcell.setBorder(Rectangle.NO_BORDER);
            tableiz.setWidthPercentage(100);
            tableiz.addCell(factucell);
            tableiz.addCell(nestedData);
            izcell.addElement(tableiz);


            TableTitulo.addCell(Logocell);
            TableTitulo.addCell(izcell);



           factucell.setBorderColor(BaseColor.WHITE);
           izcell.setBorderColor(BaseColor.WHITE);






            TableTitulo.setSpacingAfter(25);
            mDoc.add(TableTitulo);


            System.setProperty("http.agent", "Chrome");


////////////////////////////////////////////////////////////////////









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

        try {
            ///Se crean las tablas para el contenido central///
            final PdfPTable primarytable=new PdfPTable(1);


            primarytable.setWidthPercentage(100);


            PdfPCell nestercell=new  PdfPCell();

            nestercell.setBorder(Rectangle.NO_BORDER);
            nestercell.setFixedHeight(350f);

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


            Atable.setTotalWidth(new float[] { 27, 10,10,53,10 });

            Atable.setHorizontalAlignment(Element.ALIGN_LEFT);

            Atable.setWidthPercentage(100);
            Atable.setSpacingAfter(2);
            PdfPTable Atable2 = new PdfPTable(3);

            Atable2.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            Atable2.setTotalWidth(new float[] { 70, 15,15 });
            Atable2.setWidthPercentage(100);
            Atable2.setSpacingAfter(0);
////////////////////////////////////////////////////
            ///se crean los párrafos de título/////

            Paragraph Product_name=new Paragraph(Product,regularTotalBold);
            Product_name.setAlignment(Element.ALIGN_CENTER);
            Paragraph Quantity=new Paragraph(context.getResources().getString(R.string.Quantity),regularTotalBold);
            Quantity.setAlignment(Element.ALIGN_CENTER);
            Paragraph Price=new Paragraph(context.getResources().getString(R.string.Price),regularTotalBold);
            Price.setAlignment(Element.ALIGN_CENTER);

            Paragraph Result=new Paragraph(Subtotal,regularTotalBold);
            Result.setAlignment(Element.ALIGN_CENTER);



            Paragraph desc=new Paragraph(context.getResources().getString(R.string.Description),regularTotalBold);
            desc.setAlignment(Element.ALIGN_CENTER);
            Paragraph Netvalue=new Paragraph(Total,regularTotalBold);
            Netvalue.setAlignment(Element.ALIGN_MIDDLE);

            Paragraph Total=new Paragraph(context.getResources().getString(R.string.Total),regularTotalBold);
            Paragraph Psubtotal=new Paragraph(context.getResources().getString(R.string.Subtotal),regularTotalBold);
            Paragraph Pdiscount=new Paragraph(context.getResources().getString(R.string.Discount)+" "+"("+DiscountValue+")"+"%",regularTotalBold);
            Paragraph Ptx1=new Paragraph(context.getResources().getString(R.string.Tax)+" "+"("+TaxValue+"%"+")",regularTotalBold);
            Paragraph Ptx2=new Paragraph(context.getResources().getString(R.string.Tax2)+" "+"("+TaxValue2+"%"+")",regularTotalBold);
            String Valorfooter=valorventas2;

/////se crean los párrafos de la parte baja del total////
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
            PdfPCell celdaProducto= cellLabels(cellP,Product_name);


            PdfPCell cellQ = new PdfPCell();
            PdfPCell celdaPrice= cellLabels(cellQ,Quantity);


            PdfPCell cellP2 = new PdfPCell();
            PdfPCell celdaQuantity= cellLabels(cellP2,Price);

            PdfPCell cellDes = new PdfPCell();
            PdfPCell celdaDescripcion=cellLabels(cellDes,desc);

            PdfPCell cellR = new PdfPCell();
            PdfPCell celdaResult= cellLabels(cellR,Result);

            Atable.addCell(celdaProducto);
            Atable.addCell(celdaQuantity);
            Atable.addCell(celdaPrice);
            Atable.addCell(celdaDescripcion);
            Atable.addCell(celdaResult);

            Atable.setHeaderRows(1);

            ///terminan headerrows/////

            ///empiezan celdas suma resultados////





            PdfPCell cellTotal = new PdfPCell();
            PdfPCell celdaTotalT= cellresumenfinal(cellTotal,Total);
            celdaTotalT.setBackgroundColor(new BaseColor(235,236,240));



            PdfPCell cellTotalV = new PdfPCell();
            PdfPCell celdaTotalV=  cellresumenfinal(cellTotalV,Totalvalue);


            PdfPCell cellDiscount = new PdfPCell();
            PdfPCell celdaDiscountT=cellresumenfinal(cellDiscount,Pdiscount);
            celdaDiscountT.setBackgroundColor(new BaseColor(235,236,240));

            PdfPCell cellDiscountV = new PdfPCell();
            PdfPCell celdaDiscountV=cellresumenfinal(cellDiscountV,DiscountValue2);

            PdfPCell cellTx1 = new PdfPCell();
            PdfPCell celdaTx1=cellresumenfinal(cellTx1,Ptx1);
            celdaTx1.setBackgroundColor(new BaseColor(235,236,240));

            PdfPCell cellTx1V = new PdfPCell();
            PdfPCell celdaTx1V=cellresumenfinal(cellTx1V,Tx1Value);

            PdfPCell cellTx2 = new PdfPCell();
            PdfPCell celdaTx2=cellresumenfinal(cellTx2,Ptx2);
            celdaTx2.setBackgroundColor(new BaseColor(235,236,240));


            PdfPCell cellTx2V = new PdfPCell();

            PdfPCell celdaTx2V=cellresumenfinal(cellTx2V,Tx2Value);

            PdfPCell cellSubTotal = new PdfPCell();
            PdfPCell celdaSubTotal=cellresumenfinal(cellSubTotal,Psubtotal);
            celdaSubTotal.setBackgroundColor(new BaseColor(235,236,240));

            PdfPCell cellSubTotalV = new PdfPCell();
            PdfPCell celdaSubTotalV=cellresumenfinal(cellSubTotalV,SubtotalValue);

            PdfPCell cellRELLENO= new PdfPCell();
            cellRELLENO.setPaddingBottom(2);
            cellRELLENO.setPaddingTop(2);
            cellRELLENO.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellRELLENO.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cellRELLENO.setBorder(Rectangle.NO_BORDER);
            cellRELLENO.setFixedHeight(18);
            cellRELLENO.addElement(RELLENO);
            /////////////////////////////////////////////////////////







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

                String par6=String.valueOf(Lista7.get(aw));
                Paragraph q6 = new Paragraph(par6,regularTotal2);
                q6.setAlignment(Element.ALIGN_LEFT);







                p.add(q2);
//
                p1.add(q3);
                p2.add(q5);
                p3.add(q6);
                p4.add(q4);
//
                p.setAlignment(Element.ALIGN_LEFT);
                p1.setAlignment(Element.ALIGN_RIGHT);
                p2.setAlignment(Element.ALIGN_RIGHT);
                p3.setAlignment(Element.ALIGN_LEFT);
                p4.setAlignment(Element.ALIGN_RIGHT);
                p5.setAlignment(Element.ALIGN_RIGHT);




                cell = new PdfPCell();
                cellbucle(cell,p,Element.ALIGN_MIDDLE,Element.ALIGN_RIGHT);

                PdfPCell cell1 = new PdfPCell();
                cellbucle(cell1,p2,Element.ALIGN_MIDDLE,Element.ALIGN_RIGHT);


                PdfPCell cell2 = new PdfPCell();
                cellbucle(cell2,p2,Element.ALIGN_MIDDLE,Element.ALIGN_RIGHT);

                PdfPCell cell3 = new PdfPCell();
                cellbucle(cell3,p3,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER);
                cell3.setPaddingBottom(8);

                PdfPCell cell4 = new PdfPCell();
                cellbucle(cell4,p4,Element.ALIGN_MIDDLE,Element.ALIGN_RIGHT);
                cell4.setPaddingBottom(8);

                PdfPCell cell5 = new PdfPCell();
                cellbucle(cell5,p5,Element.ALIGN_MIDDLE,Element.ALIGN_RIGHT);




                Atable.addCell(cell);
                Atable.addCell(cell1);
                Atable.addCell(cell4);
                Atable.addCell(cell3);
                Atable.addCell(cell2);



            }



            Atable2.addCell(cellRELLENO);
            Atable2.addCell(celdaSubTotal);
            Atable2.addCell(celdaSubTotalV);
            Atable2.addCell(cellRELLENO);
            Atable2.addCell(celdaTx1);
            Atable2.addCell(celdaTx1V);
            Atable2.addCell(cellRELLENO);
            Atable2.addCell(celdaTx2);
            Atable2.addCell(celdaTx2V);
            Atable2.addCell(cellRELLENO);
            Atable2.addCell(celdaDiscountT);
            Atable2.addCell(celdaDiscountV);
            Atable2.addCell(cellRELLENO);
            Atable2.addCell(celdaTotalT);
            Atable2.addCell(celdaTotalV);



            nestercell.addElement(Atable);
            nestercell2.addElement(Atable2);

            primarytable.addCell(nestercell);
            primarytable.addCell(nestercell2);








            Atable.completeRow();

            mDoc.add(primarytable);



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
            Log.d("fallofirma",String.valueOf(e));
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
        factucell.setFixedHeight(80);

        factucell.setBorderColor(new BaseColor(255,255,255));
        factucell.addElement(Plazo1);
        factucell.addElement(Plazo);


    }
    public void factudatatop(PdfPTable TableTitulo, float leftF,float Rightf) throws DocumentException {
        TableTitulo.setTotalWidth(new float[] {leftF, Rightf});
        TableTitulo.setSpacingBefore(10);
        TableTitulo.setWidthPercentage(100);
        TableTitulo.setHorizontalAlignment(Element.ALIGN_LEFT);

    }
    public PdfPCell CustomerDataCell(PdfPCell CustomerDataCell, Paragraph p,int AligmentH, int AligmentV,BaseColor color1){


        CustomerDataCell.setUseAscender(true);
        CustomerDataCell.setHorizontalAlignment(AligmentH);
        CustomerDataCell.setBorderColor(color1);
        CustomerDataCell.setVerticalAlignment(AligmentV);
        CustomerDataCell.addElement(p);

     return CustomerDataCell;
    }
    public void CargarPreferencias(Context context){
         logopreference2 = context.getSharedPreferences("logopref2", context.MODE_PRIVATE);
         radiotipo= logopreference2.getString("logocheck2","no hay datos");
    }

    public PdfPCell cellLabels(PdfPCell cellP,Paragraph p){
        cellP.setPaddingBottom(8);
        cellP.setPaddingTop(5);
        cellP.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellP.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellP.setBorder(Rectangle.BOTTOM);
        cellP.setBorderColor(BaseColor.BLACK);
        cellP.setFixedHeight(25);
        cellP.setBackgroundColor(new BaseColor(235,236,240));
        cellP.addElement(p);
        return  cellP;
    }
    public PdfPCell cellresumenfinal(PdfPCell cellresumen,Paragraph p){
        cellresumen.setPaddingBottom(2);
        cellresumen.setPaddingTop(2);
        cellresumen.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellresumen.setHorizontalAlignment(Element.ALIGN_MIDDLE);

        //cellTotal.setBorder( Rectangle.BOTTOM|Rectangle.LEFT);
        cellresumen.setBorderColor(BaseColor.DARK_GRAY);
        cellresumen.setFixedHeight(18);
        cellresumen.addElement(p);

        return cellresumen;

    }

    public PdfPCell cellbucle(PdfPCell cellBucle,Paragraph p,int AlignmentV,int AlignmentH){
        cellBucle.setPaddingBottom(8);
        cellBucle.setPaddingTop(5);
        cellBucle.setVerticalAlignment(AlignmentV);
        cellBucle.setHorizontalAlignment(AlignmentH);
        cellBucle.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
//
        cellBucle.setBorderColor(BaseColor.WHITE);

        cellBucle.setFixedHeight(20);
        cellBucle.addElement(p);

        return cellBucle;
    }

}
