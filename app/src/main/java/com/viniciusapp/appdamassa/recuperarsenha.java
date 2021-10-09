package com.viniciusapp.appdamassa;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class recuperarsenha extends AppCompatActivity {
    EditText semail;
    Button rbtn;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperarsenha);
        rbtn=findViewById(R.id.rbtn);
        semail=findViewById(R.id.semail);

        rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =semail.getText().toString().trim();
                resetarSenhar(email);
            }
        });
    }

    private void resetarSenhar(String email) {
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(recuperarsenha.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            AlertDialog.Builder alerta = new AlertDialog.Builder(recuperarsenha.this);
                            alerta.setMessage
                                    ("Enviamos um Email para voce")
                                    .setNegativeButton("OK"
                                            , null)
                                    .create()
                                    .show();
                        }else{
                            AlertDialog.Builder alerta = new AlertDialog.Builder(recuperarsenha.this);
                            alerta.setMessage
                                    ("Email nao resistado")
                                    .setNegativeButton("OK"
                                            , null)
                                    .create()
                                    .show();
                        }
                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();
        auth =coneccao.getFirebaseAuth();
    }

}