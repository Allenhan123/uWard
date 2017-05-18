package com.ygxinjian.anhui.youwardrobe.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ygxinjian.anhui.youwardrobe.Controller.sharepreference.LocalData;
import com.ygxinjian.anhui.youwardrobe.Model.NetResultModel;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.View.WheelView;
import com.ygxinjian.anhui.youwardrobe.YouWardrobeApplication;
import com.ygxinjian.anhui.youwardrobe.api.Api;
import com.ygxinjian.anhui.youwardrobe.utils.DevUtil;
import com.ygxinjian.anhui.youwardrobe.utils.TextUtil;
import com.ygxinjian.anhui.youwardrobe.utils.UiUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
    private List<String> list=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_figure);
        ButterKnife.inject(this);
        navTvTitle.setText("身材信息");
        for (int i = 10; i < 200; i++) {
            list.add(String.valueOf(i));
        }
    }


    @OnClick({R.id.nav_go_back, R.id.nav_right, R.id.item_figure, R.id.item_short, R.id.item_trousers, R.id.item_chest, R.id.item_waist, R.id.item_buttocks, R.id.item_height, R.id.item_weight, R.id.item_shoulder})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_go_back:
                finish();
                break;
            case R.id.nav_right:
                save();
                break;
            case R.id.item_figure:
                openDialog("尺码", Arrays.asList(PLANETS), tvFigureSize);
                break;
            case R.id.item_short:
                openDialog("上身", list, tvShortSize);
                break;
            case R.id.item_trousers:
                openDialog("下身", list, tvTrousersSize);
                break;
            case R.id.item_chest:
                openDialog("胸围", list, tvChest);
                break;
            case R.id.item_waist:
                openDialog("腰围", list, tvWaist);
                break;
            case R.id.item_buttocks:
                openDialog("臀围", list, tvButtocks);
                break;
            case R.id.item_height:
                openDialog("身高", list, tvHeight);
                break;
            case R.id.item_weight:
                openDialog("体重", list, tvWeight);
                break;
            case R.id.item_shoulder:
                openDialog("肩宽", list, tvShoulder);
                break;
        }
    }

    private void openDialog(String title, List<String> items, final TextView tvItem) {
        View outerView = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
        WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
        wv.setItems(items);
        wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                tvItem.setText(item);
            }
        });
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setView(outerView)
                .setPositiveButton("确定", null)
                .show();
    }


    private void save() {
        String size = tvFigureSize.getText().toString();
        String TopSize = tvShortSize.getText().toString();
        String DownSize = tvTrousersSize.getText().toString();
        String Height = tvHeight.getText().toString();
        String Weight = tvWeight.getText().toString();
        String Bust = tvChest.getText().toString();
        String Waistline = tvWaist.getText().toString();
        String Hipline = tvButtocks.getText().toString();
        String ShoulderWidth = tvShoulder.getText().toString();
        if (TextUtil.isNull(size) || TextUtil.isNull(TopSize) || TextUtil.isNull(DownSize)
                || TextUtil.isNull(Height) || TextUtil.isNull(Weight) || TextUtil.isNull(Bust)
                || TextUtil.isNull(Waistline) || TextUtil.isNull(Hipline) || TextUtil.isNull(ShoulderWidth)) {
            DevUtil.showInfo(getContext(), "请完善您的信息");
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("Size", size);
        map.put("TopSize", TopSize);
        map.put("DownSize", DownSize);
        map.put("Height", Height);
        map.put("Weight", Weight);
        map.put("Bust", Bust);
        map.put("Waistline", Waistline);
        map.put("Hipline", Hipline);
        map.put("ShoulderWidth", ShoulderWidth);
        final Dialog dialog = UiUtil.getLoadDialog(getContext(), true);
        dialog.show();
        Api.getYouWardrobeApi().perfectFigure(YouWardrobeApplication.getLocalData().getString(LocalData.KEY_USE_ID)
                , map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NetResultModel>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss();
                    }

                    @Override
                    public void onNext(NetResultModel netResultModel) {
                        if (netResultModel.getCode() == NetResultModel.RESULT_CODE_SUCCESS) {
                            finish();
                        } else {
                            DevUtil.showInfo(getContext(), "保存失败");
                        }
                    }
                });
    }

}
