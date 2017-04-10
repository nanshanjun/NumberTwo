package com.bwei.zhangjiyong1502b20170213;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 作者： 南山君
 * 时间：2017/2/13.8:58
 */

public class Circle extends View {

    private float outRadius;
    private float inRadius;
    private int textSize;
    private int circleColor;
    private int texrColor;
    int message=1000;

    public Circle(Context context) {
        super(context);

        initData(context,null);

    }

    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);

        initData(context, attrs);

    }

    private void initData(Context context, AttributeSet attrs) {
        //获得自定义属性
        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.myView);
        //外圆半径
        outRadius = ta.getFloat(R.styleable.myView_outCircleRadius, 60);
        //内圆半径
        inRadius = ta.getFloat(R.styleable.myView_inCircleRadius, 30);
        //字体大小
        textSize = ta.getInteger(R.styleable.myView_textSize, 15);
        //圆环颜色
        circleColor = ta.getColor(R.styleable.myView_circleColor, Color.RED);
        //字体颜色
        texrColor = ta.getColor(R.styleable.myView_textColor, Color.BLACK);

        ta.recycle();

    }

    public Circle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //实例化画笔
        Paint opaint=new Paint();
        //设置抗锯齿
        opaint.setAntiAlias(true);
        //设置圆形类型
        opaint.setStyle(Paint.Style.FILL);
        //画笔颜色
        opaint.setColor(circleColor);
        //画外圆
        canvas.drawCircle(100,100,outRadius,opaint);
        //实例化画笔
        Paint ipaint=new Paint();
        //设置抗锯齿
        ipaint.setAntiAlias(true);
        //设置圆形类型
        ipaint.setStyle(Paint.Style.FILL);
        //画笔颜色
        ipaint.setColor(Color.WHITE);
        //画圆
        canvas.drawCircle(100,100,inRadius,ipaint);
        //实例化画笔
        Paint tpaint=new Paint();
        //设置画笔宽度
        tpaint.setStrokeWidth(2);
        //画笔颜色
        tpaint.setColor(texrColor);

        Paint.FontMetrics fontMetrics=tpaint.getFontMetrics();

        float dy=(fontMetrics.descent-fontMetrics.ascent)/2-fontMetrics.descent;

        float Texty=100+dy;
        //写字
        canvas.drawText(message+"",100-tpaint.measureText(message+"")/2,Texty,tpaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            //触屏按下时
            case MotionEvent.ACTION_DOWN:
                //获得点的X坐标
                int x = (int) event.getX();
                //获得点的Y坐标
                int y = (int) event.getY();
                //判断是否在园内
                if ((x-100)*(x-100)+(y-100)*(y-100)< outRadius*outRadius &&(x-100)*(x-100)+(y-100)*(y-100)> inRadius*inRadius){
                    //获得随机数
                    addMessage();
                    //刷新    ui
                    postInvalidate();

                }

                break;

        }

        return true;
    }

    //获得随机数

    public void addMessage(){

        int random= 1000+(int)(Math.random()*8999);

        message = random;

    }



}
