package com.example.android.cudgel.ui.model;

/**
 * Created by Android on 16-03-2015.
 */
public class CurrentTest {

    public String Test_id;
    public String Test_date;
    public String Result;
    public int Total_ques;
    public int Answered;
    public int UnAnswered;
    public int Correct_ques;
    public int Wrong_ques;


    public String optionSelect;

    public void setValues(String val1,String val2,String val3,int val4,int val5,int val6,int val7,int val8){
        this.Test_id = val1;
        this.Test_date = val2;
        this.Result = val3;
        this.Total_ques = val4;
        this.Correct_ques = val5;
        this.Wrong_ques = val6;
        this.Answered = val7;
        this.UnAnswered = val8;
    }

    public void setOptionSelect(String value){
        this.optionSelect=value;
    }
    public String getOptionSelect( ){
        return this.optionSelect;
    }

}
