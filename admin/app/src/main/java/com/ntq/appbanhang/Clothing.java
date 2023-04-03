package com.ntq.appbanhang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Clothing extends AppCompatActivity {
    Button btnLogin, btnRegister;
    ProgressDialog progressDialog;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing);

        setControl();
        setLogin();
        setRegister();

        NextLayout();
    }

    private void NextLayout() {
        //kiểm tra xem người dùng đã login chưa
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser == null) {
            OpenDialogLogin(Gravity.CENTER);
        }
        else{
            //Đã login
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void setControl() {
        btnLogin = findViewById(R.id.login);
        btnRegister = findViewById(R.id.register);
        progressDialog = new ProgressDialog(this);
    }

    private void setLogin() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenDialogLogin(Gravity.CENTER);

            }
        });
    }

    public void OpenDialogLogin(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_login);

        Window window = dialog.getWindow();
        if(window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Xác định vị trí của dialog
        WindowManager.LayoutParams windowAtribute = window.getAttributes();
        windowAtribute.gravity = gravity;
        window.setAttributes(windowAtribute);

        //khi click ra ngoài diaolog sẽ tắt dialog
        if(Gravity.CENTER == gravity) {
            dialog.setCancelable(true);
        }
        else{
            dialog.setCancelable(false);
        }

        EditText edtMailSDT = dialog.findViewById(R.id.edtMailSDT);
        EditText edtPass = dialog.findViewById(R.id.edtPassword);
        Button btnLoginDialog = dialog.findViewById(R.id.btnLoginDialog);
        TextView txtRegis = dialog.findViewById(R.id.txtRegister);
        TextView txtForgot = dialog.findViewById(R.id.txtForgot);
        btnLoginDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                String email = edtMailSDT.getText().toString().trim();
                String password = edtPass.getText().toString().trim();
                progressDialog.show();
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Clothing.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressDialog.dismiss();
                                    Intent intent = new Intent(Clothing.this, MainActivity.class);
                                    startActivity(intent);
                                    finishAffinity();
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(Clothing.this, "Đăng nhập không thành công",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
//                dialog.dismiss();

            }
        });

         //========================= Quên mật khẩu =========================
        txtForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtMailSDT.getText().toString();
                onClickForgotPassWord(email);
            }
        });

        dialog.show();

        txtRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenDiaRegister(Gravity.CENTER);
                dialog.dismiss();
            }
        });
    }

    private void onClickForgotPassWord(String email) {
        progressDialog.show();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(Clothing.this, "Đã gửi mã. Vui lòng kiểm tra Email", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void setRegister() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenDiaRegister(Gravity.CENTER);
            }

        });
    }

    public void OpenDiaRegister(int gravity) {
        final Dialog dialog = new Dialog(Clothing.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_register);

        Window window = dialog.getWindow();
        if(window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Xác định vị trí của dialog
        WindowManager.LayoutParams windowAtribute = window.getAttributes();
        windowAtribute.gravity = gravity;
        window.setAttributes(windowAtribute);

        //khi click ra ngoài diaolog sẽ tắt dialog
        if(Gravity.CENTER == gravity) {
            dialog.setCancelable(true);
        }
        else{
            dialog.setCancelable(false);
        }

        Button btnRegisDialog = dialog.findViewById(R.id.btnRegis);
        EditText edtEmailSDT = dialog.findViewById(R.id.edtEmailSDT);
        EditText edtPass = dialog.findViewById(R.id.edtPasswordRe);
        EditText edtName = dialog.findViewById(R.id.edtTen);
        btnRegisDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edtEmailSDT.getText().toString().trim();
                String password = edtPass.getText().toString().trim();
                String name = edtName.getText().toString().trim();
                FirebaseAuth auth = FirebaseAuth.getInstance();

                progressDialog.show();
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Clothing.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressDialog.dismiss();
                                    insertUser();
                                    Intent intent = new Intent(Clothing.this, MainActivity.class);
//                                    setContentView(R.layout.activity_main);
                                    Toast.makeText(Clothing.this, "yes:"+email, Toast.LENGTH_SHORT).show();

                                    startActivity(intent);
                                    finishAffinity();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Clothing.this, "Đăng ký không thành công",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                dialog.dismiss();

            }
        });
        dialog.show();

        TextView txtLogin = dialog.findViewById(R.id.txtLogin);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenDialogLogin(Gravity.CENTER);
                dialog.dismiss();
            }
        });
    }

    private void insertUser() {
        db=FirebaseFirestore.getInstance();
        HashMap<String,Object> user= new HashMap<>();
        user.put("email", Server.firebaseUser.getEmail());
        db.collection("user").document(String.valueOf(Server.firebaseUser.getEmail())).set(user);
    }
}