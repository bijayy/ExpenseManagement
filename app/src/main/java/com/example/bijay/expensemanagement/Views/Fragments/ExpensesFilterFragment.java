package com.example.bijay.expensemanagement.Views.Fragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.bijay.expensemanagement.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpensesFilterFragment extends DialogFragment implements RadioGroup.OnCheckedChangeListener {

    private static final String TAG = ExpensesFilterFragment.class.getSimpleName();

    private RadioGroup radioBtnGroupFilters;

    public ExpensesFilterFragment() {
        // Required empty public constructor
        Log.d(TAG, "[ExpensesFilterFragment] constructor initialization is done");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expenses_filter, container, false);

        radioBtnGroupFilters = view.findViewById(R.id.radioBtnGroupExpensesFilters);
        radioBtnGroupFilters.setOnCheckedChangeListener(this);

        Log.d(TAG, "[onCreateView] is working fine");
        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (checkedId)
        {
            case R.id.radioBtnFilterByExpensesGroup:
                fragmentTransaction.replace(R.id.idMainActivityLayout, new ViewExpensesGroupsFragment(), "ViewExpensesGroupsFragment");
                Log.d(TAG, "[onCheckedChanged][radioBtnFilterByExpensesGroup] Redirecting to [ViewExpensesGroupsFragment]");
                break;

            case R.id.radioBtnFilterByExpenses:
                fragmentTransaction.replace(R.id.idMainActivityLayout, new ViewExpensesFragment(), "ViewExpensesFragment");
                Log.d(TAG, "[onCheckedChanged][radioBtnFilterByExpenses] Redirecting to [ViewExpensesFragment]");
                break;

            case R.id.radioBtnFilterByPersons:
                fragmentTransaction.replace(R.id.idMainActivityLayout, new ViewPersonsFragment(), "ViewPersonsFragment");
                Log.d(TAG, "[onCheckedChanged][radioBtnFilterByExpensesGroup] Redirecting to [ViewPersonsFragment]");
                break;

            case R.id.radioBtnFilterByDates:
                fragmentTransaction.replace(R.id.idMainActivityLayout, new ViewExpenseByDateRangeFragment(), "ViewExpenseByDateRangeFragment");
                Log.d(TAG, "[onCheckedChanged][radioBtnFilterByDates] Redirecting to [ViewExpenseByDateRangeFragment]");
                break;

            default:
                Log.d(TAG, "[onCheckedChanged][default] Not Implemented");
                break;
        }

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        this.getDialog().dismiss();

        Log.d(TAG, "Radio Group: "+ group +", Checked: " + checkedId);
    }
}
