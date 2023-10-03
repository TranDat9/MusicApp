package com.example.appnhac.Adapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerPlaylistnahc extends FragmentPagerAdapter {
    public  final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    public ViewPagerPlaylistnahc(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
    public void AddFragmet(Fragment fragment){
        fragmentArrayList.add(fragment);
    }
}
