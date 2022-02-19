package com.example.calculadorainventario;

import androidx.annotation.NonNull;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.GridView;


public class activitybotonesing extends AppCompatActivity {
    BottomNavigationView navigationView;
    Button btnuevocliente, btnuevopro, btnuevocolor;
    //RecyclerView Recyclercliente;
    NEWMATFragment NEWMATFragment;
    newclie newclie;
    newcolorfrag newcolorfrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitybotonesing);


    newclie= new newclie();
    newcolorfrag= new newcolorfrag();
    NEWMATFragment= new NEWMATFragment();
    navigationView =(BottomNavigationView) findViewById(R.id.navigation);

    btnuevocolor =(Button) findViewById(R.id.btnuevocolor);

    btnuevopro =(Button) findViewById(R.id.btnuevopro);

    btnuevocliente =(Button) findViewById(R.id.btnuevocliente);
    //Recyclercliente=(RecyclerView) findViewById(R.id.Recyclercliente);

    getSupportFragmentManager().beginTransaction().add(R.id.contenedorfragmentos,newclie).commit();





    navigationView.setSelectedItemId(R.id.action_add);
//    navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
//
//    {
//        @Override
//        public boolean onNavigationItemSelected (@NonNull MenuItem item){
//        switch (item.getItemId()) {
//            case R.id.btnuevocolor:
//                startActivity(new Intent(getApplicationContext(), activitybotonesing.class));
//                overridePendingTransition(0, 0);
//                return true;
//            case R.id.action_home:
//                startActivity(new Intent(getApplicationContext(), home1.class));
//                overridePendingTransition(0, 0);
//                return true;
//            case R.id.action_more:
//                startActivity(new Intent(getApplicationContext(), ingresodat.class));
//                overridePendingTransition(0, 0);
//                return true;
//            case R.id.action_add:
//                startActivity(new Intent(getApplicationContext(), activitybotonesing.class));
//                overridePendingTransition(0, 0);
//                return true;
//
//            case R.id.action_more:
//                startActivity(new Intent(getApplicationContext(), VistaAA.class));
//                overridePendingTransition(0, 0);
//                return true;
//
//        }
//        return false;
//        }
//
//    });

}

    public void onClick(View view) {
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();



        switch (view.getId()){
            case R.id.btnuevocliente:
                transaction.replace(R.id.contenedorfragmentos,newclie);
                break;
            case R.id.btnuevocolor:
                transaction.replace(R.id.contenedorfragmentos,newcolorfrag);
                break;
            case R.id.btnuevopro:
                transaction.replace(R.id.contenedorfragmentos,NEWMATFragment);
                break;


        }
        transaction.commit();
    }

}

