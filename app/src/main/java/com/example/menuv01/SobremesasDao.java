package com.example.menuv01;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SobremesasDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSobremesas(Sobremesas sobremesas);    //Insert entradas (1)

    @Update
    void updateSobremesas(Sobremesas sobremesas);     //Update entradas

    @Delete
    void deleteSobremesas(Sobremesas sobremesas);     //Delete entradas

    @Query("DELETE FROM sobremesas_table")
    void deleteAllSobremesas();                                               //Delete all entradas

    @Query("SELECT * from sobremesas_table ORDER BY sobremesasnome ASC")
    LiveData<List<Sobremesas>> getAllSobremesas();                                  //Select all entradas

}
