package com.example.antonio.marinaApp.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.antonio.marinaApp.R;
import com.example.antonio.marinaApp.adapters.BiblicalFactsAdapter;
import com.example.antonio.marinaApp.adapters.VideosAdapter;
import com.example.antonio.marinaApp.models.biblicalFacts.BiblicalFactsModel;
import com.example.antonio.marinaApp.models.biblicalFacts.Item;
import com.example.antonio.marinaApp.service.URLs;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.antonio.marinaApp.controller.BibleFactsController.getCastingRequests;

public class PracticeTopicsActivity extends AppCompatActivity {

    ArrayList<Item> items=new ArrayList<>();

    @BindView(R.id.rv_all_user)
    RecyclerView rv_all_user;
    Unbinder unbinder;
    String TAG=PracticeTopicsActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_topics);
        unbinder= ButterKnife.bind(this);
        fechData();
    }

    private void fechData(){

        getCastingRequests(this, URLs.Space_ID, URLs.Environment_id,URLs.BibilicalFactsEntery_ID, URLs.Delivery_access_token, new Callback<BiblicalFactsModel>() {
            @Override
            public void onResponse(@NonNull Call<BiblicalFactsModel> call, @NonNull Response<BiblicalFactsModel> response) {
                try{
                    if (response.isSuccessful()){
                        for (Item item:response.body().getItems()){

                            if (item.getFields().getId()==2){
                                items.add(item);

                            }
                        }

                        VideosAdapter adapter = new VideosAdapter(items, PracticeTopicsActivity.this);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        rv_all_user.setLayoutManager(mLayoutManager);
                        rv_all_user.setItemAnimator(new DefaultItemAnimator());
                        rv_all_user.setAdapter(adapter);


                        Log.e(TAG, "isSuccessful");

                    }else {

                        Log.e(TAG,"not successfull");

                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(@NonNull Call<BiblicalFactsModel> call, @NonNull Throwable t) {
                Log.e(TAG,"onFailure>>>"+t.getMessage());

            }
        });

    }
}
