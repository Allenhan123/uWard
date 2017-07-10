package com.ygxinjian.anhui.youwardrobe.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ygxinjian.anhui.youwardrobe.R;

/**
 * Created by ${Ua} on 2017/4/13.
 */

public class FindPswActivity extends BaseActivity {
    private static final int CHANGE_PASSWORD_BACK = 1002;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_psw;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }


//    修改成功
//    Intent data = new Intent();
//    data.putExtra("phone", phone);
//    data.putExtra("password", mPassword1.getText().toString());
//    setResult(CHANGE_PASSWORD_BACK, data);
//    this.finish();
}
