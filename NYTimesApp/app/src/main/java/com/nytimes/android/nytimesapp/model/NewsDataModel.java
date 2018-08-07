package com.nytimes.android.nytimesapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsDataModel implements Parcelable{
    private String title;
    private String newsAbstract;
    private String published_date;
    private String source;
    private String byline;
    private String keywords;
    private String type;

    public NewsDataModel(Parcel in) {
        title = in.readString();
        newsAbstract = in.readString();
        published_date = in.readString();
        source = in.readString();
        byline = in.readString();
        keywords = in.readString();
        type = in.readString();
    }

    public static final Creator<NewsDataModel> CREATOR = new Creator<NewsDataModel>() {
        @Override
        public NewsDataModel createFromParcel(Parcel in) {
            return new NewsDataModel(in);
        }

        @Override
        public NewsDataModel[] newArray(int size) {
            return new NewsDataModel[size];
        }
    };

    public NewsDataModel() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewsAbstract() {
        return newsAbstract;
    }

    public void setNewsAbstract(String newsAbstract) {
        this.newsAbstract = newsAbstract;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(newsAbstract);
        parcel.writeString(published_date);
        parcel.writeString(source);
        parcel.writeString(byline);
        parcel.writeString(keywords);
        parcel.writeString(type);
    }
}
