package com.schoolmanapp.ktct_school.school.school;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by srishtiinnovative on 31/05/17.
 */

class teacheradptr extends BaseAdapter{
    Context context;
   ArrayList<String> tname,tid,tclass,tdivision,tphone,temail,fpath;


    public teacheradptr(Context teacher, ArrayList<String> tname, ArrayList<String> spid, ArrayList<String> classname, ArrayList<String> divname, ArrayList<String> phno, ArrayList<String> email, ArrayList<String> fpath) {
        context=teacher;
        this.tname=tname;
        this.tid=spid;
        this.tclass=classname;
        this.tdivision=divname;
        this.tphone=phno;
        this.temail=email;
        this.fpath=fpath;
    }

    @Override
    public int getCount() {
        return tname.size();
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
        TextView tv_class,tv_tid,tv_division,tv_name,tv_phone,tv_email;
          ImageView icon;
        // LinearLayout ll;
    ImageView img_del;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;



        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        rowView = vi.inflate(R.layout.custom_teacher, null);
        holder.tv_name=(TextView) rowView.findViewById(R.id.tname);
        holder.tv_tid=(TextView) rowView.findViewById(R.id.tid);
        holder.tv_class=(TextView) rowView.findViewById(R.id.tclass);
        holder.tv_division=(TextView) rowView.findViewById(R.id.tdiv);
        holder.img_del=(ImageView) rowView.findViewById(R.id.img_del_tr);

        holder.tv_phone=(TextView) rowView.findViewById(R.id.tphno);

        holder.tv_email=(TextView) rowView.findViewById(R.id.temail);
        holder.icon=(ImageView)rowView.findViewById(R.id.img_tr);



        // holder.detail=(ImageView)rowView.findViewById(R.id.img_detail);
//        holder.ll=(LinearLayout)rowView.findViewById(R.id.ll_list);

        holder.tv_name.setText(tname.get(position));
        holder.tv_tid.setText(tid.get(position));

            holder.tv_class.setText(tclass.get(position));

            holder.tv_division.setText(tdivision.get(position));

        holder.tv_phone.setText(tphone.get(position));
        holder.tv_email.setText(temail.get(position));
        try {
            if(fpath==null){
                Picasso.with(context).load(R.drawable.dummy).into(holder.icon);
            }else {
                String path = "http://www.schoolman.in//" +fpath;
                Log.e("path", path);
                Picasso.with(context).load(path).into(holder.icon);}
        }catch (NullPointerException e)
        {
            e.printStackTrace();
        }        holder.img_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Opendialogue(String.valueOf(position));
            }
        });
//        Picasso.with(context)
//                .load(path)
//                .transform(new RoundedCornersTransformation(30, 0,
//                        RoundedCornersTransformation.CornerType.ALL))
//                .into(holder.icon);
        return rowView;
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


}
