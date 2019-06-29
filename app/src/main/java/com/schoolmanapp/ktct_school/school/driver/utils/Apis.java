package com.schoolmanapp.ktct_school.school.driver.utils;


import com.schoolmanapp.ktct_school.school.driver.Model.Mod_BusId;
import com.schoolmanapp.ktct_school.school.driver.Model.Mod_login;
import com.schoolmanapp.ktct_school.school.driver.Model.Mod_start_response;
import com.schoolmanapp.ktct_school.school.driver.Model.Mod_stop;
import com.schoolmanapp.ktct_school.school.driver.Model.Mod_travel;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * Created by sics on 1/29/2017.
 */

public interface Apis {



    @FormUrlEncoded
    @POST("Driver/DriverLogin")
    Call<Mod_login> loginmodel(@Field("driverSpecialId") String driverSpecialId);

    @FormUrlEncoded
    @POST("Driver/BusIdentification")
    Call<Mod_BusId> busidmodel(@Field("busSpecialId") String busSpecialId,
                               @Field("tripNo") String tripNo,
                               @Field("schoolId") String schoolId);



    @POST("Driver/TripStart")
    Call<Mod_start_response> start_trip(@Body RequestBody object);

    @FormUrlEncoded
    @POST("Driver/Travelling")
    Call<Mod_travel> UpdateLocation(@Field("tripId") String tripId,
                                    @Field("longitude") String longitude,
                                    @Field("latitude") String latitude);


    @FormUrlEncoded
    @POST("Driver/TripComplete")
    Call<Mod_stop> endtrip(@Field("tripId") String tripId,
                           @Field("completeTime") String completeTime,
                           @Field("latitude") String latitude,
                           @Field("longitude") String longitude);





}

