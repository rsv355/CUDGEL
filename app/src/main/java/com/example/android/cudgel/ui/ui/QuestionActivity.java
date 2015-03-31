package com.example.android.cudgel.ui.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.cudgel.R;
import com.example.android.cudgel.ui.base.DBAdapter;
import com.example.android.cudgel.ui.base.QuestionDetails;
import com.example.android.cudgel.ui.model.CurrentTest;
import com.filippudak.ProgressPieView.ProgressPieView;
import com.pixplicity.easyprefs.library.Prefs;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class QuestionActivity extends ActionBarActivity {
    private TextView txtSecond,txtMinute,txtHour;
    private Toolbar toolbar;

    ProgressDialog dialog;

    net.qiujuer.genius.widget.GeniusButton btnNext,btnPrevious;
    private ArrayList<QuestionDetails> Ques_det;
    int counter=0;
    net.qiujuer.genius.widget.GeniusCheckBox optA,optB,optC,optD;

    public static ArrayList<String> selectoption=new ArrayList<String>();

    String selectedOption="NA";
    DBAdapter db;

    CurrentTest obj;
    TextView txtQuestion,txtOptA,txtOptB,txtOptC,txtOptD,txtno;

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(QuestionActivity.this);
        alertDialog.setTitle("Warning");

        // Setting Dialog Message
        alertDialog.setMessage("Would you like to cancel this test ?\nCurrent test data will be lost!!!");

        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

                Intent i = new Intent(QuestionActivity.this,StartTestActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);


            }
        });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event

            }
        });

        // Showing Alert Message
        alertDialog.show();




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        db= new DBAdapter(QuestionActivity.this);
        obj = new CurrentTest();

        btnPrevious = (net.qiujuer.genius.widget.GeniusButton)findViewById(R.id.btnPrevious);
      //  pieView = (com.filippudak.ProgressPieView.ProgressPieView) findViewById(R.id.progressPieViewXml);

        btnNext = (net.qiujuer.genius.widget.GeniusButton)findViewById(R.id.btnNext);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#3D3427"));
        toolbar.setNavigationIcon(R.drawable.icon_back);

        txtSecond= (TextView) findViewById(R.id.txtSecond);
        txtMinute= (TextView) findViewById(R.id.txtMinute);
        txtHour= (TextView) findViewById(R.id.txtHour);

         txtno = (TextView) findViewById(R.id.txtQno);
         txtQuestion = (TextView) findViewById(R.id.txtQuestion);
         txtOptA = (TextView) findViewById(R.id.txtOptA);
         txtOptB = (TextView) findViewById(R.id.txtOptB);
         txtOptC = (TextView) findViewById(R.id.txtOptC);
         txtOptD = (TextView) findViewById(R.id.txtOptD);

        optA = (net.qiujuer.genius.widget.GeniusCheckBox) findViewById(R.id.a);
        optB = (net.qiujuer.genius.widget.GeniusCheckBox) findViewById(R.id.b);
        optC = (net.qiujuer.genius.widget.GeniusCheckBox) findViewById(R.id.c);
        optD = (net.qiujuer.genius.widget.GeniusCheckBox) findViewById(R.id.d);



        if (toolbar != null) {
            toolbar.setTitle("CUDGEL");
            setSupportActionBar(toolbar);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(QuestionActivity.this);
                alertDialog.setTitle("Warning");

                // Setting Dialog Message
                alertDialog.setMessage("Would you like to cancel this test ?\nCurrent test data will be lost!!!");

                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {

                        Intent i = new Intent(QuestionActivity.this,StartTestActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);


                    }
                });

                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event

                    }
                });

                // Showing Alert Message
                alertDialog.show();
            }
        });




        setclock();
        setupQuestion(counter);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter+=1;
                if(counter>=StartTestActivity.Ques_det.size()){
                    counter=StartTestActivity.Ques_det.size()-1;

                }else {
                    setupQuestion(counter);
                }

            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter-=1;
                if(counter<0){
                    counter=0;
                    Toast.makeText(QuestionActivity.this,"You are at the first Question",Toast.LENGTH_LONG).show();
                }else {

                    setupQuestion(counter);
                }
            }
        });


    }

 private void setupQuestion(int counter){
     if(counter==0){
            btnPrevious.setVisibility(View.GONE);
            btnNext.setVisibility(View.VISIBLE);
       }

     else{
         btnPrevious.setVisibility(View.VISIBLE);
         btnNext.setVisibility(View.VISIBLE);
     }

     txtQuestion.setText(StartTestActivity.Ques_det.get(counter).Question.trim());
     txtno.setText(""+(counter+1));
     txtOptA.setText(StartTestActivity.Ques_det.get(counter).optA.trim());
     txtOptB.setText(StartTestActivity.Ques_det.get(counter).optB.trim());
     txtOptC.setText(StartTestActivity.Ques_det.get(counter).optC.trim());
     txtOptD.setText(StartTestActivity.Ques_det.get(counter).optD.trim());
 }






private void setclock(){
//3600000
        new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                txtSecond.setText(String.format("%02d", ((millisUntilFinished/1000) % 60)));
                txtMinute.setText(String.format("%02d", ((millisUntilFinished/1000) % 3600) / 60));
                txtHour.setText(String.format("%02d", (millisUntilFinished/1000) / 3600));
            }
            public void onFinish() {
                Toast.makeText(QuestionActivity.this,"Questions are finished :)",Toast.LENGTH_LONG).show();
                Intent i = new Intent(QuestionActivity.this,FinishActivity.class);
                startActivity(i);
                finish();
            }
        }.start();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }



    //end of min class
}