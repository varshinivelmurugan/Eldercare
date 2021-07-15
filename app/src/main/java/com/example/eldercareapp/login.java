package com.example.eldercareapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

//internet code refered for writting this
public class login extends AppCompatActivity {

    EditText username, password;
    Button login;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.usernamelogin);
        password = (EditText) findViewById(R.id.passwordlogin);
        login = (Button) findViewById(R.id.btnlogin);


        db = new DBHelper(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(login.this, "Please enter the credentials", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean result = db.checkusernamePassword(user, pass);
                    if (result == true) {
                        long mobno = db.getmobno(user);
                        System.out.println("ph" + mobno);
                        //float word=db.getWordScore(user);
                        //float color=db.getColorScore(user);
                        //float card=db.getCardScore(user);
                        Intent intent = new Intent(login.this, MainActivity.class);
                        intent.putExtra("name", user);
                        intent.putExtra("mobno", mobno);
                        //intent.putExtra("word",word);
                        // intent.putExtra("color",color);
                        // intent.putExtra("card",card);

                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        System.out.println(user);
                        System.out.println("name");
                        startActivity(intent);
//finish();

                    } else {
                        Toast.makeText(login.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}
