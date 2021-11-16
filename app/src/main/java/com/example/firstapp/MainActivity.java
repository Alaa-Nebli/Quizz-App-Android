package com.example.firstapp;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText name ;
    android.widget.Button Button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name) ; // reference to text editor
        Button = findViewById(R.id.button) ; // reference to Button
        Button.setEnabled(false); // set the button inactive

        name.addTextChangedListener(new TextWatcher() { //watch the text field

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {   }

            @Override
            public void afterTextChanged(Editable s) {
                Button.setEnabled(true); // set the button active
            }
        });

        Button.setOnClickListener(new View.OnClickListener() { // button click listener
            @Override
            public void onClick(View v) { // onclick event
                Intent i = new Intent(getApplicationContext(),QuizzActivity.class);
                i.putExtra("name" ,name.getText().toString()) ;
                startActivity(i);

            }
        });


    }





    }