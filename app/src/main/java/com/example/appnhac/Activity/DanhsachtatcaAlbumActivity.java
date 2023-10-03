package com.example.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.appnhac.Adapter.AllAlbumAdapter;
import com.example.appnhac.Model.Album;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.DataService;

import java.util.ArrayList;
import java.util.List;

public class DanhsachtatcaAlbumActivity extends AppCompatActivity {
    Toolbar toolbarallalbum;
    RecyclerView recyclerViewAllAlbum;
    AllAlbumAdapter albumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatca_album);
        addEvents();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Album>> callback = dataService.GetDanhSachCacAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> mangalbum= (ArrayList<Album>) response.body();
               albumAdapter = new AllAlbumAdapter(DanhsachtatcaAlbumActivity.this,mangalbum);
               recyclerViewAllAlbum.setLayoutManager(new GridLayoutManager(DanhsachtatcaAlbumActivity.this,2));
               recyclerViewAllAlbum.setAdapter(albumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void addEvents() {
        recyclerViewAllAlbum = findViewById(R.id.recyclerviewAllalbum);
        toolbarallalbum = findViewById(R.id.toolbaralbum);
        setSupportActionBar(toolbarallalbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tat ca cac album");
        toolbarallalbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}