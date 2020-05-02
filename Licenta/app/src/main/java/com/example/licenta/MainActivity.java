package com.example.licenta;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView romana;
    private TextView engleza;
    private TextView verificare;
    private FirebaseFirestore db;
    private Button TTS;
    private Button STT;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        romana = findViewById(R.id.textView);
        engleza = findViewById(R.id.helloWorld);
        verificare=findViewById(R.id.abc);
        TTS = findViewById(R.id.pressToListen);
        STT = findViewById(R.id.buttonTest);


        final Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.US);


        db = FirebaseFirestore.getInstance();
        db.collection("cuvinte").document("pronumeRomana").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot pronumeRomana = task.getResult();
                List<String> pronume = (List<String>) pronumeRomana.get("pronume");
                Log.d("myTag", pronume.toString());
                romana.setText(pronume.get(0));
            }
        });
        db.collection("cuvinte").document("pronumeEngleza").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot pronumeRomana = task.getResult();
                List<String> pronumeE = (List<String>) pronumeRomana.get("pronume");
                Log.d("myTag", pronumeE.toString());
                engleza.setText(pronumeE.get(0));
            }
        });


        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int ttsLang = textToSpeech.setLanguage(Locale.US);
                    textToSpeech.setSpeechRate(1);

                    if (ttsLang == TextToSpeech.LANG_MISSING_DATA
                            || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "The Language is not supported!");
                    } else {
                        Log.i("TTS", "Language Supported.");
                    }
                    Log.i("TTS", "Initialization success.");
                } else {
                    Toast.makeText(getApplicationContext(), "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
                }
            }
        }, "com.google.android.tts");

        TTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = engleza.getText().toString();
                Log.i("TTS", "button clicked: " + data);
                int speechStatus = textToSpeech.speak(data, TextToSpeech.QUEUE_FLUSH, null);

                if (speechStatus == TextToSpeech.ERROR) {
                    Log.e("TTS", "Error in converting Text to Speech!");
                }


            }
        });


        STT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    startActivityForResult(intent, 10);
                } catch (ActivityNotFoundException a) {

                    Toast.makeText(getApplicationContext(), "asdf", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10: {
                if (resultCode == RESULT_OK && null != data) {
                    java.util.ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (text.get(0).equals(engleza.getText())) {
                        verificare.setText(text.get(0));
                    }

                }
                break;
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

}
