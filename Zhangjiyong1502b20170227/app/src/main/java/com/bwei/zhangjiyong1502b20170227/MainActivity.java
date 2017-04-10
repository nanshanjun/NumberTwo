package com.bwei.zhangjiyong1502b20170227;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.bwei.zhangjiyong1502b20170227.entity.AllMessage;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private MyAdapter my;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//初始化控件
        lv = (ListView) findViewById(R.id.lv);
//获得数据
        initDate();

    }

    private void initDate() {

        String url = "http://www.yulin520.com/a2a/impressApi/news/mergeList?sign=C7548DE604BCB8A17592EFB9006F9265&pageSize=10&gender=2&ts=1871746850&page=1";

        HttpUtils http = new HttpUtils();

        http.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //获得json串
                String result = responseInfo.result;

                Gson gson = new Gson();

                AllMessage allMessage = gson.fromJson(result, AllMessage.class);

                Log.d("dddd",allMessage.getCode()+"");

                List<AllMessage.DataBean> data = allMessage.getData();

                Log.d("size",data.size()+"");

                my = new MyAdapter(MainActivity.this,data);

                lv.setAdapter(my);
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });

//        //创建okHttpClient对象
//        OkHttpClient mOkHttpClient = new OkHttpClient();
////创建一个Request
//        final Request request = new Request.Builder()
//                .url("http://www.yulin520.com/a2a/impressApi/news/mergeList?sign=C7548DE604BCB8A17592EFB9006F9265&pageSize=10&gender=2&ts=1871746850&page=1")
//                .build();
////new call
//        Call call = mOkHttpClient.newCall(request);
////请求加入调度
//        call.enqueue(new Callback()
//        {
//            @Override
//            public void onFailure(Request request, IOException e)
//            {
//            }
//
//            @Override
//            public void onResponse(final Response response) throws IOException
//            {
//                String htmlStr =  response.body().string();
//
//                Gson gson=new Gson();
//
//                AllMessage allMessage = gson.fromJson(htmlStr, AllMessage.class);
//
//                List<AllMessage.DataBean> data = allMessage.getData();
//
//                my.addRest(data);
//
//            }
//        });

    }
}
