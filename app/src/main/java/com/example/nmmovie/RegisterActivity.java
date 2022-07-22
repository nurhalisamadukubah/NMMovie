package com.example.nmmovie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {

    TextView link_masuk;
    Button tombol_daftar;
    EditText nama_lengkap, nomor_handphone, kata_sandi;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ini code untuk halaman apa yg ditampilkan kalau activity ini dijalankan
        setContentView(R.layout.activity_register);

        // deklarasi tombol link menuju ke halaman login dan tombol daftar
        link_masuk = (TextView) findViewById(R.id.link_masuk);
        tombol_daftar = (Button) findViewById(R.id.tombol_daftar);
        DB = new DBHelper(this);

        // source code untuk aksi link masuk di tekan
        link_masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ini code untuk tampilkan halaman, tergantung dari parameter kedua, yg dibawah ini loginactivity berarti login yang mau di tampilkan
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        tombol_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // mengambil dari dari form inputan register
                nama_lengkap = (EditText) findViewById(R.id.et_nama_lengkap);
                nomor_handphone = (EditText) findViewById(R.id.et_nomor_handphone);
                kata_sandi = (EditText) findViewById(R.id.et_kata_sandi);
                String str_nama_lengkap = nama_lengkap.getText().toString();
                String str_nomor_handphone = nomor_handphone.getText().toString();
                String str_kata_sandi = kata_sandi.getText().toString();

                // mengecek apakah nama lengkap atau nomor telepon atau kata sandi kosong
                if( str_nama_lengkap.equals("") || str_nomor_handphone.equals("") || str_kata_sandi.equals("") ) {
                    Toast.makeText(RegisterActivity.this, "Silahkan lengkap data yang diperlukan", Toast.LENGTH_SHORT).show();
                } else {
                    // source code insert ke database
                    Boolean checkIfPhoneNumberIsExists = DB.cekNomorTeleponDiTableUsers(str_nomor_handphone);
                    if( checkIfPhoneNumberIsExists ) {
                        Toast.makeText(RegisterActivity.this, "Nomor Telepon sudah terdaftar dalam sistem. Silahkan gunakan nomor telepon lain", Toast.LENGTH_SHORT).show();
                    } else {
                        Boolean insertDataToTableUsers = DB.tambahDataKeTabelUsers(str_nama_lengkap, str_nomor_handphone, str_kata_sandi);
                        if (insertDataToTableUsers) {
                            Toast.makeText(RegisterActivity.this, "Berhasil mendaftarkan Akun. Silahkan Login", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Gagal mendaftarkan Akun. Silahkan coba lagi", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}
