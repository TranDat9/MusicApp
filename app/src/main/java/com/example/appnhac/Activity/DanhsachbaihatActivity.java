package com.example.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appnhac.Adapter.DanhsachbaihatAdapter;
import com.example.appnhac.Model.Album;
import com.example.appnhac.Model.BaiHat;
import com.example.appnhac.Model.Playlist;
import com.example.appnhac.Model.Quangcao;
import com.example.appnhac.Model.TheLoai;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.DataService;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DanhsachbaihatActivity extends AppCompatActivity {
  Quangcao quangcao;
  CoordinatorLayout coordinatorLayout;
  CollapsingToolbarLayout collapsingToolbarLayout;
  Toolbar toolbar;
  RecyclerView recyclerViewDanhsachbaihat;
  FloatingActionButton floatingActionButtonPlay;
  ImageView imgdanhsachcakhuc;
  ArrayList<BaiHat> mangbaihat;
  DanhsachbaihatAdapter danhsachbaihatAdapter;
  Playlist playlist;
  TheLoai theLoai;
  Album album;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DataIntent();
        anhxa();
        addEvents();

        if(quangcao != null && !quangcao.getTenBaiHat().equals("")) {
            setValueInView(quangcao.getTenBaiHat(),quangcao.getHinhAnh());
            GetDataQuangcao(quangcao.getIdQuangcao());
        }
        if(playlist != null && !playlist.getTen().equals("")) {
            setValueInView(playlist.getTen(),playlist.getHinhnen());
            GetDataPlayList(playlist.getIdPlayList());
        }
        if(theLoai != null && !theLoai.getTenTheLoai().equals("")) {
            setValueInView(theLoai.getTenTheLoai(),theLoai.getHinhTheLoai());
            GetDataTheLoai(theLoai.getIdTheLoai());
        }
        if(album != null && !album.getTenAlbum().equals("")) {
            setValueInView(album.getTenAlbum(),album.getHinhAlbum());
            GetDataAlbum(album.getIdAlbum());
        }



}

    private void GetDataAlbum(String idAlbum) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callabck = dataService.getBaiHatTheoAlbum(idAlbum);
        callabck.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                recyclerViewDanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewDanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });

    }

    private void GetDataTheLoai(String idTheLoai) {
         DataService dataService = APIService.getService();
         Call<List<BaiHat>> callabck = dataService.getBaiHatTheoTheLoai(idTheLoai);
         callabck.enqueue(new Callback<List<BaiHat>>() {
             @Override
             public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                 mangbaihat = (ArrayList<BaiHat>) response.body();
                 danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                 recyclerViewDanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                 recyclerViewDanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                 eventClick();
             }

             @Override
             public void onFailure(Call<List<BaiHat>> call, Throwable t) {

             }
         });
    }

    private void GetDataPlayList( String idplaylist) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> call = dataService.getBaiHatTheoPlayList(idplaylist);
        call.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                recyclerViewDanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewDanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }


    private void setValueInView(String ten , String hinh) {
        collapsingToolbarLayout.setTitle(ten);

        try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Picasso.with(this).load(hinh).into(imgdanhsachcakhuc);
    }


    private void GetDataQuangcao(String idquangcao) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback  = dataService.getBaiHat(idquangcao);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                recyclerViewDanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewDanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventClick();

            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });

    }

    private void addEvents() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButtonPlay.setEnabled(false);

    }

    private void anhxa() {
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout =findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbardanhsach);
        recyclerViewDanhsachbaihat = findViewById(R.id.recycleviewdanhsachbaihat);
        floatingActionButtonPlay = findViewById(R.id.floatingactionButton);
        imgdanhsachcakhuc = findViewById(R.id.imageviewdanhsachcakhuc);

    }

    private void DataIntent() {
        Intent intent = getIntent();
        quangcao = (Quangcao) intent.getSerializableExtra("banner");

        if(intent.hasExtra("itemplaylist"))
        {
            playlist = (Playlist) intent.getSerializableExtra("itemplaylist");
        }

        if(intent.hasExtra("idtheloai"))
        {
            theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");
        }
        if(intent.hasExtra("album"))
        {
            album = (Album) intent.getSerializableExtra("album");
        }



    }
    private void eventClick()
    {
        floatingActionButtonPlay.setEnabled(true);
        floatingActionButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhsachbaihatActivity.this,PlayMusicActivity.class);
                intent.putExtra("cacbaihat",mangbaihat);
                startActivity(intent);
            }
        });

    }
}