package com.example.antonio.marinaApp.ulities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by antonio on 6/30/18.
 */

public class Helpers {

    public static void onDoIntentTo(Activity activity,Class<?> openActivity){

        Intent intent=new Intent(activity,openActivity);
        activity.startActivity(intent);

    }

    public static void showMessage(Context context,String content){


        Toast.makeText(context,content,Toast.LENGTH_SHORT).show();
    }
}
