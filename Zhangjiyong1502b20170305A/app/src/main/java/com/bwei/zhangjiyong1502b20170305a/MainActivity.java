package com.bwei.zhangjiyong1502b20170305a;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bwei.zhangjiyong1502b20170305a.adapter.DownAdapter;
import com.bwei.zhangjiyong1502b20170305a.adapter.LeftAdapter;
import com.bwei.zhangjiyong1502b20170305a.adapter.TopAdapter;
import com.bwei.zhangjiyong1502b20170305a.bean.MyMessage;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rc_left;
    private RecyclerView rc_top;
    private RecyclerView rc_down;
    private TextView tv_down;
    private TextView tv_top;
    private List<MyMessage.RsBean.ChildrenBeanX> children;
    private LeftAdapter la;
    private TopAdapter ta;
    private DownAdapter da;
    private List<MyMessage.RsBean> rs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        rc_top.setAdapter(ta);

        rc_down.setAdapter(da);

        LinearLayoutManager llm=new LinearLayoutManager(this);

        rc_left.setLayoutManager(llm);

        GridLayoutManager glmt=new GridLayoutManager(this,3);

        rc_top.setLayoutManager(glmt);

        GridLayoutManager glmd=new GridLayoutManager(this,3);

        rc_down.setLayoutManager(glmd);

        initData();

    }

    private void initData() {

        String url="http://mock.eoapi.cn/success/4q69ckcRaBdxhdHySqp2Mnxdju5Z8Yr4";

        OkHttpManager.getAsync(url, new OkHttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {

                Gson gson=new Gson();

                MyMessage myMessage = gson.fromJson(result, MyMessage.class);

                rs = myMessage.getRs();

                la.addRest(rs);

                rc_left.setAdapter(la);

                la.setOnItemClickLitener(new LeftAdapter.OnItemClickLitener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        if (rs.get(position).getChildren().size()>1) {

                            tv_top.setText(rs.get(position).getChildren().get(0).getDirName());

                            tv_down.setText(rs.get(position).getChildren().get(1).getDirName());

                            ta.addRest(rs.get(position).getChildren().get(0).getChildren());

                            da.addRest(rs.get(position).getChildren().get(1).getChildren());

                        }else {

                            tv_top.setText(rs.get(position).getChildren().get(0).getDirName());

                            ta.addRest(rs.get(position).getChildren().get(0).getChildren());

                        }

                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });

            }
        });

    }

    private void initView() {

        rc_left = (RecyclerView) findViewById(R.id.rc_left);

        rc_top = (RecyclerView) findViewById(R.id.rc_top);

        rc_down = (RecyclerView) findViewById(R.id.rc_down);

        tv_top = (TextView) findViewById(R.id.tv_top);

        tv_down = (TextView) findViewById(R.id.tv_down);

        la = new LeftAdapter(MainActivity.this);

        ta = new TopAdapter(MainActivity.this);

        da = new DownAdapter(MainActivity.this);

    }
}
