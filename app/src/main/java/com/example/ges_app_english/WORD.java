package com.example.ges_app_english;

import java.util.Locale;

import android.app.ListActivity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ListView;

public class WORD extends ListActivity {


	static final String[] word_list =
   new String[] {
				    "accessary, accessory",
				    "ad, add",
				    "ail, ale",
				    "air, heir",
				    "aisle, I'll, isle",
				    "all, awl",
				    "allowed, aloud",

	};




	TextToSpeech ttobj;
	float pitch = -5;
    float speechRate = -5;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setListAdapter(new WordArrayAdapter(this, word_list ));


		ttobj=new TextToSpeech(getApplicationContext(),
                new TextToSpeech.OnInitListener() {
                public void onInit(int status) {

                	ttobj.setPitch(pitch);
        			ttobj.setSpeechRate(speechRate);

                   if(status != TextToSpeech.ERROR){
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
                   }
                });

	}


	public void onPause(){
        if(ttobj !=null){
           ttobj.stop();
           ttobj.shutdown();
        }
        super.onPause();
     }


	protected void onListItemClick(ListView l, View v, int position, long id) {

        String selectedValue = (String) getListAdapter().getItem(position);

        ttobj.speak(selectedValue, TextToSpeech.QUEUE_FLUSH, null);


    }




}
