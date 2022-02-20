package com.example.calculadorainventario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
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
    ImageView back4;
    CheckBox checkcliente;
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
        back4=findViewById(R.id.back4);
        checkcliente=findViewById(R.id.checkcliente);
        navigationView = (BottomNavigationView) findViewById(R.id.navigation);


        txinputtel1 = (TextInputEditText) findViewById(R.id.txinputtel1);
        floatingActionButton4=findViewById(R.id.floatingActionButton4);
        mAuth=FirebaseAuth.getInstance();
        String id= mAuth.getCurrentUser().getUid();
        myrootDbaseref2 = FirebaseDatabase.getInstance().getReference();
        mydb2 = myrootDbaseref2.child("CLIENTE").child(id).push();
        back4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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
                

                    String Nombre = txinputnombre.getText().toString();

                    String Tel = txinputtel1.getText().toString();
                Map<String, Object> datosclientesnuevos = new HashMap<>();
                datosclientesnuevos.put("Nombre", Nombre);
                datosclientesnuevos.put("Tel", Tel);
                datosclientesnuevos.put("Key", mydb2.getKey());

                mydb2.setValue(datosclientesnuevos);



                // Intent intent1=new Intent(InclientActivity.this,fragments3.class);
                //startActivity(intent1);

                if(checkcliente.isChecked()==true) {

                    Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                    intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                    intent
                            .putExtra(ContactsContract.Intents.Insert.PHONE, txinputtel1.getText())
                            .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
                            .putExtra(ContactsContract.Intents.Insert.NAME, txinputnombre.getText().toString());
                    startActivity(intent);
                }else {
                    ((TextInputEditText) findViewById(R.id.txinputnombre)).setText("");

                    ((TextInputEditText) findViewById(R.id.txinputtel1)).setText("");
                }






            }
        });


    }




}


