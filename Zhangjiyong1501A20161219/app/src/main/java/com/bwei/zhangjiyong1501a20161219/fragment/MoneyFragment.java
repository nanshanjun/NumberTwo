package com.bwei.zhangjiyong1501a20161219.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.bwei.zhangjiyong1501a20161219.R;
import com.bwei.zhangjiyong1501a20161219.adapter.MoneyGridviewAdapter;
import com.bwei.zhangjiyong1501a20161219.entity.AllMessage;
import com.bwei.zhangjiyong1501a20161219.entity.NeedMessage;
import com.bwei.zhangjiyong1501a20161219.utils.MyInternetUtils;
import com.google.gson.Gson;

import java.util.List;

/**
 * 作者： 南山君
 * 时间：2016/12/19.9:11
 */

public class MoneyFragment extends Fragment {

    private View view;
    private GridView gv_fragment_message;
    private MoneyGridviewAdapter moneyGridviewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载布局
        view= inflater.inflate(R.layout.fragment_money,null);
        
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获得控件
        gv_fragment_message = (GridView) view.findViewById(R.id.gv_fragment_message);

        new AsyncTask<Void,Void,String>(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(Void... voids) {
                //获得json信息
                MyInternetUtils myInternetUtils=new MyInternetUtils();

                String json = myInternetUtils.getMessage();

                Log.d("json",json);

                return json;
            }
            //解析json串
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                Gson gson=new Gson();

                AllMessage allMessage = gson.fromJson(s, AllMessage.class);

                List<NeedMessage> data = allMessage.data;

                Log.d("dddd----",data.size()+"");

                //适配器
                moneyGridviewAdapter= new MoneyGridviewAdapter(data,getActivity());
                //设置适配器
                gv_fragment_message.setAdapter(moneyGridviewAdapter);

            }

        }.execute();

//        myMessage mm=new myMessage();
//
//        mm.execute();

    }
    //使用AsyncTask+HttpURLConnection访问网络,请求json数据
    class myMessage extends AsyncTask<Void,Void,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            //获得json信息
            MyInternetUtils myInternetUtils=new MyInternetUtils();

            String json = myInternetUtils.getMessage();

            Log.d("json",json);

            return json;
        }
        //解析json串
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Gson gson=new Gson();

            AllMessage allMessage = gson.fromJson(s, AllMessage.class);

            List<NeedMessage> data = allMessage.data;

            Log.d("dddd----",data.size()+"");

            //适配器
            moneyGridviewAdapter= new MoneyGridviewAdapter(data,getActivity());
            //设置适配器
            gv_fragment_message.setAdapter(moneyGridviewAdapter);

        }
    }
}
