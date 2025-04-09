package com.example.demo.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;


@Entity
public class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String nomeAventureiro;
    private Classe classe;
    private int level;
    private String listaItensMagicos;
    private String força;
    private String defesa;

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeAventureiro() {
        return nomeAventureiro;
    }

    public Classe getClasse() {
        return classe;
    }

    public int getLevel() {
        return level;
    }

    public String getListaItensMagicos() {
        return listaItensMagicos;
    }

    public String getForça() {
        return força;
    }

    public String getDefesa() {
        return defesa;
    }

    public Personagem(long id, String nome, String nomeAventureiro, Classe classe, int level, String listaItensMagicos, String força, String defesa) {
        this.id = id;
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classe = classe;
        this.level = level;
        this.listaItensMagicos = listaItensMagicos;
        this.força = força;
        this.defesa = defesa;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNomeAventureiro(String nomeAventureiro) {
        this.nomeAventureiro = nomeAventureiro;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setListaItensMagicos(String listaItensMagicos) {
        this.listaItensMagicos = listaItensMagicos;
    }

    public void setForça(String força) {
        this.força = força;
    }

    public void setDefesa(String defesa) {
        this.defesa = defesa;
    }
}
