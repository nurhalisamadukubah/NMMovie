package com.example.nmmovie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class PembayaranActivity extends AppCompatActivity {
    ImageButton tombol_back;
    Button tombol_pesan;
    Intent intent;
    ImageView tombol_profil, poster_film;
    HashMap<String, Integer> poster;
    TextView tv_judul, tv_total_pembayaran, tv_total_harga, tv_daftar_kursi, tv_jam, tv_tanggal, tv_nama_bioskop, tv_tiket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        // membuat list poster dari poster film
        poster = new HashMap<String, Integer>();
        poster.put("TOP GUN: MAVERICK", R.drawable.topgun);
        poster.put("THE DOLL 3", R.drawable.thedoll);
        poster.put("KKN DI DESA PENARI (UNCUT)", R.drawable.kkn);
        poster.put("DORAEMON THE MOVIE: NOBITA`S LITTLE STAR WARS 2021", R.drawable.doraemon);
        poster.put("DOCTOR STRANGE IN THE MULTIVERSE", R.drawable.doctor_strange);

        // deklarasi variabel komponen tampilan
        tv_judul = (TextView) findViewById(R.id.tv_judul);
        tv_total_pembayaran = (TextView) findViewById(R.id.tv_total_pembayaran);
        tv_total_harga = (TextView) findViewById(R.id.tv_total_harga);
        tv_daftar_kursi = (TextView) findViewById(R.id.tv_daftar_kursi);
        tv_jam = (TextView) findViewById(R.id.tv_jam);
        tv_tanggal = (TextView) findViewById(R.id.tv_tanggal);
        tv_nama_bioskop = (TextView) findViewById(R.id.tv_nama_bioskop);
        tv_tiket = (TextView) findViewById(R.id.tv_tiket);
        poster_film = (ImageView) findViewById(R.id.poster_film);

        // mengambil data yang dikirim dari halaman sebelumnya
        intent = getIntent();
        String judul = intent.getStringExtra("judul");
        String nama_bioskop = intent.getStringExtra("nama_bioskop");
        String genre = intent.getStringExtra("genre");
        String jam_tayang = intent.getStringExtra("jam_tayang");
        String daftar_kursi = intent.getStringExtra("daftar_kursi");
        String tiket = intent.getStringExtra("tiket");
        int harga = 35000;
        // rumus total harga pemesanan tikte
        int total_harga = Integer.parseInt(tiket)*harga;

        // menampilkan data ke tampilan
        tv_judul.setText(judul);
        tv_daftar_kursi.setText(daftar_kursi);
        tv_jam.setText(jam_tayang);
        tv_nama_bioskop.setText(nama_bioskop);
        tv_tiket.setText(tiket + " x Rp35.000");
        poster_film.setImageResource(poster.get(judul));

//        ambil tanggal sekarang
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy");
        String tanggal = dateFormat.format(date);
        int total_pembayaran = total_harga+2500;

        // fungsi untuk membuat format rupiah
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        // mengubah total pembayaran dan total harga menjadi format Rp
        String str_total_pembayaran = kursIndonesia.format(total_pembayaran);
        String str_total_harga = kursIndonesia.format(total_harga);

        // menampilkan data ke tampilan
        tv_total_pembayaran.setText(str_total_pembayaran);
        tv_total_harga.setText(str_total_harga);
        tv_tanggal.setText(tanggal);

        // fungsi ke halaman profil
        tombol_profil = (ImageView) findViewById(R.id.tombol_profil);
        tombol_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfilActivity.class);
                startActivity(intent);
            }
        });
        // fungsi tombol kembali ke halaman sebelumnya
        tombol_back = (ImageButton) findViewById(R.id.tombol_back);
        tombol_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tombol_pesan = (Button) findViewById(R.id.tombol_pesan);
        tombol_pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // berpindah ke halaman tiket
                Intent intent = new Intent(getApplicationContext(), TiketActivity.class);
                // mengirim data ke halaman tiket
                intent.putExtra("judul", judul);
                intent.putExtra("nama_bioskop", nama_bioskop);
                intent.putExtra("genre", genre);
                intent.putExtra("penayangan", tanggal + ", " +jam_tayang);
                intent.putExtra("daftar_kursi", daftar_kursi);
                intent.putExtra("total_pembayaran", str_total_pembayaran);
                startActivity(intent);
            }
        });
    }
}
