package com.example.calculadorainventario;

import java.io.Serializable;
import java.util.Date;
import androidx.versionedparcelable.ParcelField;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TablasqlHome")

public class HomeNote implements Serializable {


    @PrimaryKey(autoGenerate = true)

    public int Key;
    public Double Medida;
    public Double Valor;
    public int Precio;
    public String Cliente;
    public String  Producto;
    public String Estado;
    public String Fecha;
    public String Fechaparapago;
    //public Date Hora;
    public int Unidades;
    public String pdfurl;
    public int dias_plazo;

    public HomeNote() {
    }

    public void setMedida(Double medida) {
        Medida = medida;
    }

    public void setValor(Double valor) {
        Valor = valor;
    }

    public void setPrecio(int precio) {
        Precio = precio;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public void setProducto(String producto) {
        Producto = producto;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public void setFechaparapago(String fechaparapago) {
        Fechaparapago = fechaparapago;
    }

    public void setUnidades(int unidades) {
        Unidades = unidades;
    }

    public void setPdfurl(String pdfurl) {
        this.pdfurl = pdfurl;
    }

    public void setDias_plazo(int dias_plazo) {
        this.dias_plazo = dias_plazo;
    }



    public Double getMedida() {
        return Medida;
    }

    public Double getValor() {
        return Valor;
    }

    public int getPrecio() {
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

    public int getUnidades() {
        return Unidades;
    }

    public String getPdfurl() {
        return pdfurl;
    }

    public int getDias_plazo() {
        return dias_plazo;
    }

    public void setKey(int key) {
        Key = key;
    }


}
