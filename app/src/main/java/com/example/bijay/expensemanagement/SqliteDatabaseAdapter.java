package com.example.bijay.expensemanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SqliteDatabaseAdapter {

    private static final String TAG = SqliteDatabaseHelper.class.getSimpleName();
    private SqliteDatabaseHelper sqliteDatabaseHelper;

    SqliteDatabaseAdapter(Context context) {
        sqliteDatabaseHelper = new SqliteDatabaseHelper(context);

        Log.d(TAG, "Constructor running in thread: " + Thread.currentThread().getName());
    }

    /**
     * Insert data of PersonModel in sqlite.
     * @param person
     */
    public long addPerson(PersonModel person) {
        if (person != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(sqliteDatabaseHelper.NAME, person.Name);
            contentValues.put(sqliteDatabaseHelper.PHONE_NUMBER, person.MobileNumber);
            contentValues.put(sqliteDatabaseHelper.EMAIL, person.Email);
            contentValues.put(sqliteDatabaseHelper.GROUP_NAME, person.GroupName);

            Log.d(TAG, "addPerson() successfully added person details running in thread: " + Thread.currentThread().getName());
            return sqliteDatabaseHelper.getWritableDatabase().insert(sqliteDatabaseHelper.TABLE_NAME, null, contentValues);
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
        contentValues.put(sqliteDatabaseHelper.NAME, name);
        contentValues.put(sqliteDatabaseHelper.PHONE_NUMBER, phoneNumber);
        contentValues.put(sqliteDatabaseHelper.EMAIL, emailId);
        contentValues.put(sqliteDatabaseHelper.GROUP_NAME, groupName);

        String[] whereArgs = {id};
        int totalRowUpdated = sqliteDatabaseHelper.getWritableDatabase().update(sqliteDatabaseHelper.TABLE_NAME,  contentValues,sqliteDatabaseHelper.ID +"=?", whereArgs);

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
        int totalRowDeleted = sqliteDatabaseHelper.getWritableDatabase().delete(sqliteDatabaseHelper.TABLE_NAME, sqliteDatabaseHelper.ID +"=?", whereArgs);

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
        String[] colums = { sqliteDatabaseHelper.ID, sqliteDatabaseHelper.NAME, sqliteDatabaseHelper.PHONE_NUMBER,
                sqliteDatabaseHelper.EMAIL, sqliteDatabaseHelper.GROUP_NAME};

        cursor = sqliteDatabaseHelper.getWritableDatabase().query(sqliteDatabaseHelper.TABLE_NAME, colums, null, null, null, null, null);

        while(cursor.moveToNext()) {
            PersonModel personModel = new PersonModel();
            int idIndex = cursor.getColumnIndex(sqliteDatabaseHelper.ID);
            int nameIndex = cursor.getColumnIndex(sqliteDatabaseHelper.NAME);
            int phoneNumberIndex = cursor.getColumnIndex(sqliteDatabaseHelper.PHONE_NUMBER);
            int emailIndex = cursor.getColumnIndex(sqliteDatabaseHelper.EMAIL);
            int groupNameIndex = cursor.getColumnIndex(sqliteDatabaseHelper.GROUP_NAME);

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
        String[] colums = { sqliteDatabaseHelper.ID, sqliteDatabaseHelper.NAME, sqliteDatabaseHelper.PHONE_NUMBER,
                sqliteDatabaseHelper.EMAIL, sqliteDatabaseHelper.GROUP_NAME};
        String[]  selectionArgs = {id};

        cursor = sqliteDatabaseHelper.getWritableDatabase().query(sqliteDatabaseHelper.TABLE_NAME, colums, sqliteDatabaseHelper.ID +"=?", selectionArgs, null, null, null);

        if(cursor.moveToNext()) {
            PersonModel personModel = new PersonModel();
            int idIndex = cursor.getColumnIndex(sqliteDatabaseHelper.ID);
            int nameIndex = cursor.getColumnIndex(sqliteDatabaseHelper.NAME);
            int phoneNumberIndex = cursor.getColumnIndex(sqliteDatabaseHelper.PHONE_NUMBER);
            int emailIndex = cursor.getColumnIndex(sqliteDatabaseHelper.EMAIL);
            int groupNameIndex = cursor.getColumnIndex(sqliteDatabaseHelper.GROUP_NAME);

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
