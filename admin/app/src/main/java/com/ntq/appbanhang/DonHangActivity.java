package com.ntq.appbanhang;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.checkerframework.checker.units.qual.Area;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DonHangActivity extends AppCompatActivity {
    Toolbar backDon;
    EditText inputGhichu,inputDiaChi,inputsdt;
    TextView tongTienHang,tongtiendon, tongTienDon;
    Button btnDatHang, btnHuyDatHang;
    RecyclerView recycledonxacnhan;
    ScrollView scrollView;
     String diaChi, ghiChu,email,ten,sdt;
     int tienvanchuyen;
    long tongtien1,tongtien2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);
        scrollView=findViewById(R.id.scrollView);
        recycledonxacnhan=findViewById(R.id.recycledonxacnhan);
        backDon=findViewById(R.id.backDon);
        inputDiaChi=findViewById(R.id.inputDiaChi);
        inputGhichu=findViewById(R.id.inputGhiChu);
        tongtiendon=findViewById(R.id.tongtiendon);
        tongTienDon=findViewById(R.id.tongTienDon);
        tongTienHang=findViewById(R.id.tongTienHang);
        btnDatHang=findViewById(R.id.btnDatHang);
        btnHuyDatHang=findViewById(R.id.btnHuyDatHang);
        inputsdt=findViewById(R.id.inputsdt);
        setSupportActionBar(backDon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        tongtien1=getIntent().getLongExtra("tongtien",0);
        tongtien2=tongtien1+30000;
        tongTienHang.setText("Tổng tiền hàng:         "+decimalFormat.format(tongtien1)+" Đ");
        tongtiendon.setText("Tổng thanh toán:           " +decimalFormat.format(tongtien2)+" Đ");
        tongTienDon.setText(decimalFormat.format(tongtien2)+" Đ");

        List<ItemDonModel> itemDonModelList= new ArrayList<>();
        for (GioHang gioHang: Server.listMuaHang){
            itemDonModelList.add(new ItemDonModel(gioHang.getTenSP(),gioHang.getHinhSP(),gioHang.getSoLuong(), (int) gioHang.getGiaSP()));
        }

        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        ItemLichSuAdapter itemLichSuAdapter= new ItemLichSuAdapter(getApplicationContext(),itemDonModelList);
        recycledonxacnhan.setLayoutManager(layoutManager);
        recycledonxacnhan.setAdapter(itemLichSuAdapter);
        backDon.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Server.listMuaHang.clear();
                Intent intent=new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnHuyDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Server.listMuaHang.clear();
                Intent intent=new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaChi= inputDiaChi.getText().toString().trim();
                ghiChu=inputGhichu.getText().toString().trim();
                sdt=inputsdt.getText().toString().trim();
                ten=Server.firebaseUser.getEmail();
                email= Server.firebaseUser.getEmail();
                tienvanchuyen=30000;
                if(TextUtils.isEmpty(diaChi) || TextUtils.isEmpty(ten) || TextUtils.isEmpty(sdt)){
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Vui lòng nhập đủ thông tin giao hàng! ");
                }else{
                    guiDonHang();
                    finish();
                }
            }
        });
    }

    private void guiDonHang() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Server.urlInssertDonHang, new Response.Listener<String>() {
            @Override
            public void onResponse(final String responsemadonhang) {
                Log.d("aaa",responsemadonhang);
                if( Integer.valueOf(responsemadonhang.trim())>0 ){
                    Log.d("aaa","vào" );
                    RequestQueue queue2=Volley.newRequestQueue(getApplicationContext());
                    StringRequest request2= new StringRequest(Request.Method.POST, Server.urlInsertDonHangChiTiet,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response2) {
                                    if(response2.equals("1")){
                                        Server.listMuaHang.clear();
                                        CheckConnection.ShowToast_Short(getApplicationContext(),"Mua hàng thành công");
                                        Intent intent= new Intent(getApplicationContext(),LichSuMuaActivity.class);
                                        startActivity(intent);
                                    } else {
                                        CheckConnection.ShowToast_Short(getApplicationContext(),"Mua hàng không thành công!");
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            JSONArray jsonArray= new JSONArray();
                            for(int i=0;i<Server.listMuaHang.size();i++){
                                JSONObject jsonObject= new JSONObject();
                                try {
                                    jsonObject.put("madonhang", responsemadonhang);
                                    jsonObject.put("masanpham", Server.listMuaHang.get(i).getIdSP());
                                    jsonObject.put("tensanpham",Server.listMuaHang.get(i).getTenSP());
                                    jsonObject.put("soluong",Server.listMuaHang.get(i).getSoLuong());
                                    jsonObject.put("giasanpham",Server.listMuaHang.get(i).getGiaSP());
                                    Log.d("aaa",jsonObject.toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                jsonArray.put(jsonObject);
                                Log.d("aaa",jsonArray.toString());
                            }
                            HashMap<String,String> hashMapchitiet= new HashMap<String,String>();
                            hashMapchitiet.put("jsonchitiet", jsonArray.toString());
                            return hashMapchitiet;
                        }
                    };
                    queue2.add(request2);
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
                param.put("tenkhachhang", ten);
                param.put("sdt", sdt);
                param.put("email", email);
                param.put("diachi", diaChi);
                param.put("ghichu", ghiChu);
                param.put("tienvanchuyen", String.valueOf(tienvanchuyen));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

}