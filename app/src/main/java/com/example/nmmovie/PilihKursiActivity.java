package com.example.nmmovie;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class PilihKursiActivity extends AppCompatActivity {
    Intent intent;
    DBHelper dbHelper;
    String str_jamtayang;
    ImageButton tombol_back;
    Button tombol_lanjutkan, tombol_a1, tombol_a2, tombol_a3, tombol_a4, tombol_a5, tombol_b1, tombol_b2, tombol_b3, tombol_b4, tombol_b5, tombol_c1, tombol_c2, tombol_c3, tombol_c4, tombol_c5, tombol_d1, tombol_d2, tombol_d3, tombol_d4, tombol_d5, tombol_e1, tombol_e2, tombol_e3, tombol_e4, tombol_e5;
    ArrayList<String> daftar_kursi;
    String jam_tayang;
    TextView tv_daftar_kursi, tv_total_harga, tv_jam;
    ArrayList<Jam> jamArrayList;
    JamListAdapter jamListAdapter;
    GridView gv_jamtayang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_kursi);
        intent = getIntent();
        dbHelper = new DBHelper(this);
        gv_jamtayang = (GridView) findViewById(R.id.gv_jamtayang);
        jamArrayList = new ArrayList<>();
        // this mengarah ke class, R.layout.jam itu xml tampilan yang akan digunakan untuk list item grid view jam di tampilan R.layout.activity_pilih)kursi, jamArrayList itu array dari jam2 tayang film
        jamListAdapter = new JamListAdapter(this, R.layout.jam, jamArrayList);
        gv_jamtayang.setAdapter(jamListAdapter);

        // mengambil data yang dikirim dari halaman sebelumnya
        int id = intent.getIntExtra("id", 0);
        String genre = intent.getStringExtra("genre");
        String nama_bioskop = intent.getStringExtra("nama_bioskop");
        String judul = intent.getStringExtra("judul");

        daftar_kursi = new ArrayList<String>();

        // deklarasi variabel komponen
        tv_jam = (TextView) findViewById(R.id.tv_jam);
        tv_total_harga = (TextView) findViewById(R.id.tv_total_harga);
        tv_daftar_kursi = (TextView) findViewById(R.id.tv_daftar_kursi);

        // fungsi untuk berpindah halaman
        tombol_lanjutkan = (Button) findViewById(R.id.tombol_lanjutkan);
        tombol_lanjutkan.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PembayaranActivity.class);
                intent.putExtra("judul", judul);
                intent.putExtra("nama_bioskop", nama_bioskop);
                intent.putExtra("genre", genre);
                intent.putExtra("jam_tayang", jam_tayang);
                intent.putExtra("daftar_kursi", String.join(", ", daftar_kursi));
                intent.putExtra("tiket", Integer.toString(daftar_kursi.size()));
                startActivity(intent);
            }
        });

        tombol_back = (ImageButton) findViewById(R.id.tombol_back);
        tombol_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // kode menampilkan jam tayang
        Cursor cursor = dbHelper.ambilDataFilmBerdasarkanId(Integer.toString(id));
        while(cursor.moveToNext()){
            str_jamtayang = cursor.getString(8);
        }
        if (!str_jamtayang.equals("")){
            String[] arr_jamtayang = str_jamtayang.split(";");
            for (int i = 0; i < arr_jamtayang.length; i++) {
                jamArrayList.add(new Jam(arr_jamtayang[i]));
            }
            jamListAdapter.notifyDataSetChanged();
        }
        gv_jamtayang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                jam_tayang = jamArrayList.get(position).getJam();
                tv_jam.setText(jam_tayang);
            }
        });
        // end kode menampilkan jam tayang

        //  deklarasi tombol kursi
        tombol_a1 = (Button) findViewById(R.id.tombol_a1);
        tombol_a2 = (Button) findViewById(R.id.tombol_a2);
        tombol_a3 = (Button) findViewById(R.id.tombol_a3);
        tombol_a4 = (Button) findViewById(R.id.tombol_a4);
        tombol_a5 = (Button) findViewById(R.id.tombol_a5);
        tombol_b1 = (Button) findViewById(R.id.tombol_b1);
        tombol_b2 = (Button) findViewById(R.id.tombol_b2);
        tombol_b3 = (Button) findViewById(R.id.tombol_b3);
        tombol_b4 = (Button) findViewById(R.id.tombol_b4);
        tombol_b5 = (Button) findViewById(R.id.tombol_b5);
        tombol_c1 = (Button) findViewById(R.id.tombol_c1);
        tombol_c2 = (Button) findViewById(R.id.tombol_c2);
        tombol_c3 = (Button) findViewById(R.id.tombol_c3);
        tombol_c4 = (Button) findViewById(R.id.tombol_c4);
        tombol_c5 = (Button) findViewById(R.id.tombol_c5);
        tombol_d1 = (Button) findViewById(R.id.tombol_d1);
        tombol_d2 = (Button) findViewById(R.id.tombol_d2);
        tombol_d3 = (Button) findViewById(R.id.tombol_d3);
        tombol_d4 = (Button) findViewById(R.id.tombol_d4);
        tombol_d5 = (Button) findViewById(R.id.tombol_d5);
        tombol_e1 = (Button) findViewById(R.id.tombol_e1);
        tombol_e2 = (Button) findViewById(R.id.tombol_e2);
        tombol_e3 = (Button) findViewById(R.id.tombol_e3);
        tombol_e4 = (Button) findViewById(R.id.tombol_e4);
        tombol_e5 = (Button) findViewById(R.id.tombol_e5);

        // fungsi ketika tombol kursi ditekan
        tombol_a1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                clickButton(tombol_a1, "A1", tv_daftar_kursi, tv_total_harga);
            }
        });
        tombol_a2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                clickButton(tombol_a2, "A2", tv_daftar_kursi, tv_total_harga);
            }
        });
        tombol_a3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                clickButton(tombol_a3, "A3", tv_daftar_kursi, tv_total_harga);
            }
        });
        tombol_a4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                clickButton(tombol_a4, "A4", tv_daftar_kursi, tv_total_harga);
            }
        });
        tombol_a5.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                clickButton(tombol_a5, "A5", tv_daftar_kursi, tv_total_harga);
            }
        });
        tombol_b1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                clickButton(tombol_b1, "B1", tv_daftar_kursi, tv_total_harga);
            }
        });
        tombol_b2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                clickButton(tombol_b2, "B2", tv_daftar_kursi, tv_total_harga);
            }
        });
        tombol_b3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                clickButton(tombol_b3, "B3", tv_daftar_kursi, tv_total_harga);
            }
        });
        tombol_b4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                clickButton(tombol_b4, "B4", tv_daftar_kursi, tv_total_harga);
            }
        });
        tombol_b5.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                clickButton(tombol_b5, "B5", tv_daftar_kursi, tv_total_harga);
            }
        });
        tombol_c1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                clickButton(tombol_c1, "C1", tv_daftar_kursi, tv_total_harga);
            }
        });
        tombol_c2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                clickButton(tombol_c2, "C2", tv_daftar_kursi, tv_total_harga);
            }
        });
        tombol_c3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                clickButton(tombol_c3, "C3", tv_daftar_kursi, tv_total_harga);
            }
        });
        tombol_c4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                clickButton(tombol_c4, "C4", tv_daftar_kursi, tv_total_harga);
            }
        });
        tombol_c5.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                clickButton(tombol_c5, "C5", tv_daftar_kursi, tv_total_harga);
            }
        });
        tombol_d1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                clickButton(tombol_d1, "D1", tv_daftar_kursi, tv_total_harga);
            }
        });
        tombol_d2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                clickButton(tombol_d2, "D2", tv_daftar_kursi, tv_total_harga);
            }
        });
        tombol_d3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                clickButton(tombol_d3, "D3", tv_daftar_kursi, tv_total_harga);
            }
        });
        tombol_d4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                clickButton(tombol_d4, "D4", tv_daftar_kursi, tv_total_harga);
            }
        });
        tombol_d5.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                clickButton(tombol_d5, "D5", tv_daftar_kursi, tv_total_harga);
            }
        });
        tombol_e1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                clickButton(tombol_e1, "E1", tv_daftar_kursi, tv_total_harga);
            }
        });
        tombol_e2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                clickButton(tombol_e2, "E2", tv_daftar_kursi, tv_total_harga);
            }
        });
        tombol_e3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                clickButton(tombol_e3, "E3", tv_daftar_kursi, tv_total_harga);
            }
        });
        tombol_e4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                clickButton(tombol_e4, "E4", tv_daftar_kursi, tv_total_harga);
            }
        });
        tombol_e5.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                clickButton(tombol_e5, "E5", tv_daftar_kursi, tv_total_harga);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void clickButton(Button button, String kursi, TextView tv_daftar_kursi, TextView tv_total_harga) {
        // fungsi untuk mengubah string ke format Rp.
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        // daftar_kursi.contains(kursi) mengecek apakah kursi yang ditekan sudah dipilih atau belum
        // mengecek data kursi apakah ada di dalam array daftar kursi atau tidak
        if(daftar_kursi.contains(kursi)){
            // kalau ada berarti data tersebut dihapus
            button.setBackgroundColor(getResources().getColor(R.color.btntersedia));
            daftar_kursi.remove(kursi);
            tv_daftar_kursi.setText(String.join(", ", daftar_kursi));

            int total_harga = daftar_kursi.size() * 35000;

            String str_total_harga = kursIndonesia.format(total_harga);
            tv_total_harga.setText(str_total_harga);
        } else {
            // kalau tidak ada berarti data ditambahkan ke array daftar kursi
            button.setBackgroundColor(getResources().getColor(R.color.btnterpilih));
            daftar_kursi.add(kursi);
            tv_daftar_kursi.setText(String.join(", ", daftar_kursi));

            int total_harga = daftar_kursi.size() * 35000;

            String str_total_harga = kursIndonesia.format(total_harga);
            tv_total_harga.setText(str_total_harga);
        }
    }
}
