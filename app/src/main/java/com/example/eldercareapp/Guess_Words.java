package com.example.eldercareapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class Guess_Words extends AppCompatActivity {

    /*-------------code snippet written by me----------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess__words);
    }
    public void EnterGame(View view){
        String name=getIntent().getExtras().getString("name");

        Intent i = new Intent(this,GuessWords_mainpg.class);
        i.putExtra("name",name);
        startActivity(i);
    }


    public void Close(View view){
        finish();
    }
}