package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.API_interface.API_interface;
import com.schoolmanapp.ktct_school.school.parent.Api_client.Api_client;
import com.schoolmanapp.ktct_school.school.parent.model_class.model_fees;
import com.schoolmanapp.ktct_school.school.parent.utils.AppPreferences;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by srishtiinnovative on 11/01/18.
 */

public class outstanding extends Fragment {


    @Bind(R.id.paid_det_date)
    TextView tv_date;
//    @Bind(R.id.paid_det_billno)
//    TextView tv_billno;
    @Bind(R.id.paid_det_name)
    TextView tv_name;
    @Bind(R.id.paid_det_class)
    TextView tv_class;
    @Bind(R.id.list_fees)

    ListView lv;
    @Bind(R.id.total)
    TextView tv_total;
    AppPreferences appPreferences;
    String student_name,student_class_name;
    List<model_fees.DataBean.DueHistoryDataBean> details;
    adapter_dues adapter;
    int amount;

    int kidid,classid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.outsatnding, container, false);
        ButterKnife.bind(this, view);
        appPreferences = AppPreferences.getInstance(getActivity(), getResources().getString(R.string.app_name));


        student_name = appPreferences.getData("student_name");

        student_class_name = appPreferences.getData("student_class_name");
        tv_name.setText(student_name);
        tv_class.setText(student_class_name);
//        Bundle args =getActivity().getExtras();
//        if (args != null) {
//
//            int billno=args.getInt("pbillno");
//            String pdate=args.getString("pdate");
//            tv_billno.setText(billno+"");
//            tv_date.setText(pdate);
//        }

        api();
return  view;

    }
    void api()
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

                    details = response.body().getData().getDueHistoryData();
                    if(details.size()>0) {
                        for(int i=0;i<details.size();i++)
                            amount+=response.body().getData().getDueHistoryData().get(i).getAmount();
                        tv_total.setText(amount+"");

                        adapter = new adapter_dues(getActivity(), details);
                        lv.setAdapter(adapter);




                    }
                    else
                    {
                        Toast.makeText(getActivity(), "No Dues", Toast.LENGTH_SHORT).show();
                    }

                }

            }

            @Override
            public void onFailure(Call<model_fees> call, Throwable t) {

                Toast.makeText(getActivity(), "failed", Toast.LENGTH_SHORT).show();

            }
        });
    }




}
