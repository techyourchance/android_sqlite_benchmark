package com.techyourchance.sqlitebenchmark.dependencyinjection.service;

import android.app.Service;
import android.content.Context;

import com.techyourchance.sqlitebenchmark.common.logging.MyLogger;
import com.techyourchance.sqlitebenchmark.test.TestContentGenerator;
import com.techyourchance.sqlitebenchmark.test.TestDatabasePreloader;
import com.techyourchance.sqlitebenchmark.test.TestExecutor;
import com.techyourchance.sqlitebenchmark.test.TestSqliteOpenHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {

    private final Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }

    @Provides
    Context context() {
        return mService;
    }

    @Provides
    Service service() {
        return mService;
    }

    @Provides
    TestContentGenerator testContentGenerator() {
        return new TestContentGenerator();
    }

    @Provides
    TestExecutor testExecutor(TestSqliteOpenHelper sqliteOpenHelper, MyLogger logger) {
        return new TestExecutor(sqliteOpenHelper, logger);
    }

    @Provides
    TestDatabasePreloader testDatabasePreloader(TestSqliteOpenHelper sqliteOpenHelper,
                                                          TestContentGenerator testContentGenerator,
                                                          MyLogger logger) {
        return new TestDatabasePreloader(sqliteOpenHelper, testContentGenerator, logger);
    }


}
