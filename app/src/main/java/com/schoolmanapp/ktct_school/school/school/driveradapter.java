package com.schoolmanapp.ktct_school.school.school;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_List_Driver;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by srishtiinnovative on 11/07/17.
 */

public class driveradapter extends ArrayAdapter<Mod_List_Driver.UserDataBean> {
    private LayoutInflater mInflater;
    private Context context;
    Bundle bundle;
    View view;


    public driveradapter(Context context, List<Mod_List_Driver.UserDataBean> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);

    }


    private static class ViewHolder {
        public final LinearLayout linearLayout;
        public final TextView tv_name;
        public final TextView tv_did;
        public final TextView tv_phone;
        public final TextView tv_lcno;
        public final TextView tv_loc;
        public final TextView tv_city;


        public final ImageView icon;

        ImageView img_del, img_edit;

        private ViewHolder(LinearLayout linearLayout, TextView tv_name, TextView tv_did, TextView tv_phone, TextView tv_lcno, TextView tv_loc, TextView tv_city, ImageView icon, ImageView img_del, ImageView img_edit) {
            this.linearLayout = linearLayout;
            this.tv_name = tv_name;
            this.tv_did = tv_did;
            this.tv_phone = tv_phone;
            this.tv_lcno = tv_lcno;
            this.tv_loc = tv_loc;

            this.tv_city = tv_city;
            this.icon = icon;
            this.img_del = img_del;
            this.img_edit = img_edit;

        }

        public static driveradapter.ViewHolder create(LinearLayout linearLayout) {
            TextView tv_loc = (TextView) linearLayout.findViewById(R.id.location);
            TextView tv_city = (TextView) linearLayout.findViewById(R.id.city);

            TextView tv_did = (TextView) linearLayout.findViewById(R.id.did);
            TextView tv_phone = (TextView) linearLayout.findViewById(R.id.dphno);
            TextView tv_lcno = (TextView) linearLayout.findViewById(R.id.d_lcno);
            TextView tv_name = (TextView) linearLayout.findViewById(R.id.dname);
            ImageView icon = (ImageView) linearLayout.findViewById(R.id.img_driver);
            ImageView img_del = (ImageView) linearLayout.findViewById(R.id.del_dr_img);
            ImageView img_edit = (ImageView) linearLayout.findViewById(R.id.edit_dr_img);


            return new driveradapter.ViewHolder(linearLayout, tv_name, tv_did, tv_phone, tv_lcno, tv_loc, tv_city, icon, img_del, img_edit);
        }
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final driveradapter.ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.customdriver, parent, false);
            vh = driveradapter.ViewHolder.create((LinearLayout) view);
            view.setTag(vh);
        } else {
            vh = (driveradapter.ViewHolder) convertView.getTag();
        }

        final Mod_List_Driver.UserDataBean tlist = getItem(position);
        try {


            vh.tv_name.setText(tlist.getDriverName());

            vh.tv_did.setText(tlist.getDriverSpecialId());
            vh.tv_phone.setText(tlist.getContactNumber());

            vh.tv_lcno.setText(tlist.getLicenseNumber());
            vh.tv_loc.setText(tlist.getAddress());
            vh.tv_city.setText(tlist.getCity());
            if (tlist.getFilePath() == null) {
                Picasso.with(context).load(R.drawable.dummy).into(vh.icon);
            } else {
                String path = "http://www.schoolman.in//" + tlist.getFilePath();
                Picasso.with(context).load(path).into(vh.icon);
            }

            vh.img_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Opendialogue(Integer.toString(tlist.getDriverId()));

                }
            });
            vh.img_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Opendialogue(Integer.toString(tlist.getDriverId()));
                    Editdialogue(Integer.toString(position));

                }
            });
        } catch (NullPointerException e) {

        }

        return vh.linearLayout;
    }

    public void Opendialogue(final String position) {

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
                Driver.deleteInterface.onDelete(position);

            }
        });
    }

    public void Editdialogue(final String position) {

        final Dialog dialog = new Dialog(context); // Context, this, etc.
        dialog.setContentView(R.layout.edit_dialogue);
        Button cancel = (Button) dialog.findViewById(R.id.dialog_cancel);
        Button ok = (Button) dialog.findViewById(R.id.dialog_ok);
        dialog.setTitle("Do you want to edit?");


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
                Driver.editInterface.onEdit(position);


            }
        });
    }


}

