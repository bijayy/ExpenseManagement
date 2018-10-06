package com.example.bijay.expensemanagement.Views.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.bijay.expensemanagement.Adapter.CustomSpinner;
import com.example.bijay.expensemanagement.Models.ExpensesGroupModel;
import com.example.bijay.expensemanagement.R;
import com.example.bijay.expensemanagement.Views.Fragments.EditExpenseGroupFragment;
import com.example.bijay.expensemanagement.Views.Fragments.ExpensesFilterFragment;
import com.example.bijay.expensemanagement.Views.Fragments.MainFragment;
import com.example.bijay.expensemanagement.Views.Fragments.ViewExpensesGroupsFragment;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements ViewExpensesGroupsFragment.SendDataBetweenFragment{

    private static final String TAG = MainActivity.class.getSimpleName();

    private ExpensesGroupModel expensesGroupModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.idMainActivityLayout, new MainFragment(), "MainFragment");
        transaction.commit();

        Log.d(TAG, "[onCreate] is done");
    }


    @Override
    public void sendData(String data) {
        String[] splitIdAndGroupName = data.split(Pattern.quote(". "));
        ExpensesGroupModel expensesGroupModel = new ExpensesGroupModel();
        expensesGroupModel.ID = Integer.parseInt(splitIdAndGroupName[0]);
        expensesGroupModel.GroupName = splitIdAndGroupName[1];
        this.expensesGroupModel = expensesGroupModel;

        EditExpenseGroupFragment editExpenseGroupFragment = new EditExpenseGroupFragment();
        editExpenseGroupFragment.sendData(this.expensesGroupModel);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(editExpenseGroupFragment, "EditExpenseGroupFragment");
        fragmentTransaction.commit();
    }
}
