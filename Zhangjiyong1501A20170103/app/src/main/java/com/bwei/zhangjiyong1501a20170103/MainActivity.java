package com.bwei.zhangjiyong1501a20170103;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Circle circle_one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circle_one = (Circle) findViewById(R.id.circle_one);

//        circle_one.onMyCircleDownListener(new Circle.CircleDown() {
//            @Override
//            public void CircleDon() {
//
//                int random= 1000+(int)(Math.random()*8999);
//
//                circle_one.addMessage(random+"");
//
//            }
//        });

    }
}
