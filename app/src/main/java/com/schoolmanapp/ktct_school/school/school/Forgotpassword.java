package com.schoolmanapp.ktct_school.school.school;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.Model.model_forgot_pass;
import com.schoolmanapp.ktct_school.school.school.common.BaseActivity;
import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
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
 * Created by srishtiinnovative on 29/05/17.
 */

public class Forgotpassword extends BaseActivity implements Validator.ValidationListener {
    @Bind(R.id.send)
    TextView iv_send;
    @Bind(R.id.fgtpass_close)
    ImageView iv_fgtpass;

    @NotEmpty
    @Email
@Bind(R.id.email)
    EditText et_email;
    @Inject
    Utils utils;
    @Inject
    AppPreferences appPreferences;
    Validator validator;
    String email;
AlertDialog pd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword);
        ButterKnife.bind(this);
        getApp().getActivityComponent().inject(this);
        validator = new Validator(this);
        validator.setValidationListener(this);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
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

        email = et_email.getText().toString();

        pd = new SpotsDialog(this);
        pd.show();

        Call<model_forgot_pass> call = utils.getApi().forgotpswd("0",email);

        call.enqueue(new Callback<model_forgot_pass>() {
            @Override
            public void onResponse(Call<model_forgot_pass> call, Response<model_forgot_pass> response) {
                if (response.isSuccess()) {
                    try {

                        String msg=response.body().getMsg();
                        if(msg.equals("Successfull")) {


                            Intent i = new Intent(Forgotpassword.this, Login.class);
                            if(pd.isShowing())
                            {
                                pd.dismiss();
                            }

                            startActivity(i);

                        }

                        else {
                            if(pd.isShowing())
                            {
                                pd.dismiss();
                            }

                            utils.toToast(response.body().getMsg());


                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        if(pd.isShowing())
                        {
                            pd.dismiss();
                        }

                        Toast.makeText(getApplication(),"Error",Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure (Call < model_forgot_pass > call, Throwable t){
                if(pd.isShowing())
                {
                    pd.dismiss();
                }
                utils.toToast("Network failure");
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
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
