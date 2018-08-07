package com.nytimes.android.nytimesapp.dashboard;

import android.content.Context;

import com.nytimes.android.nytimesapp.model.NewsDataModel;

import java.util.List;

public interface DashboardView {
    void showMessage(String message);
    void showResult(List<NewsDataModel> list);
    void redirectPage();
    Context getContext();

}
