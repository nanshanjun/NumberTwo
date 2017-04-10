package com.bwei.zhangjiyong1502b20170220;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwei.zhangjiyong1502b20170220.entity.AllMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： 南山君
 * 时间：2017/2/20.9:08
 */

public class MyAdapter extends BaseAdapter{

    Context context;

    List<AllMessage.ResultBean.DataBean> list=new ArrayList<>();

    public MyAdapter(Context context) {
        this.context = context;
    }

    public void addRest(List<AllMessage.ResultBean.DataBean> list){

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
    public View getView(int i, View convertview, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if (convertview==null){

            viewHolder=new ViewHolder();

            convertview=View.inflate(context,R.layout.listview,null);

            viewHolder.tv_content= (TextView) convertview.findViewById(R.id.tv_content);

            viewHolder.tv_time= (TextView) convertview.findViewById(R.id.tv_time);

            convertview.setTag(viewHolder);

        }else {

            viewHolder= (ViewHolder) convertview.getTag();

        }

        viewHolder.tv_content.setText(list.get(i).getContent());

        viewHolder.tv_time.setText(list.get(i).getUpdatetime());

        return convertview;
    }

    class ViewHolder{

        TextView tv_content;

        TextView tv_time;

    }
}
