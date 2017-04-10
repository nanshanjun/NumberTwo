package com.bwei.testdown;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    CirCle cirCle;

    int max=360;

    int current=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        cirCle= (CirCle) findViewById(R.id.cc_main_mine);

        new Thread(){

            @Override
            public void run() {
                super.run();

                while(current<=360){

                    current++;

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    cirCle.addRest(max,current);

                }

            }
        }.start();

    }
}
