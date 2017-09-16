package com.techyourchance.sqlitebenchmark.dependencyinjection.controller;

import com.techyourchance.sqlitebenchmark.screens.common.dialogs.info.InfoDialog;
import com.techyourchance.sqlitebenchmark.screens.common.dialogs.prompt.PromptDialog;
import com.techyourchance.sqlitebenchmark.screens.test.activities.TestActivity;

import dagger.Subcomponent;

@Subcomponent(modules = {ControllerModule.class, ViewMvcModule.class})
public interface ControllerComponent {

    void inject(TestActivity testActivity);

    void inject(InfoDialog infoDialog);
    void inject(PromptDialog promptDialog);

}
