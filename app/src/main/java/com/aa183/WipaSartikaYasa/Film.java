package com.aa183.WipaSartikaYasa;

import java.util.Date;

public class Film {

    private int idFilm;
    private String judul;
    private Date tanggal;
    private String gambar;
    private String sinopsis;
    private String rating;
    private String link;

    public Film(int idFilm, String judul, Date tanggal, String gambar, String sinopsis, String rating, String link) {
        this.idFilm = idFilm;
        this.judul = judul;
        this.tanggal = tanggal;
        this.gambar = gambar;
        this.sinopsis = sinopsis;
        this.rating = rating;
        this.link = link;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
