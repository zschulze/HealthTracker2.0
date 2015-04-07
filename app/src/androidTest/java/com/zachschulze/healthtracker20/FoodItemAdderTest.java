package com.zachschulze.healthtracker20;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import com.zachschulze.healthtracker20.database.FoodItemDataSource;
import com.zachschulze.healthtracker20.models.FoodItem;
import com.zachschulze.healthtracker20.ui.activities.MainActivity;

/**
 * Created by Zach on 4/7/2015.
 */
public class FoodItemAdderTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mMainActivity;
    private FoodItemDataSource mFoodItemDataSource;

    public FoodItemAdderTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mMainActivity = getActivity();
        mFoodItemDataSource = new FoodItemDataSource(mMainActivity);
    }

    @SmallTest
    public void testFoodItemAdder1() {
        FoodItem foodItemTest = new FoodItem("", -1, -1, "");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(true, successful);
    }

    public void testFoodItemAdder2() {
        FoodItem foodItemTest = new FoodItem("Corn", 100, 1, "Cup");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(true, successful);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
