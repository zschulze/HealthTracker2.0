package com.zachschulze.healthtracker20.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.zachschulze.healthtracker20.ui.activities.AddFoodItemActivity;
import com.zachschulze.healthtracker20.ui.activities.AddMealItemActivity;
import com.zachschulze.healthtracker20.R;

/**
 * Created by Zach on 3/24/2015.
 */
public class FoodsAndMealsFragment extends Fragment {
    private FragmentTabHost mTabHost;

    public FoodsAndMealsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mTabHost = new FragmentTabHost(getActivity());
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.layout.fragment_foodsandmeals);

        mTabHost.addTab(mTabHost.newTabSpec("foods").setIndicator("Foods"),
                FoodsTab.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("meals").setIndicator("Meals"),
                MealsTab.class, null);

        setHasOptionsMenu(true);

        return mTabHost;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_foodsandmealsfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch(item.getItemId()) {
            case R.id.add_item:
                int current = mTabHost.getCurrentTab();
                if (current == 0) {
                    Intent intent = new Intent(super.getActivity(), AddFoodItemActivity.class);
                    startActivity(intent);
                }
                else if (current == 1) {
                    Intent intent = new Intent(super.getActivity(), AddMealItemActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(super.getActivity(), AddMealItemActivity.class);
                    startActivity(intent);
                }
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
    }
}

