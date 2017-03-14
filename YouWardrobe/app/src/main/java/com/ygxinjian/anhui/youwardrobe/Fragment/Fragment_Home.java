package com.ygxinjian.anhui.youwardrobe.Fragment;

import android.os.Handler;
import android.view.View;

import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.View.WardrobeProgress;

/**
 * Created by handongqiang on 17/3/13.
 */

public class Fragment_Home extends BaseFragment {
    private WardrobeProgress wardrobeProgress;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_home, null);
        wardrobeProgress = new WardrobeProgress(mActivity);
        wardrobeProgress.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                wardrobeProgress.dismiss();
            }
        },4000);
        return view;
    }
}
