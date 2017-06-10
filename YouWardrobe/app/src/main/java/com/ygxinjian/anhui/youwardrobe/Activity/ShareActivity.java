package com.ygxinjian.anhui.youwardrobe.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.utils.DevUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by handongqiang on 17/5/25.
 */

public class ShareActivity extends BaseActivity {
    @InjectView(R.id.nav_go_back)
    ImageView navGoBack;
    @InjectView(R.id.nav_tv_title)
    TextView navTvTitle;
    @InjectView(R.id.nav_right)
    TextView navRight;
    private BottomSheetBehavior sheetBehavior;
    private LinearLayout ll_wechat,ll_friend;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_share;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        navTvTitle.setText("分享好友");
        navRight.setText("分享");
        View shareView = findViewById(R.id.share_view);

        //获取BottomSheetBehavior
        sheetBehavior = BottomSheetBehavior.from(shareView);

        ll_wechat = (LinearLayout) shareView.findViewById(R.id.ll_wechat_share);
        ll_friend = (LinearLayout) shareView.findViewById(R.id.ll_friend_share);
        ll_wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DevUtil.showInfo(ShareActivity.this,"微信分享");
            }
        });
        ll_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DevUtil.showInfo(ShareActivity.this,"朋友圈分享");
            }
        });
        //设置折叠时的高度
        //sheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);

        //监听BottomSheetBehavior 状态的变化
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        //下滑的时候是否可以隐藏
        sheetBehavior.setHideable(true);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.nav_go_back, R.id.nav_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_go_back:
                finish();
                break;
            case R.id.nav_right:
                if(sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }else {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
                break;
        }
    }
}
