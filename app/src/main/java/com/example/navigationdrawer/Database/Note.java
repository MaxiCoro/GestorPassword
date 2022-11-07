package com.example.navigationdrawer.Database;

public class Note {
    private String id;
    private String titulo;
    private String descripcion;

    public Note(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
