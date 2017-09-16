package com.techyourchance.sqlitebenchmark.common.settings;

/**
 * This class encapsulates various settings/configurations/data which affect functionality of
 * the app.
 */
public class SettingsManager {

    private static final String KEY_IS_AFTER_DATABASE_PRELOAD = "KEY_IS_AFTER_DATABASE_PRELOAD";

    private final SettingsEntryFactory mSettingsEntryFactory;

    public SettingsManager(SettingsEntryFactory settingsEntryFactory) {
        mSettingsEntryFactory = settingsEntryFactory;
    }

    public SettingDataEntry<Boolean> isAfterDatabasePreload() {
        return mSettingsEntryFactory.getDataEntry(Boolean.class, KEY_IS_AFTER_DATABASE_PRELOAD, false);
    }

}
