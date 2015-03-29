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
import com.zachschulze.healthtracker20.database.FoodItemDataSource;
import com.zachschulze.healthtracker20.models.FoodItem;


public class AddFoodItemActivity extends ActionBarActivity {
    EditText mFoodName;
    EditText mCalories;
    EditText mServingSize;
    EditText mServingUnit;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_item);

        mFoodName = (EditText) findViewById(R.id.foodName);
        mCalories = (EditText) findViewById(R.id.calories);
        mServingSize = (EditText) findViewById(R.id.servingSize);
        mServingUnit = (EditText) findViewById(R.id.servingUnit);

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_food_item, menu);
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

    public void onSubmit() {
        String invalidMessage = "Please input:";
        Boolean valid = true;
        String foodName = mFoodName.getText().toString();
        String servingUnit = mServingUnit.getText().toString();

        if (foodName.equals("")) {
            invalidMessage += " (food name)";
            valid = false;
        }
        if (mCalories.getText().toString().equals("")) {
            invalidMessage += " (calories)";
            valid = false;
        }
        if (mServingSize.getText().toString().equals("")) {
            invalidMessage += " (serving size)";
            valid = false;
        }
        if (servingUnit.equals("")) {
            invalidMessage += " (serving unit)";
            valid = false;
        }
        if (valid) {
            int calories = Integer.parseInt(mCalories.getText().toString());
            int servingSize = Integer.parseInt(mServingSize.getText().toString());

            FoodItem foodItem = new FoodItem(foodName, calories, servingSize, servingUnit);

            FoodItemDataSource dataSource = new FoodItemDataSource(this);
            dataSource.addFoodItem(foodItem);

            finish();
        } else {
            Toast.makeText(this, invalidMessage, Toast.LENGTH_LONG).show();
        }
    }
}
