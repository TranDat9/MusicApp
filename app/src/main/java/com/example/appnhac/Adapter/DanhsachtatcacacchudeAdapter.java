package com.example.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.appnhac.Activity.DanhsachtheloaitheochudeActivity;
import com.example.appnhac.Model.ChuDe;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DanhsachtatcacacchudeAdapter extends RecyclerView.Adapter<DanhsachtatcacacchudeAdapter.ViewHolder> {
    public DanhsachtatcacacchudeAdapter(Context context, ArrayList<ChuDe> mangchude) {
        this.context = context;
        this.mangchude = mangchude;
    }

    Context context;
    ArrayList<ChuDe> mangchude;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context) ;
        View view = inflater.inflate(R.layout.dong_cac_chu_de,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          ChuDe chuDe = mangchude.get(position);
        Picasso.with(context).load(chuDe.getHinhChuDe()).into(holder.imgChude);
    }

    @Override
    public int getItemCount() {
        return mangchude.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgChude;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgChude = itemView.findViewById(R.id.imageViewdongcacchude);
            imgChude.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachtheloaitheochudeActivity.class);
                    intent.putExtra("chude",mangchude.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
