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

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.IUnityBannerListener;
import com.unity3d.services.banners.UnityBanners;

public class news extends AppCompatActivity {
    private WebView webView;
    private String GAMEID= "4193785";
    private String BANNER_ID="Banner_Android";
    private String INTERSTITIAL_ID="Interstitial_Android";
    private  Boolean test=false;
    private LinearLayout bannerlayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);





        webView = (WebView) findViewById(R.id.webview_news);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //improve webView performance;
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //force links open in webview only
        webView.setWebViewClient(new WebViewClient());
        //premiado
        UnityAds.initialize(this, GAMEID, test);
        IUnityAdsListener iUnityAdsListener = new IUnityAdsListener() {
            @Override
            public void onUnityAdsReady(String placementId) {
                //Toast.makeText(Album.this, "Processando ads", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onUnityAdsStart(String placementId) {
                //  Toast.makeText(Album.this, "Aberto ads", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUnityAdsFinish(String placementId, UnityAds.FinishState result) {
                // Toast.makeText(Album.this, "Terminado ads", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUnityAdsError(UnityAds.UnityAdsError error, String message) {
                // Toast.makeText(Album.this, "Erro ads", Toast.LENGTH_SHORT).show();
            }
        };
        UnityAds.setListener(iUnityAdsListener);

        //banner
        IUnityBannerListener iUnityBannerListener= new IUnityBannerListener() {
            @Override
            public void onUnityBannerLoaded(String placementId, View view) {
                ((ViewGroup)findViewById(R.id.bannerlayout)).removeView(view);
                ((ViewGroup)findViewById(R.id.bannerlayout)).addView(view);
                UnityBanners.loadBanner(news.this, BANNER_ID);
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
        UnityBanners.loadBanner(news.this, BANNER_ID);



        // Initialize connectivy
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        //get active network

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // check network status

        if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) {



            UnityBanners.setBannerListener(iUnityBannerListener);
            UnityBanners.loadBanner(news.this, BANNER_ID);
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
            webView.loadUrl("https://globoesporte.globo.com/futebol/times/atletico-mg/");
        }
    }
}
