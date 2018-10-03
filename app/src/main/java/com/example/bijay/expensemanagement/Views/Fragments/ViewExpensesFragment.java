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
public class ViewExpensesFragment extends Fragment {

    private static final String TAG = ViewExpensesFragment.class.getSimpleName();

    public ViewExpensesFragment() {
        Log.d(TAG, "[ViewExpensesFragment] constructor initialization is done");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "[onCreateView] Inflate the layout for this fragment is done");

        return inflater.inflate(R.layout.fragment_view_expenses, container, false);
    }

}
