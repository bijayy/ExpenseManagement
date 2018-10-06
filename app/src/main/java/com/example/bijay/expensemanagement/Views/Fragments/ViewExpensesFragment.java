package com.example.bijay.expensemanagement.Views.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bijay.expensemanagement.Adapter.ExpensesRecyclerAdapter;
import com.example.bijay.expensemanagement.Adapter.PersonsRecyclerAdapter;
import com.example.bijay.expensemanagement.Data.Sqlite.Adapter.ExpensesSqliteDatabaseAdapter;
import com.example.bijay.expensemanagement.Data.Sqlite.Adapter.PersonSqliteDatabaseAdapter;
import com.example.bijay.expensemanagement.Models.ExpensesModel;
import com.example.bijay.expensemanagement.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewExpensesFragment extends Fragment {

    private static final String TAG = ViewExpensesFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private TextView tvTotalExpenses;

    public ViewExpensesFragment() {
        Log.d(TAG, "[ViewExpensesFragment] constructor initialization is done");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_expenses, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        tvTotalExpenses = view.findViewById(R.id.tvTotalExpenses);

        Log.d(TAG, "[onCreateView] Inflate the layout for this fragment is done");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showExpenses();
        Log.d(TAG, "[onActivityCreated] called, calling [showExpenses] method");
    }

    private void showExpenses() {
        ExpensesSqliteDatabaseAdapter expensesSqliteDatabaseAdapter = new ExpensesSqliteDatabaseAdapter(getContext());

        //Calling the custom recycler view adapter i.e. MyRecyclerAdapter
        List<ExpensesModel> expensesModels = expensesSqliteDatabaseAdapter.getExpenses();
        ExpensesRecyclerAdapter recyclerAdapter = new ExpensesRecyclerAdapter(getActivity(), expensesModels);
        recyclerView.setAdapter(recyclerAdapter);

        calculateTotalExpenses(expensesModels);
        //Creating the layout of the data to be displayed in the recycler view i.e. in linear with vertical arrangement
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        //Setting default animation of items in the recycler view. Note: it's default and it's not required to add.
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Log.d(TAG, "[showPeople] People fetched successfully");
    }

    private void calculateTotalExpenses(List<ExpensesModel> expensesModels) {
        float totalExpenses = 0;

        for(ExpensesModel expensesModel : expensesModels) {
            if(!expensesModel.Amount.isEmpty() && expensesModel.Amount.matches("^[0-9]+$"))
                totalExpenses += Float.parseFloat(expensesModel.Amount);
        }

        tvTotalExpenses.setText(totalExpenses + "");
    }
}
