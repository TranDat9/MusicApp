package com.example.appnhac.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appnhac.Activity.PlayMusicActivity;
import com.example.appnhac.Adapter.PlayNhacAdapter;
import com.example.appnhac.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Frragment_Play_Cac_Bai_Hat extends Fragment {
    View view;
    RecyclerView recyclerViewPlayNhac;
    PlayNhacAdapter playNhacAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_cac_bai_hat,container,false);
        recyclerViewPlayNhac= view.findViewById(R.id.recyclerviewplaynhac);
        if(PlayMusicActivity.mangbaihat.size()>0)
        {
            playNhacAdapter = new PlayNhacAdapter(getActivity(), PlayMusicActivity.mangbaihat);
            recyclerViewPlayNhac.setLayoutManager( new LinearLayoutManager(getActivity()));
            recyclerViewPlayNhac.setAdapter(playNhacAdapter);
        }


        return view;
    }
}
