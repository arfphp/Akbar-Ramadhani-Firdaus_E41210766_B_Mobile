package com.example.testintent2;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityKedua extends AppCompatActivity {
    TextView txtHello;
    private String nama;
    private String KEY_NAME = "NAMA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kedua);
        txtHello = (TextView) findViewById(R.id.txtHello);
        Bundle extras = getIntent().getExtras();
        nama = extras.getString(KEY_NAME);
        txtHello.setText("Hello, " + nama + " !");
    }
}
