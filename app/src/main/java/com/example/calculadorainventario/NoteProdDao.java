package com.example.calculadorainventario;

import java.security.Key;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao

public interface NoteProdDao {
    @Insert
    void Insert(NoteProducto noteProducto);
    @Update
    void Update(NoteProducto noteProducto);
    @Delete
    void Delete(NoteProducto noteProducto);
//    @Query("DELETE   FROM Tabla_Lista_Productos WHERE `Key`=(SELECT MAX(`Key`)FROM Tabla_Lista_Productos)" )
//    @Query("DELETE FROM Tabla_Lista_Productos  WHERE " +
//            Key + " = " + position + ";")

//    void DeleteLast();
    //@Query("SELECT SUM(valor_Medida)FROM TABLA_LISTA_MEDIDAS")
//int SumTablaMedida();
    @Query("SELECT * FROM Tabla_Lista_Productos ORDER BY `Key` DESC LIMIT 1")
    LiveData<List<NoteProducto>> getLastnote();
    @Query("DELETE FROM Tabla_Lista_Productos")
    void DeleteAll();
    @Query("SELECT * FROM tabla_lista_Productos")
    LiveData<List<NoteProducto>> getallnotes();
//    @Query("SELECT valor_Medida FROM Tabla_Lista_Productos")
//    LiveData<List<Double>>getallDoublenotes();
//
//    //@Query("SELECT COALESCE(sum(COALESCE(valor_Medida,0)), 0) From Tabla_Lista_Medidas")
   @Query("SELECT SUM(Cant_prod)FROM Tabla_Lista_Productos")
    LiveData<Double> getTotal();
    @Query("SELECT SUM(Cant_prod)FROM Tabla_Lista_Productos WHERE `Nombre_prod`= 'Bonyourt'")
    LiveData<Integer> getsumProdnom();
//    @Query("SELECT AVG(valor_Medida)FROM Tabla_Lista_Productos")
//    LiveData<Double>getPromed();
//    @Query("SELECT COUNT(*) FROM Tabla_Lista_Productos")
//    LiveData<Integer>getCount();
    //ya lo modifique
}


