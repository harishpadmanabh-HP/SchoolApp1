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
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Add_Teacher;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_unass_div;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_unassigned;
import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

public class AddTeacheFragment extends Fragment  implements Validator.ValidationListener{

    View view;
    LinearLayout llhide,llshow;
   TextView iv;
    String picturePath,encodedString,tr_name,tr_contact,tr_email;
    private static int RESULT_LOAD_IMAGE = 1;
  //  Spinner spclass,spdivision;
    Validator validator;
    final private int ASK_WRITE_PERMISSION = 1001;
    final private int ASK_CAMERA_PERMISSION = 1002;
    final private int ASK_CAMERA_WRITE_PERMISSION = 1003;
    String picturename;
    File profileImageFile;
    private Uri mCropImagedUri;
    private final int CROP_IMAGE = 100;


    ArrayList<String> classname;
    ArrayList<Integer> classid;
    ArrayList<String> divisionname;
    ArrayList<Integer> divisionid;
    ArrayList<String> divisionnameall;
    ArrayList<Integer> divisionidall;
    AlertDialog pd;
    ArrayAdapter<String> dataAdapter_div;

    @Bind(R.id.tr_pro_pic)
    ImageView iv_propic;
    @NotEmpty
    @Bind(R.id.tr_name)
    EditText trname;
    @Bind(R.id.imgback)
    ImageView iv_back;

    @NotEmpty

    @Bind(R.id.tr_contact)
    EditText trcontact;
    @NotEmpty
    @Email
    @Bind(R.id.tr_email)
    EditText tremail;
    Bundle bundle;

    @Bind(R.id.sp_class)
    Spinner spclass;

    @Bind((R.id.sp_division))
    Spinner spdivision;

    @Inject
    Utils utils;
    @Inject
    AppPreferences appPreferences;
    String scid;
    Integer sid,spin_pos=0,spindiv_pos=0;
    Boolean ans,ans1;
    String cid,did;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.addteacher,container,false);
        Home h=(Home)getActivity();
        h.getApp().getActivityComponent().inject(this);
        ButterKnife.bind(this,view);
        validator = new Validator(this);
        validator.setValidationListener(this);
        String[] classno = new String[100];
        classno[0]="Class";
        classname=new ArrayList<String>();
        classid=new ArrayList<Integer>();
        divisionname=new ArrayList<String>();
        divisionid=new ArrayList<Integer>();
        divisionnameall=new ArrayList<String>();
        divisionidall=new ArrayList<Integer>();
        classname.add("Class");
        classid.add(0);
        divisionname.add("Division");
        divisionid.add(0);
        bundle=new Bundle();
        llhide=(LinearLayout)view.findViewById(R.id.tabhidelay);
        llshow=(LinearLayout)view.findViewById(R.id.tabaddlay);

        spclass.setBackgroundResource(R.drawable.edittext_border);
        spdivision.setBackgroundResource(R.drawable.edittext_border);
//        h.tabview1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadFragment3(new Teacher());
//
//            }
//        });



        sid = appPreferences.getInt("scid");
        scid = Integer.toString(sid);
        classlist();

        ArrayAdapter<String> dataAdapter_class = new ArrayAdapter<String>(getActivity(), R.layout.custom_spinner, classname);

        // Drop down layout style - list view with radio button
        dataAdapter_class.setDropDownViewResource(R.layout.custom_dropdown);

        // attaching data adapter to spinner
        try {
            dataAdapter_class.notifyDataSetChanged();
        }catch(Exception e)
        {
            utils.toToast("No class added for the school");
        }
        spclass.setAdapter(dataAdapter_class);
        spclass.setSelection(0, false);


        spclass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                spin_pos= classid.get(position);
                divlist(spin_pos);
                pd.dismiss();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        dataAdapter_div = new ArrayAdapter<String>(getActivity(), R.layout.custom_spinner, divisionname);

        // Drop down layout style - list view with radio button
        dataAdapter_div.setDropDownViewResource(R.layout.custom_dropdown);

        // attaching data adapter to spinner
        try {


            dataAdapter_div.notifyDataSetChanged();
        }catch (Exception e)
        {
            utils.toToast("No division added for the class");
        }
        spdivision.setAdapter(dataAdapter_div);
        spdivision.setSelection(0, false);


        spdivision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spindiv_pos=divisionid.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }
});

        iv=(TextView) view.findViewById(R.id.add_teacher_btn);

        iv_propic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();

            }
        });
iv.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        ans=phone_validation();

        validator.validate();


    }
    });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment3(new Teacher());

            }
        });
        return view;
    }

private void loadFragment3(Teacher fragment) {
    {
        try {
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            // fragmentTransaction.addToBackStack("teacher");
            fragmentTransaction.replace(R.id.frameadd_lay, fragment);

            fragmentTransaction.commit();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

    private  void loadFragment2(Fragment fragment)
    {
        fragment.setArguments(bundle);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        FragmentManager fm=getFragmentManager();


        FragmentTransaction fragmentTransaction=fm.beginTransaction();

        fragmentTransaction.replace(R.id.frameadd_lay,fragment);
        fragmentTransaction.commit();
        pd.dismiss();
    }
    public void classlist()
    {

        Call<Mod_unassigned> call = utils.getApi().unassgdclass(scid);

        call.enqueue(new Callback<Mod_unassigned>() {
            @Override
            public void onResponse(Call<Mod_unassigned> call, Response<Mod_unassigned> response) {
                if (response.isSuccess()) {

                    List<Mod_unassigned.UserDataBean> details = response.body().getUserData();

                    if (details.size() > 0) {
                        int l = details.size();

                        for (int i = 0; i < l; i++) {
                            String c = details.get(i).getClassName().toString();
                           int cid=details.get(i).getClassId();

                            classname.add(c);
                            classid.add(cid);

                        }
                    }

                }
            }


            @Override
            public void onFailure(Call<Mod_unassigned> call, Throwable t) {
                //  utils.toToast("Network failure");
                Toast.makeText(getActivity(), "Network Failure", Toast.LENGTH_SHORT).show();
            }

        });
    }
    public  void divlist(int pos)
    {

        if(!divisionname.isEmpty()) {
            divisionname.clear();
            divisionid.clear();
            divisionname.add("Division");
            divisionid.add(0);
        }
        pd = new SpotsDialog(getActivity());
        pd.show();
        String p=Integer.toString(pos);
        try {

            Call<Mod_unass_div> call = utils.getApi().unassdiv(scid, p);

            call.enqueue(new Callback<Mod_unass_div>() {
                @Override
                public void onResponse(Call<Mod_unass_div> call, Response<Mod_unass_div> response) {
                    if (response.isSuccess()) {

                        List<Mod_unass_div.UserDataBean> details = response.body().getUserData();

                        if (details.size() > 0) {
                            int l = details.size();


                            for (int i = 0; i < l; i++) {
                                String c = details.get(i).getDivision().toString();
                                int cid = details.get(i).getDivisionId();
                                divisionname.add(c);
                                divisionid.add(cid);


                            }
                            dataAdapter_div.notifyDataSetChanged();
                        }

                    }
                }


                @Override
                public void onFailure(Call<Mod_unass_div> call, Throwable t) {
                    //  utils.toToast("Network failure");
                    Toast.makeText(getActivity(), "Network Failure", Toast.LENGTH_SHORT).show();
                }

            });
        }catch (Exception e)
        {
            utils.toToast("Division not set for the corresponding class");
        }
    }

    @Override
    public void onValidationSucceeded() {

        Boolean ans = phone_validation();
     //   Boolean ans1 = spinner_validation();
        if (ans) {


            tr_name = trname.getText().toString();

            tr_contact = trcontact.getText().toString();
            tr_email = tremail.getText().toString();
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
            }
        //    if(spin_pos>0 && spindiv_pos >0) {
                 cid = Integer.toString(spin_pos);
                 did = Integer.toString(spindiv_pos);
          //  }
            //else
              //   cid="No Class";
            //did="No Division";

            sid = appPreferences.getInt("scid");
            scid = Integer.toString(sid);
            pd = new SpotsDialog(getActivity());
            try {
                pd.show();
                Call<Mod_Add_Teacher> call = utils.getApi().addteacher(scid, tr_name, cid, did, tr_contact, tr_email, encodedString, picturePath);

                call.enqueue(new Callback<Mod_Add_Teacher>() {
                    @Override
                    public void onResponse(Call<Mod_Add_Teacher> call, Response<Mod_Add_Teacher> response) {
                        if (response.isSuccess()) {
                            String msg = response.body().getMsg();

                            if (msg.equals("Success")) {
                                String tid = response.body().getUserData();
                                //utils.toToast(response.message());
                                utils.toToast("Teacher added successfully");
                                bundle.putString("tid", tid);

                                loadFragment2(new Fragment_cfm_teacher());

                            } else {
                                if (pd.isShowing()) {
                                    pd.dismiss();
                                }


                                utils.toToast(response.body().getMsg());


                            }
                        } else {
                            if (pd.isShowing()) {
                                pd.dismiss();
                            }


                            utils.toToast(response.body().getMsg());
                        }


                    }

                    @Override
                    public void onFailure(Call<Mod_Add_Teacher> call, Throwable t) {
                        if (pd.isShowing()) {
                            pd.dismiss();
                        }
                        utils.toToast("Failure" + t);


                    }
                });

            }catch(Exception e)
            {
                e.printStackTrace();
                if(pd.isShowing())
                {
                    pd.dismiss();
                }

                Toast.makeText(getActivity(),"Error",Toast.LENGTH_SHORT).show();
            }

        }

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
    public String resizeBase64Image(String base64image){

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


            System.gc();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return Base64.encodeToString(b, Base64.NO_WRAP);

    }
    public Boolean phone_validation()
    {
        int len=trcontact.getText().length();
        if(len<10 || len >10)
        {
            trcontact.setError("Please Enter a 10 digit phone number");
            return false;
        }
        else
            return true;
    }
    public Boolean spinner_validation() {

        if (spin_pos > 0 && spindiv_pos > 0) {
            return true;
        } else {
            if (spin_pos <= 0) {
                TextView errorText = (TextView) spclass.getSelectedView();
                errorText.setError("");
                errorText.setTextColor(Color.RED);//just to highlight that this is an error
                errorText.setText("Please select a class");//changes the selected item text to this
            }
            if (spindiv_pos <= 0) {
                TextView errorText = (TextView) spdivision.getSelectedView();
                errorText.setError("");
                errorText.setTextColor(Color.RED);//just to highlight that this is an error
                errorText.setText("Please select a Division");//changes the selected item text to this
            }
        }
        return false;
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
//        cameraIntent.putExtra("crop", "true");
//        cameraIntent.putExtra("aspectX", 0);
//        cameraIntent.putExtra("aspectY", 0);
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
                // Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                picturename = picturePath.substring(picturePath.lastIndexOf("/") + 1);

                profileImageFile = new File(picturePath);
            } else if (requestCode == 2) {
                try {


                     Uri selectedImage = data.getData();


                    String[] filePath = {MediaStore.Images.Media.DATA};
                    Cursor c = getActivity().getContentResolver().query(selectedImage, filePath, null, null, null);
                    c.moveToFirst();
                    int columnIndex = c.getColumnIndex(filePath[0]);
                    picturePath = c.getString(columnIndex);
                    c.close();
                    Bitmap decoded = (BitmapFactory.decodeFile(picturePath));


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
                            rotatedBitmap = rotateImage(decoded, 90);
                            break;

                        case ExifInterface.ORIENTATION_ROTATE_180:
                            rotatedBitmap = rotateImage(decoded, 180);
                            break;

                        case ExifInterface.ORIENTATION_ROTATE_270:
                            rotatedBitmap = rotateImage(decoded, 270);
                            break;

                        case ExifInterface.ORIENTATION_NORMAL:
                        default:
                            rotatedBitmap = decoded;
                    }

//                    Uri tempUri=getImageUri(getActivity(),rotatedBitmap);
//                    picturePath=getRealPathFromURI(tempUri);
//                    Log.e("path",picturePath);
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

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
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
//    private boolean performCropImage(){
//        try {
//            if(selectedImage!=null){
//                //call the standard crop action intent (the user device may not support it)
//                Intent cropIntent = new Intent("com.android.camera.action.CROP");
//                //indicate image type and Uri
//                cropIntent.setDataAndType(selectedImage, "image/*");
//                //set crop properties
//                cropIntent.putExtra("crop", "true");
//                //indicate aspect of desired crop
//                cropIntent.putExtra("aspectX", 1);
//                cropIntent.putExtra("aspectY", 1);
//                cropIntent.putExtra("scale", true);
//                //indicate output X and Y
//                cropIntent.putExtra("outputX", 500);
//                cropIntent.putExtra("outputY", 500);
//                //retrieve data on return
//                cropIntent.putExtra("return-data", false);
//
//                File f = createNewFile("CROP_");
//                try {
//                    f.createNewFile();
//                } catch (IOException ex) {
//                   // VLLog.e("io", ex.getMessage());
//                }
//
//                mCropImagedUri = Uri.fromFile(f);
//                cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCropImagedUri);
//                //start the activity - we handle returning in onActivityResult
//                startActivityForResult(cropIntent, CROP_IMAGE);
//                return true;
//            }
//        }
//        catch(ActivityNotFoundException anfe){
//            //display an error message
//            String errorMessage = "Whoops - your device doesn't support the crop action!";
//            Toast toast = Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT);
//            toast.show();
//            return false;
//        }
//        return false;
//    }
//
//    private File createNewFile(String prefix){
//        if(prefix==null || "".equalsIgnoreCase(prefix)){
//            prefix="IMG_";
//        }
//        File newDirectory = new File(Environment.getExternalStorageDirectory()+"/mypics/");
//        if(!newDirectory.exists()){
//            if(newDirectory.mkdir()){
//              //  VLLog.d(mContext.getClass().getName(), newDirectory.getAbsolutePath()+" directory created");
//            }
//        }
//        File file = new File(newDirectory,(prefix+System.currentTimeMillis()+".jpg"));
//        if(file.exists()){
//            //this wont be executed
//            file.delete();
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return file;
//    }


}
