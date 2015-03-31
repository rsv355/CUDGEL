package com.example.android.cudgel.ui.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.cudgel.R;
import com.example.android.cudgel.ui.base.QuestionDetails;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.pixplicity.easyprefs.library.Prefs;

import net.qiujuer.genius.widget.GeniusButton;

import java.util.ArrayList;
import java.util.List;


public class CustomResultDialogActivity extends ActionBarActivity {

    TextView txtTotQuestion,txtQuestAns,txtQuestUnAns,txtQuestCorrAns,txtQuestWronAns,txttestID,txtdate;
    int pos;
    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);

        txttestID  = (TextView)findViewById(R.id.txttestID);
        txtdate  = (TextView)findViewById(R.id.txtdate);
        img  = (ImageView)findViewById(R.id.imgIcon);


        txtTotQuestion  = (TextView)findViewById(R.id.txtTotQuestion);
       // txtQuestAns = (TextView)findViewById(R.id.txtQuestAns);
       // txtQuestUnAns = (TextView)findViewById(R.id.txtQuestUnAns);
        txtQuestCorrAns = (TextView)findViewById(R.id.txtQuestCorrAns);
        txtQuestWronAns = (TextView)findViewById(R.id.txtQuestWronAns);

        pos=getIntent().getIntExtra("pos",0);


        txttestID.setText(""+ResultActivity.resultList.get(pos).Test_id);
        txtdate.setText(""+ResultActivity.resultList.get(pos).Test_date);


        txtTotQuestion.setText(""+ResultActivity.resultList.get(pos).Total_ques);
      //  txtQuestAns.setText(""+ResultActivity.resultList.get(pos).Answered);
      //  txtQuestUnAns.setText(""+ResultActivity.resultList.get(pos).UnAnswered);
        txtQuestCorrAns.setText(""+ResultActivity.resultList.get(pos).Correct_ques);
        txtQuestWronAns.setText(""+ResultActivity.resultList.get(pos).Wrong_ques);

        if(ResultActivity.resultList.get(pos).Result.trim().equalsIgnoreCase("Pass")){
            img.setImageResource(R.drawable.icon_small_pass);
        }
        else{
            img.setImageResource(R.drawable.icon_small_fail);
        }
    }





    //end of min class
}