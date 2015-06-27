package com.zachschulze.healthtracker20.database;

/**
 * Created by Zach on 3/28/2015.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HealthTrackerSQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "healthtracker.db";

    // Food Items Table functionality
    public static final String TABLE_FOODITEMS = "FOODITEMS";
    public static final String COLUMN_FOOD_ID = "FOOD_ID";
    public static final String COLUMN_FOODNAME = "FOODNAME";
    public static final String COLUMN_FOOD_CALORIES = "CALORIES";
    public static final String COLUMN_FOOD_SERVINGSIZE = "SERVINGSIZE";
    public static final String COLUMN_FOOD_SERVINGUNIT = "SERVINGUNIT";
    private static final String CREATE_FOODITEMS_TABLE = "CREATE TABLE " +
            TABLE_FOODITEMS + "(" +
            COLUMN_FOOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_FOODNAME + " TEXT," +
            COLUMN_FOOD_CALORIES + " INTEGER," +
            COLUMN_FOOD_SERVINGSIZE + " INTEGER," +
            COLUMN_FOOD_SERVINGUNIT + " TEXT" + ")";

    // Meal Items Table functionality
    public static final String TABLE_MEALITEMS = "MEALITEMS";
    public static final String COLUMN_MEAL_ID = "MEAL_ID";
    public static final String COLUMN_MEALNAME = "MEALNAME";
    public static final String COLUMN_MEAL_CALORIES = "CALORIES";
    public static final String COLUMN_MEAL_NUMSERVINGS = "NUMSERVINGS";
    private static final String CREATE_MEALITEMS_TABLE = "CREATE TABLE " +
            TABLE_MEALITEMS + "(" +
            COLUMN_MEAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_MEALNAME + " TEXT," +
            COLUMN_MEAL_CALORIES + " INTEGER," +
            COLUMN_MEAL_NUMSERVINGS + " INTEGER)";

    // Food and Meal Junction Table functionality
    public static final String TABLE_MEALITEMS_FOODITEMS = "MEALITEMS_FOODITEMS";
    public static final String COLUMN_FOODID = "FOOD_ID";
    public static final String COLUMN_MEALID = "MEAL_ID";
    private static final String CREATE_MEALITEMS_FOODITEMS_TABLE = "CREATE TABLE " +
            TABLE_MEALITEMS_FOODITEMS + "(" +
            COLUMN_FOODID + " INTEGER," +
            COLUMN_MEALID + " INTEGER," +
            "FOREIGN KEY (" + COLUMN_MEALID + ") REFERENCES " + TABLE_MEALITEMS + "(" + COLUMN_MEALID + "), " +
            "FOREIGN KEY (" + COLUMN_FOODID + ") REFERENCES " + TABLE_FOODITEMS + "(" + COLUMN_FOODID + "))";

    public HealthTrackerSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_FOODITEMS_TABLE);
        db.execSQL(CREATE_MEALITEMS_TABLE);
        db.execSQL(CREATE_MEALITEMS_FOODITEMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOODITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEALITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEALITEMS_FOODITEMS);
        onCreate(db);
    }
}
