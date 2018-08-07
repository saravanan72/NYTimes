package com.nytimes.android.nytimesapp.dashboard;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.nytimes.android.nytimesapp.R;
import com.nytimes.android.nytimesapp.adapter.NewsRecyclerAdapter;
import com.nytimes.android.nytimesapp.details.NewsDetailsActivity;
import com.nytimes.android.nytimesapp.listener.RecyclerItemListener;
import com.nytimes.android.nytimesapp.model.NewsDataModel;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DashBoardActivity extends AppCompatActivity implements DashboardView{

    private Toolbar toolbar;
    private RecyclerView recyclerView;

    private List<NewsDataModel> newsList = new ArrayList<>();

    private ProgressDialog pd;

    private DashboardPresenter dashboardPresenter;

    private ShimmerFrameLayout mShimmerViewContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        initView();
        setListeners();

        //showProgressdialog();
        dashboardPresenter = new DashboardPresenterImpl(this);
        dashboardPresenter.onCreate();

    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    protected void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.most_popular);
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DashBoardActivity.this, "You want to close?", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.nytimes_news_recyclerView);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
    }



    private void hideProgressDialog() {
        if (pd!=null)
            pd.dismiss();
    }

    private void showProgressdialog() {
        pd = new ProgressDialog(DashBoardActivity.this);
        pd.setCancelable(false);
        pd.setMessage("Loading News");
        pd.show();
    }

    private void setAdapter() {
        Log.i("TAG", "makeJsonRequest: 5");
        NewsRecyclerAdapter adapter = new NewsRecyclerAdapter(this, newsList);
        recyclerView.setAdapter(adapter);
    }

    private void setListeners(){
        recyclerView.addOnItemTouchListener(new RecyclerItemListener(getApplicationContext(), recyclerView,
                new RecyclerItemListener.RecyclerTouchListener() {
                    public void onClickItem(View v, int position) {
                       // Toast.makeText(DashBoardActivity.this, "Clicked Position "+position, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DashBoardActivity.this, NewsDetailsActivity.class);
                        intent.putExtra("NewsData", newsList.get(position));
                        startActivity(intent);
                    }

                    public void onLongClickItem(View v, int position) {
                       // Toast.makeText(DashBoardActivity.this, "Long clicked Position "+position, Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResult(List<NewsDataModel> list) {
        // Stopping Shimmer Effect's animation after data is loaded to ListView
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
        if (list.size()>0) {
            newsList.clear();
            newsList = list;
            setAdapter();
        }
        //hideProgressDialog();
    }

    @Override
    public void redirectPage() {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dashboardPresenter.onDestroy();
    }
}
