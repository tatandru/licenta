package com.example.licenta;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.licenta.ui.pronume.PronumeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Button pronume;
    private FirebaseFirestore db;
    final Bundle bundleEn = new Bundle();
    final Bundle bundleRo = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pronume = findViewById(R.id.pronume);


        db = FirebaseFirestore.getInstance();
        getPronume();
        pronume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {


                    PronumeFragment pronumeFragment = new PronumeFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    Bundle bundle = new Bundle();
                    bundle.putBundle("bundleRo", bundleRo);
                    bundle.putBundle("bundleEn", bundleEn);
                    pronumeFragment.setArguments(bundle);
                    transaction.replace(R.id.pronumeContainer, pronumeFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getPronume() {
        db.collection("cuvinte").document("pronumeEngleza").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot pronumeRomana = task.getResult();
                List<String> pronume = (List<String>) pronumeRomana.get("pronume");
                ArrayList<String> pronumeRo = new ArrayList<>(pronume.size());
                pronumeRo.addAll(pronume);
                Log.d("myTag", pronume.toString());
                bundleEn.putStringArrayList("pronumeEngleza", pronumeRo);

            }
        });
        db.collection("cuvinte").document("pronumeRomana").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot pronumeRomana = task.getResult();
                List<String> pronume = (List<String>) pronumeRomana.get("pronume");
                ArrayList<String> pronumeRo = new ArrayList<>(pronume.size());
                pronumeRo.addAll(pronume);
                Log.d("myTag", pronume.toString());
                bundleRo.putStringArrayList("pronumeRomana", pronumeRo);

            }
        });
    }
}
