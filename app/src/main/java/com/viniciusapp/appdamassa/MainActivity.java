package com.viniciusapp.appdamassa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;

import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity {
    //LinearLayout news, tabela, calendar, estatisticas, galonaveia
    LinearLayout Notíciasblock, Tabelablock, Calendário, Estatísticasblock,WallpaperBlock, GaloNaVeiaBlock, SetoristasBlock, ArenaMRVBlock, MelhoresMomentosBlock;


    private static final String ONESIGNAL_APP_ID = "e77553fd-18fd-4696-b29f-44a221ff14ff";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


        MultiDex.install(this);

        Intent intentBackgroundService = new Intent(this, FireBasePushNotificationClass.class);
        startService(intentBackgroundService);

        final Intent ii = new Intent(this, news.class);


        Notíciasblock = findViewById(R.id.Notíciasblock);
        Tabelablock = findViewById(R.id.Tabelablock);
        Calendário = findViewById(R.id.Calendário);
        Estatísticasblock = findViewById(R.id.Estatísticasblock);
        WallpaperBlock = findViewById(R.id.WallpaperBlock);
        SetoristasBlock = findViewById(R.id.SetoristaBlock);
        ArenaMRVBlock = findViewById(R.id.ArenaMRVBlock);
        MelhoresMomentosBlock = findViewById(R.id.MelhoresMomentosBlock);


        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);


        Notíciasblock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, news.class);
                startActivity(intent);


            }
        });


        Tabelablock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Tabela.class);
                startActivity(intent);
            }
        });


        Calendário.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Calendar.class);
                startActivity(intent);
            }
        });


        Estatísticasblock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, estatistica.class);
                startActivity(intent);
            }
        });


        SetoristasBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Setoristas.class);
                startActivity(intent);
            }
        });

        ArenaMRVBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ArenaMRV.class);
                startActivity(intent);
            }
        });

        MelhoresMomentosBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MelhoresMomentos.class);
                startActivity(intent);
            }
        });

        WallpaperBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, walpper.class);
                startActivity(intent);
            }
        });
    }
}