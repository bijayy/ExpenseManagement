package com.example.bijay.expensemanagement.Views.Fragments;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.bijay.expensemanagement.Adapter.ExpenseGroupsRecyclerAdapter;
import com.example.bijay.expensemanagement.Data.Sqlite.Adapter.ExpensesGroupSqliteDatabaseAdapter;
import com.example.bijay.expensemanagement.Models.ExpensesGroupModel;
import com.example.bijay.expensemanagement.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddExpenseGroupFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = AddExpenseGroupFragment.class.getSimpleName();

    private EditText etGroupName;
    private RecyclerView expenseGroupRecyclerView;
    private Button btnAddGroup;

    public AddExpenseGroupFragment() {
        // Required empty public constructor
        Log.d(TAG, "[AddExpenseGroupFragment] constructor initialization is done");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_expense_group, container, false);
        expenseGroupRecyclerView = view.findViewById(R.id.expenseGroupRecyclerView);
        etGroupName = view.findViewById(R.id.etAddGroup);
        btnAddGroup = view.findViewById(R.id.btnAddExpensesGroup);

        btnAddGroup.setOnClickListener(this);
        getExpenseGroups();;

        Log.d(TAG, "[onCreateView] Inflate the layout for this fragment is done");
        return view;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddExpensesGroup:
                addExpenseGroup();
                getExpenseGroups();

                Log.d(TAG, "[onClick] [btnAddGroup] is called for [addExpenseGroup] then [getExpenseGroups]");
                break;

            default:
                Log.d(TAG, "[onClick][default] Not Implemented");
                break;
        }
    }

    private void addExpenseGroup() {
        ExpensesGroupSqliteDatabaseAdapter expensesGroupSqliteDatabaseAdapter = new ExpensesGroupSqliteDatabaseAdapter(getContext());
        ExpensesGroupModel expensesGroupModel = new ExpensesGroupModel();
        expensesGroupModel.GroupName = etGroupName.getText().toString();
        expensesGroupSqliteDatabaseAdapter.addExpensesGroup(expensesGroupModel);

        Snackbar.make(etGroupName, Html.fromHtml("<font color=\"#00ff99\">Expense Group: " + expensesGroupModel.GroupName + "added successfully</font>"), Snackbar.LENGTH_SHORT).show();
        Log.d(TAG, "[onClick][btnAddGroup] Expense group: " + expensesGroupModel.GroupName + " added successfully");
    }

    private void getExpenseGroups() {
        ExpensesGroupSqliteDatabaseAdapter expensesGroupSqliteDatabaseAdapter = new ExpensesGroupSqliteDatabaseAdapter(getContext());

        //Calling the custom recycler view adapter i.e. MyRecyclerAdapter
        ExpenseGroupsRecyclerAdapter recyclerAdapter = new ExpenseGroupsRecyclerAdapter(getActivity(), expensesGroupSqliteDatabaseAdapter.getExpensesGroups());
        expenseGroupRecyclerView.setAdapter(recyclerAdapter);

        //Creating the layout of the data to be displayed in the recycler view i.e. in linear with vertical arrangement
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        expenseGroupRecyclerView.setLayoutManager(linearLayoutManager);

        //Setting default animation of items in the recycler view. Note: it's default and it's not required to add.
        expenseGroupRecyclerView.setItemAnimator(new DefaultItemAnimator());

        Log.d(TAG, "[getExpenseGroups] Expense group fetched successfully");
    }
}
