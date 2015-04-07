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


public class OverViewActivity extends ActionBarActivity {
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
        setContentView(R.layout.activity_overview);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#3D3427"));
        toolbar.setNavigationIcon(R.drawable.icon_back);

              if (toolbar != null) {
            toolbar.setTitle("OverView");
            setSupportActionBar(toolbar);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        TextView txt1 = (TextView)findViewById(R.id.txt1);



        txt1.setText("Sona College of Technology is committed towards social and national responsibilities; bearing this in mind we have raised the NCC Signal Coy for boys and army wing for girls from 2004-05 onwards. We believe that, an individual will come to possess an adoring personality only by involving himself / herself in activities like NCC, NSS etc.\n" +
                "\n\n" +
                "Sona College of Technology is equipped with several infrastructure facilities like short-range firing, obstacle courses for NCC training programme. The NCC programme provides ample opportunities to the cadets by way of conducting firing camps, drill practices, personality development and voluntary service programmes.\n" +
                "\n\n" +
                "The mission and vision of our NCC wing is to create an awareness among the student community regarding its importance towards nation building. Besides, an NCC cadet is presented with a plethora of job opportunities in reputed government and public sector concerns. We intend to train students for participating in Republic Day parade and pupils exchange programme. Sona NCC wing fine tunes the personality of a person in total and mentally equips him to face the various challenges he/she may encounter in his/her life." +
                "\n\n\n\n"+
        "Contact Persons :\n" +
                "\n" +
                "Dr.Lt.R.Vikrama Prasad, M.Sc., M.Phil., Ph.D.,\n" +
                "NCC Officer (Boys Wing), \n" +
                "Assistant Professor,\n" +
                "Department of Mathematics,\n" +
                "Sona College of Technology,\n" +
                "Salem. \n" +
                "Ph. 91-427-2443545, 4099999. Ext. 805");
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