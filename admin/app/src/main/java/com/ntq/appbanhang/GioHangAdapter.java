package com.ntq.appbanhang;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {
    Context context;
    List<GioHang> listGioHang;

    public GioHangAdapter(Context context, List<GioHang> listGioHang) {
        this.context = context;
        this.listGioHang = listGioHang;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GioHang item= listGioHang.get(position);
        holder.txtTenGio.setText(item.getTenSP());
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        holder.txtGiaGio.setText(decimalFormat.format((item.getGiaSP()*item.getSoLuong()))+"Đ");
        holder.txtsl.setText(item.getSoLuong()+"");
        Glide.with(context).load(item.getHinhSP())
                .placeholder(R.drawable.loading_img)
                .error(R.drawable.no_image)
                .into(holder.imgGio);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Server.listMuaHang.add(item);
                    EventBus.getDefault().postSticky(new TinhTongEventBus());
                }else{
                    for (int i=0;i<Server.listMuaHang.size();i++){
                        if(Server.listMuaHang.get(i).getIdSP()==item.getIdSP()){
                            Server.listMuaHang.remove(i);
                            EventBus.getDefault().postSticky(new TinhTongEventBus());
                        }
                    }
                }
            }
        });
        holder.setButtonClickListenner(new IButtonClickListenner() {
            @Override
            public void onButtonClick(View view, int pos, int giatri) {
                if(giatri==1){
                    if(listGioHang.get(pos).getSoLuong()>1){
                        int slmoi=listGioHang.get(pos).getSoLuong()-1;
                        listGioHang.get(pos).setSoLuong(slmoi);
                        holder.txtsl.setText(listGioHang.get(pos).getSoLuong()+"");
                        holder.txtGiaGio.setText(decimalFormat.format((listGioHang.get(pos).getGiaSP()*listGioHang.get(pos).getSoLuong()))+"Đ");
                        EventBus.getDefault().postSticky(new TinhTongEventBus());
                    }else if(listGioHang.get(pos).getSoLuong()==1){
                        AlertDialog.Builder xoaDialog=new AlertDialog.Builder(view.getRootView().getContext());
                        xoaDialog.setTitle("Thông báo");
                        xoaDialog.setMessage("Bạn có muốn xóa sản phẩm này khỏi giỏ hàng không?");
                        xoaDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(holder.checkBox.isChecked()){
                                    holder.checkBox.setChecked(false);
                                }
                                Server.listGioHang.remove(pos);
                                notifyDataSetChanged();

                                EventBus.getDefault().postSticky(new TinhTongEventBus());
                            }
                        });
                        xoaDialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        xoaDialog.show();

                    }

                }else if(giatri==2){
                    if(listGioHang.get(pos).getSoLuong()<20){
                        int slmoi=listGioHang.get(pos).getSoLuong()+1;
                        listGioHang.get(pos).setSoLuong(slmoi);
                    }
                    holder.txtsl.setText(listGioHang.get(pos).getSoLuong()+"");
                    holder.txtGiaGio.setText(decimalFormat.format((listGioHang.get(pos).getGiaSP()*listGioHang.get(pos).getSoLuong()))+"Đ");
                    EventBus.getDefault().postSticky(new TinhTongEventBus());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listGioHang!=null){
            return listGioHang.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgGio;
        TextView txtTenGio,txtGiaGio,txtsl;
        Button btntang,btngiam;
        IButtonClickListenner buttonClickListenner;
        CheckBox checkBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgGio=itemView.findViewById(R.id.imgGio);
            txtsl=itemView.findViewById(R.id.txtsl);
            txtGiaGio=itemView.findViewById(R.id.txtGiaGio);
            txtTenGio=itemView.findViewById(R.id.txtTenGio);
            btntang= itemView.findViewById(R.id.btntang);
            btngiam= itemView.findViewById(R.id.btngiam);
            checkBox=itemView.findViewById(R.id.checkbox);

            btntang.setOnClickListener(this);
            btngiam.setOnClickListener(this);
        }

        public void setButtonClickListenner(IButtonClickListenner buttonClickListenner) {
            this.buttonClickListenner = buttonClickListenner;
        }

        @Override
        public void onClick(View view) {
            if(view==btngiam){
                buttonClickListenner.onButtonClick(view, getAdapterPosition(),1);
            }else if(view==btntang){
                buttonClickListenner.onButtonClick(view, getAdapterPosition(),2);
            }
        }
    }
}
