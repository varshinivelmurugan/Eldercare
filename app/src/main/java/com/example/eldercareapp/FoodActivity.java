package com.example.eldercareapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class FoodActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);
        //code written by me
        MyListData[] myListData = new MyListData[] {
                new MyListData("Fish","136", R.drawable.fish),
                new MyListData("Oats","389", R.drawable.oats),
                new MyListData("Tofu","86", R.drawable.tofu),
                new MyListData("Olives","115", R.drawable.olives),
                new MyListData("Apple","59", R.drawable.apple),
                new MyListData("Egg","80", R.drawable.egg),
                new MyListData("Almond","170", R.drawable.almond),
                new MyListData("Buttered toast", "150",R.drawable.toast),
                new MyListData("Brown Rice","175", R.drawable.brown_rice),
                new MyListData("Grilled Chicken","225", R.drawable.grilled_chicken),
                new MyListData("Walnut","165", R.drawable.walnut),
                new MyListData("Peanut Butter","75", R.drawable.peanut_butter),
                new MyListData("Green Beans","100", R.drawable.green_beans),
                new MyListData("Milk","50", R.drawable.milk),
                new MyListData("Sprouts", "100",R.drawable.sprouts),
                new MyListData("Flax Seeds","534", R.drawable.flaxseed),
                new MyListData("Dark Chocolate","505", R.drawable.darkchoclate),
                new MyListData("Banana","90", R.drawable.banana),
                      };


        Intent intent = getIntent();
        int cal = intent.getIntExtra("cal",0);
        System.out.println(cal);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyListAdapter adapter = new MyListAdapter(myListData,cal);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}

