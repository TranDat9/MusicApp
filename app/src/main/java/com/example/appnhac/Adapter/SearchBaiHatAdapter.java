package com.example.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appnhac.Activity.PlayMusicActivity;
import com.example.appnhac.Model.BaiHat;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchBaiHatAdapter extends RecyclerView.Adapter<SearchBaiHatAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> mangbaihat;

    public SearchBaiHatAdapter(Context context, ArrayList<BaiHat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dong_search_bai_hat,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = mangbaihat.get(position);
        holder.txtTenbaihat.setText(baiHat.getTenBaiHat());
        holder.txtTencasi.setText(baiHat.getCaSi());
        Picasso.with(context).load(baiHat.getHinhbaiHat()).into(holder.imgbaihat);


    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTencasi , txtTenbaihat;
        ImageView imgbaihat ,imgluotthich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTencasi = itemView.findViewById(R.id.textviewsearchcasibaihat);
            txtTenbaihat = itemView.findViewById(R.id.textviewsearchtenbaihat);
            imgbaihat = itemView.findViewById(R.id.imageviewsearchbaihat);
            imgluotthich = itemView.findViewById(R.id.imageviewsearchluotthich);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("cakhuc",mangbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgluotthich.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getService();
                    Call<String> call= dataService.UpdateLuotThich("1",mangbaihat.get(getPosition()).getIdBaiHat());
                   call.enqueue(new Callback<String>() {
                       @Override
                       public void onResponse(Call<String> call, Response<String> response) {
                           String kq = response.body();
                           if(kq.equals("success"))
                           {
                               Toast.makeText(context,"Da thich",1).show();

                           } else {
                               Toast.makeText(context,"Fail!",1).show();
                           }
                       }

                       @Override
                       public void onFailure(Call<String> call, Throwable t) {

                       }
                   });
                }

            });

            imgluotthich.setEnabled(false);

        }
    }

}
