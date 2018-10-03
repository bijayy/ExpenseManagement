package com.example.bijay.expensemanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class DeleteExpensesActivity extends AppCompatActivity {

    private static final String TAG = DeleteExpensesActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_expenses);

        Log.d(TAG, "[onCreate] Everything is fine");
    }
}
