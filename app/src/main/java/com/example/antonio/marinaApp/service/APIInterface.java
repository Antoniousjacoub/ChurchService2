package com.example.antonio.marinaApp.service;

import com.example.antonio.marinaApp.models.biblicalFacts.BiblicalFactsModel;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;



public interface APIInterface {
///spaces/{space_id}/environments/{environment_id}/entries/{entry_id}?access_token={access_token}

//    /spaces/{space_id}/environments/{environment_id}/content_types/{content_type_id}?access_token={access_token}
    //-----------------------------------------------------------------------------------------------------------------------------
    @GET("/spaces/{space_id}/environments/{environment_id}/entries")
    Call<BiblicalFactsModel> getBiblicalFacts(@Path( "space_id")String space_id,
                                        @Path("environment_id")String environment_id,
                                        @Query("access_token") String access_token );




//    @GET("/ClassyArtist/api/V1/Gender")
//    Call<GenderModel> getGender(@Query("languageId") int languageId);






}