package com.gnollys88.apptracnghiemlichsu.Controler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gnollys88.apptracnghiemlichsu.question.DBHelper;
import com.gnollys88.apptracnghiemlichsu.question.Question;

import java.util.ArrayList;

public class ScoreController {
    private DBHelper dbHelper;

    public ScoreController(Context context) {
        dbHelper= new DBHelper(context);
    }

    public void insertScore(String name, int score){
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put("name", name);
        values.put("score", score);
        db.insert("xephang", null, values);
        db.close();
    }


    //Lấy danh sách điểm
    public Cursor getScore() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("xephang", //ten bang
                null, //danh sach cot can lay
                null, //dieu kien where
                null, //doi so dieu kien where
                null, //bieu thuc groupby
                null, //bieu thuc having
                "score DESC", //bieu thuc orderby
                null
        );
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}