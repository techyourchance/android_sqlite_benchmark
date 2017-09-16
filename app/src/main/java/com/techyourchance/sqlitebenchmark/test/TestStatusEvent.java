package com.techyourchance.sqlitebenchmark.test;

public class TestStatusEvent {

    private final boolean mIsInProgress;

    public TestStatusEvent(boolean isInProgress) {
        mIsInProgress = isInProgress;
    }

    public boolean isInProgress() {
        return mIsInProgress;
    }
}
