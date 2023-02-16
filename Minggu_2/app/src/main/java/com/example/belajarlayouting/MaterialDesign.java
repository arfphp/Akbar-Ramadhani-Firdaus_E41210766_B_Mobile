package com.example.belajarlayouting;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MaterialDesign extends AppCompatActivity{
    Button text_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_materialdesign);

        text_button = findViewById(R.id.text_button);
    }
}
