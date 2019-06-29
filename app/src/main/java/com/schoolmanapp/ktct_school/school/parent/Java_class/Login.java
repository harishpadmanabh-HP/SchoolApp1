package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.app.Activity;
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

import com.schoolmanapp.ktct_school.school.school.FourInOne;
import com.schoolmanapp.ktct_school.school.parent.API_interface.API_interface;
import com.schoolmanapp.ktct_school.school.parent.Api_client.Api_client;
import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.model_class.login_model;
import com.schoolmanapp.ktct_school.school.parent.utils.AppPreferences;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by srishtiinnovative on 29/05/17.
 */

public class Login extends Activity implements Validator.ValidationListener {
    @Bind(R.id.img_login)
    TextView img_login;

    @Bind(R.id.txt_register)
    TextView tv_register;

    @Bind(R.id.txt_fgtpass)
    TextView tv_fgtpass;

    @NotEmpty
     @Email
    @Bind(R.id.et_email_login)//Test Email@gmail.com
            EditText et_email;

    @NotEmpty
    @Password(min = 6)
    @Bind(R.id.et_password_login)//Test Password
            EditText et_password;
@Bind(R.id.parent_logo)
        ImageView iv_logo;
    Validator validator;
    AppPreferences appPreferences;
    AlertDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_parent);
        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        dialog = new SpotsDialog(this);
        img_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validator.validate();
            }
        });


        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Registration.class);
                startActivity(i);

            }
        });
iv_logo.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), com.schoolmanapp.ktct_school.school.parent.Java_class.webview.class);
        startActivity(intent);
    }
});

        tv_fgtpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Forgotpassword.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(getApplicationContext(), FourInOne.class);
        startActivity(i);// your code.
    }

    @Override
    public void onValidationSucceeded() {
        dialog.show();
        final API_interface api = Api_client.getClient().create(API_interface.class);
        String device_token=appPreferences.getData("refreshedToken");
        Call<login_model> call = api.loginModelCall(et_email.getText().toString(), et_password.getText().toString(),device_token);
        call.enqueue(new retrofit2.Callback<login_model>() {

            @Override
            public void onResponse(Call<login_model> call, Response<login_model> response) {
                if (response.isSuccess()) {
                    if (response.body().getMsg().equals("Success")) {
                        //  List<login_model.UserDataBean> details = response.body().getUserData();

                        int parent_id = response.body().getUserData().getParentId();
                        appPreferences.saveInt("parent_id", parent_id);

                        String parent_name = response.body().getUserData().getParentName();
                        appPreferences.saveData("parent_name", parent_name);

                        String parent_filepath = response.body().getUserData().getFilePath();
                        appPreferences.saveData("parent_filepath", parent_filepath);

                        String parent_address = response.body().getUserData().getAddress();
                        appPreferences.saveData("parent_address", parent_address);

                        String parent_email = response.body().getUserData().getEmail();
                        appPreferences.saveData("parent_email", parent_email);

                        String parent_contact = response.body().getUserData().getContactNumber();
                        appPreferences.saveData("parent_contact", parent_contact);

                        String parent_state = response.body().getUserData().getState();
                        appPreferences.saveData("parent_state", parent_state);

                        String parent_city = response.body().getUserData().getCity();
                        appPreferences.saveData("parent_city", parent_city);
                        appPreferences.saveDataBoolean("isLogin", true);
                        appPreferences.saveData("login_name", "Parent");


                        Intent i = new Intent(getApplicationContext(), Home.class);
                        startActivity(i);
                        dialog.dismiss();
                      //  Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<login_model> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
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
