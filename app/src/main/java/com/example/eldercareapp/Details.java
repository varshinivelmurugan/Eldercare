package com.example.eldercareapp;
       import android.content.Intent;
        import android.os.Bundle;
        import android.text.method.ScrollingMovementMethod;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;

        import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Details extends AppCompatActivity {
    TextView mDetails;
    DiaryDatabase db;
    Diary note;
    /*------------code referred from internet---------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDetails = findViewById(R.id.detailsOfNote);

        Intent i = getIntent();
        Long id = i.getLongExtra("ID",0);

        db = new DiaryDatabase(this);
        note = db.getNote(id);
        getSupportActionBar().setTitle(note.getTitle());
        mDetails.setText(note.getContent());
        mDetails.setMovementMethod(new ScrollingMovementMethod());

        Toast.makeText(this,"Title->" +note.getID(),Toast.LENGTH_SHORT).show();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteNote(note.getID());
                Toast.makeText(getApplicationContext(),"Note deleted",Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(getApplicationContext(),GratitudeMain.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    /*------------code snippet written by me---------------*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu,menu);
        return true;
    }
    /*------------code snippet written by me---------------*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit_note) {
            Toast.makeText(this,"Edit Note",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,Edit.class);
            i.putExtra("ID",note.getID());
            finish();
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}