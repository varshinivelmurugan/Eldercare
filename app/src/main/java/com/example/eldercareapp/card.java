package com.example.eldercareapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;

public class card extends AppCompatActivity {
    TextView score;
    ImageView a1, a2, a3, a4, b1, b2, b3, b4, c1, c2, c3, c4;
    //array for images
    Integer[] memcards = {11, 12, 13, 14, 15, 16, 21, 22, 23, 24, 25, 26};
    //actual images
    int img11, img12, img13, img14, img15, img16, img21, img22, img23, img24, img25, img26;
    int card1, card2;
    int firstclicked, secondclicked;
    int cardNumber = 1;
    int scoreval = 0;
    int k=0;
    int counter = 0;
    DBHelper db;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        user=getIntent().getExtras().getString("name");
        System.out.println(user);
        score = (TextView) findViewById(R.id.score);
        db=new DBHelper(this);
        a1 = (ImageView) findViewById(R.id.a1);
        a2 = (ImageView) findViewById(R.id.a2);
        a3 = (ImageView) findViewById(R.id.a3);
        a4 = (ImageView) findViewById(R.id.a4);
        b1 = (ImageView) findViewById(R.id.b1);
        b2 = (ImageView) findViewById(R.id.b2);
        b3 = (ImageView) findViewById(R.id.b3);
        b4 = (ImageView) findViewById(R.id.b4);
        c1 = (ImageView) findViewById(R.id.c1);
        c2 = (ImageView) findViewById(R.id.c2);
        c3 = (ImageView) findViewById(R.id.c3);
        c4 = (ImageView) findViewById(R.id.c4);

        a1.setTag("0");
        a2.setTag("1");
        a3.setTag("2");
        a4.setTag("3");
        b1.setTag("4");
        b2.setTag("5");
        b3.setTag("6");
        b4.setTag("7");
        c1.setTag("8");
        c2.setTag("9");
        c3.setTag("10");
        c4.setTag("11");
        //code written by me
        final TextView counttime = findViewById(R.id.counttime);
        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                counttime.setText(String.valueOf("TIMER:" + counter));
                counter++;

            }

            @Override
            public void onFinish() {
                counttime.setText("Finished");
                finalendgame();
            }
        }.start();
        //load all images to play
        displaycardimg();
        Collections.shuffle(Arrays.asList(memcards)); //shuffle all images
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int card = Integer.parseInt((String) v.getTag());
                doStuff(a1, card);
            }
        });
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int card = Integer.parseInt((String) v.getTag());
                doStuff(a2, card);
            }
        });
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int card = Integer.parseInt((String) v.getTag());
                doStuff(a3, card);
            }
        });
        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int card = Integer.parseInt((String) v.getTag());
                doStuff(a4, card);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int card = Integer.parseInt((String) v.getTag());
                doStuff(b1, card);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int card = Integer.parseInt((String) v.getTag());
                doStuff(b2, card);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int card = Integer.parseInt((String) v.getTag());
                doStuff(b3, card);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int card = Integer.parseInt((String) v.getTag());
                doStuff(b4, card);
            }
        });
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int card = Integer.parseInt((String) v.getTag());
                doStuff(c1, card);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int card = Integer.parseInt((String) v.getTag());
                doStuff(c2, card);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int card = Integer.parseInt((String) v.getTag());
                doStuff(c3, card);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int card = Integer.parseInt((String) v.getTag());
                doStuff(c4, card);
            }
        });
    }

    private void doStuff(ImageView iv, int card) {
        //set the correct image to image view
        if (memcards[card] == 11) {
            iv.setImageResource(img11);
        } else if (memcards[card] == 12) {
            iv.setImageResource(img12);
        } else if (memcards[card] == 13) {
            iv.setImageResource(img13);
        } else if (memcards[card] == 14) {
            iv.setImageResource(img14);
        } else if (memcards[card] == 15) {
            iv.setImageResource(img15);
        } else if (memcards[card] == 16) {
            iv.setImageResource(img16);
        } else if (memcards[card] == 21) {
            iv.setImageResource(img21);
        } else if (memcards[card] == 22) {
            iv.setImageResource(img22);
        } else if (memcards[card] == 23) {
            iv.setImageResource(img23);
        } else if (memcards[card] == 24) {
            iv.setImageResource(img24);
        } else if (memcards[card] == 25) {
            iv.setImageResource(img25);
        } else if (memcards[card] == 26) {
            iv.setImageResource(img26);
        }

        //To check which card is selected and save it to temporary variable
        if (cardNumber == 1) {
            card1 = memcards[card];
            if (card1 > 20) {
                card1 = card1 - 10;
            }
            cardNumber = 2;
            firstclicked = card;
            iv.setEnabled(false);
        } else if (cardNumber == 2) {
            card2 = memcards[card];
            if (card2 > 20) {
                card2 = card2 - 10;
            }
            cardNumber = 1;
            secondclicked = card;

            a1.setEnabled(false);
            a2.setEnabled(false);
            a3.setEnabled(false);
            a4.setEnabled(false);
            b1.setEnabled(false);
            b2.setEnabled(false);
            b3.setEnabled(false);
            b4.setEnabled(false);
            c1.setEnabled(false);
            c2.setEnabled(false);
            c3.setEnabled(false);
            c4.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //check if selected cards are equal
                    calculate();
                }
            }, 1000);


        }
    }
//code referred from net and written by me
    private void calculate() {
        //if images are equal remove them and add points
        if (card1 == card2)    //if(11 == 11 upto 16==16 may be)
        {
            if (firstclicked == 0) {
                a1.setVisibility(View.INVISIBLE);
            } else if (firstclicked == 1) {
                a2.setVisibility(View.INVISIBLE);
            } else if (firstclicked == 2) {
                a3.setVisibility(View.INVISIBLE);
            } else if (firstclicked == 3) {
                a4.setVisibility(View.INVISIBLE);
            } else if (firstclicked == 4) {
                b1.setVisibility(View.INVISIBLE);
            } else if (firstclicked == 5) {
                b2.setVisibility(View.INVISIBLE);
            } else if (firstclicked == 6) {
                b3.setVisibility(View.INVISIBLE);
            } else if (firstclicked == 7) {
                b4.setVisibility(View.INVISIBLE);
            } else if (firstclicked == 8) {
                c1.setVisibility(View.INVISIBLE);
            } else if (firstclicked == 9) {
                c2.setVisibility(View.INVISIBLE);
            } else if (firstclicked == 10) {
                c3.setVisibility(View.INVISIBLE);
            } else if (firstclicked == 11) {
                c4.setVisibility(View.INVISIBLE);
            }

            if (secondclicked == 0) {
                a1.setVisibility(View.INVISIBLE);
            } else if (secondclicked == 1) {
                a2.setVisibility(View.INVISIBLE);
            } else if (secondclicked == 2) {
                a3.setVisibility(View.INVISIBLE);
            } else if (secondclicked == 3) {
                a4.setVisibility(View.INVISIBLE);
            } else if (secondclicked == 4) {
                b1.setVisibility(View.INVISIBLE);
            } else if (secondclicked == 5) {
                b2.setVisibility(View.INVISIBLE);
            } else if (secondclicked == 6) {
                b3.setVisibility(View.INVISIBLE);
            } else if (secondclicked == 7) {
                b4.setVisibility(View.INVISIBLE);
            } else if (secondclicked == 8) {
                c1.setVisibility(View.INVISIBLE);
            } else if (secondclicked == 9) {
                c2.setVisibility(View.INVISIBLE);
            } else if (secondclicked == 10) {
                c3.setVisibility(View.INVISIBLE);
            } else if (secondclicked == 11) {
                c4.setVisibility(View.INVISIBLE);
            }
            scoreval=scoreval+1;

            score.setText("Score:" + scoreval);
        } else {
            a1.setImageResource(R.drawable.qmleaf);
            a2.setImageResource(R.drawable.qmleaf);
            a3.setImageResource(R.drawable.qmleaf);
            a4.setImageResource(R.drawable.qmleaf);
            b1.setImageResource(R.drawable.qmleaf);
            b2.setImageResource(R.drawable.qmleaf);
            b3.setImageResource(R.drawable.qmleaf);
            b4.setImageResource(R.drawable.qmleaf);
            c1.setImageResource(R.drawable.qmleaf);
            c2.setImageResource(R.drawable.qmleaf);
            c3.setImageResource(R.drawable.qmleaf);
            c4.setImageResource(R.drawable.qmleaf);
        }
        a1.setEnabled(true);
        a2.setEnabled(true);
        a3.setEnabled(true);
        a4.setEnabled(true);
        b1.setEnabled(true);
        b2.setEnabled(true);
        b3.setEnabled(true);
        b4.setEnabled(true);
        c1.setEnabled(true);
        c2.setEnabled(true);
        c3.setEnabled(true);
        c4.setEnabled(true);
        //checkif game is over
        checkendgame();
    }

    private void checkendgame() {
        if (a1.getVisibility() == View.INVISIBLE &&
                a2.getVisibility() == View.INVISIBLE &&
                a3.getVisibility() == View.INVISIBLE &&
                a4.getVisibility() == View.INVISIBLE &&
                b1.getVisibility() == View.INVISIBLE &&
                b2.getVisibility() == View.INVISIBLE &&
                b3.getVisibility() == View.INVISIBLE &&
                b4.getVisibility() == View.INVISIBLE &&
                c1.getVisibility() == View.INVISIBLE &&
                c2.getVisibility() == View.INVISIBLE &&
                c3.getVisibility() == View.INVISIBLE &&
                c4.getVisibility() == View.INVISIBLE) {

            finalendgame();
        }
    }


    private void finalendgame()
    {
        System.out.println(user+"  "+scoreval);
        Boolean regResult =db.insertCardScore(user,((scoreval*100)/6));
        if(regResult==true)
        {

            Toast.makeText(card.this,"Score inserted"+user+(scoreval*100)/6,Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(getApplicationContext(),login.class);
            //startActivity(intent);
        }
        else
        {
            Toast.makeText(card.this,"Score insertion failed ",Toast.LENGTH_SHORT).show();
        }
        AlertDialog.Builder alert=new AlertDialog.Builder(card.this);
        //setting all messages here
        alert.setMessage("GAME OVER!\nScore : "+scoreval).setCancelable(false)
                .setPositiveButton("NEW",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i=new Intent(getApplicationContext(),card.class);

                        String name=getIntent().getExtras().getString("name");
                           i.putExtra("name",name);
                        startActivity(i);
                       // startActivity(getIntent());
                        finish();
                    }
                }).setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        //create dialog box
        AlertDialog alert2=alert.create();
        alert2.show();
    }
    private void displaycardimg()
    {
        img11=R.drawable.i11;
        img12=R.drawable.i12;
        img13=R.drawable.i13;
        img14=R.drawable.i14;
        img15=R.drawable.i15;
        img16=R.drawable.i16;
        img21=R.drawable.i21;
        img22=R.drawable.i22;
        img23=R.drawable.i23;
        img24=R.drawable.i24;
        img25=R.drawable.i25;
        img26=R.drawable.i26;
    }
}