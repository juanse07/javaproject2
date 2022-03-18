package com.example.calculadorainventario.Dao;

import com.example.calculadorainventario.Constructores.NoteHomis;
import com.example.calculadorainventario.Note;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
@Dao

public interface NoteHomisDao {
    @Insert
    void Insert(NoteHomis noteHomis);
    @Update
    void Update(NoteHomis noteHomis);
    @Delete
    void Delete(NoteHomis noteHomis);

   @Query("SELECT * FROM tabla_lista_Homis")
    LiveData<List<NoteHomis>> getallnotes();
    @Query("DELETE FROM Tabla_Lista_Homis")
    void DeleteAll();
    @Query("SELECT SUM(Valor)FROM Tabla_Lista_Homis")
    LiveData<Double> getTotal();
    @Query("SELECT SUM(Valor)FROM Tabla_Lista_Homis")
    LiveData<Double> getcantTotal();
}
