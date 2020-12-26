package de.fortaestro.weatherapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import de.fortaestro.weatherapp.presenters.MainPresenter;
import de.fortaestro.weatherapp.utils.ActivityUtils;

import static de.fortaestro.weatherapp.utils.GlobalConsts.*;

public class MainActivity extends AppCompatActivity
{
    private MainPresenter mainPresenter;
    private TextView cityNameTextView;
    private TextView temperatureTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mainPresenter = MainPresenter.getInstance();
        this.cityNameTextView = findViewById(R.id.city_name);
        this.temperatureTextView = findViewById(R.id.city_themperature);

        this.initCityName();
        this.initWikiButton();

        String instanceState;
        if (savedInstanceState == null )
        {
            instanceState = "First run!" ;
        }
        else
        {
            instanceState = "Further run!";
        }

        this.LogActivity(instanceState + " - onCreate()", "onCreate");
    }

    private void initCityName()
    {
        this.cityNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (ActivityUtils.getInstance().isOrientationLandscape(getResources().getConfiguration().orientation))
                {
                    if (getFragmentManager().findFragmentById(R.id.choosetown) == null)
                    {
                        ChoosetownFragment choosetownFragment = new ChoosetownFragment();

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.choosetown, choosetownFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .commit();
                    }
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), ChoosetownActivity.class);
                    startActivityForResult(intent, INPUT_ACTIVITY_RESULT);
                }
            }
        });
    }

    private void initWikiButton()
    {
        Button wikiButton = findViewById(R.id.buttonWikiInfo);

        wikiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String url = "https://de.wikipedia.org/wiki/" + cityNameTextView.getText();

            Uri uri = Uri.parse(url);
            Intent browser = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(browser);
            }
        });
    }

    public void update(String townname)
    {
        this.cityNameTextView.setText(townname);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode != INPUT_ACTIVITY_RESULT)
        {
            return;
        }

        if (resultCode != RESULT_OK)
        {
            return;
        }

        String townName = data.getStringExtra("town");

        if (!townName.isEmpty())
        {
            this.cityNameTextView.setText(townName);
        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        this.LogActivity("onStart()", "onStart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState)
    {
        super.onRestoreInstanceState(saveInstanceState);
        this.LogActivity("Further run! - onRestoreInstanceState()", "onRestoreInstanceState");

        this.temperatureTextView.setText(this.mainPresenter.getThemperature());
        this.cityNameTextView.setText(this.mainPresenter.getTownName());
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        this.LogActivity("onResume()", "onResume");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        this.LogActivity("onPause()", "onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState)
    {
        super.onSaveInstanceState(saveInstanceState);
        this.LogActivity("onSaveInstanceState()", "onSaveInstanceState");

        this.mainPresenter.setThemperature((String) this.temperatureTextView.getText());
        this.mainPresenter.setTownName((String) this.cityNameTextView.getText());
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        this.LogActivity("onStop()", "onStop");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        this.LogActivity("onRestart()", "onRestart");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        this.LogActivity("onDestroy()", "onDestroy");
    }

    public void LogActivity(String message, String tag)
    {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        Log.d(tag, message);
    }
}