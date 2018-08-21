package com.example.antonio.marinaApp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.antonio.marinaApp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pl.aprilapps.easyphotopicker.Constants;

public class BiblicalFactsDetailsActivity extends AppCompatActivity {

    public static final String TITLE = "title";
    public static String IMAGE_URL="IMAGE";
    public static String TEXT_CONTENT="TEXT";

    @BindView(R.id.img_biblical_facts)ImageView img_biblical_facts;

    @BindView(R.id.tv_biblical_facts_content)TextView tv_biblical_facts_content;
    @BindView(R.id.tv_title_toolbar)TextView tv_title_toolbar;
    Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biblical_facts_details);
        unbinder= ButterKnife.bind(this);

        Bundle bundle=getIntent().getExtras();

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.img_elsaleb);
        requestOptions.error(R.drawable.img_elsaleb);
        if (bundle!=null){

            tv_biblical_facts_content.setText(bundle.getString(TEXT_CONTENT));
            tv_title_toolbar.setText(bundle.getString(TITLE));
            if (bundle.getString(IMAGE_URL) != null) {
                Glide.with(this)
                        .setDefaultRequestOptions(requestOptions)
                        .load(bundle.getString(IMAGE_URL))
                        .into(img_biblical_facts);
            }
        }
    }
}
