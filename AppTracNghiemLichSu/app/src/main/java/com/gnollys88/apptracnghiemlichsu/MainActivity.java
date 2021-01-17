package com.gnollys88.apptracnghiemlichsu;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gnollys88.apptracnghiemlichsu.question.DBHelper;
import com.gnollys88.apptracnghiemlichsu.slide.BangVangActivity;
import com.gnollys88.apptracnghiemlichsu.slide.GioiThieuActivity;
import com.gnollys88.apptracnghiemlichsu.slide.KetQuaActivity;
import com.gnollys88.apptracnghiemlichsu.slide.LuyenTapActivity;
import com.gnollys88.apptracnghiemlichsu.slide.MoDauLuyenTapActivity;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
Button btntrainghiem,btnthoat,btnbangvang,btngioithieu;
public Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trainghiem();
        thoat();
        bangvang();
//        gioithieu();
    }

//    private void gioithieu() {
//        btngioithieu=findViewById(R.id.btnthietlap);
//        btngioithieu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, GioiThieuActivity.class);
//                startActivity(i);
//            }
//        });
//    }

    private void bangvang() {
        btnbangvang=findViewById(R.id.btnBangVang);
        btnbangvang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this, BangVangActivity.class);
                startActivity(i);
            }
        });

    }

    private void thoat() {
        btnthoat=findViewById(R.id.btnThoat);
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v7.app.AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("thông báo");
                builder.setMessage("Bạn có muốn thoát hay không ?");
                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        Toast.makeText(MainActivity.this, "ây da tuổi trẻ bây giờ thật là !!!", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();
            }
        });
    }





    private void trainghiem() {
        btntrainghiem=findViewById(R.id.btnLuyenTap);
        btntrainghiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MoDauLuyenTapActivity.class);
                startActivity(i);

            }
        });
    }


}
