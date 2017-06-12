package com.ygxinjian.anhui.youwardrobe.api;

import android.util.Log;

import com.lidroid.xutils.http.client.multipart.content.FileBody;
import com.ygxinjian.anhui.youwardrobe.Controller.HttpLog;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Olivine on 2017/5/11.
 */

public class Api {
    public static final String BASE_URL = "http://115.159.116.34:8089/Interface/i_Interface.aspx/";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;

    public static YouWardrobeApi getYouWardrobeApi() {
        return getInstance().youWardrobeApi;
    }

    private YouWardrobeApi youWardrobeApi;

    //构造方法私有
    private Api() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        // Log信息拦截器
        Interceptor loggingIntercept = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                ResponseBody responseBody = response.body();
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                Buffer buffer = source.buffer();
                Charset UTF8 = Charset.forName("UTF-8");
                Log.e("REQUEST_JSON", buffer.clone().readString(UTF8));
                Log.e("REQUEST_URL", request.toString());
                return response;
            }
        };

        //日志
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLog());
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClientBuilder.addInterceptor(loggingIntercept);
        httpClientBuilder.addInterceptor(loggingInterceptor);

        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(ScalarsConverterFactory.create()) //增加返回值为String的支持
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        youWardrobeApi = retrofit.create(YouWardrobeApi.class);
    }

    //在访问Api时创建单例
    private static class SingletonHolder {
        private static final Api INSTANCE = new Api();
    }
    //获取单例
    public static Api getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
