package com.example.menuv01;

public class CartItems {

    public String Nome;
    public String Preco;
    public String Tipo;
    public String Quantidade;

    public CartItems(String Nome, String Tipo, String Preco, String Quantidade) {
        this.Nome = Nome;
        this.Tipo = Tipo;
        this.Preco = Preco;
        this.Quantidade = Quantidade;
    }

    public String getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(String quantidade) {
        Quantidade = quantidade;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getPreco() {
        return Preco;
    }

    public void setPreco(String Preco) {
        this.Preco = Preco;
    }

    public String getJsonObject() {
        return "{Tipo:" + Tipo + ",Nome:" + Nome + ",Quantidade:" + Quantidade + ",Preco:" + Preco + "}";
    }
}
