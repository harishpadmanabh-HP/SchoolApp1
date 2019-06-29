package com.schoolmanapp.ktct_school.school.teacher.java_class;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.teacher.Api_client.Api_client;
import com.schoolmanapp.ktct_school.school.teacher.Api_interface.API_interface;
import com.schoolmanapp.ktct_school.school.teacher.model_class.AllClassModel;
import com.schoolmanapp.ktct_school.school.teacher.model_class.AllDivisionsmodel;
import com.schoolmanapp.ktct_school.school.teacher.model_class.Exam;
import com.schoolmanapp.ktct_school.school.teacher.model_class.ExamsDTO;
import com.schoolmanapp.ktct_school.school.teacher.model_class.ExamsModelDTO;
import com.schoolmanapp.ktct_school.school.teacher.model_class.ExamsSubjectsListsDTO;
import com.schoolmanapp.ktct_school.school.teacher.utils.AppPreferences;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMark extends AppCompatActivity {

    Button addmark;
    Spinner Classspin,Divspin,Examspin,subjectspin;
    AppPreferences appPreferences;
    int schoolId,classId,divisionId;
    List<AllClassModel.UserDataBean> allClass;
    ArrayList<String> classname,classid,divid,divname,examid,examname,subname,subjectid;
    Spinner classSpinner;
    android.app.AlertDialog dialog ;
    String slectedClassid;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mark);
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        dialog = new SpotsDialog(AddMark.this);

        allClass=new ArrayList<>();
        classname=new ArrayList<String>();
        classid=new ArrayList<String>();
        divid=new ArrayList<String>();
        divname=new ArrayList<String>();
        examid=new ArrayList<String>();
        examname=new ArrayList<String>();
        subname=new ArrayList<String>();
        subjectid=new ArrayList<String>();




        schoolId=appPreferences.getInt("schoolid");
       // Toast.makeText(this, ""+schoolId, Toast.LENGTH_SHORT).show();



        dialog.show();
       classname.add("Select Class");
        classid.add("Class Id");
        final API_interface api = Api_client.getClient().create(API_interface.class);
        Call<AllClassModel> allClassModelCall=api.ALL_CLASS_MODEL_CALL(schoolId);
        allClassModelCall.enqueue(new Callback<AllClassModel>() {
            @Override
            public void onResponse(Call<AllClassModel> call, Response<AllClassModel> response) {

                if (response.isSuccess())
                {

                    boolean status=response.body().isStatus();
                    String msg=response.body().getMsg();

                    if(msg.equalsIgnoreCase("success"))
                    {
                        allClass.clear();
                        allClass.addAll(response.body().getUserData());


                        //Toast.makeText(AddMark.this, ""+allClass.size(), Toast.LENGTH_SHORT).show();

                        for(int i=0;i<allClass.size();i++) {

                            classname.add(allClass.get(i).getClassName());
                            // classid.add(allClass.get(i).getClassId());


                            int id=response.body().getUserData().get(i).getClassId();
                            classid.add(Integer.toString(id));

                            dialog.dismiss();


                        }
                    }


                }else
                {
                    Log.e("responseallclassfailed ","");
                }

            }

            @Override
            public void onFailure(Call<AllClassModel> call, Throwable t) {

            }
        });



      //  allClassNameandId();

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


         Classspin = (Spinner) findViewById(R.id.spinnerClass);
          Divspin = (Spinner) findViewById(R.id.spinnerDivision);
          Examspin= (Spinner) findViewById(R.id.spinnerExams);
          subjectspin= (Spinner) findViewById(R.id.spinnersubjects);



        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,classname);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        Classspin.setAdapter(aa);
        Classspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > 0) {
                    final String slectedClass = classname.get(i).toString();
                     slectedClassid = classid.get(i).toString();
                    // int cid=Integer.parseInt(slectedClassid);

                  //  appPreferences.saveInt("classId",Integer.parseInt(slectedClassid));

                    divid.clear();
                    divname.clear();
                    if (!slectedClass.equalsIgnoreCase("Select Class") && !slectedClassid.equalsIgnoreCase("Class Id")) {
dialog.show();

appPreferences.saveInt("slectedClassid",Integer.parseInt(slectedClassid));

                        Call<AllDivisionsmodel> allDivisionsmodelCall = api.allDivisionscall(schoolId, Integer.valueOf(slectedClassid));
                        allDivisionsmodelCall.enqueue(new Callback<AllDivisionsmodel>() {
                            @Override
                            public void onResponse(Call<AllDivisionsmodel> call, Response<AllDivisionsmodel> response) {
                                if (response.isSuccess()) {
                                    divid.add("Div Id");
                                    divname.add("Select Division");

                                    boolean s = response.body().isStatus();
                                    if (s == true) {
                                        String msg = response.body().getMsg();

                                        List<AllDivisionsmodel.UserDataBean> divlist = response.body().getUserData();

                                        for (int i = 0; i < divlist.size(); i++) {
                                            divid.add(String.valueOf(response.body().getUserData().get(i).getDivisionId()));
                                            divname.add(response.body().getUserData().get(i).getDivisionName());
dialog.dismiss();
                                            ArrayAdapter aap = new ArrayAdapter(AddMark.this, android.R.layout.simple_spinner_item, divname);
                                            aap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                            //Setting the ArrayAdapter data on the Spinner
                                            Divspin.setAdapter(aap);
                                            Divspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {


                                                    String selectedDivName=divname.get(i).toString();
                                                    final String selectedDivId=divid.get(i).toString();

                                                 //   int div= Integer.parseInt(selectedDivId);

                                                 //   appPreferences.saveInt("DivisionId",div );


                                                    //int selectedDivIdint=Integer.parseInt(selectedDivId);

                                                 //   Toast.makeText(AddMark.this, "selectedDivIdint         "+selectedDivId, Toast.LENGTH_SHORT).show();

                                                    if(!selectedDivId.equalsIgnoreCase("Div Id"))
                                                    {
                                                        appPreferences.saveInt("selectedDivId",Integer.parseInt(selectedDivId));
                                                        examname.clear();
                                                        examname.add("Select Exam");
                                                        examid.clear();
                                                        examid.add("Exam ID");





                                                        Call<Exam> examCall=api.examsmodelcall(schoolId,Integer.parseInt(slectedClassid),Integer.parseInt(selectedDivId));
                                                       // Call<Exam> examCall=api.examsmodelcall(10093,20493,71079);

                                                        examCall.enqueue(new Callback<Exam>() {
                                                            @Override
                                                            public void onResponse(Call<Exam> call, Response<Exam> response) {

                                                                if(response.body().isStatus()==true)
                                                                {

                                                                    if(response.body().getMsg().equalsIgnoreCase("success"))
                                                                    {
                                                                        List<Exam.ExamsBean> examList=response.body().getExams();
                                                                        subname.clear();
                                                                        subname.add("Choose Subjects");
                                                                        subjectid.clear();
                                                                        subjectid.add("Sub ID");
                                                                        for(int j=0;j<examList.size();j++)
                                                                        {
                                                                            examname.add(examList.get(j).getExamName());
                                                                            examid.add(String.valueOf(examList.get(j).getExamId()));

                                                                            List<Exam.ExamsBean.ExamsSubjectsListsBean> subjectsList=examList.get(j).getExamsSubjectsLists();

                                                                            for(int q=0;q<subjectsList.size();q++)
                                                                            {
                                                                                subname.add(subjectsList.get(q).getSubject());
                                                                                subjectid.add(String.valueOf(subjectsList.get(q).getSubId()));
                                                                            }
                                                                        }
                                                                        ArrayAdapter examarrayadapter = new ArrayAdapter(AddMark.this,android.R.layout.simple_spinner_item,examname);
                                                                        examarrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                                        //Setting the ArrayAdapter data on the Spinner
                                                                        Examspin.setAdapter(examarrayadapter);
                                                                        Examspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                            @Override
                                                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                                                                String s_examid=examid.get(i).toString();
                                                                                String s_exam=examname.get(i);
                                                                           //     appPreferences.saveInt("examID",Integer.parseInt(s_examid));
                                                                             //   appPreferences.saveData("examName",s_exam);

                                                                                if(!s_examid.equalsIgnoreCase("Exam ID") && !s_exam.equalsIgnoreCase("Select Exam"))
                                                                                {
                                                                                    appPreferences.saveInt("examID",Integer.parseInt(s_examid));
                                                                                       appPreferences.saveData("examName",s_exam);

                                                                                }else{
                                                                                    Toast.makeText(AddMark.this, "Choose a valid exam.", Toast.LENGTH_SHORT).show();
                                                                                }

                                                                            }

                                                                            @Override
                                                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                                                            }
                                                                        });



                                                                        ArrayAdapter subarray = new ArrayAdapter(AddMark.this,android.R.layout.simple_spinner_item,subname);
                                                                        subarray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                                        //Setting the ArrayAdapter data on the Spinner
                                                                        subjectspin.setAdapter(subarray);
                                                                        subjectspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                            @Override
                                                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                                                                String s_subid=subjectid.get(i).toString();
                                                                                String s_subject=subname.get(i);
                                                                                if(!s_subid.equalsIgnoreCase("Sub ID")&&!s_subject.equalsIgnoreCase("Choose Subjects")) {
                                                                                    appPreferences.saveInt("subId", Integer.parseInt(s_subid));
                                                                                    appPreferences.saveData("subName", s_subject);
                                                                                }
                                                                                else{
                                                                                    Toast.makeText(AddMark.this, "Choose a valid subject.", Toast.LENGTH_SHORT).show();
                                                                                }

                                                                            }

                                                                            @Override
                                                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                                                            }
                                                                        });

                                                                    }else
                                                                    {
                                                                        Toast.makeText(AddMark.this, "No exam found", Toast.LENGTH_SHORT).show();
                                                                    }

                                                                }else{
                                                                    Toast.makeText(AddMark.this, "No exam data found.", Toast.LENGTH_LONG).show();
                                                                    Log.e("Status-----","status failed exams api");
                                                                }


                                                            }

                                                            @Override
                                                            public void onFailure(Call<Exam> call, Throwable t) {

                                                            }
                                                        });


                                                    }else{
                                                        Log.e("no div selected-----",selectedDivId);
                                                    }
                                                                                                 }

                                                @Override
                                                public void onNothingSelected(AdapterView<?> adapterView) {

                                                }
                                            });


                                        }


                                    } else {
                                        Log.e("divStatus   ", String.valueOf(s));
                                    }


                                } else {
                                    Log.e("allDivisionsmodelFail", slectedClass);
                                }
                            }

                            @Override
                            public void onFailure(Call<AllDivisionsmodel> call, Throwable t) {

                            }
                        });


                    } else {
                        Toast.makeText(AddMark.this, "Select class", Toast.LENGTH_SHORT).show();
                    }
                    //Toast.makeText(AddMark.this, "slectedClass"+"slectedClassid"+slectedClassid+slectedClass, Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(AddMark.this, "No class selected.", Toast.LENGTH_SHORT).show();
            }
        });
        addmark= (Button) findViewById(R.id.addmarkButton);
        addmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(subjectspin.getSelectedItem()==null || subjectspin.getSelectedItem()=="Choose Subjects") {
                    Toast.makeText(AddMark.this, "Please fill all fields.", Toast.LENGTH_SHORT).show();

                   // startActivity(new Intent(getApplicationContext(), TeacherAddMarkList.class));
                }else
                {
                     startActivity(new Intent(getApplicationContext(), TeacherAddMarkList.class));

                    // Toast.makeText(AddMark.this, "Please fill all fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void allClassNameandId() {
        final API_interface api = Api_client.getClient().create(API_interface.class);
        Call<AllClassModel> allClassModelCall=api.ALL_CLASS_MODEL_CALL(schoolId);
        allClassModelCall.enqueue(new Callback<AllClassModel>() {
            @Override
            public void onResponse(Call<AllClassModel> call, Response<AllClassModel> response) {

                if (response.isSuccess())
                {

                    boolean status=response.body().isStatus();
                    String msg=response.body().getMsg();

                    if(msg.equalsIgnoreCase("success"))
                    {
                        allClass.clear();
                        allClass.addAll(response.body().getUserData());


                       // Toast.makeText(AddMark.this, ""+allClass.size(), Toast.LENGTH_SHORT).show();

                        for(int i=0;i<allClass.size();i++) {

                            classname.add(allClass.get(i).getClassName());
                           // classid.add(allClass.get(i).getClassId());

                        }
                    }


                }else
                {
                    Log.e("responseallclassfailed ","");
                }


            }

            @Override
            public void onFailure(Call<AllClassModel> call, Throwable t) {

            }
        });


    }
}
