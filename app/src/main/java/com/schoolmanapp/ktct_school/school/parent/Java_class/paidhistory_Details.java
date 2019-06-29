package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.API_interface.API_interface;
import com.schoolmanapp.ktct_school.school.parent.Api_client.Api_client;
import com.schoolmanapp.ktct_school.school.parent.model_class.model_fees;
import com.schoolmanapp.ktct_school.school.parent.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.DeleteclassInterface;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by srishtiinnovative on 11/01/18.
 */

public class paidhistory_Details extends Activity  {

    @Bind(R.id.paid_det_date)
    TextView tv_date;
    @Bind(R.id.paid_det_billno)
    TextView tv_billno;
    @Bind(R.id.paid_det_name)
    TextView tv_name;
    @Bind(R.id.paid_det_class)
    TextView tv_class;
    @Bind(R.id.list_fees)
    ListView lv;
    @Bind(R.id.out_total)
    TextView tv_total;
    @Bind(R.id.iv_settings)
    ImageView iv_settings;
    AppPreferences appPreferences;
    String student_name,student_class_name;
    adapter_details adapter;

    int kidid,classid;
    int amount,position;

    List<model_fees.DataBean.PaidHistoryDataBean> details;
    List<model_fees.DataBean.PaidHistoryDataBean.PaidBillsDataBean> paidbaills;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_outstanding);
        ButterKnife.bind(this);


        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));


        student_name = appPreferences.getData("student_name");

        student_class_name = appPreferences.getData("student_class_name");
        tv_name.setText(student_name);
        tv_class.setText(student_class_name);

        api(Integer.parseInt(getIntent().getStringExtra("position")));


//        Bundle args =getIntent().getExtras();
//        if (args != null) {
//
//            int billno=args.getInt("pbillno");
//            String pdate=args.getString("pdate");
//            position=args.getInt("position");
//            amount=args.getInt("amount");
//            tv_billno.setText(billno+"");
//            tv_date.setText(pdate);
//        }

      //  api();

    //    tv_total.setText(amount+"");

        iv_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   dialog.show();
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);

            }
        });
    }

    void api(final int position)
    {

        kidid=appPreferences.getInt("kid_id");
        classid=appPreferences.getInt("class_id");
        final API_interface api = Api_client.getClientfees().create(API_interface.class);
        Call<model_fees> call = api.paid_history(kidid,classid);
        // Call<Mod_school_reg> call = getApi().school_registration(map);
        call.enqueue(new Callback<model_fees>() {
            @Override
            public void onResponse(Call<model_fees> call, Response<model_fees> response) {
                if(response.isSuccess()) {

                    details = response.body().getData().getPaidHistoryData();
                    if(details.size()>0) {
                        // for(int i=0;i<details.size();i++) {
                        paidbaills = response.body().getData().getPaidHistoryData().get(position).getPaidBillsData();
                            int l=paidbaills.size();
                            for(int j=0;j<l;j++)
                            {
                                amount+=paidbaills.get(j).getAmount();
                            }


                          adapter = new adapter_details(paidhistory_Details.this, paidbaills);
                        lv.setAdapter(adapter);
                        // paidbaills=details.get(pos).getPaidBillsData();
//                        adapter_fees = new adapter_fees(getContext(), paidbaills);
//                        list.setAdapter(adapter);


                        tv_total.setText(amount+"");
//                        Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();

                    }
                }

            }

            @Override
            public void onFailure(Call<model_fees> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();

            }
        });
    }



}

