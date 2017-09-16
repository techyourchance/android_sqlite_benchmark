package com.techyourchance.sqlitebenchmark.test;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.WorkerThread;

import com.techyourchance.sqlitebenchmark.common.logging.MyLogger;

public class TestDatabasePreloader {

    private static final String TAG = "TestDatabasePreloader";

    private final TestSqliteOpenHelper mTestSqliteOpenHelper;
    private final TestContentGenerator mTestContentGenerator;
    private final MyLogger mLogger;

    public TestDatabasePreloader(TestSqliteOpenHelper testSqliteOpenHelper, TestContentGenerator testContentGenerator, MyLogger logger) {
        mTestSqliteOpenHelper = testSqliteOpenHelper;
        mTestContentGenerator = testContentGenerator;
        mLogger = logger;
    }

    @WorkerThread
    public void preloadDatabase(int totalEntities, int bytesInEntity) {
        mLogger.d(TAG, "Populating database with test data");

        SQLiteDatabase db = mTestSqliteOpenHelper.getWritableDatabase();

        long initNanoTime = System.nanoTime();

        db.beginTransaction();
        try {
            ContentValues cv;
            for (int i = 0; i < totalEntities; i++) {
                cv = new ContentValues(1);
                cv.put(TestSqliteOpenHelper.COL_CONTENT, mTestContentGenerator.generate(bytesInEntity));
                db.insert(TestSqliteOpenHelper.TEST_TABLE_NAME, null, cv);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        long totalMiliTime = (System.nanoTime() - initNanoTime) / 1000000;

        mLogger.d(TAG, "Population of database took " + totalMiliTime + "ms");
    }
}
