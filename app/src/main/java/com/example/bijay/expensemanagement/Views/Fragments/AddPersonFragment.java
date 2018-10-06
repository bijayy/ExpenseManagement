package com.example.bijay.expensemanagement.Views.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.example.bijay.expensemanagement.Data.Sqlite.Adapter.PersonSqliteDatabaseAdapter;
import com.example.bijay.expensemanagement.Models.ExpensesGroupModel;
import com.example.bijay.expensemanagement.Models.PersonModel;
import com.example.bijay.expensemanagement.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPersonFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = AddPersonFragment.class.getSimpleName();

    private Button btnCancelPerson;
    private Button btnAddPerson;
    private Spinner spinnerExpenseGroup;
    private EditText etPersonName;
    private EditText etPersonMobileNumber;
    private EditText etPersonEmail;

    public AddPersonFragment() {
        // Required empty public constructor
        Log.d(TAG, "[AddPersonFragment] constructor initialization is done");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_person, container, false);
        btnCancelPerson = view.findViewById(R.id.btnCancelPerson);
        btnAddPerson = view.findViewById(R.id.btnAddPerson);
        spinnerExpenseGroup= view.findViewById(R.id.spinnerExpenseGroup);
        etPersonName= view.findViewById(R.id.etPersonName);
        etPersonMobileNumber = view.findViewById(R.id.etPersonMobile);
        etPersonEmail= view.findViewById(R.id.etPersonEmail);

        btnAddPerson.setOnClickListener(this);
        btnCancelPerson.setOnClickListener(this);

        Log.d(TAG, "[onCreateView] Inflate the layout for this fragment is done");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getExpenseGroup();
        Log.d(TAG, "[onActivityCreated] called, calling [getExpenseGroup] method");
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddPerson:
                if(spinnerExpenseGroup.getSelectedItemPosition() > 0) {
                    addPerson();
                    reset();

                    Toast.makeText(getContext(), "Person added successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), "Please select expense group", Toast.LENGTH_SHORT).show();
                }

                Log.d(TAG, "[onClick] [btnAddPerson] is called");
                break;

            case R.id.btnCancelPerson:
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.idMainActivityLayout, new MainFragment(), "MainFragment");
                transaction.disallowAddToBackStack();
                transaction.commit();

                Log.d(TAG, "[onClick] [btnCancelPerson] is called, redirected to [MainFragment]");
                break;

            default:
                Log.d(TAG, "[onClick][default] Not Implemented");
                break;
        }
    }

    private void addPerson() {
        PersonSqliteDatabaseAdapter personSqliteDatabaseAdapter = new PersonSqliteDatabaseAdapter(getContext());
        PersonModel personModel = new PersonModel();
        personModel.ExpenseGroupModel = getExpenseGroup(getGroupIdFromSpinner(spinnerExpenseGroup.getSelectedItem() + ""));
        personModel.Name = etPersonName.getText().toString();
        personModel.MobileNumber = etPersonMobileNumber.getText().toString();
        personModel.Email = etPersonEmail.getText().toString();
        personSqliteDatabaseAdapter.addPerson(personModel);

        Log.d(TAG, "[onClick][btnAddGroup] Person: " + personModel.Name + " added successfully");
    }

    private ExpensesGroupModel getExpenseGroup(String id) {
        ExpensesGroupSqliteDatabaseAdapter expensesGroupSqliteDatabaseAdapter = new ExpensesGroupSqliteDatabaseAdapter(getContext());
        ExpensesGroupModel expensesGroupModel = expensesGroupSqliteDatabaseAdapter.getExpensesGroupById(id);

        Log.d(TAG, "[getExpenseGroups] Expense group: "+ expensesGroupModel.GroupName +" fetched successfully");
        return expensesGroupModel;
    }

    private void getExpenseGroup() {
        ExpensesGroupSqliteDatabaseAdapter expensesGroupSqliteDatabaseAdapter = new ExpensesGroupSqliteDatabaseAdapter(getContext());
        List<ExpensesGroupModel> expensesGroupModels = expensesGroupSqliteDatabaseAdapter.getExpensesGroups();

        ArrayList<String> strings = new ArrayList<>();
        strings.add("Select Expense Group");

        for(ExpensesGroupModel expensesGroupModel : expensesGroupModels) {
            strings.add(expensesGroupModel.ID + ". " + expensesGroupModel.GroupName);
        }

        addPersonsToSpinner(strings);

        Log.d(TAG, "[getExpenseGroups] Expense groups fetched successfully");
    }

    private void addPersonsToSpinner(ArrayList<String> expenseGroups) {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, expenseGroups);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerExpenseGroup.setAdapter(spinnerAdapter);
        //spinnerAdapter.add("DELHI");
        //spinnerAdapter.notifyDataSetChanged();
    }

    private String getGroupIdFromSpinner(String spinnerSelectedItem) {
        String[] splitIdAndGroupName = spinnerSelectedItem.split(Pattern.quote("."));

        return splitIdAndGroupName[0];
    }

    private void reset() {
        etPersonName.setText("");
        etPersonMobileNumber.setText("");
        etPersonEmail.setText("");
    }
}
