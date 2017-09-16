package com.techyourchance.sqlitebenchmark.screens.common.dialogs.info;

import com.techyourchance.sqlitebenchmark.screens.common.mvcviews.ObservableViewMvc;

public interface InfoViewMvc extends ObservableViewMvc<InfoViewMvc.InfoViewMvcListener> {

    public interface InfoViewMvcListener {
        void onDismissClicked();
    }

    void setTitle(String title);
    void setMessage(String message);
    void setDismissCaption(String dismissCaption);

}
