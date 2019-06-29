package com.schoolmanapp.ktct_school.school.driver;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.driver.Model.Mod_start_request;
import com.schoolmanapp.ktct_school.school.driver.Model.Mod_start_response;
import com.schoolmanapp.ktct_school.school.driver.Model.Mod_stop;
import com.schoolmanapp.ktct_school.school.school.common.BaseActivity;
import com.schoolmanapp.ktct_school.school.driver.common.Sharedpreference;
import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;

import com.google.gson.Gson;


import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by srishtiinnovative on 19/05/17.
 */

public class Startstop extends BaseActivity {
    RequestBody AccessTokenValue = null;
    String json = null;
    @Bind(R.id.button1)
    TextView btn_gps;
    @Bind(R.id.txtbtn_start)
    TextView tv_start;
    @Bind(R.id.txtbtn_end)
    TextView tv_end;
    @Inject
    Utils utils;
    @Inject
    AppPreferences appPreferences;
    AlertDialog pd;
    int sid, driverid;
    String loc_start, loc_end, bus_id, trip_num, tripdate, schoolid, driver_id, cur_lat, cur_long,strg_tid,shift_status;
    Boolean status;
    Context context;
    Intent intent1;
    int f=0;

    LocationManager locationManager ;
    boolean GpsStatus=true ;



    GPSTracker gpsTracker;
     Location mCurrentLocation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trip_selection);
        ButterKnife.bind(this);
        pd = new SpotsDialog(this);

        getApp().getActivityComponent().inject(this);
        gpsTracker = new GPSTracker(getApplicationContext());
        status=appPreferences.getDataBoolean("status");

        if(status)
        {tv_start.setVisibility(View.INVISIBLE);
            tv_end.setVisibility(View.VISIBLE);
        }
        sid = appPreferences.getInt("School_id");
        schoolid = Integer.toString(sid);
        bus_id = appPreferences.getData("BusId");
        driverid = appPreferences.getInt("Driver_id");
        driver_id = Integer.toString(driverid);
        trip_num = appPreferences.getData("Tripno");
        loc_start = appPreferences.getData("locationstart");
        loc_end = appPreferences.getData("locationend");
        shift_status = appPreferences.getData("shiftstatus");
        btn_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                intent1 = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent1);
                btn_gps.setVisibility(View.INVISIBLE);
             //   tv_start.setVisibility(View.VISIBLE);
                openDialog();

            }
        });


        tv_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPSStatus();

                if(GpsStatus == false)

                {
                    btn_gps.setVisibility(View.VISIBLE);
                    tv_start.setVisibility(View.INVISIBLE);
                  //  f=1;
                    utils.toToast("Please click enable button");
                }
                else {

                    openDialog();
                }

            }
        });
        tv_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirm_tripend();
            }
        });


    }

    public void openDialog() {

        final Dialog dialog = new Dialog(this); // Context, this, etc.
        dialog.setContentView(R.layout.dialog);
        Button cancel = (Button) dialog.findViewById(R.id.dialog_cancel);
        Button ok = (Button) dialog.findViewById(R.id.dialog_ok);
        dialog.setTitle("Continue or not?");
        dialog.show();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                    Mod_start_request start_req_object = null;
                    start_req_object = new Mod_start_request();

                    try {
                        Date now = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                        String tripdate = sdf.format(now);
                        Log.e("startdate", tripdate);
                        cur_lat = Double.toString(gpsTracker.getLatitude());
                        cur_long = Double.toString(gpsTracker.getLongitude());
                        start_req_object.setDriverId(driver_id);
                        start_req_object.setSchoolId(schoolid);
                        start_req_object.setTripDate(tripdate);
                        start_req_object.setBusId(bus_id);
                        start_req_object.setStartPlace(loc_start);
                        start_req_object.setEndPlace(loc_end);
                        start_req_object.setTripNo(trip_num);
                        start_req_object.setLatitude(cur_lat);
                        start_req_object.setLongitude(cur_long);
                        start_req_object.setShiftStatus(shift_status);
                        Log.e("details", driver_id + schoolid + tripdate + bus_id + loc_start + loc_end + trip_num + cur_lat + cur_long);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    try {
                        Gson gson = new Gson();
                        //att_model_object.studentList = student_list;
                        json = gson.toJson(start_req_object).trim();
                        AccessTokenValue = null;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    try {

                        AccessTokenValue = RequestBody.create(MediaType.parse("application/json"), json.getBytes("UTF-8"));

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    Log.e("AccessTokenValue:", "value" + json);

                    Log.e("AccessTokenValue:", "value" + AccessTokenValue);

                    // callRetrofitUpdateProfile(AccessTokenValue);
                    Call<Mod_start_response> call = utils.getApi().start_trip(AccessTokenValue);

                    call.enqueue(new Callback<Mod_start_response>() {
                        @Override
                        public void onResponse(Call<Mod_start_response> call, Response<Mod_start_response> response) {
                            if (response.isSuccess()) {
                                if(f==0)
                                    tv_start.setVisibility(View.INVISIBLE);


                                //  Toast.makeText(getApplicationContext(), response.body().getMsg() + "", Toast.LENGTH_SHORT).show();
                                Boolean f=response.body().isStatus();
                                int trip_id = response.body().getUserData();
                                if(trip_id>0 && f==true) {
                                    Sharedpreference pref = new Sharedpreference(getApplicationContext(), "pref");
                                    pref.saveInt("Tripid", trip_id);
                                    appPreferences.saveInt("pref_TripId", trip_id);
                                    appPreferences.saveDataBoolean("Bustravelstatus",true);

                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);

                                        appPreferences.saveData("CurrentBusId", bus_id);
                                        startService(new Intent(Startstop.this, LocationUpdateService.class));

                                }
                                else {
                                    int tid=response.body().getUserData();
                                    appPreferences.saveInt("pref_TripId", tid);
                                   if(response.body().getMsg().equals("This trip has been travelled!")) {
                                       utils.toToast(response.body().getMsg());
                                       Intent intent = new Intent(getApplicationContext(), Home.class);
                                       startActivity(intent);
                                   }
                                    else if(response.body().getMsg().equals("Bus is currently running.Please Stop the bus and try again"))
                                    {
                                       utils.toToast(response.body().getMsg());
                                       tv_end.setVisibility(View.VISIBLE);
                                   }
                                   else if(response.body().getMsg().equals("The Driver is currently travelling!"))
                                   {
                                       utils.toToast("This driver id is currently in use");
                                       Intent intent = new Intent(getApplicationContext(), Home.class);
                                       startActivity(intent);

                                   }
                                   else
                                       utils.toToast(response.body().getMsg()+"please start the trip once again");
                                    //if(f==1)
                                    Intent intent = new Intent(getApplicationContext(), Select_trip.class);
                                    startActivity(intent);

                                }


                            } else {
                                //Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();

                                utils.toToast(response.body().getMsg());
                            }
                        }

                        @Override
                        public void onFailure(Call<Mod_start_response> call, Throwable t) {

                            Toast.makeText(getApplicationContext(), "Network failure", Toast.LENGTH_SHORT).show();
                        }

                    });





            }
        });
    }


public void GPSStatus(){
    locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
    GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

}
public void confirm_tripend()
{
    final Dialog dialog = new Dialog(Startstop.this); // Context, this, etc.
    dialog.setContentView(R.layout.confirm_tripend);
    Button cancel = (Button) dialog.findViewById(R.id.dialog_cancel);
    Button ok = (Button) dialog.findViewById(R.id.dialog_ok);
    dialog.setTitle("Do you want to end the trip?");


    dialog.show();
    cancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog.dismiss();

        }
    });
    ok.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog.dismiss();
            tv_end.setVisibility(View.GONE);
            //  tv_start.setVisibility(View.VISIBLE);
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String tripdate = sdf.format(now);
            Integer tid=appPreferences.getInt("pref_TripId");
            strg_tid=tid.toString(tid);
            cur_lat = Double.toString(gpsTracker.getLatitude());
            cur_long = Double.toString(gpsTracker.getLongitude());


            pd.show();
            try {
                Call<Mod_stop> call = utils.getApi().endtrip(strg_tid, tripdate, cur_lat, cur_long);

                call.enqueue(new Callback<Mod_stop>() {
                    @Override
                    public void onResponse(Call<Mod_stop> call, Response<Mod_stop> response) {
                        if (response.isSuccess()) {
                            //    Toast.makeText(getApplicationContext(), response.body().getMsg() + "", Toast.LENGTH_SHORT).show();
                            stopService(new Intent(getBaseContext(), LocationUpdateService.class));
                            Intent i = new Intent(getBaseContext(), Home.class);
                            startActivity(i);
                            Toast.makeText(getApplicationContext(), "Trip Complete", Toast.LENGTH_SHORT).show();
                            appPreferences.saveDataBoolean("Bustravelstatus",false);

                            pd.dismiss();
                        } else {

                            if (pd.isShowing()) {

                                pd.dismiss();
                            }
                            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Mod_stop> call, Throwable t) {
                        if (pd.isShowing()) {

                            pd.dismiss();
                        }

                        Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                    }

                });
            }catch(Exception e)
            {
                e.printStackTrace();
            }





        }
    });

        }


}