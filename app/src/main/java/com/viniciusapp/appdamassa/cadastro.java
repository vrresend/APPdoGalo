package com.viniciusapp.appdamassa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class cadastro extends AppCompatActivity {

    EditText usuariotxt, emailtxt, txtprovincia, cidadetxt, editTextTextPassword, conftPassword, pagamentotxt, editTextTextPassword2;
    Button entrarbtn, comomiinscrever, termosepoli, whtsappbtn;
    CheckBox switch1;
    TextView textView15, textView16;
    ProgressBar progressBar3;

    FirebaseFirestore db=FirebaseFirestore.getInstance();
    String usuarioID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        inicializar();


        db=FirebaseFirestore.getInstance();

        termosepoli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://appdamassa.vpshostingnow.com.br/politica-de-privacidade/");

            }
        });


// temos e politicas

        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (switch1.isChecked()) {

                    entrarbtn.setEnabled(true);
                } else {
                    entrarbtn.setEnabled(false);
                }



            }
        });


        entrarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar3.setVisibility(View.VISIBLE);
                cadastrar();

            }

        });


    }


    private void cadastrar(){

        //
        String email= emailtxt.getText().toString();
        String senha=  editTextTextPassword2.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    salvarUsuario();

                    progressBar3.setVisibility(View.GONE);

                    Toast.makeText(cadastro.this, "Cadastrado com Sucesso", Toast.LENGTH_SHORT).show();
                    usuarioID=FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Intent intent = new Intent(cadastro.this, MainActivity.class);
                    intent.putExtra("usuario",   usuariotxt.getText().toString());
                    intent.putExtra("codigousuario",   usuarioID);
                    startActivity(intent);
                    finish();

                }else{
                    String erro;
                    try {

                        throw task.getException();

                    } catch (FirebaseAuthWeakPasswordException e) {
                        Toast.makeText(cadastro.this, "Asenha deve ser mais de 6 Digitos", Toast.LENGTH_SHORT).show();

                        e.printStackTrace();
                    }catch (FirebaseAuthUserCollisionException e) {
                        Toast.makeText(cadastro.this, "Este Email Ja Foi Cadastrado", Toast.LENGTH_SHORT).show();

                    }
                    catch (FirebaseAuthInvalidCredentialsException e) {
                        Toast.makeText(cadastro.this, "Email Invalido", Toast.LENGTH_SHORT).show();

                    }
                    catch (Exception e) {
                        Toast.makeText(cadastro.this, "erro ao Cadastrar Usuario", Toast.LENGTH_SHORT).show();

                    }


                }




            }
        });




    }



    private void salvarUsuario() {

        String usuario= usuariotxt.getText().toString();
        String email= emailtxt.getText().toString();
        String pontos ="12";
        String senha=   editTextTextPassword2.getText().toString();
        FirebaseFirestore db=FirebaseFirestore.getInstance();

        Map<String, Object> usuarios=new HashMap<>();
        usuarios.put("usuario", usuario);
        usuarios.put("email", email);
        usuarios.put("senha", senha);
        usuarios.put("pontos", pontos);


        usuarioID=FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference=db.collection("Usuarios").document(usuarioID);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(cadastro.this, "Sucesso", Toast.LENGTH_SHORT).show();

                Log.d("db","Sucesso ao Salvar");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(cadastro.this, "Falha inesperada"+e, Toast.LENGTH_SHORT).show();

                        Log.d("db","Erro ao Salvar");
                    }
                });


    }

    private void inicializarFirebase() {

        FirebaseApp.initializeApp(cadastro.this);
        db=FirebaseFirestore.getInstance();
    }


    private void gotoUrl(String s) {
        Uri uri =Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));



    }



    private void inicializar() {
        entrarbtn=findViewById(R.id.entrarbtn);
        usuariotxt=findViewById(R.id.usuariotxt);
        emailtxt=findViewById(R.id.emailtxt);
        editTextTextPassword=findViewById(R.id.editTextTextPassword);
        switch1=findViewById(R.id.switch1);
        textView15=findViewById(R.id.textView15);
        editTextTextPassword2=findViewById(R.id.editTextTextPassword2);
        termosepoli=findViewById(R.id.termosepoli);
        progressBar3=findViewById(R.id.progressBar3);
    }
}