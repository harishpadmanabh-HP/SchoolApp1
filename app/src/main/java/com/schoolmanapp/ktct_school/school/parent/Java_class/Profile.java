package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.API_interface.API_interface;
import com.schoolmanapp.ktct_school.school.parent.Api_client.Api_client;
import com.schoolmanapp.ktct_school.school.parent.model_class.Model_logout;
import com.schoolmanapp.ktct_school.school.parent.model_class.login_model;
import com.schoolmanapp.ktct_school.school.parent.utils.AppPreferences;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by srishtiinnovative on 01/06/17.
 */

public class Profile extends Activity {
    ImageView iv_logout;

    AppPreferences appPreferences;


    @Bind(R.id.parent_image)
    ImageView image;

    @Bind(R.id.parent_name)
    TextView name;


    @Bind(R.id.address)
    TextView address;

    @Bind(R.id.state)
    TextView state;

    @Bind(R.id.city)
    TextView city;

    @Bind(R.id.email)
    TextView email;

    @Bind(R.id.phone)
    TextView phone;
AlertDialog dialog;

    String path;
    Integer pid;
    String parent_filepath="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        dialog = new SpotsDialog(this);

        iv_logout = (ImageView) findViewById(R.id.logout);
        ButterKnife.bind(this);

       // int parent_id = appPreferences.getInt("parent_id");


        String parent_name = appPreferences.getData("parent_name");
        name.setText(parent_name);
        pid=appPreferences.getInt("parent_id");
        parent_filepath = appPreferences.getData("parent_filepath");
        //Log.e("parent_filepath",parent_filepath);


        if(parent_filepath.equals("")) {
            image.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dummy));

        }
        else {
            path = "http://www.schoolman.in//" + parent_filepath;
            Picasso.with(this).load(path).into(image);
        }
        String parent_address = appPreferences.getData("parent_address");
        address.setText(parent_address);

        String parent_email = appPreferences.getData("parent_email");
        email.setText(parent_email);

        String parent_contact = appPreferences.getData("parent_contact");
        phone.setText(parent_contact);

        String parent_state = appPreferences.getData("parent_state");
        state.setText(parent_state);

        String parent_city = appPreferences.getData("parent_city");
        city.setText(parent_city);
        dialog.dismiss();
        iv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(Profile.this); // Context, this, etc.
                dialog.setContentView(R.layout.confirm_logout);
                Button cancel = (Button) dialog.findViewById(R.id.dialog_cancel);
                Button ok = (Button) dialog.findViewById(R.id.dialog_ok);
                dialog.setTitle("Do you want to Logout?");


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
                        logoutapi();

                    }
                });

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Profile.this, Home.class);
        startActivity(intent);
    }
    public void logoutapi() {
        final API_interface api = Api_client.getClient().create(API_interface.class);
        String device_token = appPreferences.getData("refreshedToken");
        Call<Model_logout> call = api.logoutModelcall(String.valueOf(pid), device_token);
        call.enqueue(new retrofit2.Callback<Model_logout>() {

            @Override
            public void onResponse(Call<Model_logout> call, Response<Model_logout> response) {
                if(response.isSuccess())
                {
                    if(response.body().getMsg().equals("Successfully Logout"))
                    {
                        appPreferences.saveDataBoolean("isLogin",false);
                        Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                    }
                    else {

                        Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<Model_logout> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
