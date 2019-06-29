package com.schoolmanapp.ktct_school.school.parent;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.API_interface.API_interface;
import com.schoolmanapp.ktct_school.school.parent.Api_client.Api_client;
import com.schoolmanapp.ktct_school.school.school.Model.event_model;
import com.schoolmanapp.ktct_school.school.school.NothingSelectedSpinnerAdapter;
import com.schoolmanapp.ktct_school.school.school.common.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by srishtiinnovative on 16/02/18.
 */

public class actvity_events extends Activity implements
        AdapterView.OnItemSelectedListener {
    @Bind(R.id.spinnerss)
    Spinner spinner;
    List<event_model.UserDataBean> list_events;
    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November",
            "December"};

    ArrayAdapter MONTH_ADAPTER;

    String day_api;

    @Bind(R.id.list_eventsss)
    ListView list;

//
//    @Inject
//    Utils utils;

    adapter_events adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_layout);
        ButterKnife.bind(this);

//     getApp().getActivityComponent().inject(this);

        list_events = new ArrayList<>();
        MONTH_ADAPTER = new ArrayAdapter(this, android.R.layout.simple_spinner_item, months);
        MONTH_ADAPTER.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setPrompt("month");
        spinner.setAdapter(new NothingSelectedSpinnerAdapter(MONTH_ADAPTER, R.layout.edit_nothingselected, this));
        spinner.setOnItemSelectedListener(this);

//        processapi("2018-02-05 00:00:00");
////        try {
//            String str_date = "2012-08-24T09:59:59Z";
//
//            Date date;
//            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//            date = (Date) simpleFormat.parse(str_date);
//            System.out.println("Today is " + date);
//            String dayOfTheWeek = (String) DateFormat.format("EE", date); // Thursday
//            String day          = (String) DateFormat.format("dd",   date);
//            Log.e("Today is",date+"");
//            Log.e("dayOfTheWeek is",dayOfTheWeek+"");
//            Log.e("day is",day+"");
//        } catch (ParseException e) {
//            System.out.println("Exception :" + e);
//            Log.e("Exception is",e+"");
//        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        if (!(parent.getItemAtPosition(position) + "").equals("January")) {
//            day_api = "2018-01-05 00:00:00";
//            processapi(day_api);
//
//        } else if (!(parent.getItemAtPosition(position) + "").equals("February")) {
//            day_api = "2018-02-05 00:00:00";
//            processapi(day_api);
//
//        } else if (!(parent.getItemAtPosition(position) + "").equals("March")) {
//            day_api = "2018-03-05 00:00:00";
//            processapi(day_api);
//
//        } else if (!(parent.getItemAtPosition(position) + "").equals("April")) {
//            day_api = "2018-04-05 00:00:00";
//            processapi(day_api);
//
//        } else if (!(parent.getItemAtPosition(position) + "").equals("May")) {
//            day_api = "2018-05-05 00:00:00";
//            processapi(day_api);
//
//        } else if (!(parent.getItemAtPosition(position) + "").equals("June")) {
//            day_api = "2018-06-05 00:00:00";
//            processapi(day_api);
//
//        } else if (!(parent.getItemAtPosition(position) + "").equals("July")) {
//            day_api = "2018-07-05 00:00:00";
//            processapi(day_api);
//
//        } else if (!(parent.getItemAtPosition(position) + "").equals("August")) {
//            day_api = "2018-08-05 00:00:00";
//            processapi(day_api);
//
//        } else if (!(parent.getItemAtPosition(position) + "").equals("September")) {
//            day_api = "2018-09-05 00:00:00";
//            processapi(day_api);
//
//        } else if (!(parent.getItemAtPosition(position) + "").equals("October")) {
//            day_api = "2018-10-05 00:00:00";
//            processapi(day_api);
//
//        } else if (!(parent.getItemAtPosition(position) + "").equals("November")) {
//            day_api = "2018-11-05 00:00:00";
//            processapi(day_api);
//
//        } else if (!(parent.getItemAtPosition(position) + "").equals("December")) {
//            day_api = "2018-12-05 00:00:00";
//            processapi(day_api);
//
//        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


//    void processapi(String day) {
//        list_events.clear();
//        final API_interface api = Api_client.getevent().create(API_interface.class);
//        Call<event_model> call = api.getevent("10093",day);
//        call.enqueue(new retrofit2.Callback<event_model>() {
//
//            @Override
//            public void onResponse(Call<event_model> call, Response<event_model> response) {
//                if (response.isSuccess()) {
//                    if (response.body().isStatus()) {
//
//                        if (response.body().getMsg().equals("Success")) {
//                            list_events.addAll(response.body().getUserData());
//
//
//                            adapter = new adapter_events(actvity_events.this, list_events);
//                            list.setAdapter(adapter);
//
//
//
//                        }
//                        //dialog_alert.dismiss();
//                    } else
//
//                    {
//
//
//                        Toast.makeText(getApplicationContext(), "Events unavailable to fetch", Toast.LENGTH_SHORT).show();
//
//                    }
//                }
//                        // dialog_alert.dismiss();
//                   else {
//
//                    // toast("server error.Please check your network");
//
//                       Toast.makeText(getApplicationContext(), "server error.Please check your network", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<event_model> call, Throwable t) {
//
//               // toast("server error.Please check your network");
//                 Toast.makeText(getApplicationContext(), "server error.Please check your network", Toast.LENGTH_SHORT).show();
//                Log.e("ERROR!!", t.getMessage());
//
//            }
//
//        });
//
//    }

}
