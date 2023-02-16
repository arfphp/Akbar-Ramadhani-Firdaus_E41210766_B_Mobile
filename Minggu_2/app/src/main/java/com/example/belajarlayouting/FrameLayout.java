package com.example.belajarlayouting;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Button;
public class FrameLayout extends AppCompatActivity{
    ImageView imageView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_layout_frame);

        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.btn1);
    }
}
