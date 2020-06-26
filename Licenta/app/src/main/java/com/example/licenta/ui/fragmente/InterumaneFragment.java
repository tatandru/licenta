package com.example.licenta.ui.fragmente;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.licenta.R;

import java.util.ArrayList;
import java.util.Locale;
import java.util.function.UnaryOperator;

import static android.app.Activity.RESULT_OK;

public class InterumaneFragment extends Fragment {
    private TextView intrebareEngleza;
    private TextView intrebabreRomana;
    private TextView raspunsRomana;
    private TextView raspunsEngleza;
    private TextView verificareIntrebare;
    private TextView verificareRaspuns;
    private Button TTSIntrebare;
    private Button STTIntrebare;
    private Button TTSRaspuns;
    private Button STTRaspuns;
    private TextToSpeech textToSpeech;
    private ArrayList<String> intrebareRomanaArray;
    private ArrayList<String> intrebareEnglezaArray;
    private ArrayList<String> raspunsuriRomanaArray;
    private ArrayList<String> raspunsuriEnglezaArray;
    private Button avanseaza;
    private double i;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lectii_fragment, container, false);
        intrebabreRomana = view.findViewById(R.id.intrebareRomanaLectii);
        intrebareEngleza = view.findViewById(R.id.intrebareEnglezaLectii);
        raspunsEngleza = view.findViewById(R.id.raspunsEnglezaLectii);
        raspunsRomana = view.findViewById(R.id.raspunsRomanaLectii);
        verificareIntrebare = view.findViewById(R.id.verificarePronuntieLectii1);
        verificareRaspuns = view.findViewById(R.id.verificarePronuntieLectii2);
        TTSIntrebare = view.findViewById(R.id.textToSpeechButtonLectii1);
        STTIntrebare = view.findViewById(R.id.speechToTextButtonLectii1);
        TTSRaspuns = view.findViewById(R.id.textToSpeechButtonLectii2);
        STTRaspuns = view.findViewById(R.id.speechToTextButtonLectii2);
        avanseaza = view.findViewById(R.id.avanseazaLectii);

        try {
            Bundle bundleQEn = getArguments().getBundle("bundleIntrebariUmaneEn");
            Bundle bundleQRo = getArguments().getBundle("bundleIntrebariUmaneRo");
            Bundle bundleAEn = getArguments().getBundle("bundleRaspunsuriUmaneEn");
            Bundle bundleARo = getArguments().getBundle("bundleRaspunsuriUmaneRo");
            intrebareRomanaArray = bundleQRo.getStringArrayList("intrebariUmaneRo");
            intrebareEnglezaArray = bundleQEn.getStringArrayList("intrebariUmaneEn");
            raspunsuriRomanaArray = bundleARo.getStringArrayList("raspunsuriUmaneRo");
            raspunsuriEnglezaArray = bundleAEn.getStringArrayList("raspunsuriUmaneEn");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        speechToTextButton(STTIntrebare);
        textToSpeechButton(TTSIntrebare, intrebareEngleza);
        speechToTextButton(STTRaspuns);
        textToSpeechButton(TTSRaspuns, raspunsEngleza);
        initTextToSpeech();

        intrebareEngleza.setText(intrebareEnglezaArray.get((int) i));
        intrebabreRomana.setText(intrebareRomanaArray.get((int) i));
        raspunsRomana.setText(raspunsuriRomanaArray.get((int) i));
        raspunsEngleza.setText(raspunsuriEnglezaArray.get((int) i));
        avanseazaButton();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ArrayList<String> text=new ArrayList<>();
        if(data!=null) {
            text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            text.replaceAll(new UnaryOperator<String>() {
                @Override
                public String apply(String e) {
                    return e.substring(0, 1).toUpperCase() + e.substring(1);
                }
            });
        }
        switch (requestCode) {
            case 10: {
                if (resultCode == RESULT_OK) {
                    if (text.contains(intrebareEngleza.getText())) {
                        verificareIntrebare.setText(intrebareEngleza.getText()+"?");
                        verificareIntrebare.setTextColor(Color.GREEN);
                    } else {
                        verificareIntrebare.setText(text.get(0));
                        verificareIntrebare.setTextColor(Color.RED);
                        Toast.makeText(getContext(), "Incearca din nou", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            }

            case 20: {
                if (text.contains(raspunsEngleza.getText())) {
                    verificareRaspuns.setText(raspunsEngleza.getText());
                    verificareRaspuns.setTextColor(Color.GREEN);
                } else {
                    verificareRaspuns.setText(text.get(0));
                    verificareRaspuns.setTextColor(Color.RED);
                    Toast.makeText(getContext(), "Incearca din nou", Toast.LENGTH_SHORT).show();
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

    private void speechToTextButton(Button STT) {
        final Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.US);
        STT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (STTIntrebare.isPressed())
                        startActivityForResult(intent, 10);
                    if (STTRaspuns.isPressed())
                        startActivityForResult(intent, 20);
                } catch (ActivityNotFoundException a) {

                    Toast.makeText(getContext(), "asdf", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void textToSpeechButton(Button TTS, final TextView textView) {
        TTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = textView.getText().toString();
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
                if (verificareIntrebare.getCurrentTextColor() == Color.GREEN && verificareRaspuns.getCurrentTextColor() == Color.GREEN) {
                    i++;

                    verificareIntrebare.setTextColor(Color.BLACK);
                    verificareIntrebare.setText("");
                    verificareRaspuns.setTextColor(Color.BLACK);
                    verificareRaspuns.setText("");
                    if (i >= intrebareEnglezaArray.size()) {

                        Toast.makeText(getContext(), "Felicitari", Toast.LENGTH_SHORT).show();
                    } else {
                        intrebareEngleza.setText(intrebareEnglezaArray.get((int) i));
                        intrebabreRomana.setText(intrebareRomanaArray.get((int) i));
                        raspunsRomana.setText(raspunsuriRomanaArray.get((int) i));
                        raspunsEngleza.setText(raspunsuriEnglezaArray.get((int) i));
                    }
                } else {
                    Toast.makeText(getContext(), "Incearca sa pronunti.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
