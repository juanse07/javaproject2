package com.example.calculadorainventario;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.itextpdf.text.DocumentException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class logochoose extends AppCompatActivity {
    Button buttonchoose, buttonupload;
    ImageView mylogo,backchoose;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseAuth mAuth;
    Context context;
    Uri filePath;
    Bitmap bitmap;
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 111;
    OutputStream outputStream;
    RadioGroup logogrupo;
    RadioButton logoradiobutton,logoradiobutton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logochoose);
        buttonchoose=findViewById(R.id.buttonchoose);
        buttonupload=findViewById(R.id.buttonupload);
        backchoose=findViewById(R.id.backchoose);
        logogrupo=findViewById(R.id.logogrupo);
        logoradiobutton=findViewById(R.id.logoradioButton);
        logoradiobutton2=findViewById(R.id.logoradioButton2);
        mylogo=findViewById(R.id.mylogo);
        storage= FirebaseStorage.getInstance();
        storageReference=storage.getReference();
        File imgFile = new  File("/storage/emulated/0/PyMESoft/Logotipo/logopng");
        loadRadioButtons3();
        logoradiobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarpreferencias3();
            }
        });
        logoradiobutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarpreferencias3();
            }
        });

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());



            mylogo.setImageBitmap(myBitmap);


        };
        backchoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onBackPressed();
            }
        });


        buttonchoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosseimage();
            }
        });
        buttonupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadimage();
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, REQUEST_CODE_ASK_PERMISSIONS);


                    } else {
                        outputStream = new ByteArrayOutputStream();


                        try {
                            internalstorage();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    }
                } else {



                    try {
                        internalstorage();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }



                }
                //try {
                  //  internalstorage();
                //} catch (FileNotFoundException e) {
                  //  e.printStackTrace();
                //}
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK
            && data!=null && data.getData()!=null){
           filePath=data.getData();
            bitmap= null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                mylogo.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void choosseimage (){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleccionar Imagen"),1);

    }
    private void uploadimage(){
        mAuth=FirebaseAuth.getInstance();
        String id=mAuth.getCurrentUser().getUid();
        if(filePath!=null){
            final ProgressDialog progressDialog=new ProgressDialog(this);
            progressDialog.setTitle("Subiendo Imagen...");
            progressDialog.show();

           // StorageReference reference=storageReference.child("Logotipos/"+ UUID.randomUUID().toString());
            StorageReference reference=storageReference.child("Logotipos").child(id).child("logo");
            reference.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(logochoose.this,"Logo seleccionado",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            double progress=(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Cargado"+(int)progress+"%");

                        }
                    });
        }

    }
    private void internalstorage() throws FileNotFoundException {

        //error en realme
        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "PyMESoft"+File.separator+"Logotipo");
        if (!folder.exists()) {
            folder.mkdirs();

        }
        File filepath2= new File(folder.getAbsolutePath());
        filepath2.mkdir();
        File file=new File(filepath2,"logo"+"png");
        try {
            outputStream=new FileOutputStream(file);



        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.PNG,60,outputStream);





       // File myDir = context.getFilesDir();
        // Documents Path
        //String documents = "documents/data";
        //File documentsFolder = new File(myDir, documents);
        //documentsFolder.mkdirs(); // this line creates data folder at documents directory

        //String publicC = "documents/public/api." + server;
       // File publicFolder = new File(myDir, publicC);
       // publicFolder.mkdirs();
    }
    private void guardarpreferencias3() {

        SharedPreferences logopreference2 = getSharedPreferences
                ("logopref2", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = logopreference2.edit();
        editor.putInt("logocheck", logogrupo.indexOfChild(findViewById(logogrupo.getCheckedRadioButtonId())));
        if (logoradiobutton.isChecked()) {
            editor.putString("logocheck2", logoradiobutton.getText().toString());
        } else if(logoradiobutton2.isChecked()) {
            editor.putString("logocheck2", logoradiobutton2.getText().toString());}

            editor.apply();
            editor.commit();

    }

        public void loadRadioButtons3 () {
            SharedPreferences logopreference2 = getSharedPreferences
                    ("logopref2", Context.MODE_PRIVATE);
            int i = logopreference2.getInt("logocheck", -1);
            if (i >= 0) {
                ((RadioButton) ((RadioGroup) findViewById(R.id.logogrupo)).getChildAt(i)).setChecked(true);
            }


        }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    try {
                        internalstorage();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }



                    Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(this, "Permiso negado", Toast.LENGTH_SHORT).show();
                }


            }

        }






    }



}
