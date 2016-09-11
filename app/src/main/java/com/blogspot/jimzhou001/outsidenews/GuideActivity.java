package com.blogspot.jimzhou001.outsidenews;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GuideActivity extends Activity {

    private ViewPager guidePager;
    private ArrayList<View> guideViews = new ArrayList<View>();
    public static int[] guideImages = { R.drawable.guide_image_1, R.drawable.guide_image_2, R.drawable.guide_image_3, R.drawable.guide_image_4, R.drawable.guide_image_5};
    private LinearLayout guideDotsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        guidePager = (ViewPager) findViewById(R.id.guide_pager);
        guideDotsContainer = (LinearLayout) findViewById(R.id.guide_dots_container);

        String[] guideTexts = { "“最近压力好大呀！”", "“那就转移下注意力，放松一下心情呗。”", "“你有什么具体建议么？”", "“听说……", "“Wow!”"};
        LayoutInflater inflater = LayoutInflater.from(this);

        for (int i = 0; i < guideImages.length; i++) {

            View view = inflater.inflate(R.layout.item_guide, null);
            TextView text = (TextView) view.findViewById(R.id.guide_text);
            text.setText(guideTexts[i]);

            if (i==3) {
                TextView outside_text = (TextView)view.findViewById(R.id.outside_text);
                TextView guide_text_last = (TextView)view.findViewById(R.id.guide_text_last);
                outside_text.setVisibility(View.VISIBLE);
                outside_text.setTextColor(Color.BLUE);
                guide_text_last.setVisibility(View.VISIBLE);
                guide_text_last.setTextColor(Color.MAGENTA);
            }

            if (i == guideImages.length - 1) {
                text.setTextSize(30);
                final Button start = (Button)view.findViewById(R.id.guide_start);
                start.setVisibility(View.VISIBLE);
                start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
            }

            if (i%2==0) {
                text.setTextColor(Color.RED);
                ((LinearLayout)view.findViewById(R.id.guide_dialog)).setGravity(Gravity.LEFT);
            } else {
                text.setTextColor(Color.MAGENTA);
                ((LinearLayout)view.findViewById(R.id.guide_dialog)).setGravity(Gravity.RIGHT);
            }

            guideViews.add(view);

        }

        for (int i = 0; i < guideViews.size(); i++) {
            View view = inflater.inflate(R.layout.dot, null);
            if (i == 0) {
                view.setSelected(true);
            }
            guideDotsContainer.addView(view);
        }

        guidePager.setAdapter(new ViewPagerAdapter(this, guideViews));

        guidePager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < guideDotsContainer.getChildCount(); i++) {
                    guideDotsContainer.getChildAt(i).setSelected(false);
                }
                guideDotsContainer.getChildAt(position).setSelected(true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

    }

    @Override
    public void onBackPressed() {

        if ((System.currentTimeMillis() - WelcomeActivity.exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出窗外", Toast.LENGTH_SHORT).show();
            WelcomeActivity.exitTime = System.currentTimeMillis();
        } else {
            finish();
        }

    }

}
