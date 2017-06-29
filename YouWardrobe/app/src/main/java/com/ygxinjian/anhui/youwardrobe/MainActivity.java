package com.ygxinjian.anhui.youwardrobe;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.nostra13.universalimageloader.utils.L;
import com.ygxinjian.anhui.youwardrobe.Activity.CheckPermissionsActivity;
import com.ygxinjian.anhui.youwardrobe.Controller.ChangeEvent;
import com.ygxinjian.anhui.youwardrobe.Controller.RxBus;
import com.ygxinjian.anhui.youwardrobe.Controller.sharepreference.LocalData;
import com.ygxinjian.anhui.youwardrobe.Fragment.Fragment_Home;
import com.ygxinjian.anhui.youwardrobe.Fragment.MeFragment;
import com.ygxinjian.anhui.youwardrobe.Fragment.RecommendFragment;
import com.ygxinjian.anhui.youwardrobe.Fragment.WardrobeFragment;
import com.ygxinjian.anhui.youwardrobe.utils.DevUtil;
import com.ygxinjian.anhui.youwardrobe.utils.LocationUtils;
import com.ygxinjian.anhui.youwardrobe.utils.TextUtil;

import rx.Subscription;
import rx.functions.Action1;

public class MainActivity extends CheckPermissionsActivity implements BottomNavigationBar.OnTabSelectedListener {
    private static final String TAG = "MainActivity";
    public static String city;

    private BottomNavigationBar mBottomNavigationBar;
    private Fragment_Home homeFragment;
    private RecommendFragment recommendFragment;
    private WardrobeFragment youyardrobeFragment;
    private MeFragment meFragment;

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = new AMapLocationClientOption();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        initView();
        city = YouWardrobeApplication.getLocalData().getString(LocalData.KEY_USE_WEATHER);
        if (TextUtil.isNull(city)) {
            city = "合肥";
        }
        //初始化定位
        initLocation();

        startLocation();

    }


    @Override
    protected void initData() {

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
        showFragment(position);
    }

    private void showFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragments();
        switch (position) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new Fragment_Home();
                    transaction.add(R.id.ll_content, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                if (recommendFragment == null) {
                    recommendFragment = new RecommendFragment();
                    transaction.add(R.id.ll_content, recommendFragment);
                } else {
                    transaction.show(recommendFragment);
                }
                break;
            case 2:
                if (youyardrobeFragment == null) {
                    youyardrobeFragment = new WardrobeFragment();
                    transaction.add(R.id.ll_content, youyardrobeFragment);
                } else {
                    transaction.show(youyardrobeFragment);
                }
                break;
            case 3:
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    transaction.add(R.id.ll_content, meFragment);
                } else {
                    transaction.show(meFragment);
                }
                break;
            default:
                if (homeFragment == null) {
                    homeFragment = new Fragment_Home();
                    transaction.add(R.id.ll_content, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
        }
        transaction.commit();
    }

    //隐藏所有碎片视图
    private void hideFragments() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (recommendFragment != null) {
            transaction.hide(recommendFragment);
        }
        if (youyardrobeFragment != null) {
            transaction.hide(youyardrobeFragment);
        }
        if (meFragment != null) {
            transaction.hide(meFragment);
        }
        transaction.commitAllowingStateLoss();
    }


    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    private void initLocation() {
//        AMapLocationClientOption mOption = new AMapLocationClientOption();
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
//        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        locationOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        locationOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        locationOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        locationOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        locationOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        locationOption.setOnceLocationLatest(true);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        locationOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        locationOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        locationOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true

        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }


    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation loc) {
            if (null != loc) {
                //解析定位结果
                String result = LocationUtils.getLocationStr(loc);
                Log.e("LOCATION", result);
                Log.e("CITY ", loc.getCity());
                if (loc.getCity() != null) {
                    if (!city.equals(loc.getCity())) {
                        city = loc.getCity();
                        YouWardrobeApplication.getLocalData().setString(LocalData.KEY_USE_WEATHER, city);
                        //// TODO: 2017/3/28
                        //需要重新刷新天气
                       
                    }
                }
            } else {
                DevUtil.showInfo(MainActivity.this, "定位失败");
            }
        }
    };

    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void startLocation() {
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void stopLocation() {
        // 停止定位
        locationClient.stopLocation();
    }

    /**
     * 销毁定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyLocation();
    }

}
