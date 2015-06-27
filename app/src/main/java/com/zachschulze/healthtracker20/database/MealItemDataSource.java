package com.zachschulze.healthtracker20.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zachschulze.healthtracker20.models.FoodItem;
import com.zachschulze.healthtracker20.models.MealItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zach on 6/23/2015.
 */
public class MealItemDataSource {
    private HealthTrackerSQLiteHelper mHealthTrackerSQLiteHelper;
    private Context context;

    public MealItemDataSource(Context context) {
        mHealthTrackerSQLiteHelper = new HealthTrackerSQLiteHelper(context);
        this.context = context;
    }

    public boolean addMealItem(MealItem mealItem) {
        if (mealItem.getMealName() == "" || mealItem.getCalories() == -1 ||
                mealItem.getNumServings() == -1) {
            return false;
        }
        ContentValues values = new ContentValues();
        values.put(HealthTrackerSQLiteHelper.COLUMN_MEALNAME, mealItem.getMealName());
        values.put(HealthTrackerSQLiteHelper.COLUMN_MEAL_CALORIES, mealItem.getCalories());
        values.put(HealthTrackerSQLiteHelper.COLUMN_MEAL_NUMSERVINGS, mealItem.getNumServings());

        SQLiteDatabase db = mHealthTrackerSQLiteHelper.getWritableDatabase();
        db.insert(HealthTrackerSQLiteHelper.TABLE_MEALITEMS, null, values);

        //get the last meal id entered (which is actually the rowid)
        //need to test if deleting item will mess up rowid, according to initial tests it works fine
        String query = "SELECT ROWID from " + HealthTrackerSQLiteHelper.TABLE_MEALITEMS + " order by ROWID DESC limit 1";
        SQLiteDatabase db2 = mHealthTrackerSQLiteHelper.getReadableDatabase();
        Cursor c = db2.rawQuery(query, null);
        long lastId = 0;
        if (c.moveToFirst()) {
            lastId = c.getLong(0);
        }

        for (int i = 0; i < mealItem.getFoodItems().size(); i++) {
            ContentValues junctionValues = new ContentValues();
            junctionValues.put(HealthTrackerSQLiteHelper.COLUMN_MEALID, lastId);
            junctionValues.put(HealthTrackerSQLiteHelper.COLUMN_FOODID, mealItem.getFoodItems().get(i).getID());
            db.insert(HealthTrackerSQLiteHelper.TABLE_MEALITEMS_FOODITEMS, null, junctionValues);
        }

        db.close();
        return true;
    }


    public boolean deleteMealItem(String mealname) {
        boolean result = false;

        String query = "Select * FROM" + HealthTrackerSQLiteHelper.TABLE_MEALITEMS + " WHERE" + HealthTrackerSQLiteHelper.COLUMN_MEALNAME + " = \"" + mealname + "\"";

        SQLiteDatabase db = mHealthTrackerSQLiteHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        MealItem mealItem = new MealItem();

        if (cursor.moveToFirst()) {
            mealItem.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(HealthTrackerSQLiteHelper.TABLE_MEALITEMS, HealthTrackerSQLiteHelper.COLUMN_MEAL_ID + " = ?",
                    new String[] {
                            String.valueOf(mealItem.getID())
                    });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public List<MealItem> getAllMealItems() {
        List<MealItem> mealItems = new ArrayList<MealItem>();
        String selectQuery = "SELECT * FROM " + HealthTrackerSQLiteHelper.TABLE_MEALITEMS;

        SQLiteDatabase db = mHealthTrackerSQLiteHelper.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                MealItem mealItem = new MealItem();
                int currentMealId = c.getInt(c.getColumnIndex(HealthTrackerSQLiteHelper.COLUMN_MEAL_ID));
                mealItem.setID(currentMealId);
                mealItem.setMealName(c.getString(c.getColumnIndex(HealthTrackerSQLiteHelper.COLUMN_MEALNAME)));
                mealItem.setCalories(c.getInt(c.getColumnIndex(HealthTrackerSQLiteHelper.COLUMN_MEAL_CALORIES)));
                mealItem.setNumServings(c.getInt(c.getColumnIndex(HealthTrackerSQLiteHelper.COLUMN_MEAL_NUMSERVINGS)));

                FoodItemDataSource dataSource= new FoodItemDataSource(this.context);
                List<FoodItem> foodItems = dataSource.getAllFoodItemsForMeal(currentMealId);
                mealItem.setFoodItems(foodItems);

                mealItems.add(mealItem);
            } while (c.moveToNext());
        }
        return mealItems;
    }
}
