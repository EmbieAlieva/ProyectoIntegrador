package com.ekaitz_torregrosa.memoryapp.models;

import io.realm.RealmObject;

public class PuntuacionBD extends RealmObject {

    private int puntos;
    private int movimientos;
    private String tiempo;
    private String usuario;
    private String dificultad;

    public PuntuacionBD(){

    }

    public PuntuacionBD(int puntos, int movimientos, String tiempo, String usuario, String dificultad) {
        this.puntos = puntos;
        this.movimientos = movimientos;
        this.tiempo = tiempo;
        this.usuario = usuario;
        this.dificultad = dificultad;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public int getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(int movimientos) {
        this.movimientos = movimientos;
    }
}
