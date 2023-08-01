package com.example.ges_app_english;

import android.app.ListActivity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;

public class WORD extends ListActivity {

	private TextToSpeech ttobj;
	private float pitch = -5;
	private float speechRate = -5;
	private String[] word_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loadWordsFromAssets();
		setListAdapter(new WordArrayAdapter(this, word_list));
		initializeTextToSpeech();
	}

	private void loadWordsFromAssets() {
		ArrayList<String> words = new ArrayList<>();
		try {
			InputStream inputStream = getAssets().open("words.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while ((line = reader.readLine()) != null) {
				words.add(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		word_list = words.toArray(new String[0]);
	}

	private void initializeTextToSpeech() {
		ttobj = new TextToSpeech(getApplicationContext(), status -> {
			ttobj.setPitch(pitch);
			ttobj.setSpeechRate(speechRate);

			if (status != TextToSpeech.ERROR) {
				ttobj.setLanguage(Locale.US);
				// También podrías establecer otro idioma si lo prefieres.
				// Por ejemplo: ttobj.setLanguage(Locale.GERMANY);
			}
		});
	}

	@Override
	protected void onPause() {
		if (ttobj != null) {
			ttobj.stop();
			ttobj.shutdown();
		}
		super.onPause();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String selectedValue = (String) getListAdapter().getItem(position);
		ttobj.speak(selectedValue, TextToSpeech.QUEUE_FLUSH, null);
	}
}
