package com.schoolmanapp.ktct_school.school.school;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_List_Students;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by srishtiinnovative on 10/07/17.
 */

public class studentadapter extends ArrayAdapter<Mod_List_Students.UserDataBean> {
    private LayoutInflater mInflater;
    private Context context;

    public studentadapter(Context context, List<Mod_List_Students.UserDataBean> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    private static class ViewHolder {
        public final LinearLayout linearLayout;
        public final TextView name;
        public final TextView stid;
        public final TextView parname;
        public final TextView phno;
        public final TextView loc;
        public final TextView city;
        public final TextView bus;
        public final TextView dob;
        public final TextView blood;
        public final TextView gendr;
        public final ImageView pic;
        public final ImageView icon;
        public final TextView Addrs;




        private ViewHolder(LinearLayout linearLayout, TextView name, TextView stid, TextView parname, TextView phno, TextView bus, TextView loc, TextView city, ImageView pic, ImageView icon, TextView dob, TextView gendr, TextView blood, TextView addrs) {
            this.linearLayout = linearLayout;
            this.name = name;
            this.stid = stid;
            this.parname = parname;
            this.phno = phno;

            this.bus = bus;
            this.loc = loc;
            this.city = city;
            this.pic = pic;
            this.icon = icon;
           this.blood=blood;
           this.dob=dob;
           this.gendr=gendr;
        this.Addrs=addrs;
        }

        public static studentadapter.ViewHolder create(LinearLayout linearLayout) {
            TextView parname = (TextView) linearLayout.findViewById(R.id.sparname);
            TextView stid = (TextView) linearLayout.findViewById(R.id.sid);
            TextView phno = (TextView) linearLayout.findViewById(R.id.sphno);
            TextView loc = (TextView) linearLayout.findViewById(R.id.slocation);
            TextView city = (TextView) linearLayout.findViewById(R.id.scity);
            TextView name = (TextView) linearLayout.findViewById(R.id.sname);
            TextView bus = (TextView) linearLayout.findViewById(R.id.sbusid);
            ImageView pic = (ImageView) linearLayout.findViewById(R.id.stud_img);
            ImageView icon = (ImageView) linearLayout.findViewById(R.id.img_del_stud);
            TextView dob = (TextView) linearLayout.findViewById(R.id.sdob);
            TextView gendr = (TextView) linearLayout.findViewById(R.id.sgendr);
            TextView blood = (TextView) linearLayout.findViewById(R.id.sblood);
            TextView Addrs = (TextView) linearLayout.findViewById(R.id.saddr);

            return new studentadapter.ViewHolder(linearLayout, name, stid, parname, phno,bus, loc, city, pic, icon,dob,gendr,blood,Addrs);
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final studentadapter.ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.customstudent, parent, false);
            vh = studentadapter.ViewHolder.create((LinearLayout) view);
            view.setTag(vh);
        } else {
            vh = (studentadapter.ViewHolder) convertView.getTag();
        }

        final Mod_List_Students.UserDataBean slist = getItem(position);
        //final Mod_List_Classdiv.UserDataBean.DivisionBean div_list=item.getDivision().get(position);
        try {
            vh.name.setText(slist.getStundentName());
            vh.parname.setText(slist.getParentName());
            vh.stid.setText(slist.getStudentSpecialId());
            vh.phno.setText(slist.getContactNumber());
            vh.gendr.setText(slist.getGender());
            vh.dob.setText(slist.getDOBString());
            vh.blood.setText(slist.getBloodGroup());
            vh.Addrs.setText(slist.getAddress());
            vh.loc.setText(slist.getCity());
            if (slist.getBusId() == 1) {
                vh.bus.setText("N/A");
            } else {
                vh.bus.setText(slist.getBusSpecialId());
            }
            vh.city.setText(slist.getState());
//            vh.tvclass.setText(slist.getDivision().getClassName());
//            vh.div.setText(slist.getDivision().getDivisionName());
            if(slist.getFilePath()==null){
                Picasso.with(context).load(R.drawable.dummy).into(vh.pic);
            }
            else if(slist.getFilePath().equals("NULL")){
                Picasso.with(context).load(R.drawable.dummy).into(vh.pic);
            }else {
            String path = "http://www.schoolman.in///" + slist.getFilePath();
            Picasso.with(context).load(path).into(vh.pic);}
            vh.icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Opendialogue(Integer.toString(slist.getStudentId()));

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
                Student.deleteInterface.onDelete(position);

            }
        });
    }

}
