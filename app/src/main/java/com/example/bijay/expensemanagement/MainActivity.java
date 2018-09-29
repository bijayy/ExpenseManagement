package com.example.bijay.expensemanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "[MainActivity]";
    private Button btnPrepareExpensesList;
    private Button btnAddExpenses;
    private Button btnCalculateExpenses;
    private Button btnDeleteExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPrepareExpensesList = findViewById(R.id.btnPrepareExpensesList);
        btnAddExpenses = findViewById(R.id.btnAddExpenses);
        btnCalculateExpenses = findViewById(R.id.btnCalculateExpenses);
        btnDeleteExpenses = findViewById(R.id.btnDeleteExpenses);

        btnPrepareExpensesList.setOnClickListener(this);
        btnAddExpenses.setOnClickListener(this);
        btnCalculateExpenses.setOnClickListener(this);
        btnDeleteExpenses.setOnClickListener(this);

        Log.d(TAG, "[onCreate] Everything is fine");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnPrepareExpensesList:
                Toast.makeText(this, "Preparation of Expenses List Page", Toast.LENGTH_SHORT).show();

                Log.d(TAG, "[onClick][btnPrepareExpensesList] Everything is fine");
                break;
            case R.id.btnAddExpenses:
                Toast.makeText(this, "Add Expenses Page", Toast.LENGTH_LONG).show();

                Log.d(TAG, "[onClick][btnAddExpenses] Everything is fine");
                break;
            case R.id.btnCalculateExpenses:
                Toast.makeText(this, "Calculate Expenses Page", Toast.LENGTH_LONG).show();

                Log.d(TAG, "[onClick][btnCalculateExpenses] Everything is fine");
                break;
            case R.id.btnDeleteExpenses:
                Toast.makeText(this, "Delete Expenses Page", Toast.LENGTH_LONG).show();

                Log.d(TAG, "[onClick][btnDeleteExpenses] Everything is fine");
                break;
            default:
                Toast.makeText(this, "Not Implemented Exception Page", Toast.LENGTH_LONG).show();

                Log.d(TAG, "[onClick][default] Not Implemented Exception Page");
                break;
        }
    }
}
