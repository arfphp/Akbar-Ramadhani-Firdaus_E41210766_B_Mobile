package com.example.belajarlayouting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void linear(View view) {
        Intent intent = new Intent(MainActivity.this, LinearLayout.class);
        startActivity(intent);
    }
    public void Relative(View view) {
        Intent intent = new Intent(MainActivity.this, RelativeLayout.class);
        startActivity(intent);
    }
    public void Constraint(View view) {
        Intent intent = new Intent(MainActivity.this, ConstraintLayout.class);
        startActivity(intent);
    }
    public void Frame(View view) {
        Intent intent = new Intent(MainActivity.this, FrameLayout.class);
        startActivity(intent);
    }
    public void Table(View view) {
        Intent intent = new Intent(MainActivity.this, TableLayout.class);
        startActivity(intent);
    }
    public void Material(View view) {
        Intent intent = new Intent(MainActivity.this, MaterialDesign.class);
        startActivity(intent);
    }
    public void ScrollV(View view) {
        Intent intent = new Intent(MainActivity.this, ScrollViewLayout.class);
        startActivity(intent);
    }
    public void ScrollVH(View view) {
        Intent intent = new Intent(MainActivity.this, HorizontalScrollViewLayout.class);
        startActivity(intent);
    }

}