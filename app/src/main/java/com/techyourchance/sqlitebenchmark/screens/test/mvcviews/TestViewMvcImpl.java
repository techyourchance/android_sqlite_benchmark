package com.techyourchance.sqlitebenchmark.screens.test.mvcviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.techyourchance.sqlitebenchmark.R;
import com.techyourchance.sqlitebenchmark.screens.common.mvcviews.BaseViewMvc;

public class TestViewMvcImpl extends BaseViewMvc<TestViewMvc.ExampleViewMvcListener>
        implements TestViewMvc {

    private final Button mBtnShowDialog;
    private final ProgressBar mProgressTest;

    public TestViewMvcImpl(LayoutInflater inflater, ViewGroup container) {
        setRootView(inflater.inflate(R.layout.layout_test, container, false));

        mProgressTest = findViewById(R.id.progress_test);

        mBtnShowDialog = findViewById(R.id.btn_start_test);

        mBtnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (ExampleViewMvcListener listener : getListeners()) {
                    listener.onStartTestClicked();
                }
            }
        });
    }

    @Override
    public void testInProgress() {
        mBtnShowDialog.setEnabled(false);
        mBtnShowDialog.setVisibility(View.GONE);
        mProgressTest.setVisibility(View.VISIBLE);
    }


    @Override
    public void testCompleted() {
        mBtnShowDialog.setEnabled(true);
        mBtnShowDialog.setVisibility(View.VISIBLE);
        mProgressTest.setVisibility(View.GONE);
    }
}
