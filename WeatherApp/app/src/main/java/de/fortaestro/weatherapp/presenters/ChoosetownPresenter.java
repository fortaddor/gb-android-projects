package de.fortaestro.weatherapp.presenters;

public final class ChoosetownPresenter
{
    private static ChoosetownPresenter instance;
    private static final Object syncObj = new Object();

    private String townName;
    private boolean withWind;
    private boolean withPressure;

    private ChoosetownPresenter()
    {
    }

    public static ChoosetownPresenter getInstance()
    {
        synchronized (syncObj)
        {
            if (instance == null)
            {
                instance = new ChoosetownPresenter();
            }

            return instance;
        }
    }

    public String getTownName()
    {
        return townName;
    }

    public void setTownName(String townName)
    {
        this.townName = townName;
    }

    public boolean isWithWind()
    {
        return withWind;
    }

    public void setWithWind(boolean withWind)
    {
        this.withWind = withWind;
    }

    public boolean isWithPressure()
    {
        return withPressure;
    }

    public void setWithPressure(boolean withPressure)
    {
        this.withPressure = withPressure;
    }
}
