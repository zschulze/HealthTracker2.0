package com.zachschulze.healthtracker20.models;

import java.util.List;

/**
 * Created by Zach on 6/23/2015.
 */
public class MealItem {
    private int _id;
    private String _mealname;
    private int _calories;
    private int _numservings;
    private List<FoodItem> _foodItems;

    public MealItem() {

    }

    public MealItem(int id, String mealname, int calories, int numservings, List<FoodItem> foodItems) {
        this._id = id;
        this._mealname = mealname;
        this._calories = calories;
        this._numservings = numservings;
        this._foodItems = foodItems;
    }

    public MealItem(String mealname, int calories, int numservings, List<FoodItem> foodItems) {
        this._mealname = mealname;
        this._calories = calories;
        this._numservings = numservings;
        this._foodItems = foodItems;
    }

    public void setID(int id) {
        this._id = id;
    }

    public int getID() {
        return this._id;
    }

    public void setMealName(String mealname) {
        this._mealname = mealname;
    }

    public String getMealName() {
        return this._mealname;
    }

    public void setCalories(int calories) {
        this._calories = calories;
    }

    public int getCalories() {
        return this._calories;
    }

    public void setNumServings(int numservings) {
        this._numservings = numservings;
    }

    public int getNumServings() {
        return this._numservings;
    }

    public void setFoodItems(List<FoodItem> foodItems) {
        this._foodItems = foodItems;
    }

    public List<FoodItem> getFoodItems () {
        return this._foodItems;
    }

    public String toString() {
        return (_mealname);
    }
}
