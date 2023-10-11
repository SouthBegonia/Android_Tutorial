package com.example.androidtutorial.Utils;

import android.util.Log;

public class LogHelper {
    private static final String LOG_TAG = "AndroidTutorial";

    /**
     * 日志打印 - Info
     *
     * @param content content
     */
    public static void LogInfo(String content) {
        Log.i(LOG_TAG, content);
    }

    /**
     * 日志打印 - Warning
     *
     * @param content content
     */
    public static void LogWarning(String content) {
        Log.w(LOG_TAG, content);
    }

    /**
     * 日志打印 - Error
     *
     * @param content content
     */
    public static void LogError(String content) {
        Log.e(LOG_TAG, content);
    }
}