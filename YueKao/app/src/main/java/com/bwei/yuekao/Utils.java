package com.bwei.yuekao;

import java.net.URL;

/**
 * 作者： 南山君
 * 时间：2016/12/22.16:58
 */

public class Utils {

    public String getMessage(String path){

        try {

            URL url=new URL(path);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

}
