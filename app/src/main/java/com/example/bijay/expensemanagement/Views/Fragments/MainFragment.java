package com.example.bijay.expensemanagement.Views.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bijay.expensemanagement.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = MainFragment.class.getSimpleName();

    private Button btnPrepareExpensesList;
    private Button btnAddExpenses;
    private Button btnViewExpenses;

    private Button btnAddExpenseGroup;
    private Button btnViewExpenseGroup;

    private Button btnSetLimit;
    private Button btnAddPerson;
    private Button btnViewPeople;

    public MainFragment() {
        Log.d(TAG, "[MainFragment] constructor initialization is done");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        btnPrepareExpensesList = view.findViewById(R.id.btnPrepareExpensesList);
        btnAddExpenses = view.findViewById(R.id.btnAddExpenses);
        btnViewExpenses = view.findViewById(R.id.btnViewExpenses);

        btnAddExpenseGroup = view.findViewById(R.id.btnAddGroup);
        btnViewExpenseGroup = view.findViewById(R.id.btnViewGroups);

        btnAddPerson = view.findViewById(R.id.btnAddPerson);
        btnViewPeople = view.findViewById(R.id.btnViewPerson);
        btnSetLimit = view.findViewById(R.id.btnSetLimit);

        btnPrepareExpensesList.setOnClickListener(this);
        btnAddExpenses.setOnClickListener(this);
        btnViewExpenses.setOnClickListener(this);

        btnAddExpenseGroup.setOnClickListener(this);
        btnViewExpenseGroup.setOnClickListener(this);

        btnAddPerson.setOnClickListener(this);
        btnViewPeople.setOnClickListener(this);
        btnSetLimit.setOnClickListener(this);

        Log.d(TAG, "[onCreateView] is done");
        return  view;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (v.getId()) {
            case R.id.btnPrepareExpensesList:
                fragmentTransaction.replace(R.id.idMainActivityLayout, new PrepareExpenseListFragment(), "PrepareExpenseListFragment");
                Log.d(TAG, "[onClick][btnPrepareExpensesList] Redirecting to [PrepareExpenseListFragment]");
                break;

            case R.id.btnAddExpenses:
                fragmentTransaction.replace(R.id.idMainActivityLayout, new AddExpenseFragment(), "AddExpenseFragment");
                Log.d(TAG, "[onClick][btnAddExpenses] Redirecting to [AddExpenseFragment]");
                break;

            case R.id.btnViewExpenses:
                fragmentTransaction.add(new ExpensesFilterFragment(), "ExpensesFilterFragment");
                Log.d(TAG, "[onClick][btnViewExpenses] Redirecting to [ExpensesFilterFragment] dialog Fragment");
                break;

            case R.id.btnAddGroup:
                fragmentTransaction.replace(R.id.idMainActivityLayout, new AddExpenseGroupFragment(), "AddExpenseGroupFragment");
                Log.d(TAG, "[onClick][btnAddExpensesGroup] Redirecting to [AddExpenseGroupFragment]");
                break;

            case R.id.btnViewGroups:
                fragmentTransaction.replace(R.id.idMainActivityLayout, new ViewExpensesGroupsFragment(), "ViewExpensesGroupsFragment");
                Log.d(TAG, "[onClick][btnViewGroups] Redirecting to [ViewExpensesGroupsFragment]");
                break;

            case R.id.btnSetLimit:
                //fragmentTransaction.add(null, "ExpensesFilterFragment");
                Log.d(TAG, "[onClick][btnSetLimit] Redirecting to [SetLimitFragment] not implemented");
                break;

            case R.id.btnAddPerson:
                fragmentTransaction.replace(R.id.idMainActivityLayout, new AddPersonFragment(), "AddPersonFragment");
                Log.d(TAG, "[onClick][btnAddPerson] Redirecting to [AddPersonFragment]");
                break;

            case R.id.btnViewPerson:
                fragmentTransaction.replace(R.id.idMainActivityLayout, new ViewPersonsFragment(), "ViewPersonsFragment");
                Log.d(TAG, "[onClick][btnViewPerson] Redirecting to [ViewPersonsFragment]");
                break;

            default:
                Log.d(TAG, "[onClick][default] Not Implemented");
                break;
        }

        fragmentTransaction.disallowAddToBackStack();
        fragmentTransaction.commit();
    }
}
