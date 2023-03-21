package com.example.sharedpreferencess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sharedpreferencess.Preferences;

public class LoginActivity extends AppCompatActivity {

    private EditText mViewUser, mViewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        Menginisialisasi Variable dengan Form User dan Form Password dari Layout LoginActivity
        mViewUser = findViewById(R.id.et_emailSignin);
        mViewPassword = findViewById(R.id.et_passwordSignin);
//        Menjalankan Method razia() jika tombol Signin dikeyboard di sentuh
        mViewPassword.setOnEditorActionListener((v, actionId, event)-> {
            if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL){
                razia();
                return true;
            }
            return false;
        });
//        Menjalankan Method razia() jika merasakan tombol SignIn disentuh
        findViewById(R.id.button_signinSignin).setOnClickListener((v) -> {
            razia();
        });
//        Ke RegisterActivity jika merasakan tombol Signup disentuh
        findViewById(R.id.button_signupSignin).setOnClickListener((v) -> {
            startActivity(new Intent(getBaseContext(), RegisterActivity.class));
        });
    }
//    Ke MainActivity jika data status Login dari Data Preferences bernilai true
    @Override
    protected void onStart(){
        super.onStart();
        if(Preferences.getLoggedInStatus(getBaseContext())){
            startActivity(new Intent(getBaseContext(), MainActivity.class));
            finish();
        }
    }
//    Men-Check inputan User dan Password dan Memberikan akses ke MainActivity
    private void razia(){
//        Mereset semua Error dan fokus menjadi default
        mViewUser.setError(null);
        mViewPassword.setError(null);
        View fokus = null;
        boolean cancel =false;
//        Mengambil text dair form User dan form Password dengan variable baru bertipe String
        String user = mViewUser.getText().toString();
        String password = mViewPassword.getText().toString();
//        Jika form user kosong atau tidak memenuhi kriteria di Method cekUser() maka, Set error
//        di Form User dengan menset variable fokus dan error di Viewnya juga cancel menjadi true
        if(TextUtils.isEmpty(user)){
            mViewUser.setError("This Field is required");
            fokus = mViewUser;
            cancel = true;
        }else if(!cekUser(user)){
            mViewUser.setError("This Username Not Found");
            fokus = mViewUser;
            cancel = true;
        }
//        Sama syarat percabangannya dengan User seperti di atas. Bedanya ini untuk Form Password
        if(TextUtils.isEmpty(password)){
            mViewPassword.setError("This Field is Required");
            fokus = mViewPassword;
            cancel = true;
        }else if(!cekPassword(password)){
            mViewPassword.setError("This Password Incorrect");
            fokus = mViewPassword;
            cancel = true;
        }
//        Jika cancel true, variable fokus mendapatkan fokus
        if(cancel) fokus.requestFocus();
        else masuk();
    }

//    Menuju ke MainActivity dan Set User dan Status sedang Login, di Preferences
    private void masuk(){
        Preferences.setLoggedInUser(getBaseContext(), mViewUser.getText().toString());
        Preferences.setLoggedInStatus(getBaseContext(), true);
        startActivity(new Intent(getBaseContext(), MainActivity.class));
        finish();
    }
//    memeriksa apakah suatu nilai "user" sama dengan nilai "username" yang tersimpan di dalam SharedPreferences.
//    Jika nilai "username" yang tersimpan sama dengan nilai "user" yang diberikan, maka metode akan mengembalikan nilai true.
//    Jika tidak, maka metode akan mengembalikan nilai false.
    private Boolean cekUser(String user){
        SharedPreferences sp = getSharedPreferences("daftar", Context.MODE_PRIVATE);
        if(sp.getString("username", "").equals(user)){
            return true;
        }
        return false;
    }
//    memeriksa apakah suatu nilai "password" sama dengan nilai "pass" yang tersimpan di dalam SharedPreferences.
//    Jika nilai "pass" yang tersimpan sama dengan nilai "password" yang diberikan, maka metode akan mengembalikan nilai true.
//    Jika tidak, maka metode akan mengembalikan nilai false.
    private Boolean cekPassword(String password){
        SharedPreferences sp = getSharedPreferences("daftar", Context.MODE_PRIVATE);
        if(sp.getString("pass", "").equals(password)){
            return true;
        }
        return false;
    }


}
