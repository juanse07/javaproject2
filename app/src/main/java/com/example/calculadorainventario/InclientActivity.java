package com.example.calculadorainventario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.provider.ContactsContract;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class InclientActivity extends AppCompatActivity {

    DatabaseReference myrootDbaseref2, mydb2;
    Button btnuevocliente;
//    ImageView back4;
    CheckBox checkcliente;
    MaterialCardView emailbtcl,phonebtcl,customerbtcl,addressbtcl,citybtcl,zipbtcl;
    TextView emailtxcl,phonetxcl,customertxcl,addresstxcl,citytxcl,ziptxcl;
    TextInputEditText txinputnombre, txinputape, txinputtel1;
    RecyclerView Recyclercliente;
    View ClientesView;
    FirebaseAuth mAuth;
    BottomNavigationView navigationView;
    FloatingActionButton floatingActionButton4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inclient);

        btnuevocliente = (Button) findViewById(R.id.btnuevocliente);
        txinputnombre = (TextInputEditText) findViewById(R.id.txinputnombre);
//        back4=findViewById(R.id.back4);
        checkcliente=findViewById(R.id.checkcliente);
        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        emailbtcl=findViewById(R.id.emailbtcl);
        phonebtcl=findViewById(R.id.phonebtcl);
        customerbtcl=findViewById(R.id.customerbtcl);
        emailtxcl=findViewById(R.id.emailtxcl);
        phonetxcl=findViewById(R.id.phonetxcl);
        customertxcl=findViewById(R.id.customertxcl);
        addressbtcl=findViewById(R.id.addressbtcl);
        addresstxcl=findViewById(R.id.addresstxcl);
        citybtcl=findViewById(R.id.citybtcl);
        citytxcl=findViewById(R.id.citytxcl);
        zipbtcl=findViewById(R.id.zipbtcl);
        ziptxcl=findViewById(R.id.ziptxcl);

        customerbtcl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputtext;
                inputtext=getResources().getString(R.string.Customer_Name);
                int inputype=1;
                AlertDialog(customertxcl,inputtext,inputype);
//
            }
        });
        phonebtcl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputtext;
                inputtext=getResources().getString(R.string.Mobile_phone);
                int inputtype=2;
                AlertDialog(phonetxcl,inputtext,inputtype);
            }
        });

        emailbtcl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputtext;
                inputtext=getResources().getString(R.string.Email);
                int inputype=1;
                AlertDialog(emailtxcl,inputtext,inputype);

            }
        });
        addressbtcl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputtext;
                inputtext=getResources().getString(R.string.Address);
                int inputype=1;
                AlertDialog(addresstxcl,inputtext,inputype);

            }


        });
        citybtcl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputtext;
                inputtext=getResources().getString(R.string.City);
                int inputype=1;
                AlertDialog(citytxcl,inputtext,inputype);


            }
        });

        zipbtcl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputtext;
                inputtext=getResources().getString(R.string.ZIP);
                int inputype=2;
                AlertDialog(ziptxcl,inputtext,inputype);

            }
        });


        txinputtel1 = (TextInputEditText) findViewById(R.id.txinputtel1);
        floatingActionButton4=findViewById(R.id.floatingActionButton4);
        mAuth=FirebaseAuth.getInstance();
        String id= mAuth.getCurrentUser().getUid();
        myrootDbaseref2 = FirebaseDatabase.getInstance().getReference();
        mydb2 = myrootDbaseref2.child("CLIENTE").child(id).push();
//        back4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
        navigationView.setSelectedItemId(R.id.action_more);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
//                    case R.id.listaclientes2:
//
//                        startActivity(new Intent(getApplicationContext(), ingresodat.class));
//                        overridePendingTransition(0, 0);
//                        return true;
                    case R.id.action_home:
                        startActivity(new Intent(getApplicationContext(), homeinvoice2.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.action_more:
                        startActivity(new Intent(getApplicationContext(), InclientActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.action_add:
                        startActivity(new Intent(getApplicationContext(), Crearproducto.class));
                        overridePendingTransition(0, 0);
                        return true;
                    // case R.id.action_Pdf:
                    //   startActivity(new Intent(getApplicationContext(), pdfviewer.class));
                    // overridePendingTransition(0, 0);
                    //return true;
                }
                return false;
            }
        });
        floatingActionButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InclientActivity.this,fragments3.class);
                startActivity(i);
            }
        });




        btnuevocliente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                

                    String Nombre = customertxcl.getText().toString();
                    String Tel = phonetxcl.getText().toString();
                    String Email=emailtxcl.getText().toString();
                    String Address=addresstxcl.getText().toString();
                    String City=citytxcl.getText().toString();
                    String Zip=ziptxcl.getText().toString();
                Map<String, Object> datosclientesnuevos = new HashMap<>();
                datosclientesnuevos.put("Cliente_Nombre", Nombre);
                datosclientesnuevos.put("Tel", Tel);
                datosclientesnuevos.put("Email",Email );
                datosclientesnuevos.put("Address", Address);
                datosclientesnuevos.put("City", City);
                datosclientesnuevos.put("Zip", Zip);
                datosclientesnuevos.put("Key", mydb2.getKey());

                mydb2.setValue(datosclientesnuevos);



                // Intent intent1=new Intent(InclientActivity.this,fragments3.class);
                //startActivity(intent1);

                if(checkcliente.isChecked()==true) {

                    Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                    intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                    intent
                            .putExtra(ContactsContract.Intents.Insert.PHONE, phonetxcl.getText())
                            .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
                            .putExtra(ContactsContract.Intents.Insert.NAME, customertxcl.getText().toString());
                    startActivity(intent);
//                    Intent i = new Intent(InclientActivity.this,fragments3.class);
//                    startActivity(i);
                }else {
                    Intent i = new Intent(InclientActivity.this,fragments3.class);
                    startActivity(i);
                }






            }
        });


    }
    private void AlertDialog(final TextView textView, String inputtext1,int inputtype){
        AlertDialog.Builder builder=new AlertDialog.Builder(InclientActivity.this,R.style.Theme_MaterialComponents_Dialog_Alert);
        ;
        final View view= LayoutInflater.from(InclientActivity.this).inflate(R.layout.edittextdialog,(ConstraintLayout)InclientActivity.this.findViewById(R.id.parentconstrait));
        builder.setView(view);
        ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).setInputType(inputtype);
//                ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).setText(holder.txcatprice.getText().toString());
        ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).requestFocus();
        ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).setHint(inputtext1);
        ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).setHintTextColor(view.getResources().getColor(R.color.colorGrisoscuro));
        final AlertDialog alertDialog=builder.create();
        if(alertDialog.getWindow() !=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
        ((MaterialButton) view.findViewById(R.id.editdialogbutton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        ((MaterialButton) view.findViewById(R.id.aceptedtx)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView.setText(((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).getText().toString());

                alertDialog.dismiss();

            }

        });










    }

}
