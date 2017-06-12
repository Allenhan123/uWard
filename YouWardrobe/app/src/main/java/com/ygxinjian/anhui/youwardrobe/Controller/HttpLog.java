package com.ygxinjian.anhui.youwardrobe.Controller;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Olivine on 2017/6/3.
 */

public class HttpLog implements HttpLoggingInterceptor.Logger {
    private static final String TAG = HttpLog.class.getSimpleName();
    @Override
    public void log(String message) {
        Log.e(TAG, message);
    }
}
