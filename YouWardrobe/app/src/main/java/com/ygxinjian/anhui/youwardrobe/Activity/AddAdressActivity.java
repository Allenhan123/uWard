package com.ygxinjian.anhui.youwardrobe.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ygxinjian.anhui.youwardrobe.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/7/8.
 */
public class AddAdressActivity extends BaseActivity {


    @InjectView(R.id.nav_go_back)
    ImageView navGoBack;
    @InjectView(R.id.nav_tv_title)
    TextView navTvTitle;
    @InjectView(R.id.nav_right)
    TextView navRight;
    @InjectView(R.id.receiver_name)
    EditText receiverName;
    @InjectView(R.id.receiver_phone)
    EditText receiverPhone;
    @InjectView(R.id.country_name)
    TextView countryName;
    @InjectView(R.id.ll_country_select)
    LinearLayout llCountrySelect;
    @InjectView(R.id.address)
    EditText address;
    @InjectView(R.id.user_address_is_default)
    CheckBox userAddressIsDefault;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addadress);
        ButterKnife.inject(this);
        navTvTitle.setText("新增收货地址");
    }


    @OnClick({R.id.nav_go_back, R.id.nav_right, R.id.ll_country_select, R.id.user_address_is_default})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_go_back:
                finish();
                break;
            case R.id.nav_right:
                break;
            case R.id.ll_country_select:
                break;
            case R.id.user_address_is_default:
                break;
        }
    }
}
