package com.example.calculadorainventario;

import android.content.Context;
import android.content.SharedPreferences;

public class Spreferences {
    protected final String DIAS="dias";
    protected final String TIPO="tipo";
    protected final String RBBORRADOR="rbborrador";
    protected final String RBVENTA="rbcompra";
    protected final String RBCOMPRA="rbventa";
    protected final String POSITIONCATALOGO="positioncatalogo";



    private Context context;
    private SharedPreferences sp;

    public Spreferences(Context context){
        this.context=context;
        sp=context.getSharedPreferences(context.getPackageName(),Context.MODE_PRIVATE);
    }
    ////dias//////
    public void setDIAS(String dias){
        sp.edit().putString(DIAS,dias).commit();
    }
    public String getDIAS(){
        return sp.getString(DIAS, "0");
    }
    public void setTIPO(String tipo){
        sp.edit().putString(TIPO,tipo).commit();
    }
    public String getTIPO(){
        return sp.getString(TIPO, "n/a");
    }
    public void setRBBORRADOR(String rbborrador){
        sp.edit().putString(RBBORRADOR,rbborrador).commit();
    }
    public String getRBBORRADOR(){
        return sp.getString(RBBORRADOR, "Venta");

    }
    public void setpositioncatalogo(String dias){
        sp.edit().putString(POSITIONCATALOGO,"positioncatalogo").commit();
    }
    public String getpositioncatalogo(){
        return sp.getString(POSITIONCATALOGO, "n/a");
    }



}
