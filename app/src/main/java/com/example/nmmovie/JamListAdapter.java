package com.example.nmmovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class JamListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Jam> jamArrayList;

    public JamListAdapter(Context context, int layout, ArrayList<Jam> jamArrayList) {
        this.context = context;
        this.layout = layout;
        this.jamArrayList = jamArrayList;
    }

    @Override
    public int getCount() { return jamArrayList.size(); }

    @Override
    public Object getItem(int position) {
        return jamArrayList.get(position);
    }

    private class ViewHolder{ TextView tv_jam; }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        JamListAdapter.ViewHolder holder = new JamListAdapter.ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            // membuat item tombol jam tayang
            holder.tv_jam = (TextView) row.findViewById(R.id.tv_jam);
            row.setTag(holder);
        }
        else {
            holder = (JamListAdapter.ViewHolder) row.getTag();
        }
        Jam jam = jamArrayList.get(position);
        holder.tv_jam.setText(jam.getJam());

        return row;
    }
}
