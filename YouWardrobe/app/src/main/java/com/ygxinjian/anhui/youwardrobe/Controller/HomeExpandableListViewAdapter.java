package com.ygxinjian.anhui.youwardrobe.Controller;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ygxinjian.anhui.youwardrobe.Model.HomeCategoryModel;
import com.ygxinjian.anhui.youwardrobe.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ExpandableListView 适配器
 */
public class HomeExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context mContext;

    /**
     * 每个分组的名字的集合
     */
    private List<HomeCategoryModel.ResultBean.DataBean> groupList;

    /**
     * 所有分组的所有子项的 GridView 数据集合
     */
    private List<List<HomeCategoryModel.ResultBean.DataBean.ItemsBean>> itemList;


    public HomeExpandableListViewAdapter(Context context, List<HomeCategoryModel.ResultBean.DataBean> groupList,
                                         List<List<HomeCategoryModel.ResultBean.DataBean.ItemsBean>> itemList) {
        mContext = context;
        this.groupList = groupList;
        this.itemList = itemList;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return itemList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup
            parent) {
        if (null == convertView) {
            convertView = View.inflate(mContext, R.layout.item_home_group, null);
        }
        TextView tvGroup = (TextView) convertView.findViewById(R.id.tv_group_title);
        TextView tvGroupAll = (TextView) convertView.findViewById(R.id.tv_group_all);

        // 设置分组组名
        tvGroup.setText(groupList.get(groupPosition).getClassifyTitle());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View
            convertView, ViewGroup parent) {
        if (null == convertView) {
            mViewChild = new ViewChild();
            convertView = View.inflate(mContext, R.layout.item_home_grid, null);
            mViewChild.gridView = (GridView) convertView.findViewById(R.id.child_gridView);
            convertView.setTag(mViewChild);
        }else {
            mViewChild = (ViewChild) convertView.getTag();
        }
        MyGridViewAdapter gridViewAdapter = new MyGridViewAdapter(mContext, itemList.get
                (groupPosition));
        mViewChild.gridView.setAdapter(gridViewAdapter);
        mViewChild.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, "点击了第" + (groupPosition + 1) + "组，第" +
                        (position + 1) + "项", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    ViewChild mViewChild;
    static class ViewChild {
        ImageView imageHead;
        ImageView imageView;
        TextView textView;
        GridView gridView;
    }

}
