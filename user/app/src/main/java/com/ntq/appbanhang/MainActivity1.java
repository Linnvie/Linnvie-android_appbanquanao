package com.ntq.appbanhang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

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

public class MainActivity1 extends AppCompatActivity {

    RecyclerView recyclerViewChat;
    ImageView imgSend;
    EditText edtMess;
    FirebaseFirestore db;
    List<ChatMessage> list;
    ChatAdapter adapter;
    Toolbar back;
    String emailUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=FirebaseFirestore.getInstance();
        recyclerViewChat=findViewById(R.id.rcchat);
        imgSend=findViewById(R.id.imgchat);
        back= findViewById(R.id.backlichsu);
        edtMess=findViewById(R.id.edtInputTex);

        emailUser=getIntent().getStringExtra("email");

        setSupportActionBar(back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(emailUser);

        back.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerViewChat.setHasFixedSize(true);
        recyclerViewChat.setLayoutManager(layoutManager);
        list=new ArrayList<>();
        adapter= new ChatAdapter(getApplicationContext(),list,Server.ID_GUI);
        recyclerViewChat.setAdapter(adapter);
        setControl();
        getMesss();

    }

    private void setControl() {
        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessToFire();
            //    getMesss();
            }
        });
    }


    private void sendMessToFire() {
        String messs=edtMess.getText().toString().trim();
        if(TextUtils.isEmpty(messs)){

        }else {
            HashMap<String,Object> message= new HashMap<>();
            message.put(Server.ID_SEND, Server.ID_GUI.trim());
            message.put(Server.ID_RECEIVE, emailUser.trim());
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
                .whereEqualTo(Server.ID_SEND,Server.ID_GUI)
                .whereEqualTo(Server.ID_RECEIVE, emailUser)
                //.orderBy(Server.DATE)
                .addSnapshotListener(evenListener);
        db.collection(Server.PATH_CHAT)
                .whereEqualTo(Server.ID_SEND,emailUser)
                .whereEqualTo(Server.ID_RECEIVE, Server.ID_GUI)
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
                    chatitem.setMessage(item.getDocument().get(Server.MESS).toString()+"");
                    chatitem.setDatetime(formatdate(item.getDocument().getDate(Server.DATE)));

                    list.add(chatitem);

                }
            }
            Collections.sort(list,(obj1,obj2)->obj1.getDatetime().compareTo((obj2.getDatetime())));
            int count=list.size();
            if(count==0){
                adapter.notifyDataSetChanged();
            }else{
                adapter.notifyItemRangeInserted(list.size(), list.size());
                recyclerViewChat.smoothScrollToPosition(list.size()-1);
            }
        }
    });
}