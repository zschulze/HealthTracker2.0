package com.zachschulze.healthtracker20.models;

/**
 * Created by Zach on 3/28/2015.
 */
public class FoodItem {
    private int _id;
    private String _foodname;
    private int _calories;
    private int _servingsize;
    private String _servingunit;

    public FoodItem() {

    }

    public FoodItem(int id, String foodname, int calories, int servingsize, String servingunit) {
        this._id = id;
        this._foodname = foodname;
        this._calories = calories;
        this._servingsize = servingsize;
        this._servingunit = servingunit;
    }

    public FoodItem(String foodname, int calories, int servingsize, String servingunit) {
        this._foodname = foodname;
        this._calories = calories;
        this._servingsize = servingsize;
        this._servingunit = servingunit;
    }

    public void setID(int id) {
        this._id = id;
    }

    public int getID() {
        return this._id;
    }

    public void setFoodName(String foodname) {
        this._foodname = foodname;
    }

    public String getFoodName() {
        return this._foodname;
    }

    public void setCalories(int calories) {
        this._calories = calories;
    }

    public int getCalories() {
        return this._calories;
    }

    public void setServingSize(int servingsize) {
        this._servingsize = servingsize;
    }

    public int getServingSize() {
        return this._servingsize;
    }

    public void setServingUnit(String servingunit) {
        this._servingunit = servingunit;
    }

    public String getServingUnit() {
        return this._servingunit;
    }
}
