package com.bwei.zhangjiyong1502b20170227;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.zhangjiyong1502b20170227.entity.AllMessage;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： 南山君
 * 时间：2017/2/27.9:27
 */

public class MyAdapter extends BaseAdapter {

    List<AllMessage.DataBean> list=new ArrayList<>();

    Context context;

    public MyAdapter(Context context,List<AllMessage.DataBean> list) {
        this.context = context;
        this.list=list;
    }
//    //接收和刷新数据
//    public void addRest(List<AllMessage.DataBean> list){
//
//        this.list.clear();
//        this.list.addAll(list);
//        this.notifyDataSetChanged();
//
//    }

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
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if (convertView==null){

            viewHolder=new ViewHolder();

            convertView=View.inflate(context,R.layout.listview_adapter,null);

            viewHolder.bt_map_dw= (Button) convertView.findViewById(R.id.bt_map_dw);

            viewHolder.bt_picture_hc= (Button) convertView.findViewById(R.id.bt_picture_hc);

            viewHolder.iv_pivture= (ImageView) convertView.findViewById(R.id.iv_picture);

            viewHolder.tv_age= (TextView) convertView.findViewById(R.id.tv_age);

            viewHolder.tv_occupation= (TextView) convertView.findViewById(R.id.tv_occupation);

            viewHolder.tv_introduction= (TextView) convertView.findViewById(R.id.tv_introduction);

            convertView.setTag(viewHolder);

        }else {

            viewHolder= (ViewHolder) convertView.getTag();

        }
//地图定位
        viewHolder.bt_map_dw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent();

                intent.setClass(context, DingWeiActivity.class);

                context.startActivity(intent);

            }
        });
//清除缓存
        viewHolder.bt_picture_hc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
//赋值
        BitmapUtils bitmapUtils=new BitmapUtils(context);

        bitmapUtils.display(viewHolder.iv_pivture,list.get(i).getImg());

        if (list.get(i).getOccupation()!=null){

            viewHolder.tv_occupation.setVisibility(View.VISIBLE);

            viewHolder.tv_occupation.setText(list.get(i).getOccupation());

        }

        if (list.get(i).getUserAge()+""!=null){

            viewHolder.tv_age.setVisibility(View.VISIBLE);

            viewHolder.tv_age.setText(list.get(i).getUserAge()+"");

        }

        viewHolder.tv_introduction.setText(list.get(i).getIntroduction());

        return convertView;
    }
//listview优化
    class ViewHolder{

        Button bt_map_dw;
        Button bt_picture_hc;
        ImageView iv_pivture;
        TextView tv_occupation;
        TextView tv_age;
        TextView tv_introduction;

    }

}
