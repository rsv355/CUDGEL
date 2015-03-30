package com.example.android.cudgel.ui.ui;

import com.example.android.cudgel.R;
import com.example.android.cudgel.ui.base.CircleMenuLayout;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.pixplicity.easyprefs.library.Prefs;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;



public class SplashActivity extends Activity {

     FrameLayout fram_lay;
    ProgressDialog dialog;
    private CircleMenuLayout mCircleMenuLayout;

    private String[] mItemTexts = new String[] { "Blood Bank", "Result", "Settings",
            "Reports", "Share App", "About Us" };
    private int[] mItemImgs = new int[] { R.drawable.icon_feedback,
            R.drawable.icon_result, R.drawable.icon_setting,
            R.drawable.icon_reports, R.drawable.icon_share,
            R.drawable.icon_aboutus };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

         final View contentView = findViewById(R.id.fullscreen_content);
        fram_lay =(FrameLayout) findViewById(R.id.fram_lay);



        StartAnimations();

/*
        CountDownTimer countDownTimer;
        countDownTimer = new MyCountDownTimer(3000, 1000); // 1000 = 1s
        countDownTimer.start();*/

        mCircleMenuLayout = (CircleMenuLayout) findViewById(R.id.id_menulayout);
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);
        mCircleMenuLayout.setOnMenuItemClickListener(new CircleMenuLayout.OnMenuItemClickListener()
        {

            @Override
            public void itemClick(View view, int pos)
            {

                switch (pos){
                    case 0:
                        Intent i0 = new Intent(SplashActivity.this,FeedbackActivity.class);
                        startActivity(i0);
                        break;
                    case 1:
                        Intent i = new Intent(SplashActivity.this,ResultActivity.class);
                        startActivity(i);

                        break;
                    case 2:
                        Intent i2 = new Intent(SplashActivity.this,SettingsActivity.class);
                        startActivity(i2);
                        break;
                    case 3:
                        Intent i3 = new Intent(SplashActivity.this,ReportsActivity.class);
                        startActivity(i3);
                        break;
                    case 4:

                        String text="Please Check out this amazing Quiz app, \n https://play.google.com/store/apps/details?id=com.quizup.core";

                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, text);
                       // sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
                        startActivity(Intent.createChooser(sharingIntent, "Share using"));

                        break;

                    case 5:
                        Intent i5 = new Intent(SplashActivity.this,AboutusActivity.class);
                        startActivity(i5);
                        break;
                }


            }

            @Override
            public void itemCenterClick(View view)
            {
               /* Toast.makeText(SplashActivity.this,
                        "you can do something just like ccb  ",
                        Toast.LENGTH_SHORT).show();*/
                Intent i = new Intent(SplashActivity.this,StartTestActivity.class);
                startActivity(i);

            }
        });

    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        RelativeLayout l=(RelativeLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);
         anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        TextView tx = (TextView)findViewById(R.id.fullscreen_content);
        TextView download = (TextView)findViewById(R.id.downloading);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/waltograph.ttf");
        tx.setTypeface(custom_font);
        download.setTypeface(custom_font);
        tx.setText("CUDGEL");
        tx.clearAnimation();
        tx.startAnimation(anim);


        fetchQuestionMaster();

    }

    private void StartAnimations2() {


        Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate2);
        anim.reset();
        TextView tx1 = (TextView)findViewById(R.id.fullscreen_content);
        tx1.setVisibility(View.GONE);

        TextView download = (TextView)findViewById(R.id.downloading);
        download.setVisibility(View.GONE);


        TextView tx = (TextView)findViewById(R.id.fullscreen_content2);
        tx.setVisibility(View.VISIBLE);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/waltograph.ttf");
        tx.setTypeface(custom_font);
        tx.setText("CUDGEL");
        tx.clearAnimation();
        tx.startAnimation(anim);


        CountDownTimer countDownTimer;
        countDownTimer = new MyCountDownTimer2(2100, 1000); // 1000 = 1s
        countDownTimer.start();


    }

    public class MyCountDownTimer2 extends CountDownTimer {

        public MyCountDownTimer2(long startTime, long interval) {
            super(startTime, interval);
        }
        @Override
        public void onFinish() {
            Log.e("counter", "Time's up!");
            fram_lay.setVisibility(View.VISIBLE);
            //  getActivity().finish();
//            FragmentManager manager = getActivity().getSupportFragmentManager();
//            FragmentTransaction ft = manager.beginTransaction();
//            ft.setCustomAnimations(R.anim.entry, R.anim.exit,R.anim.entry, R.anim.exit);
//            ft.replace(R.id.payment_fragment, new FragmentHome(), "payment_home");
//            ft.commit();
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

    }
    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }
        @Override
        public void onFinish() {
            Log.e("counter", "Time's up!");
            StartAnimations2();

        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

    }

private void processStart2(){

    CountDownTimer countDownTimer;
    countDownTimer = new MyCountDownTimer(2100, 1000); // 1000 = 1s
    countDownTimer.start();
}


  private void fetchQuestionMaster(){
     //   dialog = ProgressDialog.show(SplashActivity.this, "Please Wait", "Fetching data from server..", true);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Question_master");
        query.whereEqualTo("IsActive", true);
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {

        //        dialog.dismiss();

                if (e == null) {
                    Log.e("size of list", String.valueOf(parseObjects.size()));
                    //Toast.makeText(StartTestActivity.this, String.valueOf(parseObjects.get(i).get("Test_id")), Toast.LENGTH_LONG).show();
                    Prefs.putString("TestID", String.valueOf(parseObjects.get(0).get("Test_id")));
                    Prefs.putString("Password",String.valueOf(parseObjects.get(0).get("Password")));

                    Prefs.putString("Time_text", String.valueOf(parseObjects.get(0).get("Time_text")));
                    Prefs.putString("Time_image",String.valueOf(parseObjects.get(0).get("Time_image")));
                    Prefs.putString("Time_audio",String.valueOf(parseObjects.get(0).get("Time_audio")));
                    Prefs.putString("Tot_Question",String.valueOf(parseObjects.get(0).get("Tot_Question")));

                    processStart2();


                } else {
                    Log.e("size of exception", e.getMessage());
                    Toast.makeText(SplashActivity.this, "Error While downloading the data !!!", Toast.LENGTH_LONG).show();
                }
            }

        });
    }


}
