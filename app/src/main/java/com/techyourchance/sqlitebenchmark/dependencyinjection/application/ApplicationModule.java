package com.techyourchance.sqlitebenchmark.dependencyinjection.application;

import android.app.Application;

import com.techyourchance.sqlitebenchmark.common.logging.MyLogger;
import com.techyourchance.sqlitebenchmark.common.multithreading.BackgroundThreadPoster;
import com.techyourchance.sqlitebenchmark.common.multithreading.MainThreadPoster;
import com.techyourchance.sqlitebenchmark.test.TestSqliteOpenHelper;

import org.greenrobot.eventbus.EventBus;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationScope
    Application application() {
        return mApplication;
    }

    @Provides
    @ApplicationScope
    MyLogger logger() {
        return new MyLogger();
    }

    @Provides
    @ApplicationScope
    EventBus eventBus() {
        return EventBus.getDefault();
    }

    @Provides
    @ApplicationScope
    MainThreadPoster mainThreadPoster() {
        return new MainThreadPoster();
    }

    @Provides
    @ApplicationScope
    BackgroundThreadPoster backgroundThreadPoster() {
        return new BackgroundThreadPoster();
    }

    @Provides
    @ApplicationScope
    TestSqliteOpenHelper mTestSqliteOpenHelper(Application application) {
        return new TestSqliteOpenHelper(application);
    }
}
