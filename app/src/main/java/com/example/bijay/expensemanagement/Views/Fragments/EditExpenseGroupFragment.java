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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bijay.expensemanagement.Data.Sqlite.Adapter.ExpensesGroupSqliteDatabaseAdapter;
import com.example.bijay.expensemanagement.Models.ExpensesGroupModel;
import com.example.bijay.expensemanagement.R;

import java.util.List;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditExpenseGroupFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = EditExpenseGroupFragment.class.getSimpleName();

    private EditText etEditGroup;
    private Button btnEditExpensesGroup;
    private static ExpensesGroupModel expensesGroupModel;

    public EditExpenseGroupFragment() {
        // Required empty public constructor
        Log.d(TAG, "[AddExpenseFragment] constructor initialization is done");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_expense_group, container, false);
        etEditGroup = view.findViewById(R.id.etEditGroup);
        btnEditExpensesGroup = view.findViewById(R.id.btnEditExpensesGroup);
        btnEditExpensesGroup.setOnClickListener(this);

        Log.d(TAG, "[onCreateView] Inflate the layout for this fragment is done");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        etEditGroup.setText(this.expensesGroupModel.GroupName);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnEditExpensesGroup) {
            ExpensesGroupSqliteDatabaseAdapter expensesGroupSqliteDatabaseAdapter = new ExpensesGroupSqliteDatabaseAdapter(getContext());
            ExpensesGroupModel expensesGroupModel = new ExpensesGroupModel();
            expensesGroupModel.GroupName = etEditGroup.getText().toString();
            expensesGroupModel.ID = this.expensesGroupModel.ID;
            int numberOfRowUdpated = expensesGroupSqliteDatabaseAdapter.updateExpensesGroup(expensesGroupModel);

            if(numberOfRowUdpated > 0) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.idMainActivityLayout, new ViewExpensesGroupsFragment(), "ViewExpensesGroupsFragment");
                fragmentTransaction.disallowAddToBackStack();
                fragmentTransaction.commit();

                Log.d(TAG, "[onClick][btnEditExpensesGroup] Expense group: " + expensesGroupModel.GroupName + " updated successfully");
                Toast.makeText(getContext(), "Expense group: " + expensesGroupModel.GroupName + " updated successfully", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getContext(), "Expense group: " + expensesGroupModel.GroupName + " update failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void sendData(ExpensesGroupModel expensesGroupModel) {
        this.expensesGroupModel = expensesGroupModel;
    }
}
