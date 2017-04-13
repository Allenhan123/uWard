package com.ygxinjian.anhui.youwardrobe.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ygxinjian.anhui.youwardrobe.Constant;
import com.ygxinjian.anhui.youwardrobe.Model.NetResultModel;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.utils.DevUtil;
import com.ygxinjian.anhui.youwardrobe.utils.TextUtil;
import com.ygxinjian.anhui.youwardrobe.utils.UiUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by ${Ua} on 2017/4/13.
 */

public class RegisterActivity extends BaseActivity {
    @InjectView(R.id.nav_tv_title)
    TextView navTvTitle;
    @InjectView(R.id.nav_right)
    ImageView navRight;
    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.et_password)
    EditText etPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);
        navTvTitle.setText(R.string.register_title);
        navRight.setVisibility(View.GONE);
    }

    @OnClick(R.id.nav_go_back)
    public void goBack() {
        finish();
    }


    @OnClick(R.id.tv_register)
    public void registerToSerVice() {
        String userName = etUsername.getText().toString();
        String userPsw = etPassword.getText().toString();

        if (TextUtil.isNull(userName)) {
            DevUtil.showInfo(this, getString(R.string.tip_user_name_null));
            return;
        }
        if (!TextUtil.isPhone(userName)) {
            DevUtil.showInfo(this, getString(R.string.tip_phone_error));
            return;
        }
        if (TextUtil.isNull(userPsw)) {
            DevUtil.showInfo(this, getString(R.string.tip_psw_null));
            return;
        }

        final Dialog dialog = UiUtil.getLoadDialog(getContext(), true);
        dialog.show();
        OkHttpUtils.post()
                .url(Constant.register)
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
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("message");
                            if (code == NetResultModel.RESULT_CODE_SUCCESS) {
                                finish();
                            } else {
                                DevUtil.showInfo(getContext(), msg);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }
}
