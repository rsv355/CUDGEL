package com.example.android.cudgel.ui.base;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android on 05-02-2015.
 */
public class QuestionDetails {

    public QuestionDetails(){

    }
    @SerializedName("Test_id")
    public String Test_id;

    @SerializedName("Q_id")
    public String Q_id;

    @SerializedName("Q_type")
    public String Q_type;

    @SerializedName("optA")
    public String optA;
    @SerializedName("optB")
    public String optB;
    @SerializedName("optC")
    public String optC;
    @SerializedName("optD")
    public String optD;

    @SerializedName("Correct_opt")
    public String Correct_opt;

    @SerializedName("Q_audio_url")
    public String Q_audio_url;

    @SerializedName("Q_image_url")
    public String Q_image_url;

    @SerializedName("Question")
    public String Question;



    public QuestionDetails(QuestionDetails obj){

     this.Test_id=obj.Test_id;

     this.Q_id=obj.Q_id;

     this.Q_type=obj.Q_type;

     this.optA=obj.optA;

     this.optB=obj.optB;

     this.optC=obj.optC;

     this.optD=obj.optD;

     this.Correct_opt=obj.Correct_opt;

     this.Q_audio_url=obj.Q_audio_url;

     this.Q_image_url=obj.Q_image_url;

     this.Question=obj.Question;

    }

}
