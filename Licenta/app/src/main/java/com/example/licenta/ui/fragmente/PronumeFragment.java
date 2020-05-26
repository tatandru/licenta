package com.example.licenta.ui.fragmente;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.licenta.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Locale;
import java.util.function.UnaryOperator;

import static android.app.Activity.RESULT_OK;

public class PronumeFragment extends Fragment {

    private TextView pronumeRomana;
    private TextView pronumeEngleza;
    private TextView verificare;
    private Button TTS;
    private Button STT;
    private TextToSpeech textToSpeech;
    private ArrayList<String> pronumeRomanaArray;
    private ArrayList<String> pronumeEnglezaArray;
    private Button avanseaza;
    private double i;
    private ProgressBar progressBar;
    private double progress;
    private Handler handler = new Handler();

    public static PronumeFragment newInstance() {
        return new PronumeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general, container, false);
        pronumeRomana = view.findViewById(R.id.cuvantRomana);
        pronumeEngleza = view.findViewById(R.id.cuvantEngleza);
        verificare = view.findViewById(R.id.verificarePronuntie);
        TTS = view.findViewById(R.id.textToSpeechButton);
        STT = view.findViewById(R.id.speechToTextButton);
        avanseaza = view.findViewById(R.id.avanseaza);
        progressBar = view.findViewById(R.id.progressBar);

        try {
            Bundle bundleRo = getArguments().getBundle("bundlePronumeRo");
            Bundle bundleEn = getArguments().getBundle("bundlePronumeEn");
            pronumeRomanaArray = bundleRo.getStringArrayList("pronumeRomana");
            pronumeEnglezaArray = bundleEn.getStringArrayList("pronumeEngleza");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        speechToTextButton();
        textToSpeechButton();
        initTextToSpeech();
        progressBar.setProgress(0);
        progressBar.setMax((pronumeEnglezaArray.size() / pronumeEnglezaArray.size()) * 100);
        pronumeEngleza.setText(pronumeEnglezaArray.get((int) i));
        pronumeRomana.setText(pronumeRomanaArray.get((int) i));
        avanseazaButton();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10: {
                if (resultCode == RESULT_OK && null != data) {
                    java.util.ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    text.replaceAll(new UnaryOperator<String>() {
                        @Override
                        public String apply(String e) {
                            return e.toLowerCase();
                        }
                    });

                    if (text.contains(pronumeEngleza.getText())) {
                        verificare.setText(pronumeEngleza.getText());
                        verificare.setTextColor(Color.GREEN);
                    } else {
                        verificare.setText(text.get(0));
                        verificare.setTextColor(Color.RED);
                        Toast.makeText(getContext(), "Incearca din nou", Toast.LENGTH_SHORT).show();
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

    private void speechToTextButton() {
        final Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.US);
        STT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    startActivityForResult(intent, 10);
                } catch (ActivityNotFoundException a) {

                    Toast.makeText(getContext(), "asdf", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void textToSpeechButton() {
        TTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = pronumeEngleza.getText().toString();
                Log.i("TTS", "button clicked: " + data);
                int speechStatus = textToSpeech.speak(data, TextToSpeech.QUEUE_FLUSH, null);

                if (speechStatus == TextToSpeech.ERROR) {
                    Log.e("TTS", "Error in converting Text to Speech!");
                }


            }
        });
    }

    private void initTextToSpeech() {
        textToSpeech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
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
                    Toast.makeText(getContext(), "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
                }
            }
        }, "com.google.android.tts");
    }

    private void avanseazaButton() {
        avanseaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (verificare.getCurrentTextColor() == Color.GREEN) {
                    i++;

                    verificare.setTextColor(Color.BLACK);
                    verificare.setText("");
                    if (i >= pronumeEnglezaArray.size()) {
                        progressBar.setProgress((pronumeEnglezaArray.size() / pronumeEnglezaArray.size()) * 100);
                        Toast.makeText(getContext(), "Felicitari", Toast.LENGTH_SHORT).show();
                    } else {
                        pronumeEngleza.setText(pronumeEnglezaArray.get((int) i));
                        pronumeRomana.setText(pronumeRomanaArray.get((int) i));
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                progress = (i / pronumeEnglezaArray.size());
                                Log.d("myTag", Double.toString(progress));
                                try {
                                    Thread.sleep(20);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.setProgress((int) (progress * 100));
                                    }
                                });
                            }

                        }).start();
                    }
                } else {
                    Toast.makeText(getContext(), "Incearca sa pronunti.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}
