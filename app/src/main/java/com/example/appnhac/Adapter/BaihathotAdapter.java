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

public class BaihathotAdapter extends RecyclerView.Adapter<BaihathotAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> arrayListBaiHat;

    public BaihathotAdapter(Context context, ArrayList<BaiHat> arrayListBaiHat) {
        this.context = context;
        this.arrayListBaiHat = arrayListBaiHat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_bai_hat_hot,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            BaiHat baiHat = arrayListBaiHat.get(position);
            holder.txtTen.setText(baiHat.getTenBaiHat().toString());
            holder.txtCaSi.setText(baiHat.getCaSi());
        Picasso.with(context).load(baiHat.getHinhbaiHat()).into(holder.imgHinh);

    }

    @Override
    public int getItemCount() {
        return arrayListBaiHat.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTen , txtCaSi;
        ImageView imgHinh ,imgLuotThich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.textviewtenbaihathot);
            txtCaSi = itemView.findViewById(R.id.textviewtencasibaihathot);
            imgHinh = itemView.findViewById(R.id.imageviewbaihathot);
            imgLuotThich = itemView.findViewById(R.id.imageviewLove);
            imgLuotThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     imgLuotThich.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getService();
                    Call<String> callback = dataService.UpdateLuotThich("1",arrayListBaiHat.get(getPosition()).getIdBaiHat());
                    callback.enqueue(new Callback<String>() {
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
                    imgLuotThich.setEnabled(false);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("cakhuc",arrayListBaiHat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
