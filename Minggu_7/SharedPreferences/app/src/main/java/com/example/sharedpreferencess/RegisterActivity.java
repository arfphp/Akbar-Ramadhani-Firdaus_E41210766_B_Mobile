package com.example.sharedpreferencess;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

public class


RegisterActivity extends AppCompatActivity {

    EditText username, pass, pass2;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.et_emailSignup);
        pass = findViewById(R.id.et_passwordSignup);
        pass2 = findViewById(R.id.et_passwordSignup2);
        btn = findViewById(R.id.button_SignupSignup);
        btn.setOnClickListener(v -> {
//            Memeriksa apakah nilai dari EditText "pass" sama dengan nilai dari EditText "pass2".
            if(pass.getText().toString().equals(pass2.getText().toString())){
//                berfungsi untuk membuat sebuah objek Shared Preferences dengan nama "daftar".
//                Context.MODE_PRIVATE adalah mode pengaksesan file Shared Preferences,
//                dimana hanya aplikasi yang sama yang dapat mengakses data yang disimpan dalam file ini.
                SharedPreferences sp = getSharedPreferences("daftar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
//                Menyimpan nilai dari EditText "username" ke dalam objek Shared Preferences dengan key "username".
                editor.putString("username", username.getText().toString());
//                Menyimpan nilai dari EditText "pass" ke dalam objek Shared Preferences dengan key "pass".
                editor.putString("pass", pass.getText().toString());
//                Menyimpan perubahan yang dilakukan pada objek Shared Preferences.
                editor.commit();
//                Menyimpan perubahan yang dilakukan pada objek Shared Preferences secara asinkron.
                editor.apply();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
