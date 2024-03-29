package com.schoolmanapp.ktct_school.school.teacher.java_class;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.ContentUris;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.database.Cursor;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.net.Uri;
        import android.os.Build;
        import android.os.Bundle;
        import android.os.Environment;
        import android.provider.DocumentsContract;
        import android.provider.MediaStore;
        import android.support.annotation.Nullable;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.WindowManager;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.gson.Gson;
        import com.schoolmanapp.ktct_school.school.teacher.Api_client.Api_client;
        import com.schoolmanapp.ktct_school.school.teacher.Api_interface.API_interface;
        import com.schoolmanapp.ktct_school.school.R;
        import com.schoolmanapp.ktct_school.school.teacher.model_class.msgtoall_response_model;
        import com.schoolmanapp.ktct_school.school.teacher.model_class.multiple_message_model;
        import com.schoolmanapp.ktct_school.school.teacher.model_class.single_message_model;
        import com.schoolmanapp.ktct_school.school.teacher.model_class.student_list_model;
        import com.schoolmanapp.ktct_school.school.teacher.utils.AppPreferences;
        import com.mobsandgeeks.saripaar.ValidationError;
        import com.mobsandgeeks.saripaar.Validator;
        import com.mobsandgeeks.saripaar.annotation.NotEmpty;

        import java.io.ByteArrayOutputStream;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.InputStream;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;
        import java.util.Random;

        import butterknife.Bind;
        import butterknife.ButterKnife;
        import dmax.dialog.SpotsDialog;
        import okhttp3.MediaType;
        import okhttp3.RequestBody;
        import retrofit2.Call;
        import retrofit2.Response;

/**
 * Created by srishtiinnovative on 06/06/17.
 */

public class student_messagetoall extends Activity implements Validator.ValidationListener {
    @Bind(R.id.send_to_all)
    TextView send;
    @NotEmpty
    @Bind(R.id.message_to_all_content)
    EditText et_content;
    @NotEmpty
    @Bind(R.id.message_to_all_subject)
    EditText et_subject;

    @Bind(R.id.attach)
    ImageView attach;

    String contents, json;


    int class_id, division_id;
    ArrayList studentid;

    AppPreferences appPreferences;
    Validator validator;
    RequestBody AccessTokenValue = null;

    String kid, file_path, message, subject;
    int student_id, teacher_id;
    AlertDialog dialog;
    String MessageType = "ForClass";
    String FilePath = null;
    String displayName = null;
    private static final int FILE_SELECT_CODE = 1;
    ArrayList rollno;

    @Bind(R.id.attachment_text)
    TextView attachment_text;

    final private int ASK_WRITE_PERMISSION = 1001;
    final private int ASK_CAMERA_PERMISSION = 1002;
    final private int ASK_CAMERA_WRITE_PERMISSION = 1003;
    String picturePath,picturename;
    private multiple_message_model message_model;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_messagetoall);
        dialog = new SpotsDialog(this);

        ButterKnife.bind(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        validator = new Validator(this);
        validator.setValidationListener(this);

        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        class_id = appPreferences.getInt("class_id");

        teacher_id = appPreferences.getInt("teacher_id");

        rollno = getIntent().getStringArrayListExtra("id");
        Log.e("size",rollno.size()+"");
//        for(int i=0;i<rollno.size();i++){
//            Log.e("st-id",rollno.get(i)+"");
//        }

        dialog.dismiss();

        attach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();


            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), message_activity.class);
        startActivity(intent);
    }

    @Override
    public void onValidationSucceeded() {
        dialog.show();
        subject = et_subject.getText().toString();
        message = et_content.getText().toString();
        message_model=new multiple_message_model();
        message_model.setTeacherId(String.valueOf(teacher_id));
        message_model.setSubject(subject);
        message_model.setDescription(message);
        ArrayList<multiple_message_model.StudentListBean> studentListBeans= new ArrayList<>();
        multiple_message_model.StudentListBean studnt;
        for(int i=0;i<rollno.size();i++){
          studnt=new multiple_message_model.StudentListBean();
          studnt.setStudentId(rollno.get(i).toString());
          studentListBeans.add(studnt);
        }
        message_model.setStudentList(studentListBeans);
        try {
            Gson gson = new Gson();
            json = gson.toJson(message_model).trim();
            System.out.println("FinalData................\n" + json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            AccessTokenValue = RequestBody.create(MediaType.parse("application/json"), json.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.e("teacher_id", teacher_id + "");
        Log.e("classid", class_id + "");
        Log.e("subject", subject + "");
        Log.e("message", message + "");
        Log.e("MessageType", MessageType + "");
        Log.e("displayName", displayName + "");

        File f = null;
        try {
            f = new File(FilePath);

            Log.e("picturePath", FilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            f.createNewFile();
            Log.e("picturePath", "file :" + f);
        } catch (Exception e) {
            e.printStackTrace();
        }


/// Parsing any Media type file    RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
//        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
//        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), FilePath);
//        MultipartBody.Part multipartBody =MultipartBody.Part.createFormData("file",f.getName(),requestFile);

        //////////////////////////////////////


        Map<String, RequestBody> map = new HashMap<>();
        Random r = new Random();
        int i1 = r.nextInt(999999999);
//        if (f1 != null) {
//            RequestBody file = RequestBody.create(MediaType.parse("image/*"), f1 );
//            map.put("PostFile\"; filename=\""+f1.getName(), file);
//        } else {
//            RequestBody file = RequestBody.create(MediaType.parse("text/plain"), "");
//            map.put("PostFile", file);
//        }

        RequestBody TeacherId = RequestBody.create(MediaType.parse("text/plain"), teacher_id + "");
        map.put("TeacherId", TeacherId);
        RequestBody ToSentId = RequestBody.create(MediaType.parse("text/plain"), class_id + "");
        map.put("ToSentId", ToSentId);
        RequestBody subjectR = RequestBody.create(MediaType.parse("text/plain"), subject);
        map.put("Subject", subjectR);
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), message);
        map.put("Descritpion", description);
        RequestBody MessageTypeR = RequestBody.create(MediaType.parse("text/plain"), MessageType);
        map.put("MessageType", MessageTypeR);
        Log.e("file", f1 + "");

        if ((f1 + "").equals("null")) {

        } else {
            //  String key = String.format("%1$s\"; filename=\"%1$s", "photo_" + String.valueOf(pos + 1));
            RequestBody file = RequestBody.create(MediaType.parse("image/*"), f1);
            map.put("PostFile\"; filename=\"" + f1.getName(), file);

        }

        final API_interface api = Api_client.getClient().create(API_interface.class);
        Call<msgtoall_response_model> call = api.message_to_allCall(AccessTokenValue);
        call.enqueue(new retrofit2.Callback<msgtoall_response_model>() {

            @Override
            public void onResponse(Call<msgtoall_response_model> call, Response<msgtoall_response_model> response) {
                if (response.isSuccess()) {
                    //  List<login_model.UserDataBean> details = response.body().getUserData();
                    if (response.body().getMsg().equals("Success")) {
                        // Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Message sent successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), message_activity.class);
                        startActivity(intent);
                        Log.e("response :", response.body().getMsg());
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                } else {

                }
            }


            @Override
            public void onFailure(Call<msgtoall_response_model> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "check network connection", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

        });
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



    File myFile;
    File f1;



    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }


    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
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
                //iv_propic.setImageBitmap(photo);
                Uri selectedImage = getImageUri(this, photo);
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
                attachment_text.setText(picturename);
                //     Log.w("path of image from Camera.****..", picturePath + "");
                // f1 = new File(picturePath);
                File f = new File(picturePath);
                f1=saveBitmapToFile(f);
            } else if (requestCode == 2) {
                Uri selectedImage = data.getData();
                // iv_propic.setImageURI(selectedImage);
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
                attachment_text.setText(picturename);
                //  Log.w("path of image from gallery.****..", picturePath + "");
                //f1 = new File(picturePath);
                File f = new File(picturePath);
                f1=saveBitmapToFile(f);
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
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            case ASK_CAMERA_PERMISSION:
                boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (cameraAccepted) {
                    askPermissionForWriteCamera();
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            case ASK_CAMERA_WRITE_PERMISSION:
                boolean cameraWriteAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (cameraWriteAccepted) {
                    cameraIntent();
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public File saveBitmapToFile(File file){
        try {

            // BitmapFactory options to downsize the image
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            o.inSampleSize = 6;
            // factor of downsizing the image

            FileInputStream inputStream = new FileInputStream(file);
            //Bitmap selectedBitmap = null;
            BitmapFactory.decodeStream(inputStream, null, o);
            inputStream.close();

            // The new size we want to scale to
            final int REQUIRED_SIZE=75;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while(o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            inputStream = new FileInputStream(file);

            Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2);
            inputStream.close();

            // here i override the original image file
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);

            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 100 , outputStream);

            return file;
        } catch (Exception e) {
            return null;
        }
    }
}
