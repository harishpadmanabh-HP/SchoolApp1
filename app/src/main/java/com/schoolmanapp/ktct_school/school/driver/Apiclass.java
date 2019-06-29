package com.schoolmanapp.ktct_school.school.driver;

import com.schoolmanapp.ktct_school.school.driver.utils.Apis;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by srishtiinnovative on 27/06/17.
 */

public class Apiclass {
    public Apis getApi() {
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
}
