package com.ygxinjian.anhui.youwardrobe.Controller.sharepreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by fanyang2 on 2016/4/27.
 */
public class LocalData {
    public static String KEY_USE_WEATHER = "weather";


    private SharedPreferences mPreference;

    public LocalData(Context context) {
        mPreference = context.getSharedPreferences("YouWardrobe", Context.MODE_PRIVATE);
    }

    public void setString(String key, String value) {
        Editor editor = mPreference.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key) {
        String value = mPreference.getString(key, "");
        return value;
    }

    public void setLong(String key, long value) {
        Editor editor = mPreference.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public long getLong(String key) {
        long value = mPreference.getLong(key, -1);
        return value;
    }

    public void setFloat(String key, float value) {
        Editor editor = mPreference.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    public float getFloat(String key) {
        float value = mPreference.getFloat(key, -1.0f);
        return value;
    }

    public void setInt(String key, int value) {
        Editor editor = mPreference.edit();
        editor.putInt(key, value);
        editor.commit();

    }

    public int getInt(String key) {
        int value = mPreference.getInt(key, -1);
        return value;
    }

    public void setBoolean(String key, boolean value) {
        Editor editor = mPreference.edit();
        editor.putBoolean(key, value);
        editor.commit();

    }

    public boolean getBoolean(String key) {
        boolean value = mPreference.getBoolean(key, false);
        return value;
    }

}
