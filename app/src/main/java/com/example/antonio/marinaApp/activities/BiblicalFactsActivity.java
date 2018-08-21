package com.example.antonio.marinaApp.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.contentful.java.cda.CDAArray;
import com.contentful.java.cda.CDAAsset;
import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAContentType;
import com.contentful.java.cda.CDAEntry;
import com.contentful.java.cda.CDAResource;
import com.example.antonio.marinaApp.R;
import com.example.antonio.marinaApp.adapters.BiblicalFactsAdapter;
import com.example.antonio.marinaApp.adapters.UserAdapter;
import com.example.antonio.marinaApp.models.biblicalFacts.BiblicalFactsModel;
import com.example.antonio.marinaApp.models.biblicalFacts.Item;
import com.example.antonio.marinaApp.service.URLs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.antonio.marinaApp.controller.BibleFactsController.getCastingRequests;

public class BiblicalFactsActivity extends AppCompatActivity {

    ArrayList<Item> items=new ArrayList<>();
    String TAG=BiblicalFactsActivity.class.getName();
    @BindView(R.id.rv_all_user)
    RecyclerView rv_all_user;
    Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biblical_facts);
        unbinder= ButterKnife.bind(this);
        fechData();

    }


    private void fechData(){

        getCastingRequests(this, URLs.Space_ID, URLs.Environment_id, URLs.Delivery_access_token, new Callback<BiblicalFactsModel>() {
            @Override
            public void onResponse(@NonNull Call<BiblicalFactsModel> call, @NonNull Response<BiblicalFactsModel> response) {
               try{
                if (response.isSuccessful()){
                    items.addAll(response.body().getItems());

                    BiblicalFactsAdapter adapter = new BiblicalFactsAdapter(items, BiblicalFactsActivity.this);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        rv_all_user.setLayoutManager(mLayoutManager);
                        rv_all_user.setItemAnimator(new DefaultItemAnimator());
                        rv_all_user.setAdapter(adapter);


                    Log.e(TAG, "isSuccessful");
                    Log.e(TAG, "isSuccessful>>>itemSize"+items.size());
                    Log.e(TAG, "isSuccessful>>>"+response.body().getItems().get(0).getFields().getTitle());
                    Log.e(TAG, "isSuccessful>>>"+response.body().getItems().get(0).getFields().getTopicdetails());
                    Log.e(TAG, "isSuccessful>>>"+response.body().getItems().get(0).getFields().getImageUrl());
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
