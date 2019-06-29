package com.schoolmanapp.ktct_school.school.driver;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.schoolmanapp.ktct_school.school.driver.Model.Mod_travel;
import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Lijo Mathew Theckanal on 15-Jun-17.
 */

public class LocationUpdateService extends Service implements LocationListener {

    AppPreferences appPreferences;
    IBinder mBinder;
    GPSTracker gpsTracker;
    Handler handler;
    int mStartMode;
    boolean mAllowRebind;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Double lat;
    Double lng;
    String strg_tid;
    private LocationManager locationManager;
    private String provider;

    private void callLocationUpdateApi() {
        try {

            Apiclass api = new Apiclass();

            final String latitude = Double.toString(gpsTracker.getLatitude());
            final String longtitude = Double.toString(gpsTracker.getLongitude());


//            Sharedpreference pref=new Sharedpreference(getBaseContext(),"pref");
//            int trip=pref.getInt("Tripid");
//            String trip_id=Integer.toString(trip);

            //   Log.e("Tripid",trip+"");
//            Log.e("Lat",latitude);
//            Log.e("Longt",longtitude);


            Integer tid = appPreferences.getInt("pref_TripId");
            strg_tid = tid.toString(tid);
            Call<Mod_travel> call = api.getApi().UpdateLocation(strg_tid, longtitude, latitude);
            call.enqueue(new retrofit2.Callback<Mod_travel>() {
                @Override
                public void onResponse(Call<Mod_travel> call, Response<Mod_travel> response) {
                    if (response.isSuccess()) {
                        Toast.makeText(getBaseContext(), "Travelling" + latitude + longtitude, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getBaseContext(), "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getBaseContext(), "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Mod_travel> call, Throwable t) {
                    System.out.println("t.toString : " + t.toString());

                    Toast.makeText(getBaseContext(), "Network failure", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        appPreferences = new AppPreferences(getApplicationContext(), "Peekabo");

        database = FirebaseDatabase.getInstance();


        String busid = appPreferences.getData("CurrentBusId");
        Log.e("Busid", busid);
        myRef = database.getReference(busid);
        gpsTracker = new GPSTracker(getApplicationContext());

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);

        Location location = locationManager.getLastKnownLocation(provider);
        locationManager.requestLocationUpdates(provider, 400, 1, this);

        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            LatLng latLng = new LatLng(latitude, longitude);

//            googleMap.addMarker(new MarkerOptions().position(latLng));
//            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//            googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        }
        handler = new Handler();
        hadler();
        //   callLocationUpdateApi();
        return mStartMode;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return mAllowRebind;
    }

    @Override
    public void onRebind(Intent intent) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
        try {
            locationManager.removeUpdates(this);
            handler.removeCallbacksAndMessages(null);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        //Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onLocationChanged(Location location) {


        //  locationTv.setText("Latitude:" + latitude + ", Longitude:" + longitude);
        lat = location.getLatitude();
        lng = location.getLongitude();
        myRef.setValue(lat + "," + lng);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();
        Location location = locationManager.getLastKnownLocation(provider);
        locationManager.requestLocationUpdates(provider, 400, 1, this);

        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        }


    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled   provider " + provider,
                Toast.LENGTH_LONG).show();

    }

    public void hadler() {
        gpsTracker = new GPSTracker(getApplicationContext());

        // Toast.makeText(this, "Service Running: " + gpsTracker.getLatitude() + "  " + gpsTracker.getLongitude(), Toast.LENGTH_SHORT).show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hadler();
                callLocationUpdateApi();
            }
        }, 150000);
    }
}