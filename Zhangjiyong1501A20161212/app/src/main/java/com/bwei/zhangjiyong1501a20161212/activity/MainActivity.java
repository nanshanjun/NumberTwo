package com.bwei.zhangjiyong1501a20161212.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.bwei.zhangjiyong1501a20161212.R;
import com.bwei.zhangjiyong1501a20161212.adapter.ListviewAdapter;
import com.bwei.zhangjiyong1501a20161212.entity.AllMessage;
import com.bwei.zhangjiyong1501a20161212.entity.NeedMessage;
import com.bwei.zhangjiyong1501a20161212.utils.MyUtils;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到控件
        lv = (ListView)findViewById(R.id.lv_activity);

        String message=MyUtils.getMessage();

        Gson gson=new Gson();
        //获得json串信息
        AllMessage allMessage=gson.fromJson(message, AllMessage.class);

        List<NeedMessage> needMessages=allMessage.data;
        //listview的适配器
        ListviewAdapter listviewadapter=new ListviewAdapter(MainActivity.this);
        //传递信息
        listviewadapter.addList(needMessages);
        //设置适配器
        lv.setAdapter(listviewadapter);

    }
//使用AsyncTask+HttpURLConnection访问网络,请求json数据。
    private class MyAsynck extends AsyncTask<Void,Void,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            //使用HttpURLConnection访问网络
            String message=MyUtils.getMessage();

            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Gson gson=new Gson();
            //获得json串信息
            AllMessage allMessage=gson.fromJson(s, AllMessage.class);

            List<NeedMessage> needMessages=allMessage.data;
            //listview的适配器
            ListviewAdapter listviewadapter=new ListviewAdapter(MainActivity.this);
            //传递信息
            listviewadapter.addList(needMessages);
            //设置适配器
            lv.setAdapter(listviewadapter);

        }
    }
}
