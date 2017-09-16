package com.techyourchance.sqlitebenchmark.test;

import java.util.HashMap;
import java.util.Map;

public class TestResults {

    private final int mTotalEntities;
    private final Map<Integer, Long> mStepsResults;

    public TestResults(int totalEntities, Map<Integer, Long> stepsResults) {
        mTotalEntities = totalEntities;
        mStepsResults = stepsResults;
    }

    public int getTotalEntities() {
        return mTotalEntities;
    }

    public Map<Integer, Long> getStepsResults() {
        return new HashMap<>(mStepsResults);
    }
}
