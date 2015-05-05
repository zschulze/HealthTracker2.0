package com.zachschulze.healthtracker20;

import android.test.ActivityInstrumentationTestCase2;

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

    public void testFoodItemAdder01() {
        FoodItem foodItemTest = new FoodItem("", -1, -1, "");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(false, successful);
    }

    public void testFoodItemAdder02() {
        FoodItem foodItemTest = new FoodItem("Corn", 100, 1, "Cup");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(true, successful);
    }

    public void testFoodItemAdder03() {
        FoodItem foodItemTest = new FoodItem("", 100, 1, "Cup");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(false, successful);
    }

    public void testFoodItemAdder04() {
        FoodItem foodItemTest = new FoodItem("Corn", -1, -1, "Cup");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(false, successful);
    }

    public void testFoodItemAdder05() {
        FoodItem foodItemTest = new FoodItem("Broccoli", 150, 2, "Cups");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(true, successful);
    }

    public void testFoodItemAdder06() {
        FoodItem foodItemTest = new FoodItem("Frosted Flakes", 150, 3, "cups");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(true, successful);
    }

    public void testFoodItemAdder07() {
        FoodItem foodItemTest = new FoodItem("", 150, 3, "cups");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(false, successful);
    }

    public void testFoodItemAdder08() {
        FoodItem foodItemTest = new FoodItem("", -1, 3, "cups");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(false, successful);
    }

    public void testFoodItemAdder09() {
        FoodItem foodItemTest = new FoodItem("Frosted Flakes", 150, 3, "");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(false, successful);
    }

    public void testFoodItemAdder10() {
        FoodItem foodItemTest = new FoodItem("Steak", 850, 1, "Pound");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(true, successful);
    }

    public void testFoodItemAdder11() {
        FoodItem foodItemTest = new FoodItem("Steak", -1, 1, "Pound");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(false, successful);
    }

    public void testFoodItemAdder12() {
        FoodItem foodItemTest = new FoodItem("Water", 0, 1, "Liter");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(true, successful);
    }

    public void testFoodItemAdder13() {
        FoodItem foodItemTest = new FoodItem("Steak", 2147483647, 1, "Pound");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(true, successful);
    }

    /* Can't test an integer > 32 bits, if user inputs an integer > 32 bits the app will crash
    public void testFoodItemAdder14() {
        FoodItem foodItemTest = new FoodItem("Steak", 2147483648, 1, "Pound");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(true, successful);
    }*/

    /* In this case "XXX" represents a String > 32 bits, if user inputs a String > 32 bits
        the app will crash
    public void testFoodItemAdder15() {
        FoodItem foodItemTest = new FoodItem("XXX", 100, 1, "Pound");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(true, successful);
    }*/

    /* In this case "XXXX" represents a String > 64 bits, if user inputs a String > 32 bits
        the app will crash
    public void testFoodItemAdder16() {
        FoodItem foodItemTest = new FoodItem("XXXX", 100, 1, "Pound");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(true, successful);
    }*/

    /* In this case "XXXX" represents a int > 64 bits, if user inputs a String > 32 bits
        the app will crash
    public void testFoodItemAdder17() {
        FoodItem foodItemTest = new FoodItem("Steak", XXXX, -1, "");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(true, successful);
    }*/

    /* Can't test decimal numbers as the user cannot enter decimal numbers
    public void testFoodItemAdder18() {
        FoodItem foodItemTest = new FoodItem("Steak", .000001, 1, "Pound");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(true, successful);
    }*/

    public void testFoodItemAdder19() {
        FoodItem foodItemTest = new FoodItem("S", 850, 1, "Pound");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(true, successful);
    }

    public void testFoodItemAdder20() {
        FoodItem foodItemTest = new FoodItem("S", 1, 1, "g");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(true, successful);
    }

    public void testFoodItemAdder21() {
        FoodItem foodItemTest = new FoodItem("", -1, -1, "Cup");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(false, successful);
    }

    public void testFoodItemAdder22() {
        FoodItem foodItemTest = new FoodItem("Water", 0, 0, "Liter");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(true, successful);
    }

    // In this case "XXXXX" represents a really long string that is < 32 bits
    public void testFoodItemAdder23() {
        FoodItem foodItemTest = new FoodItem("XXXXX", 100, 1, "Cup");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(true, successful);
    }

    public void testFoodItemAdder24() {
        FoodItem foodItemTest = new FoodItem("100", 100, 100, "100");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(true, successful);
    }

    /* User cannot input letters for calories and serving size attributes
    public void testFoodItemAdder25() {
        FoodItem foodItemTest = new FoodItem("Steak", "Steak", "Steak", "Steak");
        boolean successful = mFoodItemDataSource.addFoodItem(foodItemTest);
        assertEquals(true, successful);
    }*/

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
