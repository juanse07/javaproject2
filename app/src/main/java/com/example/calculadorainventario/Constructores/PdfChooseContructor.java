package com.example.calculadorainventario.Constructores;

import androidx.appcompat.widget.ActivityChooserView;

public class PdfChooseContructor {

    String Tittle;
    Integer Imagepdf;
    public PdfChooseContructor(Integer Imagepdf,String Tittle) {
        this.Imagepdf = Imagepdf;
        this.Tittle = Tittle;

    };



    public String gettittle(){return Tittle;}
    public Integer getImage(){return  Imagepdf;}
    public void setTittle(String tittle){Tittle=tittle;}
    public void setImage(Integer image){Imagepdf=image;};
}
