package com.schoolmanapp.ktct_school.school.teacher.java_class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.teacher.model_class.student_list_model;

import java.util.List;

/**
 * Created by Lijo Mathew Theckanal on 04-Aug-16.
 */
public class StudentAttendenceGridAdapter extends ArrayAdapter<student_list_model.UserDataBean> {

    /**
     * ViewHolder class for layout.<br />
     * <br />
     * Auto-created on 2015-08-13 11:31:31 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private static class ViewHolder {
        public final LinearLayout cv;
        public final LinearLayout mainLayout;
        public final TextView tvId;
        public final TextView tvName;
        public final ImageView ab;

        private ViewHolder(LinearLayout cv, LinearLayout mainLayout, TextView tvId, TextView tvName, ImageView ab) {
            this.cv = cv;
            this.mainLayout = mainLayout;
            this.tvId = tvId;
            this.tvName = tvName;
            this.ab=ab;
        }

        public static ViewHolder create(LinearLayout cv) {
            LinearLayout mainLayout = (LinearLayout) cv.findViewById(R.id.grid_bg);
            TextView tvId = (TextView) cv.findViewById(R.id.tvId);
            TextView tvName = (TextView) cv.findViewById(R.id.tvName);
            ImageView ab=(ImageView)cv.findViewById(R.id.img_ab);
            return new ViewHolder(cv, mainLayout, tvId, tvName,ab);
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.custom_student, parent, false);
            vh = ViewHolder.create((LinearLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        final student_list_model.UserDataBean item = getItem(position);

        vh.tvId.setText(item.getStudentId() + "");
        vh.tvName.setText(item.getStundentName() + "");
        if (student_attendence.present.contains(item.getStudentId() + "")) {
            vh.ab.setVisibility(View.INVISIBLE);
            vh.mainLayout.setBackgroundResource(R.drawable.shape_selected);
        } else {
            vh.ab.setVisibility(View.VISIBLE);
            vh.mainLayout.setBackgroundResource(R.drawable.shape_attendence);
        }
        vh.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (student_attendence.present.contains(item.getStudentId() + "")) {
                    student_attendence.present.remove(item.getStudentId() + "");
                    vh.ab.setVisibility(View.VISIBLE);
                    vh.mainLayout.setBackgroundResource(R.drawable.shape_attendence);
                } else {
                    student_attendence.present.add(item.getStudentId() + "");
                    vh.ab.setVisibility(View.INVISIBLE);
                    vh.mainLayout.setBackgroundResource(R.drawable.shape_selected);
                }
            }
        });

        return vh.cv;
    }


    private LayoutInflater mInflater;
    private Context context;

    // Constructors
    public StudentAttendenceGridAdapter(Context context, List<student_list_model.UserDataBean> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public StudentAttendenceGridAdapter(Context context, student_list_model.UserDataBean[] objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }
}