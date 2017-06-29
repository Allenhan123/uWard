package com.ygxinjian.anhui.youwardrobe.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ygxinjian.anhui.youwardrobe.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by handongqiang on 17/6/29.
 */

public class WardrobeActivity extends BaseActivity {
    @InjectView(R.id.nav_go_back)
    ImageView navGoBack;
    @InjectView(R.id.nav_tv_title)
    TextView navTvTitle;
    @InjectView(R.id.nav_right)
    ImageView navRight;
    @InjectView(R.id.exListView)
    ExpandableListView exListView;
    @InjectView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @InjectView(R.id.tv_delete)
    TextView tvDelete;
    @InjectView(R.id.tv_go_to_pay)
    TextView tvGoToPay;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wardrobe;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        ButterKnife.inject(this);

        navTvTitle.setText(R.string.my_wardrobe_name);
        navRight.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.nav_go_back, R.id.tv_delete, R.id.tv_go_to_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_go_back:
                finish();
                break;
            case R.id.tv_delete:
                break;
            case R.id.tv_go_to_pay:
                break;
        }
    }
}
