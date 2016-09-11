package com.blogspot.jimzhou001.outsidenews;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class SearchActivity extends Activity {

    private ImageView search_back;
    private ImageView scan;
    private TextView read_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search_back = (ImageView)findViewById(R.id.search_back);
        search_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        scan = (ImageView)findViewById(R.id.scan);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(SearchActivity.this);
                integrator.setPrompt("将二维码/条码放入框内，即可自动扫描");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setOrientationLocked(false);
                integrator.initiateScan();
            }
        });

        read_history = (TextView)findViewById(R.id.read_history);
        read_history.setMovementMethod(ScrollingMovementMethod.getInstance());
        final Cursor cursor_read = MainActivity.newsDatabase.query("ReadHistory", null, null, null, null, null, null);
        final Cursor cursor = MainActivity.newsDatabase.query("News", null, null, null, null, null, null);
        if (cursor_read.moveToLast()) {
            int i = 0, newsId;
            String title;
            do {
                newsId = cursor_read.getInt(cursor_read.getColumnIndex("newsId"));
                cursor.moveToPosition(newsId);
                title = cursor.getString(cursor.getColumnIndex("title"));
                read_history.append(title + "\n" + "\n");
            } while (cursor_read.moveToPrevious());
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "您取消了扫描", Toast.LENGTH_LONG).show();
            } else {
                final String s = result.getContents();
                new AlertDialog.Builder(this).setTitle("扫描结果为：").setMessage(s).setPositiveButton("打开链接", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            Intent intent1 = new Intent(Intent.ACTION_VIEW);
                            intent1.setData(Uri.parse(s));
                            startActivity(intent1);
                        } catch (Exception e) {
                            Toast.makeText(SearchActivity.this, "无法打开此内容", Toast.LENGTH_LONG).show();
                        }
                    }
                }).show();
            }
        }
    }

}
