package com.techyourchance.sqlitebenchmark.test;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.WorkerThread;

import com.techyourchance.sqlitebenchmark.common.logging.MyLogger;

import java.util.LinkedList;
import java.util.List;

public class TestExecutor {

    private static final String TAG = "TestExecutor";

    private final TestSqliteOpenHelper mTestSqliteOpenHelper;
    private final MyLogger mLogger;

    public TestExecutor(TestSqliteOpenHelper testSqliteOpenHelper, MyLogger logger) {
        mTestSqliteOpenHelper = testSqliteOpenHelper;
        mLogger = logger;
    }

    @WorkerThread
    public void executeTest(int totalEntities, List<Integer> testSteps) {

        mLogger.d(TAG, "Testing database read performance");

        long initNanoTime;
        long totalMiliTime;

        for (Integer numOfEntitiesInSingleQuery : testSteps) {

            initNanoTime = System.nanoTime();

            for (int offset = 1; offset < totalEntities; offset += numOfEntitiesInSingleQuery) {
                List<TestEntity> entities = queryEntities(offset, numOfEntitiesInSingleQuery);
                mLogger.v(TAG, entities.size() + " entities");
            }

            totalMiliTime = (System.nanoTime() - initNanoTime) / 1000000;

            mLogger.d(TAG, "Total read time while querying " + numOfEntitiesInSingleQuery + " each time: " + totalMiliTime + "ms");

        }
    }

    @WorkerThread
    private List<TestEntity> queryEntities(int offset, Integer count) {

        List<TestEntity> entities = new LinkedList<>();

        SQLiteDatabase db = mTestSqliteOpenHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(
                    TestSqliteOpenHelper.TEST_TABLE_NAME,
                    new String[]{TestSqliteOpenHelper.COL_ID, TestSqliteOpenHelper.COL_CONTENT},
                    TestSqliteOpenHelper.COL_ID + " BETWEEN ? AND ?",
                    new String[]{String.valueOf(offset), String.valueOf(offset + count - 1)},
                    null,
                    null,
                    null
            );

            if (cursor.moveToFirst()) {
                do {
                    entities.add(new TestEntity(cursor.getInt(0), cursor.getString(1)));
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) cursor.close();
        }

        return entities;
    }
}
