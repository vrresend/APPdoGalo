package com.viniciusapp.appdamassa;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.IUnityBannerListener;
import com.unity3d.services.banners.UnityBanners;

public class MelhoresMomentos extends AppCompatActivity {

    WebView webView;
    //Unity ads
    private String GAMEID= "4193785";
    private String BANNER_ID="Banner_Android";
    private String INTERSTITIAL_ID="Interstitial_Android";
    private  Boolean test=false;
    private LinearLayout bannerlayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_melhores_momentos);



        webView = findViewById(R.id.webViewmelhores);

        webView.setWebViewClient(new Browser_Home());
        webView.setWebChromeClient(new MyChrome());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);

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

        loadWebSite();


    }

    private void loadWebSite() {
        UnityBanners.loadBanner(MelhoresMomentos.this, BANNER_ID);
        webView.loadUrl("https://www.youtube.com/watch?v=kYJToG4lUhg&list=PLq6cCvKQv0R7ZhS6nXWufBTUz35ss05ur");
    }

    private class Browser_Home extends WebViewClient {
        Browser_Home() {
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }

    private class MyChrome extends WebChromeClient {
        private View mCustomView;
        private WebChromeClient.CustomViewCallback mCustomViewCallback;
        protected FrameLayout mFullscreenContainer;
        private int mOriginalOrientation;
        private int mOriginalSystemUiVisibility;

        void ChromeClient() {
        }

        public Bitmap getDefaultVideoPoster() {
            if (mCustomView == null) {
                return null;
            }
            return BitmapFactory.decodeResource(getApplicationContext().getResources(), 2130837573);
        }

        public void onHideCustomView() {
            ((FrameLayout) getWindow().getDecorView()).removeView(this.mCustomView);
            this.mCustomView = null;
            getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
            setRequestedOrientation(this.mOriginalOrientation);
            this.mCustomViewCallback.onCustomViewHidden();
            this.mCustomViewCallback = null;
        }

        public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback) {
            if (this.mCustomView != null) {
                onHideCustomView();
                return;
            }
            this.mCustomView = paramView;
            this.mOriginalSystemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
            this.mOriginalOrientation = getRequestedOrientation();
            this.mCustomViewCallback = paramCustomViewCallback;
            ((FrameLayout) getWindow().getDecorView()).addView(this.mCustomView, new FrameLayout.LayoutParams(-1, -1));
            getWindow().getDecorView().setSystemUiVisibility(3846 | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }
}