package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.API_interface.API_interface;
import com.schoolmanapp.ktct_school.school.parent.Api_client.Api_client;
import com.schoolmanapp.ktct_school.school.parent.model_class.ExamListModel;
import com.schoolmanapp.ktct_school.school.parent.model_class.ExamResultModel;
import com.schoolmanapp.ktct_school.school.parent.model_class.ExamResultrequest;
import com.schoolmanapp.ktct_school.school.parent.model_class.ListExamRequest;
import com.schoolmanapp.ktct_school.school.parent.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.teacher.java_class.AddMark;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectExamParent extends AppCompatActivity {

    android.app.AlertDialog dialog ;
    AppPreferences appPreferences;
    int studentid;
    ListExamRequest listExamRequest;
    ExamResultrequest examResultrequest;
    Spinner examspin;
    Button viewResult;
    String  json,jsonforresult;
    RequestBody AccessTokenValue = null;
    RequestBody AccessTokenValueforResult = null;
    ArrayList<String> examname;
    ArrayList<String> examid;
    String selectedExamId=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_exam_parent);
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        studentid=appPreferences.getInt("kid_idformark");
      // Toast.makeText(this, "mark"+studentid, Toast.LENGTH_SHORT).show();

        examid=new ArrayList<>();
        examname=new ArrayList<>();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        dialog = new SpotsDialog(SelectExamParent.this);


        examspin=findViewById(R.id.spinnerExamsparent);
        viewResult=findViewById(R.id.viewResultbutton);
        dialog.show();
        examResultrequest=new ExamResultrequest();
        listExamRequest=new ListExamRequest();
        //listExamRequest.setStudentId("17889");

        listExamRequest.setStudentId(String.valueOf(studentid));
        try {
            Gson gson = new Gson();
            json = gson.toJson(listExamRequest).trim();
            System.out.println("FinalData................\n" + json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            AccessTokenValue = RequestBody.create(MediaType.parse("application/json"), json.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        final API_interface api = Api_client.getevent().create(API_interface.class);
        final retrofit2.Call<ExamListModel> examListModelCall=api.examlistcall(AccessTokenValue)  ;

        examname.add("Choose Exam ");
        examid.add("Exam ID");

        examListModelCall.enqueue(new Callback<ExamListModel>() {
            @Override
            public void onResponse(retrofit2.Call<ExamListModel> call, Response<ExamListModel> response) {
                if(response.body().isStatus()==true)
                {
                    if(response.body().getMsg().equalsIgnoreCase("Success"))
                    {
                        List<ExamListModel.ResultsBean> examlists=response.body().getResults();
                       if(examlists.size()>0) {
                           for (int i = 0; i < examlists.size(); i++) {
                               examname.add(examlists.get(i).getExamName());
                               examid.add(String.valueOf(examlists.get(i).getExamId()));

                           }
                       }else
                       {
                           Toast.makeText(SelectExamParent.this, "No exam found .", Toast.LENGTH_SHORT).show();
                       }
                       // Spinner spin = (Spinner) findViewById(R.id.spinner);
                        ArrayAdapter aa = new ArrayAdapter(SelectExamParent.this,android.R.layout.simple_spinner_item,examname);
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Setting the ArrayAdapter data on the Spinner
                        examspin.setAdapter(aa);
                        dialog.dismiss();
                        examspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                String selectedExam=examname.get(i).toString();
                                  selectedExamId=examid.get(i).toString();
                                 if(!selectedExamId.equalsIgnoreCase("")&&!selectedExamId.equalsIgnoreCase("Exam ID"))
                                 {
                                     appPreferences.saveInt("Examid",Integer.parseInt(selectedExamId));
                                 }


                               // Toast.makeText(SelectExamParent.this, ""+selectedExamId, Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });




                    }else {
                        Toast.makeText(SelectExamParent.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                    }

                }else
                {
                    Toast.makeText(SelectExamParent.this, "No exam data found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(retrofit2.Call<ExamListModel> call, Throwable t) {

                dialog.dismiss();
                Toast.makeText(SelectExamParent.this, "Check your internet connection", Toast.LENGTH_SHORT).show();

            }
        });

        viewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!selectedExamId.equalsIgnoreCase("")&&!selectedExamId.equalsIgnoreCase("Exam ID")) {
                    dialog.show();
                    examResultrequest.setStudentId(String.valueOf(studentid));

                    String eid = String.valueOf(appPreferences.getInt("Examid"));
                    examResultrequest.setExamId(eid);
                    //examResultrequest.setStudentId("147618");
                    //examResultrequest.setExamId("20020");


                    try {
                        Gson gson = new Gson();
                        jsonforresult = gson.toJson(examResultrequest).trim();
                        System.out.println("FinalData................\n" + json.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        AccessTokenValueforResult = RequestBody.create(MediaType.parse("application/json"), jsonforresult.getBytes("UTF-8"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    retrofit2.Call<ExamResultModel> examResultModelCall=api.examresultcall(AccessTokenValueforResult);

                    examResultModelCall.enqueue(new Callback<ExamResultModel>() {
                        @Override
                        public void onResponse(retrofit2.Call<ExamResultModel> call, Response<ExamResultModel> response) {

                            Toast.makeText(SelectExamParent.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
dialog.dismiss();
//                            if(response.body().isStatus()==true)
//                            {
                                if(response.body().getMsg().equalsIgnoreCase("success"))
                                {
                                    String html=response.body().getResults();
                                    Intent intent=new Intent(SelectExamParent.this,ResultWebView.class);
                                    intent.putExtra("html",html);
                                    startActivity(intent);
                                }

//                            }else
//                            {
//                                Toast.makeText(SelectExamParent.this, "Failed", Toast.LENGTH_SHORT).show();
//                            }
                        }

                        @Override
                        public void onFailure(retrofit2.Call<ExamResultModel> call, Throwable t) {
dialog.dismiss();
                            Toast.makeText(SelectExamParent.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                        }
                    });




                }else{
                    Toast.makeText(SelectExamParent.this, "Choose a valid exam", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
