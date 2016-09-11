package com.blogspot.jimzhou001.outsidenews;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {

    private Activity currentActivity;
    private ArrayList<View> viewList;

    public ViewPagerAdapter(Activity activity, ArrayList<View> list) {
        this.currentActivity = activity;
        this.viewList = list;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView(viewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = viewList.get(position);

        if (currentActivity.getLocalClassName().equals("GuideActivity")) {
            ImageView imageView = (ImageView)view.findViewById(R.id.guide_image);
            imageView.setImageResource(GuideActivity.guideImages[position]);
        }

        ((ViewPager)container).addView(view);
        return viewList.get(position);

    }

}