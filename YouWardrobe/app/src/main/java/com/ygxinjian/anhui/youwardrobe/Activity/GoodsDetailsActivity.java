package com.ygxinjian.anhui.youwardrobe.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.ygxinjian.anhui.youwardrobe.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

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
    @InjectView(R.id.banner_details)
    ConvenientBanner bannerDetails;
    @InjectView(R.id.details_des)
    TextView detailsDes;
    @InjectView(R.id.details_price)
    TextView detailsPrice;
    @InjectView(R.id.ll_select_size)
    LinearLayout llSelectSize;
    @InjectView(R.id.ll_kefu)
    LinearLayout llKefu;
    @InjectView(R.id.ll_like)
    LinearLayout llLike;
    @InjectView(R.id.btn_consult_tel)
    Button btnConsultTel;

    private String prod_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsdetails);
        ButterKnife.inject(this);
        navTvTitle.setText("商品详情");
        navRight.setImageResource(R.mipmap.my_wardrobe);
        getData();

    }

    private void getData() {
        Intent _intent = getIntent();
        if (_intent != null) {
            String title = _intent.getStringExtra("title");
            prod_id = _intent.getStringExtra("url");
            Log.e("URL", prod_id);

            SpannableString spannableString = new SpannableString("价格：299.0");
            StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
            spannableString.setSpan(strikethroughSpan, 3, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            detailsPrice.setText(spannableString);
        }
    }


    @OnClick({R.id.nav_go_back, R.id.nav_right, R.id.ll_select_size, R.id.ll_kefu, R.id.ll_like, R.id.btn_consult_tel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_go_back:
                finish();
                break;
            case R.id.nav_right:
                break;
            case R.id.ll_select_size:

                break;
            case R.id.ll_kefu:
                break;
            case R.id.ll_like:
                break;
            case R.id.btn_consult_tel:
                break;
        }
    }
}
