package com.example.calculadorainventario;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.MenuItem;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.ChildEventListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.util.Log.*;


public class ingresodat extends AppCompatActivity {
    DatabaseReference myrootDbaseref,myrootDbaseref3;
    TextView txcliente1;
    Spinner listaclientes, listaclientes2,listacueros,spoperacion,spmetodo;
    BottomNavigationView navigationView;
    TextInputEditText precio1;
    SharedPreferences sharedPreferences;
    String opcion;
    ArrayList<String> ritmo = new ArrayList<String>();
    ArrayList<String> metodo = new ArrayList<String>();
    ArrayList<String> materiales1 = new ArrayList<>();
    ImageView restarprecio, sumarprecio;
    RadioButton rdventas, rdborrador,rdcompras;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresodat);
        myrootDbaseref = FirebaseDatabase.getInstance().getReference();
        myrootDbaseref3 = FirebaseDatabase.getInstance().getReference();
        listacueros= findViewById(R.id.listacueros);
        spoperacion=findViewById(R.id.spoperacion);
        listaclientes2 = findViewById(R.id.listaclientes2);
        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        precio1= (TextInputEditText)findViewById(R.id.precio1);
        restarprecio=(ImageView) findViewById(R.id.restarprecio);
        sumarprecio=(ImageView) findViewById(R.id.sumarprecio);
        rdborrador= findViewById(R.id.rdborrador);
        rdcompras= findViewById(R.id.rdcompras);
        rdventas= findViewById(R.id.rdventas);

        materiales1.add("Producto 1");
        materiales1.add("Producto 2");ArrayAdapter<String> adaptermetodo= new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,ritmo);
        listacueros.setAdapter(adaptermetodo);




        //spmetodo=findViewById(R.id.spmetodo);
        metodo.add("Venta");
        metodo.add("Compra");
        metodo.add("Borrador");
        //ArrayAdapter<String>adaptermetodo=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,metodo);
        //spmetodo.setAdapter(adaptermetodo);



        ritmo.add("Pies");
        ritmo.add("Decímetros");

        ArrayAdapter<String> adapterritmo= new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,ritmo);
        spoperacion.setAdapter(adapterritmo);

spoperacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        precio1.setText(parent.getItemAtPosition(position).toString());
        if (position == 0) {
            precio1.setText(Integer.toString(Integer.parseInt(String.valueOf(1500))));

        } else if (position == 1) {
            precio1.setText(Integer.toString(Integer.parseInt(String.valueOf(230))));

       // } else if (position == 2) {
           // precio1.setText(Integer.toString(Integer.parseInt(String.valueOf(1500))));
       // } else if (position == 3) {
            //precio1.setText(Integer.toString(Integer.parseInt(String.valueOf(1500))));
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});
sumarprecio.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        if(spoperacion.getSelectedItem().toString().equals("Pies")){
            int  valorprecio =Integer.parseInt(precio1.getText().toString());
            int valorritmo= 50;
            int valornuevosuma=valorprecio+valorritmo;
            precio1.setText(valornuevosuma+"");

        }else if(spoperacion.getSelectedItem().toString().equals("Decímetros")){
            int  valorprecio =Integer.parseInt(precio1.getText().toString());
            int valorritmo= 5;
            int valornuevosuma=valorprecio+valorritmo;
            precio1.setText(valornuevosuma+"");

        }










    }
});
restarprecio.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(spoperacion.getSelectedItem().toString().equals("Pies")){
            int  valorprecio =Integer.parseInt(precio1.getText().toString());
            int valorritmo= 50;
            int valornuevosuma=valorprecio-valorritmo;
            precio1.setText(valornuevosuma+"");

        }else if(spoperacion.getSelectedItem().toString().equals("Decímetros")){
            int  valorprecio =Integer.parseInt(precio1.getText().toString());
            int valorritmo= 5;
            int valornuevosuma=valorprecio-valorritmo;
            precio1.setText(valornuevosuma+"");}


    }
});


        myrootDbaseref.child("CLIENTE").orderByChild("Nombre").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<constspinner> misclientes1 = new ArrayList<>();

                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        String nombre = ds.child("Nombre").getValue().toString();
                        //String apellido = ds.child("Apellido").getValue().toString();
                        misclientes1.add(new constspinner(nombre));



                   }
                    ArrayAdapter<constspinner> adapter2 = new ArrayAdapter<constspinner>(ingresodat.this, android.R.layout.simple_spinner_dropdown_item, misclientes1);
                    listaclientes2.setAdapter(adapter2);


               }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Snackbar.make(findViewById(R.id.listaclientes2), "SELECCIONA UN CLIENTE",Snackbar.LENGTH_SHORT).show();

            }


        });


        navigationView.setSelectedItemId(R.id.action_calc);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.listaclientes2:
                        startActivity(new Intent(getApplicationContext(), ingresodat.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.action_home:
                        startActivity(new Intent(getApplicationContext(), home1.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.action_calc:
                        startActivity(new Intent(getApplicationContext(), ingresodat.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.action_add:
                        startActivity(new Intent(getApplicationContext(), activitybotonesing.class));
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


    }
    public void Enviarprecio1 (View View ) {
        if(rdventas.isChecked()==true){
            opcion= "Venta";
        }
        if(rdborrador.isChecked()==true){
            opcion= "Borrador";
        }
        if(rdcompras.isChecked()==true){
            opcion= "Compra";
        }

        sharedPreferences=getSharedPreferences("datosguardados", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("opcion", opcion);
        editor.putString("value", listaclientes2.getSelectedItem().toString());
        //editor.putString("producto2",listacueros.getSelectedItem().toString());
        editor.apply();
        Intent i= new Intent(this,MainActivity.class);
        i.putExtra("preciouno",precio1.getText().toString());

        startActivity(i);



    }

    }







