package com.ygxinjian.anhui.youwardrobe.Controller;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.ygxinjian.anhui.youwardrobe.R;

/**
 * Created by ${Ua} on 2017/3/10.
 */

public class ImageOptionUtils {


    // 个人  圆形    默认头像
    public static DisplayImageOptions getUserCircleOptions() {
        DisplayImageOptions options = (new DisplayImageOptions.Builder())
                .showImageForEmptyUri(R.color.white)
                .showImageOnFail(R.color.white)
                .showImageOnLoading(R.color.white)
                .displayer(new RoundedBitmapDisplayer(360))
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        return options;
    }

    public static DisplayImageOptions getDefaultOptions() {
        DisplayImageOptions options = (new DisplayImageOptions.Builder()).showImageForEmptyUri(R.color.white)
                .showImageOnFail(R.color.white).showImageOnLoading(R.color.white).displayer(new SimpleBitmapDisplayer())
                .cacheInMemory(true).cacheOnDisk(true).build();
        return options;
    }

}
