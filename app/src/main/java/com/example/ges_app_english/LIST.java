package com.example.ges_app_english;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

class TOPICS_ELEMENT {
    private String LESSON;
    private String pme;
    private String number;

    public TOPICS_ELEMENT(String LESSON, String pme, String number) {
        this.LESSON = LESSON;
        this.pme = pme;
        this.number = number;
    }
    public String getLESSON() {
        return LESSON;
    }
    public void setLESSON(String LESSON) {
        this.LESSON = LESSON;
    }
    public String getpme() {
        return pme;
    }
    public void setpme(String pme) {
        this.pme = pme;
    }
    public String getnumber() {
        return number;
    }
    public void setnumber(String number) {
        this.number = number;
    }
}
public class LIST extends ListActivity implements OnClickListener {

    private ArrayList<TOPICS_ELEMENT> TOPICS = new ArrayList<>();
    private ArrayList<String> TOPICS_STRING = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        // Cargar los elementos de la lista desde el archivo "lesson_list.txt" en assets
        loadTopicsFromAssets();

        for (TOPICS_ELEMENT elemento : TOPICS) {
            TOPICS_STRING.add(elemento.getLESSON());
        }
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list, TOPICS_STRING));

        ListView listView = getListView();
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                // TODO Auto-generated method stub

                Bundle b = new Bundle();
                String selectedValue = (String) getListAdapter().getItem(position);

                Pair<String, String> result = buscarPorLesson(selectedValue);
                b.putString(result.first, result.second);

                Intent intent = new Intent(LIST.this, TUTORIAL.class);
                intent.putExtras(b);
                startActivity(intent);

            }

        });


    }

    private void loadTopicsFromAssets() {
        try {
            InputStream inputStream = getAssets().open("lessons_list.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] bits = line.split(";");
                TOPICS.add(new TOPICS_ELEMENT(bits[0], bits[1], bits[2]));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pair<String, String> buscarPorLesson(String lessonBuscado) {
        for (TOPICS_ELEMENT elemento : TOPICS) {
            if (elemento.getLESSON().equals(lessonBuscado)) {
                String pme = elemento.getpme();
                String number = elemento.getnumber();
                return new Pair<>(pme, number);
            }
        }
        return null; // Devuelve null si no se encuentra el objeto con el Lesson buscado
    }


    public void onClick(View arg0) {
        // TODO Auto-generated method stub

    }

}
