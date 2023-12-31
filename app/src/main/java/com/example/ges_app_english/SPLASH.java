package com.example.ges_app_english;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Locale;

public class SPLASH extends Activity implements OnInitListener {

    private TextToSpeech myTTS;
    private int MY_DATA_CHECK_CODE = 0;
    private boolean timerStarted = false; // Variable para verificar si el temporizador ya se ha iniciado



    private Handler handler = new Handler(); // Handler para gestionar los retrasos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);

        // Check if Text-to-Speech (TTS) is supported on the device
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);

        // Lanzar el temporizador utilizando Handler
        if (!timerStarted) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!isFinishing()) {
                        // After the timer ends, navigate to PronunciationmadeeasyActivity
                        Intent i = new Intent(SPLASH.this, PronunciationmadeeasyActivity.class);
                        finish();
                        startActivity(i);
                    }
                }
            }, 5000); // Retraso de 5 segundos
            timerStarted = true;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                // The user has the necessary data - create the TTS instance
                myTTS = new TextToSpeech(this, this);
            } else {
                // TTS data is not available, prompt the user to install it
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }

    // TTS OnInitListener callback
    @Override
    public void onInit(int initStatus) {
        if (initStatus == TextToSpeech.SUCCESS) {
            if (myTTS.isLanguageAvailable(Locale.US) == 1) {
                myTTS.setLanguage(Locale.US);
                // Speak the welcome message after TTS initialization is successful
                Log.d("TTS", "Calling To SpeakWords");
                speakWords("Welcome to Solo Learn App");
            }
        // 2 seconds delay before checking language availability
        } else {
            // TTS initialization failed
            Toast.makeText(this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
        }
    }

    private void speakWords(String speech) {
        if (myTTS != null) {
            Log.d("TTS", "Attempting to speak: " + speech);
            myTTS.speak(speech, TextToSpeech.QUEUE_FLUSH, null, "UtteranceId");
        }
    }

    // Override onDestroy to release the TextToSpeech resources
    @Override
    protected void onDestroy() {
        if (myTTS != null) {
            myTTS.stop();
            myTTS.shutdown();
        }
        super.onDestroy();
    }
}