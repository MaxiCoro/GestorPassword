package com.example.navigationdrawer.Database;

import android.provider.BaseColumns;

public class NoteContract {

    private NoteContract(){}

    public static class NoteEntry implements BaseColumns {
        public static final String TABLE_NAME = "note";
        public static final String _ID = "_id";
        public static final String COLUMN_NAME_NOMBRE ="nombre";
        public static final String COLUMN_NAME_DESCRIPCION = "descripcion";
    }
}
