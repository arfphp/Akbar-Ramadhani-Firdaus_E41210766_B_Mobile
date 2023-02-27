package com.example.testfragment;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    Button btnPertama, btnKedua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPertama = (Button) findViewById(R.id.fragment1);
        btnKedua = (Button) findViewById(R.id.fragment2);
        btnPertama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new FragmentPertama());
            }
        });
        btnKedua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new FragmentKedua());
            }
        });
    }

    private void loadFragment(Fragment fragment){
        FragmentManager fm = getFragmentManager();
        //membuat fragmentTransaction untuk memulai dan menggantikan halaman yg sebelumnya
        FragmentTransaction ft = fm.beginTransaction();
        //menggantikan frameLayout dengan halaman yg baru
        ft.replace(R.id.frameLayout, fragment);
        //untuk menyimpan perubahan
        ft.commit();
    }
}