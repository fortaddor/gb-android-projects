package de.fortaestro.lesson5app.cityheraldry;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import de.fortaestro.lesson5app.R;

// Эта activity для показа герба в портретной ориентации
public class CoatOfArmsActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout. activity_coat_of_arms );
        if (getResources().getConfiguration(). orientation == Configuration. ORIENTATION_LANDSCAPE )
        {
            // Если устройство перевернули в альбомную ориентацию, то надо эту activity закрыть
            finish();
            return ;
        }

        if (savedInstanceState == null ) {
            // Если эта activity запускается первый раз (с каждым новым гербом первый раз)
            // то перенаправим параметр фрагменту
            CoatOfArmsFragment details = new CoatOfArmsFragment();
            details.setArguments(getIntent().getExtras());

            // Добавим фрагмент на activity
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, details).commit();
        }
    }
}