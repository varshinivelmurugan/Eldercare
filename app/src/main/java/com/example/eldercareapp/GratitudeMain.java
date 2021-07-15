package com.example.eldercareapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GratitudeMain extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    Adapter adapter;
    List<Diary> notes;
    String user;
    /*------------code snippet written by me---------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gratitude_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DiaryDatabase db = new DiaryDatabase(this);
        //modified
        user=getIntent().getExtras().getString("name");
        notes = db.getNotes(user);

        recyclerView = findViewById(R.id.notesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this,notes);
        recyclerView.setAdapter(adapter);
    }
    /*-------------code snippet written by me----------------*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.addmenu,menu);
        return true;
    }
    /*------------code snippet written by me----------------*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.Add){
            Intent i = new Intent(this,AddNote.class);
            //modified
            i.putExtra("name",user);
            //finish();
            startActivity(i);
            Toast.makeText(this,"Add button is clicked",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}