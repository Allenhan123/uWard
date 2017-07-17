package com.ygxinjian.anhui.youwardrobe.api;

import com.ygxinjian.anhui.youwardrobe.Model.AddressBodyModel;
import com.ygxinjian.anhui.youwardrobe.Model.ClassifyModel;
import com.ygxinjian.anhui.youwardrobe.Model.DressHistoryNetModel;
import com.ygxinjian.anhui.youwardrobe.Model.GoodsDetailModel;
import com.ygxinjian.anhui.youwardrobe.Model.LogInNetModel;
import com.ygxinjian.anhui.youwardrobe.Model.LoginBody;
import com.ygxinjian.anhui.youwardrobe.Model.NetResultModel;
import com.ygxinjian.anhui.youwardrobe.Model.RecommendDesignModel;
import com.ygxinjian.anhui.youwardrobe.Model.RecommendSingleModel;
import com.ygxinjian.anhui.youwardrobe.Model.ReturnAddressNetModel;
import com.ygxinjian.anhui.youwardrobe.Model.SaveUserInfoNetModel;
import com.ygxinjian.anhui.youwardrobe.Model.StyleSetNetModel;
import com.ygxinjian.anhui.youwardrobe.Model.WardrobeModel;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Olivine on 2017/5/11.
 */

public interface YouWardrobeApi {

    //登录  18656009327  123456
    @FormUrlEncoded
    @POST("?m=login")
    Observable<LogInNetModel> login(@Field("uid") String uid, @Field("pwd") String pwd);

    //保存用户个人信息(修改)将身材信息拆分到11条实现
    @POST("?m= perfect_info")
    Observable<SaveUserInfoNetModel> saveUserInfo(@Body SaveUserInfoNetModel.RequestModel model);

    //分类数据（首页板块）   *@Url：使用全路径复写baseUrl，适用于非统一baseUrl的场景。*
    @GET
    Observable<ClassifyModel>  classifyDatav3(@Url String url);

    //单品推荐（推荐板块）
    @GET("?m=recommend_single")
    Observable<RecommendSingleModel> recommendSingle(@Query("uid") String uid);

    //（设计师推荐）
    @GET("?m=recommend_suit")
    Observable<RecommendDesignModel> recommendDesign(@Query("uid") String uid);

    //（更多推荐）
    @GET("?m=recommend_suitlist")
    Observable<RecommendDesignModel> recommendMore(@Query("uid") String uid);

    //（单品推荐列表）
    @GET("?m=recommend_singlelist")
    Observable<RecommendDesignModel> recommendSingleList(@Query("uid") String uid,@Query("typeid") String type_id);

    //商品详情（）
//    http://115.159.116.34:8089/ Interface/i_Interface.aspx?m= prod_details&prod_id=商品ID&uid=手机号
    @GET("?m=prod_details")
    Observable<GoodsDetailModel> goodsDetail(@Query("prod_id") String pid, @Query("uid") String uid);

    //获取购物车（衣柜板块）
    @GET("?m=getcart")
    Observable<WardrobeModel> wardrobeCar(@Query("uid") String uid);

    // 添加至购物车
    @POST("?m=addcart")
    Observable<NetResultModel> addCar(@QueryMap HashMap<String,String> paramsMap);

    // 移除购物车
    @POST("?m=removecart")
    Observable<NetResultModel> deleteCar(@QueryMap HashMap<String,String> paramsMap);

    //穿衣记录（个人板块）
    @GET("?m=dressing_record")
    Observable<DressHistoryNetModel> dressingRecord(@Query("uid") String uid);


    //风格设置（个人板块）
    @GET("?m=style_settings")
    Observable<StyleSetNetModel> styleSettings(@Query("uid") String uid);

    //（身材信息）
    @GET("?m=perfect_figure")
    Observable<NetResultModel> perfectFigure(@Query("uid") String uid, @QueryMap Map<String, String> map);


    /**
     * （个人板块）
     */
    // 寄回地址
    @GET("?m=return_address")
    Observable<ReturnAddressNetModel> returnddress();

    // 获取用户地址
    @FormUrlEncoded
    @POST("?m=getaddress")
    Observable<ReturnAddressNetModel> getAddress(@Field("uid") String uid);

//    // 用户收货地址管理
//    @POST("?m=member_address")
//    //Body小知识 http://www.jianshu.com/p/56b5bce47d8c
//    Observable<NetResultModel> managerAddress(@Body AddressBodyModel model);

    // 用户收货地址管理
    @POST("?m=member_address")
    Observable<NetResultModel> managerAddress(@QueryMap HashMap<String,String> paramsMap);


    //获取购物车信息
    @POST("?m=getcart")
    Observable<NetResultModel> getcart(@Body RequestBody model);



   //获取购物车信息
    @GET("?m=getcart")
    Observable<NetResultModel> getcart(@Query("uid") String uid);





}
