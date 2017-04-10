package com.bwei.eggplantdoctor.activity;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.eggplantdoctor.R;

/**
 * 作者： 南山君
 * 时间：2017/1/9.16:45
 */

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    private ImageView iv_left_query;
    private TextView tv_today;
    private TextView tv_center;
    private ImageView iv_right_query;
    private ImageView iv_right_add;

    public static final int LEFT_IMAEG_RIGHT_IMAGEQUERY = 1;//左边图片 和右边图片
    public static final int LEFT_IMAGE_RIGHT_IMAGEADD = 2;//左边图片,右边图片
    public static final int LEFT_IMAGE_RIGHT_NO = 3; //左边图片,右边没有
    public static final int LEFT_TEXT_RIGHT_NO = 4; //左边文字,右边没有
    public static final int LEFT_TEXT_RIGHT_IMAGEQUERY = 5; //左边文字右边图片
    public static final int LEFT_TEXT_RIGHT_IMAGEADD= 6; //左边文字右边图片
    public static final int LEFT_NO_RIGHT_IMAGEQUERY = 7; //左边没有,右边图片
    public static final int LEFT_NO_RIGHT_IMAGEADD = 8; //左边没有,右边图片
    public static final int LEFT_NO_RIGHT_NO = 9; //左边没有,右边没有

    //初始化头部
    public abstract void initHeader();
    //初始化控件
    public abstract void initWidget();
    //设置监听
    public abstract void initSetListener();

    public void initHeaderWidget(){

        iv_left_query = (ImageView) findViewById(R.id.iv_left_query);

        tv_today = (TextView) findViewById(R.id.tv_today);

        tv_center = (TextView) findViewById(R.id.tv_center);

        iv_right_query = (ImageView) findViewById(R.id.iv_right_query);

        iv_right_add = (ImageView) findViewById(R.id.iv_right_add);

    }

    public void SetListener(){

        setImageListener();

    }

    public void setImageListener(){

        iv_left_query.setOnClickListener(this);

        iv_right_add.setOnClickListener(this);

        iv_right_query.setOnClickListener(this);

    }

    public void addImageLeftListener(View.OnClickListener listener,int id){

        iv_left_query.setVisibility(View.VISIBLE);

        iv_left_query.setOnClickListener(listener);

        iv_left_query.setImageResource(id);

    }

    public void addImageRightQueryListener(View.OnClickListener listener,int id){

        iv_right_query.setVisibility(View.VISIBLE);

        iv_right_query.setOnClickListener(listener);

        iv_right_query.setImageResource(id);

    }

    public void addImageRightAddListener(View.OnClickListener listener,int id){

        iv_right_add.setVisibility(View.VISIBLE);

        iv_right_add.setOnClickListener(listener);

        iv_right_add.setImageResource(id);

    }

    public void setTitle(String title){

        iv_left_query.setVisibility(View.GONE);

        tv_today.setVisibility(View.GONE);

        iv_right_query.setVisibility(View.GONE);

        iv_right_add.setVisibility(View.GONE);

        tv_center.setText(title);
    }

    @Override
    public void onClick(View view) {

    }

    public void Left_ImageQuery() {
        iv_left_query.setVisibility(View.VISIBLE);
    }

    public void Right_ImageQuery() {
        iv_right_query.setVisibility(View.VISIBLE);
    }

    public void Left_TextToday() {
        tv_today.setVisibility(View.VISIBLE);
    }

    public void CenterText() {
        tv_center.setVisibility(View.VISIBLE);
    }

    public void Right_ImageAdd() {
        iv_right_add.setVisibility(View.VISIBLE);
    }

    public void setNone() {
        iv_left_query.setVisibility(View.GONE);
        iv_right_query.setVisibility(View.GONE);
        tv_today.setVisibility(View.GONE);
        iv_right_add.setVisibility(View.GONE);
    }

    public void setTitleStatue(int position){

        switch (position){

            case LEFT_IMAEG_RIGHT_IMAGEQUERY:

                Left_ImageQuery();

                Right_ImageQuery();

                CenterText();

                break;

            case LEFT_IMAGE_RIGHT_IMAGEADD:

                setNone();

                Left_ImageQuery();

                Right_ImageAdd();

                CenterText();

                break;

            case LEFT_IMAGE_RIGHT_NO:

                setNone();

                Left_ImageQuery();

                CenterText();

                break;

            case LEFT_TEXT_RIGHT_NO:

                setNone();

                Left_TextToday();

                CenterText();

                break;

            case LEFT_TEXT_RIGHT_IMAGEQUERY:

                setNone();

                Left_TextToday();

                Right_ImageQuery();

                CenterText();

                break;

            case LEFT_TEXT_RIGHT_IMAGEADD:

                setNone();

                Left_TextToday();

                Right_ImageAdd();

                CenterText();

                break;

            case LEFT_NO_RIGHT_IMAGEQUERY:

                setNone();

                Right_ImageQuery();

                CenterText();

                break;

            case LEFT_NO_RIGHT_IMAGEADD:

                setNone();

                Right_ImageAdd();

                CenterText();

                break;

            case LEFT_NO_RIGHT_NO:

                setNone();

                CenterText();

                break;

        }

    }

}
