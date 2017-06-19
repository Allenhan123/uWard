package com.ygxinjian.anhui.youwardrobe.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ygxinjian.anhui.youwardrobe.Model.LoginBody;
import com.ygxinjian.anhui.youwardrobe.Model.NetResultModel;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.api.Api;
import com.ygxinjian.anhui.youwardrobe.utils.DevUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by handongqiang on 17/5/11.
 */

public class GetAdressActivity extends BaseActivity {


//    recycleView item 布局为 include_item_adress


    @InjectView(R.id.nav_go_back)
    ImageView navGoBack;
    @InjectView(R.id.nav_tv_title)
    TextView navTvTitle;
    @InjectView(R.id.nav_right)
    TextView navRight;
    @InjectView(R.id.recycleView_adress)
    RecyclerView recycleViewAdress;
    @InjectView(R.id.fab1)
    FloatingActionButton fab1;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_get_adress;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        navTvTitle.setText("收货地址");
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.nav_go_back, R.id.nav_right, R.id.fab1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_go_back:
                finish();
                break;
            case R.id.nav_right:
//                save();
                break;
            case R.id.fab1:
                DevUtil.gotoActivity(getContext(), AddAdressActivity.class);
                break;
        }
    }


    public class MyAddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private Context context;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.included_item_address, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
        }

        @Override
        public int getItemCount() {
            return 0;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            @InjectView(R.id.user_name)
            TextView userName;
            @InjectView(R.id.user_receiver_phone)
            TextView userReceiverPhone;
            @InjectView(R.id.user_adress)
            TextView userAdress;
            @InjectView(R.id.iv_is_default)
            ImageView ivIsDefault;
            @InjectView(R.id.user_address_isdefaul)
            TextView userAddressIsdefaul;
            @InjectView(R.id.ll_fix_first_address)
            LinearLayout llFixFirstAddress;
            @InjectView(R.id.user_edit)
            TextView userEdit;
            @InjectView(R.id.user_delete)
            TextView userDelete;

            MyViewHolder(View view) {
                super(view);
                ButterKnife.inject(this, view);
            }
        }
    }



    private void getAddressFromNet(){



    }


    private void save() {

    }


}
