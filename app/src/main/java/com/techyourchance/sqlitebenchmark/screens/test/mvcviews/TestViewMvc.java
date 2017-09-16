package com.techyourchance.sqlitebenchmark.screens.test.mvcviews;

import com.techyourchance.sqlitebenchmark.screens.common.mvcviews.ObservableViewMvc;
import com.techyourchance.sqlitebenchmark.test.TestResults;

public interface TestViewMvc extends ObservableViewMvc<TestViewMvc.ExampleViewMvcListener> {


    interface ExampleViewMvcListener {

        void onStartTestClicked();

    }
    void testInProgress();

    void testCompleted();

    void bindTestResults(TestResults testResults);

}
