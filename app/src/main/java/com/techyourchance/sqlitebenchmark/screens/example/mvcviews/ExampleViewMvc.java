package com.techyourchance.sqlitebenchmark.screens.example.mvcviews;

import com.techyourchance.sqlitebenchmark.screens.common.mvcviews.ObservableViewMvc;

public interface ExampleViewMvc extends ObservableViewMvc<ExampleViewMvc.ExampleViewMvcListener> {

    interface ExampleViewMvcListener {
        void onShowDialogClicked();
    }

}
