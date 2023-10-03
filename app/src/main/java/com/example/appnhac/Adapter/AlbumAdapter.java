package com.example.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appnhac.Activity.DanhsachbaihatActivity;
import com.example.appnhac.Model.Album;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
     Context context;
     ArrayList<Album> mangAlbum;

    public AlbumAdapter(Context context, ArrayList<Album> mangAlbum) {
        this.context = context;
        this.mangAlbum = mangAlbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album,parent,false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          Album album = mangAlbum.get(position);
          holder.txtTencasialbum.setText(album.getTenAlbum());
          holder.txtalbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imgHinhalbum);
    }

    @Override
    public int getItemCount() {
        return mangAlbum.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
          ImageView imgHinhalbum;
          TextView txtTencasialbum, txtalbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhalbum = itemView.findViewById(R.id.imageViewalbum);
            txtalbum = itemView.findViewById(R.id.textviewalbum);
            txtTencasialbum = itemView.findViewById(R.id.textviewtencasialbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("album",mangAlbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
