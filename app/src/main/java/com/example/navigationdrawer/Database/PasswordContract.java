package com.example.navigationdrawer.Database;

import android.provider.BaseColumns;

public class PasswordContract {

    private PasswordContract(){}

    public static class PasswordEntry implements BaseColumns{
        public static final String TABLE_NAME = "password";
        public static final String _ID = "_id";
        public static final String COLUMN_NAME_NOMBRE ="nombre";
        public static final String COLUMN_NAME_URL = "url";
        public static final String COLUMN_NAME_USUARIO = "usuario";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }
}
