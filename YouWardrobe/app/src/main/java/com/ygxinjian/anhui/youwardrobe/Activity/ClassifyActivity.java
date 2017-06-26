package com.ygxinjian.anhui.youwardrobe.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.ygxinjian.anhui.youwardrobe.Constant;
import com.ygxinjian.anhui.youwardrobe.Fragment.RecommendFragment;
import com.ygxinjian.anhui.youwardrobe.Model.ClassifyModel;
import com.ygxinjian.anhui.youwardrobe.Model.RecommendSingleModel;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.utils.DevUtil;
import com.ygxinjian.anhui.youwardrobe.utils.UiUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 商品分类
 * Created by handongqiang on 17/5/25.
 */

public class ClassifyActivity extends BaseActivity {
    private static final String TAG = "ClassifyActivity";
    @InjectView(R.id.nav_go_back)
    ImageView navGoBack;
    @InjectView(R.id.nav_tv_title)
    TextView navTvTitle;
    @InjectView(R.id.nav_right)
    TextView navRight;
    @InjectView(R.id.recyclerView_classify)
    RecyclerView recyclerViewClassify;

    private String url;
    private ClassifyModel classifyModel;
    private List<ClassifyModel.ResultBean.DataBean> list = new ArrayList<>();
    private MyAdapter myAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_classify;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        navRight.setVisibility(View.GONE);

        Intent _intent = getIntent();
        if (_intent != null) {
            String title = _intent.getStringExtra("title");
            url = _intent.getStringExtra("url");
            navTvTitle.setText(title);
        }
    }
    @Override
    protected void initData() {
        getData();
    }

    private void getData() {
        if(url!=null){
            final Dialog dialog = UiUtil.getLoadDialog(getContext(), true);
            dialog.show();
            OkHttpUtils.get().url(url)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            dialog.dismiss();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            dialog.dismiss();
                            Gson gson = new Gson();
                            classifyModel = gson.fromJson(response, ClassifyModel.class);
                            list.clear();
                            list.addAll(classifyModel.getResult().getData());
                            list.addAll(classifyModel.getResult().getData());
                            list.addAll(classifyModel.getResult().getData());
                            if(list.size()==0){
                                showEmptyView();
                                myAdapter.notifyDataSetChanged();
                            }else if(list.size()>0){
                                showContentView();
                                myAdapter = new MyAdapter(R.layout.activity_classify_item, list);
                                initAdapter();
                            }
                        }
                    });

        }

    }
    private void initAdapter() {
//        recyclerViewClassify.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        recyclerViewClassify.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        myAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        recyclerViewClassify.setAdapter(myAdapter);//设置adapter
        //设置item点击事件
        myAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent _intent = new Intent(ClassifyActivity.this,GoodsDetailsActivity.class);
                    _intent.putExtra("title",list.get(position).getProdTitle());
                    _intent.putExtra("url",list.get(position).getUrl());
                    startActivity(_intent);
            }
        });
    }
    class MyAdapter extends BaseQuickAdapter<ClassifyModel.ResultBean.DataBean, BaseViewHolder> {
        public MyAdapter(int layoutResId, List data) {
            super(R.layout.activity_classify_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper,ClassifyModel.ResultBean.DataBean item) {
            helper.setText(R.id.tv_user_classify_title, item.getProdTitle());
            helper.setText(R.id.tv_user_classify_size, item.getSize());
            ImageLoader.getInstance().displayImage(item.getImgUrl(), (ImageView) helper.getView(R.id.iv_user_classify_des));

        }
    }

    @OnClick(R.id.nav_go_back)
    public void onClick() {
        finish();
    }

}
