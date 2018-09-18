package com.example.predator.nulpandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void lab1(View view) {
        Intent openLaba1 = new Intent(MainActivity.this, Laba_1.class);
        startActivity(openLaba1);
    }

    public void lab2(View view) {
        Intent openLaba2 = new Intent(MainActivity.this, Laba_2.class);
        startActivity(openLaba2);
    }
}
