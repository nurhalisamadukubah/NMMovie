package com.example.nmmovie;
// codingan class Bioskop, yang digunakan untuk membuat object bioskop yang akan di tampilkan dalam list item grid view bioskop
public class Bioskop {
    private long id;
    private String nama;
    private String bioskop;
    private String kota;
    private String alamat;
    private String gambar;

    public Bioskop(long id, String nama, String bioskop, String kota, String alamat, String gambar) {
        this.id = id;
        this.nama = nama;
        this.bioskop = bioskop;
        this.kota = kota;
        this.alamat = alamat;
        this.gambar = gambar;
    }

    public long getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getGambar() { return gambar; }

    public String getKota() {
        return kota;
    }

    public String getBioskop() {
        return bioskop;
    }
}
