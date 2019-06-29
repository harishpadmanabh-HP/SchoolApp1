package com.schoolmanapp.ktct_school.school.school;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.schoolmanapp.ktct_school.school.R;

import java.util.List;

/**
 * Created by srishtiinnovative on 31/05/17.
 */

class studadptr1 extends ArrayAdapter<String> {

/**
 * ViewHolder class for layout.<br />
 * <br />
 * Auto-created on 2015-08-13 11:31:31 by Android Layout Finder
 * (http://www.buzzingandroid.com/tools/android-layout-finder)
 */
private static class ViewHolder {
    public final LinearLayout linearLayout;


    private ViewHolder(LinearLayout linearLayout) {
        this.linearLayout = linearLayout;

    }

    public static ViewHolder create(LinearLayout linearLayout) {

        return new ViewHolder(linearLayout);
    }
}

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.customstudent, parent, false);
            vh = ViewHolder.create((LinearLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        final String item = getItem(position);


        return vh.linearLayout;
    }

    private LayoutInflater mInflater;
    private Context context;

    // Constructors
    public studadptr1(Context context, List<String> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public studadptr1(Context context, String[] objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

}
