package com.bwei.saner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.bwei.saner.adapter.ListviewAdapter;
import com.bwei.saner.entity.AllMessage;
import com.bwei.saner.entity.NeedImage;
import com.bwei.saner.entity.Needmessage;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private List<Needmessage> data;
    private List<NeedImage> img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);

        new Thread(){

            @Override
            public void run() {
                super.run();

                MyUtils my=new MyUtils();

                final String json=my.getMessage();

                Gson gson=new Gson();

                AllMessage allMessage = gson.fromJson(json, AllMessage.class);

                data =  allMessage.data;

                for (int i = 0; i <data.size() ; i++) {

                    img =  data.get(i).img;

                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        lv.setAdapter(new ListviewAdapter(MainActivity.this,img));

                    }
                });

            }
        }.start();
//
//        new AsyncTask<Void,Void,String>(){
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//            }
//
//            @Override
//            protected String doInBackground(Void... voids) {
//
//                MyUtils my=new MyUtils();
//
//                String json=my.getMessage();
//
//                return json;
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//
//                Gson gson=new Gson();
//
//                AllMessage allMessage = gson.fromJson(s, AllMessage.class);
//
//                List<Needmessage> data = allMessage.data;
//
////                for (int i = 0; i < data.size(); i++) {
////
////                    List<NeedImage> img = data.get(i).img;
////
////                    List<NeedBody> body = data.get(i).body;
////
////                }
//
//                lv.setAdapter(new ListviewAdapter(MainActivity.this,data));
//
//            }
//        }.execute();

    }
}
