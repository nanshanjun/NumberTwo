package com.bwei.eggplantdoctor.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;

import com.bwei.eggplantdoctor.R;
import com.bwei.eggplantdoctor.fragment.BookFragment;
import com.bwei.eggplantdoctor.fragment.MessageFragment;
import com.bwei.eggplantdoctor.fragment.MineFragment;
import com.bwei.eggplantdoctor.fragment.PatientFragment;
import com.bwei.eggplantdoctor.fragment.ShowFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private FrameLayout fl_main_content;
    private Button bu_main_book;
    private Button bu_main_patient;
    private Button bu_main_message;
    private Button bu_main_mine;
    private FragmentManager manager;
    private BookFragment bookFragment;
    private PatientFragment patientFragment;
    private MessageFragment messageFragment;
    private MineFragment mineFragment;

    List<ShowFragment> list=new ArrayList<>();
    private FragmentTransaction transition;
    private TranslateAnimation translateAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化头部
        initHeader();
        //初始化控件
        initWidget();
        //设置监听
        initSetListener();

    }

    @Override
    public void initHeader() {

        initHeaderWidget();

        setTitle("预约");

        SetListener();

    }

    @Override
    public void initWidget() {

        fl_main_content = (FrameLayout) findViewById(R.id.fl_main_content);

        bu_main_book = (Button) findViewById(R.id.bu_main_book);

        bu_main_patient = (Button) findViewById(R.id.bu_main_patient);

        bu_main_message = (Button) findViewById(R.id.bu_main_message);

        bu_main_mine = (Button) findViewById(R.id.bu_main_mine);

        manager = getSupportFragmentManager();

        bookFragment = new BookFragment();

        patientFragment = new PatientFragment();

        messageFragment = new MessageFragment();

        mineFragment = new MineFragment();

        addList();

        addFragment(0);

    }

    private void addFragment(int position){

        transition = manager.beginTransaction();

        for (int i = 0; i < list.size(); i++) {

            if (i!=position){

                transition.hide(list.get(i).fragment);

            }

        }

        if (list.get(position).statue==0){

            transition.add(R.id.fl_main_content,list.get(position).fragment,position+"");

            list.get(position).statue=1;

            transition.show(list.get(position).fragment);

        }else {

            transition.show(list.get(position).fragment);

        }

        transition.commit();

    }

    private void addList(){

        for (int i = 0; i < 4; i++) {

            ShowFragment showFragment= new ShowFragment();

            switch (i){

                case 0:

                    showFragment.fragment=bookFragment;

                    break;

                case 1:

                    showFragment.fragment=patientFragment;

                    break;

                case 2:

                    showFragment.fragment=messageFragment;

                    break;

                case 3:

                    showFragment.fragment=mineFragment;

                    break;

            }

            list.add(showFragment);

        }

    }

    @Override
    public void initSetListener() {

        bu_main_book.setOnClickListener(this);

        bu_main_patient.setOnClickListener(this);

        bu_main_message.setOnClickListener(this);

        bu_main_mine.setOnClickListener(this);

        setStatue(R.id.bu_main_book);

        setTitleStatue(LEFT_NO_RIGHT_IMAGEADD);

    }

    public void addAnimation(int id){

        translateAnimation = new TranslateAnimation(0,0,0,-35);

        translateAnimation.setDuration(1000);

        translateAnimation.setRepeatCount(1);

        translateAnimation.setRepeatMode(Animation.REVERSE);

        switch (id){

            case R.id.bu_main_book:

                bu_main_book.startAnimation(translateAnimation);

                break;

            case R.id.bu_main_patient:

                bu_main_patient.startAnimation(translateAnimation);

                break;

            case R.id.bu_main_message:

                bu_main_message.startAnimation(translateAnimation);

                break;

            case R.id.bu_main_mine:

                bu_main_mine.startAnimation(translateAnimation);

                break;

        }

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);

        switch (view.getId()){

            case R.id.bu_main_book:

                setStatue(R.id.bu_main_book);

                setTitleStatue(LEFT_NO_RIGHT_IMAGEADD);

                addFragment(0);

                addAnimation(R.id.bu_main_book);

//                translateAnimation.cancel();

                break;

            case R.id.bu_main_patient:

                setStatue(R.id.bu_main_patient);

                setTitleStatue(LEFT_NO_RIGHT_IMAGEADD);

                addFragment(1);

                addAnimation(R.id.bu_main_patient);

//                translateAnimation.cancel();

                break;

            case R.id.bu_main_message:

                setStatue(R.id.bu_main_message);

                setTitleStatue(LEFT_NO_RIGHT_IMAGEQUERY);

                addFragment(2);

                addAnimation(R.id.bu_main_message);

//                translateAnimation.cancel();

                break;

            case R.id.bu_main_mine:

                setStatue(R.id.bu_main_mine);

                setTitleStatue(LEFT_NO_RIGHT_IMAGEADD);

                addFragment(3);

                addAnimation(R.id.bu_main_mine);

//                translateAnimation.cancel();

                break;

        }

    }

    public void setStatue(int id){

        switch (id){

            case R.id.bu_main_book:

                bu_main_book.setSelected(true);
                bu_main_message.setSelected(false);
                bu_main_mine.setSelected(false);
                bu_main_patient.setSelected(false);

                setTitle("预约");

                break;

            case R.id.bu_main_patient:

                bu_main_book.setSelected(false);
                bu_main_message.setSelected(false);
                bu_main_mine.setSelected(false);
                bu_main_patient.setSelected(true);

                setTitle("患者");

                break;

            case R.id.bu_main_message:

                bu_main_book.setSelected(false);
                bu_main_message.setSelected(true);
                bu_main_mine.setSelected(false);
                bu_main_patient.setSelected(false);

                setTitle("病历广场");

                break;

            case R.id.bu_main_mine:

                bu_main_book.setSelected(false);
                bu_main_message.setSelected(false);
                bu_main_mine.setSelected(true);
                bu_main_patient.setSelected(false);

                setTitle("我");

                break;

        }

    }

}
