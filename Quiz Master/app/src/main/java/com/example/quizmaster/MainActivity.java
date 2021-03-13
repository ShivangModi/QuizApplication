package com.example.quizmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.example.quizmaster.R.id.start;

public class MainActivity extends AppCompatActivity {

    private Button startBtn;
    private Button loginBtn;
    private Button registerBtn;
    private Button bookmarkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.start);
        loginBtn = findViewById(R.id.btn_login);
        registerBtn = findViewById(R.id.btn_register);
        bookmarkBtn = findViewById(R.id.bookmark);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent show = new Intent(MainActivity.this, CategoriesActivity.class);
                startActivity(show);


            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent show = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(show);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });

        bookmarkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),BookmarkActivity.class));
            }
        });
    }
}