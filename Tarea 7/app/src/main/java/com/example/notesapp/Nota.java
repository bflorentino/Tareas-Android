package com.example.notesapp;

public class Nota {

    private int id;
    private String titulo;
    private String cuerpo;

    public Nota(){}

    public Nota(String titulo, String cuerpo){
        this.titulo = titulo;
        this.cuerpo = cuerpo;
    }

    public int getId(){
        return id;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getCuerpo(){
        return cuerpo;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public void setCuerpo(String cuerpo){
        this.cuerpo = cuerpo;
    }
}
