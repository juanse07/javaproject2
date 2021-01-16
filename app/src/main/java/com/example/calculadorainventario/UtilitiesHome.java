package com.example.calculadorainventario;

public class UtilitiesHome {
    public static final String Tabla_Home="HomeTabla";
    public static final String Key="Key";
    public static final String Medida="medida";
    public static final String Cliente="cliente";
    public static final String Fecha="fecha";
    public static final String Hora="hora";
    public static final String Precio="precio";
    public static final String Producto="producto";
    public static final String Unidades="unidades";
    public static final String Valor="valor";
    public static final String pdfurl="pdfurl";
    public static final String Estado="estado";
    public static final String Dias_plazo="diasplazo";
    public static final String Fechaparapago="fechapago";
    // public final static String CREAR_TABLA_MEDIDA="CREATE TABLE"+Tabla_Medida+"("Medida TEXT)+";
    public static final String CREAR_TABLA_HOME = "CREATE TABLE " + Tabla_Home + "("  +
            Key + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Fecha + " TEXT,"+Fechaparapago+"TEXT,"+Cliente+"TEXT,"+Producto+"TEXT,"+Medida+"TEXT,"+Unidades+"TEXT,"+Precio+"TEXT,"
        +Valor+"TEXT,"+Estado+"TEXT,"+pdfurl+"TEXT,"+Dias_plazo+"TEXT,"+Hora+"TEXT)";


}
