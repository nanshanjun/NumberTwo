package com.bwei.zhangjiyong1501a20161219.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 作者： 南山君
 * 时间：2016/12/19.9:44
 */

public class MyInternetUtils{

    public String getMessage(){

        String path="http://mock.eoapi.cn/success/gIbgeNycc15SNh5mrSNTGC2Tr5WTUsCM";

        try {

            URL url=new URL(path);
            // 此处的urlConnection对象实际上是根据URL的
            // 请求协议(此处是http)生成的URLConnection类
            // 的子类HttpURLConnection,故此处最好将其转化
            // 为HttpURLConnection类型的对象,以便用到
            // HttpURLConnection更多的API.如下:

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

// 设定请求的方法为"POST"，默认是GET
            conn.setRequestMethod("GET");

            conn.setConnectTimeout(1000*5);

            conn.connect();

            //获得响应码
            int responseCode = conn.getResponseCode();

            if (responseCode==200){

                InputStream is = conn.getInputStream();

                int len=0;

                byte[] arr=new byte[1024];

                ByteArrayOutputStream baos=new ByteArrayOutputStream();

                while((len=is.read(arr))!=-1){

                    baos.write(arr,0,len);

                }
                //返回json串

                return baos.toString();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

        }
}
