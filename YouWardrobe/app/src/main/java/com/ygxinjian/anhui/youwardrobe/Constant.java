package com.ygxinjian.anhui.youwardrobe;

/**
 * Created by handongqiang on 17/3/17.
 */

public class Constant {
    //    天气预报key
    public static String weatherKer = "a98bc7f5dc264b88a57b5caf1a0f9c9c";
    public static String weatherUrl = "http://api.avatardata.cn/Weather/Query?key=a98bc7f5dc264b88a57b5caf1a0f9c9c";


    public static final String Base_Url = "http://115.159.116.34:8089/Interface/i_Interface.aspx";


    /**
     * 3.首页轮播图
     * http://115.159.116.34:8089/Interface/i_Interface.aspx?m=carousel&uid=手机号码
     **/
    public static String bannerUrl = Base_Url + "?m=carousel";
    public static String homeUrl = Base_Url + "?m=home_classify";//首页
    //    单品推荐
    public static String recommendSingleUrl = Base_Url + "?m=recommend_single";

    /*  用户  */
    //登录   http://115.159.116.34:8089/Interface/i_Interface.aspx?m=login&uid=手机号&pwd=登录密码
    public static String login = Base_Url + "?m=login";

    //注册 http://115.159.116.34:8089/Interface/i_Interface.aspx?m=register&uid=手机号&pwd=登录密码
    public static String register = Base_Url + "?m=register";

}
