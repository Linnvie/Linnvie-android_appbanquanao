package com.ntq.appbanhang;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class Server {
   //public static String localhost = "192.168.1.13:8080";
    public static String localhost = "192.168.1.15:8080";

    public  static FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
    public static String ID_GUI="shopclothing.@gmail.com";
    public static final String ID_SEND="idsend";
    public static final String ID_RECEIVE="received";
    public static final String MESS="message";
    public static final String DATE="datetime";
    public static final String PATH_CHAT="chat";
    public static final String PATH_USER="user";


}
