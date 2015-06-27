package com.zachschulze.healthtracker20.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zachschulze.healthtracker20.R;
import com.zachschulze.healthtracker20.models.FoodItem;
import com.zachschulze.healthtracker20.models.MealItem;

import java.util.List;

/**
 * Created by Zach on 6/23/2015.
 */
public class MealItemListAdapter extends BaseAdapter {
    private List<MealItem> mealItems;
    private Context context;

    public MealItemListAdapter(Context context, List<MealItem> mealItems) {
        this.context = context;
        this.mealItems = mealItems;
    }

    @Override
    public int getCount() {
        return mealItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mealItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mealItems.indexOf(getItem(i));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.tab_meals, null);
        }

        TextView textView = (TextView)convertView.findViewById(R.id.mealInfo);
        MealItem mealItem = this.mealItems.get(position);
        String mealName = mealItem.getMealName();
        int calories = mealItem.getCalories();
        int numServings = mealItem.getNumServings();
        String foodItems = "";
        List<FoodItem> foodItemsList = mealItem.getFoodItems();
        for (int i = 0; i < foodItemsList.size(); i++) {
            FoodItem currentFoodItem = foodItemsList.get(i);
            foodItems += currentFoodItem.getFoodName() + " ";
        }
        textView.setText(mealName + " (" + calories + " calories, number of servings - " + numServings + ")" +
            "\n \t (food items - " + foodItems + ")");

        return convertView;
    }
}
