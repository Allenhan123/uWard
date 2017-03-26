package com.ygxinjian.anhui.youwardrobe.Controller;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ygxinjian.anhui.youwardrobe.Model.HomeCategoryModel;
import com.ygxinjian.anhui.youwardrobe.R;

import java.util.List;

/**
 * GridView 适配器
 */
public class MyGridViewAdapter extends BaseAdapter {

    private Context mContext;

    /**
     * 每个分组下的每个子项的 GridView 数据集合
     */
    private List<HomeCategoryModel.ResultBean.DataBean.ItemsBean> itemGridList;

    public MyGridViewAdapter(Context mContext, List<HomeCategoryModel.ResultBean.DataBean.ItemsBean> itemGridList) {
        this.mContext = mContext;
        this.itemGridList = itemGridList;
    }

    @Override
    public int getCount() {
        return itemGridList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemGridList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = View.inflate(mContext, R.layout.home_grid_item, null);
        }
        TextView tvGridView = (TextView) convertView.findViewById(R.id.tv_home_grid_title);
        ImageView ivGridView = (ImageView) convertView.findViewById(R.id.iv_home_grid_item);
        ImageLoader.getInstance().displayImage(itemGridList.get(position).getImgUrl(), ivGridView);

        tvGridView.setText("风格分类");

        return convertView;
    }
}
