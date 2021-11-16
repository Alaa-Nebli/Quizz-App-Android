package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Congrats_Page extends AppCompatActivity {
    TextView name ;
    TextView message ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats_page);

        name = findViewById(R.id.userName) ;
        message = findViewById(R.id.message) ;

        name.setText(getIntent().getStringExtra("name")) ;
        message.setText("Your Score Is : " +getIntent().getStringExtra("score")) ;
    }
}