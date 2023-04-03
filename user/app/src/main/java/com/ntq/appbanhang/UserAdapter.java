package com.ntq.appbanhang;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{
    private Context context;
    private List<User> listUser;

    public UserAdapter(Context context, List<User> listUser) {
        this.context = context;
        this.listUser = listUser;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_user,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        User user=listUser.get(position);
        holder.txtEmail.setText(user.getEmail()+"");
        holder.setItemOnclickListener(new ItemOnclickListener() {
            @Override
            public void onClick(View view, int pos, boolean isLongClick) {
                if(!isLongClick){
                    Intent intent= new Intent(context, MainActivity1.class);
                    intent.putExtra("email", user.getEmail());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listUser!=null){
            return listUser.size();
        }
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imgUser;
        public TextView txtEmail;
        ItemOnclickListener itemOnclickListener;

        public void setItemOnclickListener(ItemOnclickListener itemOnclickListener) {
            this.itemOnclickListener = itemOnclickListener;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUser=itemView.findViewById(R.id.imguser);
            txtEmail=itemView.findViewById(R.id.email);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemOnclickListener.onClick(view,getAdapterPosition(),false);
        }
    }
}
