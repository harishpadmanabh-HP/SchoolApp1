package com.schoolmanapp.ktct_school.school.school;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_List_Classdiv;

import java.util.List;

/**
 * Created by srishtiinnovative on 13/06/17.
 */

public class TestAdapter extends ArrayAdapter<Mod_List_Classdiv.UserDataBean> {




    /**
     * ViewHolder class for layout.<br />
     * <br />
     * Auto-created on 2015-08-13 11:31:31 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private static class ViewHolder {
        public final LinearLayout linearLayout;
        public final TextView tv_class;
        public final TextView tv_div;


        private ViewHolder(LinearLayout linearLayout, TextView tv_class, TextView tv_div) {
            this.linearLayout = linearLayout;
            this.tv_class = tv_class;
            this.tv_div = tv_div;
        }

        public static ViewHolder create(LinearLayout linearLayout) {
            TextView tv_class = (TextView) linearLayout.findViewById(R.id.txt_class);
            TextView tv_div = (TextView) linearLayout.findViewById(R.id.txt_div);
            return new ViewHolder(linearLayout, tv_class,tv_div);
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.custom_class, parent, false);
            vh = ViewHolder.create((LinearLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

     final Mod_List_Classdiv.UserDataBean div_list = getItem(position);
        //int len=Mod_List_Classdiv.UserDataBean.DivisionBean.;
       // for(int j=0;j<len;j++) {
            // div_list.getClassName()
            //final Mod_List_Classdiv.UserDataBean.DivisionBean div_list=item.getDivision().get(position);


            int l = (div_list.getDivision().size());
            if (l > 0)
                for (int i = 0; i < l; i++) {//vh.create();
                    vh.tv_class.setText(div_list.getClassName());
                    vh.tv_div.setText(div_list.getDivision().get(i).getDivisionName());

                }

        //}
        return vh.linearLayout;
    }

    private LayoutInflater mInflater;
    private Context context;

    // Constructors
    public TestAdapter(Context context,List<Mod_List_Classdiv.UserDataBean> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

  
}