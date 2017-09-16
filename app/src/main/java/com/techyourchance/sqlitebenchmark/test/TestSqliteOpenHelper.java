package com.techyourchance.sqlitebenchmark.test;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class TestSqliteOpenHelper extends SQLiteOpenHelper {
    
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "db";

    public static final String TEST_TABLE_NAME = "articles_tbl";
    public static final String COL_ID = BaseColumns._ID;
    public static final String COL_CONTENT = "content";

    private static final String CREATE_ARTICLES_TABLE =
            "CREATE TABLE " + TEST_TABLE_NAME + " ( "
                    + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_CONTENT + " TEXT );";

    public TestSqliteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_ARTICLES_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            clear(db);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clear(SQLiteDatabase db) {
        db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TEST_TABLE_NAME);
        onCreate(db);
    }


}

