package com.ntq.appbanhang;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoaiSPFragment extends Fragment {
    GridView gridLoai;
    SearchView searchLoai;
    ArrayList<LoaiSanPham> listLoaiSP;
    LoaiSanPhamAdapter adapterLoai;
    View view;

    public LoaiSPFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.loai_sp_fragment, container, false);
        gridLoai = view.findViewById(R.id.gridLoai);
        searchLoai = view.findViewById(R.id.searchLoai);
        listLoaiSP = new ArrayList<>();
        adapterLoai = new LoaiSanPhamAdapter(this.getContext(), R.layout.item_loaisp, listLoaiSP);
        getDataLoaiSP();

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchLoai = view.findViewById(R.id.searchLoai);
        searchLoai.setQueryHint("Tìm kiếm loại sản phẩm");
        gridLoai.setAdapter(adapterLoai);
        searchLoai.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        searchLoai.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterLoai.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterLoai.getFilter().filter(newText);
                return false;
            }

        });

        gridLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),SanPhamActivity.class);
                intent.putExtra("idloaisanpham",listLoaiSP.get((int) gridLoai.getAdapter().getItemId(i)-1).getId());
                intent.putExtra("tenloaisanpham",listLoaiSP.get((int) gridLoai.getAdapter().getItemId(i)-1).getTenLoai());
//                intent.putExtra("idloaisanpham",listLoaiSP.get(i).getId());
//                intent.putExtra("tenloaisanpham",listLoaiSP.get(i).getTenLoai());
                startActivity(intent);
//                getActivity().finish();

            }
        });
        return view;
    }

    private void getDataLoaiSP() {
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.urlLoaiSP, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject object = response.getJSONObject(i);
                                    listLoaiSP.add(new LoaiSanPham(
                                            object.getInt("id"),
                                            object.getString("tensanpham"),
                                            object.getString("hinhanhloaisanpham")));
                                    adapterLoai.notifyDataSetChanged();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        CheckConnection.ShowToast_Short(getContext(),error.toString());
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }


}


















