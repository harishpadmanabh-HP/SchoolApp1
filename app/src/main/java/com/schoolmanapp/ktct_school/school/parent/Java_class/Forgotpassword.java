package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.API_interface.API_interface;
import com.schoolmanapp.ktct_school.school.parent.Api_client.Api_client;
import com.schoolmanapp.ktct_school.school.parent.model_class.model_forgot_pass;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by srishtiinnovative on 29/05/17.
 */

public class Forgotpassword extends Activity implements Validator.ValidationListener  {
    @Bind(R.id.send)
    TextView iv_send;
    @Bind(R.id.fgtpass_close)
    ImageView iv_fgtpass;

    @NotEmpty
    @Email
    @Bind(R.id.email)
    EditText email;
    Validator validator;
    AlertDialog dialog;
    String type="1";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
        dialog = new SpotsDialog(this);
        iv_send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });
        iv_fgtpass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onValidationSucceeded() {
        dialog.show();
        final API_interface api = Api_client.getforgotpass().create(API_interface.class);
        Call<model_forgot_pass> call = api.forgotpassModelCall(type,email.getText().toString());
        call.enqueue(new retrofit2.Callback<model_forgot_pass>() {

            @Override
            public void onResponse(Call<model_forgot_pass> call, Response<model_forgot_pass> response) {
                if (response.isSuccess()) {
                    if (response.body().getMsg().equals("Successfull")) {
                        //  List<login_model.UserDataBean> details = response.body().getUserData();




                        Intent i = new Intent(getApplicationContext(), Login.class);
                        startActivity(i);
                        dialog.dismiss();
                        //  Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "HIIIIII", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<model_forgot_pass> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();

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