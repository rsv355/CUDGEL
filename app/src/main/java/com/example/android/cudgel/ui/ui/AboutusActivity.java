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
import android.widget.TextView;

import com.example.android.cudgel.R;
import com.example.android.cudgel.ui.base.QuestionDetails;

import net.qiujuer.genius.widget.GeniusButton;

import java.util.ArrayList;


public class AboutusActivity extends ActionBarActivity {
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
        setContentView(R.layout.activity_aboutus);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#3D3427"));
        toolbar.setNavigationIcon(R.drawable.icon_back);

              if (toolbar != null) {
            toolbar.setTitle("About Us");
            setSupportActionBar(toolbar);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        TextView txt1 = (TextView)findViewById(R.id.txt1);



        txt1.setText("Envision is a globally renowned IT Services company dealing in Software development, Web development and designing. Being an experienced technical team.\nEnvision has mastered in ERP solutions, e-Commerce and Web based ERP solutions, Industrial Automation software communicating with Micro controllers and PLCs (Data Acquisition, SCADA) software.\n" +
                "\n\n" +
                "Understanding business in a systematic way and providing competitive solutions is passion of Envision team. Being in the industry for more than a decade in development.\nEnvision has expanded its wings to touch unlimited sky of opportunities in IT industry by providing Placement Services and Professional Training Services for Confident Candidates from any field, Engineering students, and existing professional to fine tune their rough edges. ");
        txt1.setSelected(true);


        Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        txt1.clearAnimation();
        txt1.startAnimation(anim);
    }




    @Override
    protected void onResume() {
        super.onResume();

    }

    //end of min class
}