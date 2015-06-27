package com.zachschulze.healthtracker20.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zachschulze.healthtracker20.models.FoodItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zach on 3/29/2015..
 */
public class FoodItemDataSource {
    private HealthTrackerSQLiteHelper mHealthTrackerSQLiteHelper;

    public FoodItemDataSource(Context context) {
        mHealthTrackerSQLiteHelper = new HealthTrackerSQLiteHelper(context);
    }

    public boolean addFoodItem(FoodItem foodItem) {
        if (foodItem.getFoodName() == "" || foodItem.getCalories() == -1 ||
                foodItem.getServingSize() == -1 || foodItem.getServingUnit() == "") {
            return false;
        }
        ContentValues values = new ContentValues();
        values.put(HealthTrackerSQLiteHelper.COLUMN_FOODNAME, foodItem.getFoodName());
        values.put(HealthTrackerSQLiteHelper.COLUMN_FOOD_CALORIES, foodItem.getCalories());
        values.put(HealthTrackerSQLiteHelper.COLUMN_FOOD_SERVINGSIZE, foodItem.getServingSize());
        values.put(HealthTrackerSQLiteHelper.COLUMN_FOOD_SERVINGUNIT, foodItem.getServingUnit());

        SQLiteDatabase db = mHealthTrackerSQLiteHelper.getWritableDatabase();

        db.insert(HealthTrackerSQLiteHelper.TABLE_FOODITEMS, null, values);
        db.close();
        return true;
    }

    public boolean deleteFoodItem(String foodname) {
        boolean result = false;

        String query = "Select * FROM" + HealthTrackerSQLiteHelper.TABLE_FOODITEMS + " WHERE" + HealthTrackerSQLiteHelper.COLUMN_FOODNAME + " = \"" + foodname + "\"";

        SQLiteDatabase db = mHealthTrackerSQLiteHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        FoodItem fooditem = new FoodItem();

        if (cursor.moveToFirst()) {
            fooditem.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(HealthTrackerSQLiteHelper.TABLE_FOODITEMS, HealthTrackerSQLiteHelper.COLUMN_FOOD_ID + " = ?",
                    new String[] {
                            String.valueOf(fooditem.getID())
                    });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public List<FoodItem> getAllFoodItems() {
        List<FoodItem> foodItems = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + HealthTrackerSQLiteHelper.TABLE_FOODITEMS;

        SQLiteDatabase db = mHealthTrackerSQLiteHelper.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                FoodItem foodItem = new FoodItem();
                foodItem.setID(c.getInt(c.getColumnIndex(HealthTrackerSQLiteHelper.COLUMN_FOOD_ID)));
                foodItem.setFoodName(c.getString(c.getColumnIndex(HealthTrackerSQLiteHelper.COLUMN_FOODNAME)));
                foodItem.setCalories(c.getInt(c.getColumnIndex(HealthTrackerSQLiteHelper.COLUMN_FOOD_CALORIES)));
                foodItem.setServingSize(c.getInt(c.getColumnIndex(HealthTrackerSQLiteHelper.COLUMN_FOOD_SERVINGSIZE)));
                foodItem.setServingUnit(c.getString(c.getColumnIndex(HealthTrackerSQLiteHelper.COLUMN_FOOD_SERVINGUNIT)));

                foodItems.add(foodItem);
            } while (c.moveToNext());
        }
        return foodItems;
    }

    public FoodItem getFoodItemAtIndex(int index) {
        FoodItem foodItem = new FoodItem();
        String selectQuery = "SELECT * FROM " + HealthTrackerSQLiteHelper.TABLE_FOODITEMS +
                " WHERE " + HealthTrackerSQLiteHelper.COLUMN_FOOD_ID + "=" + index;

        SQLiteDatabase db = mHealthTrackerSQLiteHelper.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            foodItem.setID(c.getInt(c.getColumnIndex(HealthTrackerSQLiteHelper.COLUMN_FOOD_ID)));
            foodItem.setFoodName(c.getString(c.getColumnIndex(HealthTrackerSQLiteHelper.COLUMN_FOODNAME)));
            foodItem.setCalories(c.getInt(c.getColumnIndex(HealthTrackerSQLiteHelper.COLUMN_FOOD_CALORIES)));
            foodItem.setServingSize(c.getInt(c.getColumnIndex(HealthTrackerSQLiteHelper.COLUMN_FOOD_SERVINGSIZE)));
            foodItem.setServingUnit(c.getString(c.getColumnIndex(HealthTrackerSQLiteHelper.COLUMN_FOOD_SERVINGUNIT)));
        }
        return foodItem;
    }

    public List<FoodItem> getAllFoodItemsForMeal(int currentMealId) {
        List<FoodItem> foodItems = new ArrayList<>();
        String junctionSelectQuery = "SELECT * FROM " + HealthTrackerSQLiteHelper.TABLE_MEALITEMS_FOODITEMS +
                " WHERE " + HealthTrackerSQLiteHelper.COLUMN_MEALID + "=" + currentMealId;

        SQLiteDatabase db = mHealthTrackerSQLiteHelper.getReadableDatabase();
        Cursor c = db.rawQuery(junctionSelectQuery, null);
        List<Integer> foodIds = new ArrayList<>();

        if (c.moveToFirst()) {
            do {
                foodIds.add(c.getInt(c.getColumnIndex(HealthTrackerSQLiteHelper.COLUMN_FOODID)));
            } while (c.moveToNext());
        }

        for (int i = 0; i < foodIds.size(); i++) {
            String foodSelectQuery = "SELECT * FROM " + HealthTrackerSQLiteHelper.TABLE_FOODITEMS + " WHERE " +
                    HealthTrackerSQLiteHelper.COLUMN_FOOD_ID + "=" + foodIds.get(i);
            SQLiteDatabase db2 = mHealthTrackerSQLiteHelper.getReadableDatabase();
            Cursor c2 = db2.rawQuery(foodSelectQuery, null);
            if (c2.moveToFirst()) {
                do {
                    FoodItem foodItem = new FoodItem();
                    foodItem.setID(c2.getInt(c2.getColumnIndex(HealthTrackerSQLiteHelper.COLUMN_FOOD_ID)));
                    foodItem.setFoodName(c2.getString(c2.getColumnIndex(HealthTrackerSQLiteHelper.COLUMN_FOODNAME)));
                    foodItem.setCalories(c2.getInt(c2.getColumnIndex(HealthTrackerSQLiteHelper.COLUMN_FOOD_CALORIES)));
                    foodItem.setServingSize(c2.getInt(c2.getColumnIndex(HealthTrackerSQLiteHelper.COLUMN_FOOD_SERVINGSIZE)));
                    foodItem.setServingUnit(c2.getString(c2.getColumnIndex(HealthTrackerSQLiteHelper.COLUMN_FOOD_SERVINGUNIT)));

                    foodItems.add(foodItem);
                } while (c2.moveToNext());
            }
        }
        return foodItems;
    }

    public List<String> getAllFoodItemNames() {
        List<String> foodNames = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + HealthTrackerSQLiteHelper.TABLE_FOODITEMS;

        SQLiteDatabase db = mHealthTrackerSQLiteHelper.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                foodNames.add(c.getString(c.getColumnIndex(HealthTrackerSQLiteHelper.COLUMN_FOODNAME)));
            } while (c.moveToNext());
        }
        return foodNames;
    }
}
