package com.bwei.mvpdemo.presenter;

import com.bwei.mvpdemo.model.MessageBean;

/**
 * 作者： 南山君
 * 时间：2017/3/24.13:40
 */

public class MainPresenter extends BasePresenter<MainInterface> {

    private MessageBean messageBean;

    public void getAllmessage(){

        messageBean = new MessageBean("123","123");

        getMvpView().getMessage(messageBean);

    }

}
