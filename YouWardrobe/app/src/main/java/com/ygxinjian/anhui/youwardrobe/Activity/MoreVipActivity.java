package com.ygxinjian.anhui.youwardrobe.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ygxinjian.anhui.youwardrobe.Fragment.Fragment_Home;
import com.ygxinjian.anhui.youwardrobe.Fragment.Fragment_Vip_Month;
import com.ygxinjian.anhui.youwardrobe.Fragment.MeFragment;
import com.ygxinjian.anhui.youwardrobe.Fragment.RecommendFragment;
import com.ygxinjian.anhui.youwardrobe.Fragment.WardrobeFragment;
import com.ygxinjian.anhui.youwardrobe.R;

import butterknife.ButterKnife;

/**
 * Created by handongqiang on 17/5/17.
 */

public class MoreVipActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener{

    private static final String TAG = "MoreVipActivity";

    private BottomNavigationBar mBottomNavigationBar;
    private Fragment_Vip_Month vipMonthFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_vip);
        initView();

    }

    private void initView() {
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.top_navigation_bar);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
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
//                if (homeFragment == null) {
//                    homeFragment = new Fragment_Home();
//                    transaction.add(R.id.ll_content, homeFragment);
//                } else {
//                    transaction.show(homeFragment);
//                }
                break;
            case 1:
//                if (recommendFragment == null) {
//                    recommendFragment = new RecommendFragment();
//                    transaction.add(R.id.ll_content, recommendFragment);
//                } else {
//                    transaction.show(recommendFragment);
//                }
                break;
            case 2:
//                if (youyardrobeFragment == null) {
//                    youyardrobeFragment = new WardrobeFragment();
//                    transaction.add(R.id.ll_content, youyardrobeFragment);
//                } else {
//                    transaction.show(youyardrobeFragment);
//                }
                break;

            default:
//                if (homeFragment == null) {
//                    homeFragment = new Fragment_Home();
//                    transaction.add(R.id.ll_content, homeFragment);
//                } else {
//                    transaction.show(homeFragment);
//                }
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
//        if (recommendFragment != null) {
//            transaction.hide(recommendFragment);
//        }
//        if (youyardrobeFragment != null) {
//            transaction.hide(youyardrobeFragment);
//        }
//        if (meFragment != null) {
//            transaction.hide(meFragment);
//        }
        transaction.commitAllowingStateLoss();
    }


    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
