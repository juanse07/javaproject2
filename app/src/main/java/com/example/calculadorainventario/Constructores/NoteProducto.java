package com.example.calculadorainventario.Constructores;

import java.io.Serializable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="Tabla_Lista_Productos")



public class NoteProducto implements Serializable {



    @PrimaryKey(autoGenerate =true)
    public int Key;


    public String Nombre_prod;
    public Double Cant_prod;
    public Double Precio_prod;
    public Double Resultado_valor;
    public Double Impuesto;
    public Double Resultado_Impuesto;
    public String Descripcion;



public NoteProducto(String Nombre_prod, Double Cant_prod, Double Precio_prod, Double Resultado_valor, Double Impuesto, Double Resultado_Impuesto, String Descripcion) {
     this.Nombre_prod = Nombre_prod;
     this.Precio_prod=Precio_prod;
     this.Cant_prod=Cant_prod;
     this.Resultado_valor=Resultado_valor;
     this.Impuesto=Impuesto;
     this.Resultado_Impuesto=Resultado_Impuesto;
     this.Descripcion=Descripcion;
}


    public void setKey(int key) {
        this.Key = key;
    }



    public String getNombre_prod() {
        return Nombre_prod;
    }
    public Double getCant_Prod() {
        return Cant_prod;
    }
    public Double getPrecio_prod() {
        return Precio_prod;
    }
public String getDescripcion(){return Descripcion;}

    public java.lang.Double getResultado_valor() {
        return Resultado_valor;
    }
    public Double getImpuesto(){return Impuesto;}
    public Double getResultado_Impueto(){return Resultado_Impuesto;}

//ya lo modifique

}
