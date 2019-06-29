package com.schoolmanapp.ktct_school.school.driver;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.driver.Model.Mod_BusId;
import com.schoolmanapp.ktct_school.school.school.common.BaseActivity;
import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by srishtiinnovative on 19/05/17.
 */

public class Home extends BaseActivity implements Validator.ValidationListener {
    Spinner sp;
    int spinbusid_pos,sid,shiftid=0;
    String shift_status;
    String shift[]={"Select Shift","Morning","Afternoon"};
    Validator validator;
    @Bind(R.id.logout)
    ImageView iv_logout;
    @Bind(R.id.img_next)
    TextView iv_next;
    @Bind(R.id.sp_shift)
    Spinner sp_shift;
    @NotEmpty
    @Bind(R.id.ed_busid)
    EditText et_bid;
    @NotEmpty
    @Bind(R.id.tp_id)
    EditText et_tid;
    @Inject
    Utils utils;
    @Inject
    AppPreferences appPreferences;
    AlertDialog pd;
    String tripid;
    private static final int PERMISSION_CALLBACK_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;
    String[] permissionsRequired = new String[]{Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    private SharedPreferences permissionStatus;
    private boolean sentToSettings = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_driver);
        validator = new Validator(this);
        validator.setValidationListener(this);
        ButterKnife.bind(this);
        getApp().getActivityComponent().inject(this);
        permissionStatus = getSharedPreferences("permissionStatus",MODE_PRIVATE);
        permisoncheck();

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getApplicationContext(), R.layout.custom_spinner, shift);

        // Drop down layout style - list view with radio button
        try{
            dataAdapter1.setDropDownViewResource(R.layout.custom_dropdown);
        }catch (Exception e)
        {
            utils.toToast("No shift added for the bus");
        }

        dataAdapter1.notifyDataSetChanged();
        sp_shift.setAdapter(dataAdapter1);
        sp_shift.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position==1)
                {
                    shift_status="0";
                    shiftid=position;

                }
                else if(position==2) {
                    shift_status = "1";
                    shiftid=position;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        iv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( shiftid>0) {
                    validator.validate();
                }

                else if(shiftid<=0)
                {
                    TextView errorText = (TextView) sp_shift.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("Please select a shift");//changes the selected item text to this
                }

            }
        });
        iv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(Home.this); // Context, this, etc.
                dialog.setContentView(R.layout.confirm_logout);
                Button cancel = (Button) dialog.findViewById(R.id.dialog_cancel);
                Button ok = (Button) dialog.findViewById(R.id.dialog_ok);
                dialog.setTitle("Do you want to logout?");


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
                        appPreferences.saveDataBoolean("isLogin",false);
                      //  appPreferences.saveData("login_name","invalid");
                        moveTaskToBack(true);
                        finish();
                        Intent i = new Intent(getBaseContext(), Login.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);



                    }
                });

            }
        });

    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        Intent i = new Intent(getBaseContext(), Home.class);
        startActivity(i);

    }


    @Override
    public void onValidationSucceeded() {
        String busid=et_bid.getText().toString();
        tripid=et_tid.getText().toString();
        int sid= appPreferences.getInt("School_id");
        String scid=Integer.toString(sid);
        final String status=shift_status;
        pd = new SpotsDialog(this);
        try
        {
        pd.show();

        Call<Mod_BusId> call = utils.getApi().busidmodel(busid,tripid,scid);

        call.enqueue(new Callback<Mod_BusId>() {

            @Override
            public void onResponse(Call<Mod_BusId> call, Response<Mod_BusId> response) {
                if (response.isSuccess()) {
                    String msg = response.body().getMsg();




                    if (msg.equals("Success")) {
                    String location_end=response.body().getUserData().getLocationEnd();
                        String location_start=response.body().getUserData().getLocationStart();
                        int bus_id=response.body().getUserData().getBusId();
                        String trip_no=response.body().getUserData().getTripNumber();
                        String busid=Integer.toString(bus_id);


                        appPreferences.saveData("locationstart", location_start);
                        appPreferences.saveData("locationend", location_end);
                        appPreferences.saveData("BusId",busid);
                        appPreferences.saveData("Tripno",tripid);
                        appPreferences.saveData("shiftstatus",shift_status);
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                        Intent i = new Intent(getBaseContext(), Select_trip.class);
                       // if (pd.isShowing()) {
                            pd.dismiss();
                        //}

                        startActivity(i);
                    } else {
                        if (pd.isShowing()) {
                            pd.dismiss();


                            utils.toToast(response.body().getMsg());
                        }


                    }
                }

            }

            @Override
            public void onFailure(Call<Mod_BusId> call, Throwable t) {
                if(pd.isShowing())
                {
                    pd.dismiss();
                }
                utils.toToast("Network failure");
            }

        });


        }catch(Exception e)
        {
            e.printStackTrace();
            if(pd.isShowing())
            {
                pd.dismiss();
            }

            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }


    }
    private void permisoncheck() {
        if (ActivityCompat.checkSelfPermission(Home.this, permissionsRequired[0]) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(Home.this, permissionsRequired[1]) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(Home.this, permissionsRequired[2]) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(Home.this, permissionsRequired[0])
                    || ActivityCompat.shouldShowRequestPermissionRationale(Home.this, permissionsRequired[1])
                    || ActivityCompat.shouldShowRequestPermissionRationale(Home.this, permissionsRequired[2])) {
                //Show Information about why you need the permission
                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                builder.setTitle("Need Multiple Permissions");
                builder.setMessage("This app needs Camera and Contacts permissions.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(Home.this, permissionsRequired, PERMISSION_CALLBACK_CONSTANT);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } else if (permissionStatus.getBoolean(permissionsRequired[0], false)) {
                //Previously Permission Request was cancelled with 'Dont Ask Again',
                // Redirect to Settings after showing Information about why you need the permission
                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                builder.setTitle("Need Multiple Permissions");
                builder.setMessage("This app needs Camera and Contacts permissions.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        sentToSettings = true;
                        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                        Toast.makeText(getBaseContext(), "Go to Permissions to Grant  Camera and Contacts", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } else {
                //just request the permission
                ActivityCompat.requestPermissions(Home.this, permissionsRequired, PERMISSION_CALLBACK_CONSTANT);
            }

            SharedPreferences.Editor editor = permissionStatus.edit();
            editor.putBoolean(permissionsRequired[0], true);
            editor.commit();
        } else {
            //You already have the permission, just go ahead.
            proceedAfterPermission();

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_CALLBACK_CONSTANT){
            //check if all permissions are granted
            boolean allgranted = false;
            for(int i=0;i<grantResults.length;i++){
                if(grantResults[i]==PackageManager.PERMISSION_GRANTED){
                    allgranted = true;
                } else {
                    allgranted = false;
                    break;
                }
            }

            if(allgranted){
                proceedAfterPermission();
            } else if(ActivityCompat.shouldShowRequestPermissionRationale(Home.this,permissionsRequired[0])
                    || ActivityCompat.shouldShowRequestPermissionRationale(Home.this,permissionsRequired[1])
                    || ActivityCompat.shouldShowRequestPermissionRationale(Home.this,permissionsRequired[2])){

                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                builder.setTitle("Need Multiple Permissions");
                builder.setMessage("This app needs Camera and Location permissions.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(Home.this,permissionsRequired,PERMISSION_CALLBACK_CONSTANT);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } else {
                Toast.makeText(getBaseContext(),"Unable to get Permission",Toast.LENGTH_LONG).show();
            }
        }
    }


    private void proceedAfterPermission() {
        //   Toast.makeText(getBaseContext(), "We got All Permissions", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (sentToSettings) {
            if (ActivityCompat.checkSelfPermission(Home.this, permissionsRequired[0]) == PackageManager.PERMISSION_GRANTED) {
                //Got Permission
                proceedAfterPermission();
            }
        }
    }

}
