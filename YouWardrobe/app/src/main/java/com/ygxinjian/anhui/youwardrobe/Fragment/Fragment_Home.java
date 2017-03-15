package com.ygxinjian.anhui.youwardrobe.Fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.View.WardrobeProgress;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

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

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_home, null);
//        wardrobeProgress = new WardrobeProgress(mActivity);
//        wardrobeProgress.show();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                wardrobeProgress.dismiss();
//            }
//        },4000);
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

    private void initHeadView() {
        headView = (LinearLayout) View.inflate(getContext(), R.layout.head_view_home, null);

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
