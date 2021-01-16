package com.example.calculadorainventario;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.versionedparcelable.ParcelField;

import java.io.Serializable;


@Entity(tableName="Tabla_Lista_Medidas")



public class Note implements Serializable{



    @PrimaryKey(autoGenerate =true)
    public int Key;



    public Double valor_Medida;



    public Note(Double valor_Medida) {
       this.valor_Medida = valor_Medida;
    }




    public void setKey(int key) {
        this.Key = key;
    }



    public Double getValor_Medida() {
        return valor_Medida;
    }
}
