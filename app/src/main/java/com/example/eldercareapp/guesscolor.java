package com.example.eldercareapp;
//code written by myself

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public final class guesscolor extends AppCompatActivity {
    private int counts;
    private int score;
    private int expectedAnswer;
    private List<ColorMap> colorMaps = new ArrayList<ColorMap>();
    private Button lBtn;
    private Button rBtn;
    private TextView scoreLabel;
    private TextView countLabel;
    private TextView textQuery;
    private TextView timerview;
    private List<Button> list = new ArrayList<Button>();
    private FloatingActionButton refreshBtn;
    int secondsRemaining = 0;
    CountDownTimer timer;
    DBHelper db;
    String user;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.guesscolor);
        initData();
        timerview = (TextView) findViewById(R.id.timer);
        lBtn = (Button) findViewById(R.id.button1);
        rBtn = (Button) findViewById(R.id.button2);
        db = new DBHelper(this);

        list.add(lBtn);
        list.add(rBtn);

        scoreLabel = (TextView) findViewById(R.id.textWins);
        countLabel = (TextView) findViewById(R.id.textPlayCount);
        textQuery = (TextView) findViewById(R.id.textQuery);
        refreshBtn = (FloatingActionButton) findViewById(R.id.fabRevert);

        timer = new CountDownTimer(30000, 1000) {
            @Override

            public void onTick(long millisUntilFinished) {
                timerview.setText(Integer.toString(secondsRemaining) + "sec");
                secondsRemaining++;
            }

            @Override
            public void onFinish() {
                list.get(0).setEnabled(false);
                list.get(1).setEnabled(false);

                alertbox();

            }
        }.start();


        setupGame();
    }

    private void initData() {
        colorMaps.add(new ColorMap(-65536, "RED"));
        colorMaps.add(new ColorMap(-16776961, "BLUE"));
        colorMaps.add(new ColorMap(-16711936, "GREEN"));
        colorMaps.add(new ColorMap(-256, "YELLOW"));
        colorMaps.add(new ColorMap(-7829368, "GRAY"));
        colorMaps.add(new ColorMap(-16777216, "BLACK"));
    }


    private final void refreshScoreBar() {

        scoreLabel.setText((CharSequence) String.valueOf(this.score));
        countLabel.setText((CharSequence) String.valueOf(this.counts));
    }


    private final void setupGame() {

        user = getIntent().getExtras().getString("name");
        Iterator buttonpress = list.iterator();

        while (buttonpress.hasNext()) {

            Button btn = (Button) buttonpress.next();
            btn.setOnClickListener((OnClickListener) new OnClickListener() {
                public final void onClick(View it) {
                    // Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    ColorDrawable var10000 = (ColorDrawable) it.getBackground();
                    guesscolor var3 = null;
                    Log.d("buttonbackgroung", "value" + var10000.getColor());
                    Log.d("Answer", "value" + expectedAnswer);
                    if (var10000.getColor() == expectedAnswer) {
                        var3 = guesscolor.this;
                        var3.score = var3.score + 1;
                    }
                    counts++;
                    refreshScoreBar();
                    resetGameColors();
                }

            });
        }

        FloatingActionButton var3 = this.refreshBtn;
        var3.setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                Intent i = new Intent(getApplicationContext(), guesscolor.class);
                String name = getIntent().getExtras().getString("name");
                i.putExtra("name", name);
                startActivity(i);
                finish();

            }
        }));


        resetGameColors();
        resetScoreBar();


    }

    private final void alertbox() {
        float score_color = (this.score * 100) / this.counts;
        System.out.println(this.counts);
        System.out.println(score_color);
        System.out.println(this.score);
        System.out.println((this.score * 100) / this.counts);
        Boolean regResult = db.insertScore(user, (this.score * 100) / this.counts);
        if (regResult == true) {

            Toast.makeText(guesscolor.this, "Score inserted" + user + score_color, Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(guesscolor.this, "Score insertion failed ", Toast.LENGTH_SHORT).show();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(guesscolor.this);

        builder.setTitle("Well done!")
                .setMessage("Your score is " + score)
                .setPositiveButton("NEW GAME", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getApplicationContext(), guesscolor.class);

                        String name = getIntent().getExtras().getString("name");
                        i.putExtra("name", name);
                        startActivity(i);
                        finish();
                    }
                }).setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }

    private final void resetScoreBar() {
        this.counts = 0;
        this.score = 0;
        this.refreshScoreBar();
    }

    private final void resetGameColors() {
        Random r = new Random();
        int randnum = r.nextInt(colorMaps.size());
        ColorMap randomColorMap1 = colorMaps.get(randnum);
        List<ColorMap> norandomColorMap1 = new ArrayList<ColorMap>();

        for (int i = 0; i < colorMaps.size(); i++) {

            if (colorMaps.get(i) != randomColorMap1)
                norandomColorMap1.add(colorMaps.get(i));

        }
        Random ran = new Random();
        int randnumb = ran.nextInt(norandomColorMap1.size());
        ColorMap randomColorMap2 = norandomColorMap1.get(randnumb);

        List<ColorMap> involveColors = new ArrayList<ColorMap>();
        involveColors.add(randomColorMap1);
        involveColors.add(randomColorMap2);

        this.setWordAndColor(involveColors);
        this.setQueryText(involveColors);
        this.setButtonColors(involveColors);
    }

    private final void setWordAndColor(List involvedColors) {

        TextView it = (TextView) findViewById(R.id.textColorWord);
        it.setText(((ColorMap) involvedColors.get(0)).getName());
        it.setTextColor(((ColorMap) involvedColors.get(1)).getValue());
    }

    private final void setQueryText(List involvedColors) {
        String[] queries = new String[]{"Word?", "Color?"};
        Random r = new Random();
        int randomnumber = r.nextInt(queries.length);
        String it = queries[randomnumber];
        textQuery.setText(it);

        int index = 0;
        for (int i = 0; i < queries.length; i++)
            if (queries[i].contains(it))
                index = i;


        expectedAnswer = ((ColorMap) involvedColors.get(index)).getValue();
        Log.d("debug", "value:" + expectedAnswer);
    }


    private final void setButtonColors(List involvedColors) {
        int[] var3 = new int[]{0, 1};
        Random r = new Random();
        int randnum = r.nextInt(var3.length);
        int randomFirst = var3[randnum];
        int randomSecond;
        if (randomFirst == 1)
            randomSecond = 0;
        else
            randomSecond = 1;

        int color1 = ((ColorMap) involvedColors.get(0)).getValue();
        int color2 = ((ColorMap) involvedColors.get(1)).getValue();

        list.get(randomFirst).setBackgroundColor(color1);
        list.get(randomSecond).setBackgroundColor(color2);

    }


}