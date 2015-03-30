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
    private Toolbar toolbar;
    private ListView listview;
    ProgressDialog dialog;
    ImageView img;
    net.qiujuer.genius.widget.GeniusButton btnNext;
    com.filippudak.ProgressPieView.ProgressPieView pieView;
    private ArrayList<QuestionDetails> Ques_det;
    int counter=0;
    net.qiujuer.genius.widget.GeniusCheckBox optA,optB,optC,optD;
    int time_text,time_image,time_audio;
    int finaltime=10;
    public static ArrayList<String> selectoption=new ArrayList<String>();

    String selectedOption="NA";
    DBAdapter db;
    boolean isTestComplete =false;
    CurrentTest obj;


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


        pieView = (com.filippudak.ProgressPieView.ProgressPieView) findViewById(R.id.progressPieViewXml);
        listview = (ListView) findViewById(R.id.listview);
        btnNext = (net.qiujuer.genius.widget.GeniusButton)findViewById(R.id.btnNext);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#3D3427"));
        toolbar.setNavigationIcon(R.drawable.icon_back);


        if (toolbar != null) {
            toolbar.setTitle("Quick Quiz");
            setSupportActionBar(toolbar);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pieView.setProgress(0);
        pieView.setMax(350);
        pieView.animateProgressFill();

        viewdata(StartTestActivity.Ques_det);


        checkQuestionNo(counter);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter+=1;
                if(counter>=StartTestActivity.Ques_det.size()){
                    Log.e("indied if","if");
                    isTestComplete=true;
                    Toast.makeText(QuestionActivity.this,"Wait for time to finish !!!",Toast.LENGTH_LONG).show();
                }else {
                    Log.e("indied else","else");
                    isTestComplete=false;

                    checkQuestionNo(counter);


                }

            }
        });



        pieView.setOnProgressListener(new ProgressPieView.OnProgressListener() {
            @Override
            public void onProgressChanged(int progress, int max) {
                int counter = progress;
                if(progress%35==0){
                    pieView.setTextSize(28);
                    pieView.setShowText(true);
                    pieView.setText(String.valueOf(progress/35));
                }

            }

            @Override
            public void onProgressCompleted() {
//                counter+=1;
//
//                if(counter>=StartTestActivity.Ques_det.size()){
//                    pieView.setTextSize(18);
//                    pieView.setText("Time up");
//
//                    selectoption.add(selectedOption);
//                    Log.e("Select value in time",""+selectedOption);
//
//                    insertRecordinDatabase();


                    Toast.makeText(QuestionActivity.this,"Questions are finished :)",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(QuestionActivity.this,FinishActivity.class);
                    startActivity(i);
                    finish();
               /* }else {
                    checkQuestionNo(counter);


                }*/


            }
        });

        time_text  =  Integer.valueOf(Prefs.getString("Time_text",""));
        time_image =  Integer.valueOf(Prefs.getString("Time_image", ""));
        time_audio =  Integer.valueOf(Prefs.getString("Time_audio", ""));


    }


    public void checkQuestionNo(final int qno ){

        ArrayList<QuestionDetails> newObj = new ArrayList<QuestionDetails>(1);
        newObj.add(StartTestActivity.Ques_det.get(counter));
        viewdata(newObj);
    }

    @Override
    protected void onResume() {
        super.onResume();
        counter=0;
    }


public void insertRecordinDatabase(){
int total_answerd=0;
int total_Unanswerd=0;
int total_correct_answer=0;
int total_wrong_answer=0;

    for(int i=0;i<StartTestActivity.Ques_det.size();i++){
  /*   Log.e("corect answer "+i,""+StartTestActivity.Ques_det.get(i).Correct_opt);
     Log.e("selection value "+i,""+selectoption.get(i));
*/
        if(selectoption.get(i+1).equalsIgnoreCase("NA")){

            total_Unanswerd++;
            total_wrong_answer++;
        }
        else{
            total_answerd++;
            if(selectoption.get(i+1).equalsIgnoreCase(StartTestActivity.Ques_det.get(i).Correct_opt)){
                total_correct_answer++;
            }
            else{
                total_wrong_answer++;
            }
        }
    }

   /* this.Test_id = val1;
    this.Test_date = val2;
    this.Result = val3;
    this.Total_ques = val4;
    this.Correct_ques = val5;
    this.Wrong_ques = val6;
    this.Answered = val7;
    this.UnAnswered = val8;

*/


    DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
    Date date1 = new Date();
    String cuurentdate = ""+dateFormat.format(date1);


    if(isTestComplete) {

        if(total_correct_answer>=total_wrong_answer) {

            obj.setValues(StartTestActivity.Ques_det.get(0).Test_id,
                    cuurentdate,
                    "PASS",
                    StartTestActivity.Ques_det.size(),
                    total_correct_answer,
                    total_wrong_answer,
                    total_answerd,
                    total_Unanswerd);
        }
        else{
            obj.setValues(StartTestActivity.Ques_det.get(0).Test_id,
                    cuurentdate,
                    "FAIL",
                    StartTestActivity.Ques_det.size(),
                    total_correct_answer,
                    total_wrong_answer,
                    total_answerd,
                    total_Unanswerd);
        }
    }

/*    initialValues.put("Test_id", Test_id);//1
    initialValues.put("Test_date", Test_date);//2
    initialValues.put("Result", Result);//3
    initialValues.put("Total_ques", Total_ques);//4

    initialValues.put("Correct_ques", Correct_ques);//5
    initialValues.put("Wrong_ques", Wrong_ques);//6

    initialValues.put("Answered", Answered);//7
    initialValues.put("UnAnswered", UnAnswered);//8*/

    if(isTestComplete) {
        db.open();
        db.insertRecord(String.valueOf(obj.Test_id),
                String.valueOf(obj.Test_date),
                String.valueOf(obj.Result),
                String.valueOf(obj.Total_ques),
                String.valueOf(obj.Correct_ques),
                String.valueOf(obj.Wrong_ques),
                String.valueOf(obj.Answered),
                String.valueOf(obj.UnAnswered));
        db.close();
    }
//    isTestComplete=true;

    /*db.open();
    db.deleteRecord();
    for (int i = 0; i < parseObject.size(); i++) {

        db.insertRecord(parseObject.get(i).getString("CATG_ID"), parseObject.get(i).getString("WORD"), parseObject.get(i).getString("PRON_ENG")
                , parseObject.get(i).getString("MEANING_HIN"), parseObject.get(i).getString("MEANING2"), parseObject.get(i).getString("MEANING3")
                , parseObject.get(i).getString("MEANING4"), parseObject.get(i).getString("MEANING5"), parseObject.get(i).getString("EX1")
                , parseObject.get(i).getString("EX2"), parseObject.get(i).getString("EX3"), parseObject.get(i).getString("EX4")
                , parseObject.get(i).getString("EX5"), parseObject.get(i).getString("MATCH1"), parseObject.get(i).getString("MATCH2")
                , parseObject.get(i).getString("MATCH3"), parseObject.get(i).getString("MATCH4"), parseObject.get(i).getString("MATCH5")
                , parseObject.get(i).getString("HIN_EX1"), parseObject.get(i).getString("HIN_EX2"), parseObject.get(i).getString("HIN_EX3")
                , parseObject.get(i).getString("HIN_EX4"), parseObject.get(i).getString("HIN_EX5")
        );
        //   db.insertContact("dd",3);
    }

    db.close();*/
}


public void viewdata(ArrayList<QuestionDetails> Ques_detlobjects) {

        Myadapter adapter = new Myadapter(QuestionActivity.this,Ques_detlobjects,counter);
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

   public class Myadapter extends BaseAdapter{

         Context context;
         int layoutResourceId;
         ArrayList<QuestionDetails> values;

         public Myadapter(Context context,ArrayList<QuestionDetails> objects,final int counter) {
             this.context = context;
             this.values=objects;
         }


       @Override
       public int getCount() {
           return values.size();
       }

       @Override
       public Object getItem(int position) {
           return null;
       }

       @Override
       public long getItemId(int position) {
           return position;
       }

       @Override
         public View getView(final int position, View convertView, ViewGroup parent) {
             if(convertView == null){
                 convertView = QuestionActivity.this.getLayoutInflater().inflate(R.layout.items_question_activity,null);
             }

           TextView txtno = (TextView) convertView.findViewById(R.id.txtQno);

             img = (ImageView)convertView.findViewById(R.id.img);

             TextView txtQuestion = (TextView) convertView.findViewById(R.id.txtQuestion);
             TextView txtOptA = (TextView) convertView.findViewById(R.id.txtOptA);
             TextView txtOptB = (TextView) convertView.findViewById(R.id.txtOptB);
             TextView txtOptC = (TextView) convertView.findViewById(R.id.txtOptC);
             TextView txtOptD = (TextView) convertView.findViewById(R.id.txtOptD);


           optA = (net.qiujuer.genius.widget.GeniusCheckBox)convertView. findViewById(R.id.a);
           optB = (net.qiujuer.genius.widget.GeniusCheckBox)convertView. findViewById(R.id.b);
           optC = (net.qiujuer.genius.widget.GeniusCheckBox)convertView. findViewById(R.id.c);
           optD = (net.qiujuer.genius.widget.GeniusCheckBox)convertView. findViewById(R.id.d);



           txtno.setText(String.valueOf(counter+1));
           txtQuestion.setText(values.get(position).Question);
           txtOptA.setText("   "+values.get(position).optA);
           txtOptB.setText("   "+values.get(position).optB);
           txtOptC.setText("   "+values.get(position).optC);
           txtOptD.setText("   "+values.get(position).optD);
             //Log.e("value", String.valueOf(values.get(position).getString("playerName")));



           optA.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   selectedOption="A";
                   optB.setChecked(false);
                   optC.setChecked(false);
                   optD.setChecked(false);

               }
           });

           optB.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   selectedOption="B";
                   optA.setChecked(false);
                   optC.setChecked(false);
                   optD.setChecked(false);

               }
           });

           optC.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   selectedOption="C";
                   optB.setChecked(false);
                   optA.setChecked(false);
                   optD.setChecked(false);

               }
           });

           optD.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   selectedOption="D";
                   optB.setChecked(false);
                   optC.setChecked(false);
                   optA.setChecked(false);

               }
           });


           selectoption.add(selectedOption);
           Log.e("Select value",""+selectedOption);
           selectedOption="NA";

           if(values.get(position).Q_type.equalsIgnoreCase("text")){
               finaltime=time_text;
           }


          /* pieView.setProgress(0);
           pieView.setMax(finaltime*35);
           pieView.animateProgressFill();*/




             return  convertView;
         }


     }


    //end of min class
}