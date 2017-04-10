package com.bwei.eggplantdoctor.utils;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * 作者： 南山君
 * 时间：2017/1/10.9:08
 */

public class OkHttpSingleTop {
    //声明对象
    CallBackM callBackM;
    //声明对象 私有化
    private static volatile OkHttpSingleTop okHttpSingleTop;
    //解析对象
    private final Gson gson;
    //获取主线程 handler
    private final Handler handler;
    //构造函数 私有化
    private OkHttpSingleTop(){

        handler = new Handler(Looper.getMainLooper());

        gson = new Gson();

    }
    // 提供公共方法
    public static OkHttpSingleTop getInstance(){

        if (null==okHttpSingleTop){

            synchronized (OkHttpSingleTop.class){

                if (null==okHttpSingleTop){

                    okHttpSingleTop=new OkHttpSingleTop();

                }

            }

        }

        return okHttpSingleTop;

    }
    /*
    * 拼接 url的方法
    *
    */
    public <T> void setUrl(String url, Map<String,String> map, Class<T> cls, Methods methods){

        switch (methods){

            case GET:

                get(url,cls,map);

                break;

            case PSOT:
                break;

        }

    }
    /*
    * get 方法
    */

    public <T> void get(String url,Class<T> cls,Map<String,String> map){

        int i=0;
        // 遍历
        Iterator<String> iterator=map.keySet().iterator();

        while (iterator.hasNext()){

            String key=iterator.next();

            String values=map.get(key);

            if (i==0){

                url+=key+"="+values;

            }else {

                url+="&"+key+"="+values;

            }

            i++;

        }

        getUrl(url,cls);

    }
    // get  请求
    public <T> void getUrl(String url, final Class<T> cls){
        //创建okHttpClient对象
        OkHttpClient okHttpClient=new OkHttpClient();
        //创建一个Request
        Request request=new Request.Builder().url(url).build();
        //new call
        Call call=okHttpClient.newCall(request);
        //回调接口
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

                if (null==callBackM){

                    callBackM.onFailure(request,e);

                }

            }

            @Override
            public void onResponse(Response response) throws IOException {

                String json=response.body().string();

                getJson(json,cls);

            }
        });

    }
    /*
*  post 方法
*  url 请求网络的地址
*  map post 请求的参数
*
*/
    public <T> void Post(String url, Map<String,String> map, final Class<T> cls){

        OkHttpClient okHttpClient=new OkHttpClient();

        FormEncodingBuilder formEncodingBuilder=new FormEncodingBuilder();

        Iterator<String> iterator=map.keySet().iterator();

        while(iterator.hasNext()){

            String key=iterator.next();

            String values=map.get(key);

            formEncodingBuilder.add(key,values);

        }

        Request request=new Request.Builder().url(url).post(formEncodingBuilder.build()).build();

        final Call call=okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

                if (null==callBackM){

                    callBackM.onFailure(request, e);

                }

            }

            @Override
            public void onResponse(Response response) throws IOException {

                String json=response.body().string();

                getJson(json,cls);

            }
        });

    }
    /*
    * GSON 解析
    *
    * */
    public <T> void getJson(String json,Class<T> cls){

        T t1=gson.fromJson(json,cls);

        MainThread(t1);

    }

    public void MainThread(final Object reponse){

        handler.post(new Runnable() {
            @Override
            public void run() {

                if (null==callBackM){

                    callBackM.onResponse(reponse);

                }

            }
        });

    }

    //设置监听
    public void setCallBackMListener(CallBackM callBackM){

        this.callBackM=callBackM;

    }

    //观察者模式
    public interface CallBackM{

        public void onFailure(Request request, IOException e);

        public void onResponse(Object response);

    }

        /*
    *  定义枚举类型
    *  GET PSOT DOWN
    *  关键字段 enum
    *
    */

    public enum Methods{

        GET,PSOT,DOWN
    }

}
