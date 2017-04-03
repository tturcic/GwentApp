package com.tt.gwentapp.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * @author tturcic
 *         \date 27.3.2017.
 */
public class PrefsManagerImpl implements PrefsManager {

    private static final String PREFS_VERSION = "version";

    private SharedPreferences prefs;

    public PrefsManagerImpl(Context context){
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }
    @Override
    public void setLatestCardVersion(String version) {
        prefs.edit().putString(PREFS_VERSION, version).apply();
    }

    @Override
    public String getLatestCardVersion() {
        return prefs.getString(PREFS_VERSION, null);
    }
}
