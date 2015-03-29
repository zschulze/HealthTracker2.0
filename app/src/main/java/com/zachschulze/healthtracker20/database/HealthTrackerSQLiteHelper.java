package com.zachschulze.healthtracker20.database;

/**
 * Created by Zach on 3/28/2015.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zach on 3/1/2015.
 */

public class HealthTrackerSQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "healthtracker.db";
    // Food Items Table functionality
    public static final String TABLE_FOODITEMS = "FOODITEMS";
    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_FOODNAME = "FOODNAME";
    public static final String COLUMN_CALORIES = "CALORIES";
    public static final String COLUMN_SERVINGSIZE = "SERVINGSIZE";
    public static final String COLUMN_SERVINGUNIT = "SERVINGUNIT";
    private static final String CREATE_FOODITEMS_TABLE = "CREATE TABLE " +
            TABLE_FOODITEMS + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_FOODNAME + " TEXT," +
            COLUMN_CALORIES + " INTEGER," +
            COLUMN_SERVINGSIZE + " INTEGER," +
            COLUMN_SERVINGUNIT + " TEXT" + ")";

    public HealthTrackerSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_FOODITEMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOODITEMS);
        onCreate(db);
    }
}
