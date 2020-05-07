package com.example.menuv01;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "principal_table")
//Table named entradas_table containing entradas to differentiate from the class
public class Principal {

    @PrimaryKey(autoGenerate = true)
    private int principalid;                                                     //ID entrada
    @NonNull
    private String principalnome;                                                //Nome entrada
    @NonNull
    private String principaldetalhe;                                             //Detalhe da entrada
    @NonNull
    private double principalpreco;

    public Principal(int principalid, @NonNull String principalnome, @NonNull String principaldetalhe, @NonNull double principalpreco) {
        this.principalid = principalid;
        this.principalnome = principalnome;
        this.principaldetalhe = principaldetalhe;
        this.principalpreco = principalpreco;
    } //set method

    public int getPrincipalid() {
        return this.principalid;
    }                         //get method id

    public String getPrincipalnome() {
        return this.principalnome;
    }                 //get method nome

    public String getPrincipaldetalhe() {
        return this.principaldetalhe;
    }          //get method detalhe

    public double getPrincipalpreco() {
        return this.principalpreco;
    }                         //get method preco

}