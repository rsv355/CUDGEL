package com.example.android.cudgel.ui.ui;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.android.cudgel.R;

public class StartMain extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_main);

        CountDownTimer countDownTimer;
        countDownTimer = new MyCountDownTimer2(5000, 5000); // 1000 = 1s
        countDownTimer.start();
    }


    public class MyCountDownTimer2 extends CountDownTimer {

        public MyCountDownTimer2(long startTime, long interval) {
            super(startTime, interval);
        }
        @Override
        public void onFinish() {
            Intent i = new Intent(StartMain.this,SplashActivity.class);
            startActivity(i);
            finish();

          //  Log.e("counter", "Time's up!");
          //  fram_lay.setVisibility(View.VISIBLE);
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




}
