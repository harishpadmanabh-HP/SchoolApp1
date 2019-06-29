package com.schoolmanapp.ktct_school.school.school;

import android.app.Dialog;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.R;

import java.util.ArrayList;

/**
 * Created by srishtiinnovative on 31/05/17.
 */

class classadapter extends BaseAdapter {
    TextView tv_class, tv_division;
    LinearLayout ll;

    Context context;

    ArrayList<String> classname, division;
    //  ArrayList<Integer> classid, divisionid;

    public classadapter(Context classdiv, ArrayList<String> studclass, ArrayList<String> division) {

        context = classdiv;
        this.classname = studclass;
        this.division = division;

    }

    @Override
    public int getCount() {
        return classname.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        TextView tv_class, tv_division;
        ImageView del_img;
        LinearLayout ll;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder = new Holder();
        View rowView;


        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        rowView = vi.inflate(R.layout.custom_class, null);
        holder.tv_class = (TextView) rowView.findViewById(R.id.txt_class);
        holder.tv_division = (TextView) rowView.findViewById(R.id.txt_div);
        holder.del_img = (ImageView) rowView.findViewById(R.id.img_del_div);
        holder.ll = (LinearLayout) rowView.findViewById(R.id.ll_classdiv);
        try {
            holder.tv_class.setText(classname.get(position));
            holder.tv_division.setText(division.get(position));


            holder.del_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Opendialogue(position);

                }
            });
            holder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//              loadFragment2(new Student());
                    Home.isclassdiv = true;
                    Classdiv.clickInterface.onllclick(position);


                }
            });
        } catch (NullPointerException e) {

        }
        return rowView;

    }

    public void Opendialogue(final int position) {

        final Dialog dialog = new Dialog(context); // Context, this, etc.
        dialog.setContentView(R.layout.delete_dialogue);
        Button cancel = (Button) dialog.findViewById(R.id.dialog_cancel);
        Button ok = (Button) dialog.findViewById(R.id.dialog_ok);
        dialog.setTitle("Do you want to delete?");


        dialog.show();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Classdiv.deleteInterface.onDelete(position);


            }
        });
    }

}
