package com.schoolmanapp.ktct_school.school.school;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_school_reg;
import com.schoolmanapp.ktct_school.school.school.common.BaseActivity;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by srishtiinnovative on 29/05/17.
 */

public class Registration extends BaseActivity implements Validator.ValidationListener {
    @Bind(R.id.signup)
    ImageView iv_signup;
    @Bind(R.id.img_reg_close)
    ImageView iv_close;
    @Bind(R.id.txt_login)
    TextView tv_login;
    @NotEmpty
    @Bind(R.id.et_schoolname)
    EditText et_name;

    @NotEmpty
    @Bind(R.id.et_addr)
    EditText et_addr;

    @NotEmpty
    @Bind(R.id.et_state)
    EditText et_state;
    @NotEmpty
    @Bind(R.id.et_city)
    EditText et_city;
    @NotEmpty

    @Bind(R.id.et_web)
    EditText et_web;
    @NotEmpty
    @Email
    @Bind(R.id.et_email)
    EditText et_email;

    @NotEmpty
    //@Max(value = 10)
    @Bind(R.id.et_phno)
    EditText et_phno;

    @NotEmpty
    @Password(min = 6)
    @Bind(R.id.et_pswd)
    EditText et_pswd;

    @NotEmpty
    @ConfirmPassword
    @Bind(R.id.et_cfm_pswd)
    EditText et_cfm_pswd;


    @Bind(R.id.img_school_logo)
    ImageView logo;
    String picturePath, encodedString;
    @Inject
    Utils utils;

    String name, addr, state, scity, web, email, phno, pswd;
    private static int RESULT_LOAD_IMAGE = 1;
    Validator validator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        getApp().getActivityComponent().inject(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ButterKnife.bind(this);

        validator = new Validator(this);
        validator.setValidationListener(this);

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);

            }
        });
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);

            }
        });
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });


        iv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                validator.validate();
            }
        });
    }
            @Override
            public void onValidationSucceeded() {


                name = et_name.getText().toString();
                addr = et_addr.getText().toString();
                state = et_state.getText().toString();
                scity = et_city.getText().toString();
                web = et_web.getText().toString();
                email = et_email.getText().toString();
                phno = et_phno.getText().toString();
                pswd = et_pswd.getText().toString();


                File f = null;
                try {
                    f = new File(picturePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    f.createNewFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                InputStream inputStream = null;//You can get an inputStream using any IO API
                try {
                    inputStream = new FileInputStream(f);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    byte[] bytes;
                    byte[] buffer = new byte[8192];
                    int bytesRead;
                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                    try {
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            output.write(buffer, 0, bytesRead);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    bytes = output.toByteArray();
                    encodedString = Base64.encodeToString(bytes, Base64.DEFAULT);
                } catch (Exception e) {
                    e.printStackTrace();
                    encodedString="";
                    picturePath="";
                }


                Call<Mod_school_reg> call = utils.getApi().school_registration(name, addr, scity, email, web, pswd, phno, state, encodedString, picturePath);
                // Call<Mod_school_reg> call = getApi().school_registration(map);
                call.enqueue(new Callback<Mod_school_reg>() {
                    @Override
                    public void onResponse(Call<Mod_school_reg> call, Response<Mod_school_reg> response) {
                        if (response.isSuccess()) {

                            String msg = response.message();
                            if (msg.equals("Success")) {
                              //  Log.d("Success", response.message());


                                utils.toToast("Registration Successfull");
                                Intent i = new Intent(Registration.this, Login.class);
                                startActivity(i);
                                // d.cancel();
                            } else {
                                utils.toToast(response.message());
                            }


                        }
                    }

                    @Override
                    public void onFailure(Call<Mod_school_reg> call, Throwable t) {
                        utils.toToast("Failure" + t);
                        // Log.d("failure", "failure" + t);
                    }
                });

            }


        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                picturePath = cursor.getString(columnIndex);
                cursor.close();

                //ImageView imageView = (ImageView) findViewById(R.id.imgView);
                logo.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            }


        }


        @Override
        public void onValidationFailed (List < ValidationError > errors) {

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

