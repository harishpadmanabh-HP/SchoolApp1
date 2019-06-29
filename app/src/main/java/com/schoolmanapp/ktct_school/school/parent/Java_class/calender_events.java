package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.API_interface.API_interface;
import com.schoolmanapp.ktct_school.school.parent.Api_client.Api_client;
import com.schoolmanapp.ktct_school.school.parent.model_class.model_events_Calender;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by srishtiinnovative on 19/02/18.
 */

public class calender_events extends Activity {
    @Bind(R.id.list_calender)
    ListView list;
    List<model_events_Calender.UserDataBean> list_events;
    ArrayList<String>list_dates,list_desc;
    com.prolificinteractive.materialcalendarview.MaterialCalendarView widget1;
    AlertDialog dialog;
    int year;
    String month;
    String calender_event_date;
    ArrayList dates, indexes;
    adapter_calender_events adapter;
    String scl_id="";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_events);
        ButterKnife.bind(this);
        list_events = new ArrayList();
        list_dates = new ArrayList();
        list_desc = new ArrayList();
        dates = new ArrayList();
        indexes = new ArrayList();

        dialog = new SpotsDialog(this);
        widget1 = (com.prolificinteractive.materialcalendarview.MaterialCalendarView) findViewById(R.id.calendarView_calender);
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        if (((c.get(Calendar.MONTH) + 1) == 10) || ((c.get(Calendar.MONTH) + 1) == 11) || ((c.get(Calendar.MONTH) + 1) == 12)) {
            month = c.get(Calendar.MONTH) + 1 + "";
        } else {
            month = "0" + (c.get(Calendar.MONTH) + 1);
        }
        scl_id=getIntent().getStringExtra("schoolid");
        if(scl_id==null){
            scl_id="10120";
        }
        processapi(year + "-" + month + "-05 00:00:00");

        widget1.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
//                Log.e("getDate!!!1", date.getDate() + "");
//                Log.e("getYear!!!1", date.getYear() + "");
//                Log.e("getMonth!!!1", (date.getMonth() + 1) + "");
//                if (((date.getMonth() + 1) == 10) || ((date.getMonth() + 1) == 11) || ((date.getMonth() + 1) == 12)) {
//                    Log.e("api!!", date.getYear() + "-" + (date.getMonth() + 1) + "-05 00:00:00");
//                    processapi(date.getYear() + "-" + (date.getMonth() + 1) + "-05 00:00:00");
//                } else {
//                    Log.e("api!!", date.getYear() + "-0" + (date.getMonth() + 1) + "-05 00:00:00");
//                    processapi(date.getYear() + "-0" + (date.getMonth() + 1) + "-05 00:00:00");
//                }
                processapi(year + "-" + month + "-05 00:00:00");
            }
        });







        widget1.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Log.e("checkdATE", date.getDate() + "");
                indexes.clear();
//                list_events.clear();
//                dates.clear();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                //dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                String date_txt = dateFormat.format(date.getDate());

                if (dates.contains(date_txt + "T00:00:00")) {
                    int position = -1;
                    // position = dates.indexOf(date_txt+"T00:00:00");
                    Log.e("position!!!", position + "");
                    for (int i = 0; i < dates.size(); i++) {
                        if (dates.get(i).equals(date_txt + "T00:00:00")) {
                            String id = Integer.toString(i);
                            indexes.add(id);

                        }
                    }
                    Log.e("position!!!", indexes + "");
                    adapter = new adapter_calender_events(calender_events.this, list_dates,list_desc,indexes);
                    list.setAdapter(adapter);

                    //  Log.e("getDescription!!!",list_events.get(position).getDescription());

                }else {
                    list.setAdapter(null);
                }

                Log.e("date_click", date_txt);


            }
        });

    }

    void processapi(String day) {
        dialog.show();
        list_events.clear();
         dates.clear();
   //     indexes.clear();
        final API_interface api = Api_client.getevent().create(API_interface.class);
        Call<model_events_Calender> call = api.getcalenderevent(scl_id);
        call.enqueue(new retrofit2.Callback<model_events_Calender>() {

            @Override
            public void onResponse(Call<model_events_Calender> call, Response<model_events_Calender> response) {
                if (response.isSuccess()) {
                    if (response.body().isStatus()) {

                        if (response.body().getMsg().equals("Success")) {
                            if (response.body().getUserData().size() > 0) {
                                list_events.addAll(response.body().getUserData());

                                for (int i = 0; i < list_events.size(); i++) {
                                    if(!(list_events.get(i).getHead().equals("Circular")))
                                    {

                                        list_dates.add(list_events.get(i).getCircularDate());
                                        list_desc.add(list_events.get(i).getDescription());
                                     dates.add(list_events.get(i).getCircularDate());
                                    calender_event_date = list_events.get(i).getCircularDate();
                                    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                                    Date convertedDate = new Date();
                                    try {


                                        convertedDate = simpleFormat.parse(calender_event_date);
                                        Log.e("date", " :" + convertedDate);

                                    } catch (ParseException e) {

                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                    Calendar cal = Calendar.getInstance();
                                    cal.setTime(convertedDate);
                                    String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
                                    Log.e("formatedDate : ", "" + formatedDate);



                                    List<CalendarDay> doubleEventDays = new ArrayList<>();
                                    doubleEventDays.add(CalendarDay.from(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH)), cal.get(Calendar.DATE)));


                                    widget1.addDecorators(
                                            new DoubleEventDecorator_calender(Color.BLUE, doubleEventDays));

                                }
                                }
                                dialog.dismiss();

//                                adapter = new adapter_events(activity_newevents.this, list_events);
//                                list.setAdapter(adapter);
                            } else {
                                list_events.clear();
                                Toast.makeText(getApplicationContext(), "Events unavailable to fetch", Toast.LENGTH_SHORT).show();
//                                adapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }


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
            public void onFailure(Call<model_events_Calender> call, Throwable t) {
                dialog.dismiss();
                // toast("server error.Please check your network");
                Toast.makeText(getApplicationContext(), "server error.Please check your network", Toast.LENGTH_SHORT).show();
                Log.e("ERROR!!", t.getMessage());
                //  adapter.notifyDataSetChanged();

            }

        });
    }
}
