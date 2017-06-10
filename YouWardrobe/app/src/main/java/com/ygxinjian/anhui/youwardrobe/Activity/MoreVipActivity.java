package com.ygxinjian.anhui.youwardrobe.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ygxinjian.anhui.youwardrobe.Fragment.Fragment_Vip_Ji;
import com.ygxinjian.anhui.youwardrobe.Fragment.Fragment_Vip_Month;
import com.ygxinjian.anhui.youwardrobe.Fragment.Fragment_Vip_Year;
import com.ygxinjian.anhui.youwardrobe.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by handongqiang on 17/5/17.
 */

public class MoreVipActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    private static final String TAG = "MoreVipActivity";
    @InjectView(R.id.nav_go_back)
    ImageView navGoBack;
    @InjectView(R.id.nav_tv_title)
    TextView navTvTitle;
    @InjectView(R.id.nav_right)
    TextView navRight;
    @InjectView(R.id.ll_vip_content)
    FrameLayout llVipContent;
    @InjectView(R.id.top_navigation_bar)
    BottomNavigationBar topNavigationBar;

    private BottomNavigationBar mBottomNavigationBar;
    private Fragment_Vip_Month vipMonthFragment;
    private Fragment_Vip_Ji vipJiFragment;
    private Fragment_Vip_Year vipYearFragment;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_more_vip;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        navTvTitle.setText("开通会员");
        initView();
    }

    @Override
    protected void initData() {

    }

    private void initView() {
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.top_navigation_bar);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_DEFAULT);
//        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setBarBackgroundColor(R.color.white_1);//set background color for navigation bar
        mBottomNavigationBar.setInActiveColor(R.color.default_gray);//unSelected icon color
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.vip_mon_normal_icon, R.string.vip_mon).setActiveColorResource(R.color.main_Red))
                .addItem(new BottomNavigationItem(R.mipmap.vip_ji_normal_icon, R.string.vip_ji).setActiveColorResource(R.color.main_Red))
                .addItem(new BottomNavigationItem(R.mipmap.vip_year_bormal_icon, R.string.vip_year).setActiveColorResource(R.color.main_Red))
                .setFirstSelectedPosition(0)
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        vipMonthFragment = new Fragment_Vip_Month();
        transaction.replace(R.id.ll_vip_content, vipMonthFragment).commit();
    }

    //Tab的点击事件
    @Override
    public void onTabSelected(int position) {
        showFragment(position);
    }

    private void showFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragments();
        switch (position) {
            case 0:
                if (vipMonthFragment == null) {
                    vipMonthFragment = new Fragment_Vip_Month();
                    transaction.add(R.id.ll_vip_content, vipMonthFragment);
                } else {
                    transaction.show(vipMonthFragment);
                }
                break;
            case 1:
                if (vipJiFragment == null) {
                    vipJiFragment = new Fragment_Vip_Ji();
                    transaction.add(R.id.ll_vip_content, vipJiFragment);
                } else {
                    transaction.show(vipJiFragment);
                }
                break;
            case 2:
                if (vipYearFragment == null) {
                    vipYearFragment = new Fragment_Vip_Year();
                    transaction.add(R.id.ll_vip_content, vipYearFragment);
                } else {
                    transaction.show(vipMonthFragment);
                }
                break;

            default:
                if (vipMonthFragment == null) {
                    vipMonthFragment = new Fragment_Vip_Month();
                    transaction.add(R.id.ll_vip_content, vipMonthFragment);
                } else {
                    transaction.show(vipMonthFragment);
                }
                break;
        }
        transaction.commit();
    }

    //隐藏所有碎片视图
    private void hideFragments() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (vipMonthFragment != null) {
            transaction.hide(vipMonthFragment);
        }
        if (vipJiFragment != null) {
            transaction.hide(vipJiFragment);
        }
        if (vipYearFragment != null) {
            transaction.hide(vipYearFragment);
        }
        transaction.commitAllowingStateLoss();
    }


    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @OnClick({R.id.nav_go_back, R.id.nav_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_go_back:
                finish();
                break;
            case R.id.nav_right:
                break;
        }
    }
}
