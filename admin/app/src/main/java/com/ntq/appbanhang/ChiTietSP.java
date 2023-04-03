package com.ntq.appbanhang;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.nex3z.notificationbadge.NotificationBadge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChiTietSP extends AppCompatActivity {

    ImageView imvHinhCTSP;
    TextView txtTenSPCT, txtGiaSPCT, txtGiaSaleSPCT;
    ImageView imvStarCTSP1, imvStarCTSP2, imvStarCTSP3, imvStarCTSP4, imvStarCTSP5, imvhHearCTSP;
    ImageButton imvbtnGioHang;
    NotificationBadge notificationBadge;
    TextView txtMota, txtSold;
    ArrayList<SanPham> mangSP;
    SanPhamAdapter sanPhamAdapter;
    SanPham sp;

    Button btnBackCTSP, btnDecrease, btnIncrease, btnAmoutProduct, btnMua, btnGioHang;

    int count = 0;
    int heartCount = 0;
    int countClickSize = 0;
    int mauClick = 0;

    int ID = 0;
    String tenChiTiet = "";
    Integer giaSPChiTiet = 0;
    Integer giaSPSaleChiTiet = 0;
    String hinhAnhSPChiTiet = "";
    String MoTaSPChiTiet = "";
    String Star1ChiTiet = "";
    String Star2ChiTiet = "";
    String Star3ChiTiet = "";
    String Star4ChiTiet = "";
    String Star5ChiTiet = "";
    String HeartChiTiet = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_sp);
        setControl();
        GetDuLieu();
        setSeeMore();
        setBack();
        setAddGioHang();
        setMuaHang();
        ClickGioHang();

    }

    private void setMuaHang() {
        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGioHangCTSP(Gravity.BOTTOM, R.layout.mua_hang);
                
            }
        });
    }


    private void setAddGioHang() {
        imvbtnGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGioHangCTSP(Gravity.BOTTOM, R.layout.gio_hang_chitiet_sp);
            }
        });
    }

    private void setBack() {
        btnBackCTSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChiTietSP.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setSeeMore() {
        ExpandableTextView textView = findViewById(R.id.expand_text_view);
        textView.setText(txtMota.getText());

    }


    private void GetDuLieu() {

        String Sold = "";
        int IdSP = 0;
        sp = (SanPham) getIntent().getSerializableExtra("chitiet");
        ID = sp.getID();
        tenChiTiet = sp.getTenSP();
        giaSPChiTiet = sp.getGiaSP();
        giaSPSaleChiTiet = sp.getGiaSale();
        hinhAnhSPChiTiet = sp.getHinhAnhSP();
        MoTaSPChiTiet = sp.getMoTaSP();
        Star1ChiTiet = sp.getStar1();
        Star2ChiTiet = sp.getStar2();
        Star3ChiTiet = sp.getStar3();
        Star4ChiTiet = sp.getStar4();
        Star5ChiTiet = sp.getStar5();
        HeartChiTiet = sp.getHeart();
        Sold = sp.getSold();
        IdSP = sp.getIdSP();
        txtTenSPCT.setText(tenChiTiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGiaSPCT.setText(decimalFormat.format(giaSPChiTiet) + "Đ");
        txtGiaSaleSPCT.setText(decimalFormat.format(giaSPSaleChiTiet) + "Đ");
        txtGiaSaleSPCT.setPaintFlags(txtGiaSaleSPCT.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        txtSold.setText("Đã bán: 4" );
        Glide.with(getApplicationContext()).load(hinhAnhSPChiTiet)
                .placeholder(R.drawable.account)
                .error(R.drawable.cart)
                .into(imvHinhCTSP);
        txtMota.setText(MoTaSPChiTiet);

        Glide.with(getApplicationContext()).load(Star1ChiTiet)
                .placeholder(R.drawable.account)
                .error(R.drawable.cart)
                .into(imvStarCTSP1);
        Glide.with(getApplicationContext()).load(Star2ChiTiet)
                .placeholder(R.drawable.account)
                .error(R.drawable.cart)
                .into(imvStarCTSP2);
        Glide.with(getApplicationContext()).load(Star3ChiTiet)
                .placeholder(R.drawable.account)
                .error(R.drawable.cart)
                .into(imvStarCTSP3);
        Glide.with(getApplicationContext()).load(Star4ChiTiet)
                .placeholder(R.drawable.account)
                .error(R.drawable.cart)
                .into(imvStarCTSP4);
        Glide.with(getApplicationContext()).load(Star5ChiTiet)
                .placeholder(R.drawable.account)
                .error(R.drawable.cart)
                .into(imvStarCTSP5);

    }


    private void setControl() {
        imvHinhCTSP = findViewById(R.id.imvChiTietSP);
        txtTenSPCT = findViewById(R.id.tenChiTietSP);
        txtGiaSPCT = findViewById(R.id.txtGiaChiTietSP);
        txtGiaSaleSPCT = findViewById(R.id.txtGiaSaleChiTietSP);
        imvStarCTSP1 = findViewById(R.id.start1CTSP);
        imvStarCTSP2 = findViewById(R.id.start2CTSP);
        imvStarCTSP3 = findViewById(R.id.start3CTSP);
        imvStarCTSP4 = findViewById(R.id.start4CTSP);
        imvStarCTSP5 = findViewById(R.id.start5CTSP);
        txtMota = findViewById(R.id.expandable_text);
        btnBackCTSP = findViewById(R.id.btnBackCTSP);
        imvbtnGioHang = findViewById(R.id.btnThemVaoGioHang);

        txtSold = findViewById(R.id.txtSold);

        btnMua = findViewById(R.id.btnMua);
        btnGioHang = findViewById(R.id.btnGioHang);
        notificationBadge = findViewById(R.id.slcart);
        if (Server.listGioHang != null) {
            int total = 0;
            for (int i = 0; i < Server.listGioHang.size(); i++) {
                total = total + Server.listGioHang.get(i).getSoLuong();
            }
            notificationBadge.setText(String.valueOf(total));
        }

    }


    private void DialogGioHangCTSP(int gravity, int view) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Xác định vị trí của dialog
        WindowManager.LayoutParams windowAtribute = window.getAttributes();
        windowAtribute.gravity = gravity;
        window.setAttributes(windowAtribute);

        //================= khi click ra ngoài diaolog sẽ tắt dialog =================
        if (Gravity.BOTTOM == gravity) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }

        // =====================================================
        if (view == R.layout.gio_hang_chitiet_sp) {
            Button btnAddSP = dialog.findViewById(R.id.btnAddSP);
            btnAddSP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    Toast.makeText(ChiTietSP.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    themGioHang();
                }
            });
        } else if (view == R.layout.mua_hang) {
            Button btnMuaHang = dialog.findViewById(R.id.btnAddMuaHang);
            btnMuaHang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    Toast.makeText(ChiTietSP.this, "Đi đến thanh toán", Toast.LENGTH_SHORT).show();
                    int soluongmua = Integer.parseInt(btnAmoutProduct.getText().toString());
                    Server.listMuaHang.add(new GioHang(sp.getID(),sp.getTenSP(), sp.getGiaSP(), sp.getHinhAnhSP(), soluongmua));
                    Intent intent = new Intent(ChiTietSP.this, DonHangActivity.class);
                    long money = sp.getGiaSP() * soluongmua;
                    intent.putExtra("tongtien", money);
                    startActivity(intent);
                    finish();

                }
            });
        }

        dialog.show();
        // ================== Tăng giảm số lượng mua sp ==================
        btnDecrease = dialog.findViewById(R.id.btnDecrease);
        btnIncrease = dialog.findViewById(R.id.btnIncrease);
        btnAmoutProduct = dialog.findViewById(R.id.numberProduct);
        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count == 0) {
                    count = 0;
                } else if (count >= 1) {
                    count = count - 1;
                }
                btnAmoutProduct.setText(count + "");
            }
        });
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = count + 1;
                btnAmoutProduct.setText(count + "");
            }
        });
        //======================= Gán csdl cho sản phẩm  ===========================
        SanPham sp = (SanPham) getIntent().getSerializableExtra("chitiet");
        Integer giaSP = 0;
        TextView txtGia = dialog.findViewById(R.id.txtGiaCTSPGioHang);
        giaSP = sp.getGiaSP();
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGia.setText("Giá: " + decimalFormat.format(giaSP) + "Đ");

        String kho = "";
        TextView txtKho = dialog.findViewById(R.id.kho);
        kho = sp.getKho();
        txtKho.setText("Kho: 10" );

        String hinhAnh = "";
        ImageView imvHinh = dialog.findViewById(R.id.imvHinh);
        hinhAnh = sp.getHinhAnhSP();
        Glide.with(getApplicationContext()).load(hinhAnh)
                .placeholder(R.drawable.account)
                .error(R.drawable.cart)
                .into(imvHinh);
        // ========================= Gán csdl cho màu  =========================
        String mau1 = "", mau2 = "", mau3 = "", mau4 = "";
        ImageView imvMau1 = dialog.findViewById(R.id.imvmau1);
        ImageView imvMau2 = dialog.findViewById(R.id.imvmau2);
        ImageView imvMau3 = dialog.findViewById(R.id.imvmau3);
        ImageView imvMau4 = dialog.findViewById(R.id.imvmau4);
        mau1 = sp.getMau1();
        mau2 = sp.getMau2();
        mau3 = sp.getMau3();
        mau4 = sp.getMau4();

        Glide.with(getApplicationContext()).load(mau1)
                .placeholder(R.drawable.account)
                .error(R.drawable.cart)
                .into(imvMau1);

        Glide.with(getApplicationContext()).load(mau2)
                .placeholder(R.drawable.account)
                .error(R.drawable.cart)
                .into(imvMau2);

        Glide.with(getApplicationContext()).load(mau3)
                .placeholder(R.drawable.account)
                .error(R.drawable.cart)
                .into(imvMau3);

        Glide.with(getApplicationContext()).load(mau4)
                .placeholder(R.drawable.account)
                .error(R.drawable.cart)
                .into(imvMau4);

        //==================================================================

        Button btnSize = dialog.findViewById(R.id.size);
        btnSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countClickSize++;
                if (countClickSize % 2 == 0) {
                    btnSize.setBackgroundColor(Color.parseColor("#d4d4d4"));
                } else {
                    btnSize.setBackgroundColor(Color.parseColor("#facd92"));
                }
            }
        });


        TextView txtMauChon = dialog.findViewById(R.id.txtMauDcChon);
        setMauClick(imvMau1, txtMauChon);
        setMauClick(imvMau2, txtMauChon);
        setMauClick(imvMau3, txtMauChon);
        setMauClick(imvMau4, txtMauChon);


    }

    private void themGioHang() {
        if (Server.listGioHang.size() > 0) {
            int soluong = Integer.parseInt(btnAmoutProduct.getText().toString());
            boolean flag = false;
            for (int i = 0; i < Server.listGioHang.size(); i++) {
                if (Server.listGioHang.get(i).getIdSP() == sp.getID()) {
                    Server.listGioHang.get(i).setSoLuong(soluong + Server.listGioHang.get(i).getSoLuong());
                    Server.listGioHang.get(i).setGiaSP(sp.getGiaSP());
                    flag = true;
                }
            }
            if (flag == false) {
                int gia = sp.getGiaSP();
                GioHang gioHang = new GioHang();
                gioHang.setGiaSP(gia);
                gioHang.setTenSP(sp.getTenSP());
                gioHang.setIdSP(sp.getID());
                gioHang.setSoLuong(soluong);
                gioHang.setHinhSP(sp.getHinhAnhSP());
                Server.listGioHang.add(gioHang);
            }
        } else {
            int soluong = Integer.parseInt(btnAmoutProduct.getText().toString());
            int gia = sp.getGiaSP();
            GioHang gioHang = new GioHang();
            gioHang.setGiaSP(gia);
            gioHang.setTenSP(sp.getTenSP());
            gioHang.setIdSP(sp.getID());
            gioHang.setSoLuong(soluong);
            gioHang.setHinhSP(sp.getHinhAnhSP());
            Server.listGioHang.add(gioHang);

        }
        int total = 0;
        for (int i = 0; i < Server.listGioHang.size(); i++) {
            total = total + Server.listGioHang.get(i).getSoLuong();
        }
        notificationBadge.setText(String.valueOf(total));

    }

    private void setMauClick(ImageView imv, TextView txt) {
        float xScale = imv.getScaleX();
        float ySacle = imv.getScaleY();
        imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mauClick++;
                if (mauClick % 2 == 0) {
                    txt.setText("Chưa có màu được chọn");
                    imv.setScaleX((xScale));
                    imv.setScaleY((float) (ySacle));
                } else {

                    imv.setScaleX((float) (xScale + 0.5));
                    imv.setScaleY((float) (ySacle + 0.5));
                    txt.setText("");
                }
            }
        });

    }
    // ==============================================================


    @Override
    protected void onResume() {
        super.onResume();
        if (Server.listGioHang != null) {
            int total = 0;
            for (int i = 0; i < Server.listGioHang.size(); i++) {
                total = total + Server.listGioHang.get(i).getSoLuong();
            }
            notificationBadge.setText(String.valueOf(total));
        }
    }

    private void ClickGioHang() {
        btnGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChiTietSP.this, GioHangActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}