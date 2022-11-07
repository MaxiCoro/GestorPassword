package com.example.navigationdrawer.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CardDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Card.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CardContract.CardEntry.TABLE_NAME + " (" +
                    CardContract.CardEntry._ID + " INTEGER PRIMARY KEY," +
                    CardContract.CardEntry.COLUMN_NAME_TITULAR + " TEXT," +
                    CardContract.CardEntry.COLUMN_NAME_TIPO + " TEXT," +
                    CardContract.CardEntry.COLUMN_NAME_NROTARJETA + " TEXT," +
                    CardContract.CardEntry.COLUMN_NAME_FINICIO + " TEXT," +
                    CardContract.CardEntry.COLUMN_NAME_FFIN + " TEXT," +
                    CardContract.CardEntry.COLUMN_NAME_EXTRA+ " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + NoteContract.NoteEntry.TABLE_NAME;

    public CardDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("CardDbHelper", "Actualizando desde la version "+oldVersion+
                " a la version "+newVersion+". Se eliminaran todos los datos");
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
