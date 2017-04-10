package com.bwei.zhangjiyong1501a20161219;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bwei.zhangjiyong1501a20161219.adapter.MainViewpagerAdapter;

/**
 * 作者： 南山君
 * 时间：2016/12/19.9:36
 */

public class FirstActivity extends FragmentActivity {

    private ViewPager vp_main_show;
    private RadioGroup rg_main_use;
    private RadioButton rb_main_mine;
    private RadioButton rb_main_stype;
    private RadioButton rb_main_money;
    private RadioButton rb_main_home;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到控件的方法
        initHeader();
        //给控件设置监听的方法
        initListener();

    }

    private void initListener() {
        //viewpager设置监听
        vp_main_show.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                switch (position){

                    case 0:

                        rb_main_home.setTextColor(Color.RED);
                        rb_main_money.setTextColor(Color.BLACK);
                        rb_main_stype.setTextColor(Color.BLACK);
                        rb_main_mine.setTextColor(Color.BLACK);

                        break;

                    case 1:

                        rb_main_home.setTextColor(Color.BLACK);
                        rb_main_money.setTextColor(Color.RED);
                        rb_main_stype.setTextColor(Color.BLACK);
                        rb_main_mine.setTextColor(Color.BLACK);

                        break;

                    case 2:

                        rb_main_home.setTextColor(Color.BLACK);
                        rb_main_money.setTextColor(Color.BLACK);
                        rb_main_stype.setTextColor(Color.RED);
                        rb_main_mine.setTextColor(Color.BLACK);

                        break;

                    case 3:

                        rb_main_home.setTextColor(Color.BLACK);
                        rb_main_money.setTextColor(Color.BLACK);
                        rb_main_stype.setTextColor(Color.BLACK);
                        rb_main_mine.setTextColor(Color.RED);

                        break;

                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //radiogroup设置监听
        rg_main_use.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i){

                    case R.id.rb_main_home:

                        vp_main_show.setCurrentItem(0);

                        break;

                    case R.id.rb_main_money:

                        vp_main_show.setCurrentItem(1);

                        break;

                    case R.id.rb_main_stype:

                        vp_main_show.setCurrentItem(2);

                        break;

                    case R.id.rb_main_mine:

                        vp_main_show.setCurrentItem(3);

                        break;

                }

            }
        });

        vp_main_show.setAdapter(new MainViewpagerAdapter(getSupportFragmentManager()));

    }
    //找到相应的控件
    private void initHeader() {

        vp_main_show = (ViewPager)findViewById(R.id.vp_main_show);

        rg_main_use = (RadioGroup)findViewById(R.id.rg_main_use);

        rb_main_home = (RadioButton)findViewById(R.id.rb_main_home);

        rb_main_money = (RadioButton)findViewById(R.id.rb_main_money);

        rb_main_stype = (RadioButton)findViewById(R.id.rb_main_stype);

        rb_main_mine = (RadioButton)findViewById(R.id.rb_main_mine);
        //设置初始颜色
        rb_main_home.setTextColor(Color.RED);
        //设置初始fragment
        vp_main_show.setCurrentItem(0);

    }

}
