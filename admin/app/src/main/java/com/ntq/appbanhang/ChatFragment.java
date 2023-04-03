package com.ntq.appbanhang;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ChatFragment extends Fragment {
    RecyclerView recyclerViewChat;
    ImageView imgSend;
    EditText edtMess;
    FirebaseFirestore db;
    List<ChatMessage> list;
    ChatAdapter adapter;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chat_fragment, container, false);
        db=FirebaseFirestore.getInstance();
        recyclerViewChat=view.findViewById(R.id.rcchat);
        imgSend=view.findViewById(R.id.imgchat);
        edtMess=view.findViewById(R.id.edtInputTex);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this.getContext());
        recyclerViewChat.setHasFixedSize(true);
        recyclerViewChat.setLayoutManager(layoutManager);
        list=new ArrayList<>();
        adapter= new ChatAdapter(this.getContext(),list,Server.firebaseUser.getEmail());
        recyclerViewChat.setAdapter(adapter);
        setControl();
        getMesss();
        return view;
    }

    private void setControl() {
        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessToFire();
            }
        });
    }

    private void sendMessToFire() {
        String messs=edtMess.getText().toString().trim();
        if(TextUtils.isEmpty(messs)){

        }else {
            HashMap<String,Object> message= new HashMap<>();
            message.put(Server.ID_SEND, String.valueOf(Server.firebaseUser.getEmail()).trim());
            message.put(Server.ID_RECEIVE, Server.ID_NHAN.trim());
            message.put(Server.MESS, messs);
            Date date=new Date();
            message.put(Server.DATE, date);
            db.collection(Server.PATH_CHAT).document(date.toString()).set(message);
            edtMess.setText("");
        }
    }
    private  String formatdate(Date date){
        return new SimpleDateFormat("MMMM dd, yyyy- hh:mm a", Locale.getDefault()).format(date);
    }
    private void getMesss() {
        db.collection(Server.PATH_CHAT)
                .whereEqualTo(Server.ID_SEND,Server.ID_NHAN)
                .whereEqualTo(Server.ID_RECEIVE, Server.firebaseUser.getEmail())
                //.orderBy(Server.DATE)
                .addSnapshotListener(evenListener);
        db.collection(Server.PATH_CHAT)
                .whereEqualTo(Server.ID_SEND,Server.firebaseUser.getEmail())
                .whereEqualTo(Server.ID_RECEIVE, Server.ID_NHAN)
                // .orderBy(Server.DATE)
                .addSnapshotListener(evenListener);
    }
    private final EventListener<QuerySnapshot> evenListener= ((value, error) -> {
        if(error!=null){
            return;
        }
        if(value!=null){
            for(DocumentChange item:value.getDocumentChanges()){
                if(item.getType()==DocumentChange.Type.ADDED){
                    ChatMessage chatitem = new ChatMessage();
                    chatitem.setIdsend(item.getDocument().get(Server.ID_SEND).toString());
                    chatitem.setReceiveid(item.getDocument().get(Server.ID_RECEIVE).toString());
                    chatitem.setMessage(item.getDocument().get(Server.MESS).toString());
                    chatitem.setDatetime(formatdate(item.getDocument().getDate(Server.DATE)));

                    list.add(chatitem);

                }
            }
            Collections.sort(list,(obj1, obj2)->obj1.getDatetime().compareTo((obj2.getDatetime())));
            int count=list.size();
            if(count==0){
                adapter.notifyDataSetChanged();
            }else{
                adapter.notifyItemRangeInserted(list.size(), list.size());
                recyclerViewChat.smoothScrollToPosition(list.size()-1);
            }
        }
    });
//    private void getMesss() {
//        db.collection(Server.PATH_CHAT)
//                .whereEqualTo(Server.ID_SEND,Server.firebaseUser.getEmail().trim())
//                .whereEqualTo(Server.ID_RECEIVE,Server.ID_NHAN.trim())
//                .orderBy(Server.DATE)
//                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()){
//                    list.clear();
//                    QuerySnapshot snapshot=task.getResult();
//                    if(snapshot!=null) {
//                        for (QueryDocumentSnapshot item : snapshot) {
//                            ChatMessage chatitem = new ChatMessage();
//                            chatitem.setIdsend(item.get(Server.ID_SEND).toString());
//                            chatitem.setReceiveid(item.get(Server.ID_RECEIVE).toString());
//                            chatitem.setMessage(item.get(Server.MESS).toString());
//                            chatitem.setDatetime(formatdate(item.getDate(Server.DATE)));
//
//                            list.add(chatitem);
//                        }
//                        adapter.notifyDataSetChanged();
//                        adapter.notifyItemRangeInserted(list.size(), list.size());
//                        recyclerViewChat.smoothScrollToPosition(list.size() - 1);
//                    }
//
//                }
//            }
//        });
//
//
//        db.collection(Server.PATH_CHAT)
//                .whereEqualTo(Server.ID_SEND,Server.ID_NHAN)
//                .whereEqualTo(Server.ID_RECEIVE,Server.firebaseUser.getEmail())
//                .orderBy(Server.DATE)
//                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()){
//                            list.clear();
//                            QuerySnapshot snapshot=task.getResult();
//                            if(snapshot!=null) {
//                                for (QueryDocumentSnapshot item : snapshot) {
//                                    ChatMessage chatitem = new ChatMessage();
//                                    chatitem.setIdsend(item.get(Server.ID_SEND).toString());
//                                    chatitem.setReceiveid(item.get(Server.ID_RECEIVE).toString());
//                                    chatitem.setMessage(item.get(Server.MESS).toString());
//                                    chatitem.setDatetime(formatdate(item.getDate(Server.DATE)));
//
//                                    list.add(chatitem);
//                                }
//                                adapter.notifyDataSetChanged();
//                                adapter.notifyItemRangeInserted(list.size(), list.size());
//                                recyclerViewChat.smoothScrollToPosition(list.size() - 1);
//                            }
//
//                        }
//                    }
//                });
  //  }
}
