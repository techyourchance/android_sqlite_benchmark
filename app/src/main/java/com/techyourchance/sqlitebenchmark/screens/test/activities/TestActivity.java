package com.techyourchance.sqlitebenchmark.screens.test.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.techyourchance.sqlitebenchmark.screens.common.activities.BaseActivity;
import com.techyourchance.sqlitebenchmark.screens.common.dialogs.DialogsFactory;
import com.techyourchance.sqlitebenchmark.screens.common.dialogs.DialogsManager;
import com.techyourchance.sqlitebenchmark.screens.common.mvcviews.ViewMvcFactory;
import com.techyourchance.sqlitebenchmark.screens.test.mvcviews.TestViewMvc;
import com.techyourchance.sqlitebenchmark.test.TestResultsEvent;
import com.techyourchance.sqlitebenchmark.test.TestService;
import com.techyourchance.sqlitebenchmark.test.TestStatusEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public class TestActivity extends BaseActivity implements TestViewMvc.ExampleViewMvcListener {

    @Inject ViewMvcFactory mViewMvcFactory;
    @Inject EventBus mEventBus;

    private TestViewMvc mViewMvc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getControllerComponent().inject(this);

        super.onCreate(savedInstanceState);

        mViewMvc = mViewMvcFactory.newMvcView(TestViewMvc.class, null);
        mViewMvc.registerListener(this);

        setContentView(mViewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkIfTestInProgress();
        mEventBus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mEventBus.unregister(this);
    }

    private void checkIfTestInProgress() {
        TestStatusEvent testStatusEvent = mEventBus.getStickyEvent(TestStatusEvent.class);
        if (testStatusEvent != null && testStatusEvent.isInProgress()) {
            testInProgress();
        } else {
            testCompleted();
        }
    }

    private void testInProgress() {
        mViewMvc.testInProgress();
    }

    private void testCompleted() {
        mViewMvc.testCompleted();
        TestResultsEvent testResultsEvent = mEventBus.getStickyEvent(TestResultsEvent.class);
        // we might have test results from the previous execution
        if (testResultsEvent != null) {
            mViewMvc.bindTestResults(testResultsEvent.getTestResults());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTestStatusEvent(TestStatusEvent testStatusEvent) {
        if (testStatusEvent.isInProgress()) {
            testInProgress();
        } else {
            testCompleted();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTestResultsEvent(TestResultsEvent testResultsEvent) {
        mViewMvc.bindTestResults(testResultsEvent.getTestResults());
    }

    @Override
    public void onStartTestClicked() {
        Intent i = new Intent(this, TestService.class);
        startService(i);
    }
}
