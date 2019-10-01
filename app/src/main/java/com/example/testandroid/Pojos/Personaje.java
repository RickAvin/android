package com.example.testandroid.Pojos;

public class Personaje {

    private String amiiboSeries;
    private String character;
    private String gameSeries;
    private String head;
    private String image;
    private String name;
    private Relase release;
    private String tail;
    private String type;

    public String getAmiiboSeries() {
        return amiiboSeries;
    }

    public void setAmiiboSeries(String amiiboSeries) {
        this.amiiboSeries = amiiboSeries;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getGameSeries() {
        return gameSeries;
    }

    public void setGameSeries(String gameSeries) {
        this.gameSeries = gameSeries;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Relase getRelease() {
        return release;
    }

    public void setRelease(Relase release) {
        this.release = release;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Personaje{" +
                "amiiboSeries='" + amiiboSeries + '\'' +
                ", character='" + character + '\'' +
                ", gameSeries='" + gameSeries + '\'' +
                ", head='" + head + '\'' +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", release=" + release +
                ", tail='" + tail + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
