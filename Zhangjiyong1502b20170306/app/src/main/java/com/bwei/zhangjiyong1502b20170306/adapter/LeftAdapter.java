package com.bwei.zhangjiyong1502b20170306.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.zhangjiyong1502b20170306.R;
import com.bwei.zhangjiyong1502b20170306.entity.MyMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： 南山君
 * 时间：2017/3/6.9:06
 */

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.MyViewHolder> {

    Context context;

    List<MyMessage.RsBean> list=new ArrayList<>();

    public LeftAdapter(Context context) {
        this.context = context;
    }

    public void addRest(List<MyMessage.RsBean> list){

        this.list.clear();
        this.list.addAll(list);
        //刷新界面
        this.notifyDataSetChanged();

    }

    @Override
    public LeftAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //实例化viewholder
        MyViewHolder holder=new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.recycleview_left, parent,
                false));

        return holder;
    }
    //点击事件
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public void onBindViewHolder(final LeftAdapter.MyViewHolder holder, int position) {

        holder.tv_left.setText(list.get(position).getDirName());

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView tv_left;

        public MyViewHolder(View view)
        {
            super(view);
            tv_left = (TextView) view.findViewById(R.id.tv_left);
        }
    }
}
