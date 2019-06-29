package com.schoolmanapp.ktct_school.school.parent;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.Java_class.activity_newevents;
import com.schoolmanapp.ktct_school.school.parent.actvity_events;
import com.schoolmanapp.ktct_school.school.school.FileDisplay;
import com.schoolmanapp.ktct_school.school.school.FourInOne;
import com.schoolmanapp.ktct_school.school.school.Model.event_model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by srishtiinnovative on 16/10/17.
 */

public class adapter_events extends BaseAdapter {
    Context mContext;

    List<event_model.UserDataBean> list;
    ArrayList<String> dates;
    Integer flag=0;




    public adapter_events(activity_newevents activity, List<event_model.UserDataBean> list) {
        mContext = activity;
        this.list = list;
        dates=new ArrayList<String>();
    }

    @Override
    public int getCount() {
        return list.size();
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
        }
 else {
            v = (View) convertView;
        }



        TextView date_text = (TextView) v.findViewById(R.id.datesss);
        TextView day_text = (TextView) v.findViewById(R.id.daysss);
      TextView des_text = (TextView) v.findViewById(R.id.descriptionsss);
//        TextView read_text = (TextView) v.findViewById(R.id.img_readmore);
        ImageView file_img = (ImageView) v.findViewById(R.id.fileimg);
        LinearLayout llMain = (LinearLayout) v.findViewById(R.id.llMain);
        if(list.get(position).getHead().equals("Circular")) {
            v.setVisibility(View.VISIBLE);
            llMain.setVisibility(View.VISIBLE);
            des_text.setText(list.get(position).getDescription());
            if(list.get(position).getFilePath()!="")
            {
                file_img.setVisibility(View.VISIBLE);
            }
            else
            {
                file_img.setVisibility(View.GONE);
            }
      //      des_text.setTrimExpandedText(list.get(position).getDescription());
            int l=list.get(position).getDescription().length();
            if(list.get(position).getDescription().length()>100 && (list.get(position).getFilePath()!=""))
            {
                des_text.setText((list.get(position).getDescription()).substring(0,20)+"...");
                des_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, FileDisplay.class);
                        intent.putExtra("description",list.get(position).getDescription());
                        intent.putExtra("filepath",list.get(position).getFilePath());
                        mContext.startActivity(intent);
                    }
                });
            }
            else if(list.get(position).getDescription().length()>100)
            {

                des_text.setText((list.get(position).getDescription()).substring(0,20)+"...");
                des_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, FileDisplay.class);
                        intent.putExtra("description", list.get(position).getDescription());

                        mContext.startActivity(intent);
                    }
                });
            }

            try {
                String str_date = list.get(position).getCircularDate();

                Date date;
                SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                date = (Date) simpleFormat.parse(str_date);
                System.out.println("Today is " + date);
                String dayOfTheWeek = (String) DateFormat.format("EE", date); // Thursday
                String day = (String) DateFormat.format("dd", date);
                day_text.setText(dayOfTheWeek);
                date_text.setText(day);
//                if(position==0) {
//                    day_text.setText(dayOfTheWeek);
//                    date_text.setText(day);
//                }
//                else {
//                    for (int j = position; j < 0; j--) {
//
//                        if (!(str_date.equals(list.get(j).getCircularDate()))) {
//                            flag=1;
//
//                        }
//                        if(flag==0)
//                        day_text.setText(dayOfTheWeek);
//                        date_text.setText(day);
//                    }
//                }

                Log.e("Today is", date + "");
                Log.e("dayOfTheWeek is", dayOfTheWeek + "");
                Log.e("day is", day + "");
            } catch (ParseException e) {
                System.out.println("Exception :" + e);
                Log.e("Exception is", e + "");
            }

        }else{
            llMain.setVisibility(View.GONE);
            v.setVisibility(View.GONE);
        }
file_img.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(mContext, FileDisplay.class);
        intent.putExtra("filepath",list.get(position).getFilePath());
        mContext.startActivity(intent);
    }
});


        return v;
    }


}
