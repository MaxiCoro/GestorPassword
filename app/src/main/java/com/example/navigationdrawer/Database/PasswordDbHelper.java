package com.example.navigationdrawer.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PasswordDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Password.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PasswordContract.PasswordEntry.TABLE_NAME + " (" +
                    PasswordContract.PasswordEntry._ID + " INTEGER PRIMARY KEY," +
                    PasswordContract.PasswordEntry.COLUMN_NAME_NOMBRE + " TEXT," +
                    PasswordContract.PasswordEntry.COLUMN_NAME_URL + " TEXT," +
                    PasswordContract.PasswordEntry.COLUMN_NAME_USUARIO + " TEXT," +
                    PasswordContract.PasswordEntry.COLUMN_NAME_PASSWORD + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PasswordContract.PasswordEntry.TABLE_NAME;

    public PasswordDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("PaisDbHelper", "Actualizando desde la version "+oldVersion+
                " a la version "+newVersion+". Se eliminaran todos los datos");
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
