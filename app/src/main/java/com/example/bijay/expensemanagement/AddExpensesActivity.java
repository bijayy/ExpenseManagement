package com.example.bijay.expensemanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class AddExpensesActivity extends AppCompatActivity {

    private static final String TAG = "[AddExpensesActivity]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);

        Log.d(TAG, "[onCreate] Everything is fine");
    }
}
