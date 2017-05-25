package com.ygxinjian.anhui.youwardrobe.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.ygxinjian.anhui.youwardrobe.Model.ClassifyModel;
import com.ygxinjian.anhui.youwardrobe.R;
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify);
        ButterKnife.inject(this);
        navRight.setVisibility(View.GONE);

        recyclerViewClassify.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        myAdapter = new MyAdapter(getContext(), list);
        recyclerViewClassify.setAdapter(myAdapter);

        initData();
    }

    private void initData() {
        //取得从上一个Activity当中传递过来的Intent对象
        Intent _intent = getIntent();
        //从Intent当中根据key取得value
        if (_intent != null) {
            String title = _intent.getStringExtra("title");
            url = _intent.getStringExtra("url");
            navTvTitle.setText(title);
            getData();
        }
    }

    private void getData() {
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
                        myAdapter.notifyDataSetChanged();
                    }
                });
    }

    @OnClick(R.id.nav_go_back)
    public void onClick() {
        finish();
    }


    public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private Context context;
        private List<ClassifyModel.ResultBean.DataBean> list;

        public MyAdapter(Context context, List<ClassifyModel.ResultBean.DataBean> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.activity_classify_item, parent, false);
            return new MyAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyAdapter.ViewHolder styleViewHolder = (MyAdapter.ViewHolder) holder;
            styleViewHolder.bindData(list.get(position));

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            @InjectView(R.id.iv_user_classify_des)
            ImageView ivUserClassifyDes;
            @InjectView(R.id.tv_user_classify_title)
            TextView tvUserClassifyTitle;
            @InjectView(R.id.tv_user_classify_size)
            TextView tvUserClassifySize;

            ViewHolder(View view) {
                super(view);
                ButterKnife.inject(this, view);
            }
            public void bindData(ClassifyModel.ResultBean.DataBean model){
                ImageLoader.getInstance().displayImage(model.getImgUrl(),ivUserClassifyDes);
                tvUserClassifyTitle.setText(model.getProdTitle());
                tvUserClassifySize.setText(model.getSize());

            }
        }

    }
}
