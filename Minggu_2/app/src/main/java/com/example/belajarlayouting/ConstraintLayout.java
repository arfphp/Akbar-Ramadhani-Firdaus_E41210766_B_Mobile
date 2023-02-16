package com.example.belajarlayouting;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.cardview.widget.CardView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textfield.TextInputEditText;
public class ConstraintLayout extends AppCompatActivity{
    CardView cardView, cardViews2;
    TextView textView;
    ImageView imageView;
    TextInputLayout textInputLayout2, textInputLayout3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_constraint);

        cardView = findViewById(R.id.cardView);
        cardViews2 = findViewById(R.id.cardViews2);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        textInputLayout2 = findViewById(R.id.textInputLayout2);
        textInputLayout3 = findViewById(R.id.textInputLayout3);
    }
}
