package com.gnollys88.apptracnghiemlichsu.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gnollys88.apptracnghiemlichsu.R;
import com.gnollys88.apptracnghiemlichsu.question.Question;
import com.gnollys88.apptracnghiemlichsu.question.QuestionController;
import com.gnollys88.apptracnghiemlichsu.slide.LuyenTapActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenSlidePageFragment extends Fragment {

    ArrayList<Question> arr_Ques;
    public static final String ARG_PAGE ="page";
    public static final String ARG_CHECKANSWER ="checkAnswer";
    public int checkans;//bien kiem tra

    TextView tvNum,tvQuestion;
    RadioGroup radioGroup;
    RadioButton radA,radB,radC,radD;
    private int mPageNumber;//vi tri trang hien tai
    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);

        tvNum=(TextView)rootView.findViewById(R.id.tvNum);
        tvQuestion=(TextView)rootView.findViewById(R.id.tvQuestion);
        radA=(RadioButton)rootView.findViewById(R.id.radA);
        radB=(RadioButton)rootView.findViewById(R.id.radB);
        radC=(RadioButton)rootView.findViewById(R.id.radC);
        radD=(RadioButton)rootView.findViewById(R.id.radD);
        radioGroup=(RadioGroup)rootView.findViewById(R.id.radGroup);

        int i = getArguments().getInt(ARG_PAGE);

        Log.i("dulieu", "onCreateView: " + i);

        // GET QUESTION BY ID THEN SET ON FRAGMENT

        QuestionController controller = new QuestionController(getActivity());
        Question question = controller.getQuestionById(i);



        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        arr_Ques=new ArrayList<Question>();

        LuyenTapActivity luyenTapActivity= (LuyenTapActivity) getActivity();
         arr_Ques= luyenTapActivity.getData();
        mPageNumber=getArguments().getInt(ARG_PAGE);//gui~ di la phai~ lay vr
        checkans=getArguments().getInt(ARG_CHECKANSWER);

    }
    public static ScreenSlidePageFragment create(int pageNumber,int checkAnswer){
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        args.putInt(ARG_CHECKANSWER,checkAnswer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

         tvNum.setText("câu"+ (mPageNumber+1));
         tvQuestion.setText(arr_Ques.get(mPageNumber).getQuestions());
         radA.setText(getItem(mPageNumber).getAns_a());
        radB.setText(getItem(mPageNumber).getAns_b());
        radC.setText(getItem(mPageNumber).getAns_c());
        radD.setText(getItem(mPageNumber).getAns_d());
        if(checkans!=0){
            radA.setClickable(false);
            radB.setClickable(false);
            radC.setClickable(false);
            radD.setClickable(false);
            getcheckAns(getItem(mPageNumber).getResult().toString());

        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                getItem(mPageNumber).choiceID=checkedId;
                getItem(mPageNumber).setTraloi(geChoiceFromID(checkedId));
//                Toast.makeText(getActivity(), "dap an ne"+checkedId, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public Question getItem(int position){
        return arr_Ques.get(position);
    }
    public String geChoiceFromID(int ID){
        if(ID==R.id.radA){
            return "A";
        }else  if(ID==R.id.radB){
            return "B";
        }else  if(ID==R.id.radC){
            return "C";
        }else  if(ID==R.id.radD){
            return "D";
        }else return "";

    }
    private void getcheckAns(String ans){
        if (ans.equals("A")==true){
            radA.setBackgroundColor(Color.YELLOW);
        }else if (ans.equals("B")==true) {
            radB.setBackgroundColor(Color.YELLOW);
        }else if (ans.equals("C")==true){
            radC.setBackgroundColor(Color.YELLOW);
        }else if (ans.equals("D")==true){
            radD.setBackgroundColor(Color.YELLOW);
        } ;
    }//hamf kiểm tra câu đúng, nếu đuungs đổi mau background  radio button tương ứng
}