package com.ygxinjian.anhui.youwardrobe;

/**
 * Created by handongqiang on 17/3/17.
 */

public class Constant {
    //    天气预报key
    public static String weatherKer = "2977e9ddc4004e89ab5ded5755bea77c";
//    public static String weatherKer = "a98bc7f5dc264b88a57b5caf1a0f9c9c";
    public static String weatherUrl = "http://api.avatardata.cn/Weather/Query?key=dbc2d673816a43569b12aa2201bb4b36";


    public static final String Base_Url = "http://115.159.116.34:8089/Interface/i_Interface.aspx";
    public static final String Base_Image_Url = "http://115.159.116.34:8089";


    /**
     * 3.首页轮播图
     * http://115.159.116.34:8089/Interface/i_Interface.aspx?m=carousel&uid=手机号码
     **/
    public static String bannerUrl = Base_Url + "?m=carousel";
    public static String homeUrl = Base_Url + "?m=home_classify";//首页

    //    设计师推荐
    public static String recommendDesignUrl = Base_Url + "?m=recommend_suit";
    //    单品推荐
    public static String recommendSingleUrl = Base_Url + "?m=recommend_single";

    //    时租区http://115.159.116.34:8089/Interface/i_Interface.aspx?m= when_classify&uid=手机号码
    public static String rentUrl = Base_Url + "?m= when_classify";
    /*  用户  */
    //登录   http://115.159.116.34:8089/Interface/i_Interface.aspx?m=login&uid=手机号&pwd=登录密码
    public static String login = Base_Url + "?m=login";

    //注册 http://115.159.116.34:8089/Interface/i_Interface.aspx?m=register&uid=手机号&pwd=登录密码
    public static String register = Base_Url + "?m=register";
//更多推荐
    public static String moreRecommend =  "?m=recommend_suitlist&uid=";

//    单品列表
    public static String singleRecommendLister =  "?m=recommend_singlelist&uid=";


}
