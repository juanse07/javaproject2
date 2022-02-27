package com.example.calculadorainventario.Constructores;

import androidx.annotation.NonNull;

public class cuerospinner {
    String TIPO_CUERO;
    String Impuesto;
    String Descripcion;

    public String getDescripcion(){return Descripcion;}
    public void setDescripcion(String descripcion){Descripcion=descripcion;}

    public String getEstado_Imp() {
        return Estado_Imp;
    }

    public void setEstado_Imp(String estado_Imp) {
        Estado_Imp = estado_Imp;
    }

    String Estado_Imp;

    String Ritmo;

    public String getImpuesto() {
        return Impuesto;
    }

    public void setImpuesto(String impuesto) {
        Impuesto = impuesto;
    }

    public String getRitmo() {
        return Ritmo;
    }

    public void setRitmo(String ritmo) {
        Ritmo = ritmo;
    }

    String Key;

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    String Precio;

    public cuerospinner() {
    }

    public String getTIPO_CUERO() {
        return TIPO_CUERO;
    }

    public void setTIPO_CUERO(String TIPO_CUERO) {
        this.TIPO_CUERO = TIPO_CUERO;
    }
    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
}
