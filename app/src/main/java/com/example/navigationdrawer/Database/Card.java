package com.example.navigationdrawer.Database;

public class Card {

    private String id;
    private String titular;
    private String tipo;
    private String nroTarjeta;
    private String inicioTarjeta;
    private String finTarjeta;
    private String extra;

    public Card(String titular, String tipo, String nroTarjeta, String inicioTarjeta, String finTarjeta, String extra){
        this.titular = titular;
        this.tipo = tipo;
        this.nroTarjeta = nroTarjeta;
        this.inicioTarjeta = inicioTarjeta;
        this.finTarjeta = finTarjeta;
        this.extra = extra;
    }

    public String getExtra() {
        return extra;
    }

    public String getFinTarjeta() {
        return finTarjeta;
    }

    public String getInicioTarjeta() {
        return inicioTarjeta;
    }

    public String getNroTarjeta() {
        return nroTarjeta;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTitular() {
        return titular;
    }
}
