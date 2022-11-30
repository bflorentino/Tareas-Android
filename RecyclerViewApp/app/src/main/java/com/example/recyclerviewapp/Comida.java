package com.example.recyclerviewapp;

public class Comida {

    private String nombre;
    private String tipo;
    private int imagenValor;
    private int id;

    public Comida(){

    }

    public Comida(String nombre, String tipo, int imagenValor) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.imagenValor = imagenValor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getImagenValor() {
        return imagenValor;
    }

    public void setImagenValor(int imagenValor) {
        this.imagenValor = imagenValor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
