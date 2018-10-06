package com.example.bijay.expensemanagement.Views.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bijay.expensemanagement.Data.Sqlite.Adapter.ExpensesGroupSqliteDatabaseAdapter;
import com.example.bijay.expensemanagement.Data.Sqlite.Adapter.ExpensesSqliteDatabaseAdapter;
import com.example.bijay.expensemanagement.Data.Sqlite.Adapter.PersonSqliteDatabaseAdapter;
import com.example.bijay.expensemanagement.Models.ExpensesGroupModel;
import com.example.bijay.expensemanagement.Models.ExpensesModel;
import com.example.bijay.expensemanagement.Models.PersonModel;
import com.example.bijay.expensemanagement.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddExpenseFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = AddExpenseFragment.class.getSimpleName();

    //private Spinner spinnerExpenseGroups;
    private Spinner spinnerByWhom;
    private Spinner spinnerForWhom;

    private EditText etDate;
    private EditText etPurpose;
    private EditText etProductName;
    private EditText etAmount;

    private Button btnSave;
    private Button btnCancel;
    //private CalenderView calendarView;

    public AddExpenseFragment() {
        // Required empty public constructor
        Log.d(TAG, "[AddExpenseFragment] constructor initialization is done");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_expense, container, false);

        //spinnerExpenseGroups = view.findViewById(R.id.spinnerExpenseGroups);
        spinnerByWhom = view.findViewById(R.id.spinnerByWhom);
        spinnerForWhom = view.findViewById(R.id.spinnerForWhom);
        etDate = view.findViewById(R.id.etDate);
        etPurpose = view.findViewById(R.id.etPurpose);
        etAmount = view.findViewById(R.id.etAmount);
        etProductName = view.findViewById(R.id.etProductName);
        btnSave = view.findViewById(R.id.btnSave);
        btnCancel = view.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        bindPersonToForAndWhomSpinner();

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
            case R.id.btnSave:
                if(isValid()) {
                    AddExpense();
                    reset();

                    Toast.makeText(getContext(), "Expense added successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), "Please select Persons", Toast.LENGTH_SHORT).show();
                }

                Log.d(TAG, "[onClick] [btnSave] is called");
                break;

            case R.id.btnCancel:
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.idMainActivityLayout, new MainFragment(), "MainFragment");
                transaction.disallowAddToBackStack();
                transaction.commit();

                Log.d(TAG, "[onClick] [btnCancel] is called, redirected to [MainFragment]");
                break;

            default:
                Log.d(TAG, "[onClick][default] Not Implemented");
                break;
        }
    }

    private void AddExpense() {
        ExpensesSqliteDatabaseAdapter expensesSqliteDatabaseAdapter = new ExpensesSqliteDatabaseAdapter(getContext());
        ExpensesModel expensesModel = new ExpensesModel();
        expensesModel.ByWhom = getPersondFromSpinner(spinnerByWhom.getSelectedItem() + "");
        expensesModel.ForWhom = getPersondFromSpinner(spinnerForWhom.getSelectedItem() + "");
        expensesModel.Date = etDate.getText().toString();
        expensesModel.ProductName = etProductName.getText().toString();
        expensesModel.Purpose = etPurpose.getText().toString();
        expensesModel.Amount = etAmount.getText().toString();
        expensesSqliteDatabaseAdapter.addExpense(expensesModel);

        Log.d(TAG, "[AddExpense] Expense: " + expensesModel.ID + " added successfully");
    }

    private void bindPersonToForAndWhomSpinner() {
        PersonSqliteDatabaseAdapter personSqliteDatabaseAdapter = new PersonSqliteDatabaseAdapter(getContext());
        List<PersonModel> personModelList = personSqliteDatabaseAdapter.getPersons();

        ArrayList<String> strings = new ArrayList<>();
        strings.add("-- Who Did Expense? --");

        for(PersonModel personModel : personModelList) {
            strings.add(personModel.ID + ". " + personModel.Name);
        }

        addPersonsToSpinner(spinnerByWhom, strings);

        strings = new ArrayList<>();
        strings.add("-- For Whom? --");

        for(PersonModel personModel : personModelList) {
            strings.add(personModel.ID + ". " + personModel.Name);
        }

        addPersonsToSpinner(spinnerForWhom, strings);

        Log.d(TAG, "[bindPersonToForAndWhomSpinner] People fetched successfully");
    }

    private void addPersonsToSpinner(Spinner spinner, ArrayList<String> people) {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, people);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        //spinnerAdapter.add("DELHI");
        //spinnerAdapter.notifyDataSetChanged();
    }

    private boolean isValid() {
        if(spinnerByWhom.getSelectedItemPosition() > 0 && spinnerForWhom.getSelectedItemPosition() > 0)
            return true;
        return  false;
    }

    private String getPersondFromSpinner(String spinnerSelectedItem) {
        String[] splitPersonIdAndName = spinnerSelectedItem.split(Pattern.quote("."));

        return splitPersonIdAndName[0];
    }

    private void reset() {
        etAmount.setText("");
        etPurpose.setText("");
        etProductName.setText("");
        etDate.setText("");
    }
}
