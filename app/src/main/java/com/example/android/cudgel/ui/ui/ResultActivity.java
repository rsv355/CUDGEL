package com.example.android.cudgel.ui.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.cudgel.R;
import com.example.android.cudgel.ui.base.DBAdapter;
import com.example.android.cudgel.ui.base.QuestionDetails;
import com.example.android.cudgel.ui.model.CurrentTest;
import com.pixplicity.easyprefs.library.Prefs;

import net.qiujuer.genius.widget.GeniusButton;

import java.util.ArrayList;


public class ResultActivity extends ActionBarActivity {
    private Toolbar toolbar;
    public static int i;
    ListView listResult;
    public static CustomAdapter adapter;
    DBAdapter db;
    public static  ArrayList<CurrentTest> resultList=new ArrayList<CurrentTest>();
    Dialog dialog;
    LinearLayout linearemp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        db= new DBAdapter(ResultActivity.this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#3D3427"));
        toolbar.setNavigationIcon(R.drawable.icon_back);

              if (toolbar != null) {
            toolbar.setTitle("Result");
            setSupportActionBar(toolbar);
        }



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

       // linearemp = (LinearLayout)findViewById(R.id.linearemp);
        listResult = (ListView)findViewById(R.id.listResult);



        loaddata();
    }


    private void Display(Cursor c) {
    c.moveToFirst();


    while (!c.isAfterLast()) {
        CurrentTest newObj = new CurrentTest();

   //     Toast.makeText(ResultActivity.this,""+ c.getString(c.getColumnIndex("Test_date")),Toast.LENGTH_SHORT).show();
        newObj.Test_id = c.getString(c.getColumnIndex("Test_id"));
        newObj.Test_date = c.getString(c.getColumnIndex("Test_date"));
        newObj.Result = c.getString(c.getColumnIndex("Result"));
        newObj.Total_ques = c.getInt(c.getColumnIndex("Total_ques"));
        newObj.Answered = c.getInt(c.getColumnIndex("Answered"));
        newObj.UnAnswered = c.getInt(c.getColumnIndex("UnAnswered"));
        newObj.Correct_ques = c.getInt(c.getColumnIndex("Correct_ques"));
        newObj.Wrong_ques = c.getInt(c.getColumnIndex("Wrong_ques"));

        resultList.add(newObj);
            c.moveToNext();
        }

        adapter = new CustomAdapter(ResultActivity.this, resultList);
        listResult.setAdapter(adapter);
        adapter.notifyDataSetChanged();


      /*  if(resultList.size()!=0){
      //      linearemp.setVisibility(View.GONE);
        }*/



        listResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent i = new Intent(ResultActivity.this,CustomResultDialogActivity.class);
                i.putExtra("pos",position);
                startActivity(i);
/*

               final Dialog dialog = new Dialog(ResultActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_dialog);
                dialog.show();*/

            }
        });

    }

    void loaddata(){
        resultList = new ArrayList<CurrentTest>();
        db.open();
        Cursor c = db.getAllResult();
        if (c.moveToFirst()) {
            Display(c);
             }
        else {
            resultList = new ArrayList<CurrentTest>();
        }
        c.close();
        db.close();



    }

    @Override
    protected void onResume() {
        super.onResume();



    }


    public class CustomAdapter extends BaseAdapter{
        ArrayList<CurrentTest> result;
        Context context;

        public CustomAdapter(Context ctx, ArrayList<CurrentTest> prgmNameList) {
            // TODO Auto-generated constructor stub
            result=prgmNameList;
            this.context = ctx;
        }

        @Override
        public int getCount() {
            return result.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.item_list_result,null);
            }


            TextView txttestID = (TextView)convertView.findViewById(R.id.txttestID);
            TextView txtdate = (TextView)convertView.findViewById(R.id.txtdate);
            ImageView imgPassorFail = (ImageView)convertView.findViewById(R.id.imgPassorFail);

            txttestID.setText(result.get(position).Test_id.trim());
            txtdate.setText(result.get(position).Test_date.trim());

            if(result.get(position).Result.trim().equalsIgnoreCase("Pass")){
                imgPassorFail.setImageResource(R.drawable.icon_small_pass);
            }
            else{
                imgPassorFail.setImageResource(R.drawable.icon_small_fail);
            }





            return convertView;
        }
    }

    //end of min class
}