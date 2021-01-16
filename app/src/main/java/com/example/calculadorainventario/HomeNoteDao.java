package com.example.calculadorainventario;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


@Dao

public interface HomeNoteDao {
    @Insert
    void Insert(HomeNote homeNote);
    @Update
    void Update(HomeNote homeNote);
    @Delete
    void Delete(HomeNote homeNote);
    @Query("SELECT * FROM TablasqlHome")
    LiveData<List<HomeNote>> getallHomenotes();
    @Query("DELETE FROM TablasqlHome")
    void deleteallHome();
    @Query("SELECT*FROM TablasqlHome")
            LiveData<List<HomeNote>> getallventas();
    @Query("SELECT COUNT(*) FROM TablasqlHome WHERE Estado LIKE '%Venta%'")
    public [] getFooSearch(String searchTerm);
    LiveData<Integer>getCountVenta();
    //String query = "SELECT * FROM " + TABLE_HIGHLIGHTS+ " WHERE " + KEY_BOOKNAME + " = '" + bookname + "'";
}
