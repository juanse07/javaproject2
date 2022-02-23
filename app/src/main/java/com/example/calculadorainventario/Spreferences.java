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
    protected final String COMPANYNAME="companyname";
    protected final String COMPANYPHONE="companyphone";
    protected final String COMPANYEMAIL="companyemail";
    protected final String ADDRESSNAME="addressname";




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

    public void setCompanyname(String companyname){sp.edit().putString(COMPANYNAME,companyname).commit();}
    public String getCompanyname(){return sp.getString(COMPANYNAME,"PyMESoft2");}
    public void setAddressname(String addressname){sp.edit().putString(ADDRESSNAME,addressname).commit();}
    public String getAdressname(){return sp.getString(ADDRESSNAME,"Address");}

    public void setCompanyphone(String companyphone){sp.edit().putString(COMPANYPHONE,companyphone).commit();}
    public String getCompanyphone(){return sp.getString(COMPANYPHONE,"PyMESoft2");}
    public void setCOMPANYEMAIL(String companyemail){sp.edit().putString(COMPANYEMAIL,companyemail).commit();}
    public String getCOMPANYEMAIL(){return sp.getString(COMPANYEMAIL,"Address");}

}
