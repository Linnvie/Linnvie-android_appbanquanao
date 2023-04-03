package com.ntq.appbanhang;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private Context context;
    private List<ChatMessage> listChat;
    private String sendid;

    private static final int TYPE_SEND=1;
    private static final int TYPE_RECEIVE=2;

    public ChatAdapter(Context context, List<ChatMessage> listChat, String sendid) {
        this.context = context;
        this.listChat = listChat;
        this.sendid = sendid;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        Log.d("aaa",viewType+"");
//        CheckConnection.ShowToast(this.context,viewType+"");
        if(viewType==TYPE_SEND){

            view= LayoutInflater.from(context).inflate(R.layout.item_chat_client,parent,false);
            return  new ViewHolder(view);
        }else{
            view= LayoutInflater.from(context).inflate(R.layout.item_chat_shop,parent,false);
            return  new ViewHolder(view);
        }

    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtChat;
        public TextView txtTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtChat=itemView.findViewById(R.id.txtchat);
            txtTime=itemView.findViewById(R.id.txtTime);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatMessage chatMessage= listChat.get(position);
        if(chatMessage==null){
            return;
        }
        holder.txtChat.setText(chatMessage.getMessage());
        //holder.txtTime.setText(new Timer().toString());
        holder.txtTime.setText(chatMessage.getDatetime());
    }

    @Override
    public int getItemCount() {
        if(listChat!=null){
            return listChat.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (listChat.get(position).getIdsend().equals(Server.firebaseUser.getEmail())){
            return TYPE_SEND;
        }else {
            return TYPE_RECEIVE;
        }
    }
}

