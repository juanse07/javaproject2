package com.example.calculadorainventario;

public class Utilities {
    public static final String Tabla_Medida="MatrizLista";
    public static final String KEY_ID="KEY_ID";
    public static final String Medida="medida";
   // public final static String CREAR_TABLA_MEDIDA="CREATE TABLE"+Tabla_Medida+"("Medida TEXT)+";
    public static final String CREAR_TABLA_MEDIDA = "CREATE TABLE " + Tabla_Medida + "("  +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
           Medida + " TEXT)";



}
