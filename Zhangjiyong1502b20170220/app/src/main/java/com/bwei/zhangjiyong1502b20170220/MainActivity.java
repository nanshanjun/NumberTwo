package com.bwei.zhangjiyong1502b20170220;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.bawei.xlistview.view.XListView;
import com.bwei.zhangjiyong1502b20170220.entity.AllMessage;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener, AdapterView.OnItemLongClickListener {

    private MyAdapter my;
    private List<AllMessage.ResultBean.DataBean> data;
    private XListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  网络请求，获得数据
        initData();
        //  初始化控件
        lv = (XListView) findViewById(R.id.lv);
        //  获得适配器
        my = new MyAdapter(this);
        //设置适配器
        lv.setAdapter(my);

        lv.setPullLoadEnable(true);

        lv.setPullRefreshEnable(true);
        //设置监听事件
        lv.setXListViewListener(this);

        lv.setOnItemLongClickListener(this);

    }

    private void initData() {
        //xutils方法请求数据
        HttpUtils http=new HttpUtils();

        String url="http://japi.juhe.cn/joke/content/list.from?key=94fbc7ec2262160140d71e1418322f34%20&page=1&pagesize=10&sort=asc&time=1418745237";

        http.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                String result = responseInfo.result;

                Gson gson=new Gson();

                AllMessage allMessage = gson.fromJson(result, AllMessage.class);

                data = allMessage.getResult().getData();

                my.addRest(data);

            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("提示"); //设置标题
        builder.setMessage("是否确认删除?"); //设置内容
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //关闭dialog

                data.remove(i);

                my.addRest(data);

                Toast.makeText(MainActivity.this, "确认" , Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "取消" , Toast.LENGTH_SHORT).show();
            }
        });

        //参数都设置完成了，创建并显示出来
        builder.create().show();

        return false;
    }

    @Override
    public void onRefresh() {

        initData();

    }

    @Override
    public void onLoadMore() {

        initData();

    }
}
