package com.example.appnhac.Service;

import com.example.appnhac.Model.Album;
import com.example.appnhac.Model.BaiHat;
import com.example.appnhac.Model.ChuDe;
import com.example.appnhac.Model.Playlist;
import com.example.appnhac.Model.Quangcao;
import com.example.appnhac.Model.TheLoai;
import com.example.appnhac.Model.Theloaitrongngay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface DataService {

    @GET("congbanner.php")
    Call<List<Quangcao>> getDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> getPlaylistForCurrentDay();


    @GET("chudevatheloaitrongngay.php")
    Call<Theloaitrongngay> GetCategoryMusic();

    @GET("albumhot.php")
    Call<List<Album>> GetAlbumHot();

    @GET("baihatduocthich.php")
    Call<List<BaiHat>> GetBaiHatHot();

    @FormUrlEncoded
    @POST("Danhsachbaihat.php")
    Call<List<BaiHat>> getBaiHat(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("Danhsachbaihat.php")
    Call<List<BaiHat>> getBaiHatTheoPlayList(@Field("idplaylist") String idplaylist);

    @GET("danhsachcacplaylist.php")
    Call<List<Playlist>> GetDanhSachCacPlayList();

    @FormUrlEncoded
    @POST("Danhsachbaihat.php")
    Call<List<BaiHat>> getBaiHatTheoTheLoai(@Field("idtheloai") String idtheloai);


    @GET("tatcacacchude.php")
    Call<List<ChuDe>> GetDanhSachCacChuDe();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> getBaiHatTheoChude(@Field("idchude") String idchude);

    @GET("tatcaalbum.php")
    Call<List<Album>> GetDanhSachCacAlbum();

    @FormUrlEncoded
    @POST("Danhsachbaihat.php")
    Call<List<BaiHat>> getBaiHatTheoAlbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> UpdateLuotThich(@Field("luotthich") String luotthich,@Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("search.php")
    Call<List<BaiHat>> GetSearchBH(@Field("tukhoa") String tukhoa);



}

