package com.blogspot.jimzhou001.outsidenews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {

    private int resourceId;

    public NewsAdapter(Context context, int textViewRecourceId, ArrayList<News> objects) {
        super(context, textViewRecourceId, objects);
        resourceId = textViewRecourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.newsImage = (ImageView)view.findViewById(R.id.news_image);
            viewHolder.newsTitle = (TextView)view.findViewById(R.id.news_title);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.newsImage.setImageResource(news.getImageId());
        viewHolder.newsTitle.setText(news.getTitle());
        return view;
    }

    class ViewHolder {
        ImageView newsImage;
        TextView newsTitle;
    }

}
