package com.example.calculadorainventario;

import java.io.Serializable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="Tabla_Lista_Productos")



public class NoteProducto implements Serializable {



    @PrimaryKey(autoGenerate =true)
    public int Key;


    public String Nombre_prod;
    public String Cant_prod;
    public String Precio_prod;
    public Double Resultado_valor;



public NoteProducto(String Nombre_prod, String Cant_prod, String Precio_prod,Double Resultado_valor) {
     this.Nombre_prod = Nombre_prod;
     this.Precio_prod=Precio_prod;
     this.Cant_prod=Cant_prod;
     this.Resultado_valor=Resultado_valor;
}


    public void setKey(int key) {
        this.Key = key;
    }



    public String getNombre_prod() {
        return Nombre_prod;
    }
    public String getCant_Prod() {
        return Cant_prod;
    }
    public String getPrecio_prod() {
        return Precio_prod;
    }


    public java.lang.Double getResultado_valor() {
        return Resultado_valor;
    }
//ya lo modifique

}
