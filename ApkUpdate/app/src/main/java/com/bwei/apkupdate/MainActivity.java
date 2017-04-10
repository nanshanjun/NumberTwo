package com.bwei.apkupdate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pb;

//    File path;
//    //安装路径
//    File file;

    CieCle cc;

    int max=360;

    int current=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        pb = (ProgressBar) findViewById(R.id.pb);

        cc = (CieCle) findViewById(R.id.cc);

        new Thread(){

            @Override
            public void run() {
                super.run();

                while (current<=360){

                    current++;

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    cc.add(max,current);

                }

            }
        }.start();

    }
}
