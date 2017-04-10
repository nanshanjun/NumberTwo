package com.bwei.myrecyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * 作者： 南山君
 * 时间：2017/1/5.14:38
 */

public class DividerItemDecoration extends RecyclerView{

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public DividerItemDecoration(Context context) {
        super(context);
    }

    public DividerItemDecoration(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public DividerItemDecoration(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
