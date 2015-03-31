package com.example.android.cudgel.ui.ui;

import android.app.ProgressDialog;
import android.content.Context;
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
import com.example.android.cudgel.ui.base.QuestionDetails;
import com.example.android.cudgel.ui.model.CurrentTest;

import net.qiujuer.genius.widget.GeniusButton;

import java.util.ArrayList;


public class BloodSearchActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private GeniusButton btnStart;
    ProgressDialog dialog;
    public static CustomAdapter adapter;
    ListView list;
    Spinner spBlood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodsearch);

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
                     //   adapter = new CustomAdapter(BloodSearchActivity.this, resultList);
                        list.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    public class CustomAdapter extends BaseAdapter {
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