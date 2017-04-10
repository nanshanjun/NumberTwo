package com.bwei.zhangjiyong1502b20170305a.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.zhangjiyong1502b20170305a.R;
import com.bwei.zhangjiyong1502b20170305a.bean.MyMessage;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： 南山君
 * 时间：2017/3/5.13:29
 */

public class DownAdapter extends RecyclerView.Adapter<DownAdapter.MyViewHolder>{

    Context context;

    List<MyMessage.RsBean.ChildrenBeanX.ChildrenBean> list=new ArrayList<>();

    public DownAdapter(Context context) {
        this.context = context;
    }

    public void addRest(List<MyMessage.RsBean.ChildrenBeanX.ChildrenBean> list){

        this.list.clear();
        this.list.addAll(list);
        this.notifyDataSetChanged();

    }

    @Override
    public DownAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycleview_down, parent,
                false));

        return holder;
    }

    @Override
    public void onBindViewHolder(final DownAdapter.MyViewHolder holder, int position) {

        ImageLoader.getInstance().displayImage(list.get(position).getImgApp(),holder.iv_down);

        holder.tv_down.setText(list.get(position).getDirName());

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

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_down;

        TextView tv_down;

        public MyViewHolder(View itemView) {
            super(itemView);

            iv_down= (ImageView) itemView.findViewById(R.id.iv_down);

            tv_down= (TextView) itemView.findViewById(R.id.tv_down);

        }
    }

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

}
