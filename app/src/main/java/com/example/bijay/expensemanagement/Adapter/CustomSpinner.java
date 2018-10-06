package com.example.bijay.expensemanagement.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ArrayAdapter;

public class CustomSpinner {
    private static final String TAG = CustomSpinner.class.getSimpleName();
    private int selectedItemIndex = -1;

    public int showCustomSpinner(ArrayAdapter adapter, Context context, final String title) {
        new AlertDialog.Builder(context)
            .setTitle(title)
            .setAdapter(adapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    selectedItemIndex = which;

                    Log.d(TAG, "Spinner Title: " + title + " Selected item index: " + which);
                    dialog.dismiss();
                }
            }).create().show();

        Log.d(TAG, "Spinner Title: " + title + " Selected item index: " + selectedItemIndex);
        return selectedItemIndex;
    }
}
