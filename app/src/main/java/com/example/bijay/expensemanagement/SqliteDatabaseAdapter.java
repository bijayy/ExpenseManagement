package com.example.bijay.expensemanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class SqliteDatabaseAdapter {

    private static final String TAG = SqliteDatabaseHelper.class.getSimpleName();
    private val sqliteDatabaseHelper: SqliteDatabaseHelper

    constructor(context: Context?) {
        sqliteDatabaseHelper = SqliteDatabaseHelper(context)

        Log.d(TAG, "Constructor running in thread: ${Thread.currentThread().name}")
    }

    /**
     * Insert name and age of employ in sqlite.
     * @param name
     * @param age
     */
    fun insert(name: String, age: String): Long {
        val contentValues = ContentValues()
        contentValues.put(sqliteDatabaseHelper.NAME, name)
        contentValues.put(sqliteDatabaseHelper.AGE, age)

        Log.d(TAG, "insert() running in thread: ${Thread.currentThread().name}")
        return sqliteDatabaseHelper.writableDatabase.insert(sqliteDatabaseHelper.TABLE_NAME, null, contentValues)
    }

    /**
     * Update name of employ.
     * @param oldName
     * @param newName
     */
    fun update(oldName: String, newName: String): Int {
        val contentValues = ContentValues()
        contentValues.put(sqliteDatabaseHelper.NAME, newName)

        val whereArgs: Array<String> = arrayOf(oldName)
        val totalRowUpdated = sqliteDatabaseHelper.writableDatabase.update(sqliteDatabaseHelper.TABLE_NAME,  contentValues,"${sqliteDatabaseHelper.NAME}=?", whereArgs)

        Log.d(TAG, "update() running in thread: ${Thread.currentThread().name}")
        return totalRowUpdated;
    }

    /**
     * Delete employ by name.
     * @param name
     */
    fun delete(name: String): Int {
        val whereArgs = arrayOf(name)
        val totalRowDeleted = sqliteDatabaseHelper.writableDatabase.delete(sqliteDatabaseHelper.TABLE_NAME, "${sqliteDatabaseHelper.NAME} = ?", whereArgs)

        Log.d(TAG, "delete() running in thread: ${Thread.currentThread().name}")
        return totalRowDeleted
    }

    /**
     * Select emplyee by name or select all. Pass nothing to select all records of employee.
     * @param name is optional.
     */
    fun select(name: String = ""): String {

        val buffer = StringBuffer()
        val cursor: Cursor
        val colums: Array<String> = arrayOf<String>(sqliteDatabaseHelper.ID, sqliteDatabaseHelper.NAME, sqliteDatabaseHelper.AGE)
        val selectionArgs = arrayOf(name)

        if(name == "")
            cursor = sqliteDatabaseHelper.writableDatabase.query(sqliteDatabaseHelper.TABLE_NAME, colums, null, null, null, null, null)
        else
            cursor = sqliteDatabaseHelper.writableDatabase.query(sqliteDatabaseHelper.TABLE_NAME, colums, "${sqliteDatabaseHelper.NAME} = ?", selectionArgs, null, null, null)

        while(cursor.moveToNext()) {
            val employ = Employ()
            val idIndex = cursor.getColumnIndex(sqliteDatabaseHelper.ID)
            val nameIndex = cursor.getColumnIndex(sqliteDatabaseHelper.NAME)
            val ageIndex = cursor.getColumnIndex(sqliteDatabaseHelper.AGE)

            employ.ID = cursor.getInt(idIndex)
            employ.Name = cursor.getString(nameIndex)
            employ.Age = cursor.getString(ageIndex)

            buffer.append("${employ.ID} ${employ.Name} ${employ.Age} \n")
        }

        Log.d(TAG, "select() running in thread: ${Thread.currentThread().name}")
        return buffer.toString()
    }
}
