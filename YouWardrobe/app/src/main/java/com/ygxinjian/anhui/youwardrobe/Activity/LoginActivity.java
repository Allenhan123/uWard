package com.ygxinjian.anhui.youwardrobe.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ygxinjian.anhui.youwardrobe.Constant;
import com.ygxinjian.anhui.youwardrobe.Controller.sharepreference.LocalData;
import com.ygxinjian.anhui.youwardrobe.MainActivity;
import com.ygxinjian.anhui.youwardrobe.Model.BaseModel;
import com.ygxinjian.anhui.youwardrobe.Model.LogInNetModel;
import com.ygxinjian.anhui.youwardrobe.Model.NetResultModel;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.YouWardrobeApplication;
import com.ygxinjian.anhui.youwardrobe.api.Api;
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
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    private ImageView mImg_Background;

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        ButterKnife.inject(this);
//        StatusBarUtils.setWindowStatusBarColor(this, R.color.Login_Red);
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.Login_Red);

        mImg_Background = (ImageView) findViewById(R.id.de_img_backgroud);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.translate_anim);
                mImg_Background.startAnimation(animation);
            }
        }, 200);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_login, R.id.tv_register, R.id.tv_forget})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
//                loginToService();
                loginTest();
                break;
            case R.id.tv_register:
//                Intent loginIntent = new Intent(this, RegisterActivity.class);
//                startActivity(loginIntent);
                startActivityForResult(new Intent(this, RegisterActivity.class), 1);

                break;
            case R.id.tv_forget:
//                Intent findIntent = new Intent(this, FindPswActivity.class);
//                startActivity(findIntent);
                startActivityForResult(new Intent(this, FindPswActivity.class), 2);

                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2 && data != null) {
            String phone = data.getStringExtra("phone");
            String password = data.getStringExtra("password");
            etUsername.setText(phone);
            etPassword.setText(password);
        } else if (data != null && requestCode == 1) {
            String phone = data.getStringExtra("phone");
            String password = data.getStringExtra("password");
            etUsername.setText(phone);
            etPassword.setText(password);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 登录到服务器
     */
    private void loginToService() {
        String userName = etUsername.getText().toString();
        String userPsw = etPassword.getText().toString();
        if (TextUtil.isNull(userName)) {
            DevUtil.showInfo(this, getString(R.string.tip_user_name_null));
            return;
        }
        if (TextUtil.isNull(userPsw)) {
            DevUtil.showInfo(this, getString(R.string.tip_psw_null));
            return;
        }

        final Dialog dialog = UiUtil.getLoadDialog(getContext(), true);
        dialog.show();
        OkHttpUtils.post()
                .url(Constant.login)
                .addParams("uid", userName)
                .addParams("pwd", userPsw)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        dialog.dismiss();
                        DevUtil.showInfo(getContext(), getString(R.string.tip_net_error));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        dialog.dismiss();
                        LogInNetModel netModel = BaseModel.getGson().fromJson(response.toString(), LogInNetModel.class);
                        if (netModel.getCode() == NetResultModel.RESULT_CODE_SUCCESS) {
                            YouWardrobeApplication.getLocalData().setString(LocalData.KEY_USE_INFO, netModel.getResult().toString());
                            YouWardrobeApplication.getLocalData().setString(LocalData.KEY_USE_ID, netModel.getResult().getName());
                            DevUtil.gotoActivity(getContext(), MainActivity.class);
                            finish();
                        } else {
                            DevUtil.showInfo(getContext(), netModel.getMessage());
                        }

                    }
                });
    }

    //登录  (使用RxJava +Retrofit)
    private void loginTest() {
        String userName = etUsername.getText().toString();
        String userPsw = etPassword.getText().toString();
        if (TextUtil.isNull(userName)) {
            DevUtil.showInfo(this, getString(R.string.tip_user_name_null));
            return;
        }
        if (TextUtil.isNull(userPsw)) {
            DevUtil.showInfo(this, getString(R.string.tip_psw_null));
            return;
        }

        final Dialog dialog = UiUtil.getLoadDialog(getContext(), true);
        dialog.show();
        Api.getYouWardrobeApi().login(userName, userPsw)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<LogInNetModel>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                        dialog.dismiss();
                    }

                    @Override
                    public void onNext(LogInNetModel logInNetModel) {
                        Log.d(TAG, "onNext: " + logInNetModel.toString());
                        LogInNetModel netModel = logInNetModel;
                        if (netModel.getCode() == NetResultModel.RESULT_CODE_SUCCESS) {
                            YouWardrobeApplication.getLocalData().setString(LocalData.KEY_USE_INFO, netModel.getResult().toString());
                            YouWardrobeApplication.getLocalData().setString(LocalData.KEY_USE_ID, netModel.getResult().getName());
                            DevUtil.gotoActivity(getContext(), MainActivity.class);
                            finish();
                        } else {
                            DevUtil.showInfo(getContext(), netModel.getMessage());
                        }
                    }
                });


    }
}
