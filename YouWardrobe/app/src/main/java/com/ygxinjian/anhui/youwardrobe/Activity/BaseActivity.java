package com.ygxinjian.anhui.youwardrobe.Activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.utils.StatusBarUtils;


/**
 * Created by handongqiang on 16/12/29.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        禁止横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//      沉浸式状态栏
        StatusBarUtils.setWindowStatusBarColor(this, R.color.main_Red);
//        隐藏actionbar
        getSupportActionBar().hide();
    }
}
