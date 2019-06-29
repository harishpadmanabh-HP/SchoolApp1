package com.schoolmanapp.ktct_school.school.school;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Add_Stud;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_List_Bus;
import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


/**
 * Created by srishtiinnovative on 01/06/17.
 */

public class AddStudentFragment extends Fragment implements Validator.ValidationListener {

    Calendar myCalendar;
    @Bind(R.id.cal_icon)
    ImageView iv_cal;

    @NotEmpty
    @Bind(R.id.dob)
    EditText et_date;

    View view;
    LinearLayout llhide, llshow;
    Validator validator;
    TextView iv;
    Spinner spclass, spdivision, spbus;
    ArrayList<String> classname;
    ArrayList<Integer> classid;
    ArrayList<String> divisionname, busname, tripno, tripnoofbus,gender_sp;
    ArrayList<Integer> divisionid, bid, tid;
    final private int ASK_WRITE_PERMISSION = 1001;
    final private int ASK_CAMERA_PERMISSION = 1002;
    final private int ASK_CAMERA_WRITE_PERMISSION = 1003;

    String picturePath;
    private static int RESULT_LOAD_IMAGE = 1;

    @Bind(R.id.st_class)
    TextView et_class;

    @Bind(R.id.st_div)
    TextView et_div;

    @Bind(R.id.st_blood_grp)
    EditText et_blood;

    @Bind(R.id.stud_prof_img)
    ImageView iv_propic;
    @NotEmpty
    @Bind(R.id.st_name)
    EditText et_name;
    @NotEmpty
    @Bind(R.id.st_parname)
    EditText et_parname;
    @NotEmpty
    @Bind(R.id.st_addr)
    EditText et_addr;
    @NotEmpty

    @Bind(R.id.st_phno)
    EditText et_phno;
    @NotEmpty
    @Bind(R.id.st_city)
    AutoCompleteTextView et_city;
    @NotEmpty
    @Bind(R.id.st_state)
    AutoCompleteTextView et_state;


    @Bind(R.id.sp_gender)
    Spinner sp_gender;

    @Bind(R.id.st_tripno)
    Spinner sp_trip;
    @NotEmpty
    @Bind(R.id.st_admno)
    EditText et_admnno;
    @Inject
    Utils utils;
    @Inject
    AppPreferences appPreferences;

    String scid;
    Bundle bundle;
    Integer sid, spin_pos = 0, spindiv_pos = 0, spinbusid_pos = 0, spintrip_pos = 0;
    //String spintrip_pos;
    String name, addr, city, state, parname, phno, cn, divname, bn, encodedString, trip, admno;
    AlertDialog pd;

    PlacesTask placesTask;
    ParserTask parserTask;
    int notrips, tripnumber = 0;
    String spinner_trip;
    ArrayAdapter<String> gender_adapter;
    ArrayAdapter<String> dataAdapter_div;
    ArrayAdapter<String> dataAdapter_trip;
    String class_id, div_id, c_id, d_id,b_id;
    Integer cid, did;
    Boolean ans, ans1;
    String picturename;
    File profileImageFile;
    @Bind(R.id.imgback)
    ImageView iv_back;
    String placetype,date,gender_pos,blood_grp;
    Integer position_genderspinner=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.addstudent, container, false);
        Home h = (Home) getActivity();
        h.getApp().getActivityComponent().inject(this);
        ButterKnife.bind(this, view);
        validator = new Validator(this);
        validator.setValidationListener(this);
        llhide = (LinearLayout) view.findViewById(R.id.tabhidelay);
        llshow = (LinearLayout) view.findViewById(R.id.tabaddlay);


        spbus = (Spinner) view.findViewById(R.id.sp_busid);

        iv = (TextView) view.findViewById(R.id.add_stud_btn);
        bundle = new Bundle();
        classname = new ArrayList<String>();
        classid = new ArrayList<Integer>();
        divisionname = new ArrayList<String>();
        divisionid = new ArrayList<Integer>();
        tid = new ArrayList<Integer>();
        bid = new ArrayList<Integer>();
        gender_sp=new ArrayList<String>();
        busname = new ArrayList<String>();
        tripno = new ArrayList<String>();
        tripnoofbus = new ArrayList<String>();

        busname.add("Bus Name");
        tripno.add("Trip Number");
        gender_sp.add("Gender");
        gender_sp.add("Male");
        gender_sp.add("Female");
        bid.add(0);
        tripnoofbus.add("tripnumber");
        tid.add(0);
        classname.add("Class");
        classid.add(0);
        divisionname.add("Division");
        divisionid.add(0);
        sid = appPreferences.getInt("scid");
        scid = Integer.toString(sid);
        Bundle args = this.getArguments();
        if (args != null) {
//            class_id = args.getString("classname");
//            div_id = args.getString("divname");
//            cid = args.getInt("classid");
//            did = args.getInt("divid");
//            c_id = Integer.toString(cid);
//            d_id = Integer.toString(did);

        }
        class_id=appPreferences.getData("classtopass");
        div_id= appPreferences.getData("divisiontopass");
        cid=appPreferences.getInt("cidtopass");
        did= appPreferences.getInt("didtopass");

//        class_id = args.getString("classname");
//            div_id = args.getString("divname");
//            cid = args.getInt("classid");
//            did = args.getInt("divid");
            c_id = Integer.toString(cid);
            d_id = Integer.toString(did);

        et_class.setText(class_id);
        et_div.setText(div_id);


       gender_adapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_spinner, gender_sp);

        // Drop down layout style - list view with radio button
        gender_adapter.setDropDownViewResource(R.layout.custom_dropdown);

        // attaching data adapter to spinner
//        try {
//            gender_adapter .notifyDataSetChanged();
//        } catch (Exception e) {
//            utils.toToast(e+"");
//        }
        try {
            sp_gender.setAdapter(gender_adapter);
        }catch (NullPointerException e)
        {
            e.printStackTrace();
        }

        gender_adapter.notifyDataSetChanged();
        sp_gender.setSelection(0, false);
        sp_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position>0) {
                    position_genderspinner=position;
                    gender_pos = gender_sp.get(position);
//                    notrips = Integer.parseInt(tripnoofbus.get(position));
//                    trip(notrips);
                }
                else
                {
                    TextView errorText = (TextView)sp_gender.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("Please select Gender");//changes the selected item text to this
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        buslist();


        ArrayAdapter<String> dataAdapter_bus = new ArrayAdapter<String>(getActivity(), R.layout.custom_spinner, busname);

        // Drop down layout style - list view with radio button
        dataAdapter_bus.setDropDownViewResource(R.layout.custom_dropdown);

        // attaching data adapter to spinner
        try {
            dataAdapter_bus.notifyDataSetChanged();
        } catch (Exception e) {
            utils.toToast("No bus added for the school");
        }
        spbus.setAdapter(dataAdapter_bus);
        spbus.setSelection(0, false);
        spbus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position>0) {
                    spinbusid_pos = bid.get(position);
                    notrips = Integer.parseInt(tripnoofbus.get(position));
                    trip(notrips);
                }
                else
                {
                    TextView errorText = (TextView) spbus.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("Please select a Bus");//changes the selected item text to this
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dataAdapter_trip = new ArrayAdapter<String>(getActivity(), R.layout.custom_spinner, tripno);

        // Drop down layout style - list view with radio button
        dataAdapter_trip.setDropDownViewResource(R.layout.custom_dropdown);
        try {


            dataAdapter_trip.notifyDataSetChanged();
        } catch (Exception e) {
            utils.toToast("No trip added for the bus");
        }
        // attaching data adapter to spinner

        sp_trip.setAdapter(dataAdapter_trip);
        sp_trip.setSelection(0, false);


        sp_trip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

if(position>0) {

    spintrip_pos = tid.get(position);
    spinner_trip = tripno.get(position);
    tripnumber = position;

}
else {
    spinner_trip="0";
//    TextView errorText = (TextView) sp_trip.getSelectedView();
//    errorText.setError("");
//    errorText.setTextColor(Color.RED);//just to highlight that this is an error
//    errorText.setText("Please select a trip");//changes the selected item text to this
}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment3(new Student());
            }
        });


        iv_propic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });


        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                validator.validate();
                ans = phone_validation();
                ans1 = spinner_validation();
            }

        });
//        h.tabview3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadFragment3(new Displayclass());
//
//            }
//        });


        et_city.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                placetype="city";
                try {


                    placesTask = new PlacesTask();
                    placesTask.execute(s.toString());
                }catch (NullPointerException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });
        et_state.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                placetype="state";
                try {
                    placesTask = new PlacesTask();
                    placesTask.execute(s.toString());
                }catch (NullPointerException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
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

        return view;
    }
    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        et_date.setText(sdf.format(myCalendar.getTime()));
       date=et_date.getText().toString();
    }
    private void loadFragment2(Fragment fragment) {
        fragment.setArguments(bundle);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameadd_lay, fragment);
        fragmentTransaction.commit();
        pd.dismiss();

    }

    public void buslist() {
        try {
            Call<Mod_List_Bus> call = utils.getApi().buslist(scid);

            call.enqueue(new Callback<Mod_List_Bus>() {
                @Override
                public void onResponse(Call<Mod_List_Bus> call, Response<Mod_List_Bus> response) {
                    if (response.isSuccess()) {

                        List<Mod_List_Bus.UserDataBean> details = response.body().getUserData();
                        // utils.tif(oToast(response.body().getMsg());


                        String msg = response.body().getMsg();
                        if (msg.equals("Success")) {
                            if (details.size() > 0) {
                                int l = details.size();
                                for (int i = 0; i < l; i++) {
                                    int busid = details.get(i).getBusId();
                                    String loc = details.get(i).getLocationStart();

                                    String bus_regno = details.get(i).getBusSpecialId().toString();
                                    String no_trips = details.get(i).getTripNumber();
                                    tripnoofbus.add(no_trips);
                                    bid.add(busid);
                                    busname.add(bus_regno + " - " + loc);


                                }

                            } else
                                utils.toToast("No Bus added for the school");
                        } else
                            utils.toToast(response.body().getMsg());


                    }

                }


                @Override
                public void onFailure(Call<Mod_List_Bus> call, Throwable t) {
                    //  utils.toToast("Network failure");
                    Toast.makeText(getActivity(), "Network Failure", Toast.LENGTH_SHORT).show();
                }

            });
        } catch (Exception e) {
            utils.toToast("Select a Bus");
        }

    }

    public void trip(int not) {
        tripno.clear();
        tid.clear();
        tripno.add("Trip Number");
        tid.add(0);
        for (int i = 1; i <= not; i++) {

            String no = Integer.toString(i);
            tripno.add(no);
            tid.add(i);
        }
        dataAdapter_trip.notifyDataSetChanged();
    }


    @Override
    public void onValidationSucceeded() {
        System.out.print("valid_success");
        Boolean ans = phone_validation();
        Boolean ans1 = spinner_validation();
        if (ans && ans1) {

            name = et_name.getText().toString();
            addr = et_addr.getText().toString();
            phno = et_phno.getText().toString();
            parname = et_parname.getText().toString();
            city = et_city.getText().toString();
            state = et_state.getText().toString();
            admno = et_admnno.getText().toString();
            blood_grp=et_blood.getText().toString();
            //  trip = et_trip.getText().toString();


            if (picturePath == null) {

                Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(), R.drawable.dummy);
                ByteArrayOutputStream bao = new ByteArrayOutputStream();
                bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 100, bao);
                byte[] ba = bao.toByteArray();
                encodedString = Base64.encodeToString(ba, Base64.DEFAULT);

//                Uri path = Uri.parse("android.resource://com.example.srishtiinnovative.school/" + R.drawable.dummy);
//
//
//
//
//
//                picturePath = path.toString();
//                Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(), R.drawable.dummy);
//                ByteArrayOutputStream bao = new ByteArrayOutputStream();
//                bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 100, bao);
//                byte[] ba = bao.toByteArray();
//                encodedString = Base64.encodeToString(ba, Base64.DEFAULT);


            } else {
                File f = new File(picturePath);
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                InputStream inputStream = null;//You can get an inputStream using any IO API
                try {
                    inputStream = new FileInputStream(f);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                byte[] bytes;
                byte[] buffer = new byte[8192];
                int bytesRead;
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bytes = output.toByteArray();
                encodedString = Base64.encodeToString(bytes, Base64.DEFAULT);
                encodedString = resizeBase64Image(encodedString);
            }
//            String cid = Integer.toString(spin_pos);
//            String did = Integer.toString(spindiv_pos);
            if(spinbusid_pos==0){
                b_id="1";
            }else
            {
                b_id = Integer.toString(spinbusid_pos);

            }


            sid = appPreferences.getInt("scid");
            scid = Integer.toString(sid);
            //   utils.toToast("values"+scid+picturePath+name+parname+addr+state+city+phno+cid+did+bid);
            Log.e("Name :", name);
            Log.e("School id :", scid);
//            Log.e("picture path :", picturePath);
            Log.e("Parent :", parname);
            Log.e("Addr :", addr);
            Log.e("Name", name);
            Log.e("State :", state);
            Log.e("City :", city);
            Log.e("Phone", phno);
            Log.e("date",date);
            Log.e("Bus id :", b_id);
            Log.e("trip id :", spintrip_pos+"");
            Log.e("image  :", encodedString);
            Log.e("blood  :", blood_grp);
            Log.e("gender :", gender_pos);

            pd = new SpotsDialog(getActivity());
            pd.show();


            try {
                Call<Mod_Add_Stud> call = utils.getApi().addstud(scid, picturePath, name, parname, addr, state, city, phno, c_id, d_id, b_id, "2", spinner_trip, encodedString, admno,gender_pos,blood_grp,date);

                call.enqueue(new Callback<Mod_Add_Stud>() {
                    @Override
                    public void onResponse(Call<Mod_Add_Stud> call, Response<Mod_Add_Stud> response) {
                        if (response.isSuccess()) {
                            //Log.d("Success", response.message());
                            //   utils.toToast(response.body().getMsg());

                            String msg = response.body().getMsg();
                            if (msg.equals("Success")) {
                                String sid = response.body().getUserData();

                                //  Log.e("Student",sid);
                                bundle.putString("sid", sid);
                                bundle.putInt("cl_id",cid);
                                bundle.putInt("d_id",did);
                                bundle.putString("cl_name",class_id);
                                bundle.putString("d_name",div_id);

                                loadFragment2(new Fragment_cfm_stud());

                            } else {
                                if (pd.isShowing()) {
                                    pd.dismiss();
                                }

                                utils.toToast(response.body().getMsg());


                            }

                        } else

                        {
                            if (pd.isShowing()) {
                                pd.dismiss();
                            }

                            utils.toToast(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Mod_Add_Stud> call, Throwable t) {
                        if (pd.isShowing()) {
                            pd.dismiss();
                        }
                        utils.toToast("Failure");
                        // Log.d("failure", "failure" + t);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                if (pd.isShowing()) {
                    pd.dismiss();
                }

                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }

        }
    }
        @Override
        public void onValidationFailed (List < ValidationError > errors) {
            System.out.print("valid_failed");
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

    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }


            br.close();
            data = sb.toString();

        } catch (Exception e) {
            Log.d("Exception while ", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches all places from GooglePlaces AutoComplete Web Service
    private class PlacesTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... place) {
            // For storing data from web service
            String data = "";

            // Obtain browser key from https://code.google.com/apis/console
            String key = "key=AIzaSyBjFF4jFjoRjun2FzjNagZlrtiKJ4pu3CE";

            String input = "";

            try {
                input = "input=" + URLEncoder.encode(place[0], "utf-8");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            String components = "components=country:in";
            String types="";
            // place type to be searched
            if(placetype.equals("city")) {
                 types = "types=(cities)";
            }
            else {
                if (placetype.equals("state")) {
                    types = "types=geocode";
                }
            }


      //      String types = "types=geocode";

            // Sensor enabled
            String sensor = "sensor=false";

            // Building the parameters to the web service
            String parameters = input + "&" + types + "&" + components + "&" +sensor + "&" + key;

            // Output format
            String output = "json";

            // Building the url to the web service
            String url = "https://maps.googleapis.com/maps/api/place/autocomplete/" + output + "?" + parameters;

            try {
                // Fetching the data from we service
                data = downloadUrl(url);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // Creating ParserTask
            parserTask = new ParserTask();

            // Starting Parsing the JSON string returned by Web Service
            parserTask.execute(result);
        }
    }

    /**
     * A class to parse the Google Places in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String, String>>> {

        JSONObject jObject;

        @Override
        protected List<HashMap<String, String>> doInBackground(String... jsonData) {

            List<HashMap<String, String>> places = null;

            PlaceJSONParser placeJsonParser = new PlaceJSONParser();

            try {
                jObject = new JSONObject(jsonData[0]);

                // Getting the parsed data as a List construct
                places = placeJsonParser.parse(jObject);

            } catch (Exception e) {
                Log.d("Exception", e.toString());
            }
            return places;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> result) {

            String[] from = new String[]{"description"};
            int[] to = new int[]{android.R.id.text1};

            // Creating a SimpleAdapter for the AutoCompleteTextView
            SimpleAdapter adapter = new SimpleAdapter(getActivity(), result, android.R.layout.simple_list_item_1, from, to);

            // Setting the adapter
            et_city.setThreshold(1);

            et_city.setAdapter(adapter);
            et_state.setThreshold(1);

            et_state.setAdapter(adapter);
        }
    }

    public String resizeBase64Image(String base64image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] b= baos.toByteArray();;
        try {
            byte[] encodeByte = Base64.decode(base64image.getBytes(), Base64.DEFAULT);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPurgeable = true;
            options.inSampleSize=4;
            Bitmap image = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length, options);


            if (image.getHeight() <= 400 && image.getWidth() <= 400) {
                return base64image;
            }
            image = Bitmap.createScaledBitmap(image, 300, 100, false);

            image.compress(Bitmap.CompressFormat.PNG, 100, baos);


            System.gc();
        }catch (Exception e) {
            e.printStackTrace();

        }
        return Base64.encodeToString(b, Base64.NO_WRAP);


    }

    public Boolean phone_validation() {
        int len = et_phno.getText().length();
        if (len<10 || len >10) {
            et_phno.setError("Please Enter a 10 digit phone number");
            return false;
        } else
            return true;
    }

    public Boolean spinner_validation()

    {
//        if (spinbusid_pos > 0 && spintrip_pos > 0 && position_genderspinner>0 )
            if ( position_genderspinner>0 ){

            return true;
        } else {
//            if (spinbusid_pos <= 0) {
//                TextView errorText = (TextView) spbus.getSelectedView();
//                errorText.setError("");
//                errorText.setTextColor(Color.RED);//just to highlight that this is an error
//                errorText.setText("Please select a Bus");//changes the selected item text to this
//            }
//            if (spintrip_pos <=0) {
//                TextView errorText = (TextView) sp_trip.getSelectedView();
//                errorText.setError("");
//                errorText.setTextColor(Color.RED);//just to highlight that this is an error
//                errorText.setText("Please select a trip");//changes the selected item text to this
//            }
            if (position_genderspinner<=0) {
                TextView errorText = (TextView) sp_gender.getSelectedView();
                errorText.setError("");
                errorText.setTextColor(Color.RED);//just to highlight that this is an error
                errorText.setText("Please select Gender");//changes the selected item text to this
            }


            return false;
        }


    }

private void selectImage() {
    final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    View view = inflater.inflate(R.layout.customtitle, null);
    builder.setCustomTitle(view);
    builder.setItems(options, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int item) {
            if (options[item].equals("Take Photo")) {

                if (shouldAskPermission()) {
                    askPermissionCamera();
                } else {
                    cameraIntent();
                }
            } else if (options[item].equals("Choose from Gallery")) {
                if (shouldAskPermission()) {
                    askPermissionForWrite();
                } else {
                    galleryIntent();
                }

            } else if (options[item].equals("Cancel")) {
                dialog.dismiss();
            }
        }
    });
    builder.show();
}

    private void galleryIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

    private void cameraIntent() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {

                Bitmap photo = (Bitmap) data.getExtras().get("data");
                iv_propic.setImageBitmap(photo);
                Uri selectedImage = getImageUri(getActivity(), photo);
                final InputStream imageStream;
                try {
                    imageStream = getActivity().getContentResolver().openInputStream(selectedImage);
                    final Bitmap bitmapImage = BitmapFactory.decodeStream(imageStream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getActivity().getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                picturePath = c.getString(columnIndex);
                c.close();


             //   Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                picturename = picturePath.substring(picturePath.lastIndexOf("/") + 1);
           //     Log.w("path of image from Camera.****..", picturePath + "");
                profileImageFile = new File(picturePath);
            } else if (requestCode == 2) {
                try {
                    Uri selectedImage = data.getData();
                   //iv_propic.setImageURI(selectedImage);

                    String[] filePath = {MediaStore.Images.Media.DATA};
                    Cursor c = getActivity().getContentResolver().query(selectedImage, filePath, null, null, null);
                    c.moveToFirst();
                    int columnIndex = c.getColumnIndex(filePath[0]);
                    picturePath = c.getString(columnIndex);
                    c.close();

                    Bitmap bitmap = (BitmapFactory.decodeFile(picturePath));
                    ExifInterface ei = null;
                    try {
                        ei = new ExifInterface(picturePath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                            ExifInterface.ORIENTATION_UNDEFINED);

                    Bitmap rotatedBitmap = null;
                    switch (orientation) {

                        case ExifInterface.ORIENTATION_ROTATE_90:
                            rotatedBitmap = rotateImage(bitmap, 90);
                            break;

                        case ExifInterface.ORIENTATION_ROTATE_180:
                            rotatedBitmap = rotateImage(bitmap, 180);
                            break;

                        case ExifInterface.ORIENTATION_ROTATE_270:
                            rotatedBitmap = rotateImage(bitmap, 270);
                            break;

                        case ExifInterface.ORIENTATION_NORMAL:
                        default:
                            rotatedBitmap = bitmap;
                    }
                    // iv_propic.setImageURI(selectedImage);
                    iv_propic.setImageBitmap(rotatedBitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Bitmap b=null;
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        try {
            b= Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                    matrix, true);


        }catch (OutOfMemoryError e)
        {
            e.printStackTrace();
        }
        return  b;
    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "easyBooking", null);
        return Uri.parse(path);
    }

    private boolean shouldAskPermission() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    private void askPermissionForWrite() {
        String[] perms = {"android.permission.WRITE_EXTERNAL_STORAGE"};
        requestPermissions(perms, ASK_WRITE_PERMISSION);
    }

    @TargetApi(23)
    private void askPermissionCamera() {
        String[] perms = {"android.permission.CAMERA"};
        int permsRequestCode = ASK_CAMERA_PERMISSION;
        requestPermissions(perms, permsRequestCode);
    }

    @TargetApi(23)
    private void askPermissionForWriteCamera() {
        String[] perms = {"android.permission.WRITE_EXTERNAL_STORAGE"};
        requestPermissions(perms, ASK_CAMERA_WRITE_PERMISSION);
    }


    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {
        switch (permsRequestCode) {
            case ASK_WRITE_PERMISSION:
                boolean writeAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (writeAccepted) {
                    galleryIntent();
                } else {
                    Toast.makeText(getActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            case ASK_CAMERA_PERMISSION:
                boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (cameraAccepted) {
                    askPermissionForWriteCamera();
                } else {
                    Toast.makeText(getActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            case ASK_CAMERA_WRITE_PERMISSION:
                boolean cameraWriteAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (cameraWriteAccepted) {
                    cameraIntent();
                } else {
                    Toast.makeText(getActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void loadFragment3(Fragment fragment) {
        try {

            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.frameadd_lay, fragment);
            fragmentTransaction.commit();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}


