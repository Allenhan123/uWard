package com.ygxinjian.anhui.youwardrobe.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.ygxinjian.anhui.youwardrobe.YouWardrobeApplication;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by handongqiang on 16/12/22.
 */

public class DevUtil {
    private static final String TAG = DevUtil.class.getSimpleName();

    public DevUtil() {
    }
    public static void gotoActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    public static void gotoActivity(Context context, Class cls, String extra) {
        Intent intent = new Intent(context, cls);
        intent.putExtra("data", extra);
        startActivity(context, intent);
    }

    public static void startActivity(Context context, Intent mIntent) {
        startActivity(context, mIntent, "error");
    }
    public static void startActivity(Context context, Intent mIntent, String errInfo) {
        try {
            context.startActivity(mIntent);
        } catch (Exception var4) {
            var4.printStackTrace();
            DevUtil.showInfo(context, errInfo);
        }

    }

    public static void showInfo(Context context, String string) {
        if(!string.isEmpty()) {
            Log.d(TAG, "showInfo: " + string);
            Toast makeText = Toast.makeText(context, string, Toast.LENGTH_LONG);
            if(makeText != null) {
                makeText.show();
            }
        }

    }



    public static void showShortInfo(Context context, String string) {
        if(!string.isEmpty()) {
            Log.d(TAG, "showInfo: " + string);
            Toast makeText = Toast.makeText(context, string, Toast.LENGTH_SHORT);
            if(makeText != null) {
                makeText.show();
            }
        }

    }

    public static boolean isPhone(String phone) {
        String regex = "^(1)\\d{10}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    public static void closeImm(Activity activity) {
        if(activity != null) {
            View currentFocus = activity.getCurrentFocus();
            if(currentFocus != null) {
                closeImm(activity, currentFocus.getWindowToken());
            }
        }
    }

    public static void closeImm(Context context, IBinder windowToken) {
        InputMethodManager inputMethodManager = (InputMethodManager)context.getSystemService("input_method");
        inputMethodManager.hideSoftInputFromWindow(windowToken, 2);
    }

    public static void deleteFolder(String dir) {
        File delfolder = new File(dir);
        File[] oldFile = delfolder.listFiles();

        try {
            for(int e = 0; e < oldFile.length; ++e) {
                if(oldFile[e].isDirectory()) {
                    deleteFolder(dir + oldFile[e].getName() + "//");
                }

                oldFile[e].delete();
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }


    public static Dialog getTipsShowerDlg(Context context, boolean cancelable, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示").setMessage(message).setCancelable(cancelable).setNegativeButton("确定", (DialogInterface.OnClickListener)null);
        AlertDialog dialog = builder.create();
        return dialog;
    }

    public static Dialog getChooseDlg(Context context, boolean cancelable, String title, String message, DialogInterface.OnClickListener negativeListener, DialogInterface.OnClickListener positiveListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(message).setCancelable(cancelable).setNegativeButton("确定", negativeListener).setPositiveButton("取消", positiveListener);
        AlertDialog dialog = builder.create();
        return dialog;
    }


    private static Context getContext() {
        return YouWardrobeApplication.getAppContext();
    }

    private static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 获取颜色值
     * @param resId 颜色资源id
     * @return 颜色值
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static int getColor(int resId, Resources.Theme theme) {
        return getResources().getColor(resId, theme);
    }

    public static int getColor(String color) {
        return Color.parseColor(color);
    }

    /**
     * 获取Drawable
     * @param resTd Drawable资源id
     * @return Drawable
     */
    public static Drawable getDrawable(int resTd) {
        return getResources().getDrawable(resTd);
    }

    /**
     * 获取字符串
     * @param resId 字符串资源id
     * @return 字符串
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 获取字符串数组
     * @param resId 数组资源id
     * @return 字符串数组
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 将dp值转换为px值
     * @param dp 需要转换的dp值
     * @return px值
     */
    public static int dp2px(float dp) {
        return (int) (getResources().getDisplayMetrics().density * dp + 0.5f);
//        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
    public static int dipToPix(Context context, int dip) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
        return size;
    }
    /**
     * 将px值转换为dp值
     * @param px 需要转换的px值
     * @return dp值
     */
    public static int px2dp(float px) {
        return (int) (px / getResources().getDisplayMetrics().density + 0.5f);
    }

    public static int sp2px(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    public static DisplayMetrics getDisplayMetrics() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        return displayMetrics;
    }

    /**
     * 获取屏幕宽度 像素值
     * @return 屏幕宽度
     */
    public static int getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度 像素值
     * @return 屏幕高度
     */
    public static int getScreenHegith() {
        return getDisplayMetrics().heightPixels;
    }
}