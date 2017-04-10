package com.bwei.xiangmueryuekao;

import android.content.Context;
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
 * 时间：2017/2/13.19:45
 */

public class Circle extends View {

    Paint paint=new Paint();
    private Region rectregion;
    private Region circleregion;
    private Path rectpath;
    private Path circlepath;
    private Region inregion;
    private Path inpath;

    public Circle(Context context) {
        super(context);

        initView(context,null);

    }

    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context, attrs);

    }

    private void initView(Context context, AttributeSet attrs) {

        paint.setColor(Color.RED);

        rectregion = new Region();

        circleregion = new Region();

        inregion = new Region();

        rectpath = new Path();

        circlepath = new Path();

        inpath = new Path();

    }

    public Circle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        paint.setColor(Color.BLUE);

        rectpath.addRect(20,20,200,200,Path.Direction.CW);

        circlepath.addCircle(110,110,90,Path.Direction.CW);

        inpath.addCircle(110,110,40, Path.Direction.CW);

        // ▼将剪裁边界设置为视图大小
        Region region = new Region(-w,-h,w,h);
        // ▼将 Path 添加到 Region 中
        rectregion.setPath(rectpath, region);

        circleregion.setPath(circlepath, region);

        inregion.setPath(inpath, region);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

                int x = (int) event.getX();
                int y = (int) event.getY();

                if (inregion.contains(x, y)) {

                    Toast.makeText(this.getContext(), "内圆被点击", Toast.LENGTH_SHORT).show();

                }else if (circleregion.contains(x, y)) {

                    Toast.makeText(this.getContext(), "圆被点击", Toast.LENGTH_SHORT).show();

                } else if (rectregion.contains(x, y)) {

                    Toast.makeText(this.getContext(), "矩形被点击", Toast.LENGTH_SHORT).show();

                }  else {

                    Toast.makeText(this.getContext(), "空白被点击", Toast.LENGTH_SHORT).show();

                }

                break;

        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint ipaint=new Paint();

        ipaint.setColor(Color.WHITE);

        Path rect=rectpath;

        Path circle=circlepath;

        Path ipath=inpath;

        canvas.drawPath(rect,paint);

        canvas.drawPath(circle,new Paint());

        canvas.drawPath(ipath,ipaint);

    }
}
