package com.example.menuv01;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao        //Containing all the actions the program can do on the DB
public interface EntradasDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertEntrada(Entradas entradas);    //Insert entradas (1)

    @Update
    void updateEntrada(Entradas entradas);     //Update entradas

    @Delete
    void deleteEntrada(Entradas entradas);     //Delete entradas

    @Query("DELETE FROM entradas_table")
    void deleteAllEntradas();                                               //Delete all entradas

    @Query("SELECT * from entradas_table ORDER BY entradasnome ASC")
    LiveData<List<Entradas>> getAllEntradas();                                  //Select all entradas

}
