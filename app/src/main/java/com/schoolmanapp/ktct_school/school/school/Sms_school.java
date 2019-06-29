package com.schoolmanapp.ktct_school.school.school;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_List_Classdiv;
import com.schoolmanapp.ktct_school.school.school.common.BaseActivity;
import com.schoolmanapp.ktct_school.school.school.sms_student_list_adapter;
import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.VISIBLE;

public class Sms_school extends BaseActivity {
    ListView sms_std;
    @Inject
    AppPreferences appPreferences;
    AlertDialog pd;
    public static onchecked onchecked;
    @Inject
    Utils utils;
    String scid;
    int sid ;
    String take="2";
    ArrayList<String> classname, division;
    ArrayList<Integer> did,cid;
    ArrayList<Boolean> status;
    ArrayList<Integer>select_class_id,select_div_id;
    public sms_student_list_adapter smsStudentListAdapter;
    TextView send;
    CheckBox sms_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_school);
        ButterKnife.bind(this);
        getApp().getActivityComponent().inject(this);
//        utils= new Utils();
//        appPreferences = com.schoolmanapp.srishtiinnovative.school.school.utils.AppPreferences.getInstance(getApplicationContext(), getResources().getString(R.string.app_name));
        send=(TextView)findViewById(R.id.send_bt);
        sms_std=(ListView)findViewById(R.id.lv_sms);
        sms_all=(CheckBox)findViewById(R.id.checkBox_sms);
        pd = new SpotsDialog(Sms_school.this);
        sid=appPreferences.getInt("scid");
        scid=Integer.toString(sid);
        classname = new ArrayList<String>();
        division = new ArrayList<String>();
        did = new ArrayList<Integer>();
        cid=new ArrayList<Integer>();
        status=new ArrayList<>();
        select_class_id=new ArrayList<Integer>();
        select_div_id=new ArrayList<Integer>();
        sms_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sms_all.isChecked()){
                    for(int i=0;i<status.size();i++){
                        status.set(i,true);
                        select_div_id.clear();
                        select_class_id.clear();
                    }
                    select_class_id.addAll(cid);
                    select_div_id.addAll(did);
                    smsStudentListAdapter.notifyDataSetChanged();
                }
                else {
                    for(int i=0;i<status.size();i++){
                        status.set(i,false);
                    }
                    select_div_id.clear();
                    select_class_id.clear();
                    smsStudentListAdapter.notifyDataSetChanged();
                }
            }
        });
        onchecked=new onchecked() {
            @Override
            public void onchecked(Integer cls, Integer div, ArrayList<Boolean> status,int postion ) {
                status.set(postion,true);

                select_class_id.add(cls);
                select_div_id.add(div);
                if(select_class_id.size()>=0){
                    sms_all.setChecked(false);
//                sms_all.setVisibility(View.GONE);
                }
                if(select_class_id.size()==0){
                    take="2";
                    sms_all.setVisibility(View.VISIBLE);
                }
                if(select_class_id.size()==cid.size()){
                    sms_all.setChecked(true);
                    sms_all.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void unchecked(Integer cls, Integer div, ArrayList<Boolean> status,int postion) {
                int s=postion;
                status.set(postion,false);


                for(int i=0;i<select_class_id.size();i++){
                    if(select_class_id.get(i) ==cls){
                        select_class_id.remove(i);
                    }
                    if(select_div_id.get(i) ==div){
                        select_div_id.remove(i);
                    }

                }
                if(select_class_id.size()>=0){
                    sms_all.setChecked(false);
//                sms_all.setVisibility(View.GONE);
                }
                if(select_class_id.size()==0){
                    take="2";
                    sms_all.setChecked(false);
                    sms_all.setVisibility(View.VISIBLE);
                }
            }


        };
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sms_all.isChecked()){
                    take ="0";
                }
                if(select_class_id.size()>0){
                    take="1";
                }
                if(take.equals("2")){
                    Toast.makeText(Sms_school.this, "Select Checkbox", Toast.LENGTH_SHORT).show();

                }else {
                    Intent intent = new Intent(getApplicationContext(), Sms_send_activity.class);
                    intent.putExtra("clsid", select_class_id);
                    intent.putExtra("divid", select_div_id);
                    intent.putExtra("take", take);
                    intent.putExtra("scis", sid);
                    startActivity(intent);
                    finish();
                }




            }
        });

        std_api();

    }

    public void std_api(){
        pd.show();

        Call<Mod_List_Classdiv> call = utils.getApi().classlist(scid);

        call.enqueue(new Callback<Mod_List_Classdiv>() {
            @Override
            public void onResponse(Call<Mod_List_Classdiv> call, Response<Mod_List_Classdiv> response) {
                if (response.isSuccess()) {
                    if (response.body().getMsg().equals("Success")) {
                        List<Mod_List_Classdiv.UserDataBean> details = response.body().getUserData();
                        // Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();


                        if (details.size() > 0) {

                            int l = details.size();
                            for (int i = 0; i < l; i++) {
                                String c = details.get(i).getClassName().toString();

                                List<Mod_List_Classdiv.UserDataBean.DivisionBean> d = details.get(i).getDivision();
                                int len = d.size();
                                for (int j = 0; j < len; j++) {
                                    try {


                                        String div = d.get(j).getDivisionName();
                                        int divid = d.get(j).getDivisionId();
                                        int clid = d.get(j).getClassId();
                                        classname.add(c);
                                        division.add(div);
                                        did.add(divid);
                                        cid.add(clid);
                                        status.add(false);
                                    }catch (NullPointerException e){

                                    }



                                }
                                smsStudentListAdapter= new sms_student_list_adapter(getApplication(), classname, division,cid,did,status);
                                sms_std.setAdapter(smsStudentListAdapter);

                            }
                        } else {
                            Toast.makeText(Sms_school.this, "No classes added for the school", Toast.LENGTH_SHORT).show();

                        }
                    } else {

                        utils.toToast(response.body().getMsg());
                    }

                } else

                    utils.toToast(response.body().getMsg());

                if (pd.isShowing()) {

                    pd.dismiss();
                }

            }


            @Override
            public void onFailure(Call<Mod_List_Classdiv> call, Throwable t) {
                //  utils.toToast("Network failure");
                Toast.makeText(getApplicationContext(), "Network Failure", Toast.LENGTH_SHORT).show();
            }

        });
    }

}
