package com.ygxinjian.anhui.youwardrobe.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.ygxinjian.anhui.youwardrobe.Model.ClassifyModel;
import com.ygxinjian.anhui.youwardrobe.Model.NetResultModel;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.api.Api;
import com.ygxinjian.anhui.youwardrobe.utils.UiUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        myAdapter = new MyAdapter(R.layout.activity_classify_item, list);
        initAdapter();
    }
    @Override
    protected void initData() {
        getData();
    }

    private void getData() {
        if(url!=null){
            final Dialog dialog = UiUtil.getLoadDialog(getContext(), true);
            dialog.show();
            Api.getYouWardrobeApi().classifyDatav3(url)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ClassifyModel>() {
                        @Override
                        public void onCompleted() {
                            dialog.dismiss();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG, "onError: " + e.toString());
                            dialog.dismiss();
                        }

                        @Override
                        public void onNext(ClassifyModel netModel) {
                            if (netModel.getCode() == NetResultModel.RESULT_CODE_SUCCESS) {
                                list.clear();
                                //// TODO: 2017/5/17   去掉下一行代码 测试用
                                list.addAll(netModel.getResult().getData());
                                list.addAll(netModel.getResult().getData());
                                list.addAll(netModel.getResult().getData());
                                if(list.size()==0){
                                    showEmptyView();
                                    myAdapter.notifyDataSetChanged();
                                }else if(list.size()>0){
                                    showContentView();
                                    myAdapter.notifyDataSetChanged();

                                }
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
