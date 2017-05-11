package com.ygxinjian.anhui.youwardrobe.Activity;

import android.os.Bundle;
import android.os.Handler;

import com.ygxinjian.anhui.youwardrobe.Controller.sharepreference.LocalData;
import com.ygxinjian.anhui.youwardrobe.MainActivity;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.YouWardrobeApplication;
import com.ygxinjian.anhui.youwardrobe.utils.DevUtil;
import com.ygxinjian.anhui.youwardrobe.utils.StatusBarUtils;
import com.ygxinjian.anhui.youwardrobe.utils.TextUtil;


/**
 * Created by handongqiang on 17/1/5.
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        StatusBarUtils.setWindowStatusBarColor(this,R.color.splash_Red);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 判断之前有没有登陆过
//                boolean userLogin= PrefUtils.getBoolean(SplashActivity.this, "isFirstLogin",
//                        true);
//                if (!userLogin) {
//                    // 跳转到新手引导页
//                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                } else {
//                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//                }
                if (TextUtil.isNull(YouWardrobeApplication.getLocalData().getString(LocalData.KEY_USE_ID))){
                    DevUtil.gotoActivity(SplashActivity.this, LoginActivity.class);
                }else {
                    DevUtil.gotoActivity(SplashActivity.this, MainActivity.class);
                }
                finish();
            }
        }, 100);
    }
}
