package com.bwei.zhangjiyong1501a20161212.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.zhangjiyong1501a20161212.R;
import com.bwei.zhangjiyong1501a20161212.entity.NeedMessage;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： 南山君
 * 时间：2016/12/12.10:12
 */

public class ListviewAdapter extends BaseAdapter {

    Context context;

    List<NeedMessage> list=new ArrayList<>();

    public ListviewAdapter(Context context) {

        this.context=context;

    }

    public void addList(List<NeedMessage> list){

        this.list.clear();
        this.list.addAll(list);
        this.notifyDataSetChanged();

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
        viewHolder holder;

        if (view==null){

            view=View.inflate(context, R.layout.adapter_listview,null);

            holder=new viewHolder();
            //找到控件
            holder.iv= (ImageView) view.findViewById(R.id.iv_listview);

            holder.tv_content= (TextView) view.findViewById(R.id.tv_content);

            holder.tv_title= (TextView) view.findViewById(R.id.tv_title);

            view.setTag(holder);
        }else {

            holder= (viewHolder) view.getTag();

        }
        //给控件赋值
        ImageLoader.getInstance().displayImage(list.get(i).image_url,holder.iv);

        holder.tv_content.setText(list.get(i).content);

        holder.tv_title.setText(list.get(i).title);

        return view;
    }

    class viewHolder{

        ImageView iv;
        TextView tv_title;
        TextView tv_content;

    }
}
