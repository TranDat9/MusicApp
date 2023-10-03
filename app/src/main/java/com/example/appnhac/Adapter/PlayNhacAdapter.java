package com.example.appnhac.Adapter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appnhac.Model.BaiHat;
import com.example.appnhac.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlayNhacAdapter extends RecyclerView.Adapter<PlayNhacAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> mangbaihat;

    public PlayNhacAdapter(Context context, ArrayList<BaiHat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_play_bai_hat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
             BaiHat baiHat = mangbaihat.get(position);
             holder.txtindex.setText(position+1 +" ");
             holder.txtcasi.setText(baiHat.getCaSi());
             holder.txttenbaihat.setText(baiHat.getTenBaiHat());

    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtindex , txttenbaihat , txtcasi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtindex = itemView.findViewById(R.id.textviewplaynahcindex);
            txtcasi = itemView.findViewById(R.id.textviewplaynhaccasibaihat);
            txttenbaihat = itemView.findViewById(R.id.textviewplaynhactenbaihat);
        }
    }
}
