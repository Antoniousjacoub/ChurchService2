package com.example.antonio.marinaApp.controller;

import android.content.Context;

import com.example.antonio.marinaApp.models.biblicalFacts.BiblicalFactsModel;
import com.example.antonio.marinaApp.service.APIClient;
import com.example.antonio.marinaApp.service.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by antonio on 8/20/18.
 */

public class BibleFactsController {

    public static void getCastingRequests(Context context,
                                          String space_id,
                                          String environment_id,
                                          String entry_id,
                                          String access_token ,
                                          Callback<BiblicalFactsModel> callback) {
        APIInterface aPIClient = APIClient.getClient(context).create(APIInterface.class);
        Call<BiblicalFactsModel> call = aPIClient.getBiblicalFacts(space_id,environment_id,access_token);
        call.enqueue(callback);
    }
}
