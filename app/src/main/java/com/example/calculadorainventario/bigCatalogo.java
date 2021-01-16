package com.example.calculadorainventario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabItem;

public class bigCatalogo extends AppCompatActivity {
    TabItem tabitem1, tabitem2,tabitem3;
    FrameLayout contenedorfragmentos2;
    catalogo catalogo;
    carrito_fragment  carrito_fragment;
    vistapreliminarfrag vistapreliminarfrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_catalogo);

       catalogo= new catalogo();
      carrito_fragment= new carrito_fragment();
       vistapreliminarfrag= new vistapreliminarfrag();
        //navigationView =(BottomNavigationView) findViewById(R.id.navigation);

        tabitem1 =findViewById(R.id.tabitem1);

        tabitem2 =findViewById(R.id.tabitem2);

        tabitem3 =findViewById(R.id.tabitem3);
        //Recyclercliente=(RecyclerView) findViewById(R.id.Recyclercliente);

        getSupportFragmentManager().beginTransaction().add(R.id.contenedorfragmentos2,carrito_fragment).commit();







    }

    public void onClicktab(View view) {
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();



        switch (view.getId()){
            case R.id.tabitem1:
                transaction.replace(R.id.contenedorfragmentos2,catalogo);
                break;
            case R.id.tabitem2:
                transaction.replace(R.id.contenedorfragmentos2,carrito_fragment);
                break;
            case R.id.tabitem3:
                transaction.replace(R.id.contenedorfragmentos2,vistapreliminarfrag);
                break;


        }
        transaction.commit();
    }

}



