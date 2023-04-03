package com.ntq.appbanhang;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LichSuMuaActivity extends AppCompatActivity {
    Toolbar backlichsu;
    RecyclerView recycleLichSu;
    List<LichSuModel> listLichSu;
    LichSuAdapter lichSuAdapter;
    int madonhang=0;
    String sdt = "";
    String diaChi = "";
    String ghiChu = "";
    String thoigian="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_mua);
        backlichsu=findViewById(R.id.backlichsu);
        recycleLichSu=findViewById(R.id.recycleLichSu);

        setSupportActionBar(backlichsu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recycleLichSu.setLayoutManager(layoutManager);
        listLichSu=new ArrayList<>();
        lichSuAdapter= new LichSuAdapter(getApplicationContext(),listLichSu);
        recycleLichSu.setAdapter(lichSuAdapter);
        DuLieuLichSu();

        backlichsu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void DuLieuLichSu() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Server.urlgetDonHangChiTiet, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("aaa",response.toString());
                if(response!=null){
                    try {
                        JSONArray jsonArray= new JSONArray(response);
                        Log.d("aaa",jsonArray.toString());
                        for(int i = 0; i <jsonArray.length(); i++) {
                            List<ItemDonModel> lis=new ArrayList<>();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            madonhang = jsonObject.getInt("id");
                            sdt = jsonObject.getString("sodienthoai");
                            diaChi = jsonObject.getString("diachi");
                            ghiChu = jsonObject.getString("ghichu");
                            thoigian=jsonObject.getString("thoigian");
                            JSONArray js2= jsonObject.getJSONArray("resultchitiet");

                            for(int j=0;j<js2.length();j++){
                                JSONObject jo2=js2.getJSONObject(j);
                                lis.add(new ItemDonModel(jo2.getString("tensanpham"),
                                        jo2.getString("hinhanhsanpham"),jo2.getInt("soluong"),
                                        jo2.getInt("giatien")));

                            }

                            listLichSu.add(new LichSuModel(madonhang,sdt,diaChi,ghiChu,thoigian,lis));
                            lichSuAdapter.notifyDataSetChanged();
                        }

                    }catch (JSONException e){
                        e.printStackTrace();

                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(),"lỗi"+error);
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param= new HashMap<String,String>();
                param.put("username", Server.firebaseUser.getEmail());
                return param;
            }
        };
        requestQueue.add(stringRequest);

    }
}