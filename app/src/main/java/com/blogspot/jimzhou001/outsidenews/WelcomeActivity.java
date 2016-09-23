package com.blogspot.jimzhou001.outsidenews;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    public static long exitTime = System.currentTimeMillis();//用于实现按返回键一次提醒、两次退出的功能

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSharedPreferences("guide", MODE_PRIVATE).getBoolean("is_first", true)==true) {

            SharedPreferences.Editor guide_editor = getSharedPreferences("guide", MODE_PRIVATE).edit();
            guide_editor.putBoolean("is_first", false);
            guide_editor.commit();
            Intent intent = new Intent(WelcomeActivity.this, GuideActivity.class);
            startActivity(intent);
            finish();

        }

        setContentView(R.layout.activity_welcome);

    }

    @Override
    protected void onStart() {

        super.onStart();

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessageDelayed(1, 2000);
            }
        }).start();

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void onBackPressed() {

        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出窗外", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }

    }

}
