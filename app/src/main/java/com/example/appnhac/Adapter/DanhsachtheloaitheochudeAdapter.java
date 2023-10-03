package com.example.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appnhac.Activity.DanhsachbaihatActivity;
import com.example.appnhac.Model.TheLoai;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DanhsachtheloaitheochudeAdapter extends RecyclerView.Adapter<DanhsachtheloaitheochudeAdapter.ViewHolder> {
    Context context;
    ArrayList<TheLoai> mangTheloai;

    public DanhsachtheloaitheochudeAdapter(Context context, ArrayList<TheLoai> mangTheloai) {
        this.context = context;
        this.mangTheloai = mangTheloai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  =    inflater.inflate(R.layout.dong_the_loai_theo_chu_de,parent,false);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
              TheLoai theLoai = mangTheloai.get(position);
        Picasso.with(context).load(theLoai.getHinhTheLoai()).into(holder.imghinhnen);
        holder.txtTentheloai.setText(theLoai.getTenTheLoai());

    }

    @Override
    public int getItemCount() {
        return mangTheloai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imghinhnen;
        TextView txtTentheloai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           txtTentheloai= itemView.findViewById(R.id.textviewtentheloaitheochude);
            imghinhnen= itemView.findViewById(R.id.imageviewtheloaitheochude);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("idtheloai",mangTheloai.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
