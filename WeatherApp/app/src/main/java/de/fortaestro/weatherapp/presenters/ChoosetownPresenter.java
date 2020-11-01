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
        return this.townName;
    }

    public void setTownName(String townName)
    {
        this.townName = townName;
    }

    public boolean isWithWind()
    {
        return this.withWind;
    }

    public void setWithWind(boolean withWind)
    {
        this.withWind = withWind;
    }

    public boolean isWithPressure()
    {
        return this.withPressure;
    }

    public void setWithPressure(boolean withPressure)
    {
        this.withPressure = withPressure;
    }
}
