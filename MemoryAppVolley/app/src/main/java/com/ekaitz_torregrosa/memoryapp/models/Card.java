package com.ekaitz_torregrosa.memoryapp.models;

public class Card {

    private int id;
    private int numPareja;
    private int image;
    private int imageEmparejada;
    private boolean volteada = false;
    private boolean emparejada = false;

    public Card(int image, int imageEmparejada, int id, int numPareja) {
        this.image = image;
        this.imageEmparejada = imageEmparejada;
        this.id = id;
        this.numPareja = numPareja;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumPareja() {
        return numPareja;
    }

    public void setNumPareja(int numPareja) {
        this.numPareja = numPareja;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImageEmparejada() {
        return imageEmparejada;
    }

    public void setImageEmparejada(int imageEmparejada) {
        this.imageEmparejada = imageEmparejada;
    }

    public boolean isVolteada() {
        return volteada;
    }

    public void setVolteada(boolean volteada) {
        this.volteada = volteada;
    }

    public boolean isEmparejada() {
        return emparejada;
    }

    public void setEmparejada(boolean emparejada) {
        this.emparejada = emparejada;
    }
}
