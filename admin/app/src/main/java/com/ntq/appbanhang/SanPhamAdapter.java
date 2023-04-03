package com.ntq.appbanhang;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ItemHolder> implements Filterable {

    Context context;
    int layout;
    ArrayList<SanPham> arrayListSanPham;
    ArrayList<SanPham> arrayListSanPhamSearch;
    int count = 0;

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

    public SanPhamAdapter(Context context, ArrayList<SanPham> listProduct, int layout) {
        this.context = context;
        this.arrayListSanPham = listProduct;
        this.arrayListSanPhamSearch = listProduct;
        this.layout=layout;
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout,null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

        SanPham product = arrayListSanPham.get(position);
        ID = product.getID();
        holder.txtTenSP.setText(product.getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGiaSP.setText(decimalFormat.format(product.getGiaSP()) + "Đ");
        holder.txtPriceSale.setText(decimalFormat.format(product.getGiaSale()) + "Đ");
        holder.txtPriceSale.setPaintFlags(holder.txtPriceSale.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Glide.with(context).load(product.getHinhAnhSP()).placeholder(R.drawable.account)
                .error(R.drawable.cart)
                .into(holder.imgHinhSanPham);
        Glide.with(context).load(product.getStar1()).placeholder(R.drawable.account)
                .error(R.drawable.cart)
                .into(holder.imgStar1);
        Glide.with(context).load(product.getStar2()).placeholder(R.drawable.account)
                .error(R.drawable.cart)
                .into(holder.imgStar2);
        Glide.with(context).load(product.getStar3()).placeholder(R.drawable.account)
                .error(R.drawable.cart)
                .into(holder.imgStar3);
        Glide.with(context).load(product.getStar4()).placeholder(R.drawable.account)
                .error(R.drawable.cart)
                .into(holder.imgStar4);
        Glide.with(context).load(product.getStar5()).placeholder(R.drawable.account)
                .error(R.drawable.cart)
                .into(holder.imgStar5);

        Glide.with(context).load(product.getHeart()).placeholder(R.drawable.account)
                .error(R.drawable.cart)
                .into(holder.imgHear);

        // ====================================== Click Heart =================================
       ClickHeart(holder.imgHear, holder.imgHearEd, position, product.getID(), product.getTenSP(), product.getGiaSP(), product.getGiaSale(), product.getHinhAnhSP(), product.getStar1(), product.getStar2(), product.getStar3(), product.getStar4(), product.getStar5(), product.getHeart());


        holder.setOnclickListener(new ItemOnclickListener() {
            @Override
            public void onClick(View view, int pos, boolean isLongClick) {
                if(!isLongClick) {
                    Intent intent = new Intent(context, ChiTietSP.class);
                    intent.putExtra("chitiet", product);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayListSanPham.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()) {
                    arrayListSanPham = arrayListSanPhamSearch;
                }
                else {
                    ArrayList<SanPham> list = new ArrayList<>();
                    for(SanPham sanPham: arrayListSanPhamSearch) {
                        if (sanPham.getTenSP().toLowerCase().contains(strSearch.toLowerCase())) {
                            list.add(sanPham);
                        }
                    }
                    arrayListSanPham = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = arrayListSanPham;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                arrayListSanPham = (ArrayList<SanPham>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    //
    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imgHinhSanPham;
        private TextView txtTenSP, txtGiaSP, txtMoTaSP;
        private ImageView imgStar1,imgStar2, imgStar3, imgStar4, imgStar5, imgHear, imgHearEd;
        private TextView txtPriceSale, txtSold;
        private ItemOnclickListener onclickListener;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhSanPham = itemView.findViewById(R.id.imgHinh);
            txtTenSP = itemView.findViewById(R.id.txtName);
            txtGiaSP = itemView.findViewById(R.id.txtPrice);
            imgStar1 = itemView.findViewById(R.id.star1);
            imgStar2 = itemView.findViewById(R.id.star2);
            imgStar3 = itemView.findViewById(R.id.star3);
            imgStar4 = itemView.findViewById(R.id.star4);
            imgStar5 = itemView.findViewById(R.id.star5);
            imgHear = itemView.findViewById(R.id.heart);
            txtPriceSale = itemView.findViewById(R.id.txtPriceSale);
            itemView.setOnClickListener(this);
        }

        public void setOnclickListener(ItemOnclickListener onclickListener) {
            this.onclickListener = onclickListener;
        }

        @Override
        public void onClick(View view) {
            onclickListener.onClick(view, getAdapterPosition(), false);
        }


    }

    private void ClickHeart(ImageView imgHeart, ImageView imageViewHeartEd, int position, int ID, String ten, Integer gia, Integer giaSale, String hinhAnh, String star1, String star2, String star3, String star4, String star5, String heart) {
        imgHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count ++;
                if(count % 2 == 0) {
                    imgHeart.setImageResource(R.drawable.heart);
                    imgHeart.setY(5);
                    imgHeart.getLayoutParams().height = 30;
                    imgHeart.getLayoutParams().width = 40;
                    for(int i = 0; i < MainActivity.heartArrayList.size(); i++) {
                        if(MainActivity.heartArrayList.get(i).getID() == ID) {
                            MainActivity.heartArrayList.remove(i);
                        }
                    }
                }
                else if(count % 2 != 0) {
                    imgHeart.setImageResource(R.drawable.heartred);
                    imgHeart.setY(5);
                    imgHeart.getLayoutParams().height = 30;
                    imgHeart.getLayoutParams().width = 40;

                    if(MainActivity.heartArrayList.size()>=0) {
                        boolean exit = false;
                        for(int i = 0; i < MainActivity.heartArrayList.size(); i++) {
                            if(MainActivity.heartArrayList.get(i).getID() == ID) {
                                MainActivity.heartArrayList.remove(i);
                                exit = true;
                             }
                        }
                        if(exit == false) {
                            MainActivity.heartArrayList.add(new Heart(ID, ten, gia, giaSale, hinhAnh, star1, star2, star3, star4, star5, heart));
                        }
                    }
                    else {
                        MainActivity.heartArrayList.add(new Heart(ID, ten, gia, giaSale, hinhAnh, star1, star2, star3, star4, star5, heart));
                    }
                }
            }
        });
    }

}
