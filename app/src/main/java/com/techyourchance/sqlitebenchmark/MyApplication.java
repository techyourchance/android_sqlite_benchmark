package com.techyourchance.sqlitebenchmark;

import android.app.Application;
import android.support.annotation.UiThread;

import com.techyourchance.sqlitebenchmark.dependencyinjection.application.ApplicationComponent;
import com.techyourchance.sqlitebenchmark.dependencyinjection.application.ApplicationModule;
import com.techyourchance.sqlitebenchmark.dependencyinjection.application.DaggerApplicationComponent;
import com.techyourchance.sqlitebenchmark.dependencyinjection.application.SettingsModule;

public class MyApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * @return application injector of type {@link ApplicationComponent}
     */
    @UiThread
    public ApplicationComponent getApplicationComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .settingsModule(new SettingsModule())
                    .build();
        }
        return mApplicationComponent;
    }
}
