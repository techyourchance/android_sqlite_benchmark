package com.techyourchance.sqlitebenchmark.screens.example.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.techyourchance.sqlitebenchmark.R;
import com.techyourchance.sqlitebenchmark.screens.common.dialogs.DialogsFactory;
import com.techyourchance.sqlitebenchmark.screens.common.dialogs.DialogsManager;
import com.techyourchance.sqlitebenchmark.screens.common.activities.BaseActivity;
import com.techyourchance.sqlitebenchmark.screens.common.mvcviews.ViewMvcFactory;
import com.techyourchance.sqlitebenchmark.screens.example.mvcviews.ExampleViewMvc;

import javax.inject.Inject;

public class ExampleActivity extends BaseActivity implements ExampleViewMvc.ExampleViewMvcListener {

    @Inject DialogsManager mDialogsManager;
    @Inject DialogsFactory mDialogsFactory;
    @Inject ViewMvcFactory mViewMvcFactory;

    private ExampleViewMvc mViewMvc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getControllerComponent().inject(this);

        super.onCreate(savedInstanceState);

        mViewMvc = mViewMvcFactory.newMvcView(ExampleViewMvc.class, null);
        mViewMvc.registerListener(this);

        setContentView(mViewMvc.getRootView());
    }

    @Override
    public void onShowDialogClicked() {
        DialogFragment dialog = mDialogsFactory.newInfoDialog(
                getString(R.string.dialog_title),
                getString(R.string.dialog_message),
                getString(R.string.dialog_button_caption));
        mDialogsManager.showRetainedDialogWithId(dialog, null);
    }
}