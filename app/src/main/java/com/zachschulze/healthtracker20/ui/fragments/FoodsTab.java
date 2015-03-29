package com.zachschulze.healthtracker20.ui.fragments;

/**
 * Created by Zach on 3/25/2015.
 */

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.zachschulze.healthtracker20.R;
import com.zachschulze.healthtracker20.adapters.FoodItemListAdapter;
import com.zachschulze.healthtracker20.database.FoodItemDataSource;
import com.zachschulze.healthtracker20.models.FoodItem;

import java.util.List;

public class FoodsTab extends ListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_foods, container, false);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FoodItemDataSource dataSource = new FoodItemDataSource(this.getActivity());
        List<FoodItem> foodItems = dataSource.getAllFoodItems();

        ListAdapter adapter = new FoodItemListAdapter(getActivity(), foodItems);
        setListAdapter(adapter);
    }

    @Override
    public void onResume() {
        FoodItemDataSource dataSource = new FoodItemDataSource(this.getActivity());
        List<FoodItem> foodItems = dataSource.getAllFoodItems();

        ListAdapter adapter = new FoodItemListAdapter(getActivity(), foodItems);
        setListAdapter(adapter);
        super.onResume();
    }
}