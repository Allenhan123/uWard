package com.ygxinjian.anhui.youwardrobe.Controller;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.adapter.CBPageAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.ygxinjian.anhui.youwardrobe.R;


/**
 * Created by handongqiang on 17/1/6.
 */

public  class NetworkImageHolderView implements CBPageAdapter.Holder<String>{
    private ImageView imageView;
    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        imageView.setImageResource(R.mipmap.banner_loading);
        ImageLoader.getInstance().displayImage(data,imageView);
    }
}
