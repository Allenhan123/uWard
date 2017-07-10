package com.ygxinjian.anhui.youwardrobe.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ygxinjian.anhui.youwardrobe.Constant;
import com.ygxinjian.anhui.youwardrobe.Model.NetResultModel;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.utils.DevUtil;
import com.ygxinjian.anhui.youwardrobe.utils.StatusBarUtils;
import com.ygxinjian.anhui.youwardrobe.utils.TextUtil;
import com.ygxinjian.anhui.youwardrobe.utils.UiUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import okhttp3.Call;

/**
 * Created by ${Ua} on 2017/4/13.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener{
    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.et_password)
    EditText etPassword;
    private  EditText etVerify;
    private TextView tv_sendSms;
    private Button btn_register;
    private static final int REGISTER_BACK = 1001;

    private static String appKey = "1cf9ebeba5b32";
    // 填写从短信SDK应用后台注册得到的APPSECRET
    private static String appSecret = "50e162b039f0e82c4046d91582283802";
    private String number,userPsd;
    private ProgressDialog dialog;

    private ImageView mImg_Background;

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//        ButterKnife.inject(this);
//        StatusBarUtils.setWindowStatusBarColor(this, R.color.Login_Red);
//        tv_sendSms = (TextView) findViewById(R.id.tv_sendSms);
//        btn_register = (Button) findViewById(R.id.btn_register);
//        etVerify = (EditText) findViewById(R.id.et_verify);
//        tv_sendSms.setOnClickListener(this);
//        btn_register.setOnClickListener(this);
//        initSDK();
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.Login_Red);
        tv_sendSms = (TextView) findViewById(R.id.tv_sendSms);
        btn_register = (Button) findViewById(R.id.btn_register);
        etVerify = (EditText) findViewById(R.id.et_verify);
        tv_sendSms.setOnClickListener(this);
        btn_register.setOnClickListener(this);

        mImg_Background = (ImageView) findViewById(R.id.de_img_backgroud);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(RegisterActivity.this, R.anim.translate_anim);
                mImg_Background.startAnimation(animation);
            }
        }, 200);

        initSDK();
    }

    @Override
    protected void initData() {

    }

    private void initSDK() {
        SMSSDK.initSDK(this, appKey, appSecret,false);
        SMSSDK.registerEventHandler(ev); //注册短信回调监听
    }

    /**
     * 短信验证的回调监听
     */
    private EventHandler ev = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            if (result == SMSSDK.RESULT_COMPLETE) { //回调完成
                //提交验证码成功,如果验证成功会在data里返回数据。data数据类型为HashMap<number,code>
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    Log.e("TAG", "提交验证码成功" + data.toString());
                    HashMap<String, Object> mData = (HashMap<String, Object>) data;
                    String country = (String) mData.get("country");//返回的国家编号
                    String phone = (String) mData.get("phone");//返回用户注册的手机号

                    Log.e("TAG", country + "====" + phone);

                    if (phone.equals(number)) {
                        runOnUiThread(new Runnable() {//更改ui的操作要放在主线程，实际可以发送hander
                            @Override
                            public void run() {
//                                showDailog("恭喜你！通过验证");
//                                dialog.dismiss();
                                registerToSerVice();
                                //    Toast.makeText(MainActivity.this, "通过验证", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showDailog("验证失败");
                                dialog.dismiss();
                                //     Toast.makeText(MainActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {//获取验证码成功
                    Log.e("TAG", "获取验证码成功");
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {//返回支持发送验证码的国家列表

                }
            } else {
                ((Throwable) data).printStackTrace();
            }
        }
    };
    private void showDailog(String text) {
        new AlertDialog.Builder(this)
                .setTitle(text)
                .setPositiveButton("确定", null)
                .show();
    }
    public void registerToSerVice() {
        userPsd = etPassword.getText().toString();
        if (TextUtil.isNull(userPsd)) {
            DevUtil.showInfo(this, getString(R.string.tip_psw_null));
            return;
        }

        final Dialog dialog = UiUtil.getLoadDialog(getContext(), true);
        dialog.show();
        OkHttpUtils.post()
                .url(Constant.register)
                .addParams("uid", number)
                .addParams("pwd", userPsd)
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
                                DevUtil.showInfo(RegisterActivity.this,"注册成功");
                                Intent data = new Intent();
                                data.putExtra("phone", number);
                                data.putExtra("password", userPsd);
                                setResult(REGISTER_BACK, data);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_sendSms:
                number = etUsername.getText().toString();
                if(DevUtil.isPhone(number)){
//                    DevUtil.showInfo(this,"true");
                    //发送短信，传入国家号和电话---使用SMSSDK核心类之前一定要在MyApplication中初始化，否侧不能使用
                    SMSSDK.getVerificationCode("+86", number);
                    Toast.makeText(this, "发送成功:" + number, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_register:
                String security = etVerify.getText().toString();
                if (!TextUtils.isEmpty(security)) {
//                    dialog = ProgressDialog.show(this, null, "正在验证...", false, true);
                    //提交短信验证码
                    SMSSDK.submitVerificationCode("+86", number, security);//国家号，手机号码，验证码
//                    Toast.makeText(this, "提交了注册信息:" + number, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //要在activity销毁时反注册，否侧会造成内存泄漏问题
        SMSSDK.unregisterAllEventHandler();
    }
}
