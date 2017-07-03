package com.ygxinjian.anhui.youwardrobe.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.ygxinjian.anhui.youwardrobe.Controller.GlideImageLoader;
import com.ygxinjian.anhui.youwardrobe.Controller.ImagePickerAdapter;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.utils.DevUtil;
import com.ygxinjian.anhui.youwardrobe.utils.TextUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by handongqiang on 17/7/2.
 */

public class ProductPingActivity extends BaseActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener{
    @InjectView(R.id.nav_go_back)
    ImageView navGoBack;
    @InjectView(R.id.nav_tv_title)
    TextView navTvTitle;
    @InjectView(R.id.nav_right)
    TextView navRight;
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
    LinearLayout cardViewTakephotos;
    @InjectView(R.id.m_ratingBar_shop)
    RatingBar mRatingBarShop;
    @InjectView(R.id.m_assess_edt)
    EditText mAssessEdt;

    private Context context;
    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 6;
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_ping;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        navRight.setText("提交");
        navTvTitle.setText("写评价");
        context = this;
        //最好放到 Application oncreate执行
        initImagePicker();
        initWidget();
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    private void initWidget() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                //打开选择,本次允许选择的数量
                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SELECT);
                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                selImageList.addAll(images);
                adapter.setImages(selImageList);
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                selImageList.clear();
                selImageList.addAll(images);
                adapter.setImages(selImageList);
            }
        }
    }
    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @OnClick({R.id.nav_go_back, R.id.nav_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_go_back:
                finish();
                break;
            case R.id.nav_right:
                if(TextUtils.isEmpty(mAssessEdt.getText())){
                    DevUtil.showShortInfo(context,"请写下您的评价");
                }else {
                    commit();
                }
                break;
        }
    }

    private void commit() {
//        selImageList为提交的图片数组  mRatingBarShop.getRating()为选中的星星数
        if (selImageList != null && selImageList.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < selImageList.size(); i++) {
                if (i == selImageList.size() - 1) sb.append("图片").append(i + 1).append(" ： ").append(selImageList.get(i).path);
                else sb.append("图片").append(i + 1).append(" ： ").append(selImageList.get(i).path).append("\n");
            }
//            图片路径
        Log.e("IMAGE",sb.toString());
        }
        ArrayList<File> files = new ArrayList<>();
        if (selImageList != null && selImageList.size() > 0) {
            for (int i = 0; i < selImageList.size(); i++) {
                files.add(new File(selImageList.get(i).path));
            }
        }
        Map<String, String> params = new HashMap<>();
        //        params.put("username", "杨光福");
        //        params.put("password", "123");

        String url = "http://192.168.10.168:8080/FileUpload/FileUploadServlet";
        OkHttpUtils.post()//
//                .addFile("mFile", "01.jpg", file)//
//                .addFile("mFile", "afua.txt", file2)//
                .url(url)
                .params(params)//
//                .addFileParams("file", files)
                .build();
//                .execute(new MyStringCallback());

    }
}
