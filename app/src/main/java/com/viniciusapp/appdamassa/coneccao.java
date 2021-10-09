package com.viniciusapp.appdamassa;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class coneccao {

    private  static FirebaseAuth firebaseAuth;
    private  static AuthStateListener authStateListener;
    private  static FirebaseUser firebaseUser;

    private coneccao(){

    }

    public  static FirebaseAuth getFirebaseAuth(){
        if (firebaseAuth==null){

            inicializarFirebaseAuth();
        }
        return firebaseAuth;
    }

    private static void inicializarFirebaseAuth() {
        firebaseAuth= getInstance();
        authStateListener= new AuthStateListener(){
            @Override
            public  void onAuthStateChanged (@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user !=null){
                    firebaseUser=user;

                }
            }

        };
        firebaseAuth.addAuthStateListener(authStateListener);
    }
    public static FirebaseUser getFirebaseUser(){
        return firebaseUser;
    }
    public static  void logOut(){
        firebaseAuth.signOut();
    }
}
