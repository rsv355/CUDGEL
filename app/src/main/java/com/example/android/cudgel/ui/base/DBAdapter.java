package com.example.android.cudgel.ui.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Android on 08-12-2014.
 */

public class DBAdapter {
    public static final String KEY_ROWID = "_id";
   /* public static final String KEY_NAME = "playername";
    public static final String KEY_SCORE = "score";*/
    private static final String TAG = "DBAdapter";
    private static final String DATABASE_NAME = "EAPP";

    private static final String DATABASE_TABLE = "Result";
    private static final int DATABASE_VERSION = 1;



    private static final String DATABASE_CREATE =
            "create table Result (_id integer primary key autoincrement, "
                    + "Test_id text not null," +
                      "Test_date text not null," +
                      "Result text," +
                      "Total_ques text not null," +
                      "Correct_ques text," +
                      "Wrong_ques text," +
                       "Answered text," +
                      "UnAnswered text );";

    private static final String BLOOD_TABLE =
            "create table BLOOD_TABLE (_id integer primary key autoincrement, "
                    + "name text ," +
                    "mob1 text ," +
                    "mob2 text," +
                    "blood_group text  ," +
                    "area text," +
                    "city text," +
                    "state text );";

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DATABASE_CREATE);
                db.execSQL(BLOOD_TABLE);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS Result");
            db.execSQL("DROP TABLE IF EXISTS BLOOD_TABLE");
         
            onCreate(db);
        }
    }


    //---opens the database---
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }


    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }

/*
    + "Test_id text not null," +
            "Test_date text not null," +
            "Result text," +
            "Total_ques text not null," +
            "Correct_ques text," +
            "Wrong_ques text," +
            "Answered text," +
            "UnAnswered text );
*/


    //---insert a contact into the database---
    public long insertRecord(String Test_id,String Test_date, String Result,String Total_ques
            ,String Correct_ques,String Wrong_ques,String Answered,String UnAnswered)
    {
        ContentValues initialValues = new ContentValues();

        initialValues.put("Test_id", Test_id);//1
        initialValues.put("Test_date", Test_date);//2
        initialValues.put("Result", Result);//3
        initialValues.put("Total_ques", Total_ques);//4

        initialValues.put("Correct_ques", Correct_ques);//5
        initialValues.put("Wrong_ques", Wrong_ques);//6

        initialValues.put("Answered", Answered);//7
        initialValues.put("UnAnswered", UnAnswered);//8

        Log.e("insert in eapp ","ok");

        return db.insert(DATABASE_TABLE, null, initialValues);
    }


  /*  + "name text ," +
            "mob1 text ," +
            "mob2 text," +
            "blood_group text  ," +
            "area text," +
            "city text," +
            "state text );";*/

    public long insertRecordBLOOD(String name,String mob1, String mob2,String blood_group
            ,String area,String city,String state)
    {
        ContentValues initialValues = new ContentValues();

        initialValues.put("name", name);//1
        initialValues.put("mob1", mob1);//2
        initialValues.put("mob2", mob2);//3
        initialValues.put("blood_group", blood_group);//4

        initialValues.put("area", area);//5
        initialValues.put("city", city);//6

        initialValues.put("state", state);//7


        Log.e("insert in blood ","ok");

        return db.insert(BLOOD_TABLE, null, initialValues);
    }


    //---deletes a particular contact---
    public void deleteRecord( )
    {
        Log.e("record ddelted ","ok");
        db.execSQL("delete  from "+ DATABASE_TABLE);
      //  return db.delete(DATABASE_TABLE,null );
    }

    public void deleteRecordBLOOD( )
    {
        Log.e("record ddelted ","ok");
        db.execSQL("delete  from "+ BLOOD_TABLE);

    }

    //---retrieves a particular contact---
    public Cursor getRecordBLOOD(String rowId) throws SQLException
    {
        String selectQuery = "SELECT * FROM BLOOD_TABLE WHERE UPPER(blood_group) ="+"\""+rowId.toString().trim().toUpperCase()+"\"";
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }



    //---retrieves a particular contact---
   public Cursor getRecord(String rowId) throws SQLException
    {
        String selectQuery = "SELECT * FROM Result WHERE UPPER(WORD) ="+"\""+rowId.toString().trim().toUpperCase()+"\"";
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }



    public Cursor getAllResult() throws SQLException
    {
        String selectQuery = "SELECT * FROM Result";
        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }

    public Cursor getHistoryWord(String WORD) throws SQLException
    {
        String selectQuery = "SELECT * FROM D_Word_History WHERE WORD ="+"\""+WORD.toString().trim()+"\"";
        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }




/*
    //---updates a contact---
    public boolean updateContact(long rowId, String name, int email)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_SCORE, email);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }*/
}