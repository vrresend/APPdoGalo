package com.viniciusapp.appdamassa;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class walpper extends AppCompatActivity implements myadapterer.RecyclerItemClick {

    Intent i;
    RecyclerView recview;
    ArrayList<model> datalist;
    FirebaseFirestore db;
    myadapterer adapter;
    FirebaseFirestore basededados = FirebaseFirestore.getInstance();
    private static final int PERMISSIONS_STORAGE_CODE = 1000;
    EditText pontostxt;
    String ID;

    private String GAMEID = "4192231";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTITIAL_ID = "Interstitial_Android";
    private Boolean test = true;
    private LinearLayout bannerlayout;

    String link = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walpper);
        recview = (RecyclerView) findViewById(R.id.recview);
        pontostxt = findViewById(R.id.pontostxt);
        recview.setLayoutManager(new LinearLayoutManager(this));


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
                //aumentapontos();
            }

            @Override
            public void onUnityAdsError(UnityAds.UnityAdsError error, String message) {
                // Toast.makeText(Album.this, "Erro ads", Toast.LENGTH_SHORT).show();
            }
        };
        UnityAds.setListener(iUnityAdsListener);


        datalist = new ArrayList<>();
        adapter = new myadapterer(this, datalist, this);
        recview.setAdapter(adapter);
        db = FirebaseFirestore.getInstance();
        todas();


    }

    private void todas() {

        db.collection("wallpaper")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d : list) {

                            // datalist.clear();
                            model obj = d.toObject(model.class);
                            datalist.add(obj);


                        }
                        adapter.notifyDataSetChanged();
                    }
                });


    }

    private void startDownloading(String link) {


        String url = link;
        // String url = pesqu.getText().toString().trim();
        //String url = "https://firebasestorage.googleapis.com/v0/b/mozplay-cdafc.appspot.com/o/k1%20rapper-%20INSTRUMENTAL%20-inocente.mp3?alt=media&token=380d8632-7e0c-4562-8e82-5083f5503256";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Download");
        request.setDescription("Baixando Arquivo...");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "" + System.currentTimeMillis());
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_STORAGE_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // startDownloading();
                } else {
                    AlertDialog.Builder alerta = new AlertDialog.Builder(walpper.this);
                    alerta.setMessage("permissions daned").setNegativeButton("volte sempre"
                            , null)
                            .create()
                            .show();
                }
        }


    }


    @Override
    public void itemClick(model item) {
        String link =item.imagem;

        Toast.makeText(walpper.this, "Testado com sucesso "+link, Toast.LENGTH_LONG).show();
        startDownloading(link);


        /*

        Intent i = getIntent();
         String codigo_Usuario = i.getExtras().getString("codigousuario");


        String usuarioID=codigo_Usuario;
        DocumentReference documentReference =db.collection("Usuarios")
                .document(usuarioID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if (documentSnapshot!=null){
                    pontostxt.setText(documentSnapshot.getString("usuario"));

                    Private void pontosmostra(){
                    }
                }
            }
        });


        int pontos =Integer.parseInt(pontostxt.getText().toString());

        if(pontos >=4){

            diminui();
        }else{
            Toast.makeText(this, "Pontos insuficientes para fazer download\n" +
                    "Assista um video curto e ganha 12 pontos", Toast.LENGTH_SHORT).show();
            UnityAds.load(INTERSTITIAL_ID);
            mostrainterticial();

        }



*/

    }

    private  void mostrainterticial() {
        if (UnityAds.isReady(INTERSTITIAL_ID)) {
            UnityAds.show(walpper.this, INTERSTITIAL_ID);

        }
    }
    private  void aumentapontos(){

        Intent i = getIntent();
        int numero= Integer.parseInt(pontostxt.getText().toString().trim());
        numero=numero+12;
        String idusurio =i.getExtras().getString("codigousuario");
        DocumentReference ref = db.collection("Usuarios").document(""+idusurio);
        HashMap hashMap = new HashMap();
        hashMap.put("pontos", ""+numero);
        ref.update(hashMap).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(walpper.this, "Ganhaste 12 pontos", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Toast.makeText(escutar.this, "Falha", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private  void diminui(){

        Intent i = getIntent();
        int numero= Integer.parseInt(pontostxt.getText().toString().trim());
        numero=numero-4;
        String idusurio =i.getExtras().getString("codigousuario");
        DocumentReference ref = db.collection("Usuarios").document(""+idusurio);
        HashMap hashMap = new HashMap();
        hashMap.put("pontos", ""+numero);
        ref.update(hashMap).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(walpper.this, "Ganhaste 12 pontos", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Toast.makeText(escutar.this, "Falha", Toast.LENGTH_SHORT).show();
            }
        });

    }



}


