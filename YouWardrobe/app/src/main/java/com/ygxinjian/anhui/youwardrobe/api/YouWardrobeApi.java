package com.ygxinjian.anhui.youwardrobe.api;

import com.ygxinjian.anhui.youwardrobe.Model.LogInNetModel;
import com.ygxinjian.anhui.youwardrobe.Model.SaveUserInfoNetModel;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Olivine on 2017/5/11.
 */

public interface YouWardrobeApi {
    //登录
    @FormUrlEncoded
    @POST("?m=login")
    Observable<LogInNetModel> login(@Field("uid") String uid, @Field("pwd") String pwd);

    //保存用户个人信息(修改)将身材信息拆分到11条实现
    @POST("?m= perfect_info")
    Observable<SaveUserInfoNetModel> saveUserInfo(@Body SaveUserInfoNetModel.RequestModel model);

}
