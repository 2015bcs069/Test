package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import com.jjoe64.graphview.series.DataPoint;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "Readings";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "HUMMIDITY";
    public static final String COL_3 = "TEMPERATURE";
    public static final String COL_4 = "CARBONDIOXIDE";
    public static final String COL_5 = "MOISTURE";
    static final String COL_6 = "LigtStatus";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //  SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,HUMMIDITY INTEGER NOT NULL,TEMPERATURE INTEGER NOT NULL," +
                "CARBONDIOXIDE INTEGER NOT NULL, MOISTURE INTEGER NOT NULL, LigtStatus TEXT NOT NULL)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String humm,String temp,String c02, String moist, String light) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,humm);
        contentValues.put(COL_3,temp);
        contentValues.put(COL_4,c02);
        contentValues.put(COL_5,moist);
        contentValues.put(COL_6,light);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;

        else

            return true;

    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String humm,String temp,String c02, String moist) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,humm);
        contentValues.put(COL_3,temp);
        contentValues.put(COL_4,c02);
        contentValues.put(COL_5,moist);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

    public void execSQL(String s) {
    }
 public DataPoint[] getData() {


        SQLiteDatabase db = this.getWritableDatabase();
        String []columns = {"HUMMIDITY","TEMPERATURE"};
        Cursor cursor = db.query("Readings",columns,null,null,null,null,null);
        DataPoint[] dataPoints = new DataPoint[cursor.getCount()];
        for( int i=0; i<cursor.getCount(); i++)
        {
            cursor.moveToNext();
            dataPoints[i] = new DataPoint(cursor.getInt(0),cursor.getInt(1));
            //cursor.close();
        }
        return dataPoints;

    }
}