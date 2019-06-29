package com.schoolmanapp.ktct_school.school.school;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_List_Bus;
import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by srishtiinnovative on 01/06/17.
 */

public class HistoryFragment extends Fragment implements Validator.ValidationListener {
    View view;
    TextView iv;
    Spinner sp;
    int spinbusid_pos,sid,shiftid=0;
    String scid;
    Validator validator;
    Calendar myCalendar;
    ArrayList<String> busname;
    ArrayList<Integer> bid;
    int ans;
    @Bind(R.id.sp_shift)
    Spinner sp_shift;

    @Bind(R.id.cal_icon)
    ImageView iv_cal;
    @NotEmpty
    @Bind(R.id.busdate)
    EditText et_busdate;
    @NotEmpty
    @Bind(R.id.tripid)
    EditText et_tripid;
    String trip_date,str_tripid;

    String shift[]={"Shift","Morning" ,"Afternoon"};
    //int shit;
    String shift_status;
    @Inject
    Utils utils;
    @Inject
    AppPreferences appPreferences;
    ArrayAdapter<String> dataAdapter_bus;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.history,container,false);
        iv = (TextView) view.findViewById(R.id.hist_btn);
        Home h=(Home)getActivity();
        sp=(Spinner)view.findViewById(R.id.sp_busid);
        ButterKnife.bind(this,view);
        validator = new Validator(this);
        validator.setValidationListener(this);
        bid=new ArrayList<Integer>();
        busname=new ArrayList<String>();
        busname.add("Bus Name");
        bid.add(0);
        Home.isInHomePage = false;
        h.getApp().getActivityComponent().inject(this);
        sid = appPreferences.getInt("scid");
        scid = Integer.toString(sid);

        buslist();
        dataAdapter_bus  = new ArrayAdapter<String>(getActivity(), R.layout.custom_spinner, busname);

        // Drop down layout style - list view with radio button
        try {


            dataAdapter_bus.setDropDownViewResource(R.layout.custom_dropdown);
        }catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        // attaching data adapter to spinner
        try {
            sp.setAdapter(dataAdapter_bus);
        }catch (NullPointerException e)
        {
            e.printStackTrace();
        }
      //  sp.setAdapter(dataAdapter_bus);
//        try{
//        dataAdapter_bus.notifyDataSetChanged();
//        }catch (Exception e)
//        {
//            utils.toToast("No bus added for the school");
//        }


        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinbusid_pos=bid.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getActivity(), R.layout.custom_spinner, shift);

        // Drop down layout style - list view with radio button
        try{
        dataAdapter1.setDropDownViewResource(R.layout.custom_dropdown);
        }catch (Exception e)
        {
            utils.toToast("No shift added for the bus");
        }

        // attaching data adapter to spinner
        dataAdapter1.notifyDataSetChanged();
        sp_shift.setAdapter(dataAdapter1);
        sp_shift.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position==1)
                {
                    shift_status="0";
                    shiftid=position;

                }
                else if(position==2) {
                    shift_status = "1";
                    shiftid=position;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        myCalendar= Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        iv_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(),date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(spinbusid_pos >0 && shiftid>0) {
                    validator.validate();
                }
                else
                if (spinbusid_pos < 1) {
                    TextView errorText = (TextView) sp.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("Please select a bus");//changes the selected item text to this
                }
                else if(shiftid<=0)
              {
                    TextView errorText = (TextView) sp_shift.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("Please select a shift");//changes the selected item text to this
                }


            }
        });
        return view;
    }

    private  void loadFragment2(Fragment_customtravel fragment)
    {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        android.app.FragmentManager fm=getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction=fm.beginTransaction();
        fragmentTransaction.replace(R.id.frame_lay,fragment);
        fragmentTransaction.commit();
    }
    public  void buslist() {
        try{
        Call<Mod_List_Bus> call = utils.getApi().buslist(scid);

        call.enqueue(new Callback<Mod_List_Bus>() {
            @Override
            public void onResponse(Call<Mod_List_Bus> call, Response<Mod_List_Bus> response) {
                if (response.isSuccess()) {
                    if (response.body().getMsg().equals("Success")) {

                        List<Mod_List_Bus.UserDataBean> details = response.body().getUserData();
                        // utils.toToast(response.body().getMsg());
                      //  Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                        //appPreferences.saveData(Constants.currentUser, details.get(0).getUserId());
                        //  appPreferences.saveDataBoolean(Constants.isLogin, true);
                        if (details.size() > 0) {
                            int l = details.size();
                            for (int i = 0; i < l; i++) {
                                int busid = details.get(i).getBusId();

                                String bus_regno = details.get(i).getBusSpecialId().toString();

                                String loc = details.get(i).getLocationStart();



                                bid.add(busid);
                               
                                busname.add(bus_regno+" - "+loc);


                            }
                            dataAdapter_bus.notifyDataSetChanged();

                        } else
                           // utils.toToast(response.body().getMsg());
                            utils.toToast("No Buses Availlable");
                    }
                    else utils.toToast(response.body().getMsg());
                }else
                    utils.toToast(response.body().getMsg());


            }


            @Override
            public void onFailure(Call<Mod_List_Bus> call, Throwable t) {
                //  utils.toToast("Network failure");
                Toast.makeText(getActivity(), "Network Failure", Toast.LENGTH_SHORT).show();
            }

        });
        }catch(Exception e)
        {
            e.printStackTrace();


            //Toast.makeText(getActivity(),"Network Failure",Toast.LENGTH_SHORT).show();
        }


    }
    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        et_busdate.setText(sdf.format(myCalendar.getTime()));
        trip_date=et_busdate.getText().toString();
    }
    public void store_pram_history()
    {
        String str_bid=Integer.toString(spinbusid_pos);
        str_tripid=et_tripid.getText().toString();
        appPreferences.saveData("bus_ID",str_bid);
        appPreferences.saveData("shift",shift_status);
        appPreferences.saveData("tripid",str_tripid);
        appPreferences.saveData("tripdate",trip_date);


    }

    @Override
    public void onValidationSucceeded() {
        store_pram_history();
        loadFragment2(new Fragment_customtravel());

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getActivity());

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }

        }
    }

}
