package com.bwei.yuekao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * 作者： 南山君
 * 时间：2016/12/22.16:26
 */

public class NewsFragment extends Fragment {

    private View view;
    private ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_news,null);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lv = (ListView) view.findViewById(R.id.lv);

        new Thread(){

            @Override
            public void run() {
                super.run();



            }
        }.start();

    }
}
