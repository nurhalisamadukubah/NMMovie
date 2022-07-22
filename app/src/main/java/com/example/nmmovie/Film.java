package com.example.nmmovie;

// class film untuk membuat object film yang akan di tampilkan dalam list film dari grid view film
public class Film {
    private int _id;
    private String judul;
    private String rate;
    private String durasi;
    private String genre;
    private String director;
    private String sinopsis;
    private String jamtayang;

    public Film(int _id, String judul, String rate, String durasi, String genre, String director, String sinopsis, String jamtayang) {
        this._id = _id;
        this.judul = judul;
        this.rate = rate;
        this.durasi = durasi;
        this.genre = genre;
        this.director = director;
        this.sinopsis = sinopsis;
        this.jamtayang = jamtayang;
    }

    public int getId() {
        return _id;
    }

    public String getJudul() {
        return judul;
    }

    public String getRate() {
        return rate;
    }

    public String getDurasi() {
        return durasi;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public String getJamtayang() {
        return jamtayang;
    }
}
