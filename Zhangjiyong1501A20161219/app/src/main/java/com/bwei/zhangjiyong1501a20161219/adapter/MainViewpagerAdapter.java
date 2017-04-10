package com.bwei.zhangjiyong1501a20161219.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bwei.zhangjiyong1501a20161219.fragment.HomeFragment;
import com.bwei.zhangjiyong1501a20161219.fragment.MineFragment;
import com.bwei.zhangjiyong1501a20161219.fragment.MoneyFragment;
import com.bwei.zhangjiyong1501a20161219.fragment.StypeFragment;

/**
 * 作者： 南山君
 * 时间：2016/12/19.9:28
 */

public class MainViewpagerAdapter extends FragmentPagerAdapter {

    public MainViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment=new Fragment();

        switch (position){

            case 0:

                fragment=new HomeFragment();

                break;

            case 1:

                fragment=new MoneyFragment();

                break;

            case 2:

                fragment=new StypeFragment();

                break;

            case 3:

                fragment=new MineFragment();

                break;

        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
