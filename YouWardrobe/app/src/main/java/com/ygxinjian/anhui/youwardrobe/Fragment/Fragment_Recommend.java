package com.ygxinjian.anhui.youwardrobe.Fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.ygxinjian.anhui.youwardrobe.Constant;
import com.ygxinjian.anhui.youwardrobe.Model.RecommendSingleModel;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.utils.DevUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import me.yuqirong.cardswipelayout.CardConfig;
import me.yuqirong.cardswipelayout.CardItemTouchHelperCallback;
import me.yuqirong.cardswipelayout.CardLayoutManager;
import me.yuqirong.cardswipelayout.OnSwipeListener;
import okhttp3.Call;

/**
 * Created by handongqiang on 17/3/13.
 */

public class Fragment_Recommend extends BaseFragment {
    private ImageView iv_back;
    private TextView tv_title;
    private List<Integer> list = new ArrayList<>();
    private RecyclerView recyclerView,recycleView_single;
    private RecommendSingleModel recommend_single_model;
    private RecommendSingleAdapter mAdapter;
    private List<RecommendSingleModel.ResultBean.DataBean> data;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_recommend, null);
        iv_back = (ImageView) view.findViewById(R.id.nav_go_back);
        iv_back.setVisibility(View.GONE);
        tv_title = (TextView) view.findViewById(R.id.nav_tv_title);
        tv_title.setText("精心推荐");
//        推荐图片
        initRecycData();
        initRecycView(view);

//        单品推荐
        initRecycSingleData();
        recycleView_single = (RecyclerView) view.findViewById(R.id.recyclerView_single);

        return view;
    }

//    推荐
    private void initRecycView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViews);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new MyAdapter());
        CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(recyclerView.getAdapter(), list);
        cardCallback.setOnSwipedListener(new OnSwipeListener<Integer>() {

            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                MyAdapter.MyViewHolder myHolder = (MyAdapter.MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1 - Math.abs(ratio) * 0.2f);
                if (direction == CardConfig.SWIPING_LEFT) {
                    myHolder.dislikeImageView.setAlpha(Math.abs(ratio));
                } else if (direction == CardConfig.SWIPING_RIGHT) {
                    myHolder.likeImageView.setAlpha(Math.abs(ratio));
                } else {
                    myHolder.dislikeImageView.setAlpha(0f);
                    myHolder.likeImageView.setAlpha(0f);
                }
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, Integer o, int direction) {
                MyAdapter.MyViewHolder myHolder = (MyAdapter.MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1f);
                myHolder.dislikeImageView.setAlpha(0f);
                myHolder.likeImageView.setAlpha(0f);
                Toast.makeText(mActivity, direction == CardConfig.SWIPED_LEFT ? "加入衣柜" : "不喜欢", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipedClear() {
                Toast.makeText(mActivity, "data clear", Toast.LENGTH_SHORT).show();
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }
                }, 3000L);
            }

        });
        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper);
        recyclerView.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(recyclerView);
    }
    private void initRecycData() {
        list.add(R.drawable.img_avatar_01);
        list.add(R.drawable.img_avatar_02);
        list.add(R.drawable.img_avatar_03);
        list.add(R.drawable.img_avatar_04);
        list.add(R.drawable.img_avatar_05);
        list.add(R.drawable.img_avatar_06);
    }
    private class MyAdapter extends RecyclerView.Adapter {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ImageView avatarImageView = ((MyViewHolder) holder).avatarImageView;
            avatarImageView.setImageResource(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView avatarImageView;
            ImageView likeImageView;
            ImageView dislikeImageView;

            MyViewHolder(View itemView) {
                super(itemView);
                avatarImageView = (ImageView) itemView.findViewById(R.id.iv_avatar);
                likeImageView = (ImageView) itemView.findViewById(R.id.iv_like);
                dislikeImageView = (ImageView) itemView.findViewById(R.id.iv_dislike);
            }

        }
    }

    private void initRecycSingleData() {
        OkHttpUtils.get().url(Constant.recommendSingleUrl).addParams("uid", "18656009327").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }
            @Override
            public void onResponse(String response, int id) {
                Log.d("Result",response);
                Gson gson = new Gson();
                recommend_single_model = gson.fromJson(response,RecommendSingleModel.class);
                data = recommend_single_model.getResult().getData();
                mAdapter = new RecommendSingleAdapter(mActivity);
                mAdapter.openLoadAnimation();
//                View headView = LayoutInflater.from(mActivity).inflate(R.layout.recommend_head_layout,null);
//                View headView = View.inflate(mActivity,R.layout.recommend_head_layout,null);
//
////                initRecycData();
////                initRecycView(headView);
//                mAdapter.addHeaderView(headView);
                initAdapter();
            }
        });
    }
    /**
     * 设置RecyclerView属性
     */
    private void initAdapter() {
        recycleView_single.setLayoutManager(new GridLayoutManager(mActivity, 2));
        mAdapter.openLoadAnimation();
        recycleView_single.setAdapter(mAdapter);//设置adapter
        //设置item点击事件
        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                DevUtil.showInfo(mActivity,"单品"+position);
                Intent intent = new Intent();
//                intent.setClass(mActivity, CustomWebViewActivity.class);
//                intent.putExtra("url",Constants.NewsMainURL+newsModel.getResult().getData().get(position).getWz());
//                intent.putExtra("title",newsModel.getResult().getData().get(position).getTitle());
//                startActivity(intent);
            }
        });
    }

    class RecommendSingleAdapter extends BaseQuickAdapter<RecommendSingleModel.ResultBean.DataBean> {

        public RecommendSingleAdapter(Context context) {
            super(context,R.layout.recommenf_single_item,data);
        }

        @Override
        public void convert(BaseViewHolder helper, RecommendSingleModel.ResultBean.DataBean mData) {
            helper.setText(R.id.tv_recommend_item, mData.getClassifyTitle());
            ImageLoader.getInstance().displayImage(mData.getImgUrl(), (ImageView) helper.getView(R.id.iv_recommend_item));
        }
    }

}
