package com.ygxinjian.anhui.youwardrobe.Fragment;

import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.adapter.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.ygxinjian.anhui.youwardrobe.Constant;
import com.ygxinjian.anhui.youwardrobe.Controller.NetworkImageHolderView;
import com.ygxinjian.anhui.youwardrobe.MainActivity;
import com.ygxinjian.anhui.youwardrobe.Model.WeatherModel;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.View.WardrobeProgress;
import com.ygxinjian.anhui.youwardrobe.utils.DevUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by handongqiang on 17/3/13.
 */

public class Fragment_Home extends BaseFragment {
    @InjectView(R.id.nav_tv_title)
    ImageView navTvTitle;
    @InjectView(R.id.nav_go_back)
    ImageView navGoBack;
    @InjectView(R.id.list_view)
    ListView listView;
    @InjectView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private WardrobeProgress wardrobeProgress;
    private WeatherModel weatherModel;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_home, null);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);


        initHeadView();
        listView.addHeaderView(headView);
        listView.setAdapter(new MyAdapter());
        return rootView;
    }


    private LinearLayout headView;
    private TextView tv_city;
    private TextView tv_time;
    private TextView tv_describe;
    private TextView tv_temperature;
    private ImageView iv_weather;
    private ConvenientBanner mConvenientBanner;
    private String[] images = {
            "http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
    };
    private void initHeadView() {
        headView = (LinearLayout) View.inflate(getContext(), R.layout.head_view_home, null);
        tv_city = (TextView) headView.findViewById(R.id.location_city);
        tv_describe = (TextView) headView.findViewById(R.id.describe_weather);
        tv_temperature = (TextView) headView.findViewById(R.id.temperature);
        tv_time = (TextView) headView.findViewById(R.id.day_year);
        iv_weather = (ImageView) headView.findViewById(R.id.iv_weather);

//        时间
        SimpleDateFormat   formatter   =   new SimpleDateFormat("yyyy年MM月dd日");
        Date curDate =  new Date(System.currentTimeMillis());
        String  time   =   formatter.format(curDate);
        tv_time.setText(time);

        mConvenientBanner = (ConvenientBanner) headView.findViewById(R.id.banner);
        mConvenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, Arrays.asList(images))
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_pager_focused})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

        initWeather();
        initBanner();

    }

    private void initWeather() {
        if(MainActivity.city!=null){
            Log.e("CITY",MainActivity.city);
            OkHttpUtils.get().url(Constant.weatherUrl).addParams("cityname", MainActivity.city).build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {

                }
                @Override
                public void onResponse(String response, int id) {
                Log.d("Weather",response);
                    Gson gson = new Gson();
                    weatherModel = gson.fromJson(response,WeatherModel.class);
                    if(weatherModel.getReason().equals("Succes")) {
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

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (position<3){
                convertView = View.inflate(getContext(), R.layout.item_home_type_1, null);
            }else {
                convertView = View.inflate(getContext(), R.layout.item_home_type_2, null);
            }

            return convertView;
        }
    }
}
