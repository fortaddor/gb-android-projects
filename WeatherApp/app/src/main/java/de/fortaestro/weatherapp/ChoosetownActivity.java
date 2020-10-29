package de.fortaestro.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChoosetownActivity extends AppCompatActivity
{
    private String[] townArray = new String[]
            {
                    "Berlin", "Hamburg", "London", "Moskau", "New York", "Schwarzenbek"
            };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosetown);

        this.initListView();
        this.initButtons();
    }

    private void initListView()
    {
        ListView townListView = findViewById(R.id.listViewCommonTowns);
        List<String> townArrayList = new ArrayList<>(Arrays.asList(this.townArray));
        ArrayAdapter listAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, townArrayList);
        townListView.setAdapter(listAdapter);

        townListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                String townName = adapterView.getItemAtPosition(i).toString();
                EditText townEditText = findViewById(R.id.editTextTown);
                townEditText.setText(townName);
            }
        });
    }

    private void initButtons()
    {
        Button acceptButton = findViewById(R.id.buttonAccept);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
    }

    @Override
    public void finish() {
        EditText townEditText = findViewById(R.id.editTextTown);

        Intent data = new Intent();
        data.putExtra("town", townEditText.getText().toString());
        this.setResult(RESULT_OK, data);
        super.finish();
    }
}