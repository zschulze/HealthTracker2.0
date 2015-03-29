package com.zachschulze.healthtracker20.ui.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zachschulze.healthtracker20.R;

/**
 * Created by Zach on 3/24/2015.
 */
public class MealPlansFragment extends Fragment {
    public MealPlansFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mealplans, container, false);

        return rootView;
    }
}
