package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ToDoDatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "todolist";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_ITEM = "item";
    public static final String COLUMN_NAME_STATUS = "status";
    public static final String COLUMN_NAME_DATE = "date";
    public static final String COLUMN_NAME_TIME = "time";
    final static String[] columns = { COLUMN_NAME_ID, COLUMN_NAME_ITEM, COLUMN_NAME_STATUS, COLUMN_NAME_DATE, COLUMN_NAME_TIME };

    final private static String CREATE_CMD =
            "CREATE TABLE "+TABLE_NAME+" ("
                    + COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_NAME_ITEM + " TEXT NOT NULL, "
                    + COLUMN_NAME_STATUS + " TEXT, "
                    + COLUMN_NAME_DATE + " TEXT, "
                    + COLUMN_NAME_TIME + " TEXT)";

    private static final String SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS " + TABLE_NAME;

    final private static String NAME = "todolist_db";
    final private static Integer VERSION = 1;
    final private Context mContext;


    public ToDoDatabaseHelper(Context context) {
        super(context, NAME, null, VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_CMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
