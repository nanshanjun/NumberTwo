package com.bwei.gesturepassword;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： 南山君
 * 时间：2017/1/13.19:51
 */

public class GesturePassWord extends View {

    //画笔颜色
    public final static int Before_Color= Color.parseColor("#000000");

    public final static int After_Color=Color.parseColor("#00ffff");

    //圆形的集合
    List<Circle> circleList=new ArrayList<>();

    //画线的集合
    List<Circle> linelist=new ArrayList<>();

    //宽、高、半径
    int height;
    int width;
    int radio;

    //声明画笔
    Paint paint;

    public GesturePassWord(Context context) {
        super(context);
    }

    public GesturePassWord(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GesturePassWord(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        height=getHeight();

        width=getWidth();

        if (height==0&&width==0){

            return;

        }

        radio=width/10;

        addList();

        Circle(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_MOVE:

                move(event);

                break;

            case MotionEvent.ACTION_UP:

                up();

                break;

        }

        return true;
    }

    //抬起的方法
    public void up(){

        String password="";

        for (int i = 0; i < linelist.size(); i++) {

            password+=linelist.get(i).number+"";

        }

        Toast.makeText(getContext(),"密码是"+password,Toast.LENGTH_SHORT).show();

        //清理屏幕
        for (int i = 0; i < circleList.size(); i++) {

            circleList.get(i).color=Before_Color;

            circleList.get(i).isSelect=false;

        }
        //清空集合
        linelist.clear();
        //刷新UI
        postInvalidate();

    }

    //移动的方法
    public void move(MotionEvent event){

        int x= (int) event.getX();

        int y= (int) event.getY();

        for (int i = 0; i < circleList.size(); i++) {

            int xpow= (int) Math.pow(x-circleList.get(i).x,2);

            int ypow= (int) Math.pow(y-circleList.get(i).y,2);

            int sumpow=xpow+ypow;
            //三角函数计算
            if (sumpow<=Math.pow(radio,2)){
                //将状态设置为选中
                circleList.get(i).isSelect=true;
                //画笔的颜色
                circleList.get(i).color=After_Color;
                //设置判断避免加重复
                boolean isFlag=false;

                for (int j = 0; j < linelist.size(); j++) {
                    //通过数字判断存在不存在
                    if (linelist.get(j).number==circleList.get(i).number){

                        isFlag=true;

                    }

                }
                //如果不存在就加入
                if (isFlag==false){

                    Circle circle=new Circle();

                    circle.x=circleList.get(i).x;

                    circle.y=circleList.get(i).y;

                    circle.color=circleList.get(i).color;

                    circle.number=circleList.get(i).number;

                    linelist.add(circle);

                }
                //刷新UI
                postInvalidate();

            }

        }

    }

    //添加数据
    public void addList(){

        for (int i = 1; i <= 9; i++) {
            
            Circle circle=new Circle();

            if (i<=3){

                circle.y=height/2-radio*3;

            }else if (i>3&&i<=6){

                circle.y=height/2;

            }else {

                circle.y=height/2+radio*3;

            }

            if (i==1||i==4||i==7){

                circle.x=radio*2;

            }else if (i==2||i==5||i==8){

                circle.x=radio*5;

            }else {

                circle.x=radio*8;

            }

            circle.color=Before_Color;

            circle.radio=radio;

            circle.isSelect=false;

            circle.number=i;

            circleList.add(circle);

        }

    }

    //绘制圆
    public void Circle(Canvas canvas){

        //实例化画笔
        paint=new Paint();
        //设置笔的宽度
        paint.setStrokeWidth(3);
        //设置抗锯齿
        paint.setAntiAlias(true);
        //设置圆形的类型
        paint.setStyle(Paint.Style.STROKE);

        for (int i = 0; i < circleList.size(); i++) {

            paint.setColor(circleList.get(i).color);

            canvas.drawCircle(circleList.get(i).x,circleList.get(i).y,circleList.get(i).radio, paint);

        }

        boolean isFirst=false;

        int startx=0;

        int starty=0;

        //画线的方法
        for (int i = 0; i < linelist.size(); i++) {

            if (isFirst==false){

                isFirst=true;

                startx=linelist.get(i).x;

                starty=linelist.get(i).y;

            }else {

                paint.setColor(linelist.get(i).color);

                canvas.drawLine(startx,starty,linelist.get(i).x,linelist.get(i).y,paint);

                startx=linelist.get(i).x;

                starty=linelist.get(i).y;

            }

        }

    }

}
