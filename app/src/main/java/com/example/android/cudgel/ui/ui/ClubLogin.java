package com.example.android.cudgel.ui.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.cudgel.R;
import com.example.android.cudgel.ui.base.QuestionDetails;

import net.qiujuer.genius.widget.GeniusButton;
import net.qiujuer.genius.widget.GeniusEditText;

import java.util.ArrayList;


public class ClubLogin extends ActionBarActivity {
    private Toolbar toolbar;

    ProgressDialog dialog;

    GeniusEditText etUserid,etPassword;
    GeniusButton btnStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#3D3427"));
        toolbar.setNavigationIcon(R.drawable.icon_back);

              if (toolbar != null) {
            toolbar.setTitle("NCC Club Login");
            setSupportActionBar(toolbar);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etUserid = (GeniusEditText)findViewById(R.id.etUserid);
        etPassword = (GeniusEditText)findViewById(R.id.etPassword);
        btnStart = (GeniusButton)findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etUserid.getText().toString().equalsIgnoreCase("ncc") && etPassword.getText().toString().equalsIgnoreCase("ncc")){
                    Intent i = new Intent(ClubLogin.this,NCC.class);
                    startActivity(i);
                }else{
                    Toast.makeText(ClubLogin.this,"Wrong User id or Password",Toast.LENGTH_LONG).show();
                }



            }
        });



    }




    @Override
    protected void onResume() {
        super.onResume();

    }

    //end of min class
}