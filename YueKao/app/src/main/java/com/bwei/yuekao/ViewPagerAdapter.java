package com.bwei.yuekao;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * 作者： 南山君
 * 时间：2016/12/22.16:24
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment=new Fragment();

        switch (position){

            case 0:

                fragment=new NewsFragment();

                break;

            case 1:
                break;

            case 2:
                break;

            case 3:
                break;

            case 4:
                break;

        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
