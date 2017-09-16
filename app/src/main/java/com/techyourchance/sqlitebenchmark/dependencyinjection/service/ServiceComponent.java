package com.techyourchance.sqlitebenchmark.dependencyinjection.service;

import com.techyourchance.sqlitebenchmark.test.TestService;

import dagger.Subcomponent;

@Subcomponent(modules = {ServiceModule.class})
public interface ServiceComponent {

    public void inject(TestService testService);

}
