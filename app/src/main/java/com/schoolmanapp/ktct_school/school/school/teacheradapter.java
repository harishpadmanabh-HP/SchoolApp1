package com.schoolmanapp.ktct_school.school.school;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Teacher_List;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by srishtiinnovative on 10/07/17.
 */

public class teacheradapter extends ArrayAdapter<Mod_Teacher_List.UserDataBean> {
    private LayoutInflater mInflater;
    private Context context;


    public teacheradapter(Context context, List<Mod_Teacher_List.UserDataBean> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);

    }


    private static class ViewHolder {
        public final LinearLayout linearLayout;
        public final TextView tv_name;
        public final TextView tv_tid;
        public final TextView tv_phone;
        public final TextView tvclass;
        public final TextView tv_division;
        public final TextView tv_email;


        public final ImageView icon;

        ImageView img_del;

        private ViewHolder(LinearLayout linearLayout, TextView tv_name, TextView tv_tid, TextView tvclass, TextView tv_division, TextView tv_phone, TextView tv_email, ImageView icon, ImageView img_del) {
            this.linearLayout = linearLayout;
            this.tv_name = tv_name;
            this.tv_tid = tv_tid;
            this.tvclass = tvclass;
            this.tv_division = tv_division;
            this.tv_phone = tv_phone;

            this.tv_email = tv_email;
            this.icon = icon;
            this.img_del = img_del;

        }

        public static teacheradapter.ViewHolder create(LinearLayout linearLayout) {
            TextView tvclass = (TextView) linearLayout.findViewById(R.id.tclass);
            TextView tv_division = (TextView) linearLayout.findViewById(R.id.tdiv);

            TextView tvtid = (TextView) linearLayout.findViewById(R.id.tid);
            TextView tvphno = (TextView) linearLayout.findViewById(R.id.tphno);
            TextView tvemail = (TextView) linearLayout.findViewById(R.id.temail);
            TextView tvname = (TextView) linearLayout.findViewById(R.id.tname);
            ImageView img_pic = (ImageView) linearLayout.findViewById(R.id.img_tr);
            ImageView img_del = (ImageView) linearLayout.findViewById(R.id.img_del_tr);


            return new teacheradapter.ViewHolder(linearLayout, tvname, tvtid, tvclass, tv_division, tvphno, tvemail, img_pic, img_del);
        }
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final teacheradapter.ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.custom_teacher, parent, false);
            vh = teacheradapter.ViewHolder.create((LinearLayout) view);
            view.setTag(vh);
        } else {
            vh = (teacheradapter.ViewHolder) convertView.getTag();
        }

        final Mod_Teacher_List.UserDataBean tlist = getItem(position);
        try {
            vh.tv_name.setText(tlist.getTeacherName());

            vh.tv_tid.setText(tlist.getTeacherSpecialId());
            vh.tv_phone.setText(tlist.getContactNumber());

            vh.tv_email.setText(tlist.getEmail());
            int l = tlist.getTeacherClass().size();
            if (l > 0) {
                vh.tvclass.setText(tlist.getTeacherClass().get(0).getClassName());
                vh.tv_division.setText(tlist.getTeacherClass().get(0).getDivisionName());
            } else {
                vh.tvclass.setText("No Class");
                vh.tv_division.setText("");

            }
            try {
                if(tlist.getFilePath()==null){
                    Picasso.with(context).load(R.drawable.dummy).into(vh.icon);
                }else {
                String path = "http://www.schoolman.in//" + tlist.getFilePath();

                Log.e("path", path);
                Picasso.with(context).load(path).into(vh.icon);}
            }catch (NullPointerException e)
            {
                e.printStackTrace();
            }

            vh.img_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Opendialogue(Integer.toString(tlist.getTeacherId()));

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
                Teacher.deleteInterface.onDelete(position);

            }
        });
    }


//        Picasso.with(context)
//                .load(path)
//                .transform(new RoundedCornersTransformation(30, 0,
//                        RoundedCornersTransformation.CornerType.ALL))
//                .into(holder.icon);

}
