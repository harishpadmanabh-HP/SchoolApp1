package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.schoolmanapp.ktct_school.school.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srishtiinnovative on 15/01/18.
 */

public class webview  extends Activity{
    @Bind(R.id.webview)
    WebView wv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        wv  = new WebView(this);
        ButterKnife.bind(this);
        wv .loadUrl("http://www.schoolman.in/");
    }
}
