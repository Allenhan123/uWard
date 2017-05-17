package com.ygxinjian.anhui.youwardrobe.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ygxinjian.anhui.youwardrobe.Controller.sharepreference.LocalData;
import com.ygxinjian.anhui.youwardrobe.Model.NetResultModel;
import com.ygxinjian.anhui.youwardrobe.Model.StyleSetNetModel;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.YouWardrobeApplication;
import com.ygxinjian.anhui.youwardrobe.api.Api;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by handongqiang on 17/5/10.
 */

public class UserStyleActivity extends BaseActivity {
//    item布局为acivity_user_style_item

    @InjectView(R.id.nav_go_back)
    ImageView navGoBack;
    @InjectView(R.id.nav_tv_title)
    TextView navTvTitle;
    @InjectView(R.id.nav_right)
    TextView navRight;
    @InjectView(R.id.recycle_view)
    RecyclerView recycle_view;

    private List<StyleSetNetModel.ResultBean.DataBean> list = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_style);
        ButterKnife.inject(this);
        navTvTitle.setText(R.string.user_user_style_title);


        recycle_view.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        myAdapter = new MyAdapter(getContext(), list);
        recycle_view.setAdapter(myAdapter);

        getStyleSetFormService();
    }

    @OnClick({R.id.nav_go_back, R.id.nav_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_go_back:
                finish();
                break;
            case R.id.nav_right:
                save();
                break;
        }
    }


    public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private Context context;
        private List<StyleSetNetModel.ResultBean.DataBean> list;

        public MyAdapter(Context context, List<StyleSetNetModel.ResultBean.DataBean> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.activity_style_item, parent, false);
            return new StyleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            StyleViewHolder styleViewHolder= (StyleViewHolder) holder;
            styleViewHolder.bindData(list.get(position));

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class StyleViewHolder extends RecyclerView.ViewHolder {
            @InjectView(R.id.iv_user_style_des)
            ImageView ivUserStyleDes;
            @InjectView(R.id.tv_user_style_title)
            TextView tvUserStyleTitle;

            StyleViewHolder(View view) {
                super(view);
                ButterKnife.inject(this, view);
            }

            public void bindData(StyleSetNetModel.ResultBean.DataBean model){
                ImageLoader.getInstance().displayImage(model.getCoverImg(),ivUserStyleDes);
                tvUserStyleTitle.setText(model.getClassifyTitle());

            }
        }
    }

    private void save() {

    }


    private void getStyleSetFormService() {

        Api.getYouWardrobeApi().styleSettings(YouWardrobeApplication.getLocalData().getString(LocalData.KEY_USE_ID))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StyleSetNetModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(StyleSetNetModel netModel) {
                        if (netModel.getCode() == NetResultModel.RESULT_CODE_SUCCESS) {
                            list.clear();
                            list.addAll(netModel.getResult().getData());
                            myAdapter.notifyDataSetChanged();
                        }
                    }
                });

    }

}
