package com.example.eldercareapp;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Diet extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton,M,F;
    String Gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);
        M = (RadioButton) findViewById(R.id.M);
        F = (RadioButton) findViewById(R.id.F);
//code written by me with reference
// Get the references to the widgets
        final EditText e1 = (EditText) findViewById(R.id.et1);
        final EditText e2 = (EditText) findViewById(R.id.et2);
        final EditText e3 = (EditText) findViewById(R.id.et3);
        final TextView tv4 = (TextView) findViewById(R.id.tv4);

        findViewById(R.id.ib1).setOnClickListener(new View.OnClickListener() {

            // Logic for validation, input can't be empty
            @Override
            public void onClick(View v) {

                String str1 = e1.getText().toString();
                String str2 = e2.getText().toString();
                String str3 = e3.getText().toString();
                if(TextUtils.isEmpty(str1)){
                    e1.setError("Please enter your weight");
                    e1.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(str2)){
                    e2.setError("Please enter your height");
                    e2.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(str3)){
                    e3.setError("Please enter your age");
                    e3.requestFocus();
                    return;
                }

//Get the user values from the widget reference
                int weight = Integer.parseInt(str1);
                int height = Integer.parseInt(str2);
                int age = Integer.parseInt(str3);
                addListenerButton();
//Calculate cal value
                int calValue = calculatecal(weight, height,age);

                tv4.setText(String.valueOf("We recommend to take"+calValue + "-" + "calories"));
                Intent i = new Intent(getApplicationContext(),FoodActivity.class);

                i.putExtra("cal",calValue);


                startActivity(i);
            }
        });

    }
    private void addListenerButton() {
        radioGroup = findViewById(R.id.Gender);
        int selectedID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(selectedID);

        System.out.println("HI"+selectedID);
        System.out.println("Gender"+M.getId());
        System.out.println("Gender"+F.getId());
        if(selectedID == M.getId()) {
            Gender="M";
        } else {
            Gender="F";
        }

    }
    //Calculate cal
    private int calculatecal (int weight, int height,int age) {
        int k;
        System.out.println(Gender);
        if(Gender=="M"){
            k=(int) (10*(weight) + 6.25*(height) - 5*(age) +5);}
        else
        {
            k=(int) (10*(weight) + 6.25*(height) - 5*(age) -161);}
        return k;
    }

}


