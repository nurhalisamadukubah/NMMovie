package com.example.nmmovie;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class FilmActivity extends AppCompatActivity {
    DBHelper dbHelper;
    ArrayList<Film> filmArrayList;
    FilmListAdapter filmListAdapter;
    Intent intent;
    HashMap<String, Integer> poster;
    // deklarasi variabel untuk komponen dalam tampilan
    ImageButton tombol_back;
    GridView gv_film;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);

        // membuat list poster dari poster-poster film
        poster = new HashMap<String, Integer>();
        poster.put("TOP GUN: MAVERICK", R.drawable.topgun);
        poster.put("THE DOLL 3", R.drawable.thedoll);
        poster.put("KKN DI DESA PENARI (UNCUT)", R.drawable.kkn);
        poster.put("DORAEMON THE MOVIE: NOBITA`S LITTLE STAR WARS 2021", R.drawable.doraemon);
        poster.put("DOCTOR STRANGE IN THE MULTIVERSE", R.drawable.doctor_strange);

        // mengambil nilai yang dikirim dari halaman sebelumnya
        intent = getIntent();
        String nama = intent.getStringExtra("nama");
        String bioskop = intent.getStringExtra("bioskop");

        gv_film = (GridView) findViewById(R.id.gv_film);
        filmArrayList = new ArrayList<>();
        // this mengarah ke classnya sendiri, R.layout.film itu ui item yang akan digunakan untuk list di grid view film, poster itu list dari poster film
        filmListAdapter = new FilmListAdapter(this, R.layout.film, filmArrayList, poster);
        gv_film.setAdapter(filmListAdapter);
        dbHelper = new DBHelper(this);

        // mengambil seluruh data film berdasarkan bioskop yang dipilih
        Cursor cursor = dbHelper.ambilDataFilmBerdasarkanBioskop(nama);
        filmArrayList.clear();
        while (cursor.moveToNext()) {
            // mengambil data dari film
            int id = cursor.getInt(0);
            String judul = cursor.getString(1);
            String rate = cursor.getString(2);
            String durasi = cursor.getString(3);
            String genre = cursor.getString(4);
            String director = cursor.getString(5);
            String sinopsis = cursor.getString(6);
            String jamtayang = cursor.getString(7);

            // memasukkan data ke dalam list film untuk di tampilkan dalam grid view
            filmArrayList.add(new Film(id, judul, rate, durasi, genre, director, sinopsis, jamtayang));
        }
        // merefresh tampilan ketika ada perubahan data dalam list film
        filmListAdapter.notifyDataSetChanged();

        gv_film.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            // fungsi untuk berpindah ke halaman detail film ketika card film di klik
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailFilmActivity.class);
                // mengirimkan variabel ke halaman selanjutnya
                intent.putExtra("id", filmArrayList.get(position).getId());
                intent.putExtra("nama_bioskop" ,bioskop + " - " + nama);
                // memulai activity selanjutnya
                startActivity(intent);
            }
        });

        // fungsi tombol back
        tombol_back = (ImageButton) findViewById(R.id.tombol_back);
        tombol_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
