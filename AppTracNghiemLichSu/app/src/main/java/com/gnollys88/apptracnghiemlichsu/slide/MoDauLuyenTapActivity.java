package com.gnollys88.apptracnghiemlichsu.slide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.gnollys88.apptracnghiemlichsu.R;
import com.gnollys88.apptracnghiemlichsu.question.DBHelper;
import com.gnollys88.apptracnghiemlichsu.question.Question;
import com.gnollys88.apptracnghiemlichsu.question.QuestionController;

import java.io.IOException;
import java.util.ArrayList;

public class MoDauLuyenTapActivity extends AppCompatActivity {
Button btnbatdauluyentap,btnquaylailuyentap;
public String a ;
RadioGroup rdgluyentap;
RadioButton rddanhnhan,rdcaunoi;
int lock=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mo_dau_luyen_tap);
        btnbatdauluyentap=findViewById(R.id.btnbatdauluyentap);
        btnquaylailuyentap=findViewById(R.id.btnquaylailuyentap);
        DBHelper db =new DBHelper(this);
        try {
            db.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddConTrols();
       radiangroup();

    }

    private void radiangroup() {

        rdcaunoi=findViewById(R.id.rdcaunoi);
        rddanhnhan=findViewById(R.id.rddanhnhan);
        rdgluyentap=findViewById(R.id.rdgluyentap);
        rdgluyentap.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rddanhnhan){
                  a= "danhnhan" ;
                  lock=1;
                    Toast.makeText(MoDauLuyenTapActivity.this, "bạn chọn chủ đề DANH NHÂN", Toast.LENGTH_SHORT).show();
                }
                else if(checkedId==R.id.rdcaunoi){
                    a = "caunoi" ;
                    lock=1;
                    Toast.makeText(MoDauLuyenTapActivity.this, "bạn chọn chủ đề CÂU NÓI", Toast.LENGTH_SHORT).show();
                }
                else return ;
            }
        });


    }


    private void AddConTrols() {
        btnbatdauluyentap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lock==1){
                    Intent i = new Intent(MoDauLuyenTapActivity.this,LuyenTapActivity.class);
                    i.putExtra("nhom",a);
                    startActivity(i);
                }
                else {
                    Toast.makeText(MoDauLuyenTapActivity.this, "hãy nhập chủ đề bạn muốn chiến", Toast.LENGTH_SHORT).show();
                }   ;

            }
        });
        btnquaylailuyentap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
