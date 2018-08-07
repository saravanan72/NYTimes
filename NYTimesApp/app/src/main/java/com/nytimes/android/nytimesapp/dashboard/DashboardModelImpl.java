package com.nytimes.android.nytimesapp.dashboard;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.nytimes.android.nytimesapp.R;
import com.nytimes.android.nytimesapp.app.AppController;
import com.nytimes.android.nytimesapp.model.NewsDataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardModelImpl implements DashboardModel{
    DashboardModelListener dashboardModelListener;
    private String BaseUrl;
    List<NewsDataModel> newsList = new ArrayList<>();
    public DashboardModelImpl(DashboardModelListener dashboardModelListener) {
        this.dashboardModelListener = dashboardModelListener;
    }

    @Override
    public void onCreate() {
        makeJsonRequest();
    }

    @Override
    public void onDestroy() {
        dashboardModelListener = null;
    }

    @Override
    public void showList() {
    }
    private void makeJsonRequest() {
        BaseUrl = dashboardModelListener.getContext().getResources().getString(R.string.base_url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BaseUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parseData(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dashboardModelListener.getResult(newsList);
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("api-key", dashboardModelListener.getContext().getResources().getString(R.string.api_key));
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("type", "Article");
                //params.put("id", "100000005955815");
                return null;
            }
        };

        //retry policy by Volley
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);


    }

    private void parseData(JSONObject response) {
        newsList.clear();
        try {
            JSONArray newsJsonArray = response.getJSONArray("results");
            for (int i=0;i<newsJsonArray.length();i++){

                NewsDataModel dataModel = new NewsDataModel();
                dataModel.setTitle(newsJsonArray.getJSONObject(i).getString("title"));
                dataModel.setNewsAbstract(newsJsonArray.getJSONObject(i).getString("abstract"));
                dataModel.setPublished_date(newsJsonArray.getJSONObject(i).getString("published_date"));
                dataModel.setByline(newsJsonArray.getJSONObject(i).getString("byline"));
                dataModel.setSource(newsJsonArray.getJSONObject(i).getJSONArray("media").getJSONObject(0).getJSONArray("media-metadata").getJSONObject(0).getString("url"));
                dataModel.setKeywords(newsJsonArray.getJSONObject(i).getString("adx_keywords"));
                dataModel.setType(newsJsonArray.getJSONObject(i).getString("type"));

                newsList.add(dataModel);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        dashboardModelListener.getResult(newsList);
    }


    public interface DashboardModelListener{

        void showMessage(String message);
        void getResult(List<NewsDataModel> list);
        void redirectPage();
        Context getContext();
    }
}
