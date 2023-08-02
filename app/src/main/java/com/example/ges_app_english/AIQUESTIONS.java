package com.example.ges_app_english;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class AIQUESTIONS extends Activity {
    private Button btnFinalizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aigen);

        LinearLayout buttonContainer = findViewById(R.id.LinContainer);
        for (int i = 1; i <= 5; i++) {
            Button button = new Button(this);
            button.setText(String.valueOf(i));
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            buttonContainer.addView(button);
        }
        btnFinalizar = findViewById(R.id.btnFinalizar);
        addButtonListeners();
    }

    private void addButtonListeners() {
        btnFinalizar.setOnClickListener(v -> {
            Intent e = new Intent(AIQUESTIONS.this, PronunciationmadeeasyActivity.class);
            startActivity(e);
        });
    }
}
