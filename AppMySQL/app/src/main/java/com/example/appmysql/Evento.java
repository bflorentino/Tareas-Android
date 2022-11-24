package com.example.appmysql;

import java.util.Date;

public class Evento {

    private String titulo;
    private String descripcion;
    private String fecha;
    private int id;

    public Evento(){}

    public Evento(String titulo, String descripcion, String fecha){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    // GETTERS
    public String getTitulo(){
        return titulo;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public String getFecha(){
        return fecha;
    }

    public int getId(){
        return id;
    }

    // SETTERS
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public void setFecha(String fecha){
        this.fecha = fecha;
    }

    public void setId(int id){
        this.id = id;
    }
}
