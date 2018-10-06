package com.example.bijay.expensemanagement.Views.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bijay.expensemanagement.Adapter.ExpenseGroupsRecyclerAdapter;
import com.example.bijay.expensemanagement.Data.Sqlite.Adapter.ExpensesGroupSqliteDatabaseAdapter;
import com.example.bijay.expensemanagement.Models.ExpensesGroupModel;
import com.example.bijay.expensemanagement.R;

import java.util.List;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ViewExpensesGroupsFragment extends Fragment implements ExpenseGroupsRecyclerAdapter.IEditDeleteClickListener {

    private static final String TAG = ViewExpensesGroupsFragment.class.getSimpleName();

    private RecyclerView expenseGroupRecyclerView;
    private SendDataBetweenFragment dataBetweenFragment;

    public ViewExpensesGroupsFragment() {
        Log.d(TAG, "[ViewExpensesGroupsFragment] constructor initialization is done");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view_expenses_groups, container, false);
        expenseGroupRecyclerView = view.findViewById(R.id.expenseGroupRecyclerView);

        Log.d(TAG, "[onCreateView] Inflate the layout for this fragment is done");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeRecyclerView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.dataBetweenFragment = (SendDataBetweenFragment) context;
    }

    private void initializeRecyclerView() {
        ExpensesGroupSqliteDatabaseAdapter expensesGroupSqliteDatabaseAdapter = new ExpensesGroupSqliteDatabaseAdapter(getActivity());

        //Calling the custom recycler view adapter i.e. MyRecyclerAdapter
        ExpenseGroupsRecyclerAdapter recyclerAdapter = new ExpenseGroupsRecyclerAdapter(getActivity(), expensesGroupSqliteDatabaseAdapter.getExpensesGroups());
        expenseGroupRecyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setEditDeleteClickListener(this);

        //Creating the layout of the data to be displayed in the recycler view i.e. in linear with vertical arrangement
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        expenseGroupRecyclerView.setLayoutManager(linearLayoutManager);

        //Setting default animation of items in the recycler view. Note: it's default and it's not required to add.
        expenseGroupRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void OnEditClickListener(View view, int position) {
        if(dataBetweenFragment != null) {
            View view1 = expenseGroupRecyclerView.findContainingItemView(view);
            TextView textView = view1.findViewById(R.id.tvExpenseGroupIdName);
            this.dataBetweenFragment.sendData(textView.getText().toString());

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.idMainActivityLayout, new EditExpenseGroupFragment(), "EditExpenseGroupFragment");
            fragmentTransaction.commit();
            Log.d(TAG, "[OnEditClickListener] Clicked Item Data is: " + textView.getText().toString());
        }
        else {
            Log.d(TAG, "[OnEditClickListener] [dataBetweenFragment] is not implemented in the Activity: " + getActivity().getClass().getSimpleName());
        }
    }

    @Override
    public void OnDeleteListener(View view, int position) {
        View view1 = expenseGroupRecyclerView.findContainingItemView(view);
        TextView textView = view1.findViewById(R.id.tvExpenseGroupIdName);
        String idToDelete = getGroupExpenseId(textView.getText().toString());

        ExpensesGroupSqliteDatabaseAdapter expensesGroupSqliteDatabaseAdapter = new ExpensesGroupSqliteDatabaseAdapter(getContext());
        expensesGroupSqliteDatabaseAdapter.deleteExpensesGroupById(idToDelete);

        Snackbar.make(getView(), Html.fromHtml("<font color=\"#00ff99\">Expense Group: [" + textView.getText().toString() + "] deleted successfully</font>"), Snackbar.LENGTH_SHORT).show();
        Log.d(TAG,  "[OnEditClickListener] Clicked Item Data is: " + textView.getText().toString());
    }

    private String getGroupExpenseId(String item) {
        String[] splitIdAndGroupName = item.split(Pattern.quote("."));

        return splitIdAndGroupName[0];
    }

    private ExpensesGroupModel getGroupExpenseGroup(String item) {
        String[] splitIdAndGroupName = item.split(Pattern.quote(". "));
        ExpensesGroupModel expensesGroupModel = new ExpensesGroupModel();
        expensesGroupModel.ID = Integer.parseInt(splitIdAndGroupName[0]);
        expensesGroupModel.GroupName = splitIdAndGroupName[1];

        return expensesGroupModel;
    }

    public void setDataBetweenFragment(SendDataBetweenFragment sendDataBetweenFragment) {
        this.dataBetweenFragment = sendDataBetweenFragment;
    }

    public interface SendDataBetweenFragment {
        void sendData(String data);
    }
}
