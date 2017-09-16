package com.techyourchance.sqlitebenchmark.dependencyinjection.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.techyourchance.sqlitebenchmark.common.Constants;
import com.techyourchance.sqlitebenchmark.common.settings.SettingsManager;
import com.techyourchance.sqlitebenchmark.common.settings.sharedpreferences.SharedPrefsSettingsEntryFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class SettingsModule {

    @Provides
    @ApplicationScope
    SettingsManager settingsManager(Application application) {
        SharedPreferences sharedPreferences = application
                .getSharedPreferences(Constants.SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        return new SettingsManager(new SharedPrefsSettingsEntryFactory(sharedPreferences));
    }

}
