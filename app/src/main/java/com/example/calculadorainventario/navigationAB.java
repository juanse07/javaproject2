package com.example.calculadorainventario;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static android.view.MenuItem.*;

public abstract class navigationAB extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener  {
    protected BottomNavigationView navigationView;
TextView txhome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navbotone);//posible error
        txhome=(TextView) findViewById(R.id.txhome) ;
        navigationView = (BottomNavigationView) findViewById(R.id.navigation);

        navigationView.setSelectedItemId(R.id.action_home);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.txhome:
                        startActivity(new Intent(getApplicationContext(),ingresodat.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.action_home:
                        startActivity(new Intent(getApplicationContext(),ingresodat.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.action_calc:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }

                return false;
            }
        });
    }}



