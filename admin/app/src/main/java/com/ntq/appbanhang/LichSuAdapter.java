package com.ntq.appbanhang;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

public class LichSuAdapter extends RecyclerView.Adapter<LichSuAdapter.Holder> {
    Context context;
    private RecyclerView.RecycledViewPool recycledViewPool= new RecyclerView.RecycledViewPool();
    List<LichSuModel> listLichSu;

    public LichSuAdapter(Context context, List<LichSuModel> listLichSu) {
        this.context = context;
        this.listLichSu = listLichSu;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donhanglon,parent,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        LichSuModel lichSuModel=listLichSu.get(position);
        Log.d("aaa1",listLichSu.size()+"va"+listLichSu.get(position).getMaDonHang());
        holder.madon.setText("Mã đơn hàng "+lichSuModel.getMaDonHang());
        holder.diachidon.setText("Địa chỉ giao: "+lichSuModel.getDiaChi());
        holder.ghichudon.setText("Ghi chú: "+ lichSuModel.getGhiChu());
        holder.sdtdon.setText("Số điện thoại giao hàng: "+lichSuModel.getSdt());
        holder.thoigian.setText("Thời gian đặt: "+ lichSuModel.getThoigian());
        LinearLayoutManager layoutManager=new LinearLayoutManager(holder.rcdon.getContext(),LinearLayoutManager.VERTICAL,false);
        layoutManager.setInitialPrefetchItemCount(lichSuModel.getResultChiTiet().size());
        ItemLichSuAdapter itemLichSuAdapter= new ItemLichSuAdapter(context,lichSuModel.getResultChiTiet());
        holder.rcdon.setLayoutManager(layoutManager);
        holder.rcdon.setRecycledViewPool(recycledViewPool);
        holder.rcdon.setAdapter(itemLichSuAdapter);
        itemLichSuAdapter.notifyDataSetChanged();
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        int tienHang=0;
        for(int i=0; i<lichSuModel.getResultChiTiet().size();i++){
            tienHang=tienHang+lichSuModel.getResultChiTiet().get(i).getSoLuong()*lichSuModel.getResultChiTiet().get(i).getGiaTien();
        }
        holder.tongTienHang.setText(decimalFormat.format(tienHang)+"Đ");
        holder.tongTienDon.setText(decimalFormat.format(tienHang+30000)+"Đ");

    }

    @Override
    public int getItemCount() {
        if(listLichSu!=null){
            return listLichSu.size();
        }else{
            return 0;
        }
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView madon,sdtdon,diachidon,ghichudon,tongTienHang,tongTienDon,thoigian;
        RecyclerView rcdon;
        public Holder(@NonNull View itemView) {
            super(itemView);
            madon=itemView.findViewById(R.id.madon);
            sdtdon=itemView.findViewById(R.id.sdtdon);
            diachidon=itemView.findViewById(R.id.diachidon);
            ghichudon=itemView.findViewById(R.id.ghichudon);
            tongTienHang=itemView.findViewById(R.id.tongTienHang);
            tongTienDon=itemView.findViewById(R.id.tongTienDon);
            thoigian=itemView.findViewById(R.id.thoigiandon);
            rcdon=itemView.findViewById(R.id.rcdon);
        }
    }
}
