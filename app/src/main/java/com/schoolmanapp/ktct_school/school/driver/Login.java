package com.schoolmanapp.ktct_school.school.driver;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.driver.Model.Mod_login;
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
 * Created by srishtiinnovative on 29/05/17.
 */

public class Login extends BaseActivity implements Validator.ValidationListener {

    @Bind(R.id.img_login)
    TextView img_login;
    @NotEmpty
    @Bind(R.id.txtdid)
    EditText et_did;
@Bind(R.id.driver_logo)
ImageView iv_logo;
    @Inject
    Utils utils;
    @Inject
    AppPreferences appPreferences;
    Validator validator;
AlertDialog pd;
    private final int ASK_LOC_PERMISSION = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_driver);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        validator = new Validator(this);
        validator.setValidationListener(this);
        ButterKnife.bind(this);
        getApp().getActivityComponent().inject(this);
        img_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();

            }
        });

        if (shouldAskPermission()) {
            askPermissionForWrite();
        }
        iv_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, com.schoolmanapp.ktct_school.school.parent.Java_class.webview.class);
                startActivity(intent);
            }
        });
    }

    private boolean shouldAskPermission() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    private void askPermissionForWrite() {
        String[] perms = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
        requestPermissions(perms, ASK_LOC_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {
        switch (permsRequestCode) {
            case ASK_LOC_PERMISSION:
                boolean writeAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (writeAccepted) {
                } else {
                    Toast.makeText(getBaseContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    @Override
    public void onValidationSucceeded() {

        String did = et_did.getText().toString();
        pd = new SpotsDialog(this);
        try{
        pd.show();

        Call<Mod_login> call = utils.getApi().loginmodel(did);

        call.enqueue(new Callback<Mod_login>() {
            @Override
            public void onResponse(Call<Mod_login> call, Response<Mod_login> response) {
                if (response.isSuccess()) {
                    try {
                        String msg = response.body().getMsg();



                        if (msg.equals("Success")) {
                            int dr_id = response.body().getUserData().getDriverId();
                            int sid = response.body().getUserData().getSchoolId();


                            appPreferences.saveInt("Driver_id", dr_id);

                            appPreferences.saveInt("School_id", sid);
                            appPreferences.saveDataBoolean("isLogin", true);
                            appPreferences.saveData("login_name", "Driver");


                            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                                                        Intent i = new Intent(getApplicationContext(), Home.class);
                            pd.dismiss();
                            startActivity(i);

                        } else {
                            if (pd.isShowing()) {
                                pd.dismiss();
                            }

                                utils.toToast(response.body().getMsg());


                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplication(),"Error",Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<Mod_login> call, Throwable t) {
                if (pd.isShowing()) {
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Login.this, com.schoolmanapp.ktct_school.school.school.FourInOne.class);
        startActivity(intent);

    }
}
