package com.bwei.fragmentlianxi;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    final int TAKE_PICTURE = 1;

    private final String IMAGE_TYPE = "image/*";
    private final int IMAGE_CODE = 0;   //这里的IMAGE_CODE是自己任意定义的

    Button btn_my;
    ImageView iv_my;

    int width;
    int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_my=(Button)findViewById(R.id.btn_my);

        DisplayMetrics metrics=new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        width=metrics.widthPixels;

        height=metrics.widthPixels;

        iv_my=(ImageView)findViewById(R.id.iv_my);

        btn_my=(Button)findViewById(R.id.btn_my);

        btn_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog=new Dialog(MainActivity.this,R.style.MyDialog);

                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_cammer,null);

                dialog.setContentView(view);

                Button btn_dialog_cammer = (Button) view.findViewById(R.id.btn_dialog_cammer);
                Button btn_dialog_photo = (Button) view.findViewById(R.id.btn_dialog_photo);
                Button btn_dialog_cance = (Button) view.findViewById(R.id.btn_dialog_cance);

                LinearLayout ll_dialog_cammer = (LinearLayout)view.findViewById(R.id.ll_dialog_cammer);
                //设置dialog的位置

                //获得当前窗体
                Window window = dialog.getWindow();

                //重新设置
                WindowManager.LayoutParams lp = window.getAttributes();

                window.setGravity(Gravity.LEFT | Gravity.BOTTOM);
                lp.x = 0; // 新位置X坐标
                lp.y = 0; // 新位置Y坐标
                lp.width = width; // 宽度
                lp.height = (height/10)*3; // 高度


                // dialog.onWindowAttributesChanged(lp);
                //(当Window的Attributes改变时系统会调用此函数)
                window.setAttributes(lp);

                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) btn_dialog_cammer.getLayoutParams();
                params.height = height/10;
                params.width = width;
                btn_dialog_cammer.setLayoutParams(params);

                LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) btn_dialog_photo.getLayoutParams();
                params1.height = height/10;
                params1.width = width;
                btn_dialog_photo.setLayoutParams(params1);

                LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) btn_dialog_cance.getLayoutParams();
                params2.height = height/10;
                params2.width = width;
                btn_dialog_cance.setLayoutParams(params2);

                dialog.show();

                btn_dialog_cammer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivityForResult(new Intent("android.media.action.IMAGE_CAPTURE"), TAKE_PICTURE);
                        dialog.dismiss();

                    }
                });

                btn_dialog_photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        photo();
                        dialog.dismiss();
                    }
                });


                //去掉dialog
                btn_dialog_cance.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PICTURE) {
            if (resultCode == RESULT_OK) {
                Bitmap bm = (Bitmap) data.getExtras().get("data");
                iv_my.setImageBitmap(bm);//想图像显示在ImageView视图上，private ImageView img;

            }
        }else if(requestCode == IMAGE_CODE){//相册拍照

            //获得图片的uri
            try {
                ContentResolver resolver = getContentResolver();
                Uri originalUri = data.getData();
                Bitmap bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);        //显得到bitmap图片
                iv_my.setImageBitmap(bm);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //相册获取图片
    public void photo(){

        Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
        getAlbum.setType(IMAGE_TYPE);
        startActivityForResult(getAlbum, IMAGE_CODE);
    }
}
