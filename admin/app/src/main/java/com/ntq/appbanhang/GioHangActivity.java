package com.ntq.appbanhang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.List;

public class GioHangActivity extends AppCompatActivity {
    TextView gioTrong,txtTien;
    Toolbar backGio;
    RecyclerView recyclerViewGio;
    Button btnTaoDon;
    GioHangAdapter gioHangAdapter;
    List<GioHang> dsGioHang;
    long money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        gioTrong=findViewById(R.id.gioTrong);
        backGio=findViewById(R.id.backGio);
        recyclerViewGio=findViewById(R.id.listGioHang);
        txtTien=findViewById(R.id.txtTien);
        btnTaoDon=findViewById(R.id.btnTaoDon);
        tinhTien();
        setSupportActionBar(backGio);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        backGio.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recyclerViewGio.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerViewGio.setLayoutManager(layoutManager);

        if(Server.listGioHang.size()==0){
            gioTrong.setVisibility(View.VISIBLE);
        }else{
            gioHangAdapter=new GioHangAdapter(getApplicationContext(), Server.listGioHang);
            recyclerViewGio.setAdapter(gioHangAdapter);
        }
        btnTaoDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( Server.listMuaHang.size()==0){
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Vui lòng chọn sản phẩm bạn muốn mua!");
                }else {
                    Intent intent = new Intent(GioHangActivity.this, DonHangActivity.class);
                    intent.putExtra("tongtien",money);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();

    }
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void eventTinhTienGioHang(TinhTongEventBus e){
        if(e!=null){
            tinhTien();
        }
    }

    private void tinhTien() {
             money=0;
            for (int i=0; i<Server.listMuaHang.size();i++){
                money=money+Server.listMuaHang.get(i).getGiaSP()*Server.listMuaHang.get(i).getSoLuong();
            }
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            txtTien.setText("Tổng tiền: "+decimalFormat.format(money)+"Đ");

    }
}