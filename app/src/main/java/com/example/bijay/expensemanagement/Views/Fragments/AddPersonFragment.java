package com.example.bijay.expensemanagement.Views.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bijay.expensemanagement.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPersonFragment extends Fragment {
    private static final String TAG = AddPersonFragment.class.getSimpleName();

    public AddPersonFragment() {
        // Required empty public constructor
        Log.d(TAG, "[AddPersonFragment] constructor initialization is done");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_person, container, false);

        Log.d(TAG, "[onCreateView] Inflate the layout for this fragment is done");
        return view;
    }

}
