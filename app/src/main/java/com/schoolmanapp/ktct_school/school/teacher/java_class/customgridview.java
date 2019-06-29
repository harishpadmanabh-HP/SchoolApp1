package com.schoolmanapp.ktct_school.school.teacher.java_class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.R;

import java.util.ArrayList;

/**
 * Created by srishtiinnovative on 05/05/17.
 */

public class customgridview extends BaseAdapter{

    private Context mContext;
    public ArrayList studentid,student_name;
int mSelectedItem;



    public customgridview(student_activity student_activity, String[] roll, String[] name) {

    }

    public customgridview(student_activity student_activity, ArrayList studentid, ArrayList student_name) {
        mContext=student_activity;
        this.studentid=studentid;
        this.student_name=student_name;
    }


    @Override
    public int getCount() {
        return studentid.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView tv_name,tv_roll;
        LinearLayout l;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;


        LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       // LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        rowView = vi.inflate(R.layout.custom_student, null);
        holder.tv_roll=(TextView) rowView.findViewById(R.id.tvId);
        holder.tv_name=(TextView) rowView.findViewById(R.id.tvName);
        holder.l=(LinearLayout)rowView.findViewById(R.id.grid_bg);
        holder.tv_roll.setText(studentid.get(position).toString());
        holder.tv_name.setText(student_name.get(position).toString());



        return rowView;
    }

}
