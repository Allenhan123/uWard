package com.ygxinjian.anhui.youwardrobe.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

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


}