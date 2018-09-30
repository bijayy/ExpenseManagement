package com.example.bijay.expensemanagement;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddExpensesActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener, View.OnKeyListener {

    private static final String TAG = "[AddExpensesActivity]";
    private boolean isValid = false;

    private Button btnCreateNewGroup;
    private Button btnUpdateExistingGroup;
    private Button btnAddExpenses;

    private Button btnSave;
    private Button btnCancel;

    private Button btnAddGroup;
    private Button btnAddPerson;
    private Button btnPersonGroupCancel;

    private EditText etDate;
    private EditText etByWhom;
    private EditText etForWhom;
    private EditText etPurpose;
    private EditText etProductName;
    private EditText etAmount;

    private EditText etExpensesGroupName;
    private EditText etPersonName;
    private EditText etMobileNumber;
    private EditText etEmailID;

    private Spinner spinnerExpensesGroup;
    private Spinner spinnerPersons;

    private ConstraintLayout addExpensesLayout;
    private LinearLayout createNewExpenseGroupLayout;
    private ConstraintLayout addExpensesFirstViewLayout;

    private List<EditText> addExpensesEditTexts = new ArrayList<EditText>();
    private List<EditText> addGroupEditTexts = new ArrayList<EditText>();
    private List<EditText> addPersonEditTexts = new ArrayList<EditText>();

    private List<String> expenseGroups = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);

        addExpensesFirstViewLayout = findViewById(R.id.addExpensesFirstView);
        addExpensesLayout = findViewById(R.id.addExpenses);
        createNewExpenseGroupLayout = findViewById(R.id.createExpensesGroup);

        btnCreateNewGroup = findViewById(R.id.btnCreateNewGroup);
        btnUpdateExistingGroup = findViewById(R.id.btnUpdateExistingGroup);
        btnAddExpenses = findViewById(R.id.btnAddExpenses);

        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        btnAddGroup = findViewById(R.id.btnAddExpensesGroup);
        btnAddPerson = findViewById(R.id.btnAddPerson);
        btnPersonGroupCancel = findViewById(R.id.btnCancelPerson);
        Log.d(TAG, "[onCreate] Button initialization is done");

        etDate = findViewById(R.id.etDate);
        etByWhom = findViewById(R.id.etByWhom);
        etForWhom = findViewById(R.id.etForWhom);
        etPurpose = findViewById(R.id.etPurpose);
        etProductName = findViewById(R.id.etProductName);
        etAmount = findViewById(R.id.etAmount);

        addExpensesEditTexts.add(etDate);
        addExpensesEditTexts.add(etByWhom);
        addExpensesEditTexts.add(etForWhom);
        addExpensesEditTexts.add(etPurpose);
        addExpensesEditTexts.add(etProductName);
        addExpensesEditTexts.add(etAmount);

        etExpensesGroupName = findViewById(R.id.etAddGroup);
        addGroupEditTexts.add(etExpensesGroupName);

        etPersonName = findViewById(R.id.etPersonName);
        etMobileNumber = findViewById(R.id.etPersonMobile);
        etEmailID = findViewById(R.id.etPersonEmail);

        spinnerExpensesGroup = findViewById(R.id.spinnerExpenseGroup);
        spinnerPersons = findViewById(R.id.spinnerPersons);

        addPersonEditTexts.add(etPersonName);
        addPersonEditTexts.add(etMobileNumber);
        addPersonEditTexts.add(etEmailID);

        Log.d(TAG, "[onCreate] EditText initialization is done");

        etDate.setOnFocusChangeListener(this);
        etByWhom.setOnFocusChangeListener(this);
        etForWhom.setOnFocusChangeListener(this);
        etPurpose.setOnFocusChangeListener(this);
        etProductName.setOnFocusChangeListener(this);
        etAmount.setOnFocusChangeListener(this);

        etExpensesGroupName.setOnFocusChangeListener(this);
        etPersonName.setOnFocusChangeListener(this);
        etMobileNumber.setOnFocusChangeListener(this);
        etEmailID.setOnFocusChangeListener(this);

        etDate.setOnKeyListener(this);
        etByWhom.setOnKeyListener(this);
        etForWhom.setOnKeyListener(this);
        etPurpose.setOnKeyListener(this);
        etProductName.setOnKeyListener(this);
        etAmount.setOnKeyListener(this);

        etExpensesGroupName.setOnKeyListener(this);
        etPersonName.setOnKeyListener(this);
        etMobileNumber.setOnKeyListener(this);
        etEmailID.setOnKeyListener(this);
        Log.d(TAG, "[onCreate] OnFocusChangeListener on EditText is done");

        btnCreateNewGroup.setOnClickListener(this);
        btnUpdateExistingGroup.setOnClickListener(this);
        btnAddExpenses.setOnClickListener(this);

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        btnAddGroup.setOnClickListener(this);
        btnAddPerson.setOnClickListener(this);
        btnPersonGroupCancel.setOnClickListener(this);
        Log.d(TAG, "[onCreate] setOnclickListener on buttons is done");

        //default settings
        hideCreateExpensesViews();

        Log.d(TAG, "[onCreate] Everything is fine");
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;

        switch (view.getId()) {
            case R.id.btnCreateNewGroup:
                createNewExpenseGroupLayout.setVisibility(View.VISIBLE);
                addExpensesLayout.setVisibility(View.GONE);
                addExpensesFirstViewLayout.setVisibility(View.GONE);

                Toast.makeText(this, "Creating New Group", Toast.LENGTH_LONG).show();
                Log.d(TAG, "[onClick(] btnCreateNewGroup is fine");
                break;
            case R.id.btnUpdateExistingGroup:

                Toast.makeText(this, "Updating Existing Group", Toast.LENGTH_LONG).show();
                Log.d(TAG, "[onClick(] btnUpdateExistingGroup is fine");
                break;

            case R.id.btnAddExpenses:
                createNewExpenseGroupLayout.setVisibility(View.GONE);
                addExpensesLayout.setVisibility(View.VISIBLE);
                addExpensesFirstViewLayout.setVisibility(View.GONE);

                Toast.makeText(this, "Add Expenses Page", Toast.LENGTH_LONG).show();
                Log.d(TAG, "[onClick(] btnAddExpenses is fine");
                break;

            case R.id.btnSave:
                if(validateRequiredFields(addExpensesEditTexts)) {
                    Toast.makeText(this, "Expense Saved Successfully", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "[onClick(] btnSave is fine");
                }
                else {
                    Log.d(TAG, "[onClick(] btnSave will not work as required field not provided");
                }
                break;
            case R.id.btnCancel:
                createNewExpenseGroupLayout.setVisibility(View.GONE);
                addExpensesLayout.setVisibility(View.GONE);
                addExpensesFirstViewLayout.setVisibility(View.VISIBLE);

                Toast.makeText(this, "Cancelled, moving back to previous page", Toast.LENGTH_LONG).show();
                Log.d(TAG, "[onClick(] btnCancel is fine");
                break;
            case R.id.btnAddExpensesGroup:
                if(validateRequiredFields(addGroupEditTexts)) {
                    expenseGroups.add( etExpensesGroupName.getText().toString());

                    Toast.makeText(this, "Expense Group ["+ etExpensesGroupName.getText().toString() +"] Saved Successfully", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "[onClick(] btnAddExpensesGroup is fine");
                }
                else {
                    Log.d(TAG, "[onClick(] btnAddExpensesGroup will not work as required field not provided");
                }
                break;
            case R.id.btnAddPerson:
                if(spinnerExpensesGroup.getSelectedItemPosition() > 0 && validateRequiredFields(addPersonEditTexts)) {
                    Toast.makeText(this, "Details Of ["+ etPersonName.getText().toString() +"] Saved Successfully", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "[onClick(] btnAddPerson is fine");
                }
                else {
                    Log.d(TAG, "[onClick(] btnAddPerson will not work as required field not provided");
                }
                break;
            case R.id.btnCancelPerson:
                createNewExpenseGroupLayout.setVisibility(View.GONE);
                addExpensesLayout.setVisibility(View.GONE);
                addExpensesFirstViewLayout.setVisibility(View.VISIBLE);

                Toast.makeText(this, "Cancelled, moving back to previous page", Toast.LENGTH_LONG).show();
                Log.d(TAG, "[onClick(] btnCancelPerson is fine");
                break;
            default:

                Toast.makeText(this, "Not Implemented", Toast.LENGTH_LONG).show();
                Log.d(TAG, "[onClick(] Button i.e. " + ((Button)view).getText().toString() + " is not implemented ");
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        validateExpensesViews((EditText) v);
        Log.d(TAG, "[onFocusChange] is fine");
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        Log.d(TAG, "[onKey] is fired. Events: " + event);

        if(event.getAction() == KeyEvent.ACTION_UP) {
            validateExpensesViews((EditText) v);
            Log.d(TAG, "[onKey] is fired. [KeyEvent.ACTION_UP]");
            return true;
        }

        return false;
    }

    private void hideCreateExpensesViews() {
        addExpensesLayout.setVisibility(View.GONE);
        createNewExpenseGroupLayout.setVisibility(View.GONE);

        /*etDate.setVisibility(View.GONE);
        etByWhom.setVisibility(View.GONE);
        etForWhom.setVisibility(View.GONE);
        etPurpose.setVisibility(View.GONE);
        etProductName.setVisibility(View.GONE);
        etAmount.setVisibility(View.GONE);

        btnSave.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);
        Log.d(TAG, "[hideCreateExpensesViews] is fine");*/
    }

    private void showCreateExpensesViews() {
        /*etDate.setVisibility(View.VISIBLE);
        etByWhom.setVisibility(View.VISIBLE);
        etForWhom.setVisibility(View.VISIBLE);
        etPurpose.setVisibility(View.VISIBLE);
        etProductName.setVisibility(View.VISIBLE);
        etAmount.setVisibility(View.VISIBLE);

        btnSave.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.VISIBLE);*/

        Log.d(TAG, "[showCreateExpensesViews] is fine");
    }

    private boolean validateRequiredFields(List<EditText> editTexts) {
        Log.d(TAG, "[validateRequiredField] before validation");

        for(EditText editText : editTexts) {
            if(!validateExpensesViews(editText)) {
                Log.d(TAG, "[validateRequiredField] after validation, isValid: false");
                return false;
            }
        }

        Log.d(TAG, "[validateRequiredField] after validation, isValid: true");
        return true;
    }

    private boolean validateExpensesViews(EditText editText) {
        Log.d(TAG, "[validateExpensesViews] isEmpty: " + editText.getText().toString().isEmpty());

        if(editText.getText().toString().isEmpty()) {
            editText.setError("Required");
            Log.d(TAG, "[validateExpensesViews] not valid");
            return false;
        }
        else {
            Log.d(TAG, "[validateExpensesViews] valid");
            return  true;
        }
    }
}
