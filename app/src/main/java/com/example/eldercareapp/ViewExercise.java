//code written by me
package com.example.eldercareapp;

import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewExercise extends AppCompatActivity {

    int video_id; //to get the image_id from the intent
    String name;  //to get yoga name from the getintent
    String description;

    TextView timer, title, descrip;   //to obtain the timer and name of the yoga

    VideoView videoView;
    Button btnstart;  //to start the timer
    boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercise);

        timer = (TextView) findViewById(R.id.timer);
        title = (TextView) findViewById(R.id.title);
        descrip = (TextView) findViewById(R.id.description);
        videoView = (VideoView) findViewById(R.id.videoView);


        // detail_image = (ImageView)findViewById(R.id.detail_image);

        btnstart = (Button) findViewById(R.id.btnstart);   //on clicking the start button,timer starts
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!running) {
                    btnstart.setText("Done");  //once the timer starts button changes as done
                    new CountDownTimer(20000, 1000) {
                        @Override
                        public void onTick(long l) {
                            timer.setText("" + l / 1000);
                        }

                        @Override
                        public void onFinish() {

                            finish();
                        }
                    }.start();
                } else {
                    Toast.makeText(ViewExercise.this, "Finish!!!", Toast.LENGTH_SHORT).show();
                    finish();
                }

                running = !running;
            }
        });

        timer.setText("");

        if (getIntent() != null)  //only if adapter class return intent image and name is set to respective variables
        {
            video_id = getIntent().getIntExtra("video_id", -1);
            name = getIntent().getStringExtra("name");
            description = getIntent().getStringExtra("description");


            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + video_id));
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            videoView.start();
            videoView.setMediaController(mediaController);
            title.setText(name);
            descrip.setText(description);


        }
    }
}