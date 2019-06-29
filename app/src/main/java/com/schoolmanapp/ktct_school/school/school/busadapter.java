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
import com.schoolmanapp.ktct_school.school.school.Model.Mod_List_Bus;

import java.util.List;

/**
 * Created by srishtiinnovative on 11/07/17.
 */

public class busadapter extends ArrayAdapter<Mod_List_Bus.UserDataBean> {
    private LayoutInflater mInflater;
    private Context context;


    public busadapter(Context context, List<Mod_List_Bus.UserDataBean> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);

    }


    private static class ViewHolder {
        public final LinearLayout linearLayout;
        public final TextView tv_busrgno;
        public final TextView tv_bid;
        public final TextView tv_btripno;
        public final TextView tv_btype;
        public final TextView tv_start;
     //   public final TextView tv_end;


         public final ImageView icon;



        private ViewHolder(LinearLayout linearLayout, TextView tv_busrgno, TextView tv_bid, TextView tv_btripno, TextView tv_btype, TextView tv_start, ImageView icon) {
            this.linearLayout = linearLayout;
            this.tv_busrgno = tv_busrgno;
            this.tv_bid = tv_bid;
            this.tv_btripno = tv_btripno;
            this.tv_btype = tv_btype;
            this.tv_start = tv_start;

           // this.tv_end = tv_end;
            this.icon=icon;

        }

        public static busadapter.ViewHolder create(LinearLayout linearLayout) {
            TextView tv_busrgno = (TextView) linearLayout.findViewById(R.id.bregno);
            TextView tv_bid = (TextView) linearLayout.findViewById(R.id.bid);

            TextView tv_btripno = (TextView) linearLayout.findViewById(R.id.btripno);
            TextView tv_btype = (TextView) linearLayout.findViewById(R.id.btype);
            TextView tv_start = (TextView) linearLayout.findViewById(R.id.location);
         //   TextView tv_end = (TextView) linearLayout.findViewById(R.id.city);
            ImageView icon = (ImageView) linearLayout.findViewById(R.id.img_del);


            return new busadapter.ViewHolder(linearLayout, tv_busrgno, tv_bid, tv_btripno, tv_btype, tv_start, icon);
        }
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final busadapter.ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.custombus, parent, false);
            vh = busadapter.ViewHolder.create((LinearLayout) view);
            view.setTag(vh);
        } else {
            vh = (busadapter.ViewHolder) convertView.getTag();
        }

        final Mod_List_Bus.UserDataBean tlist = getItem(position);
        try{
        final String busid = Integer.toString(tlist.getBusId());
        String busname=tlist.getBusName();

        vh.tv_busrgno.setText(busname);

        vh.tv_bid.setText(tlist.getBusSpecialId());
        vh.tv_btripno.setText(tlist.getTripNumber());

        vh.tv_btype.setText(tlist.getBusType());
        vh.tv_start.setText(tlist.getLocationStart());
     //   vh.tv_end.setText(tlist.getLocationEnd());


        vh.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opendialogue(busid);

            }
        });
      }
        catch (NullPointerException e){

        }
        return vh.linearLayout;
    }

    public  void Opendialogue(final String position) {

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
                Bus.deleteInterface.onDelete(position);

            }
        });
    }

}


