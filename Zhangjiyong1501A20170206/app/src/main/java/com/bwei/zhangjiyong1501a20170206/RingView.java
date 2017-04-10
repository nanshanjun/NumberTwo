package com.bwei.zhangjiyong1501a20170206;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * 作者： 南山君
 * 时间：2017/2/6.9:41
 */

public class RingView extends View {

    Paint paint;
    Paint tpaint;
   Context context;

    CircleDown circleDown;

    int center=0;
    int innerCircle;
    int ringWidth;
    int ornerCircle;
    int ring;

    int ringColor;
        int    ringWidths;
    int innerCircleWidth;
        int     ornerCircleWidth;
    int textSize;
    int paintColor;

    public RingView(Context context) {

        // TODO Auto-generated constructor stub
        this(context, null);
    }

    public RingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.paint = new Paint();
        this.paint.setColor(paintColor);
        this.paint.setAntiAlias(true); //消除锯齿
        this.paint.setStyle(Paint.Style.STROKE); //绘制空心圆
    }

    public RingView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);

        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.Ring);

        //获取自定义属性和默认值
        ringColor = mTypedArray.getColor(R.styleable.Ring_ringColor, Color.GREEN);
        paintColor = mTypedArray.getColor(R.styleable.Ring_ringColor, Color.GREEN);
        ringWidths = (int) mTypedArray.getDimension(R.styleable.Ring_ringWidth, 5);
        innerCircleWidth = (int) mTypedArray.getDimension(R.styleable.Ring_innerCircleWidth, 83);
        textSize = (int) mTypedArray.getDimension(R.styleable.Ring_textSize, 15);
        ornerCircleWidth = (int) mTypedArray.getDimension(R.styleable.Ring_ornerCircleWidth, 88);

        mTypedArray.recycle();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub

        center = getWidth()/2;
        innerCircle = dip2px(context, 83); //设置内圆半径
        ringWidth = dip2px(context, 5); //设置圆环宽度
        ornerCircle=innerCircle+ringWidth;//设置外圆半径
        ring=innerCircle+1+ringWidth/2;

        //绘制内圆
        this.paint.setColor(paintColor);
        this.paint.setARGB(155, 167, 190, 206);
        this.paint.setStrokeWidth(2);
        canvas.drawCircle(center,center, innerCircleWidth, this.paint);

        //绘制圆环
        this.paint.setColor(paintColor);
        this.paint.setARGB(255, 212 ,225, 233);
        this.paint.setStrokeWidth(ringWidth);
        canvas.drawCircle(center,center, innerCircleWidth+1+ringWidths/2, this.paint);

        //绘制外圆
        this.paint.setColor(paintColor);
        this.paint.setARGB(155, 167, 190, 206);
        this.paint.setStrokeWidth(2);
        canvas.drawCircle(center,center, innerCircleWidth+ringWidths, this.paint);

        tpaint=new Paint();

        tpaint.setColor(Color.BLACK);

        tpaint.setStrokeWidth(2);

        tpaint.setTextSize(textSize);

        int twidth= (int) tpaint.measureText("圆环");

        canvas.drawText("圆环",(getWidth()-twidth)/2,(getHeight()+15)/2,tpaint);

        super.onDraw(canvas);
    }
    public void addMessage(){

        Toast.makeText(getContext(),"在圆内",Toast.LENGTH_SHORT).show();

    }

    public void addMessaget(){

        Toast.makeText(getContext(),"在圆环内",Toast.LENGTH_SHORT).show();

    }

    public void addMessagett(){

        Toast.makeText(getContext(),"在圆外",Toast.LENGTH_SHORT).show();

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction()==MotionEvent.ACTION_DOWN){

            int x= (int) event.getX();

            int y= (int) event.getY();

            if ((x-innerCircle)/2*(x-innerCircle)/2+(y-innerCircle)/2*(y-innerCircle)<innerCircle*innerCircle){

                Toast.makeText(getContext(),"在圆内",Toast.LENGTH_SHORT).show();

            }
            else if (innerCircle*innerCircle<(x-innerCircle)/2*(x-innerCircle)/2+(y-innerCircle)/2*(y-innerCircle)&&(x-ring)/2*(x-ring)/2+(y-ring)/2*(y-ring)<ring*ring){

                Toast.makeText(getContext(),"在圆环内",Toast.LENGTH_SHORT).show();

            }
            else if ((x-ornerCircle)/2*(x-ornerCircle)/2+(y-ornerCircle)/2*(y-ornerCircle)>ornerCircle*ornerCircle){

                Toast.makeText(getContext(),"在圆外",Toast.LENGTH_SHORT).show();

            }

        }

        return true;
    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void onMyCircleDownListener(CircleDown circleDown){

        this.circleDown=circleDown;

    }

    public interface CircleDown{

        public void CircleDown();

    }

}
