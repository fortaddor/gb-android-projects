package de.fortaestro.weatherforecast.model;

public class Main
{
    private float temp;
    private int tempCelsius;
    private int pressure;
    private int humidity;

    public float getTemp()
    {
        return temp;
    }

    public void setTemp(float temp)
    {
        this.temp = temp;
        this.calculateTempCelsius();
    }

    public int getPressure()
    {
        return pressure;
    }

    public void setPressure(int pressure)
    {
        this.pressure = pressure;
    }

    public int getHumidity()
    {
        return humidity;
    }

    public void setHumidity(int humidity)
    {
        this.humidity = humidity;
    }

    public int getTempCelsius()
    {
        return tempCelsius;
    }

    public void setTempCelsius(int tempCelsius)
    {
        this.tempCelsius = tempCelsius;
    }

    private void calculateTempCelsius()
    {
        int celsius = (int) (this.temp - 273.15);

        this.setTempCelsius(celsius);
    }
}
