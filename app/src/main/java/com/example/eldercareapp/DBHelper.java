//internet code refered for writting this
package com.example.eldercareapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {
    String user;

    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table cardmatch(username Text,score int)");
        db.execSQL("create Table users(username Text primary key,mobno int,password Text )");
        db.execSQL("Create Table guess_color(username Text,score int)");

        db.execSQL("Create Table guess_word(username Text,score int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists users");
        db.execSQL("drop Table if exists guess_color");
        db.execSQL("drop Table if exists cardmatch");
        db.execSQL("drop Table if exists guess_word");
    }

    public Boolean insertData(String username, long mobno, String password) {
        System.out.println("inside insert" + mobno);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("mobno", mobno);
        contentValues.put("password", password);
        long result = db.insert("users", null, contentValues);
        if (result == -1) {
            return false;

        } else
            return true;
    }

    public Boolean insertScore(String username, float score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("score", score);
        long result = db.insert("guess_color", null, contentValues);
        if (result == -1) {
            return false;

        } else
            return true;

    }

    public float getColorScore(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        int total = 0, rowcount = 0;
        float percentage;
        Cursor cursor = db.rawQuery("SELECT COUNT(score) as Rowcount, SUM(score) as Total from guess_color WHERE username = ?", new String[]{name});

        if (cursor.moveToFirst()) {
            total = cursor.getInt(cursor.getColumnIndex("Total"));
            rowcount = cursor.getInt(cursor.getColumnIndex("Rowcount"));
        }

        percentage = (float) total / (float) rowcount;

        return percentage;
    }

    public Boolean insertWordScore(String username, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("score", score);
        long cresult = db.insert("guess_word", null, contentValues);
        if (cresult == -1) {
            return false;

        } else
            return true;

    }

    public float getWordScore(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        int total = 0, rowcount = 0;
        float percentage;
        Cursor cursor = db.rawQuery("SELECT COUNT(score) as Rowcount, SUM(score) as Total from guess_word WHERE username = ?", new String[]{name});

        if (cursor.moveToFirst()) {
            total = cursor.getInt(cursor.getColumnIndex("Total"));
            rowcount = cursor.getInt(cursor.getColumnIndex("Rowcount"));
        }

        percentage = (float) total / (float) rowcount;

        return percentage;
    }

    public Boolean insertCardScore(String username, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("score", score);
        long cresult = db.insert("cardmatch", null, contentValues);
        if (cresult == -1) {
            return false;

        } else
            return true;

    }

    public float getCardScore(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        int total = 0, rowcount = 0;
        float percentage;
        Cursor cursor = db.rawQuery("SELECT COUNT(score) as Rowcount, SUM(score) as Total from cardmatch WHERE username = ?", new String[]{name});

        if (cursor.moveToFirst()) {
            total = cursor.getInt(cursor.getColumnIndex("Total"));
            rowcount = cursor.getInt(cursor.getColumnIndex("Rowcount"));
        }

        percentage = (float) total / (float) rowcount;

        return percentage;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username =?", new String[]{username});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }


    public Boolean checkusernamePassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username =? and password=?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public long getmobno(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        long mobileno = 0;
        float percentage;
        Cursor cursor = db.rawQuery("SELECT mobno as Mobilenumber from users WHERE username = ?", new String[]{name});

        if (cursor.moveToFirst()) {

            mobileno = cursor.getLong(cursor.getColumnIndex("Mobilenumber"));
            System.out.println("inside getob" + mobileno);
        }
        return mobileno;
    }

    public void Setusername(String user) {
        this.user = user;
    }

    public String getusername() {
        return user;
    }
}
