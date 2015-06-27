package com.zachschulze.healthtracker20.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.zachschulze.healthtracker20.R;
import com.zachschulze.healthtracker20.adapters.MealItemListAdapter;
import com.zachschulze.healthtracker20.database.MealItemDataSource;
import com.zachschulze.healthtracker20.models.MealItem;

import java.util.List;

/**
 * Created by Zach on 3/28/2015.
 */
public class MealsTab extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_meals, container, false);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MealItemDataSource dataSource = new MealItemDataSource(this.getActivity());
        List<MealItem> mealItems = dataSource.getAllMealItems();

        ListAdapter adapter = new MealItemListAdapter(getActivity(), mealItems);
        setListAdapter(adapter);
    }

    @Override
    public void onResume() {
        MealItemDataSource dataSource = new MealItemDataSource(this.getActivity());
        List<MealItem> mealItems = dataSource.getAllMealItems();

        ListAdapter adapter = new MealItemListAdapter(getActivity(), mealItems);
        setListAdapter(adapter);
        super.onResume();
    }
}