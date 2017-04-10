package com.bwei.mvpdemo.model;

/**
 * 作者： 南山君
 * 时间：2017/3/24.13:34
 */

public class MessageBean {

    private String user;

    private String password;

    public MessageBean(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
