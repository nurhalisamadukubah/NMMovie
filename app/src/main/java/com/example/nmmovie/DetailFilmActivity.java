package com.example.nmmovie;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class DetailFilmActivity extends AppCompatActivity {
    HashMap<String, Integer> poster;
    Intent intent;
    DBHelper dbHelper;
    String judul, rate, durasi, genre, direktur, sinopsis, jamtayang;
    // deklarasi variabel untuk komponen dalam tampilan
    ImageView poster_film;
    TextView tv_rate, tv_durasi, tv_genre, tv_direktur, tv_judul, tv_sinopsis;
    ImageButton tombol_back;
    Button tombol_beli_sekarang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);

        // membuat list poster
        poster = new HashMap<String, Integer>();
        poster.put("TOP GUN: MAVERICK", R.drawable.topgun);
        poster.put("THE DOLL 3", R.drawable.thedoll);
        poster.put("KKN DI DESA PENARI (UNCUT)", R.drawable.kkn);
        poster.put("DORAEMON THE MOVIE: NOBITA`S LITTLE STAR WARS 2021", R.drawable.doraemon);
        poster.put("DOCTOR STRANGE IN THE MULTIVERSE", R.drawable.doctor_strange);

        // deklarasi database
        dbHelper = new DBHelper(getApplicationContext());

        tombol_back = (ImageButton) findViewById(R.id.tombol_back);
        tombol_beli_sekarang = (Button) findViewById(R.id.tombol_beli_sekarang);

        tv_judul = (TextView) findViewById(R.id.tv_judul);
        tv_rate = (TextView) findViewById(R.id.tv_rate);
        tv_durasi = (TextView) findViewById(R.id.tv_durasi);
        tv_genre = (TextView) findViewById(R.id.tv_genre);
        tv_direktur = (TextView) findViewById(R.id.tv_direktur);
        tv_sinopsis = (TextView) findViewById(R.id.tv_sinopsis);
        poster_film = (ImageView) findViewById(R.id.poster_film);

        // deklarasi intent, untuk mengambil data yang dikirim dari halaman sebelumnya (konsep mirip session)
        intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String nama_bioskop = intent.getStringExtra("nama_bioskop");

        // mengambil data film berdasarkan id, id di dapat dari data yang dikirim dari halaman sebelumnya dan digunakan sebagai argumen yang akan digunakan dalam fungsi
        Cursor cursor = dbHelper.ambilDataFilmBerdasarkanId(Integer.toString(id));
        while(cursor.moveToNext()){
            // mengambil data film dan menampilkannya ke dalam komponen dalam tampilan
            judul = cursor.getString(1);
            tv_judul.setText(judul);
            rate = cursor.getString(2);
            tv_rate.setText(rate);
            durasi = cursor.getString(3);
            tv_durasi.setText(durasi);
            genre = cursor.getString(4);
            tv_genre.setText(genre);
            direktur = cursor.getString(5);
            tv_direktur.setText(direktur);
            sinopsis = cursor.getString(6);
            tv_sinopsis.setText(sinopsis);

            // poster.get(judul) itu fungsi nya untuk mengambil value dari list poster untuk mendapatkan gambar yang sesuai
            poster_film.setImageResource(poster.get(judul));
        }

        tombol_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tombol_beli_sekarang.setOnClickListener(new View.OnClickListener() {
            @Override
            // fungsi berpindah halaman ketika card film di klik
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PilihKursiActivity.class);
                // variabel yang dikirim ke halaman selanjutnya
                intent.putExtra("id", id);
                intent.putExtra("genre", genre);
                intent.putExtra("nama_bioskop", nama_bioskop);
                intent.putExtra("judul", judul);
                // memulai acitivity selanjutnnya
                startActivity(intent);
            }
        });
    }
}
