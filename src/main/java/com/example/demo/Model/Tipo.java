package com.example.demo.Model;

public enum Tipo {
    ARMA("Arma"),
    ARMADURA("Armadura"),
    AMULETO("Amuleto");

    private String tipo;
    Tipo(String tipo){
        this.tipo = tipo;
    }

    public String getTipo(){
        return tipo;
    }

}
