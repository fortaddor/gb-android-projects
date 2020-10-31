package de.fortaestro.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static de.fortaestro.weatherapp.utils.GlobalConsts.*;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView cityNameTextView = findViewById(R.id.city_name);

        cityNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), ChoosetownActivity.class);
                startActivityForResult(intent, INPUT_ACTIVITY_RESULT);
            }
        });
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

            TextView cityNameTextView = this.findViewById(R.id.city_name);
            cityNameTextView.setText(town);
        }
    }
}