package com.bwei.apkupdate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者： 南山君
 * 时间：2016/12/30.10:39
 */

public class CieCle extends View {

    Paint Bpaint;

    Paint Rpaint;

    Paint Wpaint;

    int max = 360;

    int current;

    float textSize = 20;

    public void add(int max,int current){

        this.max=max;
        this.current=current;

        postInvalidate();

    }

    public CieCle(Context context) {
        super(context);
    }

    public CieCle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CieCle(Context context, AttributeSet attrs, int defStyleAttr) {
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

        CirClePaint(canvas);

    }

    public void CirClePaint(Canvas canvas){

        Bpaint=new Paint();

        Bpaint.setColor(Color.BLACK);

        Bpaint.setStrokeWidth(5);

        Bpaint.setAntiAlias(true);

        Bpaint.setStyle(Paint.Style.STROKE);

        Rpaint=new Paint();

        Rpaint.setColor(Color.RED);

        Rpaint.setStrokeWidth(5);

        Rpaint.setAntiAlias(true);

        Rpaint.setStyle(Paint.Style.STROKE);

        Wpaint=new Paint();

        Wpaint.setTextSize(textSize);

        Wpaint.setStrokeWidth(2);

        Wpaint.setColor(Color.BLACK);

        int center=getWidth()/2;

        int radio=(getWidth()-10)/2;

        canvas.drawCircle(getWidth()/2,getHeight()/2,radio,Bpaint);

        RectF rectF=new RectF(center-radio,center-radio,center+radio,center+radio);

        canvas.drawArc(rectF,0,360*current/max,false,Rpaint);

        int point= (int) ((current*1.0/max*1.0)*100);

        float textwidth= Bpaint.measureText(point+"%");

        canvas.drawText(point+"%",getWidth()/2,getWidth()/2,Wpaint);
    }

}
