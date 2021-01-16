package com.example.calculadorainventario;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.DocumentsContract;
import android.util.Log;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class archivopdfclass extends ActivityCompat {
    public Context context;
    private File productopdf;
    private Document document;
    private Paragraph paragraph;

    private Font ftittle=new Font(Font.FontFamily.TIMES_ROMAN,20,Font.BOLD);
    private Font ftext=new Font(Font.FontFamily.TIMES_ROMAN,12,Font.NORMAL);
    private Font fother=new Font(Font.FontFamily.TIMES_ROMAN,20,Font.BOLD, BaseColor.BLUE);

    public archivopdfclass(Context context) {
       this.context=context;

    }

    public void opendocument(){
        createfile();
        try {
            document= new Document(PageSize.A4);
            PdfWriter.getInstance(document,new FileOutputStream(productopdf));
            document.open();

        }catch (Exception e){
            Log.e("opendocument",e.toString());
        }
    }
    private Boolean isExternalStorageWritable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            Log.i("state", "si se puede escribir");
            return true;

        } else {
            Log.i("state", "no se puede escribir");
            return false;
        }
    }

    public void createfile() {


            File folder = new File(Environment.getExternalStorageDirectory().toString(), "PDF");
            if (!folder.exists()) {
                folder.mkdir();
                productopdf = new File(folder, "PDFVENTA.pdf");
            }else if (!folder.exists()){
                   folder.mkdirs();
                 productopdf=new File(folder,"Producto.pdf");
            }
    }
   // public boolean checkpermission(String permission){
     //   int check= ContextCompat.checkSelfPermission(this.context,permission);
       // return (check== PackageManager.PERMISSION_GRANTED);




    public void closedocumment(){
        document.close();
    }
    public void addMetadata(String tittle, String subject, String author){
        document.addTitle(tittle);
        document.addSubject(subject);
        document.addAuthor(author);


    }
    public void tittles(String tittle, String subject, String date ){
       try {
           paragraph=new Paragraph();
           addChildP(new Paragraph(tittle,ftittle));
           addChildP(new Paragraph(subject,ftittle));
           addChildP(new Paragraph(date,fother));
           paragraph.setSpacingAfter(30);
           document.add(paragraph);

       }catch (Exception e){
           Log.e("tittles",e.toString());

       }



    }
    private void addChildP(Paragraph childparagraph){
        childparagraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(childparagraph);
    }
    public void addparagraph(String Text){
        try {
            paragraph=new Paragraph(Text,ftext);
            paragraph.setSpacingAfter(5);
            paragraph.setSpacingBefore(5);
            document.add(paragraph);

        }catch (Exception e){
            Log.e("addparagraph",e.toString());
        }



    }
    public void createtable(String[]header, ArrayList<String[]>Unidades){
        try {


            paragraph = new Paragraph();
            paragraph.setFont(ftext);
            PdfPTable pdfPTable = new PdfPTable(header.length);
            pdfPTable.setWidthPercentage(30);
            PdfPCell pdfPCell;
            int indexC = 0;
            while (indexC < header.length) {
                pdfPCell = new PdfPCell(new Phrase(header[indexC++], fother));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                pdfPTable.addCell(pdfPCell);
            }
            for (int indexR = 0; indexR < Unidades.size(); indexR++) {
                String[] row = Unidades.get(indexR);
                for (indexC = 0; indexC < header.length; indexC++) {
                    pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(40);
                    pdfPTable.addCell(pdfPCell);

                }
            }
            paragraph.add(pdfPTable);
            document.add(paragraph);
        }catch (Exception e){
            Log.e("createtable",e.toString());

        }



    }
    //
    //}
    //public void showadobe(Activity activity){
   public void openPdfFile(File productopdf) {



        if(Build.VERSION.SDK_INT>=24){



            try{



                //For API's > 24, runtime exception occurs when a URI is exposed BEYOND this particular app that you are writing (AKA when user attempts to open in device/emulator



                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");



                m.invoke(null);



            }catch(Exception e){



                e.printStackTrace();



            }



        }



        Intent target = new Intent(Intent.ACTION_VIEW);



        target.setDataAndType(Uri.fromFile(productopdf),"application/pdf");



        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);







        Intent intent = Intent.createChooser(target, "Open File");



        try {



            context.startActivity(intent);



        } catch (ActivityNotFoundException e) {



            // Instruct the user to install a PDF reader here, or something



        }



    }




    // if (productopdf.exists())      {
         //   Toast.makeText(activity.getApplicationContext(), "Cargando documento", Toast.LENGTH_SHORT).show();
           // Uri uri = Uri.fromFile(productopdf);
            //Intent intent = new Intent(Intent.ACTION_VIEW);
            //intent.setDataAndType(uri, "application/pdf");
            //try {
              //  StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder(); StrictMode.setVmPolicy(builder.build());
                //activity.startActivity(intent);
            //} catch (ActivityNotFoundException e) {
              //  Toast.makeText(activity.getApplicationContext(),"No cuentas con una aplicación para visualizar PDF", Toast.LENGTH_LONG).show();
           // }
       //}else {
         //   Toast.makeText(activity.getApplicationContext(), "No se encontró el archivo PDF", Toast.LENGTH_SHORT).show();
        //}
       // if(productopdf.exists()){
         //   Uri uri=Uri.fromFile(productopdf);
           // Intent intent=new Intent(Intent.ACTION_VIEW);
           // intent.setDataAndType(uri,"application/pdf");
           // try {
             //  activity.startActivity(intent);

            //}catch (ActivityNotFoundException e){
            //activity.startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=com.adobe.reader")));}
        //}else {
          //  Toast.makeText(activity.getApplicationContext(),"ERROR", Toast.LENGTH_SHORT).show();}

    }




