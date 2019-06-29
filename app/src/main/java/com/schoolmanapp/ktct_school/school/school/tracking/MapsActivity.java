package com.schoolmanapp.ktct_school.school.school.tracking;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.Model.Model_driverdetails;
import com.schoolmanapp.ktct_school.school.school.common.BaseActivity;
import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lijo Mathew Theckanal on 27-Jun-17.
 */

public class MapsActivity extends BaseActivity implements OnMapReadyCallback {


    private static final String TAG = "HAHAHAHAHA";
    private GoogleMap mMap;
    Marker m;
    //GPSTracker gpsTracker;
    @Bind(R.id.tvCurrentLocation)
    TextView tvCurrentLocation;
    @Bind(R.id.driver_name)
    TextView tvdriver_name;
    @Bind(R.id.driver_number)
    TextView tvdriver_number;
    @Bind(R.id.iv_phone)
    ImageView iv_call;

    int y = 0;
    boolean f=false;
    boolean isZoomed = false;
    String busId = "",Addrs;
    Integer st;
//    @Bind(R.id.btn_recenter)
//    Button ll_bt;
    @Inject
    Utils utils;
    @Inject
    AppPreferences appPreferences;
    Double dblat,dblongt;
    CameraPosition SYDNEY;
    private float previousZoomLevel = -1.0f;

     float currentZoom = 17;
    LatLng sydney;
    String dr_phno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);


        getApp().getActivityComponent().inject(this);


        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        final AlertDialog pd;
        pd = new SpotsDialog(this);
        pd.show();

        mapFragment.getMapAsync(this);
        mapFragment.getView().setVisibility(View.INVISIBLE);

        busId = getIntent().getStringExtra("BusId");
        driverapi(busId);
        st = getIntent().getIntExtra("status",0);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference(busId);
        String lat=appPreferences.getData("lat");
        String longt=appPreferences.getData("longt");
        dblat= Double.parseDouble(lat);
        dblongt=Double.parseDouble(longt);

    iv_call.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
//        Intent callIntent = new Intent(Intent.ACTION_CALL);
//        callIntent.setData(Uri.parse("tel:"+dr_phno));
//
//        if (ActivityCompat.checkSelfPermission(MapsActivity.this,
//                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        startActivity(callIntent);

        try
        {
            if(Build.VERSION.SDK_INT > 22)
            {
                if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling

                    ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 101);

                    return;
                }

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + dr_phno));
                startActivity(callIntent);

            }
            else {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + dr_phno));
                startActivity(callIntent);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

});

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                  //  if(!(dataSnapshot.exists())) {
                    if( (st==2) ||(st==3) ||(st==0)) {
//                        gpsTracker = new GPSTracker(getApplicationContext());
//                        Double lat= gpsTracker.getLatitude();
//                        Double longt=gpsTracker.getLongitude();

                        if(pd.isShowing())
                        pd.dismiss();
                        LatLng school = new LatLng(dblat, dblongt);

                        if (!isZoomed) {
                            isZoomed = true;
                            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.mipmap.my_parent);
                            m = mMap.addMarker(new MarkerOptions().position(school).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_marker)));

                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(school, 17));
                            mapFragment.getView().setVisibility(View.VISIBLE);
                            //getLocationName(dblat,dblongt);
                            tvCurrentLocation.setText("Bus is not travelling");

                            //   Toast.makeText(getBaseContext(), "No Buses on service", Toast.LENGTH_SHORT).show();
                        }
                    }
                  //  }
                    else
                    {
                        if((dataSnapshot.exists())) {


                            if (pd.isShowing())
                                pd.dismiss();
                            String value = dataSnapshot.getValue(String.class);
                            Log.d(TAG, "Value is: " + value);
                            String[] x = value.split(",");

                            getLocationName(Double.parseDouble(x[0]), Double.parseDouble(x[1]));
                            try {


                                 sydney = new LatLng(Double.parseDouble(x[0]), Double.parseDouble(x[1]));








                                if (!isZoomed) {
                                    isZoomed = true;
                                    BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.bus_marker);
                                    m = mMap.addMarker(new MarkerOptions().position(sydney).title(value).icon(icon));
                                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 17));
                                    try{
                                        mapFragment.getView().setVisibility(View.VISIBLE);
                                    }catch(NullPointerException e)
                                    {
                                        e.printStackTrace();
                                    }




                                }



                            updateMarker(Double.parseDouble(x[0]), Double.parseDouble(x[1]));

                            y++;
                            if (y == 1) {
                                getLocationName(Double.parseDouble(x[0]), Double.parseDouble(x[1]));
                            }
                            if (y == 250) {
                                y = 0;
                            }
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                        else
                        {

                            if(pd.isShowing())
                                pd.dismiss();
                            LatLng school = new LatLng(dblat, dblongt);
                            getLocationName(dblat,dblongt);
                            if (!isZoomed) {
                                isZoomed = true;
                                BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.mipmap.my_parent);
                                m = mMap.addMarker(new MarkerOptions().position(school).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_marker)));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(school, 17));
                                mapFragment.getView().setVisibility(View.VISIBLE);

                            }
                        }
                    }

                    }



                @Override
                public void onCancelled(DatabaseError error) {

                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });
        }


    private void getLocationName(double lat, Double lng) {
        try {
            Geocoder gcd = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses = gcd.getFromLocation(lat, lng, 1);
            if (addresses.size() > 0) {
               // String p=addresses.get(0).getSubLocality();
                System.out.println(addresses.get(0).getSubLocality());
                if(addresses.get(0).getSubLocality()==null)
                {

                    tvCurrentLocation.setText("" + addresses.get(0).getLocality());
                }
                else {

                    tvCurrentLocation.setText("" + addresses.get(0).getSubLocality());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateMarker(double lat, Double lng) {

        LatLng markerLatLng = new LatLng(lat, lng);
        m.setTitle(lat + "," + lng);
        m.setPosition(markerLatLng);
         sydney = new LatLng(lat,lng);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, currentZoom));
        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {


            @Override
            public void onCameraChange(CameraPosition pos) {
                if (pos.zoom != currentZoom){
                    currentZoom = pos.zoom;
                    //utils.toToast("zoom value"+currentZoom);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, currentZoom));
                }
            }
        });
           }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
    }
void driverapi(String busId)
{

    Call<Model_driverdetails> call = utils.getApi().driverdetails(busId);


    call.enqueue(new Callback<Model_driverdetails>() {

        @Override
        public void onResponse(Call<Model_driverdetails> call, Response<Model_driverdetails> response) {
            if (response.isSuccess()) {
                //   utils.toToast(response.body().getMsg());


                String msg = response.body().getMsg();
                if (msg.equals("Success")) {
                    String dr_name = response.body().getUserData().getDriverName();
                    dr_phno= response.body().getUserData().getContactNumber();
                    tvdriver_name.setText(dr_name);
                    tvdriver_number.setText(dr_phno);


                }else if (msg.equals("Current bus is not travelling")){
                    tvdriver_name.setText("No Driver");
                    tvdriver_number.setText("No Number");
                    tvCurrentLocation.setText("Bus is not travelling");
                    iv_call.setVisibility(View.GONE);
                }


                else {

                  //  utils.toToast(response.body().getMsg());
                    tvdriver_name.setText("No Driver");
                    tvdriver_number.setText("No Number");
                    iv_call.setVisibility(View.GONE);


                }
            } else {


                utils.toToast(response.body().getMsg());


            }


        }

        @Override
        public void onFailure(Call<Model_driverdetails> call, Throwable t) {


            utils.toToast(t.getMessage());
        }

    });
}
}