package com.viniciusapp.appdamassa;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.IUnityBannerListener;
import com.unity3d.services.banners.UnityBanners;


public class GaloNaVEia extends AppCompatActivity {

    private WebView webView;
    private String URL = "https://appdamassa.vpshostingnow.com.br/wallapapers/";

    //Unity ads
    private String GAMEID= "4193785";
    private String BANNER_ID="Banner_Android";
    private String INTERSTITIAL_ID="Interstitial_Android";
    private  Boolean test=false;
    private LinearLayout bannerlayout;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galo_na_v_eia);


        checkDownloadPermission();
        webView = findViewById(R.id.webview_galonaveia);
        webView.loadUrl(URL);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

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
                UnityBanners.loadBanner(GaloNaVEia.this, BANNER_ID);
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
        UnityBanners.loadBanner(GaloNaVEia.this, BANNER_ID);

        webView.setDownloadListener(new DownloadListener() {
            @Override

            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimetype));
                request.setDescription("Downloading file...");
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimetype));
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Toast.makeText(getApplicationContext(), "Baixando o Wallpaper...", Toast.LENGTH_SHORT).show();
                registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
            }

            BroadcastReceiver onComplete = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Toast.makeText(getApplicationContext(), "Wallpaper baixado atleticano, agora é só usar!!", Toast.LENGTH_SHORT).show();
                }
            };
        });
    }

    private void checkDownloadPermission() {
        UnityBanners.loadBanner(GaloNaVEia.this, BANNER_ID);

        if (ActivityCompat.shouldShowRequestPermissionRationale(GaloNaVEia.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(GaloNaVEia.this, "Fala Atleticano! Você autoriza o APP da Massa salvar as os Wallpapers no seu celular?(para baixar é so clicar sobre a imagem desejada!!).", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(GaloNaVEia.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
    }
}