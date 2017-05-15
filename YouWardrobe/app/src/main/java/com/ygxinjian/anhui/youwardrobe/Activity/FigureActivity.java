package com.ygxinjian.anhui.youwardrobe.Activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.View.WheelView;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by handongqiang on 17/5/11.
 */
//身材信息
public class FigureActivity extends BaseActivity {
    @InjectView(R.id.nav_go_back)
    ImageView navGoBack;
    @InjectView(R.id.nav_tv_title)
    TextView navTvTitle;
    @InjectView(R.id.nav_right)
    TextView navRight;
    @InjectView(R.id.tv_figure_size)
    TextView tvFigureSize;
    @InjectView(R.id.item_figure)
    LinearLayout itemFigure;
    @InjectView(R.id.tv_short_size)
    TextView tvShortSize;
    @InjectView(R.id.item_short)
    LinearLayout itemShort;
    @InjectView(R.id.tv_trousers_size)
    TextView tvTrousersSize;
    @InjectView(R.id.item_trousers)
    LinearLayout itemTrousers;
    @InjectView(R.id.tv_chest)
    TextView tvChest;
    @InjectView(R.id.item_chest)
    LinearLayout itemChest;
    @InjectView(R.id.tv_waist)
    TextView tvWaist;
    @InjectView(R.id.item_waist)
    LinearLayout itemWaist;
    @InjectView(R.id.tv_buttocks)
    TextView tvButtocks;
    @InjectView(R.id.item_buttocks)
    LinearLayout itemButtocks;
    @InjectView(R.id.tv_height)
    TextView tvHeight;
    @InjectView(R.id.item_height)
    LinearLayout itemHeight;
    @InjectView(R.id.tv_weight)
    TextView tvWeight;
    @InjectView(R.id.item_weight)
    LinearLayout itemWeight;
    @InjectView(R.id.tv_shoulder)
    TextView tvShoulder;
    @InjectView(R.id.item_shoulder)
    LinearLayout itemShoulder;

    private static final String TAG = "FigureActivity";
    private static final String[] PLANETS = new String[]{"S", "M", "L", "XL", "XXL", "XXXL"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_figure);
        ButterKnife.inject(this);
        navTvTitle.setText("身材信息");

    }

    private void save() {

    }

    @OnClick({R.id.nav_go_back, R.id.nav_right,R.id.item_figure, R.id.item_short, R.id.item_trousers, R.id.item_chest, R.id.item_waist, R.id.item_buttocks, R.id.item_height, R.id.item_weight, R.id.item_shoulder})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_go_back:
                finish();
                break;
            case R.id.nav_right:
                save();
                break;
            case R.id.item_figure:
                View outerView = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
                WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
                wv.setItems(Arrays.asList(PLANETS));
                wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                    @Override
                    public void onSelected(int selectedIndex, String item) {
                        Log.d(TAG, "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
                        tvFigureSize.setText(item);
                    }
                });
                new AlertDialog.Builder(this)
                        .setTitle("身材信息")
                        .setView(outerView)
                        .setPositiveButton("确定", null)
                        .show();

                break;
            case R.id.item_short:

                break;
            case R.id.item_trousers:

                break;
            case R.id.item_chest:

                break;
            case R.id.item_waist:

                break;
            case R.id.item_buttocks:
                break;
            case R.id.item_height:
                break;
            case R.id.item_weight:
                break;
            case R.id.item_shoulder:
                break;
        }
    }
}
