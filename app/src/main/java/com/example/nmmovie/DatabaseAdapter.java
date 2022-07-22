package com.example.nmmovie;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {

    DatabaseHelper helper;
    SQLiteDatabase db;
    List<Bioskop> bioskopList = new ArrayList<>();

    public DatabaseAdapter(Context context) {
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    // fungsi untuk mengambil data bioskop berdasarkan kota
    public List<Bioskop> getAllBioskopFromCity(String selection){
        String columns[] = {"id", "nama", "bioskop", "kota", "alamat", "gambar"};
        Cursor cursor = db.rawQuery("SELECT * FROM bioskop WHERE kota = ?", new String[]{selection});
        while(cursor.moveToNext()){
            int index1 = cursor.getColumnIndex("id");
            int rowid = cursor.getInt(index1);
            int index2 = cursor.getColumnIndex("nama");
            String nama = cursor.getString(index2);
            int index3 = cursor.getColumnIndex("bioskop");
            String namabioskop = cursor.getString(index3);
            int index4 = cursor.getColumnIndex("kota");
            String kota = cursor.getString(index4);
            int index5 = cursor.getColumnIndex("alamat");
            String alamat = cursor.getString(index5);
            int index6 = cursor.getColumnIndex("gambar");
            String gambar = cursor.getString(index6);
            Bioskop bioskop = new Bioskop(rowid, nama, namabioskop, kota, alamat, gambar);
            bioskopList.add(bioskop);
        }
        return bioskopList;
    }

    // fungsi untuk mengambil data bioskop
    public List<Bioskop> getAllBioskops(){
        String columns[] = {"id", "nama", "bioskop", "kota", "alamat", "gambar"};
        Cursor cursor = db.query("bioskop", columns,null, null,null, null, null, null);
        while(cursor.moveToNext()){
            int index1 = cursor.getColumnIndex("id");
            int rowid = cursor.getInt(index1);
            int index2 = cursor.getColumnIndex("nama");
            String nama = cursor.getString(index2);
            int index3 = cursor.getColumnIndex("bioskop");
            String namabioskop = cursor.getString(index3);
            int index4 = cursor.getColumnIndex("kota");
            String kota = cursor.getString(index4);
            int index5 = cursor.getColumnIndex("alamat");
            String alamat = cursor.getString(index5);
            int index6 = cursor.getColumnIndex("gambar");
            String gambar = cursor.getString(index6);
            Bioskop bioskop = new Bioskop(rowid, nama, namabioskop, kota, alamat, gambar);
            bioskopList.add(bioskop);
        }
        return bioskopList;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        // deklarasi variabel untuk nama dan versi database
        private static final String DATABASE_NAME = "nmmovie.db";
        private static final int DATABASE_VERSION = 1;
        private Context context;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
