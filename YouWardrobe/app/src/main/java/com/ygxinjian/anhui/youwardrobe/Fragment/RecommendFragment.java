package com.ygxinjian.anhui.youwardrobe.Fragment;

import android.app.Dialog;
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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.ygxinjian.anhui.youwardrobe.Activity.DressHistoryActivity;
import com.ygxinjian.anhui.youwardrobe.Constant;
import com.ygxinjian.anhui.youwardrobe.Controller.SwipeCardAdapter;
import com.ygxinjian.anhui.youwardrobe.Controller.sharepreference.LocalData;
import com.ygxinjian.anhui.youwardrobe.Model.DressHistoryNetModel;
import com.ygxinjian.anhui.youwardrobe.Model.NetResultModel;
import com.ygxinjian.anhui.youwardrobe.Model.RecommendDesignModel;
import com.ygxinjian.anhui.youwardrobe.Model.RecommendSingleModel;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.View.MyGridLayoutManager;
import com.ygxinjian.anhui.youwardrobe.View.SwipCardView.SwipeFlingAdapterView;
import com.ygxinjian.anhui.youwardrobe.YouWardrobeApplication;
import com.ygxinjian.anhui.youwardrobe.api.Api;
import com.ygxinjian.anhui.youwardrobe.utils.DevUtil;
import com.ygxinjian.anhui.youwardrobe.utils.UiUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import me.yuqirong.cardswipelayout.CardConfig;
import me.yuqirong.cardswipelayout.CardItemTouchHelperCallback;
import me.yuqirong.cardswipelayout.CardLayoutManager;
import me.yuqirong.cardswipelayout.OnSwipeListener;
import okhttp3.Call;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by handongqiang on 17/3/13.
 */

public class RecommendFragment extends BaseFragment {
    private static final String TAG = "RecommendFragment";
    private ImageView iv_back;
    private TextView tv_title;
//    private List<Integer> list = new ArrayList<>();
    private RecyclerView recyclerView,recycleView_single;
    private RecommendSingleModel recommend_single_model;
    private RecommendSingleAdapter mAdapter;
    private List<RecommendSingleModel.ResultBean.DataBean> data;

//    设计师推荐
    private SwipeFlingAdapterView swipeFlingAdapterView;
    private ArrayList<String> al;
    private ArrayAdapter<String> arrayAdapter;
    private RecommendDesignModel recommendDesignModel;
    private List<RecommendDesignModel.ResultBean.DataBean> list = new ArrayList<>();
    private SwipeCardAdapter swipeCardAdapter;
    private int i;
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_recommend, null);
        iv_back = (ImageView) view.findViewById(R.id.nav_go_back);
        iv_back.setVisibility(View.GONE);
        tv_title = (TextView) view.findViewById(R.id.nav_tv_title);
        swipeFlingAdapterView = (SwipeFlingAdapterView) view.findViewById(R.id.recommend_view);
        tv_title.setText("精心推荐");


//        initRecycView(view);


//        al = new ArrayList<>();
//        al.add("php");
//        al.add("c");
//        al.add("python");
//        al.add("java");
//        al.add("html");
//        al.add("c++");
//        al.add("css");
//        arrayAdapter = new ArrayAdapter<String>(mActivity, R.layout.recommend_item, R.id.tv_recommend_name, al );
//        swipeFlingAdapterView.setAdapter(arrayAdapter);
        //        设计师推荐
        initRecycData();
        swipeFlingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                al.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                makeToast(mActivity, "Left!"+dataObject.toString());
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                makeToast(mActivity, "Right!"+dataObject.toString());
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                al.add("XML ".concat(String.valueOf(i)));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = swipeFlingAdapterView.getSelectedView();
                view.findViewById(R.id.iv_like).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.iv_dislike).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });


        // Optionally add an OnItemClickListener
        swipeFlingAdapterView.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                makeToast(mActivity, "Clicked!"+dataObject.toString());
            }
        });

//        单品推荐
        initRecycSingleData();
        recycleView_single = (RecyclerView) view.findViewById(R.id.recyclerView_single);

        return view;
    }
    static void makeToast(Context ctx, String s){
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }

    private void initRecycData() {
        final Dialog dialog = UiUtil.getLoadDialog(getContext(), true);
        dialog.show();
        OkHttpUtils.get().url(Constant.recommendDesignUrl).addParams("uid", "18656009327").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }
            @Override
            public void onResponse(String response, int id) {
                dialog.dismiss();
                Log.e("DesignResult",response);
                Gson gson = new Gson();
                recommendDesignModel = gson.fromJson(response,RecommendDesignModel.class);
                list = recommendDesignModel.getResult().getData();
                swipeCardAdapter = new SwipeCardAdapter();
                swipeFlingAdapterView.setAdapter(swipeCardAdapter);
            }
        });
    }


    public class SwipeCardAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return recommendDesignModel.getResult().getData().size();
        }
        @Override
        public Object getItem(int position) {
            return recommendDesignModel.getResult().getData().get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(mActivity, R.layout.recommend_item, null);
                holder.description = (TextView) convertView.findViewById(R.id.tv_recommend_size);
                holder.iv_recommend = (ImageView) convertView.findViewById(R.id.iv_recommend_design);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.description.setText("dsdf");
//            holder.tv_zyzz.setText(consulterMessage.result.data.get(position).zyzz);
//            holder.tv_remark.setText(consulterMessage.result.data.get(position).remark);
//            holder.tv_hps.setText(consulterMessage.result.data.get(position).hps + "人好评");
//            imageLoader.displayImage(Constant.URL + consulterMessage.result.data.get(position).imgpath, holder.iv_consulter, options);
            return convertView;
        }

    }
    class ViewHolder {
        TextView name;
        TextView description;
        TextView createTime;
        ImageView iv_recommend;
    }

//        @Override
//        public int getCount()
//        {
//            return list.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return(list.get(position));
//
//        }
//
//        @Override
//        public long getItemId(int position)
//        {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent)
//        {
//            ViewHolder viewHolder = null;
//            if (null == convertView)
//            {
//                viewHolder = new ViewHolder();
//                convertView = LayoutInflater.from(mActivity).inflate(R.layout.recommend_item, null);
//
//                viewHolder.name = (TextView) convertView.findViewById(R.id.tv_recommend_name);
//                viewHolder.description = (TextView) convertView.findViewById(R.id.tv_recommend_size);
//                viewHolder.iv_recommend = (ImageView) convertView.findViewById(R.id.iv_recommend_design);
////            viewHolder.createTime = (TextView) convertView
////                    .findViewById(R.id.createTime);
//
//                convertView.setTag(viewHolder);
//            }
//            else
//            {
//                viewHolder = (ViewHolder) convertView.getTag();
//            }
//
//            // set item values to the viewHolder:
//
//
//            viewHolder.name.setText("sdfsdfsd");
//            viewHolder.description.setText(list.get(position).getSize());
////            ImageLoader.getInstance().displayImage(Constant.Base_Image_Url+markerItems.get(position).getImgUrl(),viewHolder.iv_recommend);
////            viewHolder.createTime.setText(markerItem.getCreateDate());
//
//            return convertView;
//        }
//
//        public class ViewHolder
//        {
//            TextView name;
//            TextView description;
//            TextView createTime;
//            ImageView iv_recommend;
//        }


//    推荐
//    private void initRecycView(View view) {
//        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViews);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(new MyAdapter());
//        CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(recyclerView.getAdapter(), list);
//        cardCallback.setOnSwipedListener(new OnSwipeListener<Integer>() {
//
//            @Override
//            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
//                MyAdapter.MyViewHolder myHolder = (MyAdapter.MyViewHolder) viewHolder;
//                viewHolder.itemView.setAlpha(1 - Math.abs(ratio) * 0.2f);
//                if (direction == CardConfig.SWIPING_LEFT) {
//                    myHolder.dislikeImageView.setAlpha(Math.abs(ratio));
//                } else if (direction == CardConfig.SWIPING_RIGHT) {
//                    myHolder.likeImageView.setAlpha(Math.abs(ratio));
//                } else {
//                    myHolder.dislikeImageView.setAlpha(0f);
//                    myHolder.likeImageView.setAlpha(0f);
//                }
//            }
//
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, Integer o, int direction) {
//                MyAdapter.MyViewHolder myHolder = (MyAdapter.MyViewHolder) viewHolder;
//                viewHolder.itemView.setAlpha(1f);
//                myHolder.dislikeImageView.setAlpha(0f);
//                myHolder.likeImageView.setAlpha(0f);
//                Toast.makeText(mActivity, direction == CardConfig.SWIPED_LEFT ? "加入衣柜" : "不喜欢", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onSwipedClear() {
//                Toast.makeText(mActivity, "data clear", Toast.LENGTH_SHORT).show();
//                recyclerView.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        initData();
//                        recyclerView.getAdapter().notifyDataSetChanged();
//                    }
//                }, 3000L);
//            }
//
//        });
//        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
//        final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper);
//        recyclerView.setLayoutManager(cardLayoutManager);
//        touchHelper.attachToRecyclerView(recyclerView);
//    }

//    private class MyAdapter extends RecyclerView.Adapter {
//        @Override
//        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_item, parent, false);
//            return new MyViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//            ImageView avatarImageView = ((MyViewHolder) holder).avatarImageView;
//            avatarImageView.setImageResource(list.get(position));
//        }
//
//        @Override
//        public int getItemCount() {
//            return list.size();
//        }
//
//        class MyViewHolder extends RecyclerView.ViewHolder {
//
//            ImageView avatarImageView;
//            ImageView likeImageView;
//            ImageView dislikeImageView;
//
//            MyViewHolder(View itemView) {
//                super(itemView);
//                avatarImageView = (ImageView) itemView.findViewById(R.id.iv_avatar);
//                likeImageView = (ImageView) itemView.findViewById(R.id.iv_like);
//                dislikeImageView = (ImageView) itemView.findViewById(R.id.iv_dislike);
//            }
//
//        }
//    }


//    单品推荐
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
                initAdapter();
            }
        });
    }
    /**
     * 设置RecyclerView属性      单品推荐

     */
    private void initAdapter() {
        MyGridLayoutManager gridLayoutManager = new MyGridLayoutManager(getContext(),2);
        gridLayoutManager.setScrollEnabled(false);
        recycleView_single.setLayoutManager(gridLayoutManager);
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
//    单品推荐
    class RecommendSingleAdapter extends BaseQuickAdapter<RecommendSingleModel.ResultBean.DataBean> {

        public RecommendSingleAdapter(Context context) {
            super(context,R.layout.recommenf_single_item,data);
        }

        @Override
        public void convert(BaseViewHolder helper, RecommendSingleModel.ResultBean.DataBean mData) {
            helper.setText(R.id.tv_recommend_item, mData.getClassifyTitle());
            ImageLoader.getInstance().displayImage(Constant.Base_Image_Url+mData.getImgUrl(), (ImageView) helper.getView(R.id.iv_recommend_item));
        }
    }

}
