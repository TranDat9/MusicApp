package com.example.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appnhac.Activity.DanhsachbaihatActivity;
import com.example.appnhac.Model.Playlist;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DanhSachPlayListAdapter extends RecyclerView.Adapter<DanhSachPlayListAdapter.ViewHolder> {
    Context context;

    public DanhSachPlayListAdapter(Context context, ArrayList<Playlist> mangplaylist) {
        this.context = context;
        this.mangplaylist = mangplaylist;
    }

    ArrayList<Playlist> mangplaylist;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_playlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                  Playlist playlist = mangplaylist.get(position);
        Picasso.with(context).load(playlist.getHinhnen()).into(holder.imghinhnen);
        holder.txtTenPlayList.setText(playlist.getTen()) ;
    }

    @Override
    public int getItemCount() {
        return mangplaylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imghinhnen;
        TextView txtTenPlayList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhnen = itemView.findViewById(R.id.imageviewdanhsachplaylist);
            txtTenPlayList = itemView.findViewById(R.id.textviewdanhsachcacplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                 intent.putExtra("itemplaylist",mangplaylist.get(getPosition()));
                 context.startActivity(intent);
                }
            });

        }
    }

}
