package com.example.antonio.app5dma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_7gra_7ya)
    TextView tv_7gra_7ya;

    @BindView(R.id.tv_user_info)
    TextView tv_user_info;

    @BindView(R.id.tv_about_program)
    TextView tv_about_program;

    @BindView(R.id.tv_practice_topics)
    TextView tv_practice_topics;

    @BindView(R.id.tv_real_taxt)
    TextView tv_real_taxt;

    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder= ButterKnife.bind(this);


    }


    @OnClick({R.id.tv_real_taxt,R.id.tv_7gra_7ya,R.id.tv_about_program,R.id.tv_user_info,R.id.tv_practice_topics})
    void aVoid(View view,int pos){

        switch (view.getId()){

            case R.id.tv_7gra_7ya:

                break;

            case R.id.tv_about_program:
                break;
            case R.id.tv_real_taxt:
                break;
            case R.id.tv_user_info:
                break;
                

        }

    }
}
