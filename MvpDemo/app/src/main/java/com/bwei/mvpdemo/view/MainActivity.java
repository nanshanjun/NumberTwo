package com.bwei.mvpdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwei.mvpdemo.R;
import com.bwei.mvpdemo.model.MessageBean;
import com.bwei.mvpdemo.presenter.MainInterface;
import com.bwei.mvpdemo.presenter.MainPresenter;
import com.bwei.mvpdemo.presenter.MvpView;
import com.bwei.mvpdemo.view.activity.HomeActivity;

public class MainActivity extends AppCompatActivity implements MvpView, MainInterface, View.OnClickListener {

    private MainPresenter presenter;
    private EditText et_user;
    private EditText et_password;
    private Button jump;
    private String user;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new MainPresenter();

        presenter.setMvpView(this);

        presenter.getAllmessage();

        setContentView(R.layout.activity_main);

        jump = (Button) findViewById(R.id.jump);

        et_user = (EditText) findViewById(R.id.et_user);

        et_password = (EditText) findViewById(R.id.et_password);

        jump.setOnClickListener(this);

    }

    @Override
    public void getMessage(MessageBean messageBean) {

        user = messageBean.getUser();

        password = messageBean.getPassword();

    }

    @Override
    public void onClick(View view) {

        if (et_user.getText().toString().trim().equals(user)&et_password.getText().toString().trim().equals(password)){

            startActivity(new Intent(MainActivity.this, HomeActivity.class));

        }else {

            Toast.makeText(MainActivity.this,"fail", Toast.LENGTH_SHORT).show();

        }

    }
}
