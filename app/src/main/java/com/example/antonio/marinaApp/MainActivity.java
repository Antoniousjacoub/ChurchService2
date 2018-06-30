package com.example.antonio.marinaApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.antonio.marinaApp.activities.AboutUsActivity;
import com.example.antonio.marinaApp.activities.BiblicalFactsActivity;
import com.example.antonio.marinaApp.activities.PracticeTopicsActivity;
import com.example.antonio.marinaApp.activities.UserInformationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.antonio.marinaApp.ulities.Helpers.onDoIntentTo;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_7gra_7ya)
    TextView tv_7gra_7ya;

    @BindView(R.id.tv_user_info)
    TextView tv_user_info;

    @BindView(R.id.tv_about_program)
    TextView tv_about_program;

    @BindView(R.id.tv_practice_topics)
    TextView tv_practice_topics;

    @BindView(R.id.tv_biblical_facts)
    TextView tv_biblical_facts;

    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder= ButterKnife.bind(this);


    }

    @OnClick({R.id.tv_biblical_facts,R.id.tv_7gra_7ya,R.id.tv_about_program,R.id.tv_user_info,R.id.tv_practice_topics})
    void onClicked(View view){

        switch (view.getId()){
            case R.id.tv_about_program:
                onDoIntentTo(this,AboutUsActivity.class);
                break;
            case R.id.tv_biblical_facts:
                onDoIntentTo(this,BiblicalFactsActivity.class);
                break;
            case R.id.tv_user_info:
                onDoIntentTo(this,UserInformationActivity.class);
                break;
            case R.id.tv_practice_topics:
                onDoIntentTo(this,PracticeTopicsActivity.class);
                break;


        }

    }



}
