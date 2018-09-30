package com.example.bijay.expensemanagement;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

public class AddExpensesActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private static final String TAG = "[AddExpensesActivity]";

    private Button btnCreateNewGroup;
    private Button btnUpdateExistingGroup;
    private Button btnSave;
    private Button btnCancel;

    private EditText etDate;
    private EditText etByWhom;
    private EditText etForWhom;
    private EditText etPurpose;
    private EditText etProductName;
    private EditText etAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);

        btnCreateNewGroup = findViewById(R.id.btnCreateNewGroup);
        btnUpdateExistingGroup = findViewById(R.id.btnUpdateExistingGroup);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        Log.d(TAG, "[onCreate] Button initialization is done");

        etDate = findViewById(R.id.etDate);
        etByWhom = findViewById(R.id.etByWhom);
        etForWhom = findViewById(R.id.etForWhom);
        etPurpose = findViewById(R.id.etPurpose);
        etProductName = findViewById(R.id.etProductName);
        etAmount = findViewById(R.id.etAmount);
        Log.d(TAG, "[onCreate] EditText initialization is done");

        etDate.setOnFocusChangeListener(this);
        etByWhom.setOnFocusChangeListener(this);

        btnCreateNewGroup.setOnClickListener(this);
        btnUpdateExistingGroup.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
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
                showCreateExpensesViews();

                Toast.makeText(this, "Creating New Group", Toast.LENGTH_LONG).show();
                Log.d(TAG, "[onClick(] btnCreateNewGroup is fine");
                break;
            case R.id.btnUpdateExistingGroup:

                Toast.makeText(this, "Updating Existing Group", Toast.LENGTH_LONG).show();
                Log.d(TAG, "[onClick(] btnUpdateExistingGroup is fine");
                break;
            case R.id.btnSave:

                Toast.makeText(this, "Expense Saved Successfully", Toast.LENGTH_LONG).show();
                Log.d(TAG, "[onClick(] btnSave is fine");
                break;
            case R.id.btnCancel:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(this, "Cancelled, moving back to previous page", Toast.LENGTH_LONG).show();
                Log.d(TAG, "[onClick(] btnCancel is fine");
                break;
            default:

                Toast.makeText(this, "Not Implemented", Toast.LENGTH_LONG).show();
                Log.d(TAG, "[onClick(] Button i.e. " + ((Button)view).getText().toString() + " is not implemented ");
                break;
        }
    }


    private void hideCreateExpensesViews() {
        etDate.setVisibility(View.GONE);
        etByWhom.setVisibility(View.GONE);
        etForWhom.setVisibility(View.GONE);
        etPurpose.setVisibility(View.GONE);
        etProductName.setVisibility(View.GONE);
        etAmount.setVisibility(View.GONE);

        btnSave.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);
    }

    private void showCreateExpensesViews() {
        etDate.setVisibility(View.VISIBLE);
        etByWhom.setVisibility(View.VISIBLE);
        etForWhom.setVisibility(View.VISIBLE);
        etPurpose.setVisibility(View.VISIBLE);
        etProductName.setVisibility(View.VISIBLE);
        etAmount.setVisibility(View.VISIBLE);

        btnSave.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.VISIBLE);
    }

    private void validateExpensesViews(EditText editText) {
        if(editText.isDirty() && editText.getText().toString().isEmpty()) {
            editText.setTextColor(Color.RED);
            editText.setHint(editText.getText().toString() + " is required");
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        validateExpensesViews((EditText) v);
    }
}
