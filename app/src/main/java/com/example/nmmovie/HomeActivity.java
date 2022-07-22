package com.example.nmmovie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nmmovie.Session.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    SessionManager sessionManager; // session manager
    DatabaseAdapter databaseAdapter;
    String select_kota;
    Spinner spinner_kota;
    String[] items={"Semua", "Makassar", "Kendari", "Manado", "Kediri"}; // daftar kota
    RecyclerView rv_bioskop;
    BioskopAdapter bioskopAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<Bioskop> bioskopList = new ArrayList<>();
    ImageView tombol_profil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // deklarasi session menager
        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin(); // fungsi mengecek status login user

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // membuat spinner kota
        spinner_kota = (Spinner) findViewById(R.id.spinner_kota);
        spinner_kota.setOnItemSelectedListener(this);
        ArrayAdapter<String> aa=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,items);
        aa.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinner_kota.setAdapter(aa);

        // fungsi untuk berpindah ke halaman profil ketika tombol profil ditekan
        tombol_profil = (ImageView) findViewById(R.id.tombol_profil);
        tombol_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfilActivity.class);
                startActivity(intent);
            }
        });
    }

    // fungsi ketika kota dipilih
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        select_kota = items[position];

        databaseAdapter = new DatabaseAdapter(this);
        if(select_kota == "Semua"){
            bioskopList = databaseAdapter.getAllBioskops();
        } else {
            bioskopList = databaseAdapter.getAllBioskopFromCity(select_kota);
        }

        layoutManager = new LinearLayoutManager(this);

        rv_bioskop = findViewById(R.id.rv_bioskop);
        rv_bioskop.setHasFixedSize(true);
        rv_bioskop.setLayoutManager(layoutManager);

        HashMap<String, Integer> foto = new HashMap<String, Integer>();
        foto.put("Grand Kawanua City", R.drawable.grand_kawanua_city);
        foto.put("Lippo Plaza Manado", R.drawable.lippo_plaza_manado);
        foto.put("Mall Phinisi Point", R.drawable.mall_phinisi_point);
        foto.put("Panakkukang Square", R.drawable.panakkukang_square);
        foto.put("Lippo Plaza Kendari", R.drawable.lippo_plaza_kendari);
        foto.put("Kediri Mall", R.drawable.kediri_mall);

        // this mengarah ke class sendiri, bioskopList itu isinya list dari bioskop, yang asalnya dari line 70 atau 72, rv_bioskop merujuk ke komponen recyclerview di R.layout.activtiy_home, foto itu list dari foto bioskop
        bioskopAdapter = new BioskopAdapter(this, bioskopList, rv_bioskop, foto);
        rv_bioskop.setAdapter(bioskopAdapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { select_kota = ""; }
}
