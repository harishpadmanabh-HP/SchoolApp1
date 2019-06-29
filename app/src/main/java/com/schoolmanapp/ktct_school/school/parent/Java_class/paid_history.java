package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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

public class paid_history extends Fragment  {
    @Bind(R.id.list)
    ListView list;
  //  int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    AppPreferences appPreferences;

    List<model_fees.DataBean.PaidHistoryDataBean> details;

    adapter_details adapter;
    adapter_fees adapter_fees;
    int kidid,classid;
    int amount,position;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fees, container, false);
        ButterKnife.bind(this, view);
        appPreferences = AppPreferences.getInstance(getActivity(), getResources().getString(R.string.app_name));
   //     Toast.makeText(getActivity(),"paid",Toast.LENGTH_SHORT).show();
        api();

        return view;

    }
    void api() {


        final API_interface api = Api_client.getClientfees().create(API_interface.class);
        kidid=appPreferences.getInt("kid_id");
        classid=appPreferences.getInt("class_id");
        Log.e("kidid",kidid+"");
        Log.e("classid",classid+"");

        Call<model_fees> call = api.paid_history(kidid,classid);
        // Call<Mod_school_reg> call = getApi().school_registration(map);
        call.enqueue(new Callback<model_fees>() {
            @Override
            public void onResponse(Call<model_fees> call, Response<model_fees> response) {
                if(response.isSuccess()) {
                 //   if (response.body().getMsg().equalsIgnoreCase("Successfull")) {
                        //response.body().getData().getPaidHistoryData()
                        details = response.body().getData().getPaidHistoryData();
                        if(details.size()>0) {
//                            for (int i = 0; i < details.size(); i++) {
//                                String paid_date = details.get(i).getBilldate();
//                                int paid_amount = details.get(i).getPaidBillsData().get(i).getAmount();
                            adapter_fees = new adapter_fees(getActivity(), details);
                            list.setAdapter(adapter_fees);
                          //  Toast.makeText(getActivity(),"paid",Toast.LENGTH_SHORT).show();

//                            }
                      //  }


                    }
                }
                else
                {

                    Toast.makeText(getActivity(),"paid",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<model_fees> call, Throwable t) {

                Toast.makeText(getActivity(), "failed", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
