package com.ygxinjian.anhui.youwardrobe.weigt;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.ygxinjian.anhui.youwardrobe.R;

/**
 * Created by ${Ua} on 2017/4/13.
 */

public class ProgressHUDDialog extends Dialog {
    private Context mContext;
    private ImageView iv0, iv1;

    public ProgressHUDDialog(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.wardrobe_progress);
        setCanceledOnTouchOutside(false);
        iv0 = (ImageView) findViewById(R.id.iv_0);
        iv1 = (ImageView) findViewById(R.id.iv_1);

        iv0.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.in_from_right));
        iv1.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.out_from_right));
    }
}
