package com.example.ges_app_english;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.WindowManager;
import android.widget.Button;
import java.util.Locale;

public class PronunciationmadeeasyActivity extends Activity {

    private Button btnAIQuestion;
    private Button btnStart;
    private Button btnListWord;
    private Button btnTutorial;
    private Button btnExit;
    private TextToSpeech ttobj;
    private MediaPlayer mediaPlayer;
    private final Context context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);

        initializeTextToSpeech();
        addButtonListeners();
    }

    private void initializeTextToSpeech() {
        ttobj = new TextToSpeech(getApplicationContext(), status -> {
            if (status != TextToSpeech.ERROR) {
                ttobj.setLanguage(Locale.US);
            }
        });
    }

    private void addButtonListeners() {
        // AI Button
        btnAIQuestion = findViewById(R.id.btnExercises);
        btnAIQuestion.setOnClickListener(v -> {
            Intent intent = new Intent(PronunciationmadeeasyActivity.this, AIQUESTIONS.class);
            startActivity(intent);
        });

        // Start Button
        btnStart = findViewById(R.id.Start);
        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(PronunciationmadeeasyActivity.this, SPEECH.class);
            startActivity(intent);
        });

        // Word List Button
        btnListWord = findViewById(R.id.btnWord);
        btnListWord.setOnClickListener(v -> {
            Intent intent = new Intent(PronunciationmadeeasyActivity.this, WORD.class);
            startActivity(intent);
        });

        // Exit Button
        btnExit = findViewById(R.id.exit);
        btnExit.setOnClickListener(v -> showExitDialog());

        // Tutorial Button
        btnTutorial = findViewById(R.id.btnTutorial);
        btnTutorial.setOnClickListener(v -> {
            Intent intent = new Intent(PronunciationmadeeasyActivity.this, LIST.class);
            startActivity(intent);
        });
    }

    private void showExitDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Alert!");
        alertDialogBuilder
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> {
                    moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                })
                .setNegativeButton("No", (dialog, id) -> dialog.cancel());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    protected void onPause() {
        if (ttobj != null) {
            ttobj.stop();
            ttobj.shutdown();
        }

        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }

        super.onPause();
    }
}