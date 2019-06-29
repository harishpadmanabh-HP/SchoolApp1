package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.parent.API_interface.API_interface;
import com.schoolmanapp.ktct_school.school.parent.Api_client.Api_client;
import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.model_class.add_kid_model;
import com.schoolmanapp.ktct_school.school.parent.model_class.otp_request;
import com.schoolmanapp.ktct_school.school.parent.utils.AppPreferences;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_unassigned;
import com.schoolmanapp.ktct_school.school.school.Model.school_list_mod;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by srishtiinnovative on 06/06/17.
 */

public class Add_student extends Activity implements Validator.ValidationListener{
    @NotEmpty
    @Bind(R.id.add_kid_et)
    EditText et_kid_id;

    @Bind(R.id.add)
    TextView add;
    Validator validator;
    String id;
    AppPreferences appPreferences;
    int parent_id;
    AlertDialog dialog;

    @Bind(R.id.sp_school)
    Spinner spschool;
    ArrayList<String>school,school_id;
    Integer sid=0 ;
    Integer position_genderspinner=0;
    Boolean ans1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent);
        ButterKnife.bind(this);
        dialog = new SpotsDialog(this);
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        parent_id=appPreferences.getInt("parent_id");
        school=new ArrayList<>();
        school_id=new ArrayList<>();
        validator= new Validator(this);
        validator.setValidationListener(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });
        spschool.setBackgroundResource(R.drawable.edittext_border);
        school_list_api();
        school.add("Select Your Childs School");
        school_id.add("");
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,school);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // attaching data adapter to spinner
        try {
            aa.notifyDataSetChanged();
        }catch(Exception e)
        {
            Toast.makeText(this, "No School", Toast.LENGTH_SHORT).show();
        }
        spschool.setAdapter(aa);
        spschool.setSelection(0, false);


        spschool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position>0) {
                    position_genderspinner = position;
                    sid = Integer.valueOf(school_id.get(position));
                }
 else
                {
                    TextView errorText = (TextView)spschool.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("Please select School");//changes the selected item text to this
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Add_student.this, Home.class);
        startActivity(intent);
    }

    @Override
    public void onValidationSucceeded() {
        ans1 = spinner_validation();
        if(ans1){
            dialog.show();
            id=et_kid_id.getText().toString();
            final API_interface api= Api_client.getClient().create(API_interface.class);
            Call<otp_request> call=api.Otp_request(String.valueOf(parent_id),id, String.valueOf(sid));
            call.enqueue(new retrofit2.Callback<otp_request>(){

                @Override
                public void onResponse(Call<otp_request> call, Response<otp_request> response) {
                    if(response.isSuccess()){
                        if(response.body().isStatus()) {
                            appPreferences.saveData("otp_kid_id",id);
                            //  List<login_model.UserDataBean> details = response.body().getUserData();
                            if(response.body().getMsg().equals("Send an OTP in your contact number!")) {
                                appPreferences.saveData("otp_kid_name",response.body().getUserData().getName());
                                appPreferences.saveData("otp_kid_class",response.body().getUserData().getClassDetails());
                                appPreferences.saveData("otp_school_id", String.valueOf(sid));
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
    void school_list_api(){
        dialog.show();
        final API_interface api= Api_client.getMessage().create(API_interface.class);
        Call<school_list_mod> call=api.new_attendence();
        call.enqueue(new retrofit2.Callback<school_list_mod>(){

            @Override
            public void onResponse(Call<school_list_mod> call, Response<school_list_mod> response) {
                if(response.isSuccess()){
//
                    List<school_list_mod.UserDataBean> details = response.body().getUserData();

                    if (details.size() > 0) {
                        int l = details.size();

                        for (int i = 0; i < l; i++) {
                            String c = details.get(i).getSchoolName().toString();
                          int cid=details.get(i).getSchoolId();

                            school.add(c);
                            school_id.add(String.valueOf(cid));

                        }
                    }
                }
                dialog.dismiss();}


            @Override
            public void onFailure(Call<school_list_mod> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

        });

    }
    public Boolean spinner_validation()

    {
        if ( position_genderspinner>0 ){

            return true;
        } else {
            if (position_genderspinner<=0) {
                TextView errorText = (TextView) spschool.getSelectedView();
                errorText.setError("");
                errorText.setTextColor(Color.RED);//just to highlight that this is an error
                errorText.setText("Please select School");//changes the selected item text to this
            }
            return false;
        }


    }

}
