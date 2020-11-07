package de.fortaestro.lesson5app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import de.fortaestro.lesson5app.cityheraldry.CityHeraldryActivity;
import de.fortaestro.lesson5app.cityheraldry.CoatOfArmsActivity;
import de.fortaestro.lesson5app.fragmentmanager.FragmentManagerActivity;
import de.fortaestro.lesson5app.observer.ObserverActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button cityHeraldryButton = findViewById(R.id.buttonCityHeraldry);
        Button fragmentManagerButton = findViewById(R.id.buttonFragmentManager);
        Button observerButton = findViewById(R.id.buttonObserver);

        cityHeraldryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), CityHeraldryActivity.class);
                startActivity(intent);
            }
        });

        fragmentManagerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), FragmentManagerActivity.class);
                startActivity(intent);
            }
        });

        observerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), ObserverActivity.class);
                startActivity(intent);
            }
        });
    }
}