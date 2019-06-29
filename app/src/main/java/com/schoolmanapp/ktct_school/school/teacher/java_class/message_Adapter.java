package com.schoolmanapp.ktct_school.school.teacher.java_class;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.teacher.model_class.teachermessage_model;
import com.schoolmanapp.ktct_school.school.teacher.utils.AppPreferences;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by srishtiinnovative on 08/02/18.
 */

public class message_Adapter extends BaseAdapter {
    Context mContext;
    AppPreferences appPreferences;
    List<teachermessage_model.UserDataBean>  list;
    public TextView subject;
    public TextView descritpion;
    public ImageView image;
    public TextView time;
    public RelativeLayout ll;

    String subdate = "";
    java.util.Date dates;
    public message_Adapter(Context activity, List<teachermessage_model.UserDataBean> list) {

        mContext = activity;
        this.list = list;

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

        View view;

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_message_parent, parent, false);
        } else {
            view = (View) convertView;
        }
        appPreferences = AppPreferences.getInstance(mContext, mContext.getResources().getString(R.string.app_name));
        subject = (TextView) view.findViewById(R.id.tv_subject);
        image = (ImageView) view.findViewById(R.id.imageView);
        descritpion = (TextView) view.findViewById(R.id.description);
        time = (TextView) view.findViewById(R.id.time);
        ll=(RelativeLayout)view.findViewById(R.id.ll_tosend);

        try {
            String date = list.get(position).getTimeStamp();
            int i = date.indexOf('T');
            subdate = date.substring(0, i);
            Log.e(date + " :", "!!!" + subdate);
        } catch (NullPointerException e) {

        }


        SimpleDateFormat readFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat writeFormat = new SimpleDateFormat("MMM dd,yyyy");

        try {
            dates = readFormat.parse(subdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Log.e("Changed date", "" + writeFormat.format(dates));


       subject.setText(list.get(position).getSubject());
        time.setText(writeFormat.format(dates));
        descritpion.setText(list.get(position).getDescription());
        String path = "http://schoolman.in//" + list.get(position).getFilePath();
        Picasso.with(mContext).load(path).into(image);


        return view;

    }



}
