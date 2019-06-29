package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.app.Activity;
import android.app.AlertDialog;
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
import com.schoolmanapp.ktct_school.school.parent.adapter_events;
import com.schoolmanapp.ktct_school.school.school.Model.event_model;
import com.schoolmanapp.ktct_school.school.school.NothingSelectedSpinnerAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by srishtiinnovative on 19/02/18.
 */

public class activity_newevents extends Activity implements
        AdapterView.OnItemSelectedListener {


    @Bind(R.id.spinnerss)
    Spinner spin;

    @Bind(R.id.list_eventsss)
    ListView list;

    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November",
            "December"};
    ArrayAdapter MONTH_ADAPTER;
    String day_api;
    List<event_model.UserDataBean> list_events;
    adapter_events adapter;
    AlertDialog dialog;
    int year;
    String month;
    ArrayList<String> list_circular;
    String scl_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_layout);
        ButterKnife.bind(this);
        dialog = new SpotsDialog(this);
        list_events = new ArrayList<>();
        list_circular=new ArrayList<>();
        MONTH_ADAPTER = new ArrayAdapter(this, android.R.layout.simple_spinner_item, months);
        MONTH_ADAPTER.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setPrompt("month");
        spin.setAdapter(new NothingSelectedSpinnerAdapter(MONTH_ADAPTER, R.layout.edit_nothingselected, this));
        spin.setOnItemSelectedListener(this);
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        if (((c.get(Calendar.MONTH) + 1) == 10) || ((c.get(Calendar.MONTH) + 1) == 11) || ((c.get(Calendar.MONTH) + 1) == 12)) {
            month = c.get(Calendar.MONTH) + 1 + "";
        } else {
            month = "0" + (c.get(Calendar.MONTH) + 1);
        }
        spin.setSelection(c.get(Calendar.MONTH)+1);
      //  processapi(year + "-" + month + "-05 00:00:00");
        Log.e("year:", year + "");
        Log.e("month:", month + "");
        Log.e("day!!!:", year + "-" + month + "-05 00:00:00");
        scl_id=getIntent().getStringExtra("schoolid");
        if(scl_id==null){
            scl_id="40157";
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        list_events.clear();
        list.setAdapter(null);

        Log.e("id:", parent.getItemAtPosition(position) + "");

        if ((parent.getItemAtPosition(position) + "").equals("January")) {
            day_api = "2018-01-05 00:00:00";
            processapi(year + "-01-05 00:00:00");

        } else if ((parent.getItemAtPosition(position) + "").equals("February")) {
            day_api = "2018-02-05 00:00:00";
            processapi(year + "-02-05 00:00:00");

        } else if ((parent.getItemAtPosition(position) + "").equals("March")) {
            day_api = "2018-03-05 00:00:00";
            processapi(year + "-03-05 00:00:00");

        } else if ((parent.getItemAtPosition(position) + "").equals("April")) {
            day_api = "2018-04-05 00:00:00";
            processapi(year + "-04-05 00:00:00");

        } else if ((parent.getItemAtPosition(position) + "").equals("May")) {
            day_api = "2018-05-05 00:00:00";
            processapi(year + "-05-05 00:00:00");

        } else if ((parent.getItemAtPosition(position) + "").equals("June")) {
            day_api = "2018-06-05 00:00:00";
            processapi(year + "-06-05 00:00:00");

        } else if ((parent.getItemAtPosition(position) + "").equals("July")) {
            day_api = "2018-07-05 00:00:00";
            processapi(year + "-07-05 00:00:00");

        } else if ((parent.getItemAtPosition(position) + "").equals("August")) {
            day_api = "2018-08-05 00:00:00";
            processapi(year + "-08-05 00:00:00");

        } else if ((parent.getItemAtPosition(position) + "").equals("September")) {
            day_api = "2018-09-05 00:00:00";
            processapi(year + "-09-05 00:00:00");

        } else if ((parent.getItemAtPosition(position) + "").equals("October")) {
            day_api = "2018-10-05 00:00:00";
            processapi(year + "-10-05 00:00:00");

        } else if ((parent.getItemAtPosition(position) + "").equals("November")) {
            day_api = "2018-11-05 00:00:00";
            processapi(year + "-11-05 00:00:00");

        } else if ((parent.getItemAtPosition(position) + "").equals("December")) {
            day_api = "2018-12-05 00:00:00";
            processapi(year + "-12-05 00:00:00");

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    void processapi(String day) {
        dialog.show();

        Log.e("day",day);
        final API_interface api = Api_client.getevent().create(API_interface.class);
        Call<event_model> call = api.getevent(scl_id, day);
        call.enqueue(new retrofit2.Callback<event_model>() {

            @Override
            public void onResponse(Call<event_model> call, Response<event_model> response) {
                if (response.isSuccess()) {
                    if (response.body().isStatus()) {

                        if (response.body().getMsg().equals("Success")) {
                            if (response.body().getUserData().size() > 0) {

                                list_events.addAll(response.body().getUserData());
                                adapter = new adapter_events(activity_newevents.this, list_events);
                                list.setAdapter(adapter);
                                dialog.dismiss();
                                adapter.notifyDataSetChanged();




                            } else {
                                list_events.clear();
                                Toast.makeText(getApplicationContext(), "Events unavailable to fetch", Toast.LENGTH_SHORT).show();
                                adapter.notifyDataSetChanged();
                            }

                            dialog.dismiss();

                        } else

                        {

                            list_events.clear();
                            Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            list.setAdapter(null);
                            dialog.dismiss();
                            // adapter.notifyDataSetChanged();

                        }
                        //dialog_alert.dismiss();
                    } else

                    {


                        Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        //   adapter.notifyDataSetChanged();

                    }
                }
                // dialog_alert.dismiss();
                else {
                    dialog.dismiss();
                    // toast("server error.Please check your network");
                    //   adapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "server error.Please check your network", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<event_model> call, Throwable t) {
                dialog.dismiss();
                // toast("server error.Please check your network");
                Toast.makeText(getApplicationContext(), "server error.Please check your network", Toast.LENGTH_SHORT).show();
                Log.e("ERROR!!", t.getMessage());
                //  adapter.notifyDataSetChanged();

            }

        });

    }
}
