package de.fortaestro.weatherapp.presenters;

public final class MainPresenter
{
    private static MainPresenter instance = new MainPresenter();
    private static final Object syncObj = new Object();

    private String townName;
    private String themperature;

    private MainPresenter()
    {
    }

    public static MainPresenter getInstance()
    {
        return instance;
    }

    public String getThemperature()
    {
        return this.themperature;
    }

    public void setThemperature(String themperature)
    {
        this.themperature = themperature;
    }

    public String getTownName()
    {
        return this.townName;
    }

    public void setTownName(String townName)
    {
        this.townName = townName;
    }
}
