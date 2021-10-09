package com.viniciusapp.appdamassa;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.IUnityBannerListener;
import com.unity3d.services.banners.UnityBanners;

public class Setoristas extends AppCompatActivity {
    private WebView webView;


    //Unity ads
    private String GAMEID= "4193785";
    private String BANNER_ID="Banner_Android";
    private String INTERSTITIAL_ID="Interstitial_Android";
    private  Boolean test=false;
    private LinearLayout bannerlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setoristas);



        bannerlayout= findViewById(R.id.bannerlayout);
//Inicialize Unity
        UnityAds.initialize(this, GAMEID, test);
        //banner
        IUnityBannerListener iUnityBannerListener= new IUnityBannerListener() {
            @Override
            public void onUnityBannerLoaded(String placementId, View view) {
                ((ViewGroup)findViewById(R.id.bannerlayout)).removeView(view);
                ((ViewGroup)findViewById(R.id.bannerlayout)).addView(view);


            }

            @Override
            public void onUnityBannerUnloaded(String placementId) {

            }

            @Override
            public void onUnityBannerShow(String placementId) {

            }

            @Override
            public void onUnityBannerClick(String placementId) {

            }

            @Override
            public void onUnityBannerHide(String placementId) {

            }

            @Override
            public void onUnityBannerError(String message) {

            }
        };
        UnityBanners.setBannerListener(iUnityBannerListener);







        //WebView
        webView = (WebView) findViewById(R.id.webview_setoristas);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //improve webView performance
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //force links open in webview only
        webView.setWebViewClient(new WebViewClient());


        // Initialize connectivy
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        //get active network

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // check network status

        if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) {
            UnityBanners.setBannerListener(iUnityBannerListener);
            UnityBanners.loadBanner(Setoristas.this, BANNER_ID);
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
                    UnityBanners.loadBanner(Setoristas.this, BANNER_ID);
                    recreate();
                }
            });
            //Show dialog
            dialog.show();

        } else {

            //When internet is active
            UnityBanners.loadBanner(Setoristas.this, BANNER_ID);
            //load url
            webView.loadUrl("https://appdamassa.vpshostingnow.com.br/setoristas/");
        }
    }
}
