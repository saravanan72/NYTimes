package com.nytimes.android.nytimesapp.dashboard;

import com.nytimes.android.nytimesapp.model.NewsDataModel;

import java.util.List;

public interface DashboardModel {
    void onCreate();
    void onDestroy();
    void showList();
}
