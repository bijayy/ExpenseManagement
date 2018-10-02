package com.example.bijay.expensemanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ExpensesGroupSqliteDatabaseAdapter {
    private static final String TAG = PersonSqliteDatabaseHelper.class.getSimpleName();
    private ExpensesGroupSqliteDatabaseHelper expensesGroupSqliteDatabaseHelper;

    ExpensesGroupSqliteDatabaseAdapter(Context context) {
        expensesGroupSqliteDatabaseHelper = new ExpensesGroupSqliteDatabaseHelper(context);

        Log.d(TAG, "Constructor running in thread: " + Thread.currentThread().getName());
    }

    /**
     * Insert data of ExpensesGroupModel in sqlite.
     * @param expensesGroupModel
     */
    public long addExpensesGroup(ExpensesGroupModel expensesGroupModel) {
        if (expensesGroupModel != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(expensesGroupSqliteDatabaseHelper.GROUP_NAME, expensesGroupModel.GroupName);

            Log.d(TAG, "addExpensesGroup() successfully added expenses group details running in thread: " + Thread.currentThread().getName());
            return expensesGroupSqliteDatabaseHelper.getWritableDatabase().insert(expensesGroupSqliteDatabaseHelper.TABLE_NAME, null, contentValues);
        }

        Log.d(TAG, "addExpensesGroup() blank expenses group details cannot be added running in thread: " + Thread.currentThread().getName());
        return 0;
    }

    /**
     * Update data of ExpensesGroupModel in sqlite.
     * @param expensesGroupModel
     */
    public int updateExpensesGroupById(ExpensesGroupModel expensesGroupModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(expensesGroupSqliteDatabaseHelper.GROUP_NAME, expensesGroupModel.GroupName);

        String[] whereArgs = {expensesGroupModel.ID + ""};
        int totalRowUpdated = expensesGroupSqliteDatabaseHelper.getWritableDatabase().update(expensesGroupSqliteDatabaseHelper.TABLE_NAME,  contentValues, expensesGroupSqliteDatabaseHelper.ID +"=?", whereArgs);

        if(totalRowUpdated < 1)
            Log.d(TAG, "updateExpensesGroupById() not found expenses group id: "+ expensesGroupModel.ID +"  running in thread: " + Thread.currentThread().getName());

        Log.d(TAG, "updateExpensesGroupById() updated expenses group id "+ expensesGroupModel.ID +" running in thread: " + Thread.currentThread().getName());
        return totalRowUpdated;
    }

    /**
     * Delete ExpensesGroupModel by id.
     * @param id
     */
    public int deleteExpensesGroupById(String id) {
        String[] whereArgs = {id};
        int totalRowDeleted = expensesGroupSqliteDatabaseHelper.getWritableDatabase().delete(expensesGroupSqliteDatabaseHelper.TABLE_NAME, expensesGroupSqliteDatabaseHelper.ID +"=?", whereArgs);

        if(totalRowDeleted < 1)
            Log.d(TAG, "deleteExpensesGroupById() not found expense group id: "+ id +" running in thread: " + Thread.currentThread().getName());

        Log.d(TAG, "deleteExpensesGroupById() deleted expense group id: "+ id +" running in thread: " + Thread.currentThread().getName());
        return totalRowDeleted;
    }

    /**
     * Select ExpensesGroupModel select all.
     */
    public List<ExpensesGroupModel> getExpensesGroups() {

        List<ExpensesGroupModel> expensesGroupModels = new ArrayList<>();
        Cursor cursor = null;
        String[] colums = { expensesGroupSqliteDatabaseHelper.ID, expensesGroupSqliteDatabaseHelper.GROUP_NAME};

        cursor = expensesGroupSqliteDatabaseHelper.getWritableDatabase().query(expensesGroupSqliteDatabaseHelper.TABLE_NAME, colums, null, null, null, null, null);

        while(cursor.moveToNext()) {
            ExpensesGroupModel expensesGroupModel = new ExpensesGroupModel();
            int idIndex = cursor.getColumnIndex(expensesGroupSqliteDatabaseHelper.ID);
            int groupNameIndex = cursor.getColumnIndex(expensesGroupSqliteDatabaseHelper.GROUP_NAME);

            expensesGroupModel.ID = cursor.getInt(idIndex);
            expensesGroupModel.GroupName = cursor.getString(groupNameIndex);

            expensesGroupModels.add(expensesGroupModel);
        }

        if(expensesGroupModels.isEmpty())
            Log.d(TAG, "getExpensesGroups() found no records running in thread: " + Thread.currentThread().getName());

        Log.d(TAG, "getExpensesGroups() found records running in thread: " + Thread.currentThread().getName());
        return expensesGroupModels;
    }

    /**
     * Select ExpensesGroupModel by id.
     * @param id
     */
    public ExpensesGroupModel getExpensesGroupById(String id) {
        Cursor cursor = null;
        String[] colums = { expensesGroupSqliteDatabaseHelper.ID, expensesGroupSqliteDatabaseHelper.GROUP_NAME};
        String[]  selectionArgs = {id};

        cursor = expensesGroupSqliteDatabaseHelper.getWritableDatabase().query(expensesGroupSqliteDatabaseHelper.TABLE_NAME, colums, expensesGroupSqliteDatabaseHelper.ID +"=?", selectionArgs, null, null, null);

        if(cursor.moveToNext()) {
            ExpensesGroupModel expensesGroupModel = new ExpensesGroupModel();
            int idIndex = cursor.getColumnIndex(expensesGroupSqliteDatabaseHelper.ID);
            int groupNameIndex = cursor.getColumnIndex(expensesGroupSqliteDatabaseHelper.GROUP_NAME);

            expensesGroupModel.ID = cursor.getInt(idIndex);
            expensesGroupModel.GroupName = cursor.getString(groupNameIndex);

            Log.d(TAG, "getExpensesGroupById() found record of expense group id: "+ id +" running in thread: " + Thread.currentThread().getName());
            return expensesGroupModel;
        }

        Log.d(TAG, "getExpensesGroupById() no record running in thread: " + Thread.currentThread().getName());
        return null;
    }
}

