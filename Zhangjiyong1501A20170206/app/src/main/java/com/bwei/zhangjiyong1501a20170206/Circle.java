package com.bwei.zhangjiyong1501a20170206;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * 作者： 南山君
 * 时间：2017/2/6.8:58
 */

public class Circle extends View {

    //声明画笔
    Paint paint;
    Paint tpaint;

    CircleDown circleDown;

    int message=1000;

    public Circle(Context context) {
        super(context);
    }

    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Circle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        DrawCircle(canvas);
    }

    public void DrawCircle(Canvas canvas){

        paint=new Paint();
        //设置抗锯齿
        paint.setAntiAlias(true);
        //设置空心圆
        paint.setStyle(Paint.Style.STROKE);
        //设置笔宽度
        paint.setStrokeWidth(50);
        //设置画笔颜色
        paint.setColor(Color.YELLOW);
        //圆形圆心
        int widht=getWidth()/2;

        int height=getHeight()/2;
        //半径
        int radio=(getWidth()-50)/2;
        //画圆
        canvas.drawCircle(widht,height,radio,paint);

        tpaint=new Paint();

        tpaint.setColor(Color.BLACK);

        tpaint.setStrokeWidth(2);

        tpaint.setTextSize(15);

        int twidth= (int) tpaint.measureText("圆环");

        canvas.drawText("圆环",(getWidth()-twidth)/2,(getHeight()+15)/2,tpaint);

    }

    public void addMessage(){

        Toast.makeText(getContext(),"在圆内",Toast.LENGTH_SHORT).show();

    }

    public void addMessaget(){

        Toast.makeText(getContext(),"在圆外",Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction()==MotionEvent.ACTION_DOWN){

            int x= (int) event.getX();

            int y= (int) event.getY();

            if ((x-getWidth())/2*(x-getWidth())/2+(y-getHeight())/2*(y-getHeight())<(getWidth()-20)/2*(getWidth()-20)/2){

                addMessage();

                postInvalidate();

            }else {

                addMessaget();

                postInvalidate();

            }

        }

        return true;
    }

    public void onMyCircleDownListener(CircleDown circleDown){

        this.circleDown=circleDown;

    }

    public interface CircleDown{

        public void CircleDown();

    }
}
