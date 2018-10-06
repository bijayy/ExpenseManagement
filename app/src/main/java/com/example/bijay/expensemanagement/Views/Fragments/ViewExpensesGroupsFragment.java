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

import com.example.bijay.expensemanagement.Adapter.ExpenseGroupsRecyclerAdapter;
import com.example.bijay.expensemanagement.Data.Sqlite.Adapter.ExpensesGroupSqliteDatabaseAdapter;
import com.example.bijay.expensemanagement.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ViewExpensesGroupsFragment extends Fragment {

    private static final String TAG = ViewExpensesGroupsFragment.class.getSimpleName();

    private RecyclerView expenseGroupRecyclerView;

    public ViewExpensesGroupsFragment() {
        Log.d(TAG, "[ViewExpensesGroupsFragment] constructor initialization is done");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "[onCreateView] Inflate the layout for this fragment is done");

        View view = inflater.inflate(R.layout.fragment_view_expenses_groups, container, false);
        expenseGroupRecyclerView = view.findViewById(R.id.expenseGroupRecyclerView);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        ExpensesGroupSqliteDatabaseAdapter expensesGroupSqliteDatabaseAdapter = new ExpensesGroupSqliteDatabaseAdapter(getActivity());

        //Calling the custom recycler view adapter i.e. MyRecyclerAdapter
        ExpenseGroupsRecyclerAdapter recyclerAdapter = new ExpenseGroupsRecyclerAdapter(getActivity(), expensesGroupSqliteDatabaseAdapter.getExpensesGroups());
        expenseGroupRecyclerView.setAdapter(recyclerAdapter);

        //Creating the layout of the data to be displayed in the recycler view i.e. in linear with vertical arrangement
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        expenseGroupRecyclerView.setLayoutManager(linearLayoutManager);

        //Setting default animation of items in the recycler view. Note: it's default and it's not required to add.
        expenseGroupRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
