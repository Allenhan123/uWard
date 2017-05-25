package com.ygxinjian.anhui.youwardrobe.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.utils.DevUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by handongqiang on 17/5/17.
 */

public class Fragment_Vip_Month extends BaseFragment {
    @InjectView(R.id.cardView_months_1)
    CardView cardViewMonths1;
    @InjectView(R.id.cardView_months_2)
    CardView cardViewMonths2;
    @InjectView(R.id.cardView_months_3)
    CardView cardViewMonths3;

    private  BottomSheetBehavior sheetBehavior;
    private LinearLayout ll_wechat_pay,ll_ali_pay;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_vip_months, null);
        View shareView = mActivity.findViewById(R.id.share_view);
        //获取BottomSheetBehavior
        sheetBehavior = BottomSheetBehavior.from(shareView);

        ll_ali_pay = (LinearLayout) shareView.findViewById(R.id.ll_ali_pay);
        ll_wechat_pay = (LinearLayout) shareView.findViewById(R.id.ll_wechat_pay);
        ll_wechat_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DevUtil.showInfo(mActivity,"微信支付");
            }
        });
        ll_ali_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DevUtil.showInfo(mActivity,"支付宝支付");
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

        return view;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.cardView_months_1, R.id.cardView_months_2, R.id.cardView_months_3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cardView_months_1:
                if(sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }else {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
                break;
            case R.id.cardView_months_2:
                break;
            case R.id.cardView_months_3:
                break;
        }
    }
}
