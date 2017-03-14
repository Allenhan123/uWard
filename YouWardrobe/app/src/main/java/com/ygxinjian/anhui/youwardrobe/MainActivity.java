package com.ygxinjian.anhui.youwardrobe;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ygxinjian.anhui.youwardrobe.Activity.BaseActivity;
import com.ygxinjian.anhui.youwardrobe.Fragment.Fragment_Home;
import com.ygxinjian.anhui.youwardrobe.Fragment.Fragment_Me;
import com.ygxinjian.anhui.youwardrobe.Fragment.Fragment_Recommend;
import com.ygxinjian.anhui.youwardrobe.Fragment.Fragment_Wardrobe;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener{

    private static final String TAG = "MainActivity";
    private BottomNavigationBar mBottomNavigationBar;
    private Fragment_Home homeFragment;
    private Fragment_Recommend recommendFragment;
    private Fragment_Wardrobe youyardrobeFragment;
    private Fragment_Me meFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setBarBackgroundColor(R.color.white_1);//set background color for navigation bar
        mBottomNavigationBar.setInActiveColor(R.color.default_gray);//unSelected icon color
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.homes_normal, R.string.home_name).setActiveColorResource(R.color.main_Red))
                .addItem(new BottomNavigationItem(R.mipmap.recommend_normal, R.string.recommend_name).setActiveColorResource(R.color.main_Red))
                .addItem(new BottomNavigationItem(R.mipmap.wardrobe_normal, R.string.wardrobe_name).setActiveColorResource(R.color.main_Red))
                .addItem(new BottomNavigationItem(R.mipmap.me_normal, R.string.me_name).setActiveColorResource(R.color.main_Red))
                .setFirstSelectedPosition(0)
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        homeFragment = new Fragment_Home();
        transaction.replace(R.id.ll_content, homeFragment).commit();
    }
    //Tab的点击事件
    @Override
    public void onTabSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new Fragment_Home();
                }
                transaction.replace(R.id.ll_content, homeFragment);
                break;
            case 1:
                if (recommendFragment == null) {
                    recommendFragment = new Fragment_Recommend();
                }
                transaction.replace(R.id.ll_content, recommendFragment);
                break;
            case 2:
                if (youyardrobeFragment == null) {
                    youyardrobeFragment = new Fragment_Wardrobe();
                }
                transaction.replace(R.id.ll_content, youyardrobeFragment);
                break;
            case 3:
                if (meFragment == null) {
                    meFragment = new Fragment_Me();
                }
                transaction.replace(R.id.ll_content, meFragment);
                break;
            default:
                if (homeFragment == null) {
                    homeFragment = new Fragment_Home();
                }
                transaction.replace(R.id.ll_content, homeFragment);
                break;
        }
        transaction.commit();

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
