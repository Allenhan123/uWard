package com.ygxinjian.anhui.youwardrobe.Activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ygxinjian.anhui.youwardrobe.Constant;
import com.ygxinjian.anhui.youwardrobe.Controller.sharepreference.LocalData;
import com.ygxinjian.anhui.youwardrobe.Model.DressHistoryNetModel;
import com.ygxinjian.anhui.youwardrobe.Model.NetResultModel;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.YouWardrobeApplication;
import com.ygxinjian.anhui.youwardrobe.api.Api;
import com.ygxinjian.anhui.youwardrobe.utils.DevUtil;
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
 * Created by handongqiang on 17/5/10.
 */

public class DressHistoryActivity extends BaseActivity {

    private static final String TAG = DressHistoryActivity.class.getSimpleName();
//    item 布局为activity_dress_history_item

    @InjectView(R.id.nav_go_back)
    ImageView navGoBack;
    @InjectView(R.id.nav_tv_title)
    TextView navTvTitle;
    @InjectView(R.id.nav_right)
    TextView navRight;
    @InjectView(R.id.recycle_view)
    RecyclerView recycleView;

    private List<DressHistoryNetModel.ResultBean.DataBean> list = new ArrayList<>();
    private MyAdapter myAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_dress_history;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        navTvTitle.setText(R.string.user_dress_history_title);
        navRight.setText(R.string.clear_dress_history_title);

        recycleView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        myAdapter = new MyAdapter(getContext(), list);
        recycleView.setAdapter(myAdapter);
    }

    @Override
    protected void initData() {
        getDataFromService();

    }

    @OnClick({R.id.nav_go_back, R.id.nav_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_go_back:
                finish();
                break;
            case R.id.nav_right:
                clearHistory();
                break;
        }
    }


    public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private Context context;
        private List<DressHistoryNetModel.ResultBean.DataBean> list;

        public MyAdapter(Context context, List<DressHistoryNetModel.ResultBean.DataBean> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.activity_dress_history_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder myHolder= (MyViewHolder) holder;
            myHolder.bindData(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyViewHolder  extends RecyclerView.ViewHolder{
            @InjectView(R.id.iv_photo)
            ImageView ivPhoto;
            @InjectView(R.id.tv_dress_history_title)
            TextView tvDressHistoryTitle;
            @InjectView(R.id.tv_dress_history_size)
            TextView tvDressHistorySize;
            @InjectView(R.id.tv_dress_history_time)
            TextView tvDressHistoryTime;
            @InjectView(R.id.tv_dress_history_star)
            TextView tvDressHistoryStar;
            @InjectView(R.id.cardView_takephotos)
            CardView cardViewTakephotos;

            MyViewHolder(View view) {
                super(view);
                ButterKnife.inject(this, view);
            }

            public void bindData(final DressHistoryNetModel.ResultBean.DataBean model){
                ImageLoader.getInstance().displayImage(Constant.Base_Image_Url+model.getImgUrl(),ivPhoto);
                tvDressHistoryTitle.setText(model.getProdTitle());
                tvDressHistorySize.setText(model.getSize());
                tvDressHistoryTime.setText(model.getTransTime().substring(0,10));
                tvDressHistoryStar.setText(model.getElated());
                cardViewTakephotos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DevUtil.gotoActivity(context,ProductPingActivity.class);
                    }
                });
            }
        }
    }


    private void clearHistory() {

    }

    private void getDataFromService() {
        final Dialog dialog = UiUtil.getLoadDialog(getContext(), true);
        dialog.show();
        Api.getYouWardrobeApi().dressingRecord(YouWardrobeApplication.getLocalData().getString(LocalData.KEY_USE_ID))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DressHistoryNetModel>() {
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
                    public void onNext(DressHistoryNetModel netModel) {
                        if (netModel.getCode() == NetResultModel.RESULT_CODE_SUCCESS) {
                            list.clear();
                            //// TODO: 2017/5/17   去掉下一行代码 测试用
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
