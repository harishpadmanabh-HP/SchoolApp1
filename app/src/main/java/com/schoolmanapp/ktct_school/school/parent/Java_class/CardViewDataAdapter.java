package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.utils.AppPreferences;
import com.squareup.picasso.Picasso;

import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * Created by srishtiinnovative on 31/05/17.
 */

public class CardViewDataAdapter extends
        RecyclerView.Adapter<CardViewDataAdapter.ViewHolder> {
    AppPreferences appPreferences;
    Context context;
    AlertDialog dialog;
    private List<model_home> stList;

    public CardViewDataAdapter(Home home, List<model_home> studentList) {
        context = home;
        stList = studentList;
    }


    // Create new views
    @Override
    public CardViewDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.parent_activity_home, null);

        // create ViewHolder
        appPreferences = AppPreferences.getInstance(context, context.getResources().getString(R.string.app_name));
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        dialog = new SpotsDialog(context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        final int pos = position;

        dialog.dismiss();
        viewHolder.tvName.setText(stList.get(position).getName());
        String filepath = stList.get(position).getfile_path();
        String path = "http://schoolman.in//" + filepath;
        if (filepath == null) {
            Picasso.with(context).load(R.drawable.dummy).into(viewHolder.profile);
        } else if (filepath.equals("NULL")) {
            Picasso.with(context).load(R.drawable.dummy).into(viewHolder.profile);
        } else {
            Picasso.with(context).load(path).into(viewHolder.profile);
        }



        viewHolder.name_layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int kid_id = stList.get(pos).getkid_id();
                appPreferences.saveInt("kid_id", kid_id);
             //   Toast.makeText(context, ""+kid_id, Toast.LENGTH_SHORT).show();

                String student_name = stList.get(pos).getName();
                appPreferences.saveData("student_name", student_name);

                appPreferences.saveData("kid_name", student_name);
                String file = stList.get(pos).getfile_path();

                appPreferences.saveData("file_path", file);
                int school_id = stList.get(pos).getschoolid();

                appPreferences.saveInt("school_id", school_id);

                int busid = stList.get(pos).getbus_id();
                appPreferences.saveInt("busid", busid);
                String latitude = stList.get(pos).getlatitude();
                appPreferences.saveData("latitude", latitude);
                Log.e("latitude@@@", latitude);

                String longitude = stList.get(pos).getlongitude();
                appPreferences.saveData("longitude", longitude);
                Log.e("longitude@@@", longitude);
                int class_id = stList.get(pos).getclass_id();

                appPreferences.saveInt("class_id", class_id);


                String student_class_name = stList.get(pos).getid();
                appPreferences.saveData("student_class_name", student_class_name);


                String division = stList.get(pos).getdivision_s();
                appPreferences.saveData("division", division);
                String school_name = stList.get(pos).getschool_name();

                appPreferences.saveData("school_name", school_name);
                String teacher_name = stList.get(pos).getteacher_name();
                appPreferences.saveData("teacher_name", teacher_name);

                String CONTACT_TEACHER = stList.get(pos).getCONTACT_TEACHER();
                appPreferences.saveData("CONTACT_TEACHER", CONTACT_TEACHER);

                String driver_name = stList.get(pos).getdriver_name();
                appPreferences.saveData("driver_name", driver_name);

                String driver_number = stList.get(pos).getdriver_number();
                appPreferences.saveData("driver_number", driver_number);

                dialog.show();
                Intent intent = new Intent(context, student_activity.class);
                context.startActivity(intent);
                dialog.dismiss();
            }
        });
        viewHolder.mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int kid_id = stList.get(pos).getkid_id();
                appPreferences.saveInt("kid_idformark", kid_id);
               // Toast.makeText(context, ""+kid_id, Toast.LENGTH_SHORT).show();

                Intent mark=new Intent(context,SelectExamParent.class);
                context.startActivity(mark);
            }
        });
        viewHolder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int kid_id = stList.get(pos).getkid_id();
                int school_id = stList.get(pos).getschoolid();

                appPreferences.saveInt("school_id", school_id);


                int class_id = stList.get(pos).getclass_id();

                appPreferences.saveInt("class_id", class_id);
                appPreferences.saveInt("kid_id", kid_id);
                appPreferences.saveData("kid_name", stList.get(pos).getName());
                appPreferences.saveData("kid_path", stList.get(pos).getfile_path());
                dialog.show();
                Intent i = new Intent(context, Activity_Message.class);
                i.putExtra("kididsss", stList.get(pos).getkid_id());
                i.putExtra("file", stList.get(pos).getfile_path());
                i.putExtra("name", stList.get(pos).getName());
                context.startActivity(i);
                dialog.dismiss();
            }
        });
        viewHolder.fees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int kid_id = stList.get(pos).getkid_id();
                int school_id = stList.get(pos).getschoolid();

                appPreferences.saveInt("school_id", school_id);


                int class_id = stList.get(pos).getclass_id();
                String student_class_name = stList.get(pos).getid();
                appPreferences.saveData("student_class_name", student_class_name);
                String student_name = stList.get(pos).getName();
                appPreferences.saveData("student_name", student_name);

                appPreferences.saveInt("class_id", class_id);
                appPreferences.saveInt("kid_id", kid_id);
                dialog.show();
                Intent i = new Intent(context, activity_fees.class);
                context.startActivity(i);
                dialog.dismiss();
            }
        });

        viewHolder.attendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                int kid_id = stList.get(pos).getkid_id();
                appPreferences.saveInt("kid_id", kid_id);
                Intent i = new Intent(context, attendence_activity.class);
                context.startActivity(i);
                dialog.dismiss();
            }
        });

        viewHolder.tracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                int kid_id = stList.get(pos).getkid_id();
                appPreferences.saveInt("kid_id", kid_id);
                String latitude = stList.get(pos).getlatitude();
                appPreferences.saveData("latitude", latitude + "");
                Log.e("latitude@@@", latitude);
                String longitude = stList.get(pos).getlongitude();
                appPreferences.saveData("longitude", longitude + "");
                int busid = stList.get(pos).getbus_id();
                appPreferences.saveInt("busid", busid);
                Log.e("busid", busid+"");
                Log.e("longitude@@@", longitude);
                String driver_name = stList.get(pos).getdriver_name();
                appPreferences.saveData("driver_name", driver_name);

                String driver_number = stList.get(pos).getdriver_number();
                appPreferences.saveData("driver_number", driver_number);
if(busid==1){
    dialog.dismiss();
    Toast.makeText(context, "Your child not assign any bus", Toast.LENGTH_SHORT).show();
}else {
    Intent i = new Intent(context, MapsActivity.class);
    context.startActivity(i);
    dialog.dismiss();
}
            }
        });


    }

    // Return the size arraylist
    @Override
    public int getItemCount() {
        return stList.size();
    }

    // method to access in activity after updating selection
    public List<model_home> getStudentist() {
        return stList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvEmailId;
        public LinearLayout name_layout;
        public ImageView tracking;
        public ImageView profile;
        public ImageView fees;
        public model_home singlestudent;

        public ImageView message;
        public ImageView attendence;
        public ImageView mark;



        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            tvName = (TextView) itemLayoutView.findViewById(R.id.tvName);
            message = (ImageView) itemLayoutView
                    .findViewById(R.id.message);
            attendence = (ImageView) itemLayoutView
                    .findViewById(R.id.attendence);
            tracking = (ImageView) itemLayoutView
                    .findViewById(R.id.tracking);
            profile = (ImageView) itemLayoutView
                    .findViewById(R.id.profile_image);
            fees = (ImageView) itemLayoutView
                    .findViewById(R.id.fees);
            mark=(ImageView)itemLayoutView.findViewById(R.id.marks);
            name_layout = (LinearLayout) itemLayoutView.findViewById(R.id.name_layout);


        }

    }

}
