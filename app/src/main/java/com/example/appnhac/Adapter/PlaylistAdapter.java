package com.example.appnhac.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appnhac.Model.Playlist;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }
    class ViewHolder
    {
        TextView txtTenPlaylist;
        ImageView imgBackgroundPlaylist, imgPlaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.dong_playlist,null);
            viewHolder = new ViewHolder();
            viewHolder.txtTenPlaylist = convertView.findViewById(R.id.txtTenplaylist);
            viewHolder.imgBackgroundPlaylist= convertView.findViewById(R.id.imageviewbackgroundplaylis);
            viewHolder.imgPlaylist = convertView.findViewById(R.id.imgPlaylist);
            convertView.setTag(viewHolder);


        }
        else {
            viewHolder =(ViewHolder) convertView.getTag();
        }
        Playlist playlist = getItem(position);
        Picasso.with(getContext()).load(playlist.getHinhnen()).into(viewHolder.imgBackgroundPlaylist);
        Picasso.with(getContext()).load(playlist.getHinhicon()).into(viewHolder.imgPlaylist);
        viewHolder.txtTenPlaylist.setText(playlist.getTen());
        return convertView;
    }
}
