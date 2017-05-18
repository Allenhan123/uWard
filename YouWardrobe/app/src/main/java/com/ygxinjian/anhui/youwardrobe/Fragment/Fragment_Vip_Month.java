package com.ygxinjian.anhui.youwardrobe.Fragment;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ygxinjian.anhui.youwardrobe.R;

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

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_vip_months, null);
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
                break;
            case R.id.cardView_months_2:
                break;
            case R.id.cardView_months_3:
                break;
        }
    }
}
