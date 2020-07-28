package com.gnollys88.apptracnghiemlichsu.slide;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


import com.gnollys88.apptracnghiemlichsu.MainActivity;
import com.gnollys88.apptracnghiemlichsu.R;
import com.gnollys88.apptracnghiemlichsu.fragment.ScreenSlidePageFragment;
import com.gnollys88.apptracnghiemlichsu.question.DBHelper;
import com.gnollys88.apptracnghiemlichsu.question.Question;
import com.gnollys88.apptracnghiemlichsu.question.QuestionController;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class LuyenTapActivity extends FragmentActivity {

    TextView tvkiemtra,tvTimer,tvXemDiem;
    public int checkAns=0;
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 20;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */

    private PagerAdapter mpagerAdapter;
    QuestionController questionController;
    ArrayList<Question> arr_Ques;
    Button btnthoatgiua,btnhuygiua;
    CounterClass timer;
    int thoigianguyuoc;
    String nhom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luyen_tap);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager_luyenTap);
        mpagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mpagerAdapter);
        mPager.setPageTransformer(true, new DepthPageTransformer());
        questionController =new QuestionController(this);
        arr_Ques = new ArrayList<Question>();
        // get data

        Intent i=getIntent();
        nhom=i.getStringExtra("nhom");
        arr_Ques = questionController.getQuestion(nhom);
        tvkiemtra=findViewById(R.id.tvKiemTra);
        tvXemDiem=findViewById(R.id.tvScore);
        tvTimer=findViewById(R.id.tvTimer);
        thoigianguyuoc=10;
        timer=new CounterClass(thoigianguyuoc*60*1000,1000);

        tvkiemtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        tvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        timer.start();

        QuestionController questionController = new QuestionController(this);
        tvXemDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LuyenTapActivity.this,KetQuaActivity.class);
                intent.putExtra("arr_Ques",arr_Ques);

                startActivity(intent);
            }
        });


    }


    public ArrayList<Question> getData(){
        return arr_Ques;
    }
    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {

            // If <></>he user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
            dialogexit();
        }
    }



    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ScreenSlidePageFragment.create(position,checkAns);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1f);
                view.setTranslationX(0f);
                view.setScaleX(1f);
                view.setScaleY(1f);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }

    public void checkAnswer(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.check_answer_dialog);
        dialog.setTitle("Danh sách câu trả lời");

        CheckAnswerAdapter answerAdapter = new CheckAnswerAdapter(arr_Ques, this);
        GridView gvLsQuestion = dialog.findViewById(R.id.gvLsQuestion);
        gvLsQuestion.setAdapter(answerAdapter);

        gvLsQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPager.setCurrentItem(position);
                dialog.dismiss();
            }
        });

        Button btnCancle, btnFinish;
        btnCancle = dialog.findViewById(R.id.btnCancle);
        btnFinish = dialog.findViewById(R.id.btnFinish);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();



            }
        });
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /////
                timer.cancel();

                dialog.dismiss();
                Result();

            }
        });

        dialog.show();
    }

    public void Result(){
       tvXemDiem.setVisibility(View.VISIBLE);
       tvkiemtra.setVisibility(View.GONE);
       checkAns=1;
       if(mPager.getCurrentItem()>=5) mPager.setCurrentItem(mPager.getCurrentItem()-4);
       else if(mPager.getCurrentItem()<5) mPager.setCurrentItem(mPager.getCurrentItem()+4);
    }



    public class CounterClass extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */


        //millisInFuture: 60*1000
        //countDownInterval:  1000
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            tvTimer.setText(countTime); //SetText cho textview hiện thị thời gian.
        }

        @Override
        public void onFinish() {
            tvTimer.setText("00:00");  //SetText cho textview hiện thị thời gian.

        }
    }
    public void dialogexit(){

        AlertDialog.Builder builder= new AlertDialog.Builder(LuyenTapActivity.this);
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
                finish();

            }
        });
        builder.show();
    }

}

