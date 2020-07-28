package com.gnollys88.apptracnghiemlichsu.question;

import java.io.Serializable;

public class Question implements Serializable {
    private int id;
    private String questions;
    private String ans_a;
    private String ans_b;
    private String ans_c;
    private String ans_d;
    private String result;
    private String image;
    private String nhom;
    private String infomation;
    private String traloi="";
    public  int choiceID =-1;//ho tro chon dap an abcd



    public String getTraloi() {return traloi;
    }
    public void setTraloi(String traloi) {
        this.traloi = traloi;
    }

    public Question(int id, String questions, String ans_a, String ans_b, String ans_c, String ans_d, String result, String image, String nhom, String infomation, String traloi) {
        this.id = id;
        this.questions = questions;
        this.ans_a = ans_a;
        this.ans_b = ans_b;
        this.ans_c = ans_c;
        this.ans_d = ans_d;
        this.result = result;
        this.image = image;
        this.nhom = nhom;
        this.infomation = infomation;
        this.traloi = traloi;
    }

    public Question() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getAns_a() {
        return ans_a;
    }

    public void setAns_a(String ans_a) {
        this.ans_a = ans_a;
    }

    public String getAns_b() {
        return ans_b;
    }

    public void setAns_b(String ans_b) {
        this.ans_b = ans_b;
    }

    public String getAns_c() {
        return ans_c;
    }

    public void setAns_c(String ans_c) {
        this.ans_c = ans_c;
    }

    public String getAns_d() {
        return ans_d;
    }

    public void setAns_d(String ans_d) {
        this.ans_d = ans_d;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNhom() {
        return nhom;
    }

    public void setNhom(String nhom) {
        this.nhom = nhom;
    }

    public String getInfomation() {
        return infomation;
    }

    public void setInfomation(String infomation) {
        this.infomation = infomation;
    }
}