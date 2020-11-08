package de.fortaestro.weatherapp.business.forecast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.fortaestro.weatherapp.utils.RandomUtils;

public class Forecast
{
    private final String town;
    private List<DayForecast> dayForecastList;

    public Forecast(String town)
    {
        this.town = town;
        this.dayForecastList = new ArrayList<>();

        this.createNextDaysTemperatureForcast(3);
    }

    public String[] getForecastDisplayValues()
    {
        String[] values = new String[this.dayForecastList.size()];

        for (int i = 0; i < this.dayForecastList.size(); i++)
        {
            values[i] = this.dayForecastList.get(i).toString();
        }

        return values;
    }

    public String getTown()
    {
        return this.town;
    }

    private void createNextDaysTemperatureForcast(int dayCount)
    {
        Calendar date = Calendar.getInstance();

        for (int i = 1; i <= dayCount; i++)
        {
            date.add(Calendar.DAY_OF_MONTH, 1);
            this.dayForecastList.add(new DayForecast(date.getTime(), RandomUtils.getInstance().getInt(10, 10)));
        }
    }
}
