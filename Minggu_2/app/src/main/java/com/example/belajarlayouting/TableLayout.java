package com.example.belajarlayouting;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
public class TableLayout extends AppCompatActivity{
    TextView status;
    EditText screenName, password;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_table);

        status = findViewById(R.id.status);
        screenName = findViewById(R.id.screenName);
        password = findViewById(R.id.password);
        save = findViewById(R.id.save);
    }
}
