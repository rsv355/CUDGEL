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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.cudgel.R;
import com.example.android.cudgel.ui.base.ComplexPreferences;
import com.example.android.cudgel.ui.base.QuestionDetails;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.pixplicity.easyprefs.library.Prefs;

import net.qiujuer.genius.widget.GeniusButton;

import java.util.ArrayList;
import java.util.List;


public class StartTestActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private GeniusButton btnStart;
    ProgressDialog dialog;
    public static int i;
    private EditText etPassword;
    private EditText etTestid;
    private ListView listview;
    private TextView txtCounter;
    private QuestionDetails ques_d;
    public static ArrayList<QuestionDetails> Ques_det;
    int tempParseSize=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starttest);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#3D3427"));
        toolbar.setNavigationIcon(R.drawable.icon_back);

        etTestid = (EditText)findViewById(R.id.etTestid);
        etPassword = (EditText)findViewById(R.id.etPassword);
        txtCounter = (TextView)findViewById(R.id.txtCounter);

        btnStart= (GeniusButton)findViewById(R.id.btnStart);

        if (toolbar != null) {
            toolbar.setTitle("CUDGEL");
            setSupportActionBar(toolbar);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processTest();
            }
        });

    }


    private void processTest()
    {
        String testid = Prefs.getString("TestID", "");
        String password = Prefs.getString("Password", "");

        if (etTestid.getText().toString().trim().length() == 0)
        {
            etTestid.setError("Please Enter TestID !!!");
            return;
        }
        if (etPassword.getText().toString().trim().length() == 0)
        {
            etPassword.setError("Please Enter Password !!!");
            return;
        }

        boolean found=false;

        for(int i=0;i<SplashActivity.qm.size();i++) {
            Log.e("values",SplashActivity.qm.get(i).Test_id.toString());
            if (etTestid.getText().toString().trim().equalsIgnoreCase(SplashActivity.qm.get(i).Test_id.toString()) && etPassword.getText().toString().trim().equals(SplashActivity.qm.get(i).Password.toString())) {
                found=true;
                Prefs.putInt("Time", SplashActivity.qm.get(i).Tot_Time);
                break;

            } else {
                found=false;

            }
        }

        if(found){
            fetchQuestiondetails();
        }else{
            Toast.makeText(this, "Wrong Test id or Password !!!", Toast.LENGTH_LONG).show();
         }





    }

    @Override
    protected void onResume() {
        super.onResume();
        txtCounter.setVisibility(View.GONE);
    }


    private void processStartCounter(){
        CountDownTimer countDownTimer;
        countDownTimer = new MyCountDownTimer(10000, 1000); // 1000 = 1s
        countDownTimer.start();
    }

 public class MyCountDownTimer extends CountDownTimer {

        protected AlphaAnimation fadeIn;
        protected AlphaAnimation fadeOut;

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            txtCounter.setVisibility(View.VISIBLE);
        }

        @Override
        public void onTick(long millisUntilFinished) {
                txtCounter.setText(""+millisUntilFinished/1000);
        }

        @Override
        public void onFinish() {
            Intent intent = new Intent(StartTestActivity.this,QuestionActivity.class);
            startActivity(intent);
        }
    }


    private void fetchQuestiondetails()
    {

        dialog= ProgressDialog.show(this,"Please Wait","downloading data from server",true);
        dialog.setCancelable(false);
        Ques_det = new ArrayList<QuestionDetails>();

        String temp = Prefs.getString("TestID", "");
        ParseQuery parsequery = ParseQuery.getQuery("Question_details");
        parsequery.whereEqualTo("Test_id", etTestid.getText().toString().trim().toUpperCase());
        parsequery.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {

                if (e == null) {
                    Log.e("size of question list", String.valueOf(parseObjects.size()));
                    tempParseSize = parseObjects.size();


                    if(tempParseSize!=0) {


                        for (i = 0; i < parseObjects.size(); i++) {
                            Log.e("quesiotn", String.valueOf(((ParseObject) parseObjects.get(i)).get("Question")));

                            QuestionDetails newobj = new QuestionDetails();

                            newobj.Question = String.valueOf(((ParseObject) parseObjects.get(i)).get("Question"));
                            newobj.Correct_opt = String.valueOf(((ParseObject) parseObjects.get(i)).get("Correct_opt"));

                            newobj.Test_id = String.valueOf(((ParseObject) parseObjects.get(i)).get("Test_id"));
                            newobj.Q_id = String.valueOf(((ParseObject) parseObjects.get(i)).get("Q_id"));

                            newobj.optA = String.valueOf(((ParseObject) parseObjects.get(i)).get("optA"));
                            newobj.optB = String.valueOf(((ParseObject) parseObjects.get(i)).get("optB"));
                            newobj.optC = String.valueOf(((ParseObject) parseObjects.get(i)).get("optC"));
                            newobj.optD = String.valueOf(((ParseObject) parseObjects.get(i)).get("optD"));


                            Ques_det.add(i, newobj);


                        }

                        dialog.dismiss();
                        Intent intent = new Intent(StartTestActivity.this, QuestionActivity.class);
                        startActivity(intent);
                    }else{
                        dialog.dismiss();
                        Toast.makeText(StartTestActivity.this, "Sorry, Admin not added questions for this Test.", Toast.LENGTH_LONG).show();
                    }


                } else {
                    Log.e("size of exception", e.getMessage());
                }
            }

        });





        Log.e("size of questionarra",String.valueOf( Ques_det.size()));
    }

    //end of min class
}