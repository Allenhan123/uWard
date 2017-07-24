package com.ygxinjian.anhui.youwardrobe.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ygxinjian.anhui.youwardrobe.Activity.GoodsDetailsActivity;
import com.ygxinjian.anhui.youwardrobe.Model.GroupInfo;
import com.ygxinjian.anhui.youwardrobe.Model.ProductInfo;
import com.ygxinjian.anhui.youwardrobe.Model.WardrobeModel;
import com.ygxinjian.anhui.youwardrobe.R;
import com.ygxinjian.anhui.youwardrobe.utils.DevUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShopCarExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<WardrobeModel.ResultBean.DataBean> groups;

    //被选择的items
    private List<WardrobeModel.ResultBean.DataBean.ItemsBean> selectItems = new ArrayList<>();
    private CheckListener checkListener;


    private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;


    /**
     * 构造函数
     *
     * @param groups  组元素列表
     * @param context
     */
    public ShopCarExpandableListViewAdapter(List<WardrobeModel.ResultBean.DataBean> groups, Context context) {
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
            convertView = View.inflate(context, R.layout.item_shopcart_group, null);
            gholder = new GroupHolder(convertView);
            convertView.setTag(gholder);
        } else {
            gholder = (GroupHolder) convertView.getTag();
        }
        WardrobeModel.ResultBean.DataBean groupModel = (WardrobeModel.ResultBean.DataBean) getGroup(groupPosition);
        gholder.bindData(groupModel, groupPosition);
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final ChildHolder cholder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_shopcart_product, null);
            cholder = new ChildHolder(convertView);

            convertView.setTag(cholder);
        } else {
            cholder = (ChildHolder) convertView.getTag();
        }
        final WardrobeModel.ResultBean.DataBean.ItemsBean product = (WardrobeModel.ResultBean.DataBean.ItemsBean) getChild(groupPosition, childPosition);

       cholder.bindData(groupPosition,childPosition,product);
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

        public GroupHolder(View view) {
            cb_check = (CheckBox) view.findViewById(R.id.determine_chekbox);
            tv_group_name = (TextView) view.findViewById(R.id.tv_source_name);
        }

        public void bindData(final WardrobeModel.ResultBean.DataBean groupModel, final int groupPosition) {
            if (groupModel != null) {
                tv_group_name.setText(groupModel.getClassifyTitle());
                cb_check.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        groupModel.setChoosed(((CheckBox) v).isChecked());
//                        checkInterface.checkGroup(groupPosition, ((CheckBox) v).isChecked());// 暴露组选接口
                        //处理数据
                        handleData(groupModel,((CheckBox) v).isChecked());
                    }
                });
                cb_check.setChecked(groupModel.isChoosed());
            }
        }


        private void handleData(WardrobeModel.ResultBean.DataBean groupModel, boolean isCheck) {
            //组 isCheck==true    -> 所有子 item 被选择
            if (isCheck) {
                for (WardrobeModel.ResultBean.DataBean.ItemsBean itemsBean : groupModel.getItems()) {
                    itemsBean.setChoosed(isCheck);
                    selectItems.add(itemsBean);
                }
            } else {
                //组 isCheck==false-> 所有子 item 未选择
                for (WardrobeModel.ResultBean.DataBean.ItemsBean itemsBean : groupModel.getItems()) {
                    itemsBean.setChoosed(isCheck);
                    selectItems.remove(itemsBean);
                }
            }
            if (checkListener != null) {
                checkListener.itemClick(selectItems);
            }
        }
    }

    /**
     * 子元素绑定器
     */
    private class ChildHolder {
        View item;
        CheckBox cb_check;
        TextView tv_product_name;
        TextView tv_product_desc;
        ImageView iv_car_pic;

        public ChildHolder(View convertView) {
            item=convertView;
            cb_check = (CheckBox) convertView.findViewById(R.id.check_box);
            tv_product_name = (TextView) convertView.findViewById(R.id.tv_car_intro);
            tv_product_desc = (TextView) convertView.findViewById(R.id.tv_car_descr);
            iv_car_pic = (ImageView) convertView.findViewById(R.id.iv_car_list_pic);
        }


        public void bindData(final int groupPosition, int childPosition, final WardrobeModel.ResultBean.DataBean.ItemsBean product){

            item.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent _intent = new Intent(context, GoodsDetailsActivity.class);
                    _intent.putExtra("title", product.getProdTitle());
                    _intent.putExtra("url", product.getProdID());
                    context.startActivity(_intent);
                }
            });
            if (product != null) {
                tv_product_name.setText(product.getProdTitle());
                tv_product_desc.setText(product.getProdDesc());
                ImageLoader.getInstance().displayImage(product.getImgUrl(), iv_car_pic);

                cb_check.setChecked(product.isChoosed());
                cb_check.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        product.setChoosed(((CheckBox) v).isChecked());
//                        cholder.cb_check.setChecked(((CheckBox) v).isChecked());
//                        checkInterface.checkChild(groupPosition, childPosition, ((CheckBox) v).isChecked());// 暴露子选接口
                        handleData(groupPosition,product,((CheckBox) v).isChecked());
                    }
                });

            }
        }

        public void handleData(int groupPosition,WardrobeModel.ResultBean.DataBean.ItemsBean product,boolean isCheck){
            product.setChoosed(isCheck);
            for (WardrobeModel.ResultBean.DataBean.ItemsBean itemsBean : groups.get(groupPosition).getItems()) {
                if (!itemsBean.isChoosed()){

                }
            }

        }
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


    public interface CheckListener {
        void itemClick(List<WardrobeModel.ResultBean.DataBean.ItemsBean> selectItem);
    }

    public void setCheckListener(CheckListener checkListener) {
        this.checkListener = checkListener;
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
