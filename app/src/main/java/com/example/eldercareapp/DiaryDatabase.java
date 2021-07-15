package com.example.eldercareapp;
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

        import java.util.ArrayList;
        import java.util.List;

public class DiaryDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "DiaryDB";
    private static final String DATABASE_TABLE = "Diarytable";

    //COLUMN NAMES for database table
    private static final String KEY_USER = "user";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DATE ="date";
    private static final String KEY_TIME = "time";


    public DiaryDatabase(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    /*------------code referred from internet---------------*/

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATE TABLE diary(id INT PRIMARY KEY, title TEXT, content TEXT, date TEXT, time TEXT);
        String query = "CREATE TABLE "+DATABASE_TABLE+" ("+
                KEY_ID+" INTEGER PRIMARY KEY,"+KEY_TITLE+" TEXT,"+
                KEY_CONTENT+" TEXT,"+
                KEY_DATE+" TEXT,"+
                KEY_TIME+" TEXT,"+
                KEY_USER+" TEXT"
                +" )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion>=newVersion)return;
        db.execSQL(" DROP TABLE IF EXISTS "+DATABASE_TABLE);
        onCreate(db);
    }

    /*------------code referred from internet---------------*/

    public long addNote(Diary note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(KEY_TITLE,note.getTitle());
        c.put(KEY_CONTENT,note.getContent());
        c.put(KEY_DATE,note.getDate());
        c.put(KEY_TIME,note.getTime());
        c.put(KEY_USER,note.getUser());

        long ID = db.insert(DATABASE_TABLE,null,c);
        Log.d("Inserted","ID ->" + ID);
        return ID;
    }

    /*------------code referred from internet---------------*/

    public Diary getNote(long id){
        //select * from table where id=1
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE,new String[]{KEY_ID,KEY_TITLE,KEY_CONTENT,KEY_DATE,KEY_TIME,KEY_USER},KEY_ID+"=?",
                new String[]{String.valueOf(id)},null,null,null);
        if(cursor != null)
            cursor.moveToFirst();

        return new Diary(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5));
    }

    /*------------code referred from internet---------------*/

    public List<Diary> getNotes(String user){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Diary> allNotes = new ArrayList<>();
        // select * from table;

        String query = "SELECT * FROM "+DATABASE_TABLE;
        Cursor cursor = db.query(DATABASE_TABLE,new String[]{KEY_ID,KEY_TITLE,KEY_CONTENT,KEY_DATE,KEY_TIME,KEY_USER},KEY_USER+"=?",
                new String[]{user},null,null,null);
        // Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                Diary note = new Diary();
                note.setID(cursor.getLong(0));
                note.setTitle(cursor.getString(1));
                note.setContent(cursor.getString(2));
                note.setDate(cursor.getString(3));
                note.setTime(cursor.getString(4));

                allNotes.add(note);

            }while(cursor.moveToNext());
        }
        return allNotes;
    }

    /*------------code referred from internet---------------*/

    public int editNote(Diary note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        Log.d("Edited","Edited Title: ->" + note.getTitle());
        c.put(KEY_USER,note.getUser());
        c.put(KEY_TITLE,note.getTitle());
        c.put(KEY_CONTENT,note.getContent());
        c.put(KEY_DATE,note.getDate());
        c.put(KEY_TIME,note.getTime());
        return db.update(DATABASE_TABLE,c,KEY_ID+"=?",new String[]{String.valueOf(note.getID())});
    }

    /*------------code referred from internet---------------*/

    void deleteNote(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE,KEY_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }
}
