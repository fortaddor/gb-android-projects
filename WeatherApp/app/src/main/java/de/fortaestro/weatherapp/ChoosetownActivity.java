package de.fortaestro.weatherapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import de.fortaestro.weatherapp.presenters.ChoosetownPresenter;
import de.fortaestro.weatherapp.utils.ActivityUtils;

public class ChoosetownActivity extends AppCompatActivity
{
    private ChoosetownPresenter presenter;
    private ChoosetownFragment choosetownFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosetown);

        this.presenter = ChoosetownPresenter.getInstance();

        if (ActivityUtils.getInstance().isOrientationLandscape(getResources().getConfiguration().orientation))
        {
            this.finish();
            return;
        }

        if (savedInstanceState == null )
        {
            ChoosetownFragment choosetownFragment = new ChoosetownFragment();
            choosetownFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, choosetownFragment)
                    .commit();
        }
    }

    @Override
    public void finish()
    {
        EditText townEditText = findViewById(R.id.editTextTown);

        Intent data = new Intent();
        data.putExtra("town", townEditText.getText().toString());
        this.setResult(RESULT_OK, data);

        super.finish();
    }
}