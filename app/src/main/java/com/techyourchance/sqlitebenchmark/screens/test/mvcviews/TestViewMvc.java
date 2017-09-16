package com.techyourchance.sqlitebenchmark.screens.test.mvcviews;

import com.techyourchance.sqlitebenchmark.screens.common.mvcviews.ObservableViewMvc;

public interface TestViewMvc extends ObservableViewMvc<TestViewMvc.ExampleViewMvcListener> {


    interface ExampleViewMvcListener {

        void onStartTestClicked();
    }

    void testInProgress();

    void testCompleted();

}
