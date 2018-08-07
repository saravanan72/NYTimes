package com.nytimes.android.nytimesapp.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nytimes.android.nytimesapp.R;
import com.nytimes.android.nytimesapp.dashboard.DashBoardActivity;
import com.nytimes.android.nytimesapp.model.NewsDataModel;
import com.nytimes.android.nytimesapp.utils.PicassoCircleTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.MyViewHolder> {

    Context context;
    List<NewsDataModel> newsList;
    public static class MyViewHolder extends RecyclerView.ViewHolder {

       private ImageView logoImageview;
       private TextView titleTextView;
       private TextView subTitleTextView;
       private TextView dateTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            logoImageview = (ImageView) itemView.findViewById(R.id.nyt_news_item_imageview);
            titleTextView = (TextView) itemView.findViewById(R.id.nyt_news_item_title_textView);
            subTitleTextView = (TextView) itemView.findViewById(R.id.nyt_news_item_subtitle_textView);
            dateTextView = (TextView) itemView.findViewById(R.id.nyt_news_item_date_textView);

        }
    }

    public NewsRecyclerAdapter(Context context, List<NewsDataModel> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nyt_news_item_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        holder.titleTextView.setText(newsList.get(listPosition).getTitle());
        holder.subTitleTextView.setText(newsList.get(listPosition).getByline());
        holder.dateTextView.setText(newsList.get(listPosition).getPublished_date());

        Picasso.with(context).load(newsList.get(listPosition).getSource())
                .placeholder(R.drawable.circle_image_bg)
                .error(R.mipmap.ic_launcher)
                .transform(new PicassoCircleTransformation())
                .into(holder.logoImageview);


    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}