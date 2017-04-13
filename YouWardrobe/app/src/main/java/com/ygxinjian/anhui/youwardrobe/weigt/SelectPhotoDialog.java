package com.ygxinjian.anhui.youwardrobe.weigt;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.ygxinjian.anhui.youwardrobe.R;


/**
 * Created by ${Ua} on 2017/3/8.
 */

public class SelectPhotoDialog extends Dialog implements View.OnClickListener {

    private Context mContext;
    private TextView item0;
    private TextView item1;


    public SelectPhotoDialog(Context context) {
        super(context);
        this.mContext = context;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }


    private void initView() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_select_photo, null);
        item0 = (TextView) rootView.findViewById(R.id.item_0);
        item1 = (TextView) rootView.findViewById(R.id.item_1);
        rootView.findViewById(R.id.cancel).setOnClickListener(this);
        setContentView(rootView);

        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setWindowAnimations(R.style.dialogWindowAnim);
        this.setCanceledOnTouchOutside(true);


        item0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.OnItemClick(0);
                }
            }
        });
        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.OnItemClick(1);
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                dismiss();
                break;
        }
    }


    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

}
