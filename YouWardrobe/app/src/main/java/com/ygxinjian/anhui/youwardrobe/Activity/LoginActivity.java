package com.ygxinjian.anhui.youwardrobe.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ygxinjian.anhui.youwardrobe.MainActivity;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.utils.DevUtil;
import com.ygxinjian.anhui.youwardrobe.utils.StatusBarUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by handongqiang on 17/3/10.
 */

public class LoginActivity extends BaseActivity {
    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.tv_register)
    TextView tvRegister;
    @InjectView(R.id.tv_forget)
    TextView tvForget;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.Login_Red);
    }

    @OnClick({R.id.btn_login, R.id.tv_register, R.id.tv_forget})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                DevUtil.gotoActivity(this, MainActivity.class);
                finish();
                break;
            case R.id.tv_register:
                break;
            case R.id.tv_forget:
                break;
        }
    }
}
