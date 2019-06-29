package com.schoolmanapp.ktct_school.school.parent.Java_class;

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
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.API_interface.API_interface;
import com.schoolmanapp.ktct_school.school.parent.Api_client.Api_client;
import com.schoolmanapp.ktct_school.school.parent.model_class.model_travellingstatus;
import com.schoolmanapp.ktct_school.school.parent.utils.AppPreferences;

import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Lijo Mathew Theckanal on 27-Jun-17.
 */

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "HAHAHAHAHA";
    @Bind(R.id.tvCurrentLocationss)
    TextView tvCurrentLocation;
    @Bind(R.id.driver_name)
    TextView tv_driver_name;
    @Bind(R.id.driver_number)
    TextView tv_driver_number;
    @Bind(R.id.call)
    LinearLayout call;
    @Bind(R.id.bus_run_par)
    ImageView iv_run;
    @Bind(R.id.bus_stop_par)
    ImageView iv_stop;
    AppPreferences appPreferences;
    String lats, longitude, driver_number;
    AlertDialog dialog;
    int kid_id;
    int i;
    String BUSID;
    float currentZoom = 17;
    SupportMapFragment mapFragment;
    LatLng sydney;
    private GoogleMap mMap;
    private Marker m;
    private int y = 0;
    private boolean isZoomed = false;
    private String busId = "";
    @Bind(R.id.iv_settings)
    ImageView iv_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_map);
        ButterKnife.bind(this);
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        kid_id = appPreferences.getInt("kid_id");
        //  busId = getIntent().getStringExtra("BusId");
        apicall();
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mapFragment.getView().setVisibility(View.INVISIBLE);

        dialog = new SpotsDialog(this);
        dialog.show();
        BUSID = appPreferences.getInt("busid") + "";

        lats = appPreferences.getData("latitude");
        Log.e("latitude!!!!!", lats);

        longitude = appPreferences.getData("longitude");
        Log.e("longitude!!!", longitude);

        //////////////////

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(BUSID);

        try {
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if ((dataSnapshot.exists())) {

                        iv_run.setVisibility(View.VISIBLE);
                        iv_stop.setVisibility(View.INVISIBLE);
                        //    dialog.dismiss();
                        String value = dataSnapshot.getValue(String.class);
                        //  Toast.makeText(getApplicationContext(), "!!!!"+i, Toast.LENGTH_SHORT).show();
                        String[] x = value.split(",");
                        Log.e("value", "value" + x);
                        // tvLocation.setText(value);
                        try {
                            sydney = new LatLng(Double.parseDouble(x[0]), Double.parseDouble(x[1]));
                            if (!isZoomed) {
                                isZoomed = true;
                                BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.bus_marker);
                                m = mMap.addMarker(new MarkerOptions().position(sydney).title(value).icon(icon));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 17));
                                mapFragment.getView().setVisibility(View.VISIBLE);
                                getLocationName(Double.parseDouble(x[0]), Double.parseDouble(x[1]));

                            }
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                        updateMarker(Double.parseDouble(x[0]), Double.parseDouble(x[1]));
                        y++;
                        if (y == 1) {
                            getLocationName(Double.parseDouble(x[0]), Double.parseDouble(x[1]));
                        }
                        if (y == 250) {
                            y = 0;
                        }
                    } else {
                        iv_stop.setVisibility(View.VISIBLE);
                        iv_run.setVisibility(View.INVISIBLE);

                        try {
                            LatLng sydney = new LatLng(Double.parseDouble(lats), Double.parseDouble(longitude));
                            if (!isZoomed) {

                                isZoomed = true;
                                BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.mipmap.my_parent);
                                m = mMap.addMarker(new MarkerOptions().position(sydney).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_marker)));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 17));
                                mapFragment.getView().setVisibility(View.VISIBLE);
                                getLocationName(Double.parseDouble(lats), Double.parseDouble(longitude));
                                dialog.dismiss();

                            }
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                }


                @Override
                public void onCancelled(DatabaseError error) {
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });
        } catch (NullPointerException e) {

        }


        ///////////////////


//        String driver_name = appPreferences.getData("driver_name");
//        final String driver_number = appPreferences.getData("driver_number");
//        Log.e("driver_name", driver_name);
//        Log.e("driver_number", driver_number);
//        tv_driver_name.setText(driver_name);
//        tv_driver_number.setText(driver_number);
        iv_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent callIntent = new Intent(Intent.ACTION_CALL);
//                callIntent.setData(Uri.parse("tel:" + driver_number));//change the number
//                if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    return;
//                }
//                startActivity(callIntent);


                try {
                    if (Build.VERSION.SDK_INT > 22) {
                        if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling

                            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 101);

                            return;
                        }

                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + driver_number));
                        startActivity(callIntent);

                    } else {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + driver_number));
                        startActivity(callIntent);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            }
        });

    }

    public void getLocationName(double lat, Double lng) {
        try {
            Geocoder gcd = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses = gcd.getFromLocation(lat, lng, 1);
            if (addresses.size() > 0) {
            //    System.out.println("Location"+addresses.get(0).getSubLocality());
               // String v =addresses.get(0).getLocality();
                if (addresses.get(0).getSubLocality()==null) {

                    tvCurrentLocation.setText("" + addresses.get(0).getLocality());
                } else {
                    tvCurrentLocation.setText("" + addresses.get(0).getSubLocality());
                }

            } else {
                // do your staff
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMarker(double lat, Double lng) {

        LatLng markerLatLng = new LatLng(lat, lng);
        m.setTitle(lat + "," + lng);
        m.setPosition(markerLatLng);
        sydney = new LatLng(lat, lng);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 17));
        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {


            @Override
            public void onCameraChange(CameraPosition pos) {
                if (pos.zoom != currentZoom) {
                    currentZoom = pos.zoom;
                    //utils.toToast("zoom value"+currentZoom);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, currentZoom));
                }
            }
        });
    }


    void apicall() {

        final API_interface api = Api_client.getClient().create(API_interface.class);
        // Call<model_travellingstatus> call = api.kidtravel_statusModelCall(kid_id);
        Call<model_travellingstatus> call = api.kidtravel_statusModelCall(kid_id);
        call.enqueue(new retrofit2.Callback<model_travellingstatus>() {

            @Override
            public void onResponse(Call<model_travellingstatus> call, Response<model_travellingstatus> response) {
                if (response.isSuccess()) {
                    if (response.body().isStatus() == false) {

                        tv_driver_name.setText("No Driver found");
                        tv_driver_number.setText("No Number available");
                        iv_call.setVisibility(View.GONE);
                        i = 0;
                        iv_stop.setVisibility(View.VISIBLE);
                        iv_run.setVisibility(View.INVISIBLE);
                        //////////////////////////////////////////


                        //  Toast.makeText(getApplicationContext(),"not getting the location"+i,Toast.LENGTH_SHORT).show();
                        try {
                            LatLng sydney = new LatLng(Double.parseDouble(lats), Double.parseDouble(longitude));
                            if (!isZoomed) {
//                                isZoomed = true;
//                                BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.bus_marker);
//                                m = mMap.addMarker(new MarkerOptions().position(sydney).title(value).icon(icon));
//                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 17));
//                                mapFragment.getView().setVisibility(View.VISIBLE);

                                isZoomed = true;
                                BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.mipmap.my_parent);

                                m = mMap.addMarker(new MarkerOptions().position(sydney).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_marker)));


                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 17));
                                mapFragment.getView().setVisibility(View.VISIBLE);
                                getLocationName(Double.parseDouble(lats), Double.parseDouble(longitude));
                                dialog.dismiss();
                            }
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                        //Toast.makeText(getApplicationContext(), "The Bus has not started its journery", Toast.LENGTH_SHORT).show();
                        ////////////////////

                        //  Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    } else {
                        //      Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                        iv_run.setVisibility(View.VISIBLE);
                        iv_stop.setVisibility(View.INVISIBLE);
                        tv_driver_name.setText(response.body().getUserData().getDriverName());
                        tv_driver_number.setText(response.body().getUserData().getContactNumber());
                        driver_number = response.body().getUserData().getContactNumber();
                        i = 1;
                        dialog.dismiss();
                        //  dialog.dismiss();

                        Log.e("bus id!!", BUSID);
                        /////////////////////////////////////////////////////////////////


                        /////////////////////////////////////


                    }
                } else {
                    iv_stop.setVisibility(View.VISIBLE);
                    iv_run.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), response.body().getMsg() + "13243242", Toast.LENGTH_SHORT).show();
                    tv_driver_name.setText("No Driver found");
                    tv_driver_number.setText("No Number available");
                    iv_call.setVisibility(View.GONE);
                    i = 0;
                    dialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<model_travellingstatus> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

        });


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }
}