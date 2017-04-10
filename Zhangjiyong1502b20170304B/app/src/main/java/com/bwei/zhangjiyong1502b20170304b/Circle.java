package com.bwei.zhangjiyong1502b20170304b;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * 作者： 南山君
 * 时间：2017/3/4.10:53
 */

public class Circle extends View {

    Paint paint=new Paint();
    private Region rectRegion;
    private Region inRegion;
    private Region outRegion;
    private Path rectPath;
    private Path inPath;
    private Path outPath;
    private int textsize;
    private int incircle;
    private int outcircle;
    private int inpaint;
    private int outpaint;

    public Circle(Context context) {
        super(context);

        init(context,null);

    }

    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.myCircle);

        textsize = ta.getInteger(R.styleable.myCircle_textSize, 20);

        incircle = ta.getInteger(R.styleable.myCircle_inCircle, 40);

        outcircle = ta.getInteger(R.styleable.myCircle_outCircle, 60);

        inpaint = ta.getColor(R.styleable.myCircle_inPaint, Color.YELLOW);

        outpaint = ta.getColor(R.styleable.myCircle_outPaint, Color.RED);

        ta.recycle();

        rectRegion = new Region();

        inRegion = new Region();

        outRegion = new Region();

        rectPath = new Path();

        inPath = new Path();

        outPath = new Path();

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

        Path rect=rectPath;

        Path in=inPath;

        Path out=outPath;

        paint.setColor(Color.BLUE);

        canvas.drawPath(rect,paint);

        paint.setColor(outpaint);

        canvas.drawPath(out,paint);

        paint.setColor(inpaint);

        canvas.drawPath(in,paint);

        Paint tpaint=new Paint();

        tpaint.setColor(Color.BLACK);

        tpaint.setStrokeWidth(2);

        tpaint.setTextSize(15);

        int twidth= (int) tpaint.measureText("圆环");

        canvas.drawText("圆环",(220-twidth)/2,(220+15)/2,tpaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

                int x= (int) event.getX();

                int y= (int) event.getY();

                if (inRegion.contains(x,y)){

                    Toast.makeText(this.getContext(), "内圆被点击", Toast.LENGTH_SHORT).show();

                }else if (outRegion.contains(x,y)){

                    Toast.makeText(this.getContext(), "圆环被点击", Toast.LENGTH_SHORT).show();

                }else if (rectRegion.contains(x,y)){

                    Toast.makeText(this.getContext(), "正方形被点击", Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(this.getContext(), "空白被点击", Toast.LENGTH_SHORT).show();

                }

                break;

        }

        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        rectPath.addRect(20,20,200,200, Path.Direction.CW);

        inPath.addCircle(110,110,30, Path.Direction.CW);

        outPath.addCircle(110,110,outcircle, Path.Direction.CW);

        Region region=new Region(-w,-h,w,h);

        rectRegion.setPath(rectPath,region);

        inRegion.setPath(inPath,region);

        outRegion.setPath(outPath,region);

    }
}
