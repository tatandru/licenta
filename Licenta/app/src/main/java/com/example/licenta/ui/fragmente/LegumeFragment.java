package com.example.licenta.ui.fragmente;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.licenta.R;

import java.util.ArrayList;
import java.util.Locale;
import java.util.function.UnaryOperator;

import static android.app.Activity.RESULT_OK;

public class LegumeFragment extends Fragment {
    private TextView legumeEngleza;
    private TextView legumeRomana;
    private TextView verificare;
    private Button TTS;
    private Button STT;
    private TextToSpeech textToSpeech;
    private ArrayList<String> legumeRomanaArray;
    private ArrayList<String> legumeEnglezaArray;
    private Button avanseaza;
    private int i;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general, container, false);
        legumeRomana = view.findViewById(R.id.cuvantRomana);
        legumeEngleza = view.findViewById(R.id.cuvantEngleza);
        verificare = view.findViewById(R.id.verificarePronuntie);
        TTS = view.findViewById(R.id.textToSpeechButton);
        STT = view.findViewById(R.id.speechToTextButton);
        avanseaza = view.findViewById(R.id.avanseaza);

        try {
            Bundle bundleRo = getArguments().getBundle("bundleLegumeRo");
            Bundle bundleEn = getArguments().getBundle("bundleLegumeEn");
            legumeRomanaArray = bundleRo.getStringArrayList("legumeRomana");
            legumeEnglezaArray = bundleEn.getStringArrayList("legumeEngleza");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        speechToTextButton();
        textToSpeechButton();
        initTextToSpeech();
        legumeEngleza.setText(legumeEnglezaArray.get(i));
        legumeRomana.setText(legumeRomanaArray.get(i));
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

                    if (text.get(0).equals(legumeEngleza.getText())) {
                        verificare.setText(text.get(0));
                        verificare.setTextColor(Color.GREEN);
                    } else {
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
                String data = legumeEngleza.getText().toString();
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
                    legumeEngleza.setText(legumeEnglezaArray.get(i));
                    legumeRomana.setText(legumeRomanaArray.get(i));
                } else {
                    Toast.makeText(getContext(), "Incearca sa pronunti.", Toast.LENGTH_SHORT).show();
                }
                if (i > legumeRomanaArray.size()) {
                    Toast.makeText(getContext(), "Felicitari", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
