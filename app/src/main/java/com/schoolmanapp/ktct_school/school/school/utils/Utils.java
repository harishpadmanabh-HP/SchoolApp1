package com.schoolmanapp.ktct_school.school.school.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;


import com.schoolmanapp.ktct_school.school.school.Apis;
import com.schoolmanapp.ktct_school.school.school.component.ActivityComponent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;


/**
 * Created by sics on 1/30/2017.
 */

public class Utils {
    private static final String EMAIL_REGEX = "^\\s*?(.+)@(.+?)\\s*$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    @Inject
    Context mContext;

    public Utils(ActivityComponent appComponent) {
        appComponent.inject(this);
    }

    public Utils(){

    }

    public static Bitmap retriveVideoFrameFromVideo(String videoPath)
            throws Throwable {
        Bitmap bitmap = null;
        MediaMetadataRetriever mediaMetadataRetriever = null;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            if (Build.VERSION.SDK_INT >= 14)
                mediaMetadataRetriever.setDataSource(videoPath, new HashMap<String, String>());
            else
                mediaMetadataRetriever.setDataSource(videoPath);
            //   mediaMetadataRetriever.setDataSource(videoPath);
            bitmap = mediaMetadataRetriever.getFrameAtTime();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Throwable(
                    "Exception in retriveVideoFrameFromVideo(String videoPath)"
                            + e.getMessage());

        } finally {
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
        }
        return bitmap;
    }

    public Apis getApi() {
        OkHttpClient.Builder b = new OkHttpClient.Builder();
        b.readTimeout(360000, TimeUnit.MILLISECONDS);
        b.writeTimeout(20000, TimeUnit.MILLISECONDS);
        OkHttpClient client = b.build();
        //http://schoolman.in/api
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://test.schoolman.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        Apis apis = retrofit.create(Apis.class);
        return apis;
    }
    public Apis getApischool() {
        OkHttpClient.Builder b = new OkHttpClient.Builder();
        b.readTimeout(360000, TimeUnit.MILLISECONDS);
        b.writeTimeout(20000, TimeUnit.MILLISECONDS);
        OkHttpClient client = b.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://test.schoolman.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        Apis apis = retrofit.create(Apis.class);
        return apis;
    }

    public Dialog getDialog(Context mContext) {
        ProgressDialog mDialog = new ProgressDialog(mContext);
        mDialog.setMessage("Loading...");
        return mDialog;
    }

    public void toToast(String toasetedString) {
        Toast.makeText(mContext, "" + toasetedString, Toast.LENGTH_SHORT).show();
    }

    public void toToastError() {
        Toast.makeText(mContext, "Check your internet connection", Toast.LENGTH_SHORT).show();
    }

    public boolean isValid(String email) {
        Matcher emailMatcher = EMAIL_PATTERN.matcher("" + email);
        if (!emailMatcher.matches()) {
            return false;
        }
        return true;
    }

    public String setCurrentTime_() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
        df.setLenient(false);
        Date today = new Date();
        String s = df.format(today);
        return s;
    }

    public void confirmDialog(String message) {
        new AlertDialog.Builder(mContext)
                .setTitle("Confirm")
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
