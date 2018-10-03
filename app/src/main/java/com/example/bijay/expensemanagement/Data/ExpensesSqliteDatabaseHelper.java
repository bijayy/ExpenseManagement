package com.example.bijay.expensemanagement.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ExpensesSqliteDatabaseHelper  extends SQLiteOpenHelper {

    private static final String TAG = PersonSqliteDatabaseHelper.class.getSimpleName();

    static final String TABLE_NAME = "Expenses";
    static final String DATE = "Date";
    static final String BY_WHOM = "ByWhom";
    static final String FOR_WHOM = "ForWhom";
    static final String PURPOSE = "Purpose";
    static final String PRODUCT_NAME = "ProductName";
    static final String PRICE = "Price";
    static final String ID = "_id";
    static final String PERSON_ID = "PersonId";

    private static final String DATABASE_NAME = "Expenses.db";
    private static final int DATABASE_VERSION = 1;

    private String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE + " VARCHAR(50), " + BY_WHOM + " VARCHAR(50), "
            + PRODUCT_NAME + " VARCHAR(50), " + PRICE + " VARCHAR(20), " +
            FOR_WHOM + " VARCHAR(50), " + PURPOSE + " VARCHAR(250), " + PERSON_ID + " INTEGER, FOREIGN KEY("+ PERSON_ID +") REFERENCES " +
            ExpensesSqliteDatabaseHelper.TABLE_NAME + "("+ ExpensesSqliteDatabaseHelper.ID+"));";
    private String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public ExpensesSqliteDatabaseHelper(Context context) {
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