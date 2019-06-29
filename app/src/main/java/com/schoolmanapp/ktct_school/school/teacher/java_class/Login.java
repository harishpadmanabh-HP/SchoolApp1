package com.schoolmanapp.ktct_school.school.teacher.java_class;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.school.FourInOne;
import com.schoolmanapp.ktct_school.school.teacher.Api_client.Api_client;
import com.schoolmanapp.ktct_school.school.teacher.Api_interface.API_interface;
import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.teacher.model_class.login_model;
import com.schoolmanapp.ktct_school.school.teacher.utils.AppPreferences;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Response;

public class Login extends AppCompatActivity implements Validator.ValidationListener {
    @Bind(R.id.img_login)
    TextView img_login;

    @NotEmpty
    @Bind(R.id.teacher_id)
    EditText et_teacherid;

    Validator validator;
    ProgressBar bar;

    AppPreferences appPreferences;
    ProgressDialog pd;
    AlertDialog dialog ;
    @Bind(R.id.parent_logo)
    ImageView iv_logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        validator = new Validator(this);
        validator.setValidationListener(this);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//        pd = new ProgressDialog(this,R.style.MyTheme);
//        pd.setCancelable(false);
//        pd.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
         dialog = new SpotsDialog(this);
        img_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             validator.validate();


            }
        });
        iv_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.schoolmanapp.ktct_school.school.parent.Java_class.webview.class);
                startActivity(intent);
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
        Call<login_model> call = api.loginModelCall(et_teacherid.getText().toString());
        call.enqueue(new retrofit2.Callback<login_model>() {

            @Override
            public void onResponse(Call<login_model> call, Response<login_model> response) {
                if (response.isSuccess()) {
                    //  List<login_model.UserDataBean> details = response.body().getUserData();
                    if (response.body().getMsg().equals("Success")) {
                        int teacher_id = response.body().getUserData().getTeacherId();
                        appPreferences.saveInt("teacher_id", response.body().getUserData().getTeacherId());
                        Log.e("teacher_id"," :"+teacher_id);

                       // List<login_model.UserDataBean.SchoolDataBean> schoolDataBeans= (List<login_model.UserDataBean.SchoolDataBean>) response.body().getUserData();

                        int s=response.body().getUserData().getSchoolData().getSchoolId();
                        Toast.makeText(Login.this, "School id"+s, Toast.LENGTH_SHORT).show();
                        appPreferences.saveInt("schoolid",s);

                        List<login_model.UserDataBean.TeacherClassBean> teacher_class = response.body().getUserData().getTeacherClass();
                        if (teacher_class.size() > 0) {
                            for (int i = 0; i < teacher_class.size(); i++) {
                                int classid = teacher_class.get(i).getClassId();
                                Log.e("class", "id" + classid);
                                appPreferences.saveInt("class_id", classid);

                                int division_id = teacher_class.get(i).getDivisionId();
                                Log.e("division_id!!","::"+teacher_class.get(i).getDivisionId());
                                appPreferences.saveInt("division_id", division_id);

                                String class_name=teacher_class.get(i).getClassName();
                                appPreferences.saveData("class_name",class_name);

                                String division_name=teacher_class.get(i).getDivisionName();
                                appPreferences.saveData("division_name",division_name);
                                appPreferences.saveDataBoolean("isLogin", true);
                                appPreferences.saveData("login_name", "Teacher");


                            }



                        }

                        Intent i = new Intent(getApplicationContext(), home_activity.class);
                        startActivity(i);
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                    } else {
                        //Toast.makeText(getApplicationContext(),response.body().getMsg(),Toast.LENGTH_SHORT).show();

                        Toast.makeText(getApplicationContext(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
                else {

                }
            }


            @Override
            public void onFailure(Call<login_model> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Check the network connection", Toast.LENGTH_SHORT).show();
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



