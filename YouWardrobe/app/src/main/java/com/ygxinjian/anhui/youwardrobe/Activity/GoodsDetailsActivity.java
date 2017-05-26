package com.ygxinjian.anhui.youwardrobe.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
        }
    }


    @OnClick({R.id.nav_go_back, R.id.nav_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_go_back:
                finish();
                break;
            case R.id.nav_right:
                break;
        }
    }
}
