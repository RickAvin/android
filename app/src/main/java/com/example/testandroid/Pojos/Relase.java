package com.example.testandroid.Pojos;

public class Relase {

    private String au;
    private String eu;
    private String jp;
    private String na;

    public String getAu() {
        return au;
    }

    public void setAu(String au) {
        this.au = au;
    }

    public String getEu() {
        return eu;
    }

    public void setEu(String eu) {
        this.eu = eu;
    }

    public String getJp() {
        return jp;
    }

    public void setJp(String jp) {
        this.jp = jp;
    }

    public String getNa() {
        return na;
    }

    public void setNa(String na) {
        this.na = na;
    }

    @Override
    public String toString() {
        return "Relase{" +
                "au='" + au + '\'' +
                ", eu='" + eu + '\'' +
                ", jp='" + jp + '\'' +
                ", na='" + na + '\'' +
                '}';
    }
}
