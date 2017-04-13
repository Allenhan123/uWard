package com.ygxinjian.anhui.youwardrobe.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ${Ua} on 2017/3/24.
 */

public class TextUtil {

    public static boolean isIdentification(String string) {
        String regex = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(string);
        return m.matches();
    }


    public static boolean isNull(CharSequence cs) {
        if (TextUtils.isEmpty(cs)) {
            return true;
        }
        if (cs == "null") {
            return true;
        }
        return TextUtils.isEmpty(cs.toString().trim());
    }

    /**
     * 以1开头 的11位数字
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone) {
//        String regex = "^((13[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";
//        Pattern p = Pattern.compile(regex);
//        Matcher m = p.matcher(phone);

        String regex = "^(1)\\d{10}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        return m.matches();
    }


    public static String hidePhoneMid(String phone) {
        return phone.substring(0, 3) + "****" + phone.substring(7, phone.length());
    }


    //获取String中的数字

    public static String getStringNum(String str) {

        str = str.trim();
        String str2 = "";
        if (str != null && !"".equals(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                    str2 += str.charAt(i);
                }
            }
        }
        return str2;
    }


    public static String getStringType(String str) {

        str = str.trim();
        String str2 = "";
        if (str != null && !"".equals(str)) {
            for (int i = 0; i < str.length(); i++) {

                if ((str.charAt(i)) == '月') {
                    return "月";
                }
                if ((str.charAt(i)) == '天') {
                    return "天";
                }
                if ((str.charAt(i)) == '年') {
                    return "年";
                }

            }
        }
        return str2;
    }
}
