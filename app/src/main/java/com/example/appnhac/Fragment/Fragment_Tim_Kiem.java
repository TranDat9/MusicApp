package com.example.appnhac.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.appnhac.Adapter.SearchBaiHatAdapter;
import com.example.appnhac.Model.BaiHat;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Tim_Kiem extends Fragment {
    View view;
    Toolbar toolbarsearch;
    RecyclerView recyclerViewsearch;
    TextView txtNoData;
    SearchBaiHatAdapter searchBaiHatAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view =inflater.inflate(R.layout.fragment_tim_kiem,container,false);
        toolbarsearch= view.findViewById(R.id.toolbarsearchbaihat);
        recyclerViewsearch= view.findViewById(R.id.recyclerviewserch);
        txtNoData = view.findViewById(R.id.textviewkhongcosdulieu);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarsearch);
        toolbarsearch.setTitle("");
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_view,menu);
        MenuItem menuItem = menu.findItem(R.id.menusearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchTuKhoaBh(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
    private void SearchTuKhoaBh(String tukhoa)
    {
        DataService dataService = APIService.getService();

        Call<List<BaiHat>> call = dataService.GetSearchBH(tukhoa);
        call.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> mangbaihat = (ArrayList<BaiHat>) response.body();
                if(mangbaihat.size()>0)
                {
                    searchBaiHatAdapter = new SearchBaiHatAdapter(getActivity(),mangbaihat);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerViewsearch.setLayoutManager(linearLayoutManager);
                    recyclerViewsearch.setAdapter(searchBaiHatAdapter);
                    txtNoData.setVisibility(View.GONE);
                    recyclerViewsearch.setVisibility(View.VISIBLE);
                } else {
                    recyclerViewsearch.setVisibility(View.GONE);
                    txtNoData.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

}
