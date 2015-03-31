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
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import net.qiujuer.genius.widget.GeniusButton;

import java.util.ArrayList;
import java.util.List;


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

        processDowloadandSQLinsert();

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




     /*   list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent i = new Intent(BloodSearchActivity.this,CustomResultDialogActivity.class);
                i.putExtra("pos",position);
                startActivity(i);

            }
        });*/

    }


    private void processDowloadandSQLinsert(  ){

        dialog= ProgressDialog.show(this,"Please Wait","downloading latest data from server...",true);
        dialog.setCancelable(false);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("BLOOD_TABLE");
        // query.whereEqualTo("DATABASE_VERSION", "ALL3");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObject, ParseException e) {
                if (e == null) {
                    // check for database
                    // check for database
                    db.open();
                    db.deleteRecordBLOOD();
                    for (int i = 0; i < parseObject.size(); i++) {

                        db.insertRecordBLOOD(parseObject.get(i).getString("name"),parseObject.get(i).getString("mob1"), parseObject.get(i).getString("mob2"), parseObject.get(i).getString("blood_group")
                                , parseObject.get(i).getString("area"), parseObject.get(i).getString("city"), parseObject.get(i).getString("state")
                            );

                    }

                    db.close();


                    dialog.dismiss();


                } else {
                    Toast.makeText(getApplicationContext(), "Error to fetch details !!!", Toast.LENGTH_SHORT).show();

                }

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


            txtname.setText(result.get(position).name.trim());
            txtbloodgroup.setText(result.get(position).blood_group.trim());

            if(result.get(position).mob2 == null ){
                txtMob.setText(result.get(position).mob1.trim());
            }
            else{
                txtMob.setText(result.get(position).mob1.trim()+" , "+result.get(position).mob2.trim());
            }


            txtArea.setText(result.get(position).area.trim() + " , " + result.get(position).city.trim() + " , " + result.get(position).state.trim());

            return convertView;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    //end of min class
}