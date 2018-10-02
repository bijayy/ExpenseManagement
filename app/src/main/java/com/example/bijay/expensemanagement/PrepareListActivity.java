package com.example.bijay.expensemanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class PrepareListActivity extends AppCompatActivity {

    private static final String TAG = PrepareListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_list);

        Log.d(TAG, "[onCreate] Everything is fine");
    }
}
