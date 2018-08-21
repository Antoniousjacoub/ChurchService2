package com.example.antonio.marinaApp.service;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class APIClient extends AppCompatActivity {
//        public static final String BASEURL = "http://appsmelon.com/";
 public static final String BASEURL = "https://cdn.contentful.com";

    private static Retrofit retrofit = null;

    public static Retrofit getClient(final Context context) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        HttpLoggingInterceptor interceptor2 = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(interceptor2)
                .readTimeout(300, TimeUnit.SECONDS)
                .connectTimeout(300, TimeUnit.SECONDS)
                .build();

        //OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        try {
            okHttpClient = new OkHttpClient.Builder()
                    .sslSocketFactory(new TLSSocketFactory())
                    .addInterceptor(interceptor).addInterceptor(interceptor2)
                    .readTimeout(300, TimeUnit.SECONDS)
                    .connectTimeout(300, TimeUnit.SECONDS)
                    .build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(ScalarsConverterFactory.create())
//              .client(APIClient.getCertificate(context))
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return retrofit;
    }



}

