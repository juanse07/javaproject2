package com.example.calculadorainventario.Constructores;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

import androidx.annotation.StringRes;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="Tabla_Lista_Homis")

public class NoteHomis implements Serializable {


    @Exclude
    @StringRes
    @PrimaryKey(autoGenerate = true)
  public int Key;
    @Exclude
    public  String Cliente;
    @Exclude
   public String Fecha;
    @Exclude
   public String Hora;

    @Exclude

  public  Double Precio;
    @Exclude
    public String Medida;
    @Exclude
    public String Producto;
    @Exclude
    public String Unidades;
    @Exclude
    public Double Valor;
    @Exclude
    public String pdfurl;
    @Exclude
    public String Estado;
    @Exclude
    public String Dias_Plazo;
    @Exclude
   public String Fechaparapago;
    @Exclude
    public String Key_fire;


    public NoteHomis() {
    }

    public void setMedida(String medida) {
       this.Medida = medida;
    }

    public void setValor(Double valor) {
       this.Valor = valor;
    }

    public void setPrecio(Double precio) {
      this.Precio = precio;
    }

    public void setCliente(String cliente) {
       this.Cliente = cliente;
    }

    public void setProducto(String producto) {
       this.Producto = producto;
    }

    public void setEstado(String estado) {
       this. Estado = estado;
    }

    public void setFecha(String fecha) {
        this.Fecha = fecha;
    }

    public void setFechaparapago(String fechaparapago) {
        this.Fechaparapago = fechaparapago;
    }



    public void setPdfurl(String pdfurl) {
        this.pdfurl = pdfurl;
    }

    public void setKey_fire(String key_fire) {
        Key_fire = key_fire;
    }
    //    public void setDias_Plazo(int dias_plazo) {
//        this.Dias_Plazo = dias_plazo;
//    }



    public String getMedida() {
        return Medida;
    }

    public Double getValor() {
        return Valor;
    }

    public Double getPrecio() {
        return Precio;
    }

    public String getCliente() {
        return Cliente;
    }

    public String getProducto() {
        return Producto;
    }

    public String getEstado() {
        return Estado;
    }

    public String getFecha() {
        return Fecha;
    }

    public String getFechaparapago() {
        return Fechaparapago;
    }
    public String getKey_fire(){return Key_fire;}



    public String getPdfurl() {
        return pdfurl;
    }

//    public int getDias_Plazo() {
//        return Dias_Plazo;
//    }

    public int getKey() {
        return Key;
    }

    public void setKey(int key) {
        Key = key;
    }
}
