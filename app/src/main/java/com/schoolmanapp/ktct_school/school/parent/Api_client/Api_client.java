package com.schoolmanapp.ktct_school.school.parent.Api_client;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by srishtiinnovative on 08/06/17.
 */

public class Api_client {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://test.schoolman.in/api/Parent/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
    public static Retrofit getMessage() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://test.schoolman.in/api/School/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static Retrofit getforgotpass() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://test.schoolman.in/api/ForgotPassword/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
    public static Retrofit getClientfees() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://test.schoolman.in/api/Account/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
    public static Retrofit getevent() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://test.schoolman.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
    public static Retrofit removekid() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://test.schoolman.in/api/Parent/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }



}
