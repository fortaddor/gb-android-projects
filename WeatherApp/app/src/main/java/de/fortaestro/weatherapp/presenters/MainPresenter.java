package de.fortaestro.weatherapp.presenters;

public final class MainPresenter
{
    private static MainPresenter instance;
    private static final Object syncObj = new Object();

    private String townName;
    private String themperature;

    private MainPresenter()
    {
    }

    public static MainPresenter getInstance()
    {
        synchronized (syncObj)
        {
            if (instance == null)
            {
                instance = new MainPresenter();
            }

            return instance;
        }
    }

    public String getThemperature()
    {
        return themperature;
    }

    public void setThemperature(String themperature)
    {
        this.themperature = themperature;
    }

    public String getTownName()
    {
        return townName;
    }

    public void setTownName(String townName)
    {
        this.townName = townName;
    }
}
