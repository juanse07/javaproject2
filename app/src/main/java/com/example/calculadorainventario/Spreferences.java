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
    protected final String LANG="lang";
    protected final String PDFPREFERENCE="pdfpreference";
    protected final String PDFPOSITION="pdfposition";
    protected final String TAX1TX="tax1tx";
    protected final String TAX2TX="tax2tx";
    protected final String DISCOUNT="discount";




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

    public java.lang.String getPDFPREFERENCE() {
        return sp.getString(PDFPREFERENCE,"PDFSIMPLE");
    }
    public void setPDFPREFERENCE(String pdfpreference){ sp.edit().putString(PDFPREFERENCE,pdfpreference).commit();}
    public Integer getPDFPOSITION(){return sp.getInt(PDFPOSITION,0);}
    public void setPDFPOSITION(Integer pdfposition){
        sp.edit().putInt(PDFPOSITION,pdfposition).commit();
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
        return sp.getString(RBBORRADOR, "1");

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
    public void setLANG(String lang){sp.edit().putString(LANG,lang).commit();}
    public String getLANG(){return sp.getString(LANG,"en");}
    public  void setTAX1TX(String tax1tx){sp.edit().putString(TAX1TX,tax1tx).commit();}
    public String getTAX1TX(){return sp.getString(TAX1TX,"20");}
    public  void setTAX2TX(String tax2tx){sp.edit().putString(TAX2TX,tax2tx).commit();}
    public String getTAX2TX(){return sp.getString(TAX2TX,"0");}
    public  void setDISCOUNT(String discount){sp.edit().putString(DISCOUNT,discount).commit();}
    public String getDISCOUNT(){return sp.getString(DISCOUNT,"0");}


}
