package com.viniciusapp.appdamassa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;



public class Tabela extends AppCompatActivity {
    //LinearLayout mineiro, liebrtadores, brasileiro, copa;
    LinearLayout mineiroblock, libertadoresblock, brasileiroblock, copabrblock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabela);


        mineiroblock = findViewById(R.id.mineiroblock);
        libertadoresblock = findViewById(R.id.libertadoresblock);
        brasileiroblock = findViewById(R.id.brasileiroblock);
        copabrblock = findViewById(R.id.copabrblock);


        mineiroblock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Tabela.this, Mineiro.class);
                startActivity(intent);


            }
        });


        libertadoresblock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Tabela.this, Libertadores.class);
                startActivity(intent);
            }
        });


        brasileiroblock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Tabela.this, brasileiro.class);
                startActivity(intent);
            }
        });


        copabrblock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Tabela.this, CopaBr.class);
                startActivity(intent);
            }
        });

    }
}