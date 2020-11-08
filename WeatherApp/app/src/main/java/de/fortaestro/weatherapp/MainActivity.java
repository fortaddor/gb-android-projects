package de.fortaestro.weatherapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import de.fortaestro.weatherapp.adapters.ForecastAdapter;
import de.fortaestro.weatherapp.business.forecast.Forecast;
import de.fortaestro.weatherapp.business.forecast.ForecastRepo;
import de.fortaestro.weatherapp.presenters.MainPresenter;
import de.fortaestro.weatherapp.utils.ActivityUtils;

import static de.fortaestro.weatherapp.utils.GlobalConsts.*;

public class MainActivity extends AppCompatActivity
{
    private MainPresenter mainPresenter;
    private TextView cityNameTextView;
    private TextView temperatureTextView;

    private String[] forecastValues;

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
        this.initForecast();

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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initForecast()
    {
        ForecastRepo forecastRepo = new ForecastRepo(this);
        Forecast forecast = forecastRepo.getForecast(Locale.getDefault().getLanguage(), this.cityNameTextView.getText().toString());

        if (forecast == null)
        {
            return;
        }

        this.forecastValues = forecast.getForecastDisplayValues();

//        ListView forecastListView = findViewById(R.id.listViewForecast);
//        List<String> forecastArrayList = new ArrayList<>(Arrays.asList(this.forecastValues));
//        ArrayAdapter listAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, forecastArrayList);
//        forecastListView.setAdapter(listAdapter);

        RecyclerView recyclerView = findViewById(R.id. recycler_view );
        recyclerView.setHasFixedSize( true );

        LinearLayoutManager layoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager(layoutManager);

        ForecastAdapter adapter = new ForecastAdapter(this.forecastValues);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getDrawable(R.drawable.separator));
        recyclerView.addItemDecoration(itemDecoration);
    }

    public void update(String townname)
    {
        if (townname.isEmpty())
        {
            return;
        }

        this.cityNameTextView.setText(townname);

        this.initForecast();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode != INPUT_ACTIVITY_RESULT && resultCode != RESULT_OK)
        {
            return;
        }

        String townName = data.getStringExtra("town");
        this.update(townName);
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