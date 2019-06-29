package com.schoolmanapp.ktct_school.school.teacher.java_class;

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

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.teacher.utils.AppPreferences;

import java.util.List;

/**
 * Created by srishtiinnovative on 31/05/17.
 */

public class CardViewDataAdapter extends
        RecyclerView.Adapter<CardViewDataAdapter.ViewHolder> {
    AppPreferences appPreferences;
    private List<model_message> stList;
    private List<model_message> image_list;

    Context context;

//    public CardViewDataAdapter(List<model_message> students) {
//        this.stList = students;
//
//    }

    public CardViewDataAdapter(message_activity message_activity, List<model_message> studentList) {
        stList = studentList;

        context=message_activity;
        Log.e("hello","hai"+stList);

    }

    public CardViewDataAdapter(message_activity message_activity, List<model_message> studentList, List<model_message> file_path) {

    }

    // Create new views
    @Override
    public CardViewDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.activity_custom_message, null);
         appPreferences = AppPreferences.getInstance(context, context.getResources().getString(R.string.app_name));
        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        final int pos = position;

        viewHolder.tvName.setText(stList.get(position).getName());


//        viewHolder.message_to_all.setChecked(stList.get(position).isSelected());
//
//        viewHolder.message_to_all.setTag(stList.get(position));


        viewHolder.piclayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                CheckBox cb = (CheckBox) v;
//                model_message contact = (model_message) cb.getTag();
//
//                contact.setSelected(cb.isChecked());
//                stList.get(pos).setSelected(cb.isChecked());

//                Toast.makeText(
//                        v.getContext(),
//                        "Clicked on Checkbox: " + cb.getText() + " is "
//                                + cb.isChecked(), Toast.LENGTH_LONG).show();
                String student_name=stList.get(pos).getName();
                appPreferences.saveData("student_name",student_name);
                appPreferences.saveData("kid_name",student_name);
                String file=stList.get(pos).getFile_path();
                Log.e("filepath@@@@",file+"");
                appPreferences.saveData("file_path",file);
                int student_id=stList.get(pos).getId();
                appPreferences.saveInt("student_id",student_id);
                appPreferences.saveData("divisionid",stList.get(pos).getdiv());
                Intent intent= new Intent(context,teacher_message.class);
                context.startActivity(intent);

            }
        });

        viewHolder.tvNamelayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                CheckBox cb = (CheckBox) v;
//                model_message contact = (model_message) cb.getTag();
//
//                contact.setSelected(cb.isChecked());
//                stList.get(pos).setSelected(cb.isChecked());

//                Toast.makeText(
//                        v.getContext(),
//                        "Clicked on Checkbox: " + cb.getText() + " is "
//                                + cb.isChecked(), Toast.LENGTH_LONG).show();
                String student_name=stList.get(pos).getName();
                appPreferences.saveData("student_name",student_name);
                String file=stList.get(pos).getFile_path();
                Log.e("filepath@@@@",file+"");
                appPreferences.saveData("file_path",file);
                int student_id=stList.get(pos).getId();
                appPreferences.saveInt("student_id",student_id);
                appPreferences.saveData("divisionid",stList.get(pos).getdiv());
                Intent intent= new Intent(context,student_message.class);
                context.startActivity(intent);

            }
        });

    }

    // Return the size arraylist
    @Override
    public int getItemCount() {
        return stList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView tvName;

        public View message;
        public LinearLayout tvNamelayout;
        public LinearLayout piclayout;

        public model_message singlestudent;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            tvName= (TextView) itemLayoutView.findViewById(R.id.tvName);
            message = itemLayoutView.findViewById(R.id.chkSelected);
            tvNamelayout= (LinearLayout)itemLayoutView.findViewById(R.id.tvNamelayout);
            piclayout= (LinearLayout)itemLayoutView.findViewById(R.id.piclayout);
        }

    }

    // method to access in activity after updating selection
    public List<model_message> getStudentist() {
        return stList;
    }

}
