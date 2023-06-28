package ideanity.oceans.pronunciation;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class TUTORIAL extends Activity {

    WebView mWebView;

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial);

        Bundle b = getIntent().getExtras();
        mWebView = (WebView) findViewById(R.id.web1);

        mWebView.getSettings().setJavaScriptEnabled(true);

        int result = Integer.parseInt(b.getString("pme1"));

        if (result == 1) {
            mWebView.loadUrl("file:///android_asset/Lesson1.html");

        }

    }

}
