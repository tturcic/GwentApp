package com.tt.gwentapp.data.local;

/**
 * @author tturcic
 *         \date 27.3.2017.
 */
public interface PrefsManager {

    void setLatestCardVersion(String version);
    String getLatestCardVersion();

}
