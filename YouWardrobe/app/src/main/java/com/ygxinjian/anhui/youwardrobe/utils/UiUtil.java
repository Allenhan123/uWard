package com.ygxinjian.anhui.youwardrobe.utils;

import android.app.Dialog;
import android.content.Context;

import com.ygxinjian.anhui.youwardrobe.View.WardrobeProgress;
import com.ygxinjian.anhui.youwardrobe.weigt.ProgressHUDDialog;

/**
 * Created by ${Ua} on 2017/4/13.
 */

public class UiUtil {

    /**
     * 获取加载的dialog  方便后期修改
     *
     * @param context
     * @param Cancelable
     * @return
     */
    public static Dialog getLoadDialog(Context context, boolean Cancelable) {
        Dialog dialog = new ProgressHUDDialog(context);
        dialog.setCancelable(Cancelable);
        return dialog;
    }
}
