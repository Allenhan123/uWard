package com.ygxinjian.anhui.youwardrobe.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ygxinjian.anhui.youwardrobe.Activity.UserMessageActivity;
import com.ygxinjian.anhui.youwardrobe.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by handongqiang on 17/3/13.
 */

public class MeFragment extends BaseFragment {
    private ImageView navGoBack;
    private TextView navTvTitle;
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_me, null);
        navGoBack = (ImageView) view.findViewById(R.id.nav_go_back);
        navTvTitle = (TextView) view.findViewById(R.id.nav_tv_title);
        navGoBack.setVisibility(View.GONE);
        navTvTitle.setText("");
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);


        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.ll_user_information)
    public void openUserMessage() {
        Intent intent = new Intent(getContext(), UserMessageActivity.class);
        startActivity(intent);
    }

}
