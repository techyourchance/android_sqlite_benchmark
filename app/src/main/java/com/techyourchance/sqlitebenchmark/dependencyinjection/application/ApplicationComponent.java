package com.techyourchance.sqlitebenchmark.dependencyinjection.application;

import com.techyourchance.sqlitebenchmark.dependencyinjection.controller.ControllerComponent;
import com.techyourchance.sqlitebenchmark.dependencyinjection.controller.ControllerModule;
import com.techyourchance.sqlitebenchmark.dependencyinjection.controller.ViewMvcModule;
import com.techyourchance.sqlitebenchmark.dependencyinjection.service.ServiceComponent;
import com.techyourchance.sqlitebenchmark.dependencyinjection.service.ServiceModule;

import dagger.Component;

@ApplicationScope
@Component(
        modules = {
                ApplicationModule.class,
                SettingsModule.class
        }
)
public interface ApplicationComponent {

    ControllerComponent newControllerComponent(
            ControllerModule controllerModule,
            ViewMvcModule viewMvcModule);

    ServiceComponent newServiceComponent(ServiceModule serviceModule);

}