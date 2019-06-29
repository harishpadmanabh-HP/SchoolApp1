package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.model_class.model_events_Calender;
import com.schoolmanapp.ktct_school.school.school.FileDisplay;
import com.schoolmanapp.ktct_school.school.school.Model.event_model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by srishtiinnovative on 16/10/17.
 */

public class adapter_calender_events extends BaseAdapter {
    Context mContext;

    //List<model_events_Calender.UserDataBean> list;
    ArrayList<String> list,desc;
ArrayList indexes;



    public adapter_calender_events(calender_events activity, ArrayList<String> list, ArrayList arrayList, ArrayList indexes ) {
        mContext = activity;
        this.list = list;
        this.desc=arrayList;
        this.indexes=indexes;
    }

    @Override
    public int getCount() {
        return indexes.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v;

        if (convertView == null) {  // if it's not recycled, initialize some attributes
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.custom_events, parent, false);
        } else {
            v = (View) convertView;
        }




        TextView date_text = (TextView) v.findViewById(R.id.datesss);
        TextView day_text = (TextView) v.findViewById(R.id.daysss);
        TextView des_text = (TextView) v.findViewById(R.id.descriptionsss);
           if(desc.get(Integer.parseInt(indexes.get(position)+"")).length()>100)
        {

            des_text.setText((desc.get(Integer.parseInt(indexes.get(position)+"")).substring(0,20)+ " ..."));
            des_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, FileDisplay.class);
                    intent.putExtra("type","Event");
                    intent.putExtra("description", desc.get(Integer.parseInt(indexes.get(position)+"")).toString());

                    mContext.startActivity(intent);
                }
            });
        }
        else

        des_text.setText(desc.get(Integer.parseInt(indexes.get(position)+"")));


        try {
            String str_date = list.get(Integer.parseInt(indexes.get(position)+""));

            Date date;
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            date = (Date) simpleFormat.parse(str_date);
            System.out.println("Today is " + date);
            String dayOfTheWeek = (String) DateFormat.format("EE", date); // Thursday
            String day          = (String) DateFormat.format("dd",   date);
            day_text.setText(dayOfTheWeek);
            date_text.setText(day);
            Log.e("Today is",date+"");
            Log.e("dayOfTheWeek is",dayOfTheWeek+"");
            Log.e("day is",day+"");
        } catch (ParseException e) {
            System.out.println("Exception :" + e);
            Log.e("Exception is",e+"");
        }





        return v;

    }


}
