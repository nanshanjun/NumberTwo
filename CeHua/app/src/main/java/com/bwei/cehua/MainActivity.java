package com.bwei.cehua;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    /*
    * ll_left 左边布局
    * ll_main 主布局
    */
    private LinearLayout ll_left;
    private LinearLayout ll_main;
    private LinearLayout activity_main;
    /*
    * 是不是第一次点击
    * false 第一次点击,true 不是第一次点击
    */
    boolean isClick=false;
    //左边距
    int left;
        /*
    * distancex 滑动距离
    * distancey 滑动距离
    */
    int distancex;
    int distancey;
        /*
    * x 点击初始位置x
    * y 单击出事位置y
    */
    int x;
    int y;
        /*
    * speed 以恒定的速度移动
    *
    */
    int speed=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity_main = (LinearLayout) findViewById(R.id.activity_main);

        ll_left = (LinearLayout) findViewById(R.id.ll_left);

        ll_main = (LinearLayout) findViewById(R.id.ll_main);

        activity_main.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()){

                    case MotionEvent.ACTION_DOWN:

                        /*
                        *  通过宽度获取左边距,因为宽度和左边距相等
                        *  获取左边距
                        *  left 必须为负
                        */

                        if (isClick==false){

                            left=ll_left.getWidth();

                            left=left*(-1);

                            isClick=true;

                        }

                        x= (int) motionEvent.getX();

                        y= (int) motionEvent.getY();

                        break;

                    case MotionEvent.ACTION_MOVE:

                        distancex= (int) (motionEvent.getX()-x);

                        distancey= (int) (motionEvent.getY()-y);

                        /*
                        * 如何判断当前是水平滑动还是垂直滑动
                        * distancex 绝对值大于 distancey 水平滑动
                        */

                        if (Math.abs(distancex)>Math.abs(distancey)){

                            /*
                            *  判断是向左滑动还是向右滑动
                            *  distancex 大于0 向右滑动, distancex 小于0 向左滑动
                            */

                            if (distancex>0){

                                /*
                                * 向右滑动的时候 左边距等于 控件的宽度 减去 滑动的距离 distancex
                                *
                                */

                                left+=speed;
                                //左边距变为负的
                                /*
                                * left 如果大于0 就设置 0
                                *
                                * */

                                if (left>0){

                                    left=0;

                                }

                            }else {

                                /*
                                *  向左滑动
                                *  左边距变为负值
                                */

                                left-=speed;

                                if (left < ll_left.getWidth()*(-1)){

                                    left=ll_left.getWidth()*(-1);

                                }

                            }

                            LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) ll_left.getLayoutParams();

                            params.leftMargin=left;

                            ll_left.setLayoutParams(params);

                        }

                        break;

                    case MotionEvent.ACTION_UP:

                        /*
                        * 抬起的时候,首先判断是左滑动还是右滑动
                        * 根据 distancex 判断,如果大于0 是向右滑动,如果小于 0 向左滑动
                        */

                            /*
                            * 向右滑动的时候如果划出来的左边宽度小于整体宽度的一半的时候,设置自定弹回去
                            * 向左滑动的时候,如果进去的宽度大于一半,设置宽度为宽度,否则反之
                            */

                        if (Math.abs(left)>ll_left.getWidth()/2){

                            left=ll_left.getWidth()*(-1);

                        }else {

                            left=0;

                        }

                        LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) ll_left.getLayoutParams();

                        params.leftMargin=left;

                        ll_left.setLayoutParams(params);

                        break;

                }

                return true;
            }
        });

    }
}
