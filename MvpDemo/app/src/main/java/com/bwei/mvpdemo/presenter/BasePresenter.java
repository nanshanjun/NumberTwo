package com.bwei.mvpdemo.presenter;

/**
 * 作者： 南山君
 * 时间：2017/3/24.13:38
 */

public class BasePresenter <T extends MvpView> {

    private T mvpView;

    public T getMvpView() {
        return mvpView;
    }

    public void setMvpView(T mvpView) {
        this.mvpView = mvpView;
    }
}
