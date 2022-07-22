package com.example.nmmovie;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class BioskopAdapter extends RecyclerView.Adapter<BioskopAdapter.ViewHolder> {
    Context context;
    List<Bioskop> bioskopList;
    RecyclerView rv_bioskop;
    HashMap<String, Integer> foto;
    final View.OnClickListener onClickListener = new MyOnClickListener();

    class ViewHolder extends RecyclerView.ViewHolder {
        // deklarasi variabel untuk komponen dalam tampilan
        TextView namaTempat;
        TextView namaBioskop;
        TextView alamatBioskop;
        ImageView fotoBioskop;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaTempat = itemView.findViewById(R.id.namaTempat);
            namaBioskop = itemView.findViewById(R.id.namaBioskop);
            alamatBioskop = itemView.findViewById(R.id.alamatBioskop);
            fotoBioskop = itemView.findViewById(R.id.fotoBioskop);
        }
    }

    public BioskopAdapter(Context context, List<Bioskop> bioskopList, RecyclerView rv_bioskop, HashMap<String, Integer> foto){
        this.context = context;
        this.bioskopList = bioskopList;
        this.rv_bioskop = rv_bioskop;
        this.foto = foto;
    }

    @NonNull
    @Override
    public BioskopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.bioskop, parent, false);
        view.setOnClickListener(onClickListener);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bioskop bioskop = bioskopList.get(position);
        holder.namaTempat.setText(bioskop.getNama());
        holder.namaBioskop.setText(bioskop.getBioskop());
        holder.alamatBioskop.setText(bioskop.getAlamat());
        holder.fotoBioskop.setImageResource(foto.get(bioskop.getNama()));
    }

    @Override
    public int getItemCount() {
        return bioskopList.size();
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        // fungsi untuk berpindah halaman ke list film ketika card bioskop di tekan
        public void onClick(View v) {
            // mengambil nama dan jenis bioskop
            int itemPosition = rv_bioskop.getChildLayoutPosition(v);
            String nama = bioskopList.get(itemPosition).getNama();
            String bioskop = bioskopList.get(itemPosition).getBioskop();

            // variabel intent untuk berpindah halaman
            Intent intent = new Intent(context , FilmActivity.class);
            // konsepnya seperti session, saat berpindah halaman membawa variabel ke halaman itu untuk digunakan di halaman tersebut
            intent.putExtra("nama" , nama);
            intent.putExtra("bioskop" , bioskop);
            // memulai halaman yang di tuju
            context.startActivity(intent);
        }
    }
}
