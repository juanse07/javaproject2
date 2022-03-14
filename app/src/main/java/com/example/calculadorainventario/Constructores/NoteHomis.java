package com.example.calculadorainventario.Constructores;

import java.io.Serializable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="Tabla_Lista_Homis")

public class NoteHomis implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int Key;

    String Cliente;
    String Fecha;
    String Hora;
    Double Precio;
    Double Medida;
    String Producto;
    String Unidades;
    Double Valor;
    String pdfurl;
    String Estado;




  int dias_plazo;
    String Fechaparapago;


    public NoteHomis() {
    }

    public void setMedida(Double medida) {
        Medida = medida;
    }

    public void setValor(Double valor) {
        Valor = valor;
    }

    public void setPrecio(Double precio) {
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
