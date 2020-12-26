package de.fortaestro.weatherapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.fortaestro.weatherapp.presenters.ChoosetownPresenter;

public class ChoosetownFragment extends Fragment
{
    private String[] townArray;

    private ChoosetownPresenter presenter;
    private EditText townNameEditText;
    private CheckBox windCheckBox;
    private CheckBox pressureCheckBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View layout = inflater.inflate(R.layout.fragment_choosetown, container, false);

        this.presenter = ChoosetownPresenter.getInstance();
        this.townNameEditText = layout.findViewById(R.id.editTextTown);
        this.windCheckBox = layout.findViewById(R.id.checkBoxWind);
        this.pressureCheckBox = layout.findViewById(R.id.checkBoxPressure);

        this.townArray = getResources().getStringArray(R.array.cities);

        this.initListView(layout);
        this.initButtons(layout);

        return layout;
    }

    private void initListView(final View layout)
    {
        ListView townListView = layout.findViewById(R.id.listViewCommonTowns);
        List<String> townArrayList = new ArrayList<>(Arrays.asList(this.townArray));
        ArrayAdapter listAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, townArrayList);
        townListView.setAdapter(listAdapter);

        townListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                String townName = adapterView.getItemAtPosition(i).toString();
                EditText townEditText = layout.findViewById(R.id.editTextTown);
                townEditText.setText(townName);
            }
        });
    }

    private void initButtons(View layout)
    {
        Button acceptButton = layout.findViewById(R.id.buttonAccept);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (getActivity() instanceof MainActivity)
                {
                    ((MainActivity)getActivity()).update(getTownname());
                }
                else
                {
                    getActivity().finish();
                }
            }
        });
    }

    public void setTownname(String townname)
    {
        this.townNameEditText.setText(townname);
    }

    public String getTownname()
    {
        return this.townNameEditText.getText().toString();
    }

    public void setWithWind(boolean withWind)
    {
        this.windCheckBox.setChecked(withWind);
    }

    public boolean getWithWind()
    {
        return this.windCheckBox.isChecked();
    }

    public void setWithPressure(boolean withPressure)
    {
        this.pressureCheckBox.setChecked(withPressure);
    }

    public boolean getWithPressure()
    {
        return this.pressureCheckBox.isChecked();
    }
}