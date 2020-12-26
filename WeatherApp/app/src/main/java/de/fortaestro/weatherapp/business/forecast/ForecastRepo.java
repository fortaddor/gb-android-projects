package de.fortaestro.weatherapp.business.forecast;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import de.fortaestro.weatherapp.R;

public final class ForecastRepo
{
    private final Context context;
    private Map<String, Map<String, Forecast>> forecastRepo = new HashMap<>();

    public ForecastRepo(Context context)
    {
        this.context = context;
    }

    public Forecast getForecast(String language, String town)
    {
        if (!this.hasRepo(language))
        {
            this.createRepo(language);
        }

        return this.forecastRepo.get(language).get(town);
    }

    private boolean hasRepo(String language)
    {
        return this.forecastRepo.containsKey(language);
    }

    private void createRepo(String language)
    {
        this.forecastRepo.put(language, this.createTownForecastMap());
    }

    private Map<String, Forecast> createTownForecastMap()
    {
        Map<String, Forecast> townForecastMap = new HashMap<>();

        String[] cityArray = this.context.getResources().getStringArray(R.array.cities);
        for (String city : cityArray)
        {
            townForecastMap.put(city, new Forecast(city));
        }

        return townForecastMap;
    }
}
