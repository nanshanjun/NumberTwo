package com.bwei.testdown;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者： 南山君
 * 时间：2016/12/28.18:35
 */

public class CirCle extends View {
    //画整个圆圈的画笔
    Paint paint;
    //画当前进度条的画笔
    Paint cpaint;

    Paint percentpaint;

    //最大进度
    int max = 360;
    //当前进度
    int current;
    //文字大小
    float textSize = 20;

    public void addRest(int max,int current){

        this.max=max;
        this.current=current;
        //发送到子线程
        postInvalidate();

    }

    public CirCle(Context context) {
        super(context);
    }

    public CirCle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CirCle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (getWidth()==0||getHeight()==0){

            return;

        }

        MessageDown(canvas);

    }
    //进度条
    public void MessageDown(Canvas canvas){
        //实例化整个圆的画笔
        paint=new Paint();
        //设置画笔颜色
        paint.setColor(Color.BLACK);
        //设置抗锯齿
        paint.setAntiAlias(true);
        //设置画笔宽度
        paint.setStrokeWidth(5);
        //设置空心圆
        paint.setStyle(Paint.Style.STROKE);

        //实例化进度条圆的画笔
        cpaint = new Paint();
        cpaint.setColor(Color.RED);
        cpaint.setStrokeWidth(5);
        cpaint.setAntiAlias(true);
        cpaint.setStyle(Paint.Style.STROKE);

        //实例化进度条数据的画笔
        percentpaint = new Paint();
        percentpaint.setTextSize(textSize);
        percentpaint.setStrokeWidth(2);
        percentpaint.setColor(Color.BLACK);

        //获得圆心位置
        int center=getWidth()/2;
        //获得半径
        int radio=(getWidth()-20)/2;
        //绘制圆形
        canvas.drawCircle(getWidth()/2,getHeight()/2,radio,paint);
        //设置rect
        RectF rect=new RectF(center-radio,center-radio,center+radio,center+radio);
        //绘制当前进度的圆形
        canvas.drawArc(rect,0,360*current/max,false,cpaint);
        //进度条百分比
        int position= (int) ((current*1.0/max*1.0)*100);

        float textwidth=paint.measureText(position+"%");
        //绘制文字
        canvas.drawText(position+"%",center-getWidth()/2,center+getWidth()/2,percentpaint);

    }
}
