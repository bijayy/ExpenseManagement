package com.example.bijay.expensemanagement.Views.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.bijay.expensemanagement.R;

public class DeleteExpensesActivity extends AppCompatActivity {

    private static final String TAG = DeleteExpensesActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_expenses);

        Log.d(TAG, "[onCreate] Everything is fine");
    }
}
