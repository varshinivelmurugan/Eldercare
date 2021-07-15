//internet code refered for writting this
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


public class register extends AppCompatActivity {

    EditText username, password, mobno;
    Button register, login;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        username = (EditText) findViewById(R.id.userName);
        mobno = (EditText) findViewById(R.id.mobno);
        password = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.btnreg);
        login = (Button) findViewById(R.id.btnlogin);
        db = new DBHelper(this);
        //  sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        //user = sharedpreferences.getString(USER_KEY, null);
        //pass = sharedpreferences.getString(PASSWORD_KEY, null);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String mob = mobno.getText().toString();

                String pass = password.getText().toString();
                if (user.equals("") || pass.equals("") || mob.equals("")) {
                    Toast.makeText(register.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean validUser = db.checkusername(user);

                    if (validUser == false) {
                        long mobno = Long.parseLong(mob);
                        Boolean regResult = db.insertData(user, mobno, pass);
                        if (regResult == true) {
                            Toast.makeText(register.this, "Registration successful", Toast.LENGTH_SHORT).show();
                            //                  SharedPreferences.Editor editor = sharedpreferences.edit();
                            //                editor.putString(USER_KEY, username.getText().toString());
                            //              editor.putString(PASSWORD_KEY, password.getText().toString());
                            //            editor.apply();
                            Intent intent = new Intent(getApplicationContext(), login.class);
                            startActivity(intent);
                            //          finish();
                        } else {
                            Toast.makeText(register.this, "Not Registered", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(register.this, "User already exists \n Please Sign In", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
            }
        });
    }
   /* protected void onStart() {
        super.onStart();
        if (user!= null && password != null) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }

    }*/
}
