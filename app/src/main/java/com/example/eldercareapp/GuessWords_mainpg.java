package com.example.eldercareapp;
        import android.annotation.TargetApi;
        import android.content.DialogInterface;
        import android.content.res.AssetManager;
        import android.graphics.Color;
        import android.os.Build;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.RequiresApi;
        import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.util.ArrayList;
        import java.util.HashSet;
        import java.util.Random;
        import java.util.Set;

        import static java.lang.String.join;


public class GuessWords_mainpg extends AppCompatActivity {

    /*-----------------------------code snippet written by me------------------------------------*/

    Set<String> clue = new HashSet<String>();
    public ArrayList<String> words = new ArrayList<>();
    public CharSequence[][] question = new CharSequence[6][6];
    String start="",word2="",word3="",word4="",word5="";
    TextView txtview;
    public CharSequence[][] answer = new CharSequence[6][6];
    private EditText[][] textviews = new EditText[6][6];
    Random rand = new Random();

    int i,j;
    String user;
    DBHelper db;

    /*----------------------------code snippet written by me-------------------------------------*/

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_words_mainpg);
        user=getIntent().getExtras().getString("name");
          db=new DBHelper(this);

        textviews[0][0] = (EditText) findViewById(R.id.textView1);
        textviews[0][1] = (EditText) findViewById(R.id.textView2);
        textviews[0][2] = (EditText) findViewById(R.id.textView3);
        textviews[0][3] = (EditText) findViewById(R.id.textView4);
        textviews[0][4] = (EditText) findViewById(R.id.textView5);
        textviews[0][5] = (EditText) findViewById(R.id.textView6);
        textviews[1][0] = (EditText) findViewById(R.id.textView7);
        textviews[1][1] = (EditText) findViewById(R.id.textView8);
        textviews[1][2] = (EditText) findViewById(R.id.textView9);
        textviews[1][3] = (EditText) findViewById(R.id.textView10);
        textviews[1][4] = (EditText) findViewById(R.id.textView11);
        textviews[1][5] = (EditText) findViewById(R.id.textView12);
        textviews[2][0] = (EditText) findViewById(R.id.textView13);
        textviews[2][1] = (EditText) findViewById(R.id.textView14);
        textviews[2][2] = (EditText) findViewById(R.id.textView15);
        textviews[2][3] = (EditText) findViewById(R.id.textView16);
        textviews[2][4] = (EditText) findViewById(R.id.textView17);
        textviews[2][5] = (EditText) findViewById(R.id.textView18);
        textviews[3][0] = (EditText) findViewById(R.id.textView19);
        textviews[3][1] = (EditText) findViewById(R.id.textView20);
        textviews[3][2] = (EditText) findViewById(R.id.textView21);
        textviews[3][3] = (EditText) findViewById(R.id.textView22);
        textviews[3][4] = (EditText) findViewById(R.id.textView23);
        textviews[3][5] = (EditText) findViewById(R.id.textView24);
        textviews[4][0] = (EditText) findViewById(R.id.textView25);
        textviews[4][1] = (EditText) findViewById(R.id.textView26);
        textviews[4][2] = (EditText) findViewById(R.id.textView27);
        textviews[4][3] = (EditText) findViewById(R.id.textView28);
        textviews[4][4] = (EditText) findViewById(R.id.textView29);
        textviews[4][5] = (EditText) findViewById(R.id.textView30);
        textviews[5][0] = (EditText) findViewById(R.id.textView31);
        textviews[5][1] = (EditText) findViewById(R.id.textView32);
        textviews[5][2] = (EditText) findViewById(R.id.textView33);
        textviews[5][3] = (EditText) findViewById(R.id.textView34);
        textviews[5][4] = (EditText) findViewById(R.id.textView35);
        textviews[5][5] = (EditText) findViewById(R.id.textView36);

        txtview = (TextView) findViewById(R.id.clues);

        /*---------------------------code referred from internet---------------------------------*/

        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("words.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = in.readLine()) != null) {
                String word = line.trim();
                if (word.length() == 6) {
                    words.add(word);
                }
            }
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "File not loaded", Toast.LENGTH_LONG);
        }

        /*----------------------------code snippet written by me------------------------------*/

        //First word
        start = words.get(rand.nextInt(words.size()));
        vertWord(0, 0, start);

        //second word
        String a1 = "" + textviews[0][0].getText();
        do {
            word2 = words.get(rand.nextInt(words.size()));
            if(word2==start)word2="";
        }while (!Character.toString(word2.charAt(0)).equalsIgnoreCase(a1));

        horizWord(0,1, word2);

        //third word
        String a2 = "" + textviews[0][1].getText();
        do {
            word3 = words.get(rand.nextInt(words.size()));
        }while (!Character.toString(word3.charAt(0)).equalsIgnoreCase(a2));

        vertWord(1, 1, word3);

        //fourth word
        String a3 = "" + textviews[0][3].getText();
        do {
            word4 = words.get(rand.nextInt(words.size()));
        }while (!Character.toString(word4.charAt(0)).equalsIgnoreCase(a3));

        vertWord(1, 3, word4);

        //fifth word
        String a4 = "" + textviews[0][5].getText();
        do {
            word5 = words.get(rand.nextInt(words.size()));
        }while (!Character.toString(word5.charAt(0)).equalsIgnoreCase(a4));

        vertWord(0, 5, word5);


        for (i = 0; i < 6; i++) {
            for (j = 0; j < 6; j++) {
                question[i][j] = textviews[i][j].getText();
            }
        }

        blackBox();
        remove();
        Dialogue();

    }

    /*----------------------------code snippet written by me------------------------------------*/

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void blackBox() {
        int i;
        for (i = 1; i <= 5; i++)
        {
            textviews[i][2].setBackgroundColor(Color.BLACK);
            textviews[i][4].setBackgroundColor(Color.BLACK);
        }
    }

    /*-----------------------------code snippet written by me-----------------------------------*/

    public void remove() {
        for(int i=0;i<6;i++) {
            if(i%2==0) {
                for(int j=1;j<6;j+=2) {
                    clue.add(textviews[i][j].getText().toString());
                    textviews[i][j].setText("");
                    textviews[i][j-1].setKeyListener(null);
                    textviews[i][j-1].setTextColor(Color.WHITE);
                }
            }
            else {
                clue.add(textviews[i][0].getText().toString());
                textviews[i][0].setText("");
                for(int j=1;j<6;j++){
                    textviews[i][j].setKeyListener(null);
                    textviews[i][j].setTextColor(Color.WHITE); }
            }
        }

        //Hints
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String string = join(" ",clue);
            txtview.setText("HINTS :  "+string);
        }
    }

    /*----------------------------code snippet written by me------------------------------------*/

    private void vertWord(int row, int column, String word) {
        char[] setWord = word.toCharArray();

        for (i = row; i < word.length(); i++) {
            textviews[i][column].setText(Character.toString(setWord[i]));
        }
    }

    /*-----------------------------code snippet written by me-----------------------------------*/

    private void horizWord(int row, int column, String word) {
        char[] setWord = word.toCharArray();

        for (int i = column; i < word.length(); i++) {
            textviews[row][i].setText(Character.toString(setWord[i]));
        }
    }

    /*-----------------------------code snippet written by me-----------------------------------*/

    public void clicked(View view){
        finish();
        startActivity(getIntent());
    }

    /*-----------------------code snippet written by me(syntax referred from internet)---------------------------*/

    private void Dialogue() {
        Button bt = findViewById(R.id.button1);
        bt.setOnClickListener(
                v -> {
                    AlertDialog.Builder a_builder = new AlertDialog.Builder(this);


                    int i, j;
                    for (i = 0; i < 6; i++) {
                        for (j = 0; j < 6; j++) {
                            answer[i][j] = textviews[i][j].getText();
                        }
                    }
                    int flag = 0;
                    for(i=0;i<6;i++){
                        for(j=0;j<6;j++){
                            if(question[i][j].toString().equalsIgnoreCase(answer[i][j].toString()))
                            {
                                flag++;
                            }
                        }
                    }
                    float mark=(((float)flag-24)/(float)12)*100;
                    // String score = String.format("%.1f", mark);
                    Boolean regResult =db.insertWordScore(user,(int)mark);
                    if(regResult==true)
                    {

                        Toast.makeText(this,"Score inserted"+user+(int)mark+" "+db.getWordScore(user),Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(this,"Score insertion failed ",Toast.LENGTH_SHORT).show();
                    }


                    if(flag==36) {

                        a_builder.setMessage("YOU WON!! \n PERFECT SCORE : "+(int)mark+"%").setCancelable(false).setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).setNegativeButton("NewGame",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        finish();
                                        startActivity(getIntent());
                                    }
                                });


                        AlertDialog alert = a_builder.create();
                        alert.setTitle(("GAME OVER "));
                        alert.show();
                    }

                    else if(flag!=36){
                        a_builder.setMessage("GOOD TRY\n YOUR SCORE : "+(int)mark+"%").setCancelable(false).setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).setNegativeButton("NewGame",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        finish();
                                        startActivity(getIntent());
                                    }
                                });


                        AlertDialog alert = a_builder.create();
                        alert.setTitle(("GAME OVER"));
                        alert.show();

                    }
                }
        );

    }
}
