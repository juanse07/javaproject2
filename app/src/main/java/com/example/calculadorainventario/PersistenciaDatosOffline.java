package com.example.calculadorainventario;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class PersistenciaDatosOffline extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
