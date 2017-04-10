package com.bwei.mvpdemo.presenter;

import com.bwei.mvpdemo.model.MessageBean;

/**
 * 作者： 南山君
 * 时间：2017/3/24.13:45
 */

public interface MainInterface extends MvpView {

   void getMessage(MessageBean messageBean);

}
