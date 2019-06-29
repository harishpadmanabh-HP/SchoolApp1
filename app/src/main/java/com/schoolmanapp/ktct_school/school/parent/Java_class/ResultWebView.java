package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.schoolmanapp.ktct_school.school.R;

public class ResultWebView extends AppCompatActivity {
    String html;
    WebView webView;
    final String mimeType = "text/html";
    final String encoding = "UTF-8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_web_view);
        html=getIntent().getStringExtra("html");
        webView=findViewById(R.id.resultwebview);
        if(!html.equalsIgnoreCase(""))
        {
            webView.loadDataWithBaseURL("", html, mimeType, encoding, "");

        }


    }
}
