package de.fortaestro.weatherapp.utils;

import android.content.res.Configuration;

public final class ActivityUtils
{
    private static final ActivityUtils instance = new ActivityUtils();

    private ActivityUtils()
    {

    }

    public static ActivityUtils getInstance()
    {
        return instance;
    }

    public boolean isOrientationLandscape(int orientation)
    {
        return orientation == Configuration.ORIENTATION_LANDSCAPE;
    }
}
