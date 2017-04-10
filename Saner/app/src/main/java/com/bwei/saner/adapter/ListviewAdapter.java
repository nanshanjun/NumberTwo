package com.bwei.saner.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.saner.R;
import com.bwei.saner.entity.NeedImage;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： 南山君
 * 时间：2016/12/22.14:08
 */

public class ListviewAdapter extends BaseAdapter {

    Context context;
    List<NeedImage> list=new ArrayList<>();

    public ListviewAdapter(Context context, List<NeedImage> list) {
        this.context = context;
        this.list = list;
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

        viewHolder holder=null;

        if (view==null){

            view=View.inflate(context, R.layout.listview_it,null);

            holder=new viewHolder();

            holder.iv= (ImageView) view.findViewById(R.id.iv);

            holder.tv_name= (TextView) view.findViewById(R.id.tv_name);

            holder.tv_content= (TextView) view.findViewById(R.id.tv_content);

            view.setTag(holder);

        }else {

            holder= (viewHolder) view.getTag();

        }


        ImageLoader.getInstance().displayImage(list.get(i).carouselUrl,holder.iv);

//        holder.tv_name.setText(list.get(i).body.get(i).detectionItem);
//
//        holder.tv_content.setText(list.get(i).body.get(i).categoryTitle);

//        Toast.makeText(context,list.get(i).img.size()+"",Toast.LENGTH_SHORT).show();

        return view;
    }

    class viewHolder{

        ImageView iv;

        TextView tv_name;

        TextView tv_content;

    }

}
