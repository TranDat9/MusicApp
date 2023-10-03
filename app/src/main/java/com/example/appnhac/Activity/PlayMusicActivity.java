package com.example.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appnhac.Adapter.ViewPagerPlaylistnahc;
import com.example.appnhac.Fragment.Fragment_Dia_Nhac;
import com.example.appnhac.Fragment.Frragment_Play_Cac_Bai_Hat;
import com.example.appnhac.Model.BaiHat;
import com.example.appnhac.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayMusicActivity extends AppCompatActivity {
    Toolbar toolbarPlaynhac;
    TextView txtTimesong, txtTotaltimesong;
    SeekBar seekBarTime;
    ImageButton imgplay , imgpre, imgnext, imgrepaet,imgrandom;
    ViewPager viewPagerPlayNhac;
    Fragment_Dia_Nhac fragment_dia_nhac;
    Frragment_Play_Cac_Bai_Hat frragment_play_cac_bai_hat;

    public static ArrayList<BaiHat> mangbaihat = new ArrayList<>();
    public  static ViewPagerPlaylistnahc adapternhac;
    MediaPlayer mediaPlayer;
    int possison =0;
    boolean repeat = false;
    boolean checkrand = false;
    boolean next = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        addEvents();
        eventClick();


    }

    private void eventClick() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapternhac.getItem(1) != null)
                {
                    if(mangbaihat.size()>0)
                    {
                        fragment_dia_nhac.Playnhac(mangbaihat.get(0).getHinhbaiHat());
                        handler.removeCallbacks(this);

                    } else {
                        handler.postDelayed(this,300);
                    }
                }
            }
        },500) ;
        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.iconplay);

                } else {
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.iconpause);
                }
            }
        });
        imgrepaet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat==false)
                {
                    if (checkrand==true)
                    {
                        checkrand= false;
                        imgrepaet.setImageResource(R.drawable.iconsyned);
                        imgrandom.setImageResource(R.drawable.iconsuffle);
                    }
                    imgrepaet.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                } else {
                    imgrepaet.setImageResource(R.drawable.iconrepeat);
                    repeat = true;
                }
            }
        });
        imgrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkrand==false)
                {
                    if (repeat==true)
                    {
                        repeat= false;
                        imgrandom.setImageResource(R.drawable.iconshuffled);
                        imgrepaet.setImageResource(R.drawable.iconrepeat);
                    }
                    imgrandom.setImageResource(R.drawable.iconshuffled);
                    checkrand = true;
                } else {
                    imgrandom.setImageResource(R.drawable.iconsuffle);
                    checkrand = false;
                }
            }
        });
        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                    mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mangbaihat.size() >0)
                {
                    if(mediaPlayer.isPlaying() ||mediaPlayer != null )
                    {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(possison < mangbaihat.size())
                    {
                        imgplay.setImageResource(R.drawable.iconpause);
                        possison++;
                        if(repeat==true)
                        {
                            if(possison ==0 )
                            {
                                possison= mangbaihat.size();
                            }
                            possison-=1;
                        }
                        if(checkrand==true)
                        {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if(index== possison){
                                possison= index-1;
                            }
                            possison= index;
                        }
                        if(possison> mangbaihat.size()-1)
                        {
                            possison = 0;
                        }
                        new PlayMp3().execute(mangbaihat.get(possison).getLinkBaiHat());
                        fragment_dia_nhac.Playnhac(mangbaihat.get(possison).getHinhbaiHat());
                        getSupportActionBar().setTitle(mangbaihat.get(possison).getTenBaiHat());
                           UpdateTime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                 Handler handler1 =new Handler();
                 handler1.postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         imgpre.setClickable(true);
                         imgnext.setClickable(true);
                     }
                 },5000);
            }
        });


        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mangbaihat.size() >0)
                {
                    if(mediaPlayer.isPlaying() ||mediaPlayer != null )
                    {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(possison < mangbaihat.size())
                    {
                        imgplay.setImageResource(R.drawable.iconpause);
                        possison--;
                        if(possison<0)
                        {
                            possison = mangbaihat.size()-1;
                        }
                        if(repeat==true)
                        {

                            possison+=1;
                        }
                        if(checkrand==true)
                        {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if(index== possison){
                                possison = index-1;
                            }
                            possison= index;
                        }

                        new PlayMp3().execute(mangbaihat.get(possison).getLinkBaiHat());
                        fragment_dia_nhac.Playnhac(mangbaihat.get(possison).getHinhbaiHat());
                        getSupportActionBar().setTitle(mangbaihat.get(possison).getTenBaiHat());
                         UpdateTime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 =new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },5000);
            }
        });
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if(intent.hasExtra("cakhuc"))
        {
            BaiHat baiHat = intent.getParcelableExtra("cakhuc");
            mangbaihat.add(baiHat);
        }

        if(intent.hasExtra("cacbaihat"))
        {
            ArrayList<BaiHat> ArrListbaihat = intent.getParcelableArrayListExtra("cacbaihat");
              mangbaihat= ArrListbaihat;
        }
    }

    private void addEvents() {
        toolbarPlaynhac = findViewById(R.id.toolbarplaynahc);
        seekBarTime = findViewById(R.id.seekbarsong);
        imgplay = findViewById(R.id.imagebuttonplay);
        imgpre = findViewById(R.id.imagebuttonpreview);
        imgnext = findViewById(R.id.imagebuttonnext);
        imgrepaet = findViewById(R.id.imagebuttonrepeat);
        imgrandom = findViewById(R.id.imagebuttonsuffler);
        txtTimesong = findViewById(R.id.textviewtimesong);
        txtTotaltimesong = findViewById(R.id.textviewtotaltimesong);
        viewPagerPlayNhac = findViewById(R.id.viewpagetplaynhac);
        setSupportActionBar(toolbarPlaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop();
                mangbaihat.clear();
            }
        });
        toolbarPlaynhac.setTitleTextColor(Color.WHITE);
        fragment_dia_nhac = new Fragment_Dia_Nhac();
        frragment_play_cac_bai_hat = new Frragment_Play_Cac_Bai_Hat();
        adapternhac = new ViewPagerPlaylistnahc(getSupportFragmentManager());
        adapternhac.AddFragmet(frragment_play_cac_bai_hat);
        adapternhac.AddFragmet(fragment_dia_nhac);
        viewPagerPlayNhac.setAdapter(adapternhac);
        fragment_dia_nhac = (Fragment_Dia_Nhac) adapternhac.getItem(1);
        if(mangbaihat.size()>0)
        {
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenBaiHat());
            new PlayMp3().execute(mangbaihat.get(0).getLinkBaiHat());
            imgplay.setImageResource(R.drawable.iconpause);

        }
    }
    class PlayMp3 extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];

        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }

    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBarTime.setMax(mediaPlayer.getDuration());
    }
    private void UpdateTime()
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer!=null)
                {
                    seekBarTime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next=true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    });
                }
            }
        },3000);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                       if(next ==true)
                       {
                           if(possison < mangbaihat.size())
                           {
                               imgplay.setImageResource(R.drawable.iconpause);
                               possison++;
                               if(repeat==true)
                               {
                                   if(possison ==0 )
                                   {
                                       possison= mangbaihat.size();
                                   }
                                   possison-=1;
                               }
                               if(checkrand==true)
                               {
                                   Random random = new Random();
                                   int index = random.nextInt(mangbaihat.size());
                                   if(index== possison){
                                       possison= index-1;
                                   }
                                   possison= index;
                               }
                               if(possison> mangbaihat.size()-1)
                               {
                                   possison = 0;
                               }
                               new PlayMp3().execute(mangbaihat.get(possison).getLinkBaiHat());
                               fragment_dia_nhac.Playnhac(mangbaihat.get(possison).getHinhbaiHat());
                               getSupportActionBar().setTitle(mangbaihat.get(possison).getTenBaiHat());

                           }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 =new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },5000);
                next = false;
                handler1.removeCallbacks(this);

                       } else {
                           handler.postDelayed(this,1000);
                       }
            }
        },1000);
    }
}