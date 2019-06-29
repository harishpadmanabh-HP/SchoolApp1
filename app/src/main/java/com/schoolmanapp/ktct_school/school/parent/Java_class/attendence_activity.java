package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.parent.API_interface.API_interface;
import com.schoolmanapp.ktct_school.school.parent.Api_client.Api_client;
import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.model_class.calender_model;
import com.schoolmanapp.ktct_school.school.parent.model_class.new_attendence_model;
import com.schoolmanapp.ktct_school.school.parent.utils.AppPreferences;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

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

import static com.schoolmanapp.ktct_school.school.R.id.calendarView;

/**
 * Created by srishtiinnovative on 05/06/17.
 */

public class attendence_activity extends AppCompatActivity implements OnDateSelectedListener {
    AppPreferences appPreferences;
    int kid_id, year, month;
    String attendence_date;
    AlertDialog dialog;
    int total_att=0,total_present=0,total_absent,total_taken=0;

//    private HashSet<CalendarDay> getCalendarDaysSet(Calendar cal1, Calendar cal2) {
//        HashSet<CalendarDay> setDays = new HashSet<>();
//        while (cal1.getTime().before(cal2.getTime())) {
//            CalendarDay calDay = CalendarDay.from(cal1);
//            setDays.add(calDay);
//            cal1.add(Calendar.DATE, 1);
//        }
//
//        return setDays;
//    }

    @Bind(calendarView)
    MaterialCalendarView widget1;

    @Bind(R.id.total_present)
    TextView tv_tp;
    @Bind(R.id.total_absent)
    TextView tv_ta;
    @Bind(R.id.total_att)
    TextView tv_total;
    List<new_attendence_model.UserDataBean> details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);
        ButterKnife.bind(this);
        dialog = new SpotsDialog(this);
        Calendar calendar = Calendar.getInstance();
        widget1.setDateSelected(calendar.getTime(), true);
        widget1.setOnDateChangedListener(this);
        details=new ArrayList<>();

        widget1.setShowOtherDates(MaterialCalendarView.SHOW_ALL);
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        kid_id = appPreferences.getInt("kid_id");
        Log.e("kid_id", kid_id + "");
        get_attendence();
        //////////////////////////////////////////
//        Calendar instance = Calendar.getInstance();
//        widget1.setSelectedDate(instance.getTime());
//
//        Calendar instance1 = Calendar.getInstance();
//        instance1.set(instance1.get(Calendar.YEAR), Calendar.JANUARY, 1);
//        int a = Calendar.getInstance().get(Calendar.YEAR);
//        Log.e("instance :", " :" + a);
//        Calendar instance2 = Calendar.getInstance();
//        instance2.set(instance2.get(Calendar.YEAR), Calendar.DECEMBER, 31);


        widget1.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, final CalendarDay date) {
                get_attendence();

            }});
    }
    public void get_attendence(){
        dialog.show();
        String months = getSelectedDatesString();
        String years = getSelectedyearString();
        Log.e("month", months + " " + years);
        month = Integer.parseInt(months);
        year = Integer.parseInt(years);
        final API_interface api = Api_client.getClient().create(API_interface.class);
        Call<new_attendence_model> call = api.new_attendence(kid_id, month, year);
        call.enqueue(new retrofit2.Callback<new_attendence_model>() {

            @Override
            public void onResponse(Call<new_attendence_model> call, Response<new_attendence_model> response) {
                total_att=0;total_present=0;total_absent=0;
                if (response.isSuccess()) {
                    if (response.body().getMsg().equals("Success")) {
                      details  = response.body().getUserData();
                        total_att=response.body().getUserData().size()*2;
                        for (int i = 0; i < details.size(); i++) {
                            attendence_date = response.body().getUserData().get(i).getAttendanceDate();
                            appPreferences.saveData("attendence_data", attendence_date);
                            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                            Date convertedDate = new Date();
                            try {
                                convertedDate = dateFormat.parse(attendence_date);
                                Log.e("date", " :" + convertedDate);

                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(convertedDate);
                            String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
                            Log.e("formatedDate : ", "" + formatedDate);


                            int morning_shift = response.body().getUserData().get(i).getMornignShift() ;
                            if(morning_shift==1 )
                            total_present+=1;
//                           // Log.e("mrng", String.valueOf(total_present+=1));
                            appPreferences.saveData("morning_shift", String.valueOf(morning_shift));
                            int evening_shift = response.body().getUserData().get(i).getEveningShift() ;
                            if(evening_shift==1)
                            total_present+=1;

                            if(morning_shift==2 )
                                total_absent+=1;
                            if(evening_shift==2 )
                                total_absent+=1;


//                          //  Log.e("evng", String.valueOf(total_present+=1));
                            appPreferences.saveData("evening_shift", String.valueOf(evening_shift));
//
//                            Log.e("totalpresentcalc", String.valueOf(total_present));


                            List<CalendarDay> doubleEventDays = new ArrayList<>();
                            doubleEventDays.add(CalendarDay.from(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH)), cal.get(Calendar.DATE)));


                            widget1.addDecorators(
                                    new DoubleEventDecorator(Color.RED, Color.GREEN, doubleEventDays, morning_shift, evening_shift));
//                            Toast.makeText(getApplicationContext(), i+"", Toast.LENGTH_SHORT).show();

                        }


                        tv_tp.setText(total_present+"");
//                        Log.e("totalpresent", String.valueOf(total_present));
                        tv_total.setText(total_present+total_absent+"" );
                        tv_ta.setText(total_absent+"");


                    }

                    else if((response.body().getMsg().equals("Failed"))) {
                        tv_tp.setText("No attendence available");
                        tv_ta.setText("No attendence available");
                        tv_total.setText("No attendence available");
                        Toast.makeText(getApplicationContext(), "No attendence available", Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
//                    dialog.dismiss();
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<new_attendence_model> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

        });

    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

    }

//    @Override
//    public void onBackPressed() {
////        Intent intent = new Intent(attendence_activity.this, student_activity.class);
////        startActivity(intent);
//    }

    private String getSelectedDatesString() {
        CalendarDay date1 = widget1.getCurrentDate();
        if (date1 == null) {
            return date1.getMonth() + 1 + "";
        }
        return date1.getMonth() + 1 + "";
    }

    private String getSelectedyearString() {
        CalendarDay date1 = widget1.getCurrentDate();
        if (date1 == null) {
            return date1.getYear() + "";
        }
        return date1.getYear() + "";
    }
}