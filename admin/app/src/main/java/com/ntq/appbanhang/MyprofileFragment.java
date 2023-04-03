package com.ntq.appbanhang;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MyprofileFragment extends Fragment {
    private View qView;
    private ImageView imgAvatar;
    private EditText edtName, edtEmail;
    private Button btnUpdatePro;
    private Uri uri;
    private MainActivityDanhMucSP  mainActivityDanhMucSP;
    private ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        qView = inflater.inflate(R.layout.my_fragment_profile,container, false);
        SetControl();
        mainActivityDanhMucSP = (MainActivityDanhMucSP) getActivity();
        SetUserInformation();
        ItemListener();
        progressDialog = new ProgressDialog(getActivity());
        return qView;
    }

    private void ItemListener() {
//        imgAvatar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onClickRequestPermission();
//            }
//        });
        btnUpdatePro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickUpdateProfile();
            }
        });
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    private void onClickUpdateProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null) {
            return;
        }
        progressDialog.show();
        String name = edtName.getText().toString().trim();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .setPhotoUri(uri)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Update profile thành công", Toast.LENGTH_SHORT).show();
                            mainActivityDanhMucSP.showUserInformation();
                        }
                    }
                });
    }

    // hàm cho phép update ảnh từ Gallery
//    private void onClickRequestPermission() {
//        if(mainActivityDanhMucSP == null) {
//            return;
//        }
//        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
//            mainActivityDanhMucSP.openGallery();
//            return;
//        }
//        if(getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//            mainActivityDanhMucSP.openGallery();
//        }
//        else {
//            String [] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
//            getActivity().requestPermissions(permission, MY_REQUEST_CODE);
//        }
//    }

    private void SetUserInformation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null) {
            return;
        }
        edtName.setText(user.getDisplayName());
        edtEmail.setText(user.getEmail());
        Glide.with(getActivity()).load(user.getPhotoUrl()).error(R.drawable.user_circle).into(imgAvatar);
    }

    private void SetControl() {
        imgAvatar = qView.findViewById(R.id.imgAvatar);
        edtName = qView.findViewById(R.id.edtFullname);
        edtEmail = qView.findViewById(R.id.edtEmail);
        btnUpdatePro = qView.findViewById(R.id.btn_update_profile);
    }

//    public void SetBitmapImageView(Bitmap bitmap) {
//        imgAvatar.setImageBitmap(bitmap);
//    }
}
