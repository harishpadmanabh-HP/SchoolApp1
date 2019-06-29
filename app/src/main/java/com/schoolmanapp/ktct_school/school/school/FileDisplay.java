package com.schoolmanapp.ktct_school.school.school;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srishtiinnovative on 03/04/18.
 */

public class FileDisplay extends Activity {
    String type;
    @Bind(R.id.wv_fileattach)
    WebView wv;

    @Bind(R.id.ll_more)
    LinearLayout ll_more;

    @Bind(R.id.txtdesc)
    TextView tv;

    //    @Bind(R.id.img_show)
//    ImageView iv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showattachments);
        wv  = new WebView(this);
        ButterKnife.bind(this);
        type=getIntent().getExtras().getString("type");
        String value = getIntent().getExtras().getString("filepath");
        String desc = getIntent().getExtras().getString("description");
        TextView title=(TextView)findViewById(R.id.shw_title);
        Log.e("value",value+"");
        if(type!=null){
            title.setText(type);
        }
        else{
            title.setText("Circular");
        }

        if(desc!=null && value!=null)
        {
            ll_more.setVisibility(View.VISIBLE);
            tv.setVisibility(View.VISIBLE);
            tv.setText(desc);
            wv.setVisibility(View.VISIBLE);
            wv .loadUrl("http://www.schoolman.in/"+value);

//            iv.setVisibility(View.GONE);
//            if(value!=null)
//            Picasso.with(this).load("http://www.schoolman.in/"+value).into(iv);

        }
       else if(desc==null && value!=null)
        {
            ll_more.setVisibility(View.GONE);
            tv.setVisibility(View.GONE);
            wv.setVisibility(View.VISIBLE);
            wv .loadUrl("http://www.schoolman.in/"+value);
        }
        else if(desc!=null && value==null)
        {
            ll_more.setVisibility(View.VISIBLE);
            tv.setVisibility(View.VISIBLE);
            tv.setText(desc);
            wv.setVisibility(View.GONE);

        }
//        else
//        {
//            ll_more.setVisibility(View.VISIBLE);
//            wv.setVisibility(View.GONE);
//            tv.setText(desc);
//            if(value!=null)
//                Picasso.with(getApplicationContext()).load("http://www.schoolman.in/"+value).into(iv);
//        }

    }
}
