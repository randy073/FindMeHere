package com.randy073.findmehere;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Randy on 8/6/2015.
 */
public class Application extends android.app.Application {
    // Debugging switch
    public static final boolean APPDEBUG = true;

    // Debugging tag for the application
    public static final String APPTAG = "FindMeHere";

    // Used to pass location from MainActivity
    public static final String INTENT_EXTRA_LOCATION = "location";

    // Key for saving the search distance preference
    private static final String KEY_SEARCH_DISTANCE = "searchDistance";

    private static final float DEFAULT_SEARCH_DISTANCE = 250.0f;

    private static SharedPreferences preferences;

    private static ConfigHelper configHelper;

    public Application() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        preferences = getSharedPreferences("com.randy073.findmehere", Context.MODE_PRIVATE);

       configHelper = new ConfigHelper();
    }

    public static float getSearchDistance() {
        return preferences.getFloat(KEY_SEARCH_DISTANCE, DEFAULT_SEARCH_DISTANCE);
    }

    public static ConfigHelper getConfigHelper() {
       return configHelper;
    }

    public static void setSearchDistance(float value) {
        preferences.edit().putFloat(KEY_SEARCH_DISTANCE, value).commit();
    }
}
