package com.ygxinjian.anhui.youwardrobe.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ygxinjian.anhui.youwardrobe.Controller.ImageOptionUtils;
import com.ygxinjian.anhui.youwardrobe.Controller.sharepreference.LocalData;
import com.ygxinjian.anhui.youwardrobe.Model.BaseModel;
import com.ygxinjian.anhui.youwardrobe.Model.UserModel;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.YouWardrobeApplication;
import com.ygxinjian.anhui.youwardrobe.weigt.SelectPhotoDialog;

import java.io.File;
import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * Created by ${Ua} on 2017/4/13.
 */

public class UserMessageActivity extends BaseActivity {
    private static final String TAG = UserMessageEditActivity.class.getSimpleName();
    public static final int REQUEST_CAMERA = 1;
    public static final int REQUEST_ALBUM = 2;
    public static final int REQUEST_CROP = 3;

    @InjectView(R.id.nav_tv_title)
    TextView navTvTitle;
    @InjectView(R.id.nav_right)
    ImageView navRight;
    @InjectView(R.id.iv_user_photo)
    ImageView ivUserPhoto;
    @InjectView(R.id.tv_phone)
    TextView tvPhone;
    @InjectView(R.id.tv_card_no)
    TextView tvCardNo;
    @InjectView(R.id.tv_sex)
    TextView tvSex;
    @InjectView(R.id.tv_birthday)
    TextView tvBirthday;


    public static final String IMAGE_UNSPECIFIED = "image/*";
    private File mImageFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_message);
        ButterKnife.inject(this);
        navTvTitle.setText(R.string.user_message_title);
        navRight.setVisibility(View.GONE);

        //// TODO: 2017/4/13  缺少获取用户信息的接口
        getUserInfo();
    }

    @OnClick({R.id.nav_go_back, R.id.item_photo
            , R.id.item_phone, R.id.item_card_type
            , R.id.item_card_no, R.id.item_sex
            , R.id.item_birthday, R.id.item_figure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_go_back:
                finish();
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
            case R.id.item_phone:
                Intent intent = new Intent(getContext(), UserMessageEditActivity.class);
                startActivity(intent);
                break;
            case R.id.item_card_type:
                break;
            case R.id.item_card_no:
                break;
            case R.id.item_sex:
                break;
            case R.id.item_birthday:
                break;
            case R.id.item_figure:
                break;
        }
    }


    /**
     * 获取用户信息
     */
    private void getUserInfo() {
        UserModel userModel = BaseModel.getGson()
                .fromJson(YouWardrobeApplication.getmLocalData().getString(LocalData.KEY_USE_INFO), UserModel.class);
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

                ImageLoader.getInstance().displayImage("file://"+mImageFile.getAbsolutePath(),ivUserPhoto, ImageOptionUtils.getUserCircleOptions());
                ivUserPhoto.setImageURI(ss);
                break;
        }
    }

}