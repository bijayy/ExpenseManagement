package com.example.bijay.expensemanagement.Data.Sqlite.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.bijay.expensemanagement.Data.Sqlite.Helper.PersonSqliteDatabaseHelper;
import com.example.bijay.expensemanagement.Models.ExpensesGroupModel;
import com.example.bijay.expensemanagement.Models.PersonModel;

import java.util.ArrayList;
import java.util.List;

public class PersonSqliteDatabaseAdapter {

    private static final String TAG = PersonSqliteDatabaseHelper.class.getSimpleName();
    private PersonSqliteDatabaseHelper personSqliteDatabaseHelper;
    private Context context;

    public PersonSqliteDatabaseAdapter(Context context) {
        personSqliteDatabaseHelper = new PersonSqliteDatabaseHelper(context);
        this.context = context;

        Log.d(TAG, "Constructor running in thread: " + Thread.currentThread().getName());
    }

    /**
     * Insert data of PersonModel in sqlite.
     *
     * @param person
     */
    public long addPerson(PersonModel person) {
        if (person != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(personSqliteDatabaseHelper.NAME, person.Name);
            contentValues.put(personSqliteDatabaseHelper.PHONE_NUMBER, person.MobileNumber);
            contentValues.put(personSqliteDatabaseHelper.EMAIL, person.Email);
            contentValues.put(personSqliteDatabaseHelper.GROUP_ID, person.ExpenseGroupModel.ID);

            Log.d(TAG, "addPerson() successfully added person details running in thread: " + Thread.currentThread().getName());
            return personSqliteDatabaseHelper.getWritableDatabase().insert(personSqliteDatabaseHelper.TABLE_NAME, null, contentValues);
        }

        Log.d(TAG, "addPerson() blank person details cannot be added running in thread: " + Thread.currentThread().getName());
        return 0;
    }

    /**
     * Update data of PersonModel in sqlite.
     *
     * @param id
     * @param name
     * @param phoneNumber
     * @param emailId
     * @param groupName
     */
    public int updatePersonById(String id, String name, String phoneNumber, String emailId, String groupName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(personSqliteDatabaseHelper.NAME, name);
        contentValues.put(personSqliteDatabaseHelper.PHONE_NUMBER, phoneNumber);
        contentValues.put(personSqliteDatabaseHelper.EMAIL, emailId);
        contentValues.put(personSqliteDatabaseHelper.GROUP_ID, groupName);

        String[] whereArgs = {id};
        int totalRowUpdated = personSqliteDatabaseHelper.getWritableDatabase().update(personSqliteDatabaseHelper.TABLE_NAME, contentValues, personSqliteDatabaseHelper.ID + "=?", whereArgs);

        if (totalRowUpdated < 1)
            Log.d(TAG, "updatePersonById() unot found person id: " + id + "  running in thread: " + Thread.currentThread().getName());

        Log.d(TAG, "updatePersonById() updated person id " + id + " running in thread: " + Thread.currentThread().getName());
        return totalRowUpdated;
    }

    /**
     * Delete PersonModel by id.
     *
     * @param id
     */
    public int deletePersonById(String id) {
        String[] whereArgs = {id};
        int totalRowDeleted = personSqliteDatabaseHelper.getWritableDatabase().delete(personSqliteDatabaseHelper.TABLE_NAME, personSqliteDatabaseHelper.ID + "=?", whereArgs);

        if (totalRowDeleted < 1)
            Log.d(TAG, "deletePersonById() not found person id: " + id + " running in thread: " + Thread.currentThread().getName());

        Log.d(TAG, "deletePersonById() deleted person id: " + id + " running in thread: " + Thread.currentThread().getName());
        return totalRowDeleted;
    }

    /**
     * Select PersonModel select all.
     */
    public List<PersonModel> getPersons() {

        List<PersonModel> personModelList = new ArrayList<>();
        Cursor cursor = null;
        String[] colums = {personSqliteDatabaseHelper.ID, personSqliteDatabaseHelper.NAME, personSqliteDatabaseHelper.PHONE_NUMBER,
                personSqliteDatabaseHelper.EMAIL, personSqliteDatabaseHelper.GROUP_ID};

        cursor = personSqliteDatabaseHelper.getWritableDatabase().query(personSqliteDatabaseHelper.TABLE_NAME, colums, null, null, null, null, null);

        while (cursor.moveToNext()) {
            PersonModel personModel = new PersonModel();
            int idIndex = cursor.getColumnIndex(personSqliteDatabaseHelper.ID);
            int nameIndex = cursor.getColumnIndex(personSqliteDatabaseHelper.NAME);
            int phoneNumberIndex = cursor.getColumnIndex(personSqliteDatabaseHelper.PHONE_NUMBER);
            int emailIndex = cursor.getColumnIndex(personSqliteDatabaseHelper.EMAIL);
            int groupIDIndex = cursor.getColumnIndex(personSqliteDatabaseHelper.GROUP_ID);

            personModel.ID = cursor.getInt(idIndex);
            personModel.Name = cursor.getString(nameIndex);
            personModel.MobileNumber = cursor.getString(phoneNumberIndex);
            personModel.Email = cursor.getString(emailIndex);
            personModel.ExpenseGroupModel = getExpensesGroupById(cursor.getInt(groupIDIndex) + "");

            personModelList.add(personModel);
        }

        if (personModelList.isEmpty())
            Log.d(TAG, "getPersons() found no records running in thread: " + Thread.currentThread().getName());

        Log.d(TAG, "getPersons() found records running in thread: " + Thread.currentThread().getName());
        return personModelList;
    }

    /**
     * Select PersonModel by id.
     *
     * @param id
     */
    public PersonModel getPersonById(String id) {
        Cursor cursor = null;
        String[] colums = {personSqliteDatabaseHelper.ID, personSqliteDatabaseHelper.NAME, personSqliteDatabaseHelper.PHONE_NUMBER,
                personSqliteDatabaseHelper.EMAIL, personSqliteDatabaseHelper.GROUP_ID};
        String[] selectionArgs = {id};

        cursor = personSqliteDatabaseHelper.getWritableDatabase().query(personSqliteDatabaseHelper.TABLE_NAME, colums, personSqliteDatabaseHelper.ID + "=?", selectionArgs, null, null, null);

        if (cursor.moveToNext()) {
            PersonModel personModel = new PersonModel();
            int idIndex = cursor.getColumnIndex(personSqliteDatabaseHelper.ID);
            int nameIndex = cursor.getColumnIndex(personSqliteDatabaseHelper.NAME);
            int phoneNumberIndex = cursor.getColumnIndex(personSqliteDatabaseHelper.PHONE_NUMBER);
            int emailIndex = cursor.getColumnIndex(personSqliteDatabaseHelper.EMAIL);
            int groupIDIndex = cursor.getColumnIndex(personSqliteDatabaseHelper.GROUP_ID);

            personModel.ID = cursor.getInt(idIndex);
            personModel.Name = cursor.getString(nameIndex);
            personModel.MobileNumber = cursor.getString(phoneNumberIndex);
            personModel.Email = cursor.getString(emailIndex);
            personModel.ExpenseGroupModel = getExpensesGroupById(cursor.getInt(groupIDIndex) + "");

            Log.d(TAG, "getPersonById() found record of person id: " + id + " running in thread: " + Thread.currentThread().getName());
            return personModel;
        }

        Log.d(TAG, "getPersonById() no record running in thread: " + Thread.currentThread().getName());
        return null;
    }

    private ExpensesGroupModel getExpensesGroupById(String id) {
        ExpensesGroupSqliteDatabaseAdapter expensesGroupSqliteDatabaseAdapter = new ExpensesGroupSqliteDatabaseAdapter(context);
        ExpensesGroupModel expensesGroupModel = expensesGroupSqliteDatabaseAdapter.getExpensesGroupById(id);
        if(expensesGroupModel == null) {
            Log.d(TAG, "[getExpenseGroups] No expense group available for id: " + id);
            return null;
        }

        Log.d(TAG, "[getExpenseGroups] Expense group: "+ expensesGroupModel.GroupName +" fetched successfully");
        return expensesGroupModel;
    }
}
