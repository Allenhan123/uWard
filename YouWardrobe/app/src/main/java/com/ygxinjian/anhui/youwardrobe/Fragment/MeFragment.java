package com.ygxinjian.anhui.youwardrobe.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ygxinjian.anhui.youwardrobe.Activity.DressHistoryActivity;
import com.ygxinjian.anhui.youwardrobe.Activity.MoreVipActivity;
import com.ygxinjian.anhui.youwardrobe.Activity.ShareActivity;
import com.ygxinjian.anhui.youwardrobe.Activity.UserMessageActivity;
import com.ygxinjian.anhui.youwardrobe.Activity.UserStyleActivity;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.utils.DevUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by handongqiang on 17/3/13.
 */

public class MeFragment extends BaseFragment {
    @InjectView(R.id.tv_nick)
    TextView tvNick;
    @InjectView(R.id.rl_userMessage)
    RelativeLayout rlUserMessage;
    @InjectView(R.id.ll_dress_history)
    LinearLayout llDressHistory;
    @InjectView(R.id.ll_user_style)
    LinearLayout llUserStyle;
    @InjectView(R.id.ll_vip_information)
    LinearLayout llVipInformation;
    @InjectView(R.id.ll_user_information)
    LinearLayout llUserInformation;
    @InjectView(R.id.ll_new_user)
    LinearLayout llNewUser;
    @InjectView(R.id.ll_user_share)
    LinearLayout llUserShare;
    @InjectView(R.id.ll_about_our)
    LinearLayout llAboutOur;
    @InjectView(R.id.ll_user_exit)
    LinearLayout llUserExit;
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

    @OnClick({R.id.rl_userMessage, R.id.ll_dress_history, R.id.ll_user_style, R.id.ll_vip_information, R.id.ll_user_information, R.id.ll_new_user, R.id.ll_user_share, R.id.ll_about_our, R.id.ll_user_exit})
    public void onClick(View view) {
        switch (view.getId()) {
//            个人信息
            case R.id.rl_userMessage:
                DevUtil.gotoActivity(getContext(), UserMessageActivity.class);
                break;
//            穿衣记录
            case R.id.ll_dress_history:
                DevUtil.gotoActivity(getContext(), DressHistoryActivity.class);
                break;
//            风格设置
            case R.id.ll_user_style:
                DevUtil.gotoActivity(getContext(), UserStyleActivity.class);
                break;
//            更多特权  暂留最后处理
            case R.id.ll_vip_information:
                DevUtil.gotoActivity(getContext(), MoreVipActivity.class);
                break;
            case R.id.ll_user_information:
                DevUtil.gotoActivity(getContext(), UserMessageActivity.class);
                break;
//            新手帮助
            case R.id.ll_new_user:
                break;
//            分享
            case R.id.ll_user_share:
                DevUtil.gotoActivity(getContext(), ShareActivity.class);
                break;
//            关于我们
            case R.id.ll_about_our:
                break;
//            退出
            case R.id.ll_user_exit:
                mActivity.finish();
                break;
        }
    }

}
