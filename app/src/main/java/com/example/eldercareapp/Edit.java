package com.example.eldercareapp;
        import android.content.Intent;
        import android.os.Bundle;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.widget.EditText;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;

        import java.util.Calendar;

public class Edit extends AppCompatActivity {
    Toolbar toolbar;
    EditText title,details;
    Calendar cal;
    String todaysDate;
    String currentTime;
    DiaryDatabase db;
    Diary note;

    /*-------------code snippet written by me----------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent i = getIntent();
        Long id = i.getLongExtra("ID",0);
        db = new DiaryDatabase(this);
        note = db.getNote(id);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(note.getTitle());
        details = findViewById(R.id.details);
        title = findViewById(R.id.title);

        title.setText(note.getTitle());
        details.setText(note.getContent());

        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSeq, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSeq, int start, int before, int count) {
                if(charSeq.length() != 0){
                    getSupportActionBar().setTitle(charSeq);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //get current date and time
        cal = Calendar.getInstance();
        todaysDate = cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.DAY_OF_MONTH);
        currentTime = pad(cal.get(Calendar.HOUR))+":"+pad(cal.get(Calendar.MINUTE));
        Log.d("calendar","Date and Time: "+ todaysDate +" and "+ currentTime);
    }
    private String pad(int i){
        if(i<10) return "0"+i;
        return String.valueOf(i);
    }

    /*-------------code snippet written by me----------------*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu,menu);
        return true;
    }
    /*-------------code snippet written by me----------------*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            if (title.getText().length() != 0) {
                note.setTitle(title.getText().toString());
                note.setContent(details.getText().toString());
                int id = db.editNote(note);
                //added
                finish();
                onBackPressed();
                //startActivity(new Intent(getApplicationContext(),GratitudeMain.class));
            } else {
                title.setError("Title cannot be blank");
            }
        }
        else if(item.getItemId() == R.id.delete){
            Toast.makeText(this,"Note not saved",Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
