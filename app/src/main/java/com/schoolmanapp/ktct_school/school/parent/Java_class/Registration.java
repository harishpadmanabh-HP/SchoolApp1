package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.parent.API_interface.API_interface;
import com.schoolmanapp.ktct_school.school.parent.Api_client.Api_client;
import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.model_class.register_model;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by srishtiinnovative on 29/05/17.
 */

public class Registration extends Activity implements Validator.ValidationListener {
    @Bind(R.id.signup)
    TextView iv_signup;
    @Bind(R.id.img_reg_close)
    ImageView iv_close;
    @Bind(R.id.add_image)
    ImageView add_image;
    @Bind(R.id.txt_login)
    TextView please_login;

    @NotEmpty
    @Bind(R.id.et_name)
    EditText et_name;

    @NotEmpty
    @Bind(R.id.et_addr)
    EditText et_addr;

    @NotEmpty
    @Bind(R.id.et_state)
    EditText et_state;
    @NotEmpty
    @Bind(R.id.et_city)
    EditText et_city;


    @NotEmpty
    @Email
    @Bind(R.id.et_email)
    EditText et_email;

    @NotEmpty
    //@Max(value = 10)
    @Bind(R.id.et_phno)
    EditText et_phno;

    @NotEmpty
    @Password(min = 6)
    @Bind(R.id.et_pswd)
    EditText et_pswd;

    @NotEmpty
    @ConfirmPassword
    @Bind(R.id.et_cfm_pswd)
    EditText et_cfm_pswd;

    final private int ASK_WRITE_PERMISSION = 1001;
    final private int ASK_CAMERA_PERMISSION = 1002;
    final private int ASK_CAMERA_WRITE_PERMISSION = 1003;
    String picturename;
    private static int RESULT_LOAD_IMAGE = 1;
    private static  int MY_REQUEST_CODE=1;
    String picturePath="", encodedString="", parent_name, address, city, state, email, contact, psword;
    File profileImageFile;
    Validator validator;
    String reduced_size_image="";
    AlertDialog dialog;
    private static final int PERMISSION_CALLBACK_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;
    String[] permissionsRequired = new String[]{Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    private SharedPreferences permissionStatus;
    private boolean sentToSettings = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
        dialog = new SpotsDialog(this);



        iv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();


//                Intent i = new Intent(getApplicationContext(), Home.class);
//                startActivity(i);


            }
        });
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);

            }
        });
        please_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);

            }
        });
        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                selectImage();
            }
        });
    }

    @Override
    public void onValidationSucceeded() {

        dialog.show();
        parent_name = et_name.getText().toString();
        address = et_addr.getText().toString();
        state = et_state.getText().toString();
        city = et_city.getText().toString();
        email = et_email.getText().toString();
        contact = et_phno.getText().toString();
        psword = et_pswd.getText().toString();
        Boolean res=phone_validation();
if(res) {

    File f = null;
    try {
        f = new File(picturePath);

        Log.e("picturePath", picturePath);
    } catch (Exception e) {
        e.printStackTrace();
    }
    try {
        f.createNewFile();
        Log.e("picturePath", "file :" + f);
    } catch (Exception e) {
        e.printStackTrace();
    }
    InputStream inputStream = null;//You can get an inputStream using any IO API
    try {
        inputStream = new FileInputStream(f);
    } catch (Exception e) {
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
    } catch (Exception e) {
        e.printStackTrace();
    }
    try {
        bytes = output.toByteArray();
        encodedString = Base64.encodeToString(bytes, Base64.DEFAULT);
        reduced_size_image = resizeBase64Image(encodedString);
    } catch (Exception e) {
        e.printStackTrace();
        encodedString = "";
        picturePath = "";
    }
    Log.e("parent_name : ", parent_name);
    Log.e("address : ", address);
    Log.e("state : ", state);
    Log.e("city : ", city);
    Log.e("email : ", email);
    Log.e("contact : ", contact);
    Log.e("psword : ", psword);
    Log.e("encodedString : ", reduced_size_image);
//        Log.e("picturePath : ", picturePath);

    final API_interface api = Api_client.getClient().create(API_interface.class);
    Call<register_model> call = api.registerModelCall(parent_name, address, city, email, contact, psword, state, reduced_size_image, picturePath);
    // Call<Mod_school_reg> call = getApi().school_registration(map);
    call.enqueue(new Callback<register_model>() {
        @Override
        public void onResponse(Call<register_model> call, Response<register_model> response) {
            if (response.isSuccess()) {
                Log.d("Success", response.message());
                Toast.makeText(getApplicationContext(), response.body().getMsg() + "", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Registration.this, Login.class);
                startActivity(i);
                dialog.dismiss();
                // d.cancel();
            } else {
                Toast.makeText(getApplicationContext(), response.body().getMsg() + "", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }


        }

        @Override
        public void onFailure(Call<register_model> call, Throwable t) {
            Toast.makeText(getApplicationContext(), "failure" + "", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            // Log.d("failure", "failure" + t);
        }
    });

}
        dialog.dismiss();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
            for (ValidationError error : errors) {
                View view = error.getView();
                String message = error.getCollatedErrorMessage(this);
                // Display error messages ;)
                if (view instanceof EditText) {
                    ((EditText) view).setError(message);
                } else {
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                }
            }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Registration.this, Login.class);
        startActivity(intent);
    }

//

    public String resizeBase64Image(String base64image){
        byte [] encodeByte=Base64.decode(base64image.getBytes(),Base64.DEFAULT);
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inPurgeable = true;
        Bitmap image = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length,options);


        if(image.getHeight() <= 400 && image.getWidth() <= 400){
            return base64image;
        }
        image = Bitmap.createScaledBitmap(image, 300, 100, false);

        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG,100, baos);

        byte [] b=baos.toByteArray();
        System.gc();
        return Base64.encodeToString(b, Base64.NO_WRAP);

    }





    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
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
                add_image.setImageBitmap(photo);
                Uri selectedImage = getImageUri(getApplicationContext(), photo);
                final InputStream imageStream;
                try {
                    imageStream = getContentResolver().openInputStream(selectedImage);
                    final Bitmap bitmapImage = BitmapFactory.decodeStream(imageStream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                picturename = picturePath.substring(picturePath.lastIndexOf("/") + 1);
                //     Log.w("path of image from Camera.****..", picturePath + "");
                profileImageFile = new File(picturePath);
            } else if (requestCode == 2) {
                Uri selectedImage = data.getData();
                add_image.setImageURI(selectedImage);
                final InputStream imageStream;
                try {
                    imageStream = getContentResolver().openInputStream(selectedImage);
                    final Bitmap bitmapImage = BitmapFactory.decodeStream(imageStream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                picturename = picturePath.substring(picturePath.lastIndexOf("/") + 1);
                //  Log.w("path of image from gallery.****..", picturePath + "");
                profileImageFile = new File(picturePath);
            }
        }
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
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            case ASK_CAMERA_PERMISSION:
                boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (cameraAccepted) {
                    askPermissionForWriteCamera();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            case ASK_CAMERA_WRITE_PERMISSION:
                boolean cameraWriteAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (cameraWriteAccepted) {
                    cameraIntent();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    public Boolean phone_validation()
    {
        int len=et_phno.getText().length();
        if(len<10 || len >10)
        {
            et_phno.setError("Please Enter a 10 digit phone number");
            return false;
        }
        else
            return true;
    }

}
