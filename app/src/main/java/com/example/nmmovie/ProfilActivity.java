package com.example.nmmovie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.nmmovie.Session.SessionManager;
import java.util.HashMap;

public class ProfilActivity extends AppCompatActivity {
    SessionManager sessionManager;
    EditText et_nama_lengkap, et_nomor_telepon;
    Button tombol_beranda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        // deklarasi variabel untuk komponen di halaman profil
        et_nama_lengkap = (EditText) findViewById(R.id.et_nama_lengkap);
        et_nomor_telepon = (EditText) findViewById(R.id.et_nomor_telepon);
        tombol_beranda = (Button) findViewById(R.id.tombol_beranda);

        // ambil data user dari session
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> userDetail = sessionManager.getUserDetails();
        String user_namalengkap = userDetail.get("namalengkap");
        String user_nomortelepon = userDetail.get("nomortelepon");

        // menampilkan data user
        et_nama_lengkap.setText(user_namalengkap);
        et_nomor_telepon.setText(user_nomortelepon);

        // fungsi kembali ke halaman beranda
        tombol_beranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
