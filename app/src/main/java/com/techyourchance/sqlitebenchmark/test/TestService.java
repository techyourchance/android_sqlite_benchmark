package com.techyourchance.sqlitebenchmark.test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.techyourchance.sqlitebenchmark.common.logging.MyLogger;
import com.techyourchance.sqlitebenchmark.common.multithreading.BackgroundThreadPoster;
import com.techyourchance.sqlitebenchmark.common.services.BaseService;
import com.techyourchance.sqlitebenchmark.common.settings.SettingDataEntry;
import com.techyourchance.sqlitebenchmark.common.settings.SettingsManager;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class TestService extends BaseService {

    private static final String TAG = "TestService";

    private static final int TOTAL_ENTITIES = 200;
    private static final int BYTES_IN_ENTITY = 100*1024;

    private static final List<Integer> TEST_STEPS = Arrays.asList(1, 5, 10, 20, 50, 100, 200);

    private final Object MONITOR = new Object();

    @Inject BackgroundThreadPoster mBackgroundThreadPoster;
    @Inject EventBus mEventBus;
    @Inject SettingsManager mSettingsManager;
    @Inject TestDatabasePreloader mTestDatabasePreloader;
    @Inject TestExecutor mTestExecutor;
    @Inject MyLogger mLogger;

    private boolean mTestInProgress = false;

    @Override
    public void onCreate() {
        getServiceComponent().inject(this);
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        synchronized (MONITOR) {
            if (mTestInProgress) {
                mLogger.d(TAG, "test is already in progress");
            } else {
                mLogger.d(TAG, "initiating new test");
                mTestInProgress = true;
                initTest();
            }
            publishEventTestInProgress();
        }
        return Service.START_NOT_STICKY;
    }

    private void initTest() {
        mBackgroundThreadPoster.post(new Runnable() {
            @Override
            public void run() {
                initTestSync();
            }
        });
    }

    @WorkerThread
    private void initTestSync() {

        SettingDataEntry<Boolean> isAfterDatabasePreload = mSettingsManager.isAfterDatabasePreload();

        if (!isAfterDatabasePreload.getValue()) {
            mTestDatabasePreloader.preloadDatabase(TOTAL_ENTITIES, BYTES_IN_ENTITY);
            isAfterDatabasePreload.setValue(true);
        }

        TestResults testResults = mTestExecutor.executeTest(TOTAL_ENTITIES, TEST_STEPS);

        testCompleted(testResults);
    }

    private void testCompleted(TestResults testResults) {
        synchronized (MONITOR) {
            mTestInProgress = false;
            publishEventTestCompleted();
            publishEventTestResults(testResults);
        }
    }

    private void publishEventTestInProgress() {
        mEventBus.postSticky(new TestStatusEvent(true));
    }


    private void publishEventTestCompleted() {
        mEventBus.postSticky(new TestStatusEvent(false));
    }

    private void publishEventTestResults(TestResults testResults) {
        mEventBus.postSticky(new TestResultsEvent(testResults));
    }

}
