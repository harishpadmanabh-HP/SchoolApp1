package com.schoolmanapp.ktct_school.school.school;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.Model.mod_sms;
import com.schoolmanapp.ktct_school.school.school.Model.sms_responce_mod;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;


import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class Sms_send_activity extends AppCompatActivity {
    EditText content;
    TextView countv,smscount,send,dis_send;
    RequestBody AccessTokenValue = null;
    String json;
    Integer sms;
    int scl_id;
    String take;
    Utils utils;
    ArrayList<Integer> did,cid;
    private mod_sms mod_sms;
    AlertDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_send_activity);
        content=(EditText)findViewById(R.id.content);
        countv=(TextView) findViewById(R.id.tv_count);
        smscount=(TextView) findViewById(R.id.tv_count2);
        send=(TextView)findViewById(R.id.sms_send);
        dis_send=(TextView)findViewById(R.id.sms_dissend);
        did = new ArrayList<Integer>();
        cid=new ArrayList<Integer>();
        utils=new Utils();
        pd = new SpotsDialog(Sms_send_activity.this);
        scl_id=getIntent().getExtras().getInt("scis");
        take=getIntent().getExtras().getString("take");
        cid=getIntent().getIntegerArrayListExtra("clsid");
        did=getIntent().getIntegerArrayListExtra("divid");
        countv.setText("0");
        smscount.setText("0");

        content.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    countv.setText("0");
                    smscount.setText("0");
                }

                sms=((s.length())/160);
                smscount.setText(sms+1+"");

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s.length()==0){
                    countv.setText("0");
                    smscount.setText("0");
                }
                else if(s.length()>0) {
                    countv.setText(String.valueOf(s.length()) );
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==0){
                    smscount.setText("0");
                }
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send.setVisibility(View.GONE);
                dis_send.setVisibility(View.VISIBLE);
                sms_api();
            }
        });


    }
    public  void sms_api() {
        pd.show();
        if (content.length() == 0) {
            pd.dismiss();
            send.setVisibility(View.VISIBLE);
            dis_send.setVisibility(View.GONE);
            content.setError("Empty");

        } else {
            mod_sms = new mod_sms();
            mod_sms.setTypeId(String.valueOf(take));
            mod_sms.setMessage(content.getText().toString());
            mod_sms.setSchoolId(String.valueOf(scl_id));
            ArrayList<mod_sms.MultipleClassBean> classListBeans = new ArrayList<>();
            mod_sms.MultipleClassBean clss;
            for (int i = 0; i < cid.size(); i++) {
                clss = new mod_sms.MultipleClassBean();
                clss.setClassId(cid.get(i).toString());
                clss.setDivisionId(did.get(i).toString());
                classListBeans.add(clss);

            }
            mod_sms.setMultipleClass(classListBeans);
            System.out.println(mod_sms.getMultipleClass().size());
            try {
                Gson gson = new Gson();
                json = gson.toJson(mod_sms).trim();
                System.out.println("FinalData................\n" + json.toString());
            } catch (Exception e) {
                pd.dismiss();
                e.printStackTrace();
            }
            try {
                AccessTokenValue = RequestBody.create(MediaType.parse("application/json"), json.getBytes("UTF-8"));
            } catch (Exception e) {
                pd.dismiss();
                e.printStackTrace();
            }
//

            Call<sms_responce_mod> call = utils.getApi().school_sms(AccessTokenValue);
            call.enqueue(new retrofit2.Callback<sms_responce_mod >() {

                @Override
                public void onResponse(Call<sms_responce_mod > call, Response<sms_responce_mod > response) {
                    if (response.isSuccess()) {
                        //  List<login_model.UserDataBean> details = response.body().getUserData();
                        if (response.body().getMsg().equals("Successful")) {
                            // Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), "Message sent successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Sms_school.class);
                            startActivity(intent);
                            finish();
                            Log.e("response :", response.body().getMsg());
                        } else {
                            pd.dismiss();
                            Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                        }
                        pd.dismiss();
                    } else {
                        send.setVisibility(View.VISIBLE);
                        dis_send.setVisibility(View.GONE);

                        Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                    pd.dismiss();
                }


                @Override
                public void onFailure(Call<sms_responce_mod > call, Throwable t) {
                    send.setVisibility(View.VISIBLE);
                    dis_send.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "check network connection", Toast.LENGTH_SHORT).show();
                }


            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(),Sms_school.class);
        startActivity(intent);
        finish();
    }
}
