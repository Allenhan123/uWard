package com.ygxinjian.anhui.youwardrobe.Controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ygxinjian.anhui.youwardrobe.Constant;
import com.ygxinjian.anhui.youwardrobe.Model.RecommendDesignModel;
import com.ygxinjian.anhui.youwardrobe.R;

import java.util.List;

import static com.mob.MobSDK.getContext;

/**
 * Created by handongqiang on 17/6/7.
 */

public class SwipeCardAdapter extends BaseAdapter{
    private Context context = null;
    private List<RecommendDesignModel.ResultBean.DataBean> markerItems = null;
    public SwipeCardAdapter(Context context, List<RecommendDesignModel.ResultBean.DataBean> markerItems)
    {
        this.context = context;
        this.markerItems = markerItems;
    }

    @Override
    public int getCount()
    {
        return markerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return(markerItems.get(position));

    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder = null;
        if (null == convertView)
        {
            viewHolder = new ViewHolder();
//            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = LayoutInflater.from(context).inflate(R.layout.recommend_item, null);

            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_recommend_name);
            viewHolder.description = (TextView) convertView.findViewById(R.id.tv_recommend_size);
            viewHolder.iv_recommend = (ImageView) convertView.findViewById(R.id.iv_recommend_design);
//            viewHolder.createTime = (TextView) convertView
//                    .findViewById(R.id.createTime);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // set item values to the viewHolder:


            viewHolder.name.setText("sdfsdfsd");
            viewHolder.description.setText(markerItems.get(position).getSize());
//            ImageLoader.getInstance().displayImage(Constant.Base_Image_Url+markerItems.get(position).getImgUrl(),viewHolder.iv_recommend);
//            viewHolder.createTime.setText(markerItem.getCreateDate());

        return convertView;
    }

    private static class ViewHolder
    {
        TextView name;
        TextView description;
        TextView createTime;
        ImageView iv_recommend;
    }
}
