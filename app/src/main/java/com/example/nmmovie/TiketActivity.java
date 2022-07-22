package com.example.nmmovie;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nmmovie.Session.SessionManager;

import java.util.HashMap;

public class TiketActivity extends AppCompatActivity {
    SessionManager sessionManager;
    Button tombol_beranda;
    ImageView tombol_profil, poster_film;
    TextView tv_judul, tv_genre, tv_nama_bioskop, tv_penayangan, tv_kursi, tv_nama, tv_total_pembayaran;
    HashMap<String, Integer> poster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiket);

        poster = new HashMap<String, Integer>();
        poster.put("TOP GUN: MAVERICK", R.drawable.topgun);
        poster.put("THE DOLL 3", R.drawable.thedoll);
        poster.put("KKN DI DESA PENARI (UNCUT)", R.drawable.kkn);
        poster.put("DORAEMON THE MOVIE: NOBITA`S LITTLE STAR WARS 2021", R.drawable.doraemon);
        poster.put("DOCTOR STRANGE IN THE MULTIVERSE", R.drawable.doctor_strange);

        Intent intent = getIntent();
        tv_judul = (TextView) findViewById(R.id.tv_judul);
        tv_genre = (TextView) findViewById(R.id.tv_genre);
        tv_nama_bioskop = (TextView) findViewById(R.id.tv_nama_bioskop);
        tv_penayangan = (TextView) findViewById(R.id.tv_penayangan);
        tv_kursi = (TextView) findViewById(R.id.tv_kursi);
        tv_nama = (TextView) findViewById(R.id.tv_nama);
        tv_total_pembayaran = (TextView) findViewById(R.id.tv_total_pembayaran);
        poster_film = (ImageView) findViewById(R.id.poster_film);

        // ambil data nama lengkap user dari session
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> userDetail = sessionManager.getUserDetails();
        String user_namalengkap = userDetail.get("namalengkap");
        tv_nama.setText(user_namalengkap);

        // ambil data yang dikirim dari halaman pembayaran
        String judul = intent.getStringExtra("judul");
        String genre = intent.getStringExtra("genre");
        String bioskop = intent.getStringExtra("nama_bioskop");
        String penayangan = intent.getStringExtra("penayangan");
        String kursi = intent.getStringExtra("daftar_kursi");
        String total_pembayaran = intent.getStringExtra("total_pembayaran");

        // menampilkan data pesanan ke ui
        tv_judul.setText(judul);
        tv_genre.setText(genre);
        tv_nama_bioskop.setText(bioskop);
        tv_penayangan.setText(penayangan);
        tv_kursi.setText(kursi);
        tv_total_pembayaran.setText(total_pembayaran);
        poster_film.setImageResource(poster.get(judul));

        // fungsi berpindah ke home ketika tombol ke beranda ditekan
        tombol_beranda = (Button) findViewById(R.id.tombol_beranda);
        tombol_beranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
        // fungsi ke halaman profil
        tombol_profil = (ImageView) findViewById(R.id.tombol_profil);
        tombol_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfilActivity.class);
                startActivity(intent);
            }
        });
    }
}
