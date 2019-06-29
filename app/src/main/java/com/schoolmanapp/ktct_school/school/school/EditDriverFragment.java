package com.schoolmanapp.ktct_school.school.school;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Add_Driver;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_edit_driver;
import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;
import com.squareup.picasso.Picasso;

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
import java.util.HashMap;
import java.util.List;

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

public class EditDriverFragment extends Fragment implements  Validator.ValidationListener {
    View view;
    LinearLayout llhide, llshow;
    TextView iv;
    Bundle bundle;
    Validator validator;
    AlertDialog pd;
    String dr_name, dr_phno, dr_addr, dr_city, dr_state, dr_lcno;
    String encodedString, picturePath;
    String pic_path;
    final private int ASK_WRITE_PERMISSION = 1001;
    final private int ASK_CAMERA_PERMISSION = 1002;
    final private int ASK_CAMERA_WRITE_PERMISSION = 1003;
    String picturename;
    File profileImageFile;
    @NotEmpty
    @Bind(R.id.drname)
    EditText et_name;
    @NotEmpty
    @Bind(R.id.draddr)
    EditText et_addr;
    @NotEmpty
    @Bind(R.id.drlcno)
    EditText et_lcno;
    @NotEmpty
    @Bind(R.id.drcity)
    AutoCompleteTextView et_city;
    @NotEmpty
    @Bind(R.id.drstate)
    AutoCompleteTextView et_state;
    @NotEmpty

    @Bind(R.id.drphno)
    EditText et_phno;
    @Bind(R.id.imgback)
    ImageView iv_back;
    @Bind(R.id.dr_proimg)
    ImageView iv_prof;
    PlacesTask placesTask;
    ParserTask parserTask;
    int sid;
    @Inject
    Utils utils;
    @Inject
    AppPreferences appPreferences;
    String scid,placetype,photo;
    Integer drid;
    Integer flag;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.editdriver, container, false);
        Home h = (Home) getActivity();
        flag=0;
        h.getApp().getActivityComponent().inject(this);
        ButterKnife.bind(this, view);
        bundle = getArguments();
        encodedString="";
        if (bundle != null) {
            dr_name =bundle.getString("Name");
            dr_addr =bundle.getString("Address");
            dr_lcno=bundle.getString("Licenceno");
            dr_city =bundle.getString("City");
            drid =bundle.getInt("drid");
            placetype=bundle.getString("State");
            dr_phno =bundle.getString("Contactno");
            photo=bundle.getString("photo");
//            Log.e("fetchedpath",picturePath);
                           }
                et_name.setText(dr_name);
                et_addr.setText(dr_addr);
                et_lcno.setText(dr_lcno);
                et_city.setText(dr_city);
                et_phno.setText(dr_phno);
                et_state.setText(placetype);


        validator = new Validator(this);
        validator.setValidationListener(this);
        llhide = (LinearLayout) view.findViewById(R.id.tabhidelay);
        llshow = (LinearLayout) view.findViewById(R.id.tabaddlay);
        llshow.setVisibility(View.VISIBLE);
//        h.tabview4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadFragment3(new Driver());
//
//            }
//        });
        if (photo == null) {
            Picasso.with(h).load(R.drawable.dummy).into(iv_prof);
        } else {
            String path = "http://www.schoolman.in//" + photo;
            Picasso.with(h).load(path).into(iv_prof);
        }
        iv_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            try {
                selectImage();
            }catch (Exception e)
            {
                e.printStackTrace();
            }

            }
        });
        iv = (TextView) view.findViewById(R.id.img_adddriverbtn);
        iv.setText("SAVE");
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Boolean ans = phone_validation();
                validator.validate();



            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment3(new Driver());
            }
        });

        et_city.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
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
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        et_state.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
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





        return view;
    }
    private void loadFragment3(Fragment fragment) {
    //    fragment.setArguments(bundle);
        try {
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            FragmentManager fm = getFragmentManager();


            FragmentTransaction fragmentTransaction = fm.beginTransaction();

            fragmentTransaction.replace(R.id.frameadd_lay, fragment);
            fragmentTransaction.commit();
        }catch(Exception e)
        {e.printStackTrace();}
       // pd.dismiss();
    }

    private void loadFragment2(Driver fragment) {
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


    public void editdriver() {
        dr_name = et_name.getText().toString();
        dr_phno = et_phno.getText().toString();
        dr_addr = et_addr.getText().toString();
        dr_city = et_city.getText().toString();
        dr_state = et_state.getText().toString();
        dr_lcno = et_lcno.getText().toString();
        dr_addr = et_addr.getText().toString();

      //  utils.toToast(picturePath);
        //if(photo!)
        if(flag==0) {
            encodedString = "";
        }
        else {
            if (picturePath == null) {
                Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(), R.drawable.dummy);
                ByteArrayOutputStream bao = new ByteArrayOutputStream();
                bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 100, bao);
                byte[] ba = bao.toByteArray();
                encodedString = Base64.encodeToString(ba, Base64.DEFAULT);


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
                Log.e("eccoded", encodedString);
            }
        }

        sid = appPreferences.getInt("scid");
        scid = Integer.toString(sid);
        pd = new SpotsDialog(getActivity());
        try {
            pd.show();
//

            Call<Mod_edit_driver> call = utils.getApi().editdriver(drid, dr_name, dr_lcno, dr_phno, dr_addr, "",encodedString, dr_city, dr_state);


            call.enqueue(new Callback<Mod_edit_driver>() {
                @Override
                public void onResponse(Call<Mod_edit_driver> call, Response<Mod_edit_driver> response) {
                    if (response.isSuccess()) {


                        String msg = response.body().getMsg();
                        if ((msg.equals("Success")) ||msg.equals("No changes !")) {
//                        utils.toToast(response.message());
                            if (pd.isShowing())
                                pd.dismiss();

                                        utils.toToast(response.body().getMsg());

                            String did = response.body().getUserData().getDriverId();
                            bundle.putString("did", did);
                            loadFragment2(new Driver());

                        }
                        else {
                            if (pd.isShowing()) {
                                pd.dismiss();

                                // utils.toToast("Adding driver failed");
                                utils.toToast(response.body().getMsg());
                            }

                        }

                    } else {
                        if (pd.isShowing()) {
                            pd.dismiss();

                            //  utils.toToast("Adding driver failed");
                            utils.toToast(response.body().getMsg());
                        }


                    }
                }


                @Override
                public void onFailure(Call<Mod_edit_driver> call, Throwable t) {
                    if (pd.isShowing()) {
                        pd.dismiss();
                    }
                    utils.toToast("Failure" + t);
                    // Log.d("failure", "failure" + t);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception",e+"");
            if (pd.isShowing()) {
                pd.dismiss();
            }

            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void onValidationSucceeded() {
        Boolean ans = phone_validation();
        if (ans)
            editdriver();

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
    public class PlacesTask extends AsyncTask<String, Void, String> {

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
            // Sensor enabled
            String sensor = "sensor=false";

            // Building the parameters to the web service
            String parameters = input + "&" + types +  "&" + components+ "&" +  sensor + "&" + key;

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
    public class ParserTask extends AsyncTask<String, Integer, List<HashMap<String, String>>> {

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

        byte[] b= baos.toByteArray();
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

             baos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 100, baos);

            b = baos.toByteArray();
            System.gc();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return Base64.encodeToString(b, Base64.NO_WRAP);

    }

    public Boolean phone_validation() {
        int len = et_phno.getText().length();
        if (len < 10 || len > 10) {
            et_phno.setError("Please Enter a 10 digit phone number");
            return false;
        } else
            return true;
    }

    private void selectImage() {
        try {
            flag=1;
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
                        flag=0;
                        dialog.dismiss();
                    }
                }
            });
            builder.show();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void galleryIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

    private void cameraIntent() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {

                Bitmap photo = (Bitmap) data.getExtras().get("data");
                iv_prof.setImageBitmap(photo);
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
            //    Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                picturename = picturePath.substring(picturePath.lastIndexOf("/") + 1);
                //     Log.w("path of image from Camera.****..", picturePath + "");
                profileImageFile = new File(picturePath);
            } else if (requestCode == 2) {
                try{

                Uri selectedImage = data.getData();
            //    iv_prof.setImageURI(selectedImage);

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
                switch(orientation) {

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
                iv_prof.setImageBitmap(rotatedBitmap);

            }catch (Exception e)
                {
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
}

