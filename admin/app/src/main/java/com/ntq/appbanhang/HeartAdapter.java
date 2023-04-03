package com.ntq.appbanhang;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class HeartAdapter extends BaseAdapter {
    Context context;
    ArrayList<Heart> heartArrayList;

    public HeartAdapter(Context context, ArrayList<Heart> heartArrayList) {
        this.context = context;
        this.heartArrayList = heartArrayList;
    }

    @Override
    public int getCount() {
        return heartArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return heartArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder {
        public TextView txtTenHeart, txtGiaHeart, txtGiaSaleHeart;
        public ImageView imgHinhHeart, imgStar1, imgStar2, imgStar3, imgStar4, imgStar5;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.dong_heart, null);
            viewHolder.txtTenHeart = view.findViewById(R.id.txtNameSP);
            viewHolder.txtGiaHeart = view.findViewById(R.id.txtPrice);
            viewHolder.txtGiaSaleHeart = view.findViewById(R.id.txtPriceSale);
            viewHolder.imgHinhHeart = view.findViewById(R.id.imgHinh);
            viewHolder.imgStar1 = view.findViewById(R.id.star1);
            viewHolder.imgStar2 = view.findViewById(R.id.star2);
            viewHolder.imgStar3 = view.findViewById(R.id.star3);
            viewHolder.imgStar4 = view.findViewById(R.id.star4);
            viewHolder.imgStar5 = view.findViewById(R.id.star5);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Heart heart = (Heart) getItem(i);
        int ID =0;
        ID = heart.getID();
        viewHolder.txtTenHeart.setText(heart.getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaHeart.setText(decimalFormat.format(heart.getGiaSP()) + "Đ");
        viewHolder.txtGiaSaleHeart.setText(decimalFormat.format(heart.getGiaSPSale()) + "Đ");
        viewHolder.txtGiaSaleHeart.setPaintFlags(viewHolder.txtGiaSaleHeart.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Glide.with(context).load(heart.getHinhAnhSP()).placeholder(R.drawable.account).
                error(R.drawable.cart)
                .into(viewHolder.imgHinhHeart);
        Glide.with(context).load(heart.getStar1()).placeholder(R.drawable.account).
                error(R.drawable.cart)
                .into(viewHolder.imgStar1);
        Glide.with(context).load(heart.getStar2()).placeholder(R.drawable.account).
                error(R.drawable.cart)
                .into(viewHolder.imgStar2);
        Glide.with(context).load(heart.getStar3()).placeholder(R.drawable.account).
                error(R.drawable.cart)
                .into(viewHolder.imgStar3);
        Glide.with(context).load(heart.getStar4()).placeholder(R.drawable.account).
                error(R.drawable.cart)
                .into(viewHolder.imgStar4);
        Glide.with(context).load(heart.getStar5()).placeholder(R.drawable.account).
                error(R.drawable.cart)
                .into(viewHolder.imgStar5);



        return view;
    }
}
