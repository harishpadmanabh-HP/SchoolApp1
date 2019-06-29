package com.schoolmanapp.ktct_school.school.teacher.java_class;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.teacher.Api_client.Api_client;
import com.schoolmanapp.ktct_school.school.teacher.Api_interface.API_interface;
import com.schoolmanapp.ktct_school.school.teacher.model_class.EditValue;
import com.schoolmanapp.ktct_school.school.teacher.model_class.MarkListViewModel;
import com.schoolmanapp.ktct_school.school.teacher.model_class.MarkListViewRequest;
import com.schoolmanapp.ktct_school.school.teacher.model_class.SaveMark;
import com.schoolmanapp.ktct_school.school.teacher.model_class.SaveMarkRequest;
import com.schoolmanapp.ktct_school.school.teacher.model_class.StudentsFull;
import com.schoolmanapp.ktct_school.school.teacher.model_class.multiple_message_model;
import com.schoolmanapp.ktct_school.school.teacher.utils.AppPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.Bind;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherAddMarkList extends AppCompatActivity {

    RecyclerView student_list;
    Button save;
    String  json,jsonview;
    RequestBody AccessTokenValue = null,AccessTokenValueView=null;


    ArrayList stundentName,internalm,externalm,studid;

    AppPreferences appPreferences;

    int schoolid,classid,divid,examid,subjectid;
    android.app.AlertDialog dialog ;
    API_interface api;
    public ArrayList<EditValue> editModelArrayList;
    SaveMarkRequest saveMarkRequest;
    MarkListViewRequest markListViewRequest;
    List<MarkListViewModel.ResultsBean> viewModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marklist);
        student_list= (RecyclerView) findViewById(R.id.lv);
        save= (Button) findViewById(R.id.savemarkbutton);

        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        dialog = new SpotsDialog(TeacherAddMarkList.this);
//dialog.setMessage("Loading students");
        dialog.show();


        schoolid=appPreferences.getInt("schoolid");
        classid=appPreferences.getInt("slectedClassid");
        divid=appPreferences.getInt("selectedDivId");
        examid=appPreferences.getInt("examID");
        subjectid=appPreferences.getInt("subId");



        stundentName=new ArrayList<String>();
        internalm=new ArrayList<String>();
        externalm=new ArrayList<String>();
        studid=new ArrayList<String>();

//        stundentName.add("Test 1");
//        stundentName.add("Test 2");
//        stundentName.add("Test 3");
//        stundentName.add("Test 4");
//        stundentName.add("Test 5");
//        stundentName.add("Jijo  ");
         api = Api_client.getClient().create(API_interface.class);

        Call<StudentsFull> studentsFullCall=api.studentsFullCall(schoolid,classid,divid);

       // Call<StudentsFull> studentsFullCall=api.studentsFullCall(10085,30510,30752);
        studentsFullCall.enqueue(new Callback<StudentsFull>() {
            @Override
            public void onResponse(Call<StudentsFull> call, Response<StudentsFull> response) {

                List<StudentsFull.UserDataBean> snamelist=response.body().getUserData();

                for(int i=0;i<snamelist.size();i++)
                {
                    stundentName.add(snamelist.get(i).getStundentName());
                    studid.add(snamelist.get(i).getStudentId());
                }
//                for(int j=0;j<stundentName.size();j++)
//                {
//                    internalm.add("Internal");
//
//                }
//                for(int j=0;j<stundentName.size();j++)
//                {
//                    externalm.add("Ext");
//
//                }
                editModelArrayList = populateList(stundentName,"","");

                RecycleAdapter recycleAdapter=new RecycleAdapter(TeacherAddMarkList.this,editModelArrayList,stundentName,viewModelList);
                LinearLayoutManager verticalLayoutmanager
                        = new LinearLayoutManager(TeacherAddMarkList.this, LinearLayoutManager.VERTICAL, false);
                student_list.setLayoutManager(verticalLayoutmanager);
                student_list.setAdapter(recycleAdapter);
                dialog.dismiss();



            }

            @Override
            public void onFailure(Call<StudentsFull> call, Throwable t) {

            }
        });

        markListViewRequest=new MarkListViewRequest();
        markListViewRequest.setSchoolId(String.valueOf(schoolid));
        markListViewRequest.setClassId(String.valueOf(classid));
        markListViewRequest.setDivisionId(String.valueOf(divid));
        markListViewRequest.setExamId(String.valueOf(examid));
        markListViewRequest.setSubjectId(String.valueOf(subjectid));
        try {
            Gson gson = new Gson();
            jsonview = gson.toJson(markListViewRequest).trim();
            System.out.println("FinalData................\n" + jsonview.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            AccessTokenValueView = RequestBody.create(MediaType.parse("application/json"), jsonview.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Call<MarkListViewModel> markListViewModelCall=api.marklistviewcall(AccessTokenValueView);
        markListViewModelCall.enqueue(new Callback<MarkListViewModel>() {
            @Override
            public void onResponse(Call<MarkListViewModel> call, Response<MarkListViewModel> response) {
                if(response.body().getMsg().equalsIgnoreCase("success"))
                {
                     viewModelList = response.body().getResults();


                }
                RecycleAdapter recycleAdapter=new RecycleAdapter(TeacherAddMarkList.this,editModelArrayList,stundentName,viewModelList);
                LinearLayoutManager verticalLayoutmanager
                        = new LinearLayoutManager(TeacherAddMarkList.this, LinearLayoutManager.VERTICAL, false);
                student_list.setLayoutManager(verticalLayoutmanager);
                student_list.setAdapter(recycleAdapter);
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<MarkListViewModel> call, Throwable t) {

            }
        });



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> interalList,externalList;
                interalList=new ArrayList<>();
                externalList=new ArrayList<>();

                for(int i=0;i<RecycleAdapter.editModelArrayList.size();i++)
                {
                    interalList.add(RecycleAdapter.editModelArrayList.get(i).getEditValue1());
                    externalList.add(RecycleAdapter.editModelArrayList.get(i).getEditValue2());

                }
                saveMarkRequest =new SaveMarkRequest();
                saveMarkRequest.setExamId(String.valueOf(examid));
                saveMarkRequest.setSubjectId(String.valueOf(subjectid));
                ArrayList<SaveMarkRequest.StudentListBean> studentListBeans= new ArrayList<>();
                SaveMarkRequest.StudentListBean studnt;
                for(int i=0;i<studid.size();i++)
                {
                    studnt=new SaveMarkRequest.StudentListBean();
                    studnt.setStudentId(studid.get(i).toString());
                    studnt.setInternalMark(interalList.get(i));
                    studnt.setExternalMark(externalList.get(i));

                    studentListBeans.add(studnt);
                }
                saveMarkRequest.setStudentList(studentListBeans);
                try {
                    Gson gson = new Gson();
                    json = gson.toJson(saveMarkRequest).trim();
                    System.out.println("FinalData................\n" + json.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    AccessTokenValue = RequestBody.create(MediaType.parse("application/json"), json.getBytes("UTF-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Call<SaveMark> saveMarkCall=api.saveMarkCall(AccessTokenValue);
                saveMarkCall.enqueue(new Callback<SaveMark>() {
                    @Override
                    public void onResponse(Call<SaveMark> call, Response<SaveMark> response) {
                        if(response.body().isStatus()==true)
                        {
                            Toast.makeText(TeacherAddMarkList.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();


                            startActivity(new Intent(TeacherAddMarkList.this,AddMark.class));
                        }else
                        {
                            Toast.makeText(TeacherAddMarkList.this, "Something Went wrong.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SaveMark> call, Throwable t) {

                    }
                });


            }
        });


    }
    private ArrayList<EditValue> populateList(ArrayList<String> stundentName,String im,String ex){

        ArrayList<EditValue> list = new ArrayList<>();

        for(int i = 0; i < stundentName.size(); i++){
            EditValue editModel = new EditValue();
            editModel.setEditValue1(im);
            editModel.setEditValue2(ex);

            list.add(editModel);
        }

        return list;
    }

    static class  RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.Viewholder>
    {
        List<String> studentname;

        List<MarkListViewModel.ResultsBean> viewModelList;

        private LayoutInflater inflater;
        public static ArrayList<EditValue> editModelArrayList;


        public RecycleAdapter(Context ctx, ArrayList<EditValue> editModelArrayList,List<String> studentname,List<MarkListViewModel.ResultsBean> viewModelList){

            inflater = LayoutInflater.from(ctx);
            this.editModelArrayList = editModelArrayList;
            this.studentname=studentname;
            this.viewModelList=viewModelList;
        }

        @NonNull
        @Override
        public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.addmarklistitem, parent, false);

            return new Viewholder(itemView);        }

        @Override
        public void onBindViewHolder(@NonNull final Viewholder holder, int position) {

            holder.name.setText(studentname.get(position));

//            if(viewModelList!=null)
//            {
//                for(int i=0;i<viewModelList.size();i++) {
//                    holder.external.setText(viewModelList.get(i).getExternalMark());
//                    holder.internal.setText(viewModelList.get(i).getInternalMark());
//                }
//            }else {


            holder.internal.setText(String.valueOf(viewModelList.get(position).getInternalMark()));
            Log.e("internal mark",String.valueOf(viewModelList.get(position).getInternalMark()));
            holder.external.setText(String.valueOf(viewModelList.get(position).getExternalMark()));


                holder.internal.setText(editModelArrayList.get(position).getEditValue1());
                holder.external.setText(editModelArrayList.get(position).getEditValue2());
           // }
            Log.d("print","yes");


        }

        @Override
        public int getItemCount() {
            return studentname.size();
        }

        class Viewholder extends RecyclerView.ViewHolder {
           TextView name;
           EditText internal,external;
            public Viewholder(View itemView) {

                super(itemView);
                name=itemView.findViewById(R.id.sname);
                internal=itemView.findViewById(R.id.internalmark);
                external=itemView.findViewById(R.id.externalmark);
                internal.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        editModelArrayList.get(getAdapterPosition()).setEditValue1(internal.getText().toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                external.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        editModelArrayList.get(getAdapterPosition()).setEditValue2(external.getText().toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

            }
        }
    }



}
