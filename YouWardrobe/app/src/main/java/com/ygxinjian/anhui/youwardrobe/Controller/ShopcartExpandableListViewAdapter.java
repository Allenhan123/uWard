package com.ygxinjian.anhui.youwardrobe.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ygxinjian.anhui.youwardrobe.Activity.GoodsDetailsActivity;
import com.ygxinjian.anhui.youwardrobe.Model.GroupInfo;
import com.ygxinjian.anhui.youwardrobe.Model.ProductInfo;
import com.ygxinjian.anhui.youwardrobe.Model.WardrobeModel;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.utils.DevUtil;

import java.util.List;
import java.util.Map;

public class ShopcartExpandableListViewAdapter extends BaseExpandableListAdapter {
    private List<WardrobeModel.ResultBean.DataBean> groups;

    private Context context;

    private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;

    /**
     * 构造函数
     *
     * @param groups   组元素列表
     * @param children 子元素列表
     * @param context
     */
    public ShopcartExpandableListViewAdapter(List<WardrobeModel.ResultBean.DataBean> groups, List<WardrobeModel.ResultBean.DataBean.ItemsBean> children, Context context) {
        super();
        this.groups = groups;
        this.context = context;
    }

    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).getItems().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).getItems().get(childPosition);
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
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        GroupHolder gholder;
        if (convertView == null) {
            gholder = new GroupHolder();
            convertView = View.inflate(context, R.layout.item_shopcart_group, null);
            gholder.cb_check = (CheckBox) convertView.findViewById(R.id.determine_chekbox);
            gholder.tv_group_name = (TextView) convertView.findViewById(R.id.tv_source_name);
            convertView.setTag(gholder);
        } else {
            gholder = (GroupHolder) convertView.getTag();
        }
        final WardrobeModel.ResultBean.DataBean groupModel = (WardrobeModel.ResultBean.DataBean) getGroup(groupPosition);
        if (groupModel != null) {
            gholder.tv_group_name.setText(groupModel.getClassifyTitle());

            gholder.cb_check.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v)

                {
                    groupModel.setChoosed(((CheckBox) v).isChecked());
                    checkInterface.checkGroup(groupPosition, ((CheckBox) v).isChecked());// 暴露组选接口
                }
            });
            gholder.cb_check.setChecked(groupModel.isChoosed());
        }
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final ChildHolder cholder;
        if (convertView == null) {
            cholder = new ChildHolder();
            convertView = View.inflate(context, R.layout.item_shopcart_product, null);
            cholder.cb_check = (CheckBox) convertView.findViewById(R.id.check_box);

            cholder.tv_product_desc = (TextView) convertView.findViewById(R.id.tv_intro);
            cholder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(cholder);
        } else {
            cholder = (ChildHolder) convertView.getTag();
        }
        final WardrobeModel.ResultBean.DataBean.ItemsBean product = (WardrobeModel.ResultBean.DataBean.ItemsBean) getChild(groupPosition,childPosition);

        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                DevUtil.showInfo(context,"sd"+groupPosition+childPosition);
                Intent _intent = new Intent(context,GoodsDetailsActivity.class);
                _intent.putExtra("title",product.getProdTitle());
                _intent.putExtra("url",product.getProdID());
                context.startActivity(_intent);
            }
        });
        if (product != null) {
            cholder.tv_product_desc.setText(product.getProdDesc());
            cholder.cb_check.setChecked(product.isChoosed());
            cholder.cb_check.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    product.setChoosed(((CheckBox) v).isChecked());
                    cholder.cb_check.setChecked(((CheckBox) v).isChecked());
                    checkInterface.checkChild(groupPosition, childPosition, ((CheckBox) v).isChecked());// 暴露子选接口
                }
            });

        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    /**
     * 组元素绑定器
     */
    private class GroupHolder {
        CheckBox cb_check;
        TextView tv_group_name;
    }

    /**
     * 子元素绑定器
     */
    private class ChildHolder {
        CheckBox cb_check;

        TextView tv_product_name;
        TextView tv_product_desc;
        TextView tv_price;
        TextView iv_increase;
        TextView tv_count;
        TextView iv_decrease;
    }

    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param groupPosition 组元素位置
         * @param isChecked     组元素选中与否
         */
        public void checkGroup(int groupPosition, boolean isChecked);

        /**
         * 子选框状态改变时触发的事件
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param isChecked     子元素选中与否
         */
        public void checkChild(int groupPosition, int childPosition, boolean isChecked);
    }

    /**
     * 改变数量的接口
     */
    public interface ModifyCountInterface {
        /**
         * 增加操作
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);

        /**
         * 删减操作
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);
    }

}
