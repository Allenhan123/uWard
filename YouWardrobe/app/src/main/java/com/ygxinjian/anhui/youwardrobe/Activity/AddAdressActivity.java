package com.ygxinjian.anhui.youwardrobe.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ygxinjian.anhui.youwardrobe.Controller.sharepreference.LocalData;
import com.ygxinjian.anhui.youwardrobe.Model.AddressBodyModel;
import com.ygxinjian.anhui.youwardrobe.Model.NetResultModel;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.api.Api;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
    protected int getLayoutId() {
        return R.layout.activity_addadress;

    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        navTvTitle.setText("新增收货地址");
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.nav_go_back, R.id.nav_right, R.id.ll_country_select, R.id.user_address_is_default})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_go_back:
                finish();
                break;
            case R.id.nav_right:
                addAddressToNet();
                break;
            case R.id.ll_country_select:
                break;
            case R.id.user_address_is_default:
                break;
        }
    }


    private void addAddressToNet() {
        AddressBodyModel addressBodyMode = new AddressBodyModel();
        addressBodyMode.setUid("18656009327");
        addressBodyMode.setProvince("安徽省");
        addressBodyMode.setCity("合肥");
        addressBodyMode.setTownship("包河");
        addressBodyMode.setStreet("街道");
        addressBodyMode.setAddress("详细地址");
        addressBodyMode.setIpCode("100000");
        addressBodyMode.setIsDefault(0);
        addressBodyMode.setContact("打死小强");
        addressBodyMode.setTelephone("15256029226");


        Api.
                getYouWardrobeApi()
                .managerAddress(addressBodyMode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<NetResultModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NetResultModel netResultModel) {

                    }
                });
    }
}
