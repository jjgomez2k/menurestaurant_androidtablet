package com.example.menuv01;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "entradas_table")
//Table named entradas_table containing entradas to differentiate from the class
public class Entradas {

    @PrimaryKey(autoGenerate = true)
    private int entradasid;                                                     //ID entrada
    @NonNull
    private String entradasnome;                                                //Nome entrada
    @NonNull
    private String entradasdetalhe;                                             //Detalhe da entrada
    @NonNull
    private double entradaspreco;

    public Entradas(int entradasid, @NonNull String entradasnome, @NonNull String entradasdetalhe, @NonNull double entradaspreco) {
        this.entradasid = entradasid;
        this.entradasnome = entradasnome;
        this.entradasdetalhe = entradasdetalhe;
        this.entradaspreco = entradaspreco;
    } //set method

    public int getEntradasid() {
        return this.entradasid;
    }                         //get method id

    public String getEntradasnome() {
        return this.entradasnome;
    }                 //get method nome

    public String getEntradasdetalhe() {
        return this.entradasdetalhe;
    }          //get method detalhe

    public double getEntradaspreco() {
        return this.entradaspreco;
    }          //get method detalhe

}
