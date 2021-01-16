package com.example.calculadorainventario;

public class MatrizLista {
    public MatrizLista() {
    }

    private String medida;

    private Integer KEY_ID;

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public Integer getKEY_ID() {
        return KEY_ID;
    }

    public void setKEY_ID(Integer KEY_ID) {
        this.KEY_ID = KEY_ID;
    }

    public MatrizLista(String medida, Integer KEY_ID) {
        this.medida = medida;
        this.KEY_ID = KEY_ID;
    }
}
