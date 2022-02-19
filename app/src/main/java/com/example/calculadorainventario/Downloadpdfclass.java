package com.example.calculadorainventario;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Downloadpdfclass {
    String pattern = "EEEEE MMMMM yyyy HH:mm:ss.SSSZ";
    String pdfurl;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("en", "US"));
    String mFilepath2 = Environment.getExternalStorageDirectory() +
            File.separator + "PyMESoft" + File.separator + "pdf-descargas";
    String mFilename = simpleDateFormat.format(System.currentTimeMillis());

    public void downloadpdf (String pdfurl){

        Log.d("url is",pdfurl);
    }
//        String mFilename2 = cardcliente2.getText().toString() + System.currentTimeMillis();
//      File   file = new File(mFilepath2);
//        if (!file.exists()) {
//            file.mkdirs();
//
//        }
//        file = new File(file.getAbsolutePath());
//        file.mkdir();
//        file = new File(file, System.currentTimeMillis()+mFilename+".pdf");
////        String url1 = pdfurl;
//        // File desf = new File(mFilepath);
//        DownloadManager downloadManager = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
//        Uri uri = Uri.parse(pdfurl);
//        DownloadManager.Request request = new DownloadManager.Request(uri);
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        //request.setDestinationUri(Uri.fromFile(new File(mFilepath)));
//        request.setDestinationUri(Uri.fromFile(file));
//        //request.setDestinationInExternalFilesDir(context,destinationDirectory,file+fileExtension);
//        // request.setDestinationUri(Uri.parse(mFilepath));
//        downloadManager.enqueue(request);
//
////        file  = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +"/"+ mfilename);
//        Intent target = new Intent(Intent.ACTION_VIEW);
//        target.setDataAndType(Uri.fromFile(file),"application/pdf");
//        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//
//        Intent intent = Intent.createChooser(target, "Open File");
//        try {
//            context.startActivity(intent);
//        } catch (ActivityNotFoundException e) {
//
//        }
//
//        //
//
//
//    }

}
