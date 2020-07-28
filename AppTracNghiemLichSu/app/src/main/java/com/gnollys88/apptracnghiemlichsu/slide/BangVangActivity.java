package com.gnollys88.apptracnghiemlichsu.slide;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.gnollys88.apptracnghiemlichsu.R;
import com.gnollys88.apptracnghiemlichsu.Adapter.ScoreAdapter;
import com.gnollys88.apptracnghiemlichsu.Controler.ScoreController;

public class BangVangActivity extends AppCompatActivity {
    ListView lvScore;
    ScoreController scoreController;
    ScoreAdapter scoreAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bangvangactivity);
        init();
        scoreController=new ScoreController(BangVangActivity.this);

        Cursor cursor=scoreController.getScore();
        scoreAdapter=new ScoreAdapter(BangVangActivity.this,cursor,true);
        lvScore.setAdapter(scoreAdapter);
    }

    private void init() {
        lvScore=findViewById(R.id.lvScore);
    }

}
