package com.example.android.cudgel.ui.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.cudgel.R;

public class StartMain extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_main);

        TextView tt1= (TextView)findViewById(R.id.tt1);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/waltograph.ttf");
        tt1.setTypeface(custom_font);

        tt1.setText("CUDGEL");
    }


    @Override
    protected void onResume() {
        super.onResume();

        if(checkInternet()){
            CountDownTimer countDownTimer;
            countDownTimer = new MyCountDownTimer2(4000, 5000); // 1000 = 1s
            countDownTimer.start();
        }
        else{

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(StartMain.this);
            alertDialog.setTitle("CUDGEL");

            // Setting Dialog Message
            alertDialog.setMessage("Please Connect your Internet !!!");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int which) {


                }
            });

            // Showing Alert Message
            alertDialog.show();

        }

    }

    private boolean checkInternet(){
  ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(StartMain.this.CONNECTIVITY_SERVICE);
    if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
        //we are connected to a network
        // Toast.makeText(SplashActivity.this,"Internet is connected",Toast.LENGTH_SHORT).show();
        return true;
    } else {
        return false;
    }


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

     }

        @Override
        public void onTick(long millisUntilFinished) {

        }

    }




}
