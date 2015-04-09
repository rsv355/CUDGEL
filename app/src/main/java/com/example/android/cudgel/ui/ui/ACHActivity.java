package com.example.android.cudgel.ui.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.cudgel.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import net.qiujuer.genius.widget.GeniusButton;

import java.util.List;


public class ACHActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private GeniusButton btnStart;

    public static int i;
    Dialog dialog;
    public static CustomAdapter adapter;
    ListView list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#3D3427"));
        toolbar.setNavigationIcon(R.drawable.icon_back);

              if (toolbar != null) {
            toolbar.setTitle("Achievements");
            setSupportActionBar(toolbar);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        list = (ListView)findViewById(R.id.list);

        processDowloadandSQLinsert();

    }


    private void processDowloadandSQLinsert(  ){

        dialog= ProgressDialog.show(ACHActivity.this, "Please Wait", "downloading latest data from server...", true);
        dialog.setCancelable(false);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("ACHEV");
        // query.whereEqualTo("DATABASE_VERSION", "ALL3");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObject, ParseException e) {
                if (e == null) {
                    // check for database
                    // check for database

                    adapter = new CustomAdapter(ACHActivity.this, parseObject);
                    list.setAdapter(adapter);
                    adapter.notifyDataSetChanged();


                    dialog.dismiss();


                } else {
                    Toast.makeText(ACHActivity.this, "Error to fetch details !!!", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }


    class CustomAdapter extends BaseAdapter {
        List<ParseObject> result;
        Context context;

        public CustomAdapter(Context ctx, List<ParseObject> prgmNameList) {
            // TODO Auto-generated constructor stub
            result = prgmNameList;
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
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_list_user_search, null);
            }


            TextView txtArea = (TextView) convertView.findViewById(R.id.txtArea);
            TextView txtMob = (TextView) convertView.findViewById(R.id.txtMob);
            TextView txtname = (TextView) convertView.findViewById(R.id.txtname);


            txtname.setText(result.get(position).getString("Name").trim());
            txtMob.setText(result.get(position).getString("Achev").trim());

            txtArea.setText(result.get(position).getString("Desc").trim());

            return convertView;
        }

    }



    @Override
    protected void onResume() {
        super.onResume();

    }

    //end of min class
}