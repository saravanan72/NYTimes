package com.nytimes.android.nytimesapp.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nytimes.android.nytimesapp.R;
import com.nytimes.android.nytimesapp.model.NewsDataModel;
import com.squareup.picasso.Picasso;

public class NewsDetailsActivity extends AppCompatActivity {

    private ImageView expandedImage;
    private Toolbar toolbar;
    private NewsDataModel dataModel;

    TextView titleTextView, typeTextview, dateTextview, keywordTextView, descriptionTextView, copyRiightTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        dataModel = getIntent().getParcelableExtra("NewsData");
        iniview();
    }

    private void iniview() {
        expandedImage = (ImageView) findViewById(R.id.expandedImage);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.most_popular);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        titleTextView = (TextView) findViewById(R.id.news_details_title_textview);
        typeTextview = (TextView) findViewById(R.id.news_details_poste_type_textview);
        dateTextview = (TextView) findViewById(R.id.news_details_posted_date_textview);
        keywordTextView = (TextView) findViewById(R.id.news_details_keywords_textview);
        descriptionTextView = (TextView) findViewById(R.id.news_details_description_textview);
        copyRiightTextView = (TextView) findViewById(R.id.news_details_copy_right_textview);

        setUpDetailValues();

        setListener();

    }

    private void setUpDetailValues() {

        titleTextView.setText(dataModel.getTitle());
        typeTextview.setText(dataModel.getType());
        dateTextview.setText(dataModel.getPublished_date());
        keywordTextView.setText("Keywords: "+dataModel.getKeywords());
        descriptionTextView.setText(dataModel.getNewsAbstract());

        toolbar.setTitle(dataModel.getType());

        Picasso.with(this).load(dataModel.getSource())
                .placeholder(R.drawable.image_rectangle_bg)
                .error(R.drawable.image_rectangle_bg)
                .into(expandedImage);
    }

    private void setListener() {

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}
