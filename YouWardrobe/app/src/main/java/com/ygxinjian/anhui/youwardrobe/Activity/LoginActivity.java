package com.ygxinjian.anhui.youwardrobe.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ygxinjian.anhui.youwardrobe.Constant;
import com.ygxinjian.anhui.youwardrobe.Controller.sharepreference.LocalData;
import com.ygxinjian.anhui.youwardrobe.MainActivity;
import com.ygxinjian.anhui.youwardrobe.Model.BaseModel;
import com.ygxinjian.anhui.youwardrobe.Model.LogInNetModel;
import com.ygxinjian.anhui.youwardrobe.Model.NetResultModel;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.YouWardrobeApplication;
import com.ygxinjian.anhui.youwardrobe.utils.DevUtil;
import com.ygxinjian.anhui.youwardrobe.utils.StatusBarUtils;
import com.ygxinjian.anhui.youwardrobe.utils.TextUtil;
import com.ygxinjian.anhui.youwardrobe.utils.UiUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by handongqiang on 17/3/10.
 */

public class LoginActivity extends BaseActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
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
                loginToService();
                break;
            case R.id.tv_register:
                Intent loginIntent = new Intent(this, RegisterActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.tv_forget:
                Intent findIntent = new Intent(this, FindPswActivity.class);
                startActivity(findIntent);
                break;
        }
    }


    /**
     * 登录到服务器
     */
    private void loginToService() {
        String userName = etUsername.getText().toString();
        String userPsw = etPassword.getText().toString();
        DevUtil.gotoActivity(getContext(), MainActivity.class);

//        if (TextUtil.isNull(userName)) {
//            DevUtil.showInfo(this, getString(R.string.tip_user_name_null));
//            return;
//        }
//        if (TextUtil.isNull(userPsw)) {
//            DevUtil.showInfo(this, getString(R.string.tip_psw_null));
//            return;
//        }
//
//        final Dialog dialog = UiUtil.getLoadDialog(getContext(), true);
//        dialog.show();
//        OkHttpUtils.post()
//                .url(Constant.login)
//                .addParams("uid", userName)
//                .addParams("pwd", userPsw)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        dialog.dismiss();
//                        DevUtil.showInfo(getContext(), getString(R.string.tip_net_error));
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        dialog.dismiss();
////                        Log.d(TAG, "onResponse: " + response.toString());
//                        LogInNetModel netModel = BaseModel.getGson().fromJson(response.toString(), LogInNetModel.class);
//                        if (netModel.getCode() == NetResultModel.RESULT_CODE_SUCCESS) {
//                            YouWardrobeApplication.getmLocalData().setString(LocalData.KEY_USE_INFO, netModel.getResult().toString());
//                            YouWardrobeApplication.getmLocalData().setString(LocalData.KEY_USE_ID, netModel.getResult().getName());
//
//                            DevUtil.gotoActivity(getContext(), MainActivity.class);
//                            finish();
//                        } else {
//                            DevUtil.showInfo(getContext(), netModel.getMessage());
//                        }
//
//                    }
//                });
    }
}
