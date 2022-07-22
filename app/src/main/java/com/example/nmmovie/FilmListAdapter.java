package com.example.nmmovie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class FilmListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Film> filmArrayList;
    HashMap<String, Integer> poster;

    public FilmListAdapter(Context context, int layout, ArrayList<Film> filmArrayList, HashMap<String, Integer> poster) {
        this.context = context;
        this.layout = layout;
        this.filmArrayList = filmArrayList;
        this.poster = poster;
    }

    @Override
    public int getCount() {
        return filmArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return filmArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        // deklarasi komponen yang ada dalam ui R.layout.film
        ImageView poster_film;
        TextView tv_judul;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            // menampilkan data film ke ui R.layout.film
            holder.tv_judul = (TextView) row.findViewById(R.id.tv_judul);
            holder.poster_film = (ImageView) row.findViewById(R.id.poster_film);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Film film = filmArrayList.get(position);
        holder.tv_judul.setText(film.getJudul());
        holder.poster_film.setImageResource(poster.get(film.getJudul()));

        return row;
    }
}
