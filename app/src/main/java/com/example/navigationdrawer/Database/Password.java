package com.example.navigationdrawer.Database;

public class Password {

    private String id;
    private String nombre;
    private String url;
    private String usuario;
    private String contra;

    public Password(String nombre, String url, String usuario, String contra){
        this.nombre = nombre;
        this.url = url;
        this.usuario = usuario;
        this.contra = contra;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUrl() {
        return url;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContra() {
        return contra;
    }


}
