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

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatAdapter extends RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> mangbaihat;

    public DanhsachbaihatAdapter(Context context, ArrayList<BaiHat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_danh_sach_bai_hat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                 BaiHat baiHat = mangbaihat.get(position);
                 holder.txtCasi.setText(baiHat.getCaSi());
                 holder.txtTenbaihat.setText(baiHat.getTenBaiHat());
                 holder.txtIndex.setText(position+1+"");
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtIndex,txtTenbaihat,txtCasi;
        ImageView imgLuotthich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCasi = itemView.findViewById(R.id.tenviewcasi);
            txtIndex = itemView.findViewById(R.id.textviewdanhsachindex);
            txtTenbaihat = itemView.findViewById(R.id.textviewtenbaihat);
             imgLuotthich = itemView.findViewById(R.id.imageviewluotthichdanhsachbaihat);
            imgLuotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgLuotthich.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getService();
                    Call<String> callback = dataService.UpdateLuotThich("1",mangbaihat.get(getPosition()).getIdBaiHat());
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
                    imgLuotthich.setEnabled(false);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("cakhuc",mangbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
