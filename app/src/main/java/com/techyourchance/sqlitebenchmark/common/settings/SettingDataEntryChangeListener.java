package com.techyourchance.sqlitebenchmark.common.settings;


public interface SettingDataEntryChangeListener<T> {

    void onValueChanged(SettingDataEntry settingDataEntry, T value);

}
