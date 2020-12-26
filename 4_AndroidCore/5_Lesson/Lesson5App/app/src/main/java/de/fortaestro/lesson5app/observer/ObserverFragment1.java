package de.fortaestro.lesson5app.observer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.fortaestro.lesson5app.R;

public class ObserverFragment1 extends Fragment implements Observer
{
    private TextView textView ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_observer1, container, false);
        this.textView = view.findViewById(R.id.textView);

        return view;
    }

    // Как только пришло событие - обработаем его
    @Override
    public void updateText(String text)
    {
        this.textView.setText(text);
    }
}