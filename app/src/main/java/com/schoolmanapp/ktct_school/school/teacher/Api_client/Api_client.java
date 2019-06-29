package com.schoolmanapp.ktct_school.school.teacher.Api_client;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;


/**
 * Created by srishtiinnovative on 08/06/17.
 */

public class Api_client {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://test.schoolman.in/api/")
            //    .baseUrl("http://www.schoolman.in/api/Teacher/")

                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
