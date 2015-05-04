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
                String s = spinner.getSelectedItemsAsString();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
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

    public void onSubmit() {

    }
}
