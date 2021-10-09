package com.viniciusapp.appdamassa;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ArenaMRV extends AppCompatActivity {
    private WebView webView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arena_m_r_v);







        webView = (WebView) findViewById(R.id.webviewArenaMRV);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        // Initialize connectivy
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        //get active network

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // check network status

        if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) {

            // When internet is inactive

            //initialize dialog

            Dialog dialog = new Dialog(this);

            //set conent view
            dialog.setContentView(R.layout.dialog_alert);

            //set outside
            dialog.setCanceledOnTouchOutside(false);

            //Set dialog vidth and height
            dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);

            //Set transparent back
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            //set animation
            dialog.getWindow().getAttributes().windowAnimations =
                    android.R.style.Animation_Dialog;

            //Initialize dialog variable
            Button bt_tentedenovo = dialog.findViewById(R.id.bt_tentedenovo);

            //Perform onCliclk

            bt_tentedenovo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Call recreate methgode
                    recreate();
                }
            });
            //Show dialog
            dialog.show();

        } else {

            //When internet is active

            //load url
            webView.loadUrl("https://appdamassa.vpshostingnow.com.br/arena-mrv/");
        }
    }}