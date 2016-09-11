package com.blogspot.jimzhou001.outsidenews;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

public class ShowNewsActivity extends Activity {

    private ImageView back_shownews;
    private ImageView menu_shownews;
    private TextView newsContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shownews);

        back_shownews = (ImageView)findViewById(R.id.shownews_back);
        back_shownews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        menu_shownews = (ImageView)findViewById(R.id.shownews_menu);
        menu_shownews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        });

        newsContent = (TextView)findViewById(R.id.shownews);
        newsContent.setMovementMethod(ScrollingMovementMethod.getInstance());
        newsContent.setText(getNewsContent());
        newsContent.setTextSize(getSharedPreferences("newsContent", MODE_PRIVATE).getInt("textsize", 20));

    }

    private String getNewsContent() {
        int newsPosition = getIntent().getIntExtra("NewsPosition", 0);
        News news = MainActivity.newsLists.get(newsPosition);
        MainActivity.newsDataBaseHelper.addReadHistory(MainActivity.newsDatabase, newsPosition);
        return news.getTitle() + "\n" + news.getContent();
    }

    private void showPopupMenu(final View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.popupmenu_shownews, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new onMenuItemClickListener());
        popupMenu.show();
    }

    class onMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.share:
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, getNewsContent());
                    shareIntent.setType("text/plain");
                    startActivity(Intent.createChooser(shareIntent, "分享到"));
                    return true;
                case R.id.screenshot:
                    return true;
                case R.id.textsize:
                    String[] size = { "大字号", "中字号", "小字号"};
                    new AlertDialog.Builder(ShowNewsActivity.this).setTitle("正文字号").setItems(size, new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            SharedPreferences.Editor news_editor = getSharedPreferences("newsContent", MODE_PRIVATE).edit();
                            switch (i) {
                                case 0:
                                    news_editor.putInt("textsize", 25);
                                    break;
                                case 1:
                                    news_editor.putInt("textsize", 20);
                                    break;
                                case 2:
                                    news_editor.putInt("textsize", 15);
                                    break;
                                default:
                            }
                            news_editor.commit();
                            newsContent.setTextSize(getSharedPreferences("newsContent", MODE_PRIVATE).getInt("textsize", 15));
                            dialogInterface.dismiss();
                        }
                    }).show();
                    return true;
                case R.id.night:
                    return true;
                case R.id.feedback:
                    Intent intent = new Intent(ShowNewsActivity.this, FeedbackActivity.class);
                    startActivity(intent);
                    return true;
                default:
            }
            return false;
        }
    }

}
