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

public class Fragment_Me extends BaseFragment {
    private ImageView iv_back;
    private TextView tv_title;
    private WardrobeProgress wardrobeProgress;
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_me, null);
        wardrobeProgress = new WardrobeProgress(mActivity);
        wardrobeProgress.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                wardrobeProgress.dismiss();
            }
        },4000);

        iv_back = (ImageView) view.findViewById(R.id.nav_go_back);
        iv_back.setVisibility(View.GONE);
        tv_title = (TextView) view.findViewById(R.id.nav_tv_title);
        tv_title.setText("");
        return view;
    }
}
