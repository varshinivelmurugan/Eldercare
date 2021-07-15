package com.example.eldercareapp;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;

        import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    /*-------------code snippet written by me----------------*/
    float color,word,card;
    long mobno;

    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);
       // word=getIntent().getExtras().getFloat("word");
       // color=getIntent().getExtras().getFloat("color");
       // card=getIntent().getExtras().getFloat("card");
       mobno=getIntent().getExtras().getLong("mobno");
    }

    /*-------------code snippet written by me----------------*/
    public void Gratitude_Diary(View view){
        //modified
        String name=getIntent().getExtras().getString("name");
        Intent i = new Intent(this,GratitudeMain.class);
        //modified
        i.putExtra("name",name);
        startActivity(i);
    }

    /*-------------code snippet written by me----------------*/
    public void Guess_words(View view){
        String name=getIntent().getExtras().getString("name");
        Intent i = new Intent(this,Guess_Words.class);
        i.putExtra("name",name);
        startActivity(i);
    }
    public void card(View view){
        String name=getIntent().getExtras().getString("name");
        Intent i = new Intent(this,card.class);
        i.putExtra("name",name);
        startActivity(i);
    }
    public void diet(View view){
        Intent i = new Intent(this,Diet.class);
        startActivity(i);
    }
    public void Yoga(View view){
        Intent i = new Intent(this,ListExercises.class);
        startActivity(i);
    }
    public void Guess_color(View view){
        String name=getIntent().getExtras().getString("name");
        Intent i = new Intent(this,guesscolor.class);
        i.putExtra("name",name);
        startActivity(i);
    }
    public void Graph(View view){
        //String name=getIntent().getExtras().getString("name");
        String name=getIntent().getExtras().getString("name");
        float word=db.getWordScore(name);
        float color=db.getColorScore(name);
        float card=db.getCardScore(name);
        Intent i = new Intent(MainActivity.this, performanceGraph.class);
        i.putExtra("mobno",mobno);
        i.putExtra("cardPercentage",card);
        i.putExtra("colorPercentage", color);
        i.putExtra("wordPercentage", word);
        //Log.d("Answer","value"+name);
        startActivity(i);
    }
}