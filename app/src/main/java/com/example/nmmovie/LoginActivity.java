package com.example.nmmovie;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nmmovie.Session.SessionManager;

public class LoginActivity extends AppCompatActivity {

    TextView link_daftar;
    Button tombol_masuk;
    EditText nomor_telepon, kata_sandi;
    DBHelper DB;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(getApplicationContext()); // deklarasi session
        // deklarasi tombol
        link_daftar = (TextView) findViewById(R.id.link_daftar);
        tombol_masuk = (Button) findViewById(R.id.tombol_masuk);
        DB = new DBHelper(this); // deklarasi db

        // fungsi untuk pindah ke halaman register ketika tombol daftar ditekan
        link_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        // fungsi ketika tombol masuk ditekan
        tombol_masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // mengambil nilai dari inputan nomor telepin dan kata sani
                nomor_telepon = (EditText) findViewById(R.id.et_nomor_telepon);
                kata_sandi = (EditText) findViewById(R.id.et_kata_sandi);

                String str_nomor_telepon = nomor_telepon.getText().toString();
                String str_kata_sandi = kata_sandi.getText().toString();

                // cek jika nomor telepon atau kata sandi kosong
                if( str_nomor_telepon.equals("") || str_kata_sandi.equals("") ){
                    Toast.makeText(LoginActivity.this, "Silahkan lengkap data yang diperlukan", Toast.LENGTH_SHORT).show();
                } else {
                    // cek login
                    Boolean cekNomorTeleponKataSandiDiTableUsers = DB.cekNomorTeleponKataSandiDiTableUsers(str_nomor_telepon, str_kata_sandi);
                    if( cekNomorTeleponKataSandiDiTableUsers ){
//                        code session
                        Cursor detailUser = DB.ambilDetailUser(str_nomor_telepon);
                        String user_nomor_telepon = "";
                        String user_namalengkap = "";
                        while (detailUser.moveToNext()){
                            int index_nomortelepon = detailUser.getColumnIndex("nomortelepon");
                            user_nomor_telepon = detailUser.getString(index_nomortelepon);
                            int index_namalengkap = detailUser.getColumnIndex("namalengkap");
                            user_namalengkap = detailUser.getString(index_namalengkap);
                        }
                        sessionManager.createLoginSession(user_nomor_telepon, user_namalengkap);

                        // pindah kelaman home ketika berhasil login
                        Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Gagal. Silahkan coba lagi", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
