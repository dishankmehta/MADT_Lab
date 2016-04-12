package com.example.dishank.madt_lab;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;


/**
 * Created by Dishank on 4/4/2016.
 */
public class ProviderFile extends ContentProvider {

    static final String DB_NAME = "Timepass";
    static final String DB_TABLE = "playerinfo";
    static final int DB_VERSION = 1;
    static final String TABLE_CREATE = "CREATE TABLE \" + DB_TABLE +\" (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                    "player VARCHAR(255) not null, goals VARCHAR(255) not null);";
    static final String _ID = "_id";
    static final String PLAYERNAME = "player";
    static final String GOALS = "goals";
    static final String AUTHORITY = "com.example.dishank.provider.players";
    static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/playerinfo");
    static final int ALLROWS = 1;
    static final int SINGLEROW = 2;
    private static final UriMatcher URIMATCHER;

    static {
        URIMATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URIMATCHER.addURI(AUTHORITY, "playerinfo", ALLROWS);
        URIMATCHER.addURI(AUTHORITY, "playerinfo/#", SINGLEROW);
    }

    SQLiteDatabase PlayersDB;
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/playerinfo";


    @Override
    public boolean onCreate() {
        Context context = getContext();
        SQHelper helper = new SQHelper(context);
        PlayersDB = helper.getWritableDatabase();
        return (PlayersDB == null) ? false : true;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String criteria, String[] criteriaValues, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(DB_TABLE);
        if (URIMATCHER.match(uri) == SINGLEROW)
            queryBuilder.appendWhere(_ID + " = " + uri.getPathSegments().get(1));
        /*if (sortOrder==null || sortOrder=="")
            sortOrder = "player";*/
        Cursor c = queryBuilder.query(PlayersDB,projection,criteria,criteriaValues,null,null,sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }


    @Override
    public String getType(Uri uri) {
        switch (URIMATCHER.match(uri)){
            case ALLROWS:
                return "vnd.android.cursor.dir/vnd.players.playerinfo";
            case SINGLEROW:
                return "vnd.android.cursor.item/vnd.players.playerinfo";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowID = PlayersDB.insert(DB_TABLE,null,values);
        if (rowID >0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        try {
            throw new SQLException("Error: New row could not be inserted ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String criteria, String[] criteriaValues) {
        int count=0;
        switch (URIMATCHER.match(uri)){
            case ALLROWS:
                count = PlayersDB.delete(DB_TABLE, criteria, criteriaValues);
                break;
            case SINGLEROW:
                String id = uri.getPathSegments().get(1);
                count = PlayersDB.delete(DB_TABLE, _ID + " = " + id +(!TextUtils.isEmpty(criteria) ? " AND (" +criteria + ')': ""),criteriaValues);
                break;
            default: throw new IllegalArgumentException("URI not found: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String criteria, String[] criteriaValues) {
        int count = 0;
        switch (URIMATCHER.match(uri)){
            case ALLROWS:
                count = PlayersDB.update(DB_TABLE,values,criteria,criteriaValues);
                break;
            case SINGLEROW:
                count = PlayersDB.update(DB_TABLE, values, _ID + " = " + uri.getPathSegments().get(1) +(!    TextUtils.isEmpty(criteria) ? " AND (" +criteria + ')': ""),criteriaValues);
                break;
            default: throw new IllegalArgumentException("URI not found: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    private static class SQHelper extends SQLiteOpenHelper {
        SQHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+ DB_TABLE);
            onCreate(db);
        }
    }
}
