package com.techyourchance.sqlitebenchmark.test;


public class TestResultsEvent {
    private final TestResults mTestResults;

    public TestResultsEvent(TestResults testResults) {
        mTestResults = testResults;
    }

    public TestResults getTestResults() {
        return mTestResults;
    }
}
