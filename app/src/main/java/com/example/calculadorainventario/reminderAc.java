package com.example.calculadorainventario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.DownloadManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.MonthDay;
import java.time.Year;
import java.util.Calendar;
import java.util.Locale;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class reminderAc extends AppCompatActivity   {
    EditText txdate2, txdate3, cardcliente2, cardproducto2, cardmedida2, cardfecha2, cardvalor2, cardunidades2, cardprecio2, txpdfurl, cardestado2;
    TextView txdate1, txtime, cardhora2;
    ImageView date_1, timeimage, share2, btwzp2, backreminder,deleteimage;
    Button  btactualizarfire;
    FirebaseStorage mystorage;
    Calendar mcalendar;
    Uri pdfuri;
    String pdfurl2,Rcliente,Rproducto,Rprecio,Rvalor,Rmedida,Runidades,Restado,Rfecha,Rfecha2,Renlace;
    int mYear, mMonth, mHour, mMinute, mDay, notificationid, mMonth1;
    private static final int REQUEST_CODE_ASK_PERMISSIONS_3 = 113;
    String pattern = "EEEEE MMMMM yyyy HH:mm:ss.SSSZ";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("en", "US"));

    String mFilename = simpleDateFormat.format(System.currentTimeMillis());
    //String mFilename2 = cardcliente2.getText().toString() + System.currentTimeMillis();
    String mFilepath = Environment.getExternalStorageDirectory() + "/" + mFilename + ".pdf";
    String mFilepath2 = Environment.getExternalStorageDirectory() +
            File.separator + "PyMESoft" + File.separator + "pdf-descargas";
    String mFilepath3 = Environment.getExternalStorageDirectory() +
            File.separator + "PyMESoft" + File.separator + "pdf-descargas"+System.currentTimeMillis()+"dowloaded"+"pdf";

    DatabaseReference ref;
    FirebaseAuth mAuth;
    File file1;
    File filepath2;


    //
    //String mFilepath3 = Environment.getExternalStorageDirectory() + mFilename2 + ".pdf";
    // String mFilepath2= Environment.DIRECTORY_DOWNLOADS+mFilename+".pdf";
    // String mfilepath3="/storage/sdcard0/Android/data/package/files/Download"+mFilename+".pdf";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reminder);
        btactualizarfire = findViewById(R.id.btactualizarfire);
        cardcliente2 = findViewById(R.id.cardcliente2);
        cardfecha2 = findViewById(R.id.cardfecha2);
        cardhora2 = findViewById(R.id.cardhora2);
        cardmedida2 = findViewById(R.id.cardmedida2);
        cardprecio2 = findViewById(R.id.cardprecio2);
        cardproducto2 = findViewById(R.id.cardproducto2);
        cardestado2 = findViewById(R.id.cardestado2);
        cardunidades2 = findViewById(R.id.cardunidades2);
        deleteimage=findViewById(R.id.deleteimage);
        backreminder=findViewById(R.id.backreminder);
        cardvalor2 = findViewById(R.id.cardvalor2);
        txpdfurl = findViewById(R.id.txpdfurl);

        share2 = (ImageView) findViewById(R.id.share2);
        btwzp2 = (ImageView) findViewById(R.id.btwzp2);
        mcalendar = Calendar.getInstance();
        mAuth = FirebaseAuth.getInstance();
        RecibirDatosdeHome();
        deleteimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String datakey = getIntent().getStringExtra("key");
                final String id = mAuth.getCurrentUser().getUid();
                ref = FirebaseDatabase.getInstance().getReference().child("VENTAS").child(id).child(datakey);
                ref.removeValue();
                Toast.makeText(reminderAc.this, "Se ha eliminado el item seleccionado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(reminderAc.this, home1.class);
                startActivity(intent);

            }
        });
        btwzp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadpdf(reminderAc.this, mFilename, ".pdf", DIRECTORY_DOWNLOADS, txpdfurl.getText().toString());
                sharewzp2();
            }
        });


        backreminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                onBackPressed();
            }
        });


        btactualizarfire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatefire();

            }
        });
        share2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, REQUEST_CODE_ASK_PERMISSIONS_3);

                    } else {
                        downloadpdf(reminderAc.this, mFilename, ".pdf", DIRECTORY_DOWNLOADS, txpdfurl.getText().toString());
                        sharefirebase();
                    }


                } else {
                    downloadpdf(reminderAc.this, mFilename, ".pdf", DIRECTORY_DOWNLOADS, txpdfurl.getText().toString());
                    sharefirebase();
                }

            }
        });












    }












    public void downloadpdf(Context context, String file, String fileExtension, String destinationDirectory, String url) {
        String mFilename2 = cardcliente2.getText().toString() + System.currentTimeMillis();
        File folder = new File(mFilepath2);
        if (!folder.exists()) {
            folder.mkdirs();

        }
        filepath2 = new File(folder.getAbsolutePath());
        filepath2.mkdir();
        file1 = new File(filepath2, System.currentTimeMillis()+mFilename+".pdf");
        url = Renlace;
        // File desf = new File(mFilepath);
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //request.setDestinationUri(Uri.fromFile(new File(mFilepath)));
        request.setDestinationUri(Uri.fromFile(file1));
        //request.setDestinationInExternalFilesDir(context,destinationDirectory,file+fileExtension);
        // request.setDestinationUri(Uri.parse(mFilepath));
        downloadManager.enqueue(request);

        //


    }

    public void sharefirebase() {
        // file = new File(bundle.getString("path", ""));


        if (Build.VERSION.SDK_INT >= 24) {

            try {

                //For API's > 24, runtime exception occurs when a URI is exposed BEYOND this particular app that you are writing (AKA when user attempts to open in device/emulator

                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");

                m.invoke(null);

            } catch (Exception e) {

                e.printStackTrace();

            }

        }
        //File path=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),mFilename);
        File mpath = file1;
        //String path1= String.valueOf(path.getAbsolutePath());

        Uri pdfUri = Uri.fromFile(mpath);
        // Uri pdfUri= Uri.parse(txpdfurl.getText().toString().trim());
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        //shareIntent.putExtra(Intent.EXTRA_TEXT, pdfUri);
        shareIntent.putExtra(Intent.EXTRA_STREAM, pdfUri);
        shareIntent.setType("application/pdf");
        // shareIntent.setType("text");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {

            startActivity(Intent.createChooser(shareIntent, "share"));
        } catch (ActivityNotFoundException e) {
        }


        // Instruct the user to install a PDF reader here, or something


    }

    public void sharewzp2() {

        // file = new File(bundle.getString("path", ""));


        if (Build.VERSION.SDK_INT >= 24) {

            try {

                //For API's > 24, runtime exception occurs when a URI is exposed BEYOND this particular app that you are writing (AKA when user attempts to open in device/emulator

                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");

                m.invoke(null);

            } catch (Exception e) {

                e.printStackTrace();

            }

        }
        //File path=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),mFilename);
        File mpath = file1;
        //String path1= String.valueOf(path.getAbsolutePath());

        Uri pdfUri = Uri.fromFile(mpath);
        // Uri pdfUri= Uri.parse(txpdfurl.getText().toString().trim());
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        //shareIntent.putExtra(Intent.EXTRA_TEXT, pdfUri);
        shareIntent.putExtra(Intent.EXTRA_STREAM, pdfUri);
        shareIntent.setType("application/pdf");
        // shareIntent.setType("text");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        shareIntent.setPackage("com.whatsapp");
        try {

            startActivity(Intent.createChooser(shareIntent, "share"));
        } catch (ActivityNotFoundException e) {
        }


        // Instruct the user to install a PDF reader here, or something


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS_3: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // downloadpdf(reminderAc.this,cardcliente2.getText().toString()+System.currentTimeMillis(),".pdf",DIRECTORY_DOWNLOADS,txpdfurl.getText().toString());
                    // downloadpdf(reminderAc.this,mFilename,".pdf",DIRECTORY_DOWNLOADS,txpdfurl.getText().toString());
                    sharefirebase();

                } else {
                    Toast.makeText(reminderAc.this, "Permiso negado", Toast.LENGTH_SHORT).show();
                }

            }
        }


    }

    public void updatefire() {
        if (isNameChanged() || isProductoChanged() || isPriceChanged() || isStatusChanged()) {
            Toast.makeText(this, "Datos Correctamente Actualizados", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "No hay cambios, no es posible actualizar", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isStatusChanged() {
        final String datakey = getIntent().getStringExtra("key");
        final String id = mAuth.getCurrentUser().getUid();
        ref = FirebaseDatabase.getInstance().getReference().child("VENTAS").child(id).child(datakey);
        if (!Restado.equals(cardestado2.getText().toString())) {
            ref.child("Estado").setValue(cardestado2.getText().toString());
            return true;


        } else {
            return false;
        }
    }

    private boolean isPriceChanged() {
        final String datakey = getIntent().getStringExtra("key");
        final String id = mAuth.getCurrentUser().getUid();
        ref = FirebaseDatabase.getInstance().getReference().child("VENTAS").child(id).child(datakey);
        if (!Rprecio.equals(cardprecio2.getText().toString())) {
            ref.child("Precio").setValue(cardprecio2.getText().toString());
            return true;


        } else {
            return false;
        }
    }

    private boolean isProductoChanged() {
        final String datakey = getIntent().getStringExtra("key");
        final String id = mAuth.getCurrentUser().getUid();
        ref = FirebaseDatabase.getInstance().getReference().child("VENTAS").child(id).child(datakey);
        if (!Rproducto.equals(cardproducto2.getText().toString())) {
            ref.child("Producto").setValue(cardproducto2.getText().toString());
            return true;


        } else {
            return false;
        }
    }

    private boolean isNameChanged() {
        final String datakey = getIntent().getStringExtra("key");
        final String id = mAuth.getCurrentUser().getUid();
        ref = FirebaseDatabase.getInstance().getReference().child("VENTAS").child(id).child(datakey);
        if (!Rcliente.equals(cardcliente2.getText().toString())) {
            ref.child("Cliente").setValue(cardcliente2.getText().toString());
            return true;


        } else {
            return false;
        }

    }

    private void internalstorage() {
        File folder = new File(mFilepath2);
        if (!folder.exists()) {
            folder.mkdirs();

        }
        File filepath2 = new File(folder.getAbsolutePath());
        filepath2.mkdir();
        File file = new File(filepath2, System.currentTimeMillis()+"dowloaded"+"pdf");

    }
    private void RecibirDatosdeHome(){
        Rcliente= getIntent().getStringExtra("cliente");
        Rproducto = getIntent().getStringExtra("producto");
        Rprecio = getIntent().getStringExtra("precio");
        Restado = getIntent().getStringExtra("estado");
        Rfecha= getIntent().getStringExtra("fecha");
        Rmedida = getIntent().getStringExtra("medida");
        Runidades= getIntent().getStringExtra("unidades");
        Rvalor = getIntent().getStringExtra("valor");
        Renlace = getIntent().getStringExtra("pdfurl");
        cardcliente2.setText(Rcliente);
        cardfecha2.setText(Rfecha);
        cardmedida2.setText(Rmedida);
        cardprecio2.setText(Rprecio);
        cardproducto2.setText(Rproducto);
        cardunidades2.setText(Runidades);
        cardvalor2.setText(Rvalor);
        cardestado2.setText(Restado);

    }
}