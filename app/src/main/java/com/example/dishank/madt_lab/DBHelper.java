package com.example.dishank.madt_lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Dishank on 4/1/2016.
 */
public class DBHelper {

    public static final String DATABASE_NAME = "footballnew";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE_NAME = "players";
    public static final String KEY_ID = "_id";
    public static final String COL_NAME = "Name";
    public static final String COL_NAME1= "Matches";
    public static final String COL_NAME2= "Goals";
    public static final int COL_INDEX = 1;

    /*private static final String DB_CREATE="create table "+TABLE_NAME+" ("
            +KEY_ID+" integer primary key "+ "autoincrement , " +
            ""+COL_NAME+" text not null);";*/

    private static final String DB_CREATE="create table "+TABLE_NAME+" ("+KEY_ID+
         " integer primary key "+ "autoincrement, "+""+COL_NAME+" text not null, "+COL_NAME1+
         " text not null, "+COL_NAME2+" text not null);";

    private SQLiteDatabase database;
    private final Context context;
    private MyDBAdapter helper;

    public DBHelper(Context context)
    {
        this.context = context;
        helper = new MyDBAdapter(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHelper open()
    {
        database = helper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        database.close();
    }

    public long insertEntry(String Name, String Match, String Goal) {

        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_NAME,Name);
        contentValues.put(COL_NAME1,Match);
        contentValues.put(COL_NAME2, Goal);
        return database.insert(TABLE_NAME, null, contentValues);
    }


    public boolean removeEntry(long rowIndex) {
        System.out.print(rowIndex);
        return database.delete(TABLE_NAME, KEY_ID+" = "+rowIndex, null)>0;
    }

    public Cursor getAllEntries() {


        return database.query(TABLE_NAME, new String[]{KEY_ID,COL_NAME,COL_NAME1,COL_NAME2}, null, null, null, null, null);
    }

    public int updateEntry(long rowIndex,String playerName,String Match,String Goal) {

        ContentValues updateValues=new ContentValues();
        updateValues.put(COL_NAME, playerName);
        updateValues.put(COL_NAME1, Match);
        updateValues.put(COL_NAME2, Goal);
        return database.update(TABLE_NAME, updateValues, KEY_ID+" = "+rowIndex, null);
    }

    private static class MyDBAdapter extends SQLiteOpenHelper {


        public MyDBAdapter(Context context, String name,
                           SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            Log.w("Updation", "Data base version is being updates");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }
}
