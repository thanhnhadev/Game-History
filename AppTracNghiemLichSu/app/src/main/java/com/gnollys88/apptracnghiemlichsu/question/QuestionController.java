package com.gnollys88.apptracnghiemlichsu.question;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gnollys88.apptracnghiemlichsu.question.DBHelper;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by tuanninh1307 on 11/12/2016.
 */

public class QuestionController {

    private DBHelper dbHelper;
    SQLiteDatabase db;

    public QuestionController(Context context) {

        dbHelper= new DBHelper(context);
    }

    //Lấy danh sách câu hỏi
    public ArrayList<Question> getQuestion(String nhom){
        ArrayList<Question> lsData= new ArrayList<Question>();

        db = dbHelper.getReadableDatabase();
        Cursor cursor =db.rawQuery("select * from cauhoi where nhom ='"+nhom+"' ORDER BY random()",null);

        if(cursor.moveToFirst()){
            do {
                Question item;
                item= new Question(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),"");

                lsData.add(item);
             //   Log.i("dulieu", "getQuestion: " + item.getQuestions());
            }while (cursor.moveToNext());
        }

        return lsData;
    }


    public Question getQuestionById(int i) {
        /*db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select*from cauhoi where id=?",new String[]{i+""});
        Question question = null;
        if(cursor.moveToFirst()){
            question = new Question(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9)
            );
        }
        return question;*/

        /*ArrayList<Question> lsData= new ArrayList<Question>();*/
        Question item = null;
        db = dbHelper.getReadableDatabase();
        Cursor cursor =db.rawQuery("select * from cauhoi where id = '"+ i+"'",null);

        if(cursor.moveToFirst()){
            do {
                
                item= new Question(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),"");

               /* lsData.add(item);*/
                //   Log.i("dulieu", "getQuestion: " + item.getQuestions());
            }while (cursor.moveToNext());
        }

        return item;
    }
}
