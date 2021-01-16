package com.example.calculadorainventario;



import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShareViewModel2 extends ViewModel {
    private MutableLiveData<ArrayList<Note>> Path=new MutableLiveData<>();
    private MutableLiveData<String>Nombrepdf=new MutableLiveData<>();
    private MutableLiveData<String>Productopdf=new MutableLiveData<>();
    private MutableLiveData<CharSequence>Medidapdf=new MutableLiveData<>();
    private MutableLiveData<Double>Medidasuma=new MutableLiveData<>();
    private MutableLiveData<String>Unidadespdf=new MutableLiveData<>();
    private MutableLiveData<String>Valorpdf=new MutableLiveData<>();
    private MutableLiveData<ByteArrayOutputStream> Fechapdf=new MutableLiveData<ByteArrayOutputStream>();
    private MutableLiveData<String>Horapdf=new MutableLiveData<>();
    private MutableLiveData<String>Preciopdf=new MutableLiveData<>();
    private MutableLiveData<String>Precio2=new MutableLiveData<>();
    private MutableLiveData<String>TxValor=new MutableLiveData<>();
    private MutableLiveData<CharSequence> INGRESAR_DATOS = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Double>> Listacue=new MutableLiveData<>();

    public ShareViewModel2(){

    }
    public LiveData<ArrayList<Note>> getPath() {
        return Path;
    }

    public void setPath(ArrayList<Note>ListaPdf) {
        Path.setValue(ListaPdf);
    }

    public LiveData<String>getNombrepdf(){return Nombrepdf;}
    public void setNombrepdf(String nombrepdf){Nombrepdf.setValue(nombrepdf);}

    public LiveData<String>getProductopdf(){return Productopdf;}
    public void setProductopdf(String productopdf){Productopdf.setValue(productopdf);}

    public LiveData<CharSequence>getMedidaepdf(){return Medidapdf;}
    public void setMedidapdf(CharSequence medidapdf){Medidapdf.setValue(medidapdf);}

    public LiveData<String>getUnidadespdf(){return Unidadespdf;}
    public void setUnidadespdf(String unidadespdf){Unidadespdf.setValue(unidadespdf);}

    public LiveData<String>getValorpdf(){return Valorpdf;}
    public void setValorpdf(String valorpdf){Valorpdf.setValue(valorpdf);}

    public LiveData<String>getTxValor(){return TxValor;}
    public void setTxValor(String txvalor){TxValor.setValue(txvalor);}

    public LiveData<ByteArrayOutputStream> getFechapdf(){return Fechapdf;}
    public void setFechapdf(ByteArrayOutputStream fechapdf){Fechapdf.setValue(fechapdf);}

    public LiveData<String>getHorapdf(){return Horapdf;}
    public void setHorapdf(String horapdf){Horapdf.setValue(horapdf);}

    public LiveData<String>getPreciopdf(){return Preciopdf;}
    public void setPreciopdf(String preciopdf){Horapdf.setValue(preciopdf);}

    public LiveData<String>getPrecio2(){return Precio2;}
    public void setPrecio2(String precio2){Precio2.setValue(precio2);}
    public void setINGRESAR_DATOS(CharSequence input) {
        INGRESAR_DATOS.setValue(input);
    }
    public LiveData<Double>getMedidasuma(){return Medidasuma;}
    public void setMedidasuma(Double medidasuma){Medidasuma.setValue(medidasuma);}




    public LiveData<CharSequence> getINGRESAR_DATOS() {
        return INGRESAR_DATOS;
    }
}
