package com.ygxinjian.anhui.youwardrobe.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.ygxinjian.anhui.youwardrobe.Activity.ClassifyActivity;
import com.ygxinjian.anhui.youwardrobe.Constant;
import com.ygxinjian.anhui.youwardrobe.Controller.sharepreference.LocalData;
import com.ygxinjian.anhui.youwardrobe.Model.RecommendDesignModel;
import com.ygxinjian.anhui.youwardrobe.Model.RecommendSingleModel;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.View.BetterRecyclerView;
import com.ygxinjian.anhui.youwardrobe.View.SwipCardView.SwipeFlingAdapterView;
import com.ygxinjian.anhui.youwardrobe.YouWardrobeApplication;
import com.ygxinjian.anhui.youwardrobe.utils.DevUtil;
import com.ygxinjian.anhui.youwardrobe.utils.UiUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import okhttp3.Call;

/**
 * Created by handongqiang on 17/3/13.
 */

public class RecommendFragment extends BaseFragmentNormal
        implements SwipeFlingAdapterView.onFlingListener,
        SwipeFlingAdapterView.OnItemClickListener
{
    private static final String TAG = "RecommendFragment";
    private BetterRecyclerView recyclerViewSingle;
    private ImageView ivBack;
    private TextView tvTitle;

    private RecommendSingleModel recommend_single_model;
    private RecommendSingleAdapter mAdapter;
    private List<RecommendSingleModel.ResultBean.DataBean> data;

    //    设计师推荐
    private View headView;
    private SwipeFlingAdapterView recommendView;
    private SwipeCardAdapter swipeCardAdapter;
    private RecommendDesignModel recommendDesignModel;
    private List<RecommendDesignModel.ResultBean.DataBean> list = new ArrayList<>();

//将推荐模块作为头布局加到recycleview上
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_recommend, null);
        ivBack = (ImageView) view.findViewById(R.id.nav_go_back);
        tvTitle = (TextView) view.findViewById(R.id.nav_tv_title);
        tvTitle.setText("精心推荐");
        ivBack.setVisibility(View.GONE);
        recyclerViewSingle = (BetterRecyclerView) view.findViewById(R.id.recyclerView_single);
        recyclerViewSingle.setLayoutManager(new GridLayoutManager(mActivity, 2));
//
        initRecycSingleData();
        return view;
    }

    //    单品推荐
    private void initRecycSingleData() {
        OkHttpUtils.get().url(Constant.recommendSingleUrl).addParams("uid", "18656009327").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("Result", response);
                Gson gson = new Gson();
                recommend_single_model = gson.fromJson(response, RecommendSingleModel.class);
                data = recommend_single_model.getResult().getData();
                mAdapter = new RecommendSingleAdapter(R.layout.recommenf_single_item, data);
                initAdapter();
            }
        });
    }

    /**
     * 设置RecyclerView属性      单品推荐
     */
    private void initAdapter() {
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);

        initHeadView();
        mAdapter.addHeaderView(headView);

        recyclerViewSingle.setAdapter(mAdapter);//设置adapter
        //设置item点击事件
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent _intent = new Intent(mActivity,ClassifyActivity.class);
                _intent.putExtra("title",data.get(position).getClassifyTitle());
//                _intent.putExtra("url",data.get(position).getUrl());
                startActivity(_intent);
            }
        });
    }

    private void initHeadView() {
        headView = mActivity.getLayoutInflater().inflate(R.layout.recommend_header, (ViewGroup) recyclerViewSingle.getParent(), false);
        recommendView = (SwipeFlingAdapterView) headView.findViewById(R.id.recommend_view);
        initRecycData();
        //        设计师推荐
        if (recommendView != null) {
            recommendView.setIsNeedSwipe(true);
            recommendView.setFlingListener( this);
            recommendView.setOnItemClickListener(this);

            swipeCardAdapter = new SwipeCardAdapter();
            recommendView.setAdapter(swipeCardAdapter);
        }
    }
    @Override
    public void onItemClicked(MotionEvent event, View v, Object dataObject) {
//        Toast.makeText(mActivity, dataObject.toString(), Toast.LENGTH_SHORT).show();
    }
    @Override
    public void removeFirstObjectInAdapter() {
        swipeCardAdapter.remove(0);
    }
    //左滑
    @Override
    public void onLeftCardExit(Object dataObject) {

    }

    //右滑
    @Override
    public void onRightCardExit(Object dataObject) {
    }

    @Override
    public void onAdapterAboutToEmpty(int itemsInAdapter) {
        if (itemsInAdapter == 3) {
            initRecycData();
        }
    }

    @Override
    public void onScroll(float progress, float scrollXProgress) {
        View view = recommendView.getSelectedView();
        view.findViewById(R.id.iv_like).setAlpha(scrollXProgress < 0 ? -scrollXProgress : 0);
        view.findViewById(R.id.iv_dislike).setAlpha(scrollXProgress > 0 ? scrollXProgress : 0);
    }

    private void initRecycData() {
        final Dialog dialog = UiUtil.getLoadDialog(mActivity, true);
        dialog.show();
        OkHttpUtils.get().url(Constant.recommendDesignUrl).addParams("uid", YouWardrobeApplication.getLocalData().getString(LocalData.KEY_USE_ID)).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                dialog.dismiss();
                Log.e("DesignResult", response);
                Gson gson = new Gson();
                recommendDesignModel = gson.fromJson(response, RecommendDesignModel.class);
                list = recommendDesignModel.getResult().getData();
                swipeCardAdapter.addAll(list);
            }
        });
    }


    public class SwipeCardAdapter extends BaseAdapter {
        ArrayList<RecommendDesignModel.ResultBean.DataBean> objs;

        public SwipeCardAdapter() {
            objs = new ArrayList<>();
        }

        public void addAll(Collection<RecommendDesignModel.ResultBean.DataBean> collection) {
            if (isEmpty()) {
                objs.addAll(collection);
                notifyDataSetChanged();
            } else {
                objs.addAll(collection);
            }
        }

        public void clear() {
            objs.clear();
            notifyDataSetChanged();
        }

        public boolean isEmpty() {
            return objs.isEmpty();
        }

        public void remove(int index) {
            if (index > -1 && index < objs.size()) {
                objs.remove(index);
                notifyDataSetChanged();
            }
        }

        @Override
        public int getCount() {
            return objs.size();
        }

        @Override
        public RecommendDesignModel.ResultBean.DataBean getItem(int position) {
            if (objs == null || objs.size() == 0) return null;
            return objs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            RecommendDesignModel.ResultBean.DataBean data = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(mActivity).inflate(R.layout.recommend_item, parent, false);
                holder = new ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.tv_recommend_name);
                holder.description = (TextView) convertView.findViewById(R.id.tv_recommend_size);
                holder.iv_recommend = (ImageView) convertView.findViewById(R.id.iv_recommend_design);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.name.setText(data.getProdTitle());
            holder.description.setText(data.getSize());
            ImageLoader.getInstance().displayImage(Constant.Base_Image_Url + data.getImgUrl(), holder.iv_recommend);
            return convertView;
        }

    }

    class ViewHolder {
        TextView name;
        TextView description;
        TextView createTime;
        ImageView iv_recommend;
    }

    class RecommendSingleAdapter extends BaseQuickAdapter<RecommendSingleModel.ResultBean.DataBean, BaseViewHolder> {
        public RecommendSingleAdapter(int layoutResId, List data) {
            super(R.layout.recommenf_single_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, RecommendSingleModel.ResultBean.DataBean item) {
            helper.setText(R.id.tv_recommend_item, item.getClassifyTitle());
            ImageLoader.getInstance().displayImage(Constant.Base_Image_Url + item.getImgUrl(), (ImageView) helper.getView(R.id.iv_recommend_item));

        }
    }

}
