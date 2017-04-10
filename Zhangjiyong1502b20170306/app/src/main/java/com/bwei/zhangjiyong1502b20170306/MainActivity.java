package com.bwei.zhangjiyong1502b20170306;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.zhangjiyong1502b20170306.adapter.DownAdapter;
import com.bwei.zhangjiyong1502b20170306.adapter.LeftAdapter;
import com.bwei.zhangjiyong1502b20170306.adapter.TopAdapter;
import com.bwei.zhangjiyong1502b20170306.entity.MyMessage;
import com.bwei.zhangjiyong1502b20170306.utils.OkHttpManager;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;

public class MainActivity extends AppCompatActivity{

    private TextView tv_down;
    private TextView tv_top;
    private RecyclerView rc_down;
    private RecyclerView rc_top;
    private RecyclerView rc_left;
    private List<MyMessage.RsBean> rs;
    private LeftAdapter la;
    private TopAdapter ta;
    private DownAdapter da;
    private List<MyMessage.RsBean.ChildrenBeanX> children;
    private List<MyMessage.RsBean.ChildrenBeanX.ChildrenBean> children1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 控件初始化
        initView();
        //线性布局管理器
        LinearLayoutManager llm=new LinearLayoutManager(this);
        //recycleview设置布局管理器
        rc_left.setLayoutManager(llm);
        //网格布局管理器
        GridLayoutManager glmtop=new GridLayoutManager(this,3);
        //recycleview设置布局管理器
        rc_top.setLayoutManager(glmtop);
        //网格布局管理器
        GridLayoutManager glmdown=new GridLayoutManager(this,3);
        //recycleview设置布局管理器
        rc_down.setLayoutManager(glmdown);
        //okhttp请求网络数据
        initData();

    }

    private void initData() {

        String url="http://mock.eoapi.cn/success/4q69ckcRaBdxhdHySqp2Mnxdju5Z8Yr4";
        //okhttp请求数据
        OkHttpManager.getAsync(url, new OkHttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {

                Gson gson=new Gson();

                MyMessage myMessage = gson.fromJson(result, MyMessage.class);

                rs = myMessage.getRs();
                //实例化适配器
                la = new LeftAdapter(MainActivity.this);
                //传递数据
                la.addRest(rs);
                //设置适配器
                rc_left.setAdapter(la);
                //实例化适配器
                ta = new TopAdapter(MainActivity.this);
                //实例化适配器
                da = new DownAdapter(MainActivity.this);

                //点击事件
                la.setOnItemClickLitener(new LeftAdapter.OnItemClickLitener() {
                    @Override
                    public void onItemClick(View view, final int position) {

                        if (rs.get(position).getChildren().size()>1) {

                            //赋值
                            tv_top.setText(rs.get(position).getChildren().get(0).getDirName());

                            tv_down.setText(rs.get(position).getChildren().get(1).getDirName());
                            //传递数据
                            ta.addRest(rs.get(position).getChildren().get(0).getChildren());

                            da.addRest(rs.get(position).getChildren().get(1).getChildren());

                            //设置适配器
                            rc_top.setAdapter(ta);

                            rc_down.setAdapter(da);
                            //点击事件
                            da.setOnItemClickLitener(new DownAdapter.OnItemClickLitener() {
                                @Override
                                public void onItemClick(View view, int position1) {

                                    Toast.makeText(MainActivity.this,rs.get(position).getChildren().get(1).getChildren().get(position1).getDirName(),Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onItemLongClick(View view, int position) {

                                }
                            });
                            //监听事件
                            ta.setOnItemClickLitener(new TopAdapter.OnItemClickLitener() {
                                @Override
                                public void onItemClick(View view, int position1) {

                                    Toast.makeText(MainActivity.this,rs.get(position).getChildren().get(0).getChildren().get(position1).getDirName(),Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onItemLongClick(View view, int position) {

                                }
                            });


                        }else {
                            //赋值
                            tv_top.setText(rs.get(position).getChildren().get(0).getDirName());
                            //传递数据
                            ta.addRest(rs.get(position).getChildren().get(0).getChildren());

                            tv_down.setText("");

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

    }

}
