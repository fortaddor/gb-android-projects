package de.fortaestro.webbrowser;

import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "WebBrowser" ;
    private TextView url ;
    private WebView webView ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super .onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.browse);
        url = findViewById(R.id.url);

        Button ok = findViewById(R.id.ok);
        ok.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener()
    {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onClick(View v) {
            try {
                final URL uri = new URL(url .getText().toString());
                final Handler handler = new Handler(); // Запоминаем основной поток

                new Thread( new Runnable() {
                    public void run() {
                        HttpsURLConnection urlConnection = null ;
                        try {
                            URL uri = new URL(url .getText().toString());
                            urlConnection = (HttpsURLConnection) uri.openConnection();
                            urlConnection.setRequestMethod("GET"); // установка метода получения данных -GET
                            urlConnection.setReadTimeout(10000); // установка таймаута - 10000 миллисекунд
                            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); // читаем данные в поток
                            String result = getLines(in);

                            // Возвращаемся к основному потоку
                            handler .post( new Runnable()
                            {
                                @Override
                                public void run() {
                                    webView.loadData(result, "text/html; charset=utf-8", "utf-8");
                                }
                            });
                        }
                        catch (Exception e)
                        {
                            Log.e(TAG, "Fail connection", e);
                            e.printStackTrace();
                        }
                        finally
                        {
                            if (urlConnection != null)
                            {
                                urlConnection.disconnect();
                            }
                        }
                    }
                }).start();
            }
            catch (MalformedURLException e)
            {
                Log.e (TAG, "Fail URI", e);
                e.printStackTrace();
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        private String getLines(BufferedReader in) {
            return in.lines().collect(Collectors.joining(" \n "));
        }
    };
}