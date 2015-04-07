package com.zachschulze.healthtracker20.ui.activities;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.zachschulze.healthtracker20.R;
import com.zachschulze.healthtracker20.database.FoodItemDataSource;
import com.zachschulze.healthtracker20.models.FoodItem;

import java.util.List;


public class AddMealItemActivity extends ActionBarActivity {
    EditText mMealName;
    EditText mCalories;
    EditText mNumServings;
    Spinner foodItemsSpinner;
    Button submit;

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

        loadSpinnerData();
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

    public void loadSpinnerData() {
        FoodItemDataSource dataSource = new FoodItemDataSource(getApplicationContext());
        List<FoodItem> foodItems = dataSource.getAllFoodItems();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, foodItems);

        // Create a boolean array to store information of the selected values
        // all values should default to false

        boolean[] checkSelected = new boolean[adapter.getCount()];

        LayoutInflater inflater = (LayoutInflater)AddMealItemActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.pop_up_window_food_items, (ViewGroup)findViewById(R.id.FoodItemsPopUp));

        final PopupWindow pw = new PopupWindow(layout, ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);

        pw.setBackgroundDrawable(new BitmapDrawable());
        pw.setTouchable(true);
        pw.setOutsideTouchable(true);
        pw.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    pw.dismiss();
                    return true;
                }
                return false;
            }
        });

        pw.setContentView(layout);
        RelativeLayout layout1 = (RelativeLayout) findViewById(R.id.addMealItem);
        pw.showAsDropDown(layout1);

        final ListView list = (ListView) layout.findViewById(R.DropDownList.dropDownList);

    }

    public void onSubmit() {

    }
}
