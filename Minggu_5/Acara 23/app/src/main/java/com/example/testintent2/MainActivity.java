package com.example.testintent2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    EditText name;
    Button btnSend;
    private String KEY_NAME = "NAMA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.edt_nama);
        btnSend = (Button) findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String nama = name.getText().toString();
                    if (nama != ""){
                        Intent i = new Intent(MainActivity.this, ActivityKedua.class);
                        i.putExtra(KEY_NAME, nama);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplication(), "YOU NEED TO FILL YOUR NAME",Toast.LENGTH_SHORT);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplication(), "ERROR, TRY AGAIN !",Toast.LENGTH_SHORT);
                }
            }
        });
    }
}