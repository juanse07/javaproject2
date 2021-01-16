package com.example.calculadorainventario;

import androidx.annotation.NonNull;

public class constspinner {
    @NonNull
    @Override
    public String toString() {
        return Nombre;
    }

    String Nombre;

    public constspinner(String nombre) {
        Nombre = nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}


