package com.example.android.cudgel.ui.ui;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import com.example.android.cudgel.R;
import com.example.android.cudgel.ui.base.QuestionDetails;

import net.qiujuer.genius.widget.GeniusButton;
import net.qiujuer.genius.widget.GeniusEditText;
import java.util.ArrayList;


public class BloodDonorActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private GeniusButton btnSend;
    ProgressDialog dialog;
    public static int i;
    private EditText etPassword;
    private EditText etTestid;
    private ListView listview;
    private TextView txtCounter;
    private QuestionDetails ques_d;
    public static ArrayList<QuestionDetails> Ques_det;
    int tempParseSize=0;
    GeniusEditText etName,etEmail,etFeedback;

    final String username = "softeng.krishna@gmail.com";
    final String password = "rinkuamit";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#3D3427"));
        toolbar.setNavigationIcon(R.drawable.icon_back);

              if (toolbar != null) {
            toolbar.setTitle("Blood Donor");
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









    void initView(){

}

    @Override
    protected void onResume() {
        super.onResume();

    }

    //end of min class
}