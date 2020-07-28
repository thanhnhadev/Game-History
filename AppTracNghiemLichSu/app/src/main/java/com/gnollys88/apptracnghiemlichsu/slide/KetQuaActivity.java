package com.gnollys88.apptracnghiemlichsu.slide;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gnollys88.apptracnghiemlichsu.MainActivity;
import com.gnollys88.apptracnghiemlichsu.R;
import com.gnollys88.apptracnghiemlichsu.question.Question;
import com.gnollys88.apptracnghiemlichsu.Controler.ScoreController;

import java.util.ArrayList;

public class KetQuaActivity extends AppCompatActivity {
    ArrayList<Question> arr_bandau=new ArrayList<Question>();
    TextView tvdung,tvsai,tvtong;
    Button btnluu,btnthoat;

    int dung=-1;
    ScoreController scoreController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua);
        init();
        click();
        scoreController= new ScoreController(KetQuaActivity.this);
        Intent intent= getIntent();
        arr_bandau= (ArrayList<Question>) intent.getExtras().getSerializable("arr_Ques");
        checkketqua();
        tvdung.setText(dung+" câu");
        tvsai.setText((20-dung)+ " câu");
        tvtong.setText(dung*10+"");
    }
    private void click() {
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(KetQuaActivity.this);
                builder.setIcon(R.drawable.exit);
                builder.setTitle("thông báo");
                builder.setMessage("Bạn có muốn thoát mà không lưu hay không ?");
                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(KetQuaActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(KetQuaActivity.this, "Thoát mà không lưu, m ngượng ngùng cm j à ??", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(KetQuaActivity.this);
                LayoutInflater inflater=KetQuaActivity.this.getLayoutInflater();
                View view = inflater.inflate(R.layout.allert_dialog_save_score,null);
                builder.setView(view);

                final EditText edtName= (EditText) view.findViewById(R.id.edtName);

                TextView tvScore= (TextView) view.findViewById(R.id.tvScore);
                final int numTotal= dung*10;
                tvScore.setText(numTotal+" điểm");

                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name= edtName.getText().toString();

                        scoreController.insertScore(name,numTotal);
                        Toast.makeText(KetQuaActivity.this, "Lưu điểm thành công!",Toast.LENGTH_LONG).show();
                        finish();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog b= builder.create();
                b.show();
            }
        });
    }
    private void init() {
        tvdung=findViewById(R.id.tvdung);
        tvsai=findViewById(R.id.tvsai);
        btnluu=findViewById(R.id.btnluu);
        tvtong=findViewById(R.id.tvtong);
        btnthoat=findViewById(R.id.btnthoatluu);
    }
    public void checkketqua(){
        for(int i=0;i<arr_bandau.size();i++){

       if(   arr_bandau.get(i).getResult().equals(arr_bandau.get(i).getTraloi())==true)
                dung++;


        }
    }
}
