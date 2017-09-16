package com.techyourchance.sqlitebenchmark.screens.common.activities;

import android.support.annotation.UiThread;
import android.support.v4.app.FragmentActivity;

import com.techyourchance.sqlitebenchmark.MyApplication;
import com.techyourchance.sqlitebenchmark.dependencyinjection.controller.ControllerComponent;
import com.techyourchance.sqlitebenchmark.dependencyinjection.controller.ControllerModule;
import com.techyourchance.sqlitebenchmark.dependencyinjection.controller.ViewMvcModule;

public abstract class BaseActivity extends FragmentActivity {

    private boolean mIsControllerComponentUsed = false;

    /**
     * @return controller injector of type {@link ControllerComponent}
     */
    @UiThread
    protected ControllerComponent getControllerComponent() {
        if (mIsControllerComponentUsed) {
            throw new IllegalStateException("must not use ControllerComponent more than once");
        }
        mIsControllerComponentUsed = true;
        return ((MyApplication)getApplication())
                .getApplicationComponent()
                .newControllerComponent(new ControllerModule(this), new ViewMvcModule());
    }
}
