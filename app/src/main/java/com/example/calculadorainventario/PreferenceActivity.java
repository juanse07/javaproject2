package com.example.calculadorainventario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class PreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorq,new PreferenceFragment()).commit();
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.backpreference:
                finish();
                //onBackPressed();
                break;
        }
    }
}
