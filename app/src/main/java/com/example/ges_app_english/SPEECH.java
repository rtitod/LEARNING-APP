package com.example.ges_app_english;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class SPEECH extends Activity {

    private float pitch = -1;
    private float speechRate = -1;
    private Button btnBack;
    private Button btnClear;
    private TextToSpeech ttobj;
    private EditText write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speech);

        // Inicializar TextToSpeech
        ttobj = new TextToSpeech(getApplicationContext(), status -> {
            if (status != TextToSpeech.ERROR) {
                ttobj.setLanguage(Locale.US);
                /**

                 Sr.No	Locale
                 1	US
                 2	CANADA_FRENCH
                 3	GERMANY
                 4	ITALY
                 5	JAPAN
                 6	CHINA
                 7	UK
                 **/
            }
        });

        // Obtener referencias a las vistas
        write = findViewById(R.id.txtInput);
        btnBack = findViewById(R.id.btnBack);
        btnClear = findViewById(R.id.btnClear);

        // Agregar listeners a los botones
        addButtonListeners();
    }

    private void addButtonListeners() {
        // Botón Back
        btnBack.setOnClickListener(v -> {
            Intent e = new Intent(SPEECH.this, PronunciationmadeeasyActivity.class);
            startActivity(e);
        });

        // Botón Clear
        btnClear.setOnClickListener(v -> {
            write.setText("");
            stopSpeaking();
        });
    }

    private void stopSpeaking() {
        if (ttobj != null) {
            ttobj.stop();
        }
    }

    public void onPause() {
        stopSpeaking();
        super.onPause();
    }

    public void speakText(View view) {
        ttobj.setPitch(pitch);
        ttobj.setSpeechRate(speechRate);
        String toSpeak = write.getText().toString();
        ttobj.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }
}