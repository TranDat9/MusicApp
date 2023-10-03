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

import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllAlbumAdapter extends RecyclerView.Adapter<AllAlbumAdapter.Viewholder> {
    Context context;

    public AllAlbumAdapter(Context context, ArrayList<Album> mangAlbum) {
        this.context = context;
        this.mangAlbum = mangAlbum;
    }

    ArrayList<Album> mangAlbum;
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_all_album,parent,false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
                Album album = mangAlbum.get(position);
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imgAllalbum);
        holder.txtTenAlbum.setText(album.getTenAlbum());

    }

    @Override
    public int getItemCount() {
        return mangAlbum.size();
    }

    public class  Viewholder extends RecyclerView.ViewHolder{
         ImageView imgAllalbum;
         TextView txtTenAlbum;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imgAllalbum = itemView.findViewById(R.id.imageviewdanhsachalbum);
            txtTenAlbum = itemView.findViewById(R.id.textviewdanhsachcacalbum);
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
