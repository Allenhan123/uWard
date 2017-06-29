package com.ygxinjian.anhui.youwardrobe.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.ygxinjian.anhui.youwardrobe.Controller.ImageOptionUtils;
import com.ygxinjian.anhui.youwardrobe.Controller.sharepreference.LocalData;
import com.ygxinjian.anhui.youwardrobe.Model.BaseModel;
import com.ygxinjian.anhui.youwardrobe.Model.NetResultModel;
import com.ygxinjian.anhui.youwardrobe.Model.ReturnAddressNetModel;
import com.ygxinjian.anhui.youwardrobe.Model.UserModel;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.YouWardrobeApplication;
import com.ygxinjian.anhui.youwardrobe.api.Api;
import com.ygxinjian.anhui.youwardrobe.api.YouWardrobeApi;
import com.ygxinjian.anhui.youwardrobe.utils.DevUtil;
import com.ygxinjian.anhui.youwardrobe.utils.UiUtil;
import com.ygxinjian.anhui.youwardrobe.weigt.SelectPhotoDialog;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by ${Ua} on 2017/4/13.
 */

public class UserMessageActivity extends BaseActivity implements OnDateSetListener {
    private static final String TAG = UserMessageEditActivity.class.getSimpleName();
    public static final int REQUEST_CAMERA = 1;
    public static final int REQUEST_ALBUM = 2;
    public static final int REQUEST_CROP = 3;
    final int SEX_SELECT = 4;
    @InjectView(R.id.nav_tv_title)
    TextView navTvTitle;
    @InjectView(R.id.nav_right)
    TextView navRight;
    @InjectView(R.id.iv_user_photo)
    ImageView ivUserPhoto;
    @InjectView(R.id.tv_sex)
    TextView tvSex;


    public static final String IMAGE_UNSPECIFIED = "image/*";
    @InjectView(R.id.item_birthday)
    LinearLayout itemBirthday;
    @InjectView(R.id.item_figure)
    LinearLayout itemFigure;
    @InjectView(R.id.tv_bir)
    TextView tvBir;
    @InjectView(R.id.get_adress)
    LinearLayout getAdress;
    @InjectView(R.id.item_back_adress)
    LinearLayout itemBackAdress;
    private File mImageFile;
    TimePickerDialog mDialogYearMonthDay;
    long sixtyYears = 60L * 365 * 1000 * 60 * 60 * 24L;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_message;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        navTvTitle.setText(R.string.user_message_title);

        mDialogYearMonthDay = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setThemeColor(R.color.main_Red)
                .setMaxMillseconds(System.currentTimeMillis())
                .setMinMillseconds(System.currentTimeMillis() - sixtyYears)
                .setTitleStringId("请选择出生日期")
                .setCallBack(this)
                .build();
    }

    @Override
    protected void initData() {
        getUserInfo();
    }


    @OnClick({R.id.nav_go_back, R.id.item_photo
            , R.id.item_sex, R.id.get_adress, R.id.item_back_adress
            , R.id.item_birthday, R.id.item_figure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_go_back:
                finish();
                break;
            case R.id.nav_right:
                save();
                break;
            case R.id.item_photo: //选择头像
                final SelectPhotoDialog dialog = new SelectPhotoDialog(getContext());
                dialog.show();
                dialog.setOnItemClickListener(new SelectPhotoDialog.OnItemClickListener() {
                    @Override
                    public void OnItemClick(int position) {
                        if (position == 0) {
                            //// TODO: 2017/4/13 权限
                            selectCamera();
                        } else {
                            selectAlbum();
                        }
                        dialog.dismiss();
                    }
                });
                break;


            // 身材信息
            case R.id.item_figure:
                DevUtil.gotoActivity(getContext(), FigureActivity.class);
                break;
//            收货地址
            case R.id.get_adress:
                DevUtil.gotoActivity(getContext(), GetAdressActivity.class);
                break;
            // 寄回地址  弹窗显示固定地址
            case R.id.item_back_adress:
                getReturnAddressFromNet();
                break;
            case R.id.item_sex:
                if (sexSelectPop != null && sexSelectPop.isShowing()) {
                    sexSelectPop.dismiss();
                    sexSelectPop = null;
                } else {
                    selectSex(SEX_SELECT);
                }
                break;
            case R.id.item_birthday:
                mDialogYearMonthDay.show(getSupportFragmentManager(), "选择出生日期");
                break;

        }
    }

    //性别选择
    private PopupWindow sexSelectPop;

    private void selectSex(int type) {
        if (type == SEX_SELECT) {
            View customView = LayoutInflater.from(this).inflate(R.layout.layout_select_identitytype_popwindow, null);
            customView.findViewById(R.id.tv_inschool).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvSex.setText("帅哥");
                    sexSelectPop.dismiss();
                }
            });
            customView.findViewById(R.id.tv_graduate).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvSex.setText("美女");
                    sexSelectPop.dismiss();
                }
            });
            sexSelectPop = new PopupWindow(customView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
            customView.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (sexSelectPop != null && sexSelectPop.isShowing()) {
                        sexSelectPop.dismiss();
                        sexSelectPop = null;
                    }

                    return false;
                }
            });
        }
        sexSelectPop.update();
        sexSelectPop.setTouchable(true);
        sexSelectPop.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0xa0000000);
        sexSelectPop.setBackgroundDrawable(dw);
        sexSelectPop.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        sexSelectPop.setAnimationStyle(R.style.popupwindow_anim_style);
        sexSelectPop.showAtLocation(navTvTitle, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private void save() {

    }


    /**
     * 获取用户信息
     */
    private void getUserInfo() {
        UserModel userModel = BaseModel.getGson()
                .fromJson(YouWardrobeApplication.getLocalData().getString(LocalData.KEY_USE_INFO), UserModel.class);
        refreshUserView(userModel);
    }


    /**
     * 刷新用户信息界面
     *
     * @param model
     */
    private void refreshUserView(UserModel model) {
        Log.d(TAG, "initUserView: " + model.toString());

    }


    private void selectCamera() {
        createImageFile();
        if (!mImageFile.exists()) {
            return;
        }

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mImageFile));
        startActivityForResult(cameraIntent, REQUEST_CAMERA);
    }

    private void selectAlbum() {
        Intent albumIntent = new Intent(Intent.ACTION_PICK);
        albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
        startActivityForResult(albumIntent, REQUEST_ALBUM);
    }

    private void cropImage(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mImageFile));
        startActivityForResult(intent, REQUEST_CROP);
    }

    private void createImageFile() {
        mImageFile = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
        try {
            mImageFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "出错啦", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK != resultCode) {
            return;
        }
        switch (requestCode) {
            case REQUEST_CAMERA:
                cropImage(Uri.fromFile(mImageFile));
                break;

            case REQUEST_ALBUM:
                createImageFile();
                if (!mImageFile.exists()) {
                    return;
                }

                Uri uri = data.getData();
                if (uri != null) {
                    cropImage(uri);
                }
                break;

            case REQUEST_CROP:
                Uri ss = Uri.fromFile(mImageFile);
//                String path = parseFilePath(ss);
//                updatePhoto(mImageFile.getAbsolutePath());

                ImageLoader.getInstance().displayImage("file://" + mImageFile.getAbsolutePath(), ivUserPhoto, ImageOptionUtils.getUserCircleOptions());
                ivUserPhoto.setImageURI(ss);
                break;
        }
    }

    //显示生日
    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {

        String text = getDateToString(millseconds);
        tvBir.setText(text);
    }

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }


    /**
     * 获取寄回地址
     */
    public void getReturnAddressFromNet() {
        final Dialog dialog = UiUtil.getLoadDialog(getContext(), true);
        dialog.show();
        Api.getYouWardrobeApi()
                .returnddress()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReturnAddressNetModel>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss();
                    }

                    @Override
                    public void onNext(ReturnAddressNetModel model) {
                        if (model.getCode() == NetResultModel.RESULT_CODE_SUCCESS) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("寄回地址");
                            builder.setMessage(model.getAddress());
                            builder.setPositiveButton("确定", null);
                            builder.show();
                        }
                    }
                });
    }


}