package com.example.menuv01;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sobremesas_table")
//Table named entradas_table containing entradas to differentiate from the class
public class Sobremesas {

    @PrimaryKey(autoGenerate = true)
    private int sobremesasid;                                                     //ID entrada
    @NonNull
    private String sobremesasnome;                                                //Nome entrada
    @NonNull
    private String sobremesasdetalhe;                                             //Detalhe da entrada
    @NonNull
    private double sobremesaspreco;

    public Sobremesas(int sobremesasid, @NonNull String sobremesasnome, @NonNull String sobremesasdetalhe, @NonNull double sobremesaspreco) {
        this.sobremesasid = sobremesasid;
        this.sobremesasnome = sobremesasnome;
        this.sobremesasdetalhe = sobremesasdetalhe;
        this.sobremesaspreco = sobremesaspreco;
    } //set method

    public int getSobremesasid() {
        return this.sobremesasid;
    }                         //get method id

    public String getSobremesasnome() {
        return this.sobremesasnome;
    }                 //get method nome

    public String getSobremesasdetalhe() {
        return this.sobremesasdetalhe;
    }          //get method detalhe

    public double getSobremesaspreco() {
        return this.sobremesaspreco;
    }          //get method detalhe


}
