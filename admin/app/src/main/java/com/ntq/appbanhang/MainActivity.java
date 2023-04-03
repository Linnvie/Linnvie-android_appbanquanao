package com.ntq.appbanhang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewManHinhChinh;
    ArrayList<SanPham> mangSP;
    ImageView imgRight;
    SanPhamAdapter sanPhamAdapter;
    SearchView searchView;

    ImageView imvRight, imvHeart;
    CardView cardViewSpMoiNhat;
    ImageView btnCart;
    public static ArrayList<Heart> heartArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        if(Server.listGioHang==null){
            Server.listGioHang=new ArrayList<>();
        }
        imgRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),MainActivityDanhMucSP.class);
                startActivity(intent);
            }
        });
        SearchViewClick();

        clickRight();
        HeartClick();
        ClickCart();


        if(CheckConnection.haveNetworkConnection(getApplicationContext())) {
            ActionViewFlipper();
            DuLieuSanPhamMoiNhat();
        }
        else{
            CheckConnection.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối internet");
            finish();
        }
    }

    private void ClickCart() {
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GioHangActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void HeartClick() {
        imvHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HeartActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void clickRight() {
        imvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivityDanhMucSP.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void SearchViewClick() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                sanPhamAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sanPhamAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void DuLieuSanPhamMoiNhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongDanSPMoiNhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null) {
                    int ID = 0;
                    String TenSP = "";
                    Integer GiaSP = 0;
                    Integer GiaSPSale = 0;
                    String HinhAnhSP = "";
                    String MoTaSP = "";
                    String Star1 = "";
                    String Star2 = "";
                    String Star3 = "";
                    String Star4 = "";
                    String Star5 = "";
                    String Heart = "";
                    String HeartEd = "";
                    String Sold = "";
                    String Kho = "";
                    String Mau1 = "";
                    String Mau2 = "";
                    String Mau3 = "";
                    String Mau4 = "";
                    int IdSP = 0;
                    for(int i = 0; i <response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            TenSP = jsonObject.getString("tensanpham");
                            GiaSP = jsonObject.getInt("giasanpham");
                            GiaSPSale = jsonObject.getInt("pricesale");
                            HinhAnhSP = jsonObject.getString("hinhanhsanpham");
                            MoTaSP = jsonObject.getString("moTasanpham");
                            Star1 = jsonObject.getString("star");
                            Star2 = jsonObject.getString("star");
                            Star3 = jsonObject.getString("star");
                            Star4 = jsonObject.getString("star");
                            Star5 = jsonObject.getString("star");
                            Heart = jsonObject.getString("heart");
                            //HeartEd = jsonObject.getString("heared");
                            //Sold = jsonObject.getString("sold");
                           // Kho = jsonObject.getString("warehouse");
                            Mau1 = jsonObject.getString("mau1");
                            Mau2 = jsonObject.getString("mau2");
                            Mau3 = jsonObject.getString("mau3");
                            Mau4 = jsonObject.getString("mau4");
                            IdSP = jsonObject.getInt("idsanpham");
                            //mangSP.add(new SanPham(ID, TenSP,  GiaSPSale, GiaSP, HinhAnhSP, MoTaSP, Star1, Star2 , Star3, Star4, Star5,Heart, HeartEd, Sold, Kho, Mau1, Mau2, Mau3, Mau4, IdSP));
                            mangSP.add(new SanPham(ID,TenSP,GiaSPSale, GiaSP,HinhAnhSP,MoTaSP, Star1, Star2,Star3,Star4,Star5,Heart,Mau1,Mau2, Mau3, Mau4,IdSP));
                            sanPhamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFlipper() {
        ArrayList<String> mangQuangCao = new ArrayList<>();
        mangQuangCao.add("https://cf.shopee.vn/file/1e51af31817aa529b0ea0ccad456ca6c");
        mangQuangCao.add("https://cf.shopee.vn/file/fbf4dbad3ca4fa5083569401e0169134");
        mangQuangCao.add("https://cf.shopee.vn/file/05c76b0f17fe6e6e7470ecc65a989136");

        for(int i=0; i<mangQuangCao.size();i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(mangQuangCao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);//thời gian chờ trước khi lật sang view tiếp theo
        viewFlipper.setAutoStart(true);//lật giữa các view
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }


    private void AnhXa() {
        viewFlipper = findViewById(R.id.viewFlipper);
        recyclerViewManHinhChinh = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);
        imgRight=findViewById(R.id.imgRight);
        searchView.setQueryHint("Tìm kiếm sản phẩm");
        mangSP = new ArrayList<>();
        sanPhamAdapter=new SanPhamAdapter(getApplicationContext(), mangSP,R.layout.dong_sanpham_moinhat);
        recyclerViewManHinhChinh.setHasFixedSize(true);
        recyclerViewManHinhChinh.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewManHinhChinh.setAdapter(sanPhamAdapter);
        imvRight = findViewById(R.id.imgRight);
        imvHeart = findViewById(R.id.heartHome);
        btnCart = findViewById(R.id.btncart);
       if(Server.listGioHang==null){
            Server.listGioHang=new ArrayList<>();
       }

       if(heartArrayList != null) {

       }
       else {
           heartArrayList = new ArrayList<>();
       }
    }
}