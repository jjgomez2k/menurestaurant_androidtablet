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
public interface PrincipalDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPrincipal(Principal principal);    //Insert entradas (1)

    @Update
    void updatePrincipal(Principal principal);     //Update entradas

    @Delete
    void deletePrincipal(Principal principal);     //Delete entradas

    @Query("DELETE FROM principal_table")
    void deleteAllPrincipal();                                               //Delete all entradas

    @Query("SELECT * from principal_table ORDER BY principalnome ASC")
    LiveData<List<Principal>> getAllPrincipal();                                  //Select all entradas

}
