package com.ygxinjian.anhui.youwardrobe.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allattentionhere.fabulousfilter.AAH_FabulousFragment;
import com.ygxinjian.anhui.youwardrobe.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by handongqiang on 17/7/5.
 */


public class SelectorFragment extends AAH_FabulousFragment implements View.OnClickListener{

    private TextView tv_hot,tv_stars,tv_new,tv_s,tv_m,tv_l,tv_xl,tv_xxl;
    private String tv_order,tv_size;


    public static SelectorFragment newInstance() {
        SelectorFragment f = new SelectorFragment();
        return f;
    }

    @Override

    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.filter_kefu_view, null);
        RelativeLayout rl_content = (RelativeLayout) contentView.findViewById(R.id.rl_content);
        LinearLayout ll_buttons = (LinearLayout) contentView.findViewById(R.id.ll_buttons);
        tv_hot = (TextView) contentView.findViewById(R.id.tv_hot);
        tv_stars = (TextView) contentView.findViewById(R.id.tv_stars);
        tv_new = (TextView) contentView.findViewById(R.id.tv_new);
        tv_s = (TextView) contentView.findViewById(R.id.tv_S);
        tv_m = (TextView) contentView.findViewById(R.id.tv_M);
        tv_xl = (TextView) contentView.findViewById(R.id.tv_XL);
        tv_l = (TextView) contentView.findViewById(R.id.tv_L);
        tv_xxl = (TextView) contentView.findViewById(R.id.tv_XXL);
        tv_hot.setOnClickListener(this);
        tv_stars.setOnClickListener(this);
        tv_new.setOnClickListener(this);
        tv_s.setOnClickListener(this);
        tv_m.setOnClickListener(this);
        tv_l.setOnClickListener(this);
        tv_xl.setOnClickListener(this);
        tv_xxl.setOnClickListener(this);
        initTvView();
        tv_order = "";
        tv_size = "";

        contentView.findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFilter("closed");
            }
        });
//        点击确定回调                   closeFilter(value);  传递值过去
        ll_buttons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFilter(tv_order+tv_size);
            }
        });
        //params to set
        setAnimationDuration(500); //optional; default 500ms
        setPeekHeight(300); // optional; default 400dp
        setCallbacks((Callbacks) getActivity()); //optional; to get back result
        setViewgroupStatic(ll_buttons); // optional; layout to stick at bottom on slide
//        setViewPager(vp_types); //optional; if you use viewpager that has scrollview
        setViewMain(rl_content); //necessary; main bottomsheet view
        setMainContentView(contentView); // necessary; call at end before super
        super.setupDialog(dialog, style); //call super at last
    }

    private void initTvView() {
        tv_hot.setTextColor(this.getResources().getColor(R.color.defau_gray));
        tv_stars.setTextColor(this.getResources().getColor(R.color.defau_gray));
        tv_new.setTextColor(this.getResources().getColor(R.color.defau_gray));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_hot:
                initTvView();
                tv_hot.setTextColor(getResources().getColor(R.color.main_Red));
                tv_order = "热度";
                break;
            case R.id.tv_new:
                initTvView();
                tv_new.setTextColor(getResources().getColor(R.color.main_Red));
                tv_order = "最新";
                break;
            case R.id.tv_stars:
                initTvView();
                tv_stars.setTextColor(getResources().getColor(R.color.main_Red));
                tv_order = "喜欢数";
                break;
            case R.id.tv_S:
                tv_s.setTextColor(getResources().getColor(R.color.main_Red));
                tv_size = "S";
                break;
            case R.id.tv_M:
                tv_m.setTextColor(getResources().getColor(R.color.main_Red));
                tv_size = "M";
                break;
            case R.id.tv_L:
                tv_l.setTextColor(getResources().getColor(R.color.main_Red));
                tv_size = "L";
                break;
            case R.id.tv_XL:
                tv_xl.setTextColor(getResources().getColor(R.color.main_Red));
                tv_size = "xl";
                break;
            case R.id.tv_XXL:

                tv_xxl.setTextColor(getResources().getColor(R.color.main_Red));
                tv_size = "xxl";
                break;
        }
    }

}

