package com.ntq.appbanhang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class LoaiSanPhamAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private int layout;
    private List<LoaiSanPham> data;
    private List<LoaiSanPham> data_old;

    public LoaiSanPhamAdapter(Context context, int layout, List<LoaiSanPham> data) {
        this.context = context;
        this.layout = layout;
        this.data = data;
        this.data_old=data;
    }

    @Override
    public int getCount() {
        if(data!=null){
        return data.size();}
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {

        return data.get(i).getId();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence){
                String key= charSequence.toString();
                if(key.isEmpty()){
                    data=data_old;
                }else{
                    List<LoaiSanPham> list= new ArrayList<>();
                    for(LoaiSanPham item: data_old){
                        if(item.getTenLoai().toLowerCase().contains(key.toLowerCase())){
                            list.add(item);
                        }
                    }
                    data=list;
                }
                FilterResults filterResults= new FilterResults();
                filterResults.values=data;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                data= (List<LoaiSanPham>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder{
        ImageView imgHinhLoai;
        TextView txtLoai;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.imgHinhLoai = view.findViewById(R.id.imgLoai);
            holder.txtLoai = view.findViewById(R.id.txtLoai);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        LoaiSanPham loaiSanPham = data.get(i) ;
        holder.txtLoai.setText(loaiSanPham.getTenLoai());
        Glide.with(context).load(loaiSanPham.getHinhLoai())
                .placeholder(R.drawable.loading_img)
                .error(R.drawable.no_image)
                .into(holder.imgHinhLoai);
        return view;
    }
}
