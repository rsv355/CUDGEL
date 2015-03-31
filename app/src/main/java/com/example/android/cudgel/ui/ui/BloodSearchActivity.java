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
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.cudgel.R;
import com.example.android.cudgel.ui.base.DBAdapter;
import com.example.android.cudgel.ui.base.QuestionDetails;
import com.example.android.cudgel.ui.model.BloodGroup;
import com.example.android.cudgel.ui.model.CurrentTest;

import net.qiujuer.genius.widget.GeniusButton;

import java.util.ArrayList;


public class BloodSearchActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private GeniusButton btnStart;

    public static CustomAdapter adapter;
    ListView list;
    Spinner spBlood;
    DBAdapter db;
    public static  ArrayList<BloodGroup> resultList=new ArrayList<BloodGroup>();
    Dialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodsearch);
        db= new DBAdapter(BloodSearchActivity.this);
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

        spBlood = (Spinner)findViewById(R.id.spBlood);
        list = (ListView)findViewById(R.id.list);



        spBlood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(position==0){
                        Toast.makeText(BloodSearchActivity.this,"Please Select Blood Group first",Toast.LENGTH_LONG).show();
                    }
                else{

                            resultList = new ArrayList<BloodGroup>();
                            db.open();
                            Cursor c = db.getRecordBLOOD(spBlood.getSelectedItem().toString());
                            if (c.moveToFirst()) {
                                Display(c);
                            }
                            else {
                                resultList = new ArrayList<BloodGroup>();
                            }
                            c.close();
                            db.close();

                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void Display(Cursor c) {
        c.moveToFirst();

        while (!c.isAfterLast()) {
            BloodGroup newObj = new BloodGroup();

            //     Toast.makeText(ResultActivity.this,""+ c.getString(c.getColumnIndex("Test_date")),Toast.LENGTH_SHORT).show();
            newObj.name = c.getString(c.getColumnIndex("name"));
            newObj.mob1 = c.getString(c.getColumnIndex("mob1"));
            newObj.mob2 = c.getString(c.getColumnIndex("mob2"));
            newObj.blood_group = c.getString(c.getColumnIndex("blood_group"));
            newObj.area = c.getString(c.getColumnIndex("area"));
            newObj.city = c.getString(c.getColumnIndex("city"));
            newObj.state = c.getString(c.getColumnIndex("state"));

            resultList.add(newObj);
            c.moveToNext();
        }

        adapter = new CustomAdapter(BloodSearchActivity.this, resultList);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();


      /*  if(resultList.size()!=0){
      //      linearemp.setVisibility(View.GONE);
        }*/



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent i = new Intent(BloodSearchActivity.this,CustomResultDialogActivity.class);
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




    public class CustomAdapter extends BaseAdapter {
        ArrayList<BloodGroup> result;
        Context context;

        public CustomAdapter(Context ctx, ArrayList<BloodGroup> prgmNameList) {
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
                convertView = getLayoutInflater().inflate(R.layout.item_list_blood_donor_search,null);
            }


            TextView txtArea = (TextView)convertView.findViewById(R.id.txtArea);
            TextView txtMob = (TextView)convertView.findViewById(R.id.txtMob);
            TextView txtname = (TextView)convertView.findViewById(R.id.txtname);
            TextView txtbloodgroup = (TextView)convertView.findViewById(R.id.txtbloodgroup);

/*
            txttestID.setText(result.get(position).Test_id.trim());
            txtdate.setText(result.get(position).Test_date.trim());

            if(result.get(position).Result.trim().equalsIgnoreCase("Pass")){
                imgPassorFail.setImageResource(R.drawable.icon_small_pass);
            }
            else{
                imgPassorFail.setImageResource(R.drawable.icon_small_fail);
            }*/





            return convertView;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    //end of min class
}