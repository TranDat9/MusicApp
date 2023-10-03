package com.example.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appnhac.Activity.DanhsachbaihatActivity;
import com.example.appnhac.Model.Quangcao;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<Quangcao> arrayListQuangcao;

    public BannerAdapter(Context context, ArrayList<Quangcao> arrayListQuangcao) {
        this.context = context;
        this.arrayListQuangcao = arrayListQuangcao;
    }

    @Override
    public int getCount() {
        return arrayListQuangcao.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.dong_banner,null);

        ImageView imgbackgroundbanner = view.findViewById(R.id.imageviewbackgroundbanner);
        ImageView imgsongbanner = view.findViewById(R.id.imageviewbanner);
        TextView txtTitleSongBanner = view.findViewById(R.id.titlebannerbaihat);
        TextView txtNoiDung = view.findViewById(R.id.noidung);

        Picasso.with(context).load(arrayListQuangcao.get(position).getHinhAnh()).into(imgbackgroundbanner);
        Picasso.with(context).load(arrayListQuangcao.get(position).getHinhBaiHat()).into(imgsongbanner);
        txtTitleSongBanner.setText(arrayListQuangcao.get(position).getTenBaiHat());
        txtNoiDung.setText(arrayListQuangcao.get(position).getNoiDung());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                Quangcao quangcao = arrayListQuangcao.get(position);
                intent.putExtra("banner",quangcao);
                context.startActivity(intent);
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object) ;
    }
}
