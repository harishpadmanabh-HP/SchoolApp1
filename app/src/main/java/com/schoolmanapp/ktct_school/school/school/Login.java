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
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Login;
import com.schoolmanapp.ktct_school.school.school.common.BaseActivity;
import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

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

public class Login extends BaseActivity implements Validator.ValidationListener {
@Bind(R.id.img_login)
    TextView img_login;
//    @Bind(R.id.txt_register)
//    TextView tv_register;
AlertDialog pd;
    @NotEmpty
    @Email
    @Bind(R.id.et_log_email)
    EditText et_email;

    @NotEmpty
    @Password
    @Bind(R.id.et_log_pass)
    EditText et_pass;

    @Bind(R.id.txt_fgtpass)
    TextView tv_fgtpass;

    String email,pass;
    @Inject
    Utils utils;
    @Inject
    AppPreferences appPreferences;
    Validator validator;
    @Bind(R.id.scholol_logo)
    ImageView iv_logo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);

        validator = new Validator(this);
        validator.setValidationListener(this);
        //tv_fgtpass.setTextSize(getResources().getDimension(R.dimen.text_size));


        getApp().getActivityComponent().inject(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        iv_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.schoolmanapp.ktct_school.school.parent.Java_class.webview.class);
                startActivity(intent);
            }
        });

        img_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validator.validate();
 }
        });

//
//        tv_register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), Registration.class);
//                startActivity(i);
//
//            }
//        });
        tv_fgtpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Forgotpassword.class);
                startActivity(i);
            }
        });
    }


    @Override
    public void onValidationSucceeded() {


        email = et_email.getText().toString();
        pass = et_pass.getText().toString();
        pd = new SpotsDialog(this);
        pd.show();

        Call<Mod_Login> call = utils.getApi().loginmodel(email, pass);

        call.enqueue(new Callback<Mod_Login>() {
            @Override
            public void onResponse(Call<Mod_Login> call, Response<Mod_Login> response) {
                if (response.isSuccess()) {
                    try {

                        String msg=response.body().getMsg();
                        if(msg.equals("Success")) {

                        int sid=response.body().getUserData().getSchoolId();
                        String name= response.body().getUserData().getSchoolName();
                        String addr=response.body().getUserData().getAddress();
                        String city=response.body().getUserData().getCity();
                        String contact=response.body().getUserData().getContact();
                        String email=response.body().getUserData().getLogin().getUsername();
                        String path=response.body().getUserData().getFilePath();
                        String state=response.body().getUserData().getState();
                        String web=response.body().getUserData().getWebsite();
                            String lat=response.body().getUserData().getLatitude();
                            String longt=response.body().getUserData().getLongitude();


                            appPreferences.saveInt("scid",sid);
                            appPreferences.saveData("name",name);
                            appPreferences.saveData("addr",addr);
                            appPreferences.saveData("city",city);
                            appPreferences.saveData("contact",contact);
                            appPreferences.saveData("email",email);
                            appPreferences.saveData("path",path);
                            appPreferences.saveData("state",state);
                            appPreferences.saveData("web",web);
                            appPreferences.saveData("lat",lat);
                            appPreferences.saveData("longt",longt);
                            appPreferences.saveDataBoolean("isLogin", true);
                            appPreferences.saveData("login_name", "School");
                            //utils.toToast(response.body().getMsg());

                            Intent i = new Intent(Login.this, Home.class);
                            if(pd.isShowing())
                            {
                                utils.toToast("Login Successfull");
                                pd.dismiss();
                            }

                            startActivity(i);

                        }

                        else {
                            if(pd.isShowing())
                            {
                                pd.dismiss();
                            }

                            utils.toToast("Login Failed");


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
                public void onFailure (Call < Mod_Login > call, Throwable t){
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


    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(getApplicationContext(), FourInOne.class);
        startActivity(i);// your code.
    }
}


