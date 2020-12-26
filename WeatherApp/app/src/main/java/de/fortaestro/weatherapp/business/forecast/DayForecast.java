package de.fortaestro.weatherapp.business.forecast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DayForecast
{
    private final Date date;
    private int temperature;

    public DayForecast(Date date, int temperature)
    {
        this.date = date;
        this.temperature = temperature;
    }

    @Override
    public String toString()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd.MM.yyyy");
        return formatter.format(this.date) + " \t " + this.temperature + "°";
    }

    public Date getDate()
    {
        return this.date;
    }

    public int getTemperature()
    {
        return this.temperature;
    }

    public void setTemperature(int temperature)
    {
        this.temperature = temperature;
    }
}
