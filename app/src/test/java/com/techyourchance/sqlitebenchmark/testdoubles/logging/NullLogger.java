package com.techyourchance.sqlitebenchmark.testdoubles.logging;

import com.techyourchance.sqlitebenchmark.common.logging.MyLogger;

/**
 * No op logger for testing
 */
public class NullLogger extends MyLogger {

    @Override
    public void e(String tag, String message) {

    }

    @Override
    public void w(String tag, String message) {

    }

    @Override
    public void v(String tag, String message) {

    }

    @Override
    public void d(String tag, String message) {

    }
}
