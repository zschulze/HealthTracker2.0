package com.zachschulze.healthtracker20.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zachschulze.healthtracker20.R;
import com.zachschulze.healthtracker20.adapters.FoodItemSpinnerAdapter;
import com.zachschulze.healthtracker20.database.FoodItemDataSource;
import com.zachschulze.healthtracker20.database.MealItemDataSource;
import com.zachschulze.healthtracker20.models.FoodItem;
import com.zachschulze.healthtracker20.models.MealItem;

import java.util.ArrayList;
import java.util.List;


public class AddMealItemActivity extends ActionBarActivity {
    private EditText mMealName;
    private EditText mCalories;
    private EditText mNumServings;
    private Button submit;
    private FoodItemSpinnerAdapter spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal_item);

        mMealName = (EditText) findViewById(R.id.mealName);
        mCalories = (EditText) findViewById(R.id.calories);
        mNumServings = (EditText) findViewById(R.id.numServings);

        submit = (Button) findViewById(R.id.submitMealItem);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit();
            }
        });

        FoodItemDataSource dataSource = new FoodItemDataSource(this);
        List<String> foodNames = dataSource.getAllFoodItemNames();
        spinner = (FoodItemSpinnerAdapter) findViewById(R.id.mySpinner1);
        spinner.setItems(foodNames);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_meal_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //List<Integer> foodIndices = spinner.getSelectedIndicies();
    //Toast.makeText(getApplicationContext(), indices.toString(), Toast.LENGTH_LONG).show();

    public void onSubmit() {
        String invalidMessage = "Please input:";
        Boolean valid = true;
        String mealName = mMealName.getText().toString();

        if (mealName.equals("")) {
            invalidMessage += " (food name)";
            valid = false;
        }
        if (mCalories.getText().toString().equals("")) {
            invalidMessage += " (calories)";
            valid = false;
        }
        if (mNumServings.getText().toString().equals("")) {
            invalidMessage += " (serving size)";
            valid = false;
        }
        if (valid) {
            int calories = Integer.parseInt(mCalories.getText().toString());
            int numServings = Integer.parseInt(mNumServings.getText().toString());

            List<Integer> foodIndices = spinner.getSelectedIndicies();

            FoodItemDataSource foodItemDataSource = new FoodItemDataSource(this);

            List<FoodItem> foodItems = new ArrayList<>();

            for (int i = 1; i < (foodIndices.size() + 1); i++) {
                FoodItem foodItem = foodItemDataSource.getFoodItemAtIndex(i);
                foodItems.add(foodItem);
            }

            MealItem mealItem = new MealItem(mealName, calories, numServings, foodItems);

            MealItemDataSource mealItemDataSource = new MealItemDataSource(this);
            mealItemDataSource.addMealItem(mealItem);

            finish();
        } else {
            Toast.makeText(this, invalidMessage, Toast.LENGTH_LONG).show();
        }
    }
}
