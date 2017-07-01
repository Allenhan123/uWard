package com.ygxinjian.anhui.youwardrobe.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.ygxinjian.anhui.youwardrobe.Controller.sharepreference.LocalData;
import com.ygxinjian.anhui.youwardrobe.Model.GoodsDetailModel;
import com.ygxinjian.anhui.youwardrobe.Model.NetResultModel;
import com.ygxinjian.anhui.youwardrobe.Model.PhotoInfo;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.View.ExpandTextView;
import com.ygxinjian.anhui.youwardrobe.View.MultiImageView;
import com.ygxinjian.anhui.youwardrobe.View.WheelView;
import com.ygxinjian.anhui.youwardrobe.YouWardrobeApplication;
import com.ygxinjian.anhui.youwardrobe.api.Api;
import com.ygxinjian.anhui.youwardrobe.utils.DevUtil;
import com.ygxinjian.anhui.youwardrobe.utils.UiUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by handongqiang on 17/5/26.
 */

public class GoodsDetailsActivity extends BaseActivity {
    @InjectView(R.id.nav_go_back)
    ImageView navGoBack;
    @InjectView(R.id.nav_tv_title)
    TextView navTvTitle;
    @InjectView(R.id.nav_right)
    ImageView navRight;
    @InjectView(R.id.details_des)
    TextView detailsDes;
    @InjectView(R.id.details_price)
    TextView detailsPrice;
    @InjectView(R.id.ll_select_size)
    LinearLayout llSelectSize;
    @InjectView(R.id.tv_sel_size)
    TextView tvSelSize;
    @InjectView(R.id.ll_kefu)
    LinearLayout llKefu;
    @InjectView(R.id.ll_like)
    LinearLayout llLike;
    @InjectView(R.id.btn_consult_tel)
    Button btnConsultTel;
    @InjectView(R.id.tv_num_ping)
    TextView tvNumPing;
    @InjectView(R.id.tv_all_ping)
    TextView tvAllPing;
    @InjectView(R.id.headIv)
    ImageView headIv;
    @InjectView(R.id.nameTv)
    TextView nameTv;
    @InjectView(R.id.contentTv)
    ExpandTextView contentTv;
    @InjectView(R.id.multiImagView)
    MultiImageView multiImagView;
    @InjectView(R.id.timeTv)
    TextView timeTv;

    private String prod_id;
    private GoodsDetailModel.ResultBean data;
    private static final String TAG = "GoodsDetailsActivity";
    private Context context;
    private static final String[] PLANETS = new String[]{"S", "M", "L", "XL", "XXL", "XXXL"};
    private LinearLayout guideGroup;
    ViewPager viewPager;
    private ArrayList<String> imgUrls;
    private List<View> guideViewList = new ArrayList<View>();
    private int startPos;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goodsdetails;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        context = this;
        navTvTitle.setText("商品详情");
        navRight.setImageResource(R.mipmap.my_wardrobe);

        viewPager = (ViewPager) findViewById(R.id.pager);
        guideGroup = (LinearLayout) findViewById(R.id.guideGroup);


        SpannableString spannableString = new SpannableString("价格：299.0");
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        spannableString.setSpan(strikethroughSpan, 3, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        detailsPrice.setText(spannableString);

//        第一条评价信息
        contentTv.setText("好着哩");
        timeTv.setText("2017/07/01");

    }

    @Override
    protected void initData() {
        Intent _intent = getIntent();
        if (_intent != null) {
            String title = _intent.getStringExtra("title");
            prod_id = _intent.getStringExtra("url");
        }
        getGoodsData();
    }

    private void getGoodsData() {
        final Dialog dialog = UiUtil.getLoadDialog(getContext(), true);
        dialog.show();
        Api.getYouWardrobeApi().goodsDetail(prod_id, YouWardrobeApplication.getLocalData().getString(LocalData.KEY_USE_ID))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GoodsDetailModel>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss();
                    }

                    @Override
                    public void onNext(GoodsDetailModel netModel) {
                        if (netModel.getCode() == NetResultModel.RESULT_CODE_SUCCESS) {
                            data = netModel.getResult();
                            detailsDes.setText(data.getProdTitle());
                            imgUrls = new ArrayList<String>();
                            for (int i = 0; i < data.getImgUrls().size(); i++) {
                                String imUrl = data.getImgUrls().get(i).getImgUrl();
                                imgUrls.add(imUrl);
                            }
                            if (imgUrls != null && imgUrls.size() > 0) {
                                initAdapter();
                            }
                        }
                    }
                });
    }

    private void initAdapter() {
        ImageAdapter mAdapter = new ImageAdapter(context);
        mAdapter.setDatas(imgUrls);
        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < guideViewList.size(); i++) {
                    guideViewList.get(i).setSelected(i == position ? true : false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(startPos);
        addGuideView(guideGroup, startPos, imgUrls);
    }

    private void addGuideView(LinearLayout guideGroup, int startPos, ArrayList<String> imgUrls) {
        if (imgUrls != null && imgUrls.size() > 0) {
            guideViewList.clear();
            for (int i = 0; i < imgUrls.size(); i++) {
                View view = new View(this);
                view.setBackgroundResource(R.drawable.selector_guide_bg);
                view.setSelected(i == startPos ? true : false);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.gudieview_width),
                        getResources().getDimensionPixelSize(R.dimen.gudieview_heigh));
                layoutParams.setMargins(10, 0, 0, 0);
                guideGroup.addView(view, layoutParams);
                guideViewList.add(view);
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            return super.dispatchTouchEvent(ev);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
//全部评价
    @OnClick(R.id.tv_all_ping)
    public void onClick() {
        DevUtil.gotoActivity(context,AllPingActivity.class);

    }

    private static class ImageAdapter extends PagerAdapter {
        private List<String> datas = new ArrayList<String>();
        private LayoutInflater inflater;
        private Context context;

        public void setDatas(List<String> datas) {
            if (datas != null)
                this.datas = datas;
        }

        public ImageAdapter(Context context) {
            this.context = context;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            if (datas == null) return 0;
            return datas.size();
        }

        @Override
        public Object instantiateItem(final ViewGroup container, final int position) {
            View view = inflater.inflate(R.layout.item_pager_image, container, false);
            if (view != null) {
                final ImageView imageView = (ImageView) view.findViewById(R.id.vp_image);
//                点击看大图
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ImagePagerActivity.ImageSize imageSize = new ImagePagerActivity.ImageSize(v.getMeasuredWidth(), v.getMeasuredHeight());
                        ImagePagerActivity.startImagePagerActivity((context), datas, position, imageSize);

                    }
                });

//                //loading
                final ProgressBar loading = new ProgressBar(context);
                FrameLayout.LayoutParams loadingLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                loadingLayoutParams.gravity = Gravity.CENTER;
                loading.setLayoutParams(loadingLayoutParams);
                ((FrameLayout) view).addView(loading);

                final String imgurl = datas.get(position);

                Glide.with(context)
                        .load(imgurl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存多个尺寸
                        .thumbnail(0.1f)//先显示缩略图  缩略图为原图的1/10
                        .error(R.mipmap.banner_loading)
                        .into(new GlideDrawableImageViewTarget(imageView) {
                            @Override
                            public void onLoadStarted(Drawable placeholder) {
                                super.onLoadStarted(placeholder);
                                loading.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                                super.onLoadFailed(e, errorDrawable);
                                loading.setVisibility(View.GONE);
                            }

                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                                super.onResourceReady(resource, animation);
                                loading.setVisibility(View.GONE);
                            }
                        });

                container.addView(view, 0);
            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }


    }

    @OnClick({R.id.nav_go_back, R.id.nav_right, R.id.ll_select_size, R.id.ll_kefu, R.id.ll_like, R.id.btn_consult_tel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_go_back:
                finish();
                break;
//            切换到购物车
            case R.id.nav_right:
                DevUtil.gotoActivity(context, WardrobeActivity.class);
                break;
            case R.id.ll_select_size:
                openDialog("尺码-码", Arrays.asList(PLANETS), tvSelSize);
                break;
            case R.id.ll_kefu:

                break;
            case R.id.ll_like:

                break;
//            加入购物车
            case R.id.btn_consult_tel:
                addCar();
                break;
        }
    }

    private void addCar() {
        HashMap map = new HashMap<String, String>();
        map.put("uid", YouWardrobeApplication.getLocalData().getString(LocalData.KEY_USE_ID));
        map.put("prod_id", data.getProdID());
        map.put("tradetype", "0");
        map.put("quantity", "1");

        Api.getYouWardrobeApi()
                .addCar(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<NetResultModel>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(NetResultModel netResultModel) {
                        if (netResultModel.getCode() == NetResultModel.RESULT_CODE_SUCCESS) {
                            DevUtil.showShortInfo(context, "已加入衣柜");
                        }
                    }
                });
    }

    private void openDialog(String title, List<String> items, final TextView tvItem) {
        View outerView = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
        WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
        wv.setItems(items);
        wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                tvItem.setText(item + " 码");
            }
        });
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setView(outerView)
                .setPositiveButton("确定", null)
                .show();
    }
}
