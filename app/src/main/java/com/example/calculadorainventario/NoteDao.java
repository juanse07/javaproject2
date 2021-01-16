package com.example.calculadorainventario;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao

public interface NoteDao {
@Insert
    void Insert(Note note);
@Update
void Update(Note note);
@Delete
void Delete(Note note);
@Query("DELETE   FROM Tabla_Lista_Medidas WHERE `Key`=(SELECT MAX(`Key`)FROM Tabla_Lista_Medidas)" )

void DeleteLast();
//@Query("SELECT SUM(valor_Medida)FROM TABLA_LISTA_MEDIDAS")
//int SumTablaMedida();
@Query("SELECT * FROM Tabla_Lista_Medidas ORDER BY `Key` DESC LIMIT 1")
LiveData<List<Note>> getLastnote();
@Query("DELETE FROM Tabla_Lista_Medidas")
void DeleteAll();
@Query("SELECT * FROM tabla_lista_medidas")
LiveData<List<Note>> getallnotes();
@Query("SELECT valor_Medida FROM Tabla_Lista_Medidas")
LiveData<List<Double>>getallDoublenotes();

//@Query("SELECT COALESCE(sum(COALESCE(valor_Medida,0)), 0) From Tabla_Lista_Medidas")
@Query("SELECT SUM(valor_Medida)FROM TABLA_LISTA_MEDIDAS")
    LiveData<Double> getTotal();
@Query("SELECT AVG(valor_Medida)FROM TABLA_LISTA_MEDIDAS")
    LiveData<Double>getPromed();
@Query("SELECT COUNT(*) FROM Tabla_Lista_Medidas")
    LiveData<Integer>getCount();
}
