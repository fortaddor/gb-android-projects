package de.fortaestro.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import de.fortaestro.weatherapp.presenters.MainPresenter;

import static de.fortaestro.weatherapp.utils.GlobalConsts.*;

public class MainActivity extends AppCompatActivity
{
    private MainPresenter mainPresenter;
    private TextView cityNameTextView;
    private TextView themperatureTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mainPresenter = MainPresenter.getInstance();
        this.cityNameTextView = findViewById(R.id.city_name);
        this.themperatureTextView = findViewById(R.id.city_themperature);

        this.cityNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), ChoosetownActivity.class);
                startActivityForResult(intent, INPUT_ACTIVITY_RESULT);
            }
        });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode != INPUT_ACTIVITY_RESULT)
        {
            return;
        }

        if (resultCode == RESULT_OK)
        {
            String town = data.getStringExtra("town");

            this.cityNameTextView.setText(town);
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

        this.themperatureTextView.setText(this.mainPresenter.getThemperature());
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

        this.mainPresenter.setThemperature((String) this.themperatureTextView.getText());
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