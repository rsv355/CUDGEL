package com.example.android.cudgel.ui.ui;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.cudgel.R;
import com.example.android.cudgel.ui.base.QuestionDetails;

import net.qiujuer.genius.widget.GeniusButton;
import net.qiujuer.genius.widget.GeniusEditText;

import java.util.ArrayList;


public class BloodregisterActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private GeniusButton btnStart;
    private GeniusEditText etblddonorname,etMob,etMob2,etArea,etCity,etState;
    GeniusButton btnSave;
    Spinner spBlood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodregister);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#3D3427"));
        toolbar.setNavigationIcon(R.drawable.icon_back);

     if (toolbar != null) {
            toolbar.setTitle("Blood Bank");
            setSupportActionBar(toolbar);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        initView();



    }

private void initView(){
       etblddonorname = (GeniusEditText) findViewById(R.id.etblddonorname);
       etMob = (GeniusEditText) findViewById(R.id.etMob);
       etMob2 = (GeniusEditText) findViewById(R.id.etMob2);
       etArea = (GeniusEditText) findViewById(R.id.etArea);
       etCity = (GeniusEditText) findViewById(R.id.etCity);
       etState = (GeniusEditText) findViewById(R.id.etState);

    processValidate();
}

    private void processValidate(){
        if(etblddonorname.getText().toString().trim().length()==0){
            etblddonorname.setError("Please Enter Blood donor name !!!");
        }
        else if(etMob.getText().toString().trim().length()==0 || etMob.getText().toString().trim().length() < 10){
            etMob.setError("Please Enter Correct Mobile Number!!!");
        }

        else if(etArea.getText().toString().trim().length()==0){
            etArea.setError("Please Enter Area !!!");
        }
        else if(etCity.getText().toString().trim().length()==0){
            etCity.setError("Please Enter City !!!");
        }
        else if(etState.getText().toString().trim().length()==0){
            etState.setError("Please Enter State !!!");
        }
        else{
            processSave();
        }
    }


    private void processSave(){
        Toast.makeText(BloodregisterActivity.this,"Record saved sucessfully",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    //end of min class
}