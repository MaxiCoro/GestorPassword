package com.example.navigationdrawer.Database;

import android.provider.BaseColumns;

public class CardContract {

    private CardContract(){};

    public static class CardEntry implements BaseColumns {
        public static final String TABLE_NAME = "card";
        public static final String _ID = "id";
        public static final String COLUMN_NAME_TITULAR = "titular";
        public static final String COLUMN_NAME_TIPO ="tipo";
        public static final String COLUMN_NAME_NROTARJETA = "nroTarjeta";
        public static final String COLUMN_NAME_FINICIO ="fInicio";
        public static final String COLUMN_NAME_FFIN = "fFin";
        public static final String COLUMN_NAME_EXTRA = "extra";
    }
}
