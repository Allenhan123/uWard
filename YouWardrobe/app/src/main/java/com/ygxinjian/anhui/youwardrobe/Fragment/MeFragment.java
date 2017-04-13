package com.ygxinjian.anhui.youwardrobe.Fragment;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.View.WardrobeProgress;

/**
 * Created by handongqiang on 17/3/13.
 */

public class MeFragment extends BaseFragment {
    private ImageView iv_back;
    private TextView tv_title;
    private WardrobeProgress wardrobeProgress;
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_me, null);
        iv_back = (ImageView) view.findViewById(R.id.nav_go_back);
        iv_back.setVisibility(View.GONE);
        tv_title = (TextView) view.findViewById(R.id.nav_tv_title);
        tv_title.setText("");
        return view;
    }
}
