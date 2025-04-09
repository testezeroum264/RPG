package com.example.demo.Model;

public enum Classe {
    GUERREIRO("Guerreiro"),
    MAGO("Mago"),
    ARQUEIRO("Arqueiro"),
    LADINO("Ladino"),

    BARDO("Bardo");

    private String classe;
    Classe(String classe){
        this.classe = classe;
    }

    public String getClasse(){
        return classe;
    }
}
