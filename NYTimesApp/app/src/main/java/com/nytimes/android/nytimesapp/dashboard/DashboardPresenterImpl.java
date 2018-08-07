package com.nytimes.android.nytimesapp.dashboard;

import android.content.Context;

import com.nytimes.android.nytimesapp.model.NewsDataModel;

import java.util.List;

public class DashboardPresenterImpl implements DashboardPresenter, DashboardModelImpl.DashboardModelListener {
    DashboardView dashboardView;
    DashboardModel dashboardModel;

    public DashboardPresenterImpl(DashboardView dashboardView) {
        this.dashboardView = dashboardView;
        dashboardModel = new DashboardModelImpl(this);
    }

    @Override
    public void onCreate() {
        dashboardModel.onCreate();
    }

    @Override
    public void onDestroy() {
        dashboardModel.onDestroy();
        dashboardView = null;
        dashboardModel = null;
    }


    @Override
    public void showMessage(String message) {
        dashboardView.showMessage(message);
    }

    @Override
    public void getResult(List<NewsDataModel> list) {
        dashboardView.showResult(list);
    }

    @Override
    public void redirectPage() {
        dashboardView.redirectPage();
    }

    @Override
    public Context getContext() {
        return dashboardView.getContext();
    }
}
