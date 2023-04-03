package com.ntq.appbanhang;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

public class ItemLichSuAdapter extends RecyclerView.Adapter<ItemLichSuAdapter.Holder>{
    Context context;
    List<ItemDonModel> listItem;

    public ItemLichSuAdapter(Context context, List<ItemDonModel> listItem) {
        this.context = context;
        this.listItem = listItem;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_don_hang,parent,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        ItemDonModel itemDonModel=listItem.get(position);
        Log.d("aaa2",listItem.size()+"va"+listItem.get(position).getHinhanh());
        holder.tenitemdon.setText(itemDonModel.getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.giaitemdon.setText(decimalFormat.format(itemDonModel.getGiaTien())+"Đ");
        holder.slitemdon.setText("x"+itemDonModel.getSoLuong());
        Glide.with(context).load(itemDonModel.getHinhanh())
                .placeholder(R.drawable.loading_img)
                .error(R.drawable.no_image)
                .into(holder.imgitemdon);
    }

    @Override
    public int getItemCount() {
        if(listItem!=null){
            return listItem.size();
        }else{
            return 0;
        }
    }

    public class Holder extends RecyclerView.ViewHolder{
        ImageView imgitemdon;
        TextView tenitemdon,giaitemdon,slitemdon;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imgitemdon=itemView.findViewById(R.id.imgitemdon);
            tenitemdon=itemView.findViewById(R.id.tenitemdon);
            giaitemdon=itemView.findViewById(R.id.giaitemdon);
            slitemdon=itemView.findViewById(R.id.slitemdon);
        }
    }
}
