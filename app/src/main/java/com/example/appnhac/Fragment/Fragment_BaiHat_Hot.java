package com.example.appnhac.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appnhac.Adapter.BaihathotAdapter;
import com.example.appnhac.Model.BaiHat;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_BaiHat_Hot extends Fragment {

    View view;
    RecyclerView recyclerViewBatHatHot;
    BaihathotAdapter baihathotAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bai_hat_thich_nhat,container,false);
        recyclerViewBatHatHot = view.findViewById(R.id.recycleviewbaihathot);


        GetData();

        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> call = dataService.GetBaiHatHot();
        call.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> baiHatArr = (ArrayList<BaiHat>) response.body();
                baihathotAdapter = new BaihathotAdapter(getActivity(),baiHatArr);
                LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                recyclerViewBatHatHot.setLayoutManager(linearLayoutManager);
                recyclerViewBatHatHot.setAdapter(baihathotAdapter);



            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });

    }
}
