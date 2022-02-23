package com.example.calculadorainventario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Signature1Activity extends Activity implements View.OnTouchListener {
    public float cldx, cldy,dx,dy,nx,ny;
    ImageView imgSignature1;
    Bitmap bitmap;
    Canvas canvas;
    Paint paint;
    OutputStream outputStream;
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 111;
    CardView cardguardar,cardborrar;
    View view;
    MotionEvent event;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signature_1);
        imgSignature1= findViewById(R.id.imgSignature1);
        cardguardar=findViewById(R.id.cardguardar);
        cardborrar=findViewById(R.id.cardborrar);
        Display myDisplay=getWindowManager().getDefaultDisplay();
        float dw=myDisplay.getWidth();
        float dh=myDisplay.getHeight();
        bitmap=Bitmap.createBitmap((int)dw,(int)dh,Bitmap.Config.ARGB_8888);
        canvas=new Canvas(bitmap);
        paint=new Paint();
        paint.setColor(getResources().getColor(R.color.colorNegro));
        paint.setStrokeWidth(12);
        imgSignature1.setImageBitmap(bitmap);
        imgSignature1.setOnTouchListener(this);

        cardguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, REQUEST_CODE_ASK_PERMISSIONS);


                    } else {


                        GuardarInterno();

                    }
                } else {


                    GuardarInterno();


                }
            }
        });
        cardborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BorrarSignature();

            }
        });


    }


    @Override
    public boolean onTouch(View view,  MotionEvent event) {
        int action=event.getAction();
        switch (action){
//            case MotionEvent.ACTION_DOWN:
//                dx=event.getX();
//                dy=event.getY();
//                cldx=dx;
//                cldy=dy;
//
//            case MotionEvent.ACTION_MOVE:
//                nx=event.getX();
//                ny=event.getY();
//                canvas.drawLine(cldx,cldy,nx,ny,paint);
//                imgSignature1.invalidate();
//                cldy=ny;
//                cldx=nx;

           case MotionEvent.ACTION_DOWN:
            dx=event.getX();
            dy=event.getY();
            cldx=dx;
            cldy=dy;

            case MotionEvent.ACTION_MOVE:
                nx=event.getX();
                ny=event.getY();
                canvas.drawLine(cldx,cldy,nx,ny,paint);
               imgSignature1.invalidate();
                cldx=nx;
                cldy=ny;

        }
        return true;
    }
    public void GuardarInterno(){

            File folder = new File(Environment.getExternalStorageDirectory() +
                    File.separator + "PyMESoft"+File.separator+"Signature");
            if (!folder.exists()) {
                folder.mkdirs();

            }
            File filepath2= new File(folder.getAbsolutePath());
            filepath2.mkdir();
            File file=new File(filepath2,"signature"+"png");
            try {
                outputStream=new FileOutputStream(file);


            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
            bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        Toast.makeText(this,"Se guardó la nueva firma",Toast.LENGTH_SHORT).show();
    }
    public void BorrarSignature(){

      // Bitmap mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);


        Path path = new Path();
        imgSignature1.invalidate();

    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    GuardarInterno();



                    Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(this, "Permiso negado", Toast.LENGTH_SHORT).show();
                }


            }



        }}}