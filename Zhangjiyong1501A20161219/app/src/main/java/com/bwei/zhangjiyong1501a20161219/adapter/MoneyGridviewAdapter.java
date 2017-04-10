package com.bwei.zhangjiyong1501a20161219.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.zhangjiyong1501a20161219.R;
import com.bwei.zhangjiyong1501a20161219.entity.NeedMessage;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： 南山君
 * 时间：2016/12/19.9:40
 */

public class MoneyGridviewAdapter extends BaseAdapter {

    List<NeedMessage> list=new ArrayList<>();
    Context context;

    public MoneyGridviewAdapter(List<NeedMessage> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder=null;

        if (view==null){
            //加载布局
            view=View.inflate(context, R.layout.gridview_money,null);

            holder=new ViewHolder();
            //找到相应控件
            holder.iv_gridview_image= (ImageView) view.findViewById(R.id.iv_gridview_image);

            holder.tv_gridview_title= (TextView) view.findViewById(R.id.tv_gridview_title);

            holder.tv_gridview_content= (TextView) view.findViewById(R.id.tv_gridview_content);

            view.setTag(holder);

        }else {

            holder= (ViewHolder) view.getTag();

        }
        //图片赋值
        ImageLoader.getInstance().displayImage(list.get(i).image_url,holder.iv_gridview_image);
        //赋值
        holder.tv_gridview_title.setText(list.get(i).title);

        holder.tv_gridview_content.setText(list.get(i).content);

        return view;
    }
    //gridview优化
    class ViewHolder{

        ImageView iv_gridview_image;

        TextView tv_gridview_title;

        TextView tv_gridview_content;

    }

}
