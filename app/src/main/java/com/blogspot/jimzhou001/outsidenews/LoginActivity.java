package com.blogspot.jimzhou001.outsidenews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class LoginActivity extends Activity {

    private ImageView login_back;
    private EditText account;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_back = (ImageView)findViewById(R.id.login_back);
        login_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        account = (EditText)findViewById(R.id.account);
        account.setCursorVisible(false);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                account.setCursorVisible(true);
            }
        });
        password = (EditText)findViewById(R.id.password);
        password.setCursorVisible(false);
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password.setCursorVisible(true);
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;
    }

}