package com.schoolmanapp.ktct_school.school.school;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Edit_Username;
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
 * Created by srishtiinnovative on 20/06/17.
 */

public class Change_username extends BaseActivity implements Validator.ValidationListener {
    Validator validator;
    @Bind(R.id.imgback)
    ImageView iv_back;

    View view;
    @NotEmpty
    @Email
    @Bind(R.id.et_new_un)
    EditText et_new_un;
    @NotEmpty
    @Email
    @Bind(R.id.et_old_un)
    EditText et_old_un;
    AlertDialog pd;
    @Inject
    Utils utils;
    @Inject
    AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_username);
        ButterKnife.bind(this);
        getApp().getActivityComponent().inject(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        pd = new SpotsDialog(Change_username.this);
        TextView iv = (TextView) findViewById(R.id.change_user);
//         et_new_un=(EditText)view.findViewById(R.id.et_new_un);
//         et_old_un=(EditText)view.findViewById(R.id.et_old_un);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIv_back();
            }
        });


    }

    void setIv_back() {
        Intent intent = new Intent(Change_username.this, SetttingsFragment.class);
        startActivity(intent);
        finish();
    }


    void change() {
        String new_un = et_new_un.getText().toString();
        String old_un = et_old_un.getText().toString();
        int sid = appPreferences.getInt("scid");
        String scid = Integer.toString(sid);
        //Change_username.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

       // pd.show();

        Call<Mod_Edit_Username> call = utils.getApi().edit_user(scid, old_un, new_un);


        call.enqueue(new Callback<Mod_Edit_Username>() {
            @Override
            public void onResponse(Call<Mod_Edit_Username> call, Response<Mod_Edit_Username> response) {
                if (response.isSuccess()) {


                    String msg = response.body().getMsg();
                    if (msg.equals("Success")) {
                        //utils.toToast(response.body().getMsg());
                        utils.toToast("Username changed successfully");
                        Intent intent = new Intent(getApplicationContext(), SetttingsFragment.class);
                        startActivity(intent);
                        finish();
//                    TODO    loadFragment2(new SetttingsFragment());

                    } else {
                        if (pd.isShowing()) {
                            pd.dismiss();
                        }
                        utils.toToast(response.body().getMsg());
                        //loadFragment2(new Classdiv());
                        Intent intent = new Intent(getApplicationContext(), SetttingsFragment.class);
                        startActivity(intent);
                        finish();


                    }
                } else {
                    if (pd.isShowing()) {
                        pd.dismiss();
                    }
                    utils.toToast(response.body().getMsg());

                }

            }

            @Override
            public void onFailure(Call<Mod_Edit_Username> call, Throwable t) {
                if (pd.isShowing()) {
                    pd.dismiss();
                }
                utils.toToast("Network failure");
            }

        });
    }

//    private void loadFragment2(SetttingsFragment fragment) {
//        if(pd.isShowing())
//        {
//            pd.dismiss();
//        }
//        if (view != null) {
//            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
//        android.app.FragmentManager fm=getFragmentManager();
//        android.app.FragmentTransaction fragmentTransaction=fm.beginTransaction();
//        fragmentTransaction.replace(R.id.frame_lay,fragment);
//        fragmentTransaction.commit();
//    }

    @Override
    public void onValidationSucceeded() {
        change();

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getApplicationContext());

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
