package com.ygxinjian.anhui.youwardrobe.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.utils.DevUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by handongqiang on 17/5/11.
 */

public class GetAdressActivity extends BaseActivity {


//    recycleView item 布局为 include_item_adress


    @InjectView(R.id.nav_go_back)
    ImageView navGoBack;
    @InjectView(R.id.nav_tv_title)
    TextView navTvTitle;
    @InjectView(R.id.nav_right)
    TextView navRight;
    @InjectView(R.id.recycleView_adress)
    RecyclerView recycleViewAdress;
    @InjectView(R.id.fab1)
    FloatingActionButton fab1;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_get_adress;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        navTvTitle.setText("收货地址");
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.nav_go_back, R.id.nav_right,R.id.fab1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_go_back:
                finish();
                break;
            case R.id.nav_right:
                save();
                break;
            case R.id.fab1:
                DevUtil.gotoActivity(getContext(),AddAdressActivity.class);
                break;
        }
    }


    private void save() {

    }


}
