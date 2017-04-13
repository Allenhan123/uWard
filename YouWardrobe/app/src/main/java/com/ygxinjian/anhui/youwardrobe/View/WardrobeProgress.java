package com.ygxinjian.anhui.youwardrobe.View;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.ygxinjian.anhui.youwardrobe.R;

/**
 * Created by handongqiang on 17/3/13.
 */

public class WardrobeProgress {
    private ProgressHUDDialog mProgressHUDDialog;
    private Context mContext;

    public WardrobeProgress(Context mContext) {
        this.mContext = mContext;
        mProgressHUDDialog = new ProgressHUDDialog(mContext);
    }
    //展示
    public WardrobeProgress show() {
        if (!isShowing()) {
            mProgressHUDDialog.show();
        }
        return this;
    }

    public boolean isShowing() {
        return mProgressHUDDialog != null && mProgressHUDDialog.isShowing();
    }
    //隐藏
    public void dismiss() {
        if (mProgressHUDDialog != null && mProgressHUDDialog.isShowing()) {
            mProgressHUDDialog.dismiss();
        }
    }


    public class ProgressHUDDialog extends Dialog {
        private ImageView iv0,iv1;

        public ProgressHUDDialog(Context context) {
            super(context);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.wardrobe_progress);
            setCanceledOnTouchOutside(false);
            iv0= (ImageView) findViewById(R.id.iv_0);
            iv1= (ImageView) findViewById(R.id.iv_1);

            iv0.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.in_from_right));
            iv1.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.out_from_right));
        }
    }
}
