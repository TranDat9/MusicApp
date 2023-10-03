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

import com.example.appnhac.Adapter.DanhsachtatcacacchudeAdapter;
import com.example.appnhac.Model.ChuDe;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.DataService;

import java.util.ArrayList;
import java.util.List;

public class DanhsachtatcachudeActivity extends AppCompatActivity {

    RecyclerView recyclerViewAllchude;
    Toolbar toolbarChude;
   DanhsachtatcacacchudeAdapter danhsachtatcacacchudeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatcachude);
        addEvents();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<ChuDe>> callback = dataService.GetDanhSachCacChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> mangchude = (ArrayList<ChuDe>) response.body();
                danhsachtatcacacchudeAdapter = new DanhsachtatcacacchudeAdapter(DanhsachtatcachudeActivity.this,mangchude);
                recyclerViewAllchude.setLayoutManager(new GridLayoutManager(DanhsachtatcachudeActivity.this,1));
                recyclerViewAllchude.setAdapter(danhsachtatcacacchudeAdapter);

            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });


    }

    private void addEvents() {
        recyclerViewAllchude = findViewById(R.id.recyclerviewAllchude);
        toolbarChude = findViewById(R.id.toolbarAllchude);
        setSupportActionBar(toolbarChude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chu De");
        toolbarChude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}