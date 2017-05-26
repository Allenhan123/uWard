package com.ygxinjian.anhui.youwardrobe.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.adapter.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.ygxinjian.anhui.youwardrobe.Activity.ClassifyActivity;
import com.ygxinjian.anhui.youwardrobe.Constant;
import com.ygxinjian.anhui.youwardrobe.Controller.HomeExpandableListViewAdapter;
import com.ygxinjian.anhui.youwardrobe.Controller.NetworkImageHolderView;
import com.ygxinjian.anhui.youwardrobe.Controller.ShopcartExpandableListViewAdapter;
import com.ygxinjian.anhui.youwardrobe.Controller.sharepreference.LocalData;
import com.ygxinjian.anhui.youwardrobe.MainActivity;
import com.ygxinjian.anhui.youwardrobe.Model.BannerModel;
import com.ygxinjian.anhui.youwardrobe.Model.HomeCategoryModel;
import com.ygxinjian.anhui.youwardrobe.Model.NetResultModel;
import com.ygxinjian.anhui.youwardrobe.Model.RecommendSingleModel;
import com.ygxinjian.anhui.youwardrobe.Model.WeatherModel;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.View.WardrobeProgress;
import com.ygxinjian.anhui.youwardrobe.YouWardrobeApplication;
import com.ygxinjian.anhui.youwardrobe.utils.DevUtil;
import com.ygxinjian.anhui.youwardrobe.utils.TimeUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * HOME  TEST
 * Created by handongqiang on 17/3/13.
 */

public class Fragment_Home extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = Fragment_Home.class.getSimpleName();
    @InjectView(R.id.nav_tv_title)
    ImageView navTvTitle;
    @InjectView(R.id.nav_go_back)
    ImageView navGoBack;
    @InjectView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @InjectView(R.id.explist_view)
    ExpandableListView listView;

    private WardrobeProgress wardrobeProgress;
    private WeatherModel weatherModel;

    private HomeCategoryModel homeCategoryModel;

    private List<HomeCategoryModel.ResultBean.DataBean> groupList;
    List<List<HomeCategoryModel.ResultBean.DataBean.ItemsBean>> itemList;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_homes, null);
        wardrobeProgress = new WardrobeProgress(mActivity);
        wardrobeProgress.show();
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setColorSchemeResources(R.color.main_Red, R.color.color_text_theme);
        initHeadView();
        getData();
        listView.addHeaderView(headView);
        return rootView;
    }

    private void getData() {
        OkHttpUtils.get().url(Constant.homeUrl)
                .addParams("uid", "18656009327")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        wardrobeProgress.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        wardrobeProgress.dismiss();
                        Gson gson = new Gson();
                        homeCategoryModel = gson.fromJson(response, HomeCategoryModel.class);
                        Log.d(TAG, "onResponse: " + homeCategoryModel.toString());
                        groupList = new ArrayList<>();
                        groupList = homeCategoryModel.getResult().getData();
                        itemList = new ArrayList<>();
                        for (int i = 0; i < groupList.size(); i++) {
                            List<HomeCategoryModel.ResultBean.DataBean.ItemsBean> itemGridList = groupList.get(i).getItems();
                            itemList.add(itemGridList);
                        }

                        // 创建适配器
                        HomeExpandableListViewAdapter adapter = new HomeExpandableListViewAdapter(mActivity,
                                groupList, itemList);
                        listView.setAdapter(adapter);
                        for (int i = 0; i < groupList.size(); i++) {
                            listView.expandGroup(i);// 初始化时，将ExpandableListView以展开的方式呈现
                        }        // 隐藏分组指示器
                        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                            @Override
                            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                                Intent _intent = new Intent(mActivity,ClassifyActivity.class);
                                _intent.putExtra("title",groupList.get(groupPosition).getClassifyTitle());
                                _intent.putExtra("url",groupList.get(groupPosition).getClassifyURL());
                                startActivity(_intent);
                                return true;
                            }
                        });
                    }
                });
    }

    private LinearLayout headView;
    private TextView tv_city;
    private TextView tv_time;
    private TextView tv_describe;
    private TextView tv_temperature;
    private ImageView iv_weather;
    private ConvenientBanner mConvenientBanner;

    private void initHeadView() {
        headView = (LinearLayout) View.inflate(getContext(), R.layout.head_view_home, null);
        tv_city = (TextView) headView.findViewById(R.id.location_city);
        tv_describe = (TextView) headView.findViewById(R.id.describe_weather);
        tv_temperature = (TextView) headView.findViewById(R.id.temperature);
        tv_time = (TextView) headView.findViewById(R.id.day_year);
        iv_weather = (ImageView) headView.findViewById(R.id.iv_weather);

        // 时间
        tv_time.setText(TimeUtil.longToString(System.currentTimeMillis(), TimeUtil.FORMAT_YEAR_MONTH_DAY));

        mConvenientBanner = (ConvenientBanner) headView.findViewById(R.id.banner);

        initWeather();
        getBannerDataFromService();
    }


    /**
     * 获取Banner数据
     */

    private void getBannerDataFromService() {
        OkHttpUtils.get().url(Constant.bannerUrl).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        BannerModel bannerModel = BannerModel.getGson().fromJson(response, BannerModel.class);


                        List<String> list = new ArrayList<String>();
                        for (BannerModel.ResultBean.DataBean model : bannerModel.getResult().getData()) {
                            list.add(model.getImgUrl());
                        }

                        if (bannerModel.getCode() == NetResultModel.RESULT_CODE_SUCCESS) {
                            mConvenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
                                @Override
                                public NetworkImageHolderView createHolder() {
                                    return new NetworkImageHolderView();
                                }
                            }, list)
                                    .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_pager_focused})
                                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
                            initBanner();

                        } else {
                            DevUtil.showInfo(getContext(), "请求失败");

                        }
                    }
                });


    }


    private void initWeather() {
        if (MainActivity.city != null) {
            Log.e("CITY", MainActivity.city);
            OkHttpUtils.get().url(Constant.weatherUrl).addParams("cityname", MainActivity.city).build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    Log.d("Weather", e.toString());
                }

                @Override
                public void onResponse(String response, int id) {
                    Log.d("Weather", response);
                    Gson gson = new Gson();
                    weatherModel = gson.fromJson(response, WeatherModel.class);
                    if (weatherModel.getReason().equals("Succes")) {
                        tv_city.setText(MainActivity.city);
                        StringBuffer sb = new StringBuffer();
                        sb.append(weatherModel.getResult().getRealtime().getWeather().getTemperature() + "°");
                        tv_temperature.setText(sb);
                        tv_describe.setText(weatherModel.getResult().getLife().getInfo().getChuanyi().get(1));
                        String info = weatherModel.getResult().getRealtime().getWeather().getInfo();
                        if (info.contains("晴")) {
                            iv_weather.setImageResource(R.mipmap.weather_fine);
                        } else if (info.contains("雨")) {
                            iv_weather.setImageResource(R.mipmap.weather_fine);
                        } else if (info.contains("雪")) {
                            iv_weather.setImageResource(R.mipmap.weather_snow);
                        } else if (info.contains("云")) {
                            iv_weather.setImageResource(R.mipmap.weather_gray);
                        } else {
                            iv_weather.setImageResource(R.mipmap.weather_cloud);
                        }
                    }
                }
            });
        }
    }

    private void initBanner() {
        mConvenientBanner.startTurning(3000);
        // 进入Banner
        mConvenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.nav_go_back)
    public void onClick() {

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
                initWeather();
                // 加载完数据设置为不刷新状态，将下拉进度收起来
                swipeRefresh.setRefreshing(false);
            }
        }, 1200);
    }
}
