package com.ygxinjian.anhui.youwardrobe.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ygxinjian.anhui.youwardrobe.Controller.ShopcartExpandableListViewAdapter;
import com.ygxinjian.anhui.youwardrobe.Controller.sharepreference.LocalData;
import com.ygxinjian.anhui.youwardrobe.Model.NetResultModel;
import com.ygxinjian.anhui.youwardrobe.Model.WardrobeModel;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.YouWardrobeApplication;
import com.ygxinjian.anhui.youwardrobe.api.Api;
import com.ygxinjian.anhui.youwardrobe.utils.DevUtil;
import com.ygxinjian.anhui.youwardrobe.utils.UiUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by handongqiang on 17/3/13.
 */

public class WardrobeFragment extends BaseFragment implements ShopcartExpandableListViewAdapter.CheckInterface, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    @InjectView(R.id.nav_go_back)
    ImageView navGoBack;
    @InjectView(R.id.nav_tv_title)
    TextView navTvTitle;
    @InjectView(R.id.nav_right)
    ImageView navRight;
    @InjectView(R.id.exListView)
    ExpandableListView exListView;
    @InjectView(R.id.all_chekbox)
    CheckBox allChekbox;
    @InjectView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @InjectView(R.id.tv_delete)
    TextView tvDelete;
    @InjectView(R.id.tv_go_to_pay)
    TextView tvGoToPay;
    @InjectView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private List<WardrobeModel.ResultBean.DataBean> list = new ArrayList<>();
    private List<WardrobeModel.ResultBean.DataBean.ItemsBean> childrens = new ArrayList<>();// 子元素数据列表

    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量
    private ShopcartExpandableListViewAdapter selva;
    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wardrobe;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        navGoBack.setVisibility(View.GONE);
        navTvTitle.setText("我的衣柜");
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setColorSchemeResources(R.color.main_Red, R.color.color_text_theme);
        getWardrobeData();
        initEvents();
        return rootView;
    }


    //获取购物车数据
    private void getWardrobeData() {
        final Dialog dialog = UiUtil.getLoadDialog(getContext(), true);
        dialog.show();
        Api.getYouWardrobeApi().wardrobeCar(YouWardrobeApplication.getLocalData().getString(LocalData.KEY_USE_ID))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WardrobeModel>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss();
                    }

                    @Override
                    public void onNext(WardrobeModel netModel) {
                        if (netModel.getCode() == NetResultModel.RESULT_CODE_SUCCESS) {
                            list.clear();
                            //// TODO: 2017/5/17   去掉下一行代码 测试用
                            list.addAll(netModel.getResult().getData());
                            if (list.size() == 0) {
                                showEmptyView();
//                                selva.notifyDataSetChanged();
                            } else if (list.size() > 0) {
                                initEvents();
                            }
                        }

                    }
                });
    }


    private void initEvents() {
        selva = new ShopcartExpandableListViewAdapter(list, childrens, getContext());
        selva.setCheckInterface(this);// 关键步骤1,设置复选框接口
//        selva.setModifyCountInterface(this);// 关键步骤2,设置数量增减接口
        exListView.setAdapter(selva);

        for (int i = 0; i < selva.getGroupCount(); i++) {
            exListView.expandGroup(i);// 关键步骤3,初始化时，将ExpandableListView以展开的方式呈现
        }

        allChekbox.setOnClickListener(this);
        tvDelete.setOnClickListener(this);
        tvGoToPay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        AlertDialog alert;
        switch (v.getId()) {
            case R.id.all_chekbox:
                doCheckAll();
                break;
            case R.id.tv_go_to_pay:
                if (totalCount == 0) {
                    Toast.makeText(mActivity, "请选择要支付的商品", Toast.LENGTH_LONG).show();
                    return;
                }
                alert = new AlertDialog.Builder(mActivity).create();
                alert.setTitle("操作提示");
                alert.setMessage("总计:\n" + totalCount + "种商品\n");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mActivity, "下单成功", Toast.LENGTH_LONG).show();
                        return;
                    }
                });
                alert.show();
                break;
            case R.id.tv_delete:
                if (totalCount == 0) {
                    Toast.makeText(mActivity, "请选择要移除的商品", Toast.LENGTH_LONG).show();
                    return;
                }
                alert = new AlertDialog.Builder(mActivity).create();
                alert.setTitle("操作提示");
                alert.setMessage("您确定要将这些商品从购物车中移除吗？");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doDelete();
                    }
                });
                alert.show();
                break;
        }
    }

    /**
     * 删除操作
     * 1.不要边遍历边删除，容易出现数组越界的情况<br>
     * 2.现将要删除的对象放进相应的列表容器中，待遍历完后，以removeAll的方式进行删除
     */
    protected void doDelete() {
        List<String> deleteId = new ArrayList<>();
        final List<WardrobeModel.ResultBean.DataBean> toBeDeleteGroups = new ArrayList<WardrobeModel.ResultBean.DataBean>();// 待删除的组元素列表
        for (int i = 0; i < list.size(); i++) {
            WardrobeModel.ResultBean.DataBean group = list.get(i);
            if (group.isChoosed()) {
                toBeDeleteGroups.add(group);
            }
            List<WardrobeModel.ResultBean.DataBean.ItemsBean> toBeDeleteProducts = new ArrayList<WardrobeModel.ResultBean.DataBean.ItemsBean>();// 待删除的子元素列表
            List<WardrobeModel.ResultBean.DataBean.ItemsBean> childs = group.getItems();// 子元素数据列表
            for (int j = 0; j < childs.size(); j++) {
                if (childs.get(j).isChoosed()) {
                    toBeDeleteProducts.add(childs.get(j));
                    deleteId.add(childs.get(j).getCartID());
                }
            }
            childs.removeAll(toBeDeleteProducts);
        }
        String deleteIds = "";
        for (int i = 0; i <deleteId.size(); i++) {
            if (i == 0) {
                deleteIds = deleteId.get(i);
            } else {
                deleteIds = deleteIds+"," + deleteId.get(i);
            }
        }
        HashMap map = new HashMap<String,String>();
        map.put("cartid",deleteIds);
        Api.getYouWardrobeApi()
                .deleteCar(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<NetResultModel>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(NetResultModel netResultModel) {
                        if (netResultModel.getCode() == NetResultModel.RESULT_CODE_SUCCESS) {
                            list.removeAll(toBeDeleteGroups);
                            selva.notifyDataSetChanged();
                            calculate();
                            DevUtil.showShortInfo(mActivity,"移除成功");
                        }else {
                            DevUtil.showShortInfo(mActivity,"服务器连接失败，请稍后再试");
                        }
                    }
                });
    }


    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {
        WardrobeModel.ResultBean.DataBean group = list.get(groupPosition);
        List<WardrobeModel.ResultBean.DataBean.ItemsBean> childs = group.getItems();// 子元素数据列表

        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).setChoosed(isChecked);
        }
        if (isAllCheck())
            allChekbox.setChecked(true);
        else
            allChekbox.setChecked(false);
        selva.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void checkChild(int groupPosition, int childPosiTion, boolean isChecked) {
        boolean allChildSameState = true;// 判断改组下面的所有子元素是否是同一种状态
        WardrobeModel.ResultBean.DataBean group = list.get(groupPosition);
        List<WardrobeModel.ResultBean.DataBean.ItemsBean> childs = group.getItems();// 子元素数据列表

        for (int i = 0; i < childs.size(); i++) {
            if (childs.get(i).isChoosed() != isChecked) {
                allChildSameState = false;
                break;
            }
        }
        if (allChildSameState) {
            group.setChoosed(isChecked);// 如果所有子元素状态相同，那么对应的组元素被设为这种统一状态
        } else {
            group.setChoosed(false);// 否则，组元素一律设置为未选中状态
        }

        if (isAllCheck())
            allChekbox.setChecked(true);
        else
            allChekbox.setChecked(false);
        selva.notifyDataSetChanged();
        calculate();
    }

    private boolean isAllCheck() {
        for (WardrobeModel.ResultBean.DataBean group : list) {
            if (!group.isChoosed())
                return false;
        }

        return true;
    }

    /**
     * 全选与反选
     */
    private void doCheckAll() {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setChoosed(allChekbox.isChecked());
            WardrobeModel.ResultBean.DataBean group = list.get(i);
            List<WardrobeModel.ResultBean.DataBean.ItemsBean> childrens = group.getItems();// 子元素数据列表
            for (int j = 0; j < childrens.size(); j++) {
                childrens.get(j).setChoosed(allChekbox.isChecked());
            }
        }
        selva.notifyDataSetChanged();
        calculate();
    }

    /**
     * 统计操作<br>
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
     * 3.给底部的textView进行数据填充
     */
    private void calculate() {
        totalCount = 0;
        for (int i = 0; i < list.size(); i++) {
            WardrobeModel.ResultBean.DataBean group = list.get(i);
            List<WardrobeModel.ResultBean.DataBean.ItemsBean> childrens = group.getItems();// 子元素数据列表

            for (int j = 0; j < childrens.size(); j++) {
                WardrobeModel.ResultBean.DataBean.ItemsBean product = childrens.get(j);
                if (product.isChoosed()) {
                    totalCount++;
                }
            }
        }
        tvTotalPrice.setText(totalCount + "件");
        tvGoToPay.setText("去支付(" + totalCount + ")");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getWardrobeData();
                // 加载完数据设置为不刷新状态，将下拉进度收起来
                swipeRefresh.setRefreshing(false);
            }
        }, 500);
    }
}
