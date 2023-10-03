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

import com.example.appnhac.Adapter.DanhSachPlayListAdapter;
import com.example.appnhac.Model.Playlist;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.DataService;

import java.util.ArrayList;
import java.util.List;

public class DanhsachcacplaylistActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerViewDanhsachcacplaylist;
    DanhSachPlayListAdapter danhSachPlayListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachcacplaylist);
        addControls();
        addEvents();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Playlist>> callback = dataService.GetDanhSachCacPlayList();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> mangplaylist = (ArrayList<Playlist>) response.body();
                 danhSachPlayListAdapter = new DanhSachPlayListAdapter(DanhsachcacplaylistActivity.this,mangplaylist);
                 recyclerViewDanhsachcacplaylist.setLayoutManager(new GridLayoutManager(DanhsachcacplaylistActivity.this,2));
                 recyclerViewDanhsachcacplaylist.setAdapter(danhSachPlayListAdapter);

            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void addEvents() {
          setSupportActionBar(toolbar);
          getSupportActionBar().setDisplayHomeAsUpEnabled(true);
          getSupportActionBar().setTitle("Play Lists");
          toolbar.setTitleTextColor(getResources().getColor(R.color.mautim));
          toolbar.setNavigationOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  finish();
              }
          });
    }

    private void addControls() {
        toolbar = findViewById(R.id.toolbardanhsachcacplaylist);
        recyclerViewDanhsachcacplaylist= findViewById(R.id.recycleviewdanhsachcacplaylist);

    }
}