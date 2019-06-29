package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.API_interface.API_interface;
import com.schoolmanapp.ktct_school.school.parent.Api_client.Api_client;
import com.schoolmanapp.ktct_school.school.parent.model_class.add_kid_model;
import com.schoolmanapp.ktct_school.school.parent.model_class.otp_request;
import com.schoolmanapp.ktct_school.school.parent.utils.AppPreferences;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Response;

public class Submit_otp extends AppCompatActivity implements Validator.ValidationListener {
    @NotEmpty
    @Bind(R.id.et_otp)
    EditText et_otp;
    @Bind(R.id.ll_retry)
    LinearLayout retry;
    @Bind(R.id.submit_otp)
    TextView submit;
    @Bind(R.id.tv_otp_name)
    TextView name;
    @Bind(R.id.tv_otp_class)
    TextView classdetails;
    @Bind(R.id.submit_otp_cancel)
    TextView cancel;
    Validator validator;
    String id,otp,tv_name,tv_classdiv,scl_id;
    AppPreferences appPreferences;
    int parent_id;
    AlertDialog dialog;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_otp);
        ButterKnife.bind(this);
        dialog = new SpotsDialog(this);
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        id=appPreferences.getData("otp_kid_id");
        tv_name=appPreferences.getData("otp_kid_name");
        tv_classdiv=appPreferences.getData("otp_kid_class");
        parent_id=appPreferences.getInt("parent_id");
        scl_id=appPreferences.getData("otp_school_id");
        name.setText(tv_name);
        classdetails.setText(tv_classdiv);
        Log.e("kididotp2",id);
        validator= new Validator(this);
        validator.setValidationListener(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });
        retry.setVisibility(View.INVISIBLE);
      retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               retry();
            }
        });
      showButtons();
      cancel.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(Submit_otp.this, Home.class);
              startActivity(intent);
              finish();
          }
      });
    }
    private void showButtons(){
        handler = new Handler();

        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
             retry.setVisibility(View.VISIBLE);
            }
        }, 60000);}
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Submit_otp.this, Home.class);
        startActivity(intent);
        finish();
    }
    public void retry(){
        dialog.show();
        final API_interface api= Api_client.getClient().create(API_interface.class);
        Call<otp_request> call=api.Otp_request(String.valueOf(parent_id),id,scl_id);
        call.enqueue(new retrofit2.Callback<otp_request>(){

            @Override
            public void onResponse(Call<otp_request> call, Response<otp_request> response) {
                if(response.isSuccess()){
                    if(response.body().isStatus()) {
                        appPreferences.saveData("otp_kid_id",id);
                        //  List<login_model.UserDataBean> details = response.body().getUserData();
                        if(response.body().getMsg().equals("Send an OTP in your contact number!")) {
                            Intent i = new Intent(getApplicationContext(), Submit_otp.class);
                            startActivity(i);
                            finish();
                            Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }

                    }
                    else {
                        Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<otp_request> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

        });
    }

    @Override
    public void onValidationSucceeded() {
        dialog.show();
        otp=et_otp.getText().toString();
        final API_interface api= Api_client.getClient().create(API_interface.class);
        Call<add_kid_model> call=api. Otp_submit(String.valueOf(parent_id),id,otp,scl_id);
        call.enqueue(new retrofit2.Callback<add_kid_model>(){

            @Override
            public void onResponse(Call<add_kid_model> call, Response<add_kid_model> response) {
                if(response.isSuccess()){
                    if(response.body().getMsg().equals("Success")) {

                            Intent i = new Intent(getApplicationContext(), Home.class);
                            startActivity(i);
                            Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();


                    }
                    else {
                        Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<add_kid_model> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

        });
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
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
