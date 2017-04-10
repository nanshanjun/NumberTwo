package com.bwei.saner;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 作者： 南山君
 * 时间：2016/12/22.14:11
 */

public class MyUtils {

    public String getMessage(){

        try {

            URL url=new URL("http://mock.eoapi.cn/success/cjTiF8H7R6Dw4fmRsuUS3H7ZPaVUJzRW");

            HttpURLConnection conn= (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            conn.setConnectTimeout(5000);

            int responseCode = conn.getResponseCode();

            if (responseCode==200){

                int len=0;

                byte[] arr=new byte[1024];

                ByteArrayOutputStream baos=new ByteArrayOutputStream();

                InputStream is = conn.getInputStream();

                while((len=is.read(arr))!=-1){

                    baos.write(arr,0,len);

                }

                return baos.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

}
