package com.example.antonio.marinaApp.ulities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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

    public static File compressImage(File mFile) {

        if (mFile==null)
            return null;
		/* There isn't enough memory to open up more than a couple camera photos */
        /* So pre-scale the target bitmap into which the file is decoded */

		/* Get the size of the ImageView */
        //int targetW = mImageView.getWidth();
        //int targetH = mImageView.getHeight();

		/* Get the size of the image */
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        bmOptions.inPreferredConfig = null;
        try {
            BitmapFactory.decodeFile(mFile.getAbsolutePath(), bmOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.e("outsize", bmOptions.outWidth + " , " + bmOptions.outHeight);
        int IMAGE_MAX_SIZE = 700;
        int scale = 1;
        //todo why outsize - 1  ????????????????
        // if (bmOptions.outHeight > IMAGE_MAX_SIZE || bmOptions.outWidth > IMAGE_MAX_SIZE) {
        scale = (int) Math.pow(2, (int) Math.ceil(Math.log(IMAGE_MAX_SIZE /
                (double) Math.max(bmOptions.outHeight, bmOptions.outWidth)) / Math.log(0.5)));
        //}

        bmOptions.inJustDecodeBounds = false;
        if (bmOptions.outWidth != -1) {
            bmOptions.inSampleSize = scale;
            Log.e("sampling", scale + " sample successfully");
        } else {
            bmOptions.inSampleSize = 4;
            Log.e("sampling", scale + " faild");
        }
        //bmOptions.inPurgeable = true;
        Log.e("outsize", scale + " ");
        Bitmap b = BitmapFactory.decodeFile(mFile.getAbsolutePath(), bmOptions);

        try {
            FileOutputStream out = new FileOutputStream(mFile.getAbsoluteFile());
            b.compress(Bitmap.CompressFormat.JPEG, 100, out);
            b.recycle();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mFile;
    }
}
