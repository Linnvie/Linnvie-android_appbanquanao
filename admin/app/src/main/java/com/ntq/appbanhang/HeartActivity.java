package com.ntq.appbanhang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class HeartActivity extends AppCompatActivity {

    ListView listViewHeart;
    TextView txtThongBao;
    Toolbar toolbarHeart;
    HeartAdapter heartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart);
        Control();
        ActionToolbar();
        CheckData();
        RemoveProductHeart();
    }



    private void RemoveProductHeart() {
        listViewHeart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(HeartActivity.this);
                alertDialog.setTitle("Xoá sản phẩm yêu thích!");
                alertDialog.setMessage("Bạn có chắc xoá sản phẩm này không?");
                alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(MainActivity.heartArrayList.size() <= 0) {
                            txtThongBao.setVisibility(View.VISIBLE);
                        }
                        else {
                            MainActivity.heartArrayList.remove(position);
                            heartAdapter.notifyDataSetChanged();
                            if(MainActivity.heartArrayList.size() <= 0) {
                                txtThongBao.setVisibility(View.VISIBLE);
                            }
                            else {
                                txtThongBao.setVisibility(View.INVISIBLE);
                                heartAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
                alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        heartAdapter.notifyDataSetChanged();
                    }
                });
                alertDialog.show();
                return true;
            }
        });
    }


    private void CheckData() {
        if(MainActivity.heartArrayList.size() <= 0) {
            heartAdapter.notifyDataSetChanged();
            txtThongBao.setVisibility(View.VISIBLE);
            listViewHeart.setVisibility(View.INVISIBLE);
        }
        else {
            heartAdapter.notifyDataSetChanged();
            txtThongBao.setVisibility(View.INVISIBLE);
            listViewHeart.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarHeart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarHeart.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HeartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void Control() {
        listViewHeart = findViewById(R.id.listviewHeart);
        txtThongBao = findViewById(R.id.txtThongBao);
        toolbarHeart = findViewById(R.id.toolBarHeart);
        heartAdapter = new HeartAdapter(HeartActivity.this, MainActivity.heartArrayList);
        listViewHeart.setAdapter(heartAdapter);
    }
}