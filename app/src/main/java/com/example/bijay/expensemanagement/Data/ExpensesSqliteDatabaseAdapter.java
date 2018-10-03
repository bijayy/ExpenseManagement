package com.example.bijay.expensemanagement.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.bijay.expensemanagement.Models.ExpensesModel;

import java.util.ArrayList;
import java.util.List;

public class ExpensesSqliteDatabaseAdapter {
    private static final String TAG = PersonSqliteDatabaseHelper.class.getSimpleName();
    private ExpensesSqliteDatabaseHelper expensesSqliteDatabaseHelper;

    public ExpensesSqliteDatabaseAdapter(Context context) {
        expensesSqliteDatabaseHelper = new ExpensesSqliteDatabaseHelper(context);

        Log.d(TAG, "Constructor running in thread: " + Thread.currentThread().getName());
    }

    /**
     * Insert data of ExpensesModel in sqlite.
     * @param expense
     */
    public long addExpense(ExpensesModel expense) {
        if (expense != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(expensesSqliteDatabaseHelper.DATE, expense.Date);
            contentValues.put(expensesSqliteDatabaseHelper.BY_WHOM, expense.ByWhom);
            contentValues.put(expensesSqliteDatabaseHelper.FOR_WHOM, expense.ForWhom);
            contentValues.put(expensesSqliteDatabaseHelper.PURPOSE, expense.Purpose);
            contentValues.put(expensesSqliteDatabaseHelper.PRODUCT_NAME, expense.ProductName);
            contentValues.put(expensesSqliteDatabaseHelper.PRICE, expense.Amount);
            contentValues.put(expensesSqliteDatabaseHelper.PERSON_ID, expense.PersonId);

            Log.d(TAG, "addExpense() successfully added expense details running in thread: " + Thread.currentThread().getName());
            return expensesSqliteDatabaseHelper.getWritableDatabase().insert(expensesSqliteDatabaseHelper.TABLE_NAME, null, contentValues);
        }

        Log.d(TAG, "addExpense() blank expense details cannot be added running in thread: " + Thread.currentThread().getName());
        return 0;
    }

    /**
     * Update data of ExpensesModel in sqlite.
     * @param expense
     */
    public int updateExpenseById(ExpensesModel expense) {
        if (expense != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(expensesSqliteDatabaseHelper.DATE, expense.Date);
            contentValues.put(expensesSqliteDatabaseHelper.BY_WHOM, expense.ByWhom);
            contentValues.put(expensesSqliteDatabaseHelper.FOR_WHOM, expense.ForWhom);
            contentValues.put(expensesSqliteDatabaseHelper.PURPOSE, expense.Purpose);
            contentValues.put(expensesSqliteDatabaseHelper.PRODUCT_NAME, expense.ProductName);
            contentValues.put(expensesSqliteDatabaseHelper.PRICE, expense.Amount);
            contentValues.put(expensesSqliteDatabaseHelper.PERSON_ID, expense.PersonId);

            String[] whereArgs = {expense.ID +""};
            int totalRowUpdated = expensesSqliteDatabaseHelper.getWritableDatabase().update(expensesSqliteDatabaseHelper.TABLE_NAME, contentValues, expensesSqliteDatabaseHelper.ID + "=?", whereArgs);

            if (totalRowUpdated < 1)
                Log.d(TAG, "updateExpenseById() not found expense id: " + expense.ID + "  running in thread: " + Thread.currentThread().getName());

            Log.d(TAG, "updateExpenseById() updated expense id " + expense.ID + " running in thread: " + Thread.currentThread().getName());
            return totalRowUpdated;
        }

        return 0;
    }

    /**
     * Delete ExpensesModel by id.
     * @param id
     */
    public int deleteExpenseById(String id) {
        String[] whereArgs = {id};
        int totalRowDeleted = expensesSqliteDatabaseHelper.getWritableDatabase().delete(expensesSqliteDatabaseHelper.TABLE_NAME, expensesSqliteDatabaseHelper.ID +"=?", whereArgs);

        if(totalRowDeleted < 1)
            Log.d(TAG, "deleteExpenseById() not found expense id: "+ id +" running in thread: " + Thread.currentThread().getName());

        Log.d(TAG, "deleteExpenseById() deleted expense id: "+ id +" running in thread: " + Thread.currentThread().getName());
        return totalRowDeleted;
    }

    /**
     * Select ExpensesModel select all.
     */
    public List<ExpensesModel> getExpenses() {

        List<ExpensesModel> expensesModels = new ArrayList<>();
        Cursor cursor = null;
        String[] colums = { expensesSqliteDatabaseHelper.ID, expensesSqliteDatabaseHelper.DATE, expensesSqliteDatabaseHelper.BY_WHOM,
                expensesSqliteDatabaseHelper.FOR_WHOM, expensesSqliteDatabaseHelper.PURPOSE, expensesSqliteDatabaseHelper.PRODUCT_NAME,
                expensesSqliteDatabaseHelper.PRICE, expensesSqliteDatabaseHelper.PERSON_ID};

        cursor = expensesSqliteDatabaseHelper.getWritableDatabase().query(expensesSqliteDatabaseHelper.TABLE_NAME, colums, null, null, null, null, null);

        while(cursor.moveToNext()) {
            ExpensesModel expensesModel = new ExpensesModel();
            int idIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.ID);
            int dateIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.DATE);
            int byWhomIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.BY_WHOM);
            int forWhomIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.FOR_WHOM);
            int purposeIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.PURPOSE);
            int productNameIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.PRODUCT_NAME);
            int priceIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.PRICE);
            int personIdIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.PERSON_ID);

            expensesModel.ID = cursor.getInt(idIndex);
            expensesModel.Date = cursor.getString(dateIndex);
            expensesModel.ByWhom = cursor.getString(byWhomIndex);
            expensesModel.ForWhom = cursor.getString(forWhomIndex);
            expensesModel.Purpose = cursor.getString(purposeIndex);
            expensesModel.PersonId = cursor.getInt(personIdIndex);
            expensesModel.ProductName = cursor.getString(productNameIndex);
            expensesModel.Amount = cursor.getString(priceIndex);

            expensesModels.add(expensesModel);
        }

        if(expensesModels.isEmpty())
            Log.d(TAG, "getExpenses() found no records running in thread: " + Thread.currentThread().getName());

        Log.d(TAG, "getExpenses() found records running in thread: " + Thread.currentThread().getName());
        return expensesModels;
    }

    /**
     * Select ExpensesModel by id.
     * @param id
     */
    public ExpensesModel getExpenseById(String id) {
        Cursor cursor = null;
        String[] colums = { expensesSqliteDatabaseHelper.ID, expensesSqliteDatabaseHelper.DATE, expensesSqliteDatabaseHelper.BY_WHOM,
                expensesSqliteDatabaseHelper.FOR_WHOM, expensesSqliteDatabaseHelper.PURPOSE, expensesSqliteDatabaseHelper.PRODUCT_NAME,
                expensesSqliteDatabaseHelper.PRICE, expensesSqliteDatabaseHelper.PERSON_ID};
        String[]  selectionArgs = {id};

        cursor = expensesSqliteDatabaseHelper.getWritableDatabase().query(expensesSqliteDatabaseHelper.TABLE_NAME, colums, expensesSqliteDatabaseHelper.ID +"=?", selectionArgs, null, null, null);

        if(cursor.moveToNext()) {
            ExpensesModel expensesModel = new ExpensesModel();
            int idIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.ID);
            int dateIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.DATE);
            int byWhomIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.BY_WHOM);
            int forWhomIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.FOR_WHOM);
            int purposeIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.PURPOSE);
            int productNameIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.PRODUCT_NAME);
            int priceIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.PRICE);
            int personIdIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.PERSON_ID);

            expensesModel.ID = cursor.getInt(idIndex);
            expensesModel.Date = cursor.getString(dateIndex);
            expensesModel.ByWhom = cursor.getString(byWhomIndex);
            expensesModel.ForWhom = cursor.getString(forWhomIndex);
            expensesModel.Purpose = cursor.getString(purposeIndex);
            expensesModel.PersonId = cursor.getInt(personIdIndex);
            expensesModel.ProductName = cursor.getString(productNameIndex);
            expensesModel.Amount = cursor.getString(priceIndex);

            Log.d(TAG, "getExpenseById() found record of expense id: "+ id +" running in thread: " + Thread.currentThread().getName());
            return expensesModel;
        }

        Log.d(TAG, "getExpenseById() no record running in thread: " + Thread.currentThread().getName());
        return null;
    }
}

