package com.example.bijay.expensemanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PersonSqliteDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = PersonSqliteDatabaseHelper.class.getSimpleName();

    static final String TABLE_NAME = "Person";
    static final String NAME = "Name";
    static final String PHONE_NUMBER = "PhoneNumber";
    static final String EMAIL = "Email";
    static final String GROUP_ID = "GroupId";
    static final String ID = "_id";

    private static final String DATABASE_NAME = "Person.db";
    private static final int DATABASE_VERSION = 2;

    private String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(50), " + PHONE_NUMBER + " VARCHAR(15), " +
            EMAIL + " VARCHAR(50), " + GROUP_ID + " INTEGER, FOREIGN KEY("+ GROUP_ID +") REFERENCES " +
            ExpensesGroupSqliteDatabaseHelper.TABLE_NAME + "("+ ExpensesGroupSqliteDatabaseHelper.ID+"));";
    private String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    PersonSqliteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d(TAG, "Constructor running in thread: " + Thread.currentThread().getName());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

        Log.d(TAG, "onCreate running in thread: " + Thread.currentThread().getName());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);

        Log.d(TAG, "onUpgrade running in thread: " + Thread.currentThread().getName());
    }
}