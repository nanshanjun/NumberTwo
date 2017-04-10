package com.bwei.zhangjiyong1501a20170103;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 作者： 南山君
 * 时间：2017/1/3.8:56
 */

public class Circle_th extends View {

    //声明画笔
    Paint paint;
    Paint tpaint;

    CircleDown circleDown;

    int message=1000;

    public Circle_th(Context context) {
        super(context);
    }

    public Circle_th(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Circle_th(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画圆
        DrawCircle(canvas);

    }

    public void DrawCircle(Canvas canvas){

        paint=new Paint();
        //画笔宽度
        paint.setStrokeWidth(50);
        //空心圆
        paint.setStyle(Paint.Style.STROKE);

        paint.setAntiAlias(true);
        //颜色
        paint.setColor(Color.BLUE);
        //圆形圆心
        int width=getWidth()/2;

        int height=getHeight()/2;
        //半径
        int radio=(getWidth()-50)/2;
        //画圆
        canvas.drawCircle(width,height,radio,paint);

        tpaint=new Paint();
        //文字颜色
        tpaint.setColor(Color.BLACK);
        //文字字体宽度
        tpaint.setStrokeWidth(2);
        //文字大小
        tpaint.setTextSize(15);

        int twidth= (int) tpaint.measureText("1000");

        //绘制文字
        canvas.drawText(message+"", (getWidth()-twidth)/2, (getHeight()+15)/2, tpaint);

    }
    //获得随机数
    public void addMessage(){

        int random= 1000+(int)(Math.random()*8999);

        message=random;

    }
    //点击事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction()==MotionEvent.ACTION_DOWN){

            int x= (int) event.getX();
            int y= (int) event.getY();
            //判断是否在圆形内
            if ((x-getWidth()/2)*(x-getWidth()/2)+(y-getHeight()/2)*(y-getHeight()/2)<((getWidth()-20)/2)*((getWidth()-20)/2)){

                addMessage();
                //传递给子线程进行操作
                postInvalidate();

            }

        }

        return true;
    }

    public void onMyCircleDownListener(CircleDown circleDown){

        this.circleDown=circleDown;

    }

    public interface CircleDown{

       public void CircleDon();

    }
}
