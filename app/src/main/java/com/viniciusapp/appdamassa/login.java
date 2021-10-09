package com.viniciusapp.appdamassa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class login extends AppCompatActivity {

    EditText usuariotxt,
            editTextTextPassword;
    Button entrarbtn, esquecibtn,cadastrobtn;
    ProgressBar progressBar;

    FirebaseFirestore db=FirebaseFirestore.getInstance();
    String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


///id
        editTextTextPassword=findViewById(R.id.editTextTextPassword);
        esquecibtn =findViewById(R.id.esquecibtn);
        usuariotxt=findViewById(R.id.usuariotxt);
        entrarbtn =findViewById(R.id. entrarbtn);
        cadastrobtn=findViewById(R.id.cadastrobtn);
        esquecibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this, recuperarsenha.class);
                startActivity(intent);
            }
        });
        progressBar=findViewById(R.id.progressBar);

        entrarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (usuariotxt.getText().toString().isEmpty()|| editTextTextPassword.getText().toString().isEmpty()){

                    Toast.makeText(login.this, "Prencha todos Campos", Toast.LENGTH_SHORT).show();

                }else{

                    String email= usuariotxt.getText().toString();
                    String senha =  editTextTextPassword.getText().toString();
                    progressBar.setVisibility(View.VISIBLE);
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){


                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        FirebaseFirestore db=FirebaseFirestore.getInstance();
                                        usuarioID=FirebaseAuth.getInstance().getCurrentUser().getUid();


                                        Intent intent = new Intent(login.this, MainActivity.class);
                                        intent.putExtra("usuario",   usuariotxt.getText().toString());
                                        intent.putExtra("codigousuario",   usuarioID);
                                        startActivity(intent);
                                        finish();

                                    }
                                }, 3000);


                            }else {

                                try {
                                    throw task.getException();
                                } catch (Exception e) {

                                    Toast.makeText(login.this, "Erro ao Logar\n Caso nao tenha conta cria uma\nou Verifique a Senha e Usuario", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                } } }}); } }});


        cadastrobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, cadastro.class);
                startActivity(intent);
            }
        });


    }
}