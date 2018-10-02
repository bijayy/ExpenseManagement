package com.example.bijay.expensemanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ExpensesSqliteDatabaseAdapter {
    private static final String TAG = PersonSqliteDatabaseHelper.class.getSimpleName();
    private ExpensesSqliteDatabaseHelper expensesSqliteDatabaseHelper;

    ExpensesSqliteDatabaseAdapter(Context context) {
        expensesSqliteDatabaseHelper = new ExpensesSqliteDatabaseHelper(context);

        Log.d(TAG, "Constructor running in thread: " + Thread.currentThread().getName());
    }

    /**
     * Insert data of PersonModel in sqlite.
     * @param person
     */
    public long addPerson(PersonModel person) {
        if (person != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(expensesSqliteDatabaseHelper.NAME, person.Name);
            contentValues.put(expensesSqliteDatabaseHelper.PHONE_NUMBER, person.MobileNumber);
            contentValues.put(expensesSqliteDatabaseHelper.EMAIL, person.Email);
            contentValues.put(expensesSqliteDatabaseHelper.GROUP_NAME, person.GroupName);

            Log.d(TAG, "addPerson() successfully added person details running in thread: " + Thread.currentThread().getName());
            return expensesSqliteDatabaseHelper.getWritableDatabase().insert(expensesSqliteDatabaseHelper.TABLE_NAME, null, contentValues);
        }

        Log.d(TAG, "addPerson() blank person details cannot be added running in thread: " + Thread.currentThread().getName());
        return 0;
    }

    /**
     * Update data of PersonModel in sqlite.
     * @param id
     * @param name
     * @param phoneNumber
     * @param emailId
     * @param groupName
     */
    public int updatePersonById(String id, String name, String phoneNumber, String emailId, String groupName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(expensesSqliteDatabaseHelper.NAME, name);
        contentValues.put(expensesSqliteDatabaseHelper.PHONE_NUMBER, phoneNumber);
        contentValues.put(expensesSqliteDatabaseHelper.EMAIL, emailId);
        contentValues.put(expensesSqliteDatabaseHelper.GROUP_NAME, groupName);

        String[] whereArgs = {id};
        int totalRowUpdated = expensesSqliteDatabaseHelper.getWritableDatabase().update(expensesSqliteDatabaseHelper.TABLE_NAME,  contentValues, expensesSqliteDatabaseHelper.ID +"=?", whereArgs);

        if(totalRowUpdated < 1)
            Log.d(TAG, "updatePersonById() unot found person id: "+ id +"  running in thread: " + Thread.currentThread().getName());

        Log.d(TAG, "updatePersonById() updated person id "+ id +" running in thread: " + Thread.currentThread().getName());
        return totalRowUpdated;
    }

    /**
     * Delete PersonModel by id.
     * @param id
     */
    public int deletePersonById(String id) {
        String[] whereArgs = {id};
        int totalRowDeleted = expensesSqliteDatabaseHelper.getWritableDatabase().delete(expensesSqliteDatabaseHelper.TABLE_NAME, expensesSqliteDatabaseHelper.ID +"=?", whereArgs);

        if(totalRowDeleted < 1)
            Log.d(TAG, "deletePersonById() not found person id: "+ id +" running in thread: " + Thread.currentThread().getName());

        Log.d(TAG, "deletePersonById() deleted person id: "+ id +" running in thread: " + Thread.currentThread().getName());
        return totalRowDeleted;
    }

    /**
     * Select PersonModel select all.
     */
    public List<PersonModel> getPersons() {

        List<PersonModel> personModelList = new ArrayList<>();
        Cursor cursor = null;
        String[] colums = { expensesSqliteDatabaseHelper.ID, expensesSqliteDatabaseHelper.NAME, expensesSqliteDatabaseHelper.PHONE_NUMBER,
                expensesSqliteDatabaseHelper.EMAIL, expensesSqliteDatabaseHelper.GROUP_NAME};

        cursor = expensesSqliteDatabaseHelper.getWritableDatabase().query(expensesSqliteDatabaseHelper.TABLE_NAME, colums, null, null, null, null, null);

        while(cursor.moveToNext()) {
            PersonModel personModel = new PersonModel();
            int idIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.ID);
            int nameIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.NAME);
            int phoneNumberIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.PHONE_NUMBER);
            int emailIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.EMAIL);
            int groupNameIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.GROUP_NAME);

            personModel.ID = cursor.getString(idIndex);
            personModel.Name = cursor.getString(nameIndex);
            personModel.MobileNumber = cursor.getString(phoneNumberIndex);
            personModel.Email = cursor.getString(emailIndex);
            personModel.GroupName = cursor.getString(groupNameIndex);

            personModelList.add(personModel);
        }

        if(personModelList.isEmpty())
            Log.d(TAG, "getPersons() found no records running in thread: " + Thread.currentThread().getName());

        Log.d(TAG, "getPersons() found records running in thread: " + Thread.currentThread().getName());
        return personModelList;
    }

    /**
     * Select PersonModel by id.
     * @param id
     */
    public PersonModel getPersonById(String id) {
        Cursor cursor = null;
        String[] colums = { expensesSqliteDatabaseHelper.ID, expensesSqliteDatabaseHelper.NAME, expensesSqliteDatabaseHelper.PHONE_NUMBER,
                expensesSqliteDatabaseHelper.EMAIL, expensesSqliteDatabaseHelper.GROUP_NAME};
        String[]  selectionArgs = {id};

        cursor = expensesSqliteDatabaseHelper.getWritableDatabase().query(expensesSqliteDatabaseHelper.TABLE_NAME, colums, expensesSqliteDatabaseHelper.ID +"=?", selectionArgs, null, null, null);

        if(cursor.moveToNext()) {
            PersonModel personModel = new PersonModel();
            int idIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.ID);
            int nameIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.NAME);
            int phoneNumberIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.PHONE_NUMBER);
            int emailIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.EMAIL);
            int groupNameIndex = cursor.getColumnIndex(expensesSqliteDatabaseHelper.GROUP_NAME);

            personModel.ID = cursor.getString(idIndex);
            personModel.Name = cursor.getString(nameIndex);
            personModel.MobileNumber = cursor.getString(phoneNumberIndex);
            personModel.Email = cursor.getString(emailIndex);
            personModel.GroupName = cursor.getString(groupNameIndex);

            Log.d(TAG, "getPersonById() found record of person id: "+ id +" running in thread: " + Thread.currentThread().getName());
            return personModel;
        }

        Log.d(TAG, "getPersonById() no record running in thread: " + Thread.currentThread().getName());
        return null;
    }
}

