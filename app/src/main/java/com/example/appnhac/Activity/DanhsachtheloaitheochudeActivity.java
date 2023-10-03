package com.example.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.appnhac.Adapter.DanhsachtheloaitheochudeAdapter;
import com.example.appnhac.Model.ChuDe;
import com.example.appnhac.Model.TheLoai;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.DataService;

import java.util.ArrayList;
import java.util.List;

public class DanhsachtheloaitheochudeActivity extends AppCompatActivity {
      ChuDe chuDe;
      RecyclerView recyclerViewtheloaitheochude;
      Toolbar toolbarTheloaitheochude;
      DanhsachtheloaitheochudeAdapter danhsachtheloaitheochudeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtheloaitheochude);
        GetIntent();
        addEvents();
        GetData();


    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<TheLoai>> callabck = dataService.getBaiHatTheoChude(chuDe.getIdChuDe());
        callabck.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> mangTheloai = (ArrayList<TheLoai>) response.body();
                danhsachtheloaitheochudeAdapter = new DanhsachtheloaitheochudeAdapter(DanhsachtheloaitheochudeActivity.this,mangTheloai);
                recyclerViewtheloaitheochude.setLayoutManager( new GridLayoutManager(DanhsachtheloaitheochudeActivity.this,2));
                recyclerViewtheloaitheochude.setAdapter(danhsachtheloaitheochudeAdapter);

            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void addEvents() {
        recyclerViewtheloaitheochude = findViewById(R.id.recyclerviewtheloaitheochude);
        toolbarTheloaitheochude = findViewById(R.id.toolbartheloaitheochude);
        setSupportActionBar(toolbarTheloaitheochude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        toolbarTheloaitheochude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIntent() {
        Intent intent = getIntent();
        if(intent.hasExtra("chude"))
        {
            chuDe = (ChuDe) intent.getSerializableExtra("chude");
          //  Toast.makeText(DanhsachtheloaitheochudeActivity.this,chuDe.getTenChuDe(),1).show();

        }
    }
}